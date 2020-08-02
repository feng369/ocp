package com.topideal.supplychain.ocp.amway.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.amway.converter.AmwayDtoConverter;
import com.topideal.supplychain.ocp.amway.dto.AmwayPlatformArs;
import com.topideal.supplychain.ocp.amway.dto.CrossBorder;
import com.topideal.supplychain.ocp.amway.dto.OCPAmwayOrderRequestPojo;
import com.topideal.supplychain.ocp.amway.dto.Order;
import com.topideal.supplychain.ocp.amway.dto.OrderHeader;
import com.topideal.supplychain.ocp.amway.dto.OrderLine;
import com.topideal.supplychain.ocp.amway.dto.PaymentHeader;
import com.topideal.supplychain.ocp.amway.dto.PaymentLine;
import com.topideal.supplychain.ocp.amway.service.AmwayService;
import com.topideal.supplychain.ocp.config.dto.CatchDefaultConfig;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.config.service.TransferConfigService;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.mq.QueueConstants.QueueEnum;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
import com.topideal.supplychain.ocp.order.model.OrderAmway;
import com.topideal.supplychain.ocp.order.model.OrderAmwayItem;
import com.topideal.supplychain.ocp.order.model.OrderAmwayPaymentLine;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderAmwayItemService;
import com.topideal.supplychain.ocp.order.service.OrderAmwayPaymentLineService;
import com.topideal.supplychain.ocp.order.service.OrderAmwayService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.ocp.service.SystemConfigService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.system.service.ConfigService;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AmwayServiceImpl implements AmwayService {

    @Autowired
    private OrderAmwayService orderAmwayService;

    @Autowired
    private OfcApiService ofcApiService;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private OrderAmwayItemService amwayItemService;

    @Autowired
    private OrderTempService orderTempService;

    @Autowired
    private CatchOrderConfigService catchOrderConfigService;

    @Autowired
    private TransferConfigService transferConfigService;
    @Autowired
    private SystemConfigService configService;


    @Autowired
    private OrderAmwayPaymentLineService amwayPaymentLineService;
    protected static final Logger logger = LoggerFactory.getLogger(AmwayServiceImpl.class);

    private static final String AMWAY_DEFAULT_PLATFORM = "1000005001";


    @Override
    public BaseResponse sendOrderOfc(OrderAmway orderAmway) {
        Long orderId = orderAmway.getId();
        // 转单配置
        TransferConfig transferConfig = transferConfigService
                .findByUnique(orderAmway.getEbcCode(), orderAmway.getEbpCode(),
                        orderAmway.getDeliveryCode(), orderAmway.getCustoms(),
                        BusiModeEnum.getDescEnum(orderAmway.getImportMode()).getValue());
        if (null == transferConfig) {
            orderAmwayService.updateOrderStatus(orderAmway.getId(), OrderStatusEnum.SEND_FAILURE.getValue());
            return BaseResponse.responseFailure("订单推送OFC未找到对应转发配置");
        }
        List<OrderAmwayItem> itemList = amwayItemService.selectByOrderId(orderId);
        OfcRequest ofcRequest = AmwayDtoConverter.convertOfc(orderAmway, itemList);
        BaseResponse baseResponse = ofcApiService
                .sendOrder(ofcRequest, orderAmway.getOrderNumber(), orderAmway.getCode());
        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        if (baseResponse.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        orderAmwayService.updateOrderStatus(orderId, orderStatus.getValue());
        return baseResponse;

    }

    @Override
    public BaseResponse rePush(List<OrderAmway> orderAmwayList) {
        for (OrderAmway order : orderAmwayList) {
            //校验订单状态是否允许重推
            BusinessAssert.assertIsTrue(OrderStatusEnum.SEND_SUCCESS == order.getStatus(), String.format("订单[%s]当前状态不允许重推!", order.getCode()));
            //直接重新发送MQ
            QueueConstants.QueueEnum queueEnum = QueueConstants.QueueEnum.getQueueEnumByName("ocp.amway.order.push." + order.getSendSystem().getQueue());
            messageSender.send(queueEnum, BasicMessage.newBuilder().businessId(order.getId()).businessCode(order.getCode()).build());
        }
        return BaseResponse.responseSuccess("重推成功");
    }

    @Transactional
    @Override
    public void saveOrder(OCPAmwayOrderRequestPojo requestPojo) {
        //保存多个订单信息
        List<Order> orderList = requestPojo.getMessageDetail().getOrder();
        if (CollectionUtils.isNotEmpty(orderList)) {
            for (Order order : orderList) {
                // 保存订单头部信息
                order.setMessageID(requestPojo.getMessageHeader().getMessageID());
                order.setMessageType(requestPojo.getMessageHeader().getMessageType());
                order.setSender(requestPojo.getMessageHeader().getSender());
                order.setReceiver(requestPojo.getMessageHeader().getReceiver());
                order.setSendTime(requestPojo.getMessageHeader().getSendTime());
                order.setSign(requestPojo.getMessageHeader().getSign());

                // 校验通过存入临时表,发送订单处理MQ
                OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(order));
                BasicMessage basicMessage = new BasicMessage(orderTemp.getId(),
                        order.getOrderHeader().getOrderNumber(), "",
                        "");
                orderTempService
                        .insertAndSendMq(orderTemp,
                                QueueConstants.QueueEnum.OCP_AMWAY_ORDER_PROCESS,
                                basicMessage);

            }

        }

    }

    @Override
    @Transactional
    public void processOrder(OrderTemp orderTemp) throws Exception {

        Order order = JacksonUtils.readValue(orderTemp.getJson(), Order.class);
        BusinessAssert.assertNotNull(order, "订单解析失败");
        // 因为安利接单没有传平台编码 所以直接根据默认平台值查询接单配置
        CatchOrderConfig catchOrderConfig = catchOrderConfigService
                .selectByPlatformCode(AMWAY_DEFAULT_PLATFORM);
        BusinessAssert.assertNotNull(catchOrderConfig, "该平台接单配置未配置！");
        CatchDefaultConfig catchDefaultConfig = JacksonUtils
                .readValue(catchOrderConfig.getDefaultArguments(), CatchDefaultConfig.class);
        BusinessAssert.assertNotNull(catchDefaultConfig, "该平台接单默认配置为空！");

        //删除临时表的记录
        orderTempService.deleteById(orderTemp.getId());

        OrderAmway orderAmway = saveToOrderAmway(order, catchDefaultConfig, catchOrderConfig);
        //插入主表数据
        orderAmwayService.insert(orderAmway);
        Long orderId = orderAmway.getId();
        //产品明细信息
        List<OrderLine> orderLineList = order.getOrderHeader().getOrderLine();
        List<OrderAmwayItem> itemList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(orderLineList)) {
            for (OrderLine orderline : orderLineList) {
                OrderAmwayItem item = saveToItem(orderline, orderId);
                itemList.add(item);
            }
            //插入商品子表数据
            amwayItemService.insertList(itemList);
        }
        List<PaymentLine> paymentLineList = order.getPaymentHeader().getPaymentLine();
        List<OrderAmwayPaymentLine> amwayPaymentLineList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(paymentLineList)) {
            for (PaymentLine paymentLine : paymentLineList) {
                OrderAmwayPaymentLine orderPaymentLine = saveToPaymentLine(paymentLine, orderId);
                amwayPaymentLineList.add(orderPaymentLine);
            }
            //插入订单支付数据
            amwayPaymentLineService.insertList(amwayPaymentLineList);
        }


        // 解析转单配置，下发对应的系统
        TransferConfig transferConfig = transferConfigService
                .findByUnique(orderAmway.getEbcCode(), orderAmway.getEbpCode(),
                        orderAmway.getDeliveryCode(), orderAmway.getCustoms(),
                        BusiModeEnum.getDescEnum(orderAmway.getImportMode()).getValue());
        BusinessAssert.assertNotNull(transferConfig, "订单未找到对应转发配置!");

        //更新转发系统
        orderAmwayService
                .updateForwardSystem(orderAmway.getId(), transferConfig.getForwardSystem());
        QueueEnum queueEnum = QueueEnum.getQueueEnumByName(
                "ocp.amway.order.push." + transferConfig.getForwardSystem().getQueue());
        messageSender.send(queueEnum, BasicMessage.newBuilder().businessId(orderAmway.getId()).businessCode(orderAmway.getCode()).build());

    }

    /**
     * 插入订单主表信息
     */
    private OrderAmway saveToOrderAmway(Order order, CatchDefaultConfig catchDefaultConfig, CatchOrderConfig catchOrderConfig)
            throws Exception {
        //订单基本信息
        OrderAmway orderAmway = new OrderAmway();
        orderAmway.setMessageId(order.getMessageID());
        orderAmway.setMessageType(order.getMessageType());
        orderAmway.setSenderId(order.getSender());
        orderAmway.setReceiverId(order.getReceiver());
        orderAmway.setSendTime(DateUtils.formatStringToDate(order.getSendTime(), DateUtils.YMDHMS));
        orderAmway.setVersion(1);
        orderAmway.setSign(order.getSign());
        OrderHeader orderHeader = order.getOrderHeader();
        orderAmway.setWarehouseId(orderHeader.getWarehouseId());
        orderAmway.setBusinessType(orderHeader.getBusinessType());
        orderAmway.setVersionId(orderHeader.getVersionId());
        orderAmway.setGroupOrderNumber(orderHeader.getGroupOrderNumber());
        orderAmway.setIsGroupOrderFlag(orderHeader.getIsGroupOrderFlag());
        orderAmway.setOrderNumber(orderHeader.getOrderNumber());
        orderAmway.setOrderType(orderHeader.getOrderType());
        orderAmway.setDistributorNumber(orderHeader.getDistributorNumber());
        orderAmway.setDistributorName(orderHeader.getDistributorName());
        orderAmway.setDistributorSpouseName(orderHeader.getDistributorSpouseName());
        orderAmway.setServiceName(orderHeader.getServiceName());
        orderAmway.setOrderDstMobile(orderHeader.getOrderDstMobile());
        orderAmway.setOrderDstLandNo(orderHeader.getOrderDstLandNo());
        orderAmway.setShipToDst(orderHeader.getShipToDst());
        orderAmway.setConsigneeName(orderHeader.getConsigneeName());
        orderAmway.setShipToAddress1(orderHeader.getShipToAddress1());
        orderAmway.setShipToAddress2(orderHeader.getShipToAddress2());
        orderAmway.setShipToAddress3(orderHeader.getShipToAddress3());
        orderAmway.setShipToAddress4(orderHeader.getShipToAddress4());
        orderAmway.setShipToAddress5(orderHeader.getShipToAddress5());
        orderAmway.setPostCode(orderHeader.getPostCode());
        orderAmway.setConsigneeMobile(orderHeader.getConsigneeMobile());
        orderAmway.setConsigneeLandNo(orderHeader.getConsigneeLandNo());
        orderAmway.setSaleDate(
                DateUtils.formatStringToDate(orderHeader.getSaleDate(), DateUtils.YMDHMS));
        orderAmway.setExpectedDeliveryPeriod(orderHeader.getExpectedDeliveryPeriod());
        orderAmway.setDeliveryFee(new BigDecimal(orderHeader.getDeliveryFee()));
        orderAmway.setDeliveryInsuranceFee(new BigDecimal(orderHeader.getDeliveryInsuranceFee()));
        orderAmway.setOrderStatus(orderHeader.getOrderStatus());
        orderAmway.setOrderBv(orderHeader.getOrderBv());
        orderAmway.setOrderPv(orderHeader.getOrderPv());
        orderAmway.setOrderDiscount(new BigDecimal(orderHeader.getOrderDiscount()));
        orderAmway.setCouponBv(orderHeader.getCouponBv());
        orderAmway.setVoucherBv(orderHeader.getVoucherBv());
        orderAmway.setPromotionBv(orderHeader.getPromotionBv());
        orderAmway.setCouponPv(orderHeader.getCouponPv());
        orderAmway.setVoucherPv(orderAmway.getVoucherPv());
        orderAmway.setPromotionPv(orderAmway.getPromotionPv());
        orderAmway.setGiftPointGenerated(orderHeader.getGiftPointGenerated());
        orderAmway.setGiftPointUsed(orderHeader.getGiftPointUsed());
        orderAmway.setPosCode(orderHeader.getPosCode());
        orderAmway.setStaffNumber(orderHeader.getStaffNumber());
        orderAmway.setOrderOriginalPrice(
                new BigDecimal(order.getOrderHeader().getOrderOriginalPrice()));
        orderAmway.setRsvst1(orderHeader.getRsvst1());
        orderAmway.setRsvst2(orderHeader.getRsvst2());
        orderAmway.setRsvst3(orderHeader.getRsvst3());
        orderAmway.setRsvst4(orderHeader.getRsvst4());
        orderAmway.setRsvst5(orderHeader.getRsvst5());
        orderAmway.setRsvdc1(orderHeader.getRsvdc1());
        orderAmway.setRsvdc2(orderHeader.getRsvdc2());
        orderAmway.setRsvdc3(orderHeader.getRsvdc3());
        orderAmway.setRsvdc4(orderHeader.getRsvdc4());
        orderAmway.setRsvdc5(orderHeader.getRsvdc5());
        orderAmway.setSystemFlag(orderHeader.getSystemFlag());
        orderAmway.setShipToAddress31(order.getOrderHeader().getShipToAddress3_1());
        orderAmway.setShipToAddressType(orderHeader.getShipToAddressType());
        orderAmway.setShipToCombinedId(orderHeader.getShipToCombinedId());
        //订单跨境信息
        CrossBorder crossBorder = order.getCrossBorder();
        orderAmway.setImportMode(crossBorder.getImportMode());
        orderAmway.setCustoms(crossBorder.getCustoms());
        orderAmway.setCountry(crossBorder.getCountry());
        String key = configService.getAmwayAppSecret();
        String customerId = decryp(key, crossBorder.getCustomerID());
        //身份证需要解密再保存
        orderAmway.setCustomerId(customerId);
        orderAmway.setShipAddress(crossBorder.getShipAddress());
        orderAmway.setShipName(crossBorder.getShipName());
        orderAmway.setShipTel(crossBorder.getShipTel());
        orderAmway.setTaxFee(new BigDecimal(crossBorder.getTaxFee()));
        orderAmway.setTransportMethod(crossBorder.getTransportMethod());
        // 配送商
        orderAmway.setDeliveryCode(catchDefaultConfig.getLogisticsCode());
        orderAmway.setEbcCode(catchOrderConfig.getMerchantCode());
        orderAmway.setEbpCode(
                StringUtils.equals(order.getOrderHeader().getSystemFlag(), "Y")
                        ? catchOrderConfig.getPlatformCode()
                        : catchDefaultConfig.getPlatformCode());
        //订单支付信息
        PaymentHeader paymentHeader = order.getPaymentHeader();
        orderAmway.setPaymentHeaderAmount(new BigDecimal(paymentHeader.getPaymentHeaderAmount()));
        orderAmway.setPaymentDateTime(
                DateUtils.formatStringToDate(paymentHeader.getPaymentDateTime(), DateUtils.YMDHMS));
        // 设置订单状态
        orderAmway.setStatus(OrderStatusEnum.INIT);
        return orderAmway;
    }

    /**
     * 插入支付子表信息
     */
    private OrderAmwayPaymentLine saveToPaymentLine(PaymentLine paymentLine, Long orderId) {
        OrderAmwayPaymentLine orderPaymentLine = new OrderAmwayPaymentLine();
        orderPaymentLine.setPaymentLineAmount(new BigDecimal(paymentLine.getPaymentLineAmount()));
        orderPaymentLine.setPaymentMethod(paymentLine.getPaymentMethod());
        orderPaymentLine.setPaymentMethodName(paymentLine.getPaymentMethodName());
        orderPaymentLine.setOrderId(orderId);
        orderPaymentLine.setCreateBy(Authentication.getUserId());
        orderPaymentLine.setCreateTime(DateUtils.getNowTime());
        orderPaymentLine.setTenantId(Authentication.getUser().getTenantId());
        return orderPaymentLine;
    }

    /**
     * 插入商品子表信息
     */
    private OrderAmwayItem saveToItem(OrderLine orderline, Long orderId) {
        OrderAmwayItem item = new OrderAmwayItem();
        item.setItemSku(orderline.getItemSku());
        item.setUnitDp(StringUtils.isBlank(orderline.getUnitDp()) ? null
                : new BigDecimal(orderline.getUnitDp()));
        item.setOrderingItemName(orderline.getOrderingItemName());
        item.setOrderingItemNumber(orderline.getOrderingItemNumber());
        item.setIsReturn(orderline.getIsReturn());
        item.setOrderQty(StringUtils.isBlank(orderline.getOrderQty()) ? null
                : new BigDecimal(orderline.getOrderQty()));
        item.setRsvst1(orderline.getRsvst1());
        item.setRsvst2(orderline.getRsvst2());
        item.setRsvst3(orderline.getRsvst3());
        item.setRsvdc1(orderline.getRsvdc1());
        item.setRsvdc2(orderline.getRsvdc2());
        item.setRsvdc3(orderline.getRsvdc3());
        item.setMasterOrderingItemNumber(orderline.getMasterOrderingItemNumber());
        item.setMasterOrderQty(StringUtils.isBlank(orderline.getMasterOrderQty()) ? null
                : new BigDecimal(orderline.getMasterOrderQty()));
        item.setMasterOrderingItemName(orderline.getMasterOrderingItemName());
        item.setMasterOrderingPrice(StringUtils.isBlank(orderline.getMasterOrderingPrice()) ? null
                : new BigDecimal(orderline.getMasterOrderingPrice()));
        item.setMixLogisticsCode(orderline.getMixLogisticsCode());
        item.setMixLogisticsName(orderline.getMixLogisticsName());
        item.setOrderId(orderId);
        item.setCreateBy(Authentication.getUserId());
        item.setCreateTime(DateUtils.getNowTime());
        item.setTenantId(Authentication.getUser().getTenantId());
        return item;
    }


    /**
     * 解密方法
     */
    private String decryp(String securetkey, String content) throws Exception {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        //判断操作系统版本（window还是linux）
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os.indexOf("win") >= 0 || os.indexOf("Win") >= 0) {
            //Window
            keygen.init(128, new SecureRandom(securetkey.getBytes()));
        } else {
            //Linux
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(securetkey.getBytes());
            keygen.init(128, secureRandom);
        }
        SecretKey key = keygen.generateKey();
        Cipher cipher = Cipher.getInstance("AES");
        //		System.out.println("Start decryption:");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] a = hexStr2ByteArr(content);
        byte[] newPlainText = cipher.doFinal(a);
        return new String(newPlainText, "UTF8");
    }

    private byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }
}
