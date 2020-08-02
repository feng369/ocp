package com.topideal.supplychain.ocp.hipac.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.config.dto.CatchDefaultConfig;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.config.service.TransferConfigService;
import com.topideal.supplychain.ocp.constants.PunctuateConstant;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.CustomsCodeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.hipac.converter.HipacDtoConverter;
import com.topideal.supplychain.ocp.hipac.dto.HipacArgsDto;
import com.topideal.supplychain.ocp.hipac.dto.HipacCustomer;
import com.topideal.supplychain.ocp.hipac.dto.HipacHead;
import com.topideal.supplychain.ocp.hipac.dto.HipacOrder;
import com.topideal.supplychain.ocp.hipac.dto.HipacOrderItem;
import com.topideal.supplychain.ocp.hipac.dto.HipacPayInfo;
import com.topideal.supplychain.ocp.hipac.dto.HipacReceiveRequest;
import com.topideal.supplychain.ocp.hipac.dto.HipacSupplier;
import com.topideal.supplychain.ocp.hipac.service.HipacService;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.mq.QueueConstants.QueueEnum;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.op.service.OpApiService;
import com.topideal.supplychain.ocp.order.model.OrderHipac;
import com.topideal.supplychain.ocp.order.model.OrderHipacItem;
import com.topideal.supplychain.ocp.order.service.OrderHipacItemService;
import com.topideal.supplychain.ocp.order.service.OrderHipacService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.ocp.service.SystemConfigService;
import com.topideal.supplychain.util.AES256Util;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.util.MD5Utils;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 海拍客
 *
 * @author xuxiaoyan
 * @date 2019-12-18 09:48
 */
@Service
public class HipacServiceImpl implements HipacService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HipacServiceImpl.class);

    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private OrderHipacService orderHipacService;
    @Autowired
    private OrderHipacItemService orderHipacItemService;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private TransferConfigService transferConfigService;
    @Autowired
    private OpApiService opApiService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private SystemConfigService systemConfigService;
    @Autowired
    private OfcApiService ofcApiService;

    /**
     * 海拍客接单：
     * 1.加密用户敏感信息
     * 2.插入订单头
     * 3.插入订单明细
     * 4.发送推送OP的MQ消息
     *
     * @param req
     * @return
     */
    @Override
    @Transactional
    public void receiveOrder(HipacReceiveRequest req, String requestStr) {
        HipacHead head = req.getHead();
        HipacOrder order = req.getBody().getHipacOrder();
        HipacCustomer customer = req.getBody().getHipacCustomer();
        HipacPayInfo payInfo = req.getBody().getHipacPayInfo();
        HipacSupplier hipacSupplier = req.getBody().getHipacSupplier();

        // 根据虚拟平台编码解析接单配置
        List<Platform> platforms = platformService
                .findByVirtualCode(PlatformEnum.HIPAC.getCode());
        if (CollectionUtils.isEmpty(platforms)) {
            throw new BusinessException("签名校验失败!");
        }
        List<CatchOrderConfig> catchOrderConfigs = catchOrderConfigService
                .selectByPlatform(platforms);
        if (CollectionUtils.isEmpty(catchOrderConfigs)) {
            throw new BusinessException("签名校验失败!");
        }
        //supplierSenderID匹配企业编码，得到的接单配置，有且仅能有一个
        catchOrderConfigs = catchOrderConfigs.stream().filter(catchOrderConfig ->
                catchOrderConfig.getMerchantCode().equals(hipacSupplier.getSupplierSenderID()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(catchOrderConfigs) || catchOrderConfigs.size() > 1) {
            throw new BusinessException("签名校验失败!");
        }
        String platformArguments = catchOrderConfigs.get(0).getPlatformArguments();
        if (StringUtils.isEmpty(platformArguments)) {
            throw new BusinessException("签名校验失败!");
        }
        HipacArgsDto hipacArgsDto = JacksonUtils.readValue(platformArguments, HipacArgsDto.class);

        // 根据系统参数判断是否需要验签
        if (systemConfigService.getHipacOrderSign()) {
            String signStr = String.format("appKey=%s&custName=%s&orderNum=%s&payNo=%s&sendID=%s&service=%s&key=%s",
                    hipacArgsDto.getAppKey(), customer.getCustName(), order.getOrderNum(),
                    payInfo.getPayNo(), head.getSendID(), head.getService(), hipacArgsDto.getSecret());
            String md5Sign = MD5Utils.md5Hex(signStr);
            if (StringUtils.isEmpty(head.getSign()) || !head.getSign().equals(md5Sign)) {
                throw new BusinessException("签名校验失败!");
            }
        }

        //将数据封装到对象
        OrderHipac orderHipac = new OrderHipac();
        List<OrderHipacItem> itemList = new ArrayList<>();
        String msg = transData(req, orderHipac, itemList, catchOrderConfigs.get(0));
        if (StringUtils.isNotEmpty(msg)) {
            throw new BusinessException(msg);
        }
        //20.20.4.2 根据转单配置，决定下发系统
        TransferConfig transferConfig = transferConfigService
                .findByUnique(orderHipac.getSupplierSenderID(), orderHipac.getCbePcomCode(),
                        orderHipac.getSupplierSenderID(), orderHipac.getCustomsCode().getValue(),
                        orderHipac.getBusiMode().getValue());
        BusinessAssert.assertNotNull(transferConfig, "签名校验失败!");
        ForwardSystemEnum forwardSystem = transferConfig.getForwardSystem();
        orderHipac.setSendSystem(forwardSystem);
        QueueEnum queueEnum = QueueConstants.QueueEnum.getQueueEnumByName("ocp.hipac.order.push." + forwardSystem.getQueue());
        //保存订单主体信息
        orderHipacService.insert(orderHipac);
        //商品设置订单号和订单id
        for (OrderHipacItem orderItem : itemList) {
            orderItem.setOrderId(orderHipac.getId());
            orderItem.setOrderNum(orderHipac.getOrderNum());
        }
        //保存商品信息
        if (CollectionUtils.isNotEmpty(itemList)) {
            orderHipacItemService.insertList(itemList);
        }
        //发送推送的MQ消息
        messageSender.send(queueEnum,
                new BasicMessage(orderHipac.getId(), orderHipac.getCode()));

    }


    /**
     * 订单重推,如果状态不对的订单跳过并记录单号，状态正确的正常下发
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public String rePush(List<Long> ids) {
        StringBuilder msg = new StringBuilder();
        List<OrderHipac> orderHipacs = orderHipacService.selectByIds(ids);
        orderHipacs.forEach(orderHipac -> {
            //下发成功的订单才允许重新推送
            if (!OrderStatusEnum.SEND_SUCCESS.equals(orderHipac.getStatus())) {
                msg.append(orderHipac.getCode()).append(PunctuateConstant.COMMA);
                return;
            }
            //直接重新发送MQ
            QueueConstants.QueueEnum queueEnum = QueueConstants.QueueEnum.getQueueEnumByName("ocp.hipac.order.push." + orderHipac.getSendSystem().getQueue());
            messageSender.send(queueEnum, new BasicMessage(orderHipac.getId(), orderHipac.getCode()));
        });
        return StringUtils.isEmpty(msg) ? msg.toString() : msg.append("订单当前状态不允许重推！").toString();
    }

    /**
     * 海拍客订单重推=》OP
     *
     * @param orderHipac
     * @return
     */
    @Override
    @Transactional
    public BaseResponse sendOrderOp(OrderHipac orderHipac) {
        // 商品明细
        List<OrderHipacItem> itemList = orderHipacItemService.selectByOrderId(orderHipac.getId());
        orderHipac.setOrderItemList(itemList);
        // 解密敏感信息
        orderHipac.setCustIdNum(AES256Util.decrypt(orderHipac.getCustIdNum()));
        orderHipac.setCustPhone(AES256Util.decrypt(orderHipac.getCustPhone()));
        // 解析转单配置
        TransferConfig transferConfig = transferConfigService
                .findByUnique(orderHipac.getSupplierSenderID(), orderHipac.getCbePcomCode(),
                        orderHipac.getSupplierSenderID(), orderHipac.getCustomsCode().getValue(),
                        orderHipac.getBusiMode().getValue());
        if (null == transferConfig) {
            orderHipacService
                    .updateOrderStatus(orderHipac.getId(), OrderStatusEnum.SEND_FAILURE.getValue(),
                            "订单推送OP未找到对应转发配置");
            return BaseResponse.responseFailure("订单推送OP未找到对应转发配置");
        }
        // 获取下发的配置
        TransferDefaultConfig defaultConfig = JacksonUtils
                .readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        //转换为订单请求
        OpRequest req = HipacDtoConverter.convertOp(orderHipac, defaultConfig);
        BaseResponse response = opApiService.sendOrder(req, orderHipac.getOrderNum(), orderHipac.getCode());
        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        if (response.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        orderHipacService
                .updateOrderStatus(orderHipac.getId(), orderStatus.getValue(), response.getMessage());
        return response;
    }

    /**
     * 订单重推=》OFC
     *
     * @param orderHipac
     * @return
     */
    @Override
    @Transactional
    public BaseResponse sendOrderOfc(OrderHipac orderHipac) {
        // 商品明细
        List<OrderHipacItem> itemList = orderHipacItemService.selectByOrderId(orderHipac.getId());
        orderHipac.setOrderItemList(itemList);
        // 解密敏感信息
        orderHipac.setCustIdNum(AES256Util.decrypt(orderHipac.getCustIdNum()));
        orderHipac.setCustPhone(AES256Util.decrypt(orderHipac.getCustPhone()));
        // 解析转单配置
        TransferConfig transferConfig = transferConfigService
                .findByUnique(orderHipac.getSupplierSenderID(), orderHipac.getCbePcomCode(),
                        orderHipac.getSupplierSenderID(), orderHipac.getCustomsCode().getValue(),
                        orderHipac.getBusiMode().getValue());
        if (null == transferConfig) {
            orderHipacService
                    .updateOrderStatus(orderHipac.getId(), OrderStatusEnum.SEND_FAILURE.getValue(),
                            "订单推送OFC未找到对应转发配置");
            return BaseResponse.responseFailure("订单推送OFC未找到对应转发配置");
        }
        // 获取下发的配置
        TransferDefaultConfig defaultConfig = JacksonUtils
                .readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        //转换为ofc订单请求
        OfcRequest req = HipacDtoConverter.convertOfc(orderHipac, defaultConfig);
        BaseResponse response = ofcApiService.sendOrder(req, orderHipac.getOrderNum(), orderHipac.getCode());
        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        if (response.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        orderHipacService
                .updateOrderStatus(orderHipac.getId(), orderStatus.getValue(), response.getMessage());
        return response;
    }

    /**
     * 封装对象
     *
     * @param req
     * @param orderHipac
     * @param itemList
     */
    private String transData(HipacReceiveRequest req, OrderHipac orderHipac,
                             List<OrderHipacItem> itemList, CatchOrderConfig catchOrderConfig) {
        //店铺信息
        HipacSupplier hipacSupplier = req.getBody().getHipacSupplier();
        //订单信息
        HipacOrder hipacOrder = req.getBody().getHipacOrder();
        //支付信息
        HipacPayInfo hipacPayInfo = req.getBody().getHipacPayInfo();
        //客户信息
        HipacCustomer hipacCustomer = req.getBody().getHipacCustomer();
        //商品信息
        List<HipacOrderItem> orderItems = req.getBody().getOrderItems();

        // 设置订单信息
        orderHipac.setStatus(OrderStatusEnum.INIT);
        // 商家信息
        BeanUtils.copyProperties(hipacSupplier, orderHipac);
        // 订单信息
        BeanUtils.copyProperties(hipacOrder, orderHipac);
        try {
            orderHipac.setOrderDate(DateUtils.parseDate(hipacOrder.getOrderDate(), DateUtils.Y_M_D_HMS));
            orderHipac.setPayTime(DateUtils.parseDate(hipacPayInfo.getPayTime(), DateUtils.Y_M_D_HMS));
        } catch (ParseException e) {
            return "订单解析异常！";
        }
        // 支付信息
        BeanUtils.copyProperties(hipacPayInfo, orderHipac);
        // 用户信息
        BeanUtils.copyProperties(hipacCustomer, orderHipac);

        //设置订单信息默认值
        setDefaultValue(catchOrderConfig, orderHipac);

        //隐私信息加密
        encryptSensitiveData(orderHipac);
        //设置商品信息
        if (CollectionUtils.isNotEmpty(orderItems)) {
            orderItems.forEach(orderItem -> {
                OrderHipacItem item = new OrderHipacItem();
                BeanUtils.copyProperties(orderItem, item);
                item.setSpecNum(orderItem.getSpecNum().intValue());
                itemList.add(item);
            });
        }

        return "";
    }

    /**
     * 给订单设置接单配置的默认值参数
     *
     * @param catchOrderConfig
     * @param orderHipac
     */
    private void setDefaultValue(CatchOrderConfig catchOrderConfig, OrderHipac orderHipac) {
        CatchDefaultConfig args = JacksonUtils.readValue(catchOrderConfig.getDefaultArguments(), CatchDefaultConfig.class);
        // 电商平台
        /*if (StringUtils.isNotEmpty(args.getPlatformCode())) {
            orderHipac.setCbePcomCode(args.getPlatformCode());
        }*/
        orderHipac.setCbePcomCode(catchOrderConfig.getPlatformCode());

        // 电商企业
        /*if (StringUtils.isNotEmpty(args.getMerchantCode())) {
            orderHipac.setSupplierSenderID(args.getMerchantCode());
        }*/
        //店铺编码
        /*if (StringUtils.isNotEmpty(catchOrderConfig.getStoreCode())) {
            orderHipac.setShopNum(catchOrderConfig.getStoreCode());
        }*/
        orderHipac.setShopNum(catchOrderConfig.getStoreCode());

        // 业务模式
        if (StringUtils.isNotEmpty(args.getBusiMode())) {
            orderHipac.setBusiMode(BusiModeEnum.getValueEnum(args.getBusiMode()));
        }
        // 检区
        if (StringUtils.isNotEmpty(args.getCiqCode())) {
            orderHipac.setCiqbCode(args.getCiqCode());
        }
        // 关区
        if (StringUtils.isNotEmpty(args.getCustomsCode())) {
            orderHipac.setCustomsCode(CustomsCodeEnum.getValueEnum(args.getCustomsCode()));
        }

    }

    /**
     * 加密身份证号 电话
     *
     * @param orderHipac
     */
    private void encryptSensitiveData(OrderHipac orderHipac) {
        orderHipac.setCustIdNum(AES256Util.encrypt(orderHipac.getCustIdNum()));//订购人证件号码
        orderHipac.setCustPhone(AES256Util.encrypt(orderHipac.getCustPhone()));//订购人电话
    }

}
