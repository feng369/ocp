package com.topideal.supplychain.ocp.gs.service.impl;

import com.google.common.collect.Lists;
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
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.GsFreezeStatusEnum;
import com.topideal.supplychain.ocp.enums.GsIsSendKjbEnum;
import com.topideal.supplychain.ocp.enums.KjbSendStausEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.gs.converter.GsDtoConverter;
import com.topideal.supplychain.ocp.gs.dto.Certification;
import com.topideal.supplychain.ocp.gs.dto.GsGrabReqDto;
import com.topideal.supplychain.ocp.gs.dto.LogisticsInfo;
import com.topideal.supplychain.ocp.gs.dto.OrderGsReqDto;
import com.topideal.supplychain.ocp.gs.dto.OrderSku;
import com.topideal.supplychain.ocp.gs.dto.ShipInfo;
import com.topideal.supplychain.ocp.gs.service.GsApiService;
import com.topideal.supplychain.ocp.gs.service.GsService;
import com.topideal.supplychain.ocp.kjb.dto.KjbReqGoods;
import com.topideal.supplychain.ocp.kjb.dto.KjbRequest;
import com.topideal.supplychain.ocp.kjb.dto.KjbResponse;
import com.topideal.supplychain.ocp.kjb.service.KjbApiService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.op.service.OpApiService;
import com.topideal.supplychain.ocp.order.model.OrderGs;
import com.topideal.supplychain.ocp.order.model.OrderGsItem;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderGsItemService;
import com.topideal.supplychain.ocp.order.service.OrderGsService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.gs.service.impl</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2019-12-30 18:05</p>
 *
 * @version 1.0
 */
@Service
public class GsServiceImpl implements GsService {

    @Autowired
    private GsApiService gsApiService;
    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private OrderGsService orderGsService;
    @Autowired
    private OrderGsItemService orderGsItemService;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private KjbApiService kjbApiService;
    @Autowired
    private TransferConfigService transferConfigService;
    @Autowired
    private OfcApiService ofcApiService;
    @Autowired
    private OpApiService opApiService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;

    @Override
    @Transactional
    public void getOrder(CatchOrderConfig catchOrderConfig) {

        List<OrderGsReqDto> list = null;
        int pageNo = 1;
        GsGrabReqDto.GsGrabArgs gsGrabArgs = new GsGrabReqDto.GsGrabArgs();

        gsGrabArgs.setEndTime(DateUtils.getNowTime());
        gsGrabArgs.setStartTime(DateUtils.addDay(gsGrabArgs.getEndTime(),-3));
        gsGrabArgs.setUpdateTimeEnd(gsGrabArgs.getEndTime());
        gsGrabArgs.setUpdateTimeStart(gsGrabArgs.getStartTime());
        // 定时任务抓单--status=100，手工抓单--status=100
        gsGrabArgs.setStatus("100");
        gsGrabArgs.setBondedArea(true);
        gsGrabArgs.setPageSize(catchOrderConfig.getPageSize());

        do {
            gsGrabArgs.setPage(pageNo);

            list = gsApiService.grabOrders(gsGrabArgs,catchOrderConfig);
            for (OrderGsReqDto orderGsDto : list) {
                OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(orderGsDto));
                // 插入临时表数据，并发送处理的mq(订单临时表ID)
                orderTempService
                        .insertAndSendMq(orderTemp, QueueConstants.QueueEnum.OCP_GS_ORDER_PROCESS,
                                new BasicMessage(orderTemp.getId(), orderGsDto.getConsignCode(),
                                        String.valueOf(catchOrderConfig.getId()), ""));
            }
            pageNo ++;
        } while (!CollectionUtils.isEmpty(list) && list.size() >= catchOrderConfig.getPageSize());
    }


    /**
     * 手工抓单
     */
    @Override
    @Transactional
    public BaseResponse getOrder(Long id, String consignCode) {
        // 根据抓单id找到抓单定义
        CatchOrderConfig catchOrderConfig = catchOrderConfigService.selectEnableById(id);
        BusinessAssert.assertNotNull(catchOrderConfig, "未找到抓单配置");
        GsGrabReqDto.GsGrabArgs gsGrabArgs = new GsGrabReqDto.GsGrabArgs();

        gsGrabArgs.setEndTime(DateUtils.getNowTime());
        gsGrabArgs.setStartTime(DateUtils.addMonths(gsGrabArgs.getEndTime(),-12));
        // 定时任务抓单--status=100，手工抓单--status=100
        gsGrabArgs.setStatus("100");
        gsGrabArgs.setBondedArea(true);
        gsGrabArgs.setConsignCode(consignCode);
        List<OrderGsReqDto> list = gsApiService.grabOrder(gsGrabArgs,catchOrderConfig, consignCode);
        if (CollectionUtils.isEmpty(list)) {
            return BaseResponse.responseFailure("未抓取到订单");
        }
        for (OrderGsReqDto orderGsDto : list) {
            OrderTemp orderTemp = new OrderTemp(JacksonUtils.toJSon(orderGsDto));
            // 插入临时表数据，并发送处理的mq(订单临时表ID)
            orderTempService
                    .insertAndSendMq(orderTemp, QueueConstants.QueueEnum.OCP_GS_ORDER_PROCESS,
                            new BasicMessage(orderTemp.getId(), orderGsDto.getConsignCode(),
                                    String.valueOf(catchOrderConfig.getId()), ""));
        }
        return BaseResponse.responseSuccess("订单抓取成功");
    }

    /**
     * 保存订单
     */
    @Override
    @Transactional
    public void processOrder(OrderTemp orderTemp, CatchOrderConfig catchOrderConfig) {
        process(orderTemp.getJson(), catchOrderConfig);
        // 删除临时表
        orderTempService.deleteById(orderTemp.getId());
    }

    /**
     * 推送跨境宝
     */
    @Override
    @Transactional
    public BaseResponse sendOrderKjb(OrderGs orderGs) {
        List<OrderGsItem> orderGsItemList = orderGsItemService
                .selectByOrderId(orderGs.getId(), GsIsSendKjbEnum.OLD);
        if (CollectionUtils.isEmpty(orderGsItemList)) {
            return BaseResponse.responseFailure("订单推送KJB未找到对应商品信息");
        }
        //查询默认下发OCP配置,获取默认值配置.如果默认值配置不为空则将报文中的值替换成配置，根据币值系数转换币值
        TransferConfig transferConfig = transferConfigService
                .findByUnique(orderGs.getEbcCode(), orderGs.getEbpCode(),
                        orderGs.getLogisticsCode(), orderGs.getCustomsCode(),
                        orderGs.getBusiMode().getValue());
        if (transferConfig == null) {
            throw new BusinessException("订单未找到对应转发配置");
        }
        // 组装跨境宝请求参数
        KjbRequest reqKjbDto = GsDtoConverter.convertKjb(orderGs, orderGsItemList);
        // 发送跨境宝
        BaseResponse<KjbResponse> response = kjbApiService
                .sendOrder(reqKjbDto, orderGs.getConsignCode(), orderGs.getCode());
        // 更新订单状态
        OrderStatusEnum orderStatus =
                response.isSuccess() ? OrderStatusEnum.KJB_SUCCESS : OrderStatusEnum.KJB_FAILURE;
        orderGsService.updateOrderStatus(orderGs.getId(), orderStatus.getValue(),
                response.getData().getNotes(), response.getData().getStatus().getValue());
        // 如果没有成功，则直接返回
        if (!response.isSuccess()) {
            return response;
        }
        // 更新转发系统
        orderGsService.updateForwardSystem(orderGs.getId(), transferConfig.getForwardSystem());
        // 处理跨境宝返回的明细数据
        List<OrderGsItem> newOrderItems = processKjbItem(orderGs, reqKjbDto, response.getData());
        if (!org.springframework.util.CollectionUtils.isEmpty(newOrderItems)) {
            orderGsItemService.batchInsert(newOrderItems);
        }
        QueueConstants.QueueEnum queueEnum = QueueConstants.QueueEnum.getQueueEnumByName(
                "ocp.gs.order.send." + transferConfig.getForwardSystem().getQueue());
        messageSender.send(queueEnum, new BasicMessage(orderGs.getId(), orderGs.getCode()));
        return response;
    }

    public static List<OrderGsItem> processKjbItem(OrderGs orderGs, KjbRequest reqKjbDto, KjbResponse kjbResponse) {
        List<OrderGsItem> gsOrderItemList = new ArrayList<>();
        // 状态为不处理
        if (KjbSendStausEnum.NO_DEAL == kjbResponse.getStatus()) {
            for (KjbReqGoods kjbReqGoods : reqKjbDto.getGood_list()) {
                OrderGsItem newItem = new OrderGsItem();
                newItem.setOrderId(orderGs.getId());
                newItem.setOrderCode(orderGs.getCode());
                newItem.setPrice(kjbReqGoods.getGoods_price());
                newItem.setDeliverCode(kjbReqGoods.getGoods_id());
                newItem.setSkuTitle(kjbReqGoods.getGoods_name());
                newItem.setQuantity(new BigDecimal(kjbReqGoods.getGoods_num()));
                newItem.setTotalPrice(kjbReqGoods.getGoods_price().multiply(new BigDecimal(kjbReqGoods.getGoods_num())).setScale(4));
                newItem.setSendKjbFlag(GsIsSendKjbEnum.NEW);
                gsOrderItemList.add(newItem);
            }
        } else {//处理-成功
            for (KjbResponse.KjbResponseGoods item : kjbResponse.getGood_list()) {
                OrderGsItem newItem = new OrderGsItem();
                newItem.setOrderId(orderGs.getId());
                newItem.setSkuTitle(item.getGoods_name());
                newItem.setDeliverCode(item.getGoods_id());
                newItem.setQuantity(new BigDecimal(item.getGoods_num()));
                newItem.setTotalPrice(item.getGoods_price().multiply(new BigDecimal(item.getGoods_num())).setScale(4));
                newItem.setPrice(item.getGoods_price());
                newItem.setSendKjbFlag(GsIsSendKjbEnum.NEW);
                gsOrderItemList.add(newItem);
            }
        }
        return gsOrderItemList;
    }

    @Override
    public BaseResponse sendOrderOfc(OrderGs orderGs) {
        // 查询订单子表
        List<OrderGsItem> orderGsItemList = orderGsItemService
                .selectByOrderId(orderGs.getId(), GsIsSendKjbEnum.OLD);
        if (CollectionUtils.isEmpty(orderGsItemList)) {
            return BaseResponse.responseFailure("订单推送OFC未找到对应商品信息");
        }
        // 查询默认下发OCP配置,获取默认值配置.如果默认值配置不为空则将报文中的值替换成配置，根据币值系数转换币值
        TransferConfig transferConfig = transferConfigService
                .findByUnique(orderGs.getEbcCode(), orderGs.getEbpCode(),
                        orderGs.getLogisticsCode(), orderGs.getCustomsCode(),
                        orderGs.getBusiMode().getValue());
        if (transferConfig == null) {
            orderGsService
                    .updateOrderStatus(orderGs.getId(), OrderStatusEnum.SEND_FAILURE.getValue(),
                            "订单推送OFC未找到对应转发配置");
            return BaseResponse.responseFailure("订单推送OFC未找到对应转发配置");
        }
        TransferDefaultConfig transferDefaultConfig = JacksonUtils
                .readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        // 组装发送OFC数据
        OfcRequest req = GsDtoConverter.convertOfc(orderGs, orderGsItemList, transferDefaultConfig);
        BaseResponse response = ofcApiService
                .sendOrder(req, orderGs.getConsignCode(), orderGs.getCode());
        // 更新订单状态
        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        if (response.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        orderGsService
                .updateOrderStatus(orderGs.getId(), orderStatus.getValue(), response.getMessage());
        return response;
    }

    @Override
    public BaseResponse sendOrderOp(OrderGs orderGs) {
        // 查询订单子表
        List<OrderGsItem> orderGsItemList = orderGsItemService
                .selectByOrderId(orderGs.getId(), GsIsSendKjbEnum.NEW);
        if (CollectionUtils.isEmpty(orderGsItemList)) {
            return BaseResponse.responseFailure("订单推送OP未找到对应商品信息");
        }
        // 查询默认下发OCP配置,获取默认值配置.如果默认值配置不为空则将报文中的值替换成配置，根据币值系数转换币值
        TransferConfig transferConfig = transferConfigService
                .findByUnique(orderGs.getEbcCode(), orderGs.getEbpCode(),
                        orderGs.getLogisticsCode(), orderGs.getCustomsCode(),
                        orderGs.getBusiMode().getValue());
        if (transferConfig == null) {
            orderGsService
                    .updateOrderStatus(orderGs.getId(), OrderStatusEnum.SEND_FAILURE.getValue(),
                            "订单推送OFC未找到对应转发配置");
            return BaseResponse.responseFailure("订单推送OFC未找到对应转发配置");
        }
        TransferDefaultConfig transferDefaultConfig = JacksonUtils
                .readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
        // 组装发送OP数据
        OpRequest req = GsDtoConverter.convertOp(orderGs, orderGsItemList, transferDefaultConfig);
        BaseResponse response = opApiService
                .sendOrder(req, orderGs.getConsignCode(), orderGs.getCode());
        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        if (response.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        orderGsService
                .updateOrderStatus(orderGs.getId(), orderStatus.getValue(), response.getMessage());
        return response;
    }

    @Override
    public String rePush(List<Long> ids) {
        StringBuilder msg = new StringBuilder();
        List<OrderGs> orderGsList = orderGsService.selectByIds(ids);
        orderGsList.forEach(orderGs -> {
            //下发成功的订单才允许重新推送
            if (!OrderStatusEnum.SEND_SUCCESS.equals(orderGs.getToStatus())) {
                msg.append(orderGs.getConsignCode()).append(PunctuateConstant.COMMA);
                return;
            }
            //直接重新发送MQ
            messageSender.send(ForwardSystemEnum.OFC.equals(orderGs.getToSystem())
                            ? QueueConstants.QueueEnum.OCP_GS_ORDER_TO_OFC :
                            QueueConstants.QueueEnum.OCP_GS_ORDER_TO_OP,
                    new BasicMessage(orderGs.getId(), orderGs.getCode()));
        });
        return StringUtils.isEmpty(msg) ? msg.toString() : msg.append("订单当前状态不允许重推！").toString();
    }

    private void process(String json, CatchOrderConfig catchOrderConfig) {
        OrderGsReqDto orderGsReqDto = JacksonUtils.readValue(json, OrderGsReqDto.class);
        // 复制订单数据
        BusinessAssert.assertNotNull(orderGsReqDto, "报文格式出错");
        // 过滤 freezeStatus=2 的订单
        if (orderGsReqDto.getFreezeStatus() == GsFreezeStatusEnum.FREEZE_2.getValue()) {
            return;
        }
        // 过滤 平台订单号重复且下发成功 的订单
        List<OrderGs> oldOrders = orderGsService
                .selectByConsignCode(orderGsReqDto.getConsignCode());
        if (CollectionUtils.isNotEmpty(oldOrders)) {
            return;
        }

        OrderGs orderGs = saveOrder(orderGsReqDto, catchOrderConfig);

        // 查询需要转发的系统
        TransferConfig transferConfig = transferConfigService
                .findByUnique(orderGs.getEbcCode(), orderGs.getEbpCode(),
                        orderGs.getLogisticsCode(), orderGs.getCustomsCode(),
                        orderGs.getBusiMode().getValue());
        BusinessAssert.assertNotNull(transferConfig, "订单未找到对应转发配置!");

        // 更新转发系统
        orderGsService.updateForwardSystem(orderGs.getId(), transferConfig.getForwardSystem());
        QueueConstants.QueueEnum queueEnum = QueueConstants.QueueEnum.getQueueEnumByName(
                "ocp.gs.order.send." + transferConfig.getForwardSystem().getQueue());
        messageSender.send(queueEnum, new BasicMessage(orderGs.getId(), orderGs.getCode()));


        // 2020/05/18 之后废弃推送组合拆分接口，不再推跨境宝
//        // 数据保存成功则推送至跨境宝MQ(订单ID)
//        messageSender.send(QueueConstants.QueueEnum.OCP_GS_ORDER_TO_KJB,
//                new BasicMessage(orderGs.getId(), orderGs.getCode()));
    }

    private OrderGs saveOrder(OrderGsReqDto orderGsReqDto, CatchOrderConfig catchOrderConfig) {

        OrderGs orderGs = new OrderGs();
        BigDecimal hundred = new BigDecimal(100);
        orderGs.setToStatus(OrderStatusEnum.INIT);
        orderGs.setGrabId(catchOrderConfig.getGrabId());
        CatchDefaultConfig catchDefaultConfig = JacksonUtils
                .readValue(catchOrderConfig.getDefaultArguments(), CatchDefaultConfig.class);

        //
        orderGs.setEbpCode(catchOrderConfig.getPlatformCode());
        orderGs.setEbcCode(catchOrderConfig.getMerchantCode());
        orderGs.setCustomsCode(catchDefaultConfig.getCustomsCode());
        orderGs.setCiqCode(catchDefaultConfig.getCiqCode());
        orderGs.setLogisticsCode(catchDefaultConfig.getLogisticsCode());
        orderGs.setBusiMode(BusiModeEnum.getValueEnum(catchDefaultConfig.getBusiMode()));
        orderGs.setShopCode(catchOrderConfig.getStoreCode());
        orderGs.setShopOrderId(orderGsReqDto.getShopOrderId());
        orderGs.setConsignCode(orderGsReqDto.getConsignCode());
        orderGs.setSendKjbStatus("0");
        orderGs.setStatus(orderGsReqDto.getStatus());
        orderGs.setFreezeStatus(orderGsReqDto.getFreezeStatus());
        orderGs.setPayTime(orderGsReqDto.getPayTime());
        orderGs.setOrderCreateTime(orderGsReqDto.getOrderCreateTime());
        orderGs.setGsCreateTime(orderGsReqDto.getCreateTime());
        orderGs.setPayChannel(orderGsReqDto.getPayChannel());
        orderGs.setTradeNo(orderGsReqDto.getTradeNo());
        orderGs.setBuyerComment(orderGsReqDto.getBuyerComment());
        orderGs.setSellerComment(orderGsReqDto.getSellerComment());

        // 抓单单位为分，转为元
        orderGs.setBondedAreaGoodsPrice(orderGsReqDto.getBondedAreaGoodsPrice()
                .divide(hundred, 5, BigDecimal.ROUND_HALF_UP));
        orderGs.setBondedAreaNonCashDeduct(orderGsReqDto.getBondedAreaNonCashDeduct()
                .divide(hundred, 5, BigDecimal.ROUND_HALF_UP));
        orderGs.setBondedAreaShipExpense(orderGsReqDto.getBondedAreaShipExpense()
                .divide(hundred, 5, BigDecimal.ROUND_HALF_UP));
        orderGs.setBondedAreaTax(
                orderGsReqDto.getBondedAreaTax().divide(hundred, 5, BigDecimal.ROUND_HALF_UP));
        orderGs.setBondedAreaPayCash(
                orderGsReqDto.getBondedAreaPayCash().divide(hundred, 5, BigDecimal.ROUND_HALF_UP));
        // 收货地址信息
        if (orderGsReqDto.getShipAddress() != null) {
            ShipInfo shipAddress = orderGsReqDto.getShipAddress();
            orderGs.setShipToName(shipAddress.getShipToName());
            orderGs.setShipToMobile(shipAddress.getShipToMobile());
            orderGs.setShipToZip(shipAddress.getShipToZip());
            orderGs.setShipToProvince(shipAddress.getShipToProvince());
            orderGs.setShipToCity(shipAddress.getShipToCity());
            orderGs.setShipToDistrict(shipAddress.getShipToDistrict());
            orderGs.setShipToTown(shipAddress.getShipToTown());
            orderGs.setShipToAddress(shipAddress.getShipToAddress());
            // 收货人信息
            if (orderGsReqDto.getShipAddress().getCertification() != null) {
                Certification certification = orderGsReqDto.getShipAddress().getCertification();
                orderGs.setIdCardNo(certification.getIdCardNO());
                orderGs.setIdCardName(certification.getIdCardName());
                orderGs.setPositiveUrl(certification.getPositiveUrl());
                orderGs.setOtherSideUrl(certification.getOtherSideUrl());
            }
        }
        // 物流信息
        if (CollectionUtils.isNotEmpty(orderGsReqDto.getPackageList())) {
            LogisticsInfo logisticsInfo = orderGsReqDto.getPackageList().get(0);
            orderGs.setLogisticsChannel(logisticsInfo.getLogisticsChannel());
            orderGs.setLogisticsNumber(logisticsInfo.getLogisticsNumber());
            orderGs.setLogisticsStatus(logisticsInfo.getStatus());
            orderGs.setLogisticsStatusDesc(logisticsInfo.getStatusDesc());
        }
        // 保存到数据库
        orderGsService.insert(orderGs);
        // 内部订单号
        String code = orderGs.getCode();
        Long id = orderGs.getId();
        List<OrderSku> orderSkuList = orderGsReqDto.getOrderSkuList();
        List<OrderGsItem> itemList = Lists.newArrayList();
        for (OrderSku orderSku : orderSkuList) {
            OrderGsItem orderGsItem = new OrderGsItem();
            orderGsItem.setOrderId(orderGs.getId());
            orderGsItem.setOrderCode(orderGs.getConsignCode());
            orderGsItem.setDeliverCode(orderSku.getDeliverCode());
            orderGsItem.setPrice(orderSku.getPrice().divide(hundred));
            orderGsItem.setQuantity(orderSku.getQuantity());
            orderGsItem.setSkuTitle(orderSku.getSkuTitle());
            orderGsItem.setBondedAreaGoodsPrice(orderSku.getBondedAreaGoodsPrice().divide(hundred));
            orderGsItem.setAcrossPromotionAmount(orderSku.getAcrossPromotionAmount().divide(hundred));
            orderGsItem.setActualIncomePrice(orderSku.getActualIncomePrice().divide(hundred));
            orderGsItem.setBondedAreaNonCashDeduct(orderSku.getBondedAreaNonCashDeduct().divide(hundred));
            orderGsItem.setBondedAreaTax(orderSku.getBondedAreaTax().divide(hundred));
            orderGsItem.setPayPrice(orderSku.getPayPrice().divide(hundred));
            orderGsItem.setPlatformPromotionAmount(orderSku.getPlatformPromotionAmount().divide(hundred));
            orderGsItem.setPurchaseCount(orderSku.getPurchaseCount());
            orderGsItem.setRealCashAmount(orderSku.getRealCashAmount().divide(hundred));
            orderGsItem.setShipExpenseShare(orderSku.getShipExpenseShare().divide(hundred));
            orderGsItem.setShopPromotionAmount(orderSku.getShopPromotionAmount().divide(hundred));
            orderGsItem.setSkuProperty(orderSku.getSkuProperty());

            orderGsItem.setOrderId(id);
            orderGsItem.setOrderCode(code);
            orderGsItem.setSendKjbFlag(GsIsSendKjbEnum.OLD);

            orderGsItem.setCreateBy(Authentication.getUserId());
            orderGsItem.setCreateTime(new Date());
            orderGsItem.setTenantId(orderGs.getTenantId());
            itemList.add(orderGsItem);
        }
        orderGsItemService.batchInsert(itemList);
        return orderGs;
    }
}
