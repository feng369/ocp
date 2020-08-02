package com.topideal.supplychain.ocp.dxy.service.impl;

import com.google.common.collect.Lists;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.config.dto.CatchDefaultConfig;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.config.service.TransferConfigService;
import com.topideal.supplychain.ocp.constants.PunctuateConstant;
import com.topideal.supplychain.ocp.constants.SystemConstants;
import com.topideal.supplychain.ocp.dxy.converter.DxyDtoConverter;
import com.topideal.supplychain.ocp.dxy.dto.DxyOrderItemResponse;
import com.topideal.supplychain.ocp.dxy.dto.DxyOrderResponse;
import com.topideal.supplychain.ocp.dxy.dto.DxyRequest;
import com.topideal.supplychain.ocp.dxy.service.DxyApiService;
import com.topideal.supplychain.ocp.dxy.service.DxyService;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.LogisticsEnum;
import com.topideal.supplychain.ocp.enums.MerchantEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.jd.converter.JdDtoConverter;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.mq.QueueConstants.QueueEnum;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
import com.topideal.supplychain.ocp.order.model.OrderDistribution;
import com.topideal.supplychain.ocp.order.model.OrderDxy;
import com.topideal.supplychain.ocp.order.model.OrderDxyItem;
import com.topideal.supplychain.ocp.order.model.OrderJd;
import com.topideal.supplychain.ocp.order.model.OrderJdItem;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderDxyItemService;
import com.topideal.supplychain.ocp.order.service.OrderDxyService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.ocp.service.SystemConfigService;
import com.topideal.supplychain.ocp.utils.AmountUtils;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.poifs.crypt.dsig.OOXMLURIDereferencer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @ClassName DxyServiceImpl
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/6/30 15:28
 * @Version 1.0
 **/
@Service
public class DxyServiceImpl implements DxyService {

    @Autowired
    private OrderTempService orderTempService;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private TransferConfigService transferConfigService;

    @Autowired
    private DxyApiService dxyApiService;

    @Autowired
    private OfcApiService ofcApiService;

    @Autowired
    private CatchOrderConfigService catchOrderConfigService;

    @Autowired
    private OrderDxyService orderDxyService;
    @Autowired
    private OrderDxyItemService orderDxyItemService;
    @Autowired
    private SystemConfigService systemConfigService;
    /**
     * 定时拉取订单
     */
    @Override
    @Transactional
    public BaseResponse getOrder(CatchOrderConfig catchOrderConfig) {
        BaseResponse<List<DxyOrderResponse>> baseResponse = new BaseResponse<>(
                BaseResponse.SUCCESS);
        List<DxyOrderResponse> list = null;
        DxyRequest.Builder builder = DxyRequest.newBuilder()
                .pageSize(catchOrderConfig.getPageSize());
        //调用抓单接口
        boolean flag = getQueryTime(builder, catchOrderConfig);
        if (!flag) {
            return baseResponse;
        }
        DxyRequest dxyRequest = builder.build();
        do {
            baseResponse = dxyApiService.getOrder(dxyRequest, catchOrderConfig);
            list = baseResponse.getData();
            if (CollectionUtils.isEmpty(list) || !baseResponse.isSuccess()) {
                break;
            }
            for (DxyOrderResponse order : list) {
                OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(order));
                String orderNo = catchOrderConfig.getCode();
                if (order != null) {
                    orderNo = order.getOrderId();
                }
                //插入临时表数据，并发送处理的mq
                orderTempService.insertAndSendMq(orderTemp, QueueEnum.OCP_DXY_PROCESS_ORDER,
                        BasicMessage.newBuilder().businessId(orderTemp.getId())
                                .businessCode(orderNo).udf1(catchOrderConfig.getCode()).build());
            }
            dxyRequest.setPageNo(dxyRequest.getPageNo() + 1);
        } while (!CollectionUtils.isEmpty(list) && list.size() >= dxyRequest.getPageSize());

        if (!baseResponse.isSuccess()) {
            return baseResponse;
        }
        updateQuery(catchOrderConfig);
        //判断是否要进行下个时间段的查询
        if (isNeedNext(catchOrderConfig)) {
            messageSender.send(QueueConstants.QueueEnum.OCP_DXY_GET_ORDER,
                    BasicMessage.newBuilder().businessId(catchOrderConfig.getId())
                            .businessCode(catchOrderConfig.getCode()).build());
        }
        return baseResponse;
    }


    //获取查询时间并判断是否要进行抓取行为
    private boolean getQueryTime(DxyRequest.Builder dxyRequest, CatchOrderConfig catchOrderConfig) {
        Date beginDate = catchOrderConfig.getLastQueryTime();
        //如果第一次时间为空，则取当前时间往前（间隔时间+延迟1分钟）的时间
        if (beginDate == null) {
            beginDate = DateUtils
                    .addMinute(DateUtils.getNowTime(), catchOrderConfig.getIntervalCount() * -1);
        }
        beginDate = DateUtils.dateFormatToDate(beginDate, DateUtils.Y_M_D_HMS);
        Date endDate = DateUtils.addMinute(beginDate, catchOrderConfig.getIntervalCount());
        //如果当前时间比结束时间大,则抓取，否则不进行抓取行为
        if (DateUtils.getTwoDatesDifOfMins(DateUtils.getNowTime(), endDate) < 0) {
            return false;
        }
        catchOrderConfig.setLastQueryTime(beginDate);
        dxyRequest.startTime(DateUtils.dateToString(beginDate, DateUtils.Y_M_D_HMS))
                .endTime(DateUtils.dateToString(endDate, DateUtils.Y_M_D_HMS));
        return true;
    }

    //判断是否需要进行下一次时间段的查询
    private boolean isNeedNext(CatchOrderConfig catchOrderConfig) {

        Date endDate = DateUtils.addMinute(catchOrderConfig.getLastQueryTime(),
                catchOrderConfig.getIntervalCount() * 2);
        //如果当前时间比结束时间大于等于1分钟，则抓取，否则不进行抓取行为
        return DateUtils.getTwoDatesDifOfMins(DateUtils.getNowTime(), endDate) >= 0;
    }

    /**
     * 更新抓单配置的查询时间
     */
    private void updateQuery(CatchOrderConfig catchOrderConfig) {
        Date beginDate = catchOrderConfig.getLastQueryTime();
        Date query = DateUtils.addMinute(beginDate, catchOrderConfig.getIntervalCount());
        catchOrderConfigService.updateQueryTime(query, catchOrderConfig.getId());
    }


    @Override
    @Transactional
    public BaseResponse getMissOrder(CatchOrderConfig catchOrderConfig) {
        BaseResponse<List<DxyOrderResponse>> baseResponse = new BaseResponse<>(
                BaseResponse.SUCCESS);
        List<DxyOrderResponse> list = null;
        DxyRequest.Builder builder = DxyRequest.newBuilder()
                .pageSize(catchOrderConfig.getPageSize());
        //调用抓单接口
        int intervalTime = systemConfigService.getIntervalTimeForMiss() * -1;
        builder.endTime(DateUtils.dateToString(DateUtils.getNowTime(),DateUtils.DATETIME_PATTERN))
                .startTime(DateUtils.dateToString(DateUtils.addDays(DateUtils.getNowTime(), intervalTime),DateUtils.DATETIME_PATTERN));
        DxyRequest dxyRequest = builder.build();
        do {
            baseResponse = dxyApiService.getOrder(dxyRequest, catchOrderConfig);
            list = baseResponse.getData();
            if (CollectionUtils.isEmpty(list) || !baseResponse.isSuccess()) {
                break;
            }
            for (DxyOrderResponse order : list) {
                OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(order));
                String orderNo = catchOrderConfig.getCode();
                if (order != null) {
                    orderNo = order.getOrderId();
                }
                //插入临时表数据，并发送处理的mq
                orderTempService.insertAndSendMq(orderTemp, QueueEnum.OCP_DXY_PROCESS_ORDER,
                        BasicMessage.newBuilder().businessId(orderTemp.getId())
                                .businessCode(orderNo).udf1(catchOrderConfig.getCode()).build());
            }
            dxyRequest.setPageNo(dxyRequest.getPageNo() + 1);
        } while (!CollectionUtils.isEmpty(list) && list.size() >= dxyRequest.getPageSize());

        return baseResponse;
    }

    @Override
    @Transactional
    public void processOrder(OrderTemp orderTemp,CatchOrderConfig catchOrderConfig) {
        DxyOrderResponse dxyOrderResponse = JacksonUtils
                .readValue(orderTemp.getJson(), DxyOrderResponse.class);
        BusinessAssert.assertNotNull(dxyOrderResponse, "订单转换错误");
        saveOrder(dxyOrderResponse,catchOrderConfig);
        orderTempService.deleteById(orderTemp.getId());
    }

    /**
     * 保存订单
     */
    private void saveOrder(DxyOrderResponse dxyOrderResponse,CatchOrderConfig catchOrderConfig) {
        //校验订单是否已存在
        boolean isExist = orderDxyService.isExist(dxyOrderResponse.getOrderId());
        if (isExist) {
            return ;
        }
        CatchDefaultConfig catchDefaultConfig = JacksonUtils.readValue(catchOrderConfig.getDefaultArguments(),CatchDefaultConfig.class);
        BusinessAssert.assertNotNull(catchDefaultConfig,String.format("[%s]抓单的默认配置不存在",catchOrderConfig.getCode()));
        BusinessAssert.assertNotEmpty(catchDefaultConfig.getCustomsCode(),String.format("[%s]抓单的默认配置关区未配置",catchOrderConfig.getCode()));
        BusinessAssert.assertNotEmpty(catchDefaultConfig.getBusiMode(),String.format("[%s]抓单的默认配置业务模式未配置",catchOrderConfig.getCode()));
        //校验是否存在电商企业
        TransferConfig transferConfig = transferConfigService.findByUnique(MerchantEnum.VM.getCode(),dxyOrderResponse.getPlatformCode(),
                LogisticsEnum.VL.getCode(),catchDefaultConfig.getCustomsCode(),catchDefaultConfig.getBusiMode());
        BusinessAssert.assertNotNull(transferConfig,"转单配置不存在");

        OrderDxy orderDxy = new OrderDxy();
        orderDxy.setOrderId(dxyOrderResponse.getOrderId());
        orderDxy.setPlatformCode(dxyOrderResponse.getPlatformCode());

        orderDxy.setPlatformName(dxyOrderResponse.getPlatformName());

        orderDxy.setRealPayAmount(AmountUtils.convertY(dxyOrderResponse.getRealPayAmount()));

        orderDxy.setDiscountAmount(AmountUtils.convertY(dxyOrderResponse.getDiscountAmount()));

        orderDxy.setTaxAmount(AmountUtils.convertY(dxyOrderResponse.getTaxAmount()));

        orderDxy.setFreightAmount(AmountUtils.convertY(dxyOrderResponse.getFreightAmount()));

        orderDxy.setProvince(dxyOrderResponse.getProvince());

        orderDxy.setCity(dxyOrderResponse.getCity());

        orderDxy.setArea(dxyOrderResponse.getArea());

        orderDxy.setAddress(dxyOrderResponse.getAddress());

        orderDxy.setPostCode(dxyOrderResponse.getPostCode());

        orderDxy.setBuyerName(dxyOrderResponse.getBuyerName());

        orderDxy.setBuyerIdCard(dxyOrderResponse.getBuyerIdCard());

        orderDxy.setBuyerMobile(dxyOrderResponse.getBuyerMobile());

        orderDxy.setReceiverName(dxyOrderResponse.getReceiverName());

        orderDxy.setReceiverIdCard(dxyOrderResponse.getReceiverIdCard());

        orderDxy.setReceiverMobile(dxyOrderResponse.getReceiverMobile());

        orderDxy.setPayTime(dxyOrderResponse.getPayTime());

        orderDxy.setPaymentNo(dxyOrderResponse.getPaymentNo());

        orderDxy.setPayType(dxyOrderResponse.getPayType());

        orderDxy.setPayCompanyCode(dxyOrderResponse.getPayCompanyCode());

        orderDxy.setForwardSystem(ForwardSystemEnum.OFC.getValue());

        orderDxy.setOrderCreateTime(dxyOrderResponse.getCreateTime());
        orderDxy.setUserRemark(dxyOrderResponse.getUserRemark());
        orderDxy.setCustomsCode(catchDefaultConfig.getCustomsCode());
        orderDxy.setBusiMode(BusiModeEnum.getValueEnum(catchDefaultConfig.getBusiMode()));
        orderDxy.setForwardSystem(transferConfig.getForwardSystem().getValue());
        orderDxyService.insert(orderDxy);

        List<DxyOrderItemResponse> orderItemResponses = dxyOrderResponse.getOrderItems();
        List<OrderDxyItem> list = Lists.newArrayList();
        orderItemResponses.stream().forEach(o -> {
            OrderDxyItem orderDxyItem = new OrderDxyItem();
            orderDxyItem.setOrderId(orderDxy.getId());
            orderDxyItem.setSkuCode(o.getSkuCode());
            orderDxyItem.setCommodityName(o.getCommodityName());
            orderDxyItem.setSpecificationName(o.getSpecificationName());
            orderDxyItem.setQuantity(o.getQuantity());
            orderDxyItem.setPrice(AmountUtils.convertY(o.getPrice()));
            orderDxyItem.setCostPrice(AmountUtils.convertY(o.getCostPrice()));
            orderDxyItem.setAmount(AmountUtils.convertY(o.getAmount()));
            orderDxyItem.setDiscountAmount(AmountUtils.convertY(o.getDiscountAmount()));
            orderDxyItem.setTaxAmount(AmountUtils.convertY(o.getTaxAmount()));

            list.add(orderDxyItem);
        });
        orderDxyItemService.insertList(list);
        messageSender.send(QueueEnum.OCP_DXY_ORDER_TO_OFC, BasicMessage.newBuilder().businessId(orderDxy.getId()).businessCode(orderDxy.getCode()).build());
    }


    /***发送订单到ofc*****/
    @Override
    @Transactional
    public BaseResponse sendOrderOfc(OrderDxy orderDxy) {

        //查询默认下发OCP配置,获取默认值配置.如果默认值配置不为空则将报文中的值替换成配置
        TransferConfig transferConfig = transferConfigService
                .findByUnique(MerchantEnum.VM.getCode(), orderDxy.getPlatformCode(),
                        LogisticsEnum.VL.getCode(), orderDxy.getCustomsCode(),
                        orderDxy.getBusiMode().getValue());
        if (transferConfig == null) {
            orderDxyService.updateOrderStatus(orderDxy.getId(), OrderStatusEnum.SEND_FAILURE.getValue());
            return BaseResponse.responseFailure("订单推送OFC未找到对应转发配置");
        }
        //有赞订单商品明细表
        List<OrderDxyItem> orderItems = orderDxyItemService.selectByOrderId(orderDxy.getId());

        //获取下发的配置
        TransferDefaultConfig defaultConfig = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        //转换为订单请求
        OfcRequest req = DxyDtoConverter.convertOfc(orderDxy,orderItems, defaultConfig,transferConfig.getStoreCode(),transferConfig.getStoreName());

        BaseResponse response = ofcApiService.sendOrder(req, orderDxy.getOrderId(), orderDxy.getCode());

        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        if (response.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        orderDxyService.updateOrderStatus(orderDxy.getId(), orderStatus.getValue());
        return response;
    }

    /**
     * 订单重推
     * @param ids
     * @return
     */
    @Override
    public String rePush(List<Long> ids) {
        StringBuilder msg = new StringBuilder();
        List<OrderDxy> orderDxyList = orderDxyService.selectByIds(ids);
        orderDxyList.forEach(orderDxy -> {
            //下发成功的订单才允许重新推送
            if (!OrderStatusEnum.SEND_SUCCESS.equals(orderDxy.getStatus())) {
                msg.append(orderDxy.getOrderId()).append(PunctuateConstant.COMMA);
                return;
            }
            // 重新下发OFC
            messageSender.send(
                    QueueEnum.OCP_DXY_ORDER_TO_OFC, BasicMessage.newBuilder().businessId(orderDxy.getId()).businessCode(orderDxy.getCode()).build());

        });

        return StringUtils.isEmpty(msg) ? msg.toString() : msg.append("订单当前状态不允许重推！").toString();
    }
}
