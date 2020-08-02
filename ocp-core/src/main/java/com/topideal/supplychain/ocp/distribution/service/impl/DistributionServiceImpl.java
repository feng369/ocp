package com.topideal.supplychain.ocp.distribution.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.YesOrNoEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.big.converter.BigConvertConstant;
import com.topideal.supplychain.ocp.config.dto.FxDefaultConfig;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.config.model.BigJdChannelConfig;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.model.FxConfig;
import com.topideal.supplychain.ocp.config.service.BigJdChannelConfigService;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.config.service.FxConfigService;
import com.topideal.supplychain.ocp.constants.PunctuateConstant;
import com.topideal.supplychain.ocp.constants.SystemConstants;
import com.topideal.supplychain.ocp.distribution.converter.FxDtoConverter;
import com.topideal.supplychain.ocp.distribution.service.DistributionService;
import com.topideal.supplychain.ocp.enums.BigBusiTypeEnum;
import com.topideal.supplychain.ocp.enums.BigChangeFlagEnum;
import com.topideal.supplychain.ocp.enums.BigOrderStatusEnum;
import com.topideal.supplychain.ocp.enums.BigOrderTypeEnum;
import com.topideal.supplychain.ocp.enums.CustomsCodeEnum;
import com.topideal.supplychain.ocp.enums.CustomsTypeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.IsGoodsRecordEnum;
import com.topideal.supplychain.ocp.enums.LogisticsEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.enums.ReceiveIdTypeEnum;
import com.topideal.supplychain.ocp.ew.dto.EwFxRequest;
import com.topideal.supplychain.ocp.ew.service.EwApiService;
import com.topideal.supplychain.ocp.fx.dto.FxGoodsDto;
import com.topideal.supplychain.ocp.fx.dto.FxReceiveDto;
import com.topideal.supplychain.ocp.fx.dto.FxRecipientDto;
import com.topideal.supplychain.ocp.kjb.dto.KjbFxRequest;
import com.topideal.supplychain.ocp.kjb.service.KjbApiService;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.mq.QueueConstants.QueueEnum;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
import com.topideal.supplychain.ocp.order.model.OrderDistribution;
import com.topideal.supplychain.ocp.order.model.OrderDistributionItem;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderDistributionItemService;
import com.topideal.supplychain.ocp.order.service.OrderDistributionService;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.util.AES256Util;
import com.topideal.supplychain.util.JacksonUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 分销订单 业务逻辑
 *
 * @author xuxiaoyan
 * @date 2020-05-22 10:31
 */
@Service
public class DistributionServiceImpl implements DistributionService {

    private static final Integer LENGTH_LIMIT = 255;

    @Autowired
    private OrderTempService orderTempService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private PlatformService platformService;
    @Autowired
    private BigJdChannelConfigService bigJdChannelConfigService;
    @Autowired
    private OrderDistributionService orderDistributionService;
    @Autowired
    private FxConfigService fxConfigService;
    @Autowired
    private OrderDistributionItemService orderDistributionItemService;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private KjbApiService kjbApiService;
    @Autowired
    private OfcApiService ofcApiService;
    @Autowired
    private EwApiService ewApiService;

    /**
     * 校验报文必填信息 校验通过存入临时表，发送订单处理mq队列
     */
    @Override
    @Transactional
    public void receiveOrder(FxReceiveDto requestDto, String requestJson) {
        // 必填校验
        validateOrderRequest(requestDto);
        // 校验通过存入临时表,发送订单处理MQ
        OrderTemp orderTemp = new OrderTemp(requestJson);
        BasicMessage basicMessage = new BasicMessage(orderTemp.getId(), requestDto.getOrderId(), "",
                "");
        orderTempService
                .insertAndSendMq(orderTemp, QueueConstants.QueueEnum.OCP_FX_ORDER_PROCESS,
                        basicMessage);
    }

    /**
     * 分销订单处理： 1.解析临时表Json原始报文 2.根据平台解析接单配置 3.接单配置解析成功，存入分销订单表 4.推送跨境宝分销订单接口
     */
    @Override
    @Transactional
    public void processOrder(OrderTemp orderTemp) {
        FxReceiveDto distributionDto = JacksonUtils
                .readValue(orderTemp.getJson(), FxReceiveDto.class);
        BusinessAssert.assertNotNull(distributionDto, "订单解析失败");
        Platform platform = platformService.findByCode(distributionDto.getCbepcomCode());
        BusinessAssert.assertNotNull(platform, "该平台接单配置未配置！");
        List<Platform> platformList = new ArrayList<>();
        platformList.add(platform);
        List<CatchOrderConfig> catchOrderConfigs = catchOrderConfigService
                .selectByPlatform(platformList);
        BusinessAssert.assertNotEmpty(catchOrderConfigs, "该平台接单配置未配置！");
        // 解析分销配置，下发对应的系统
        FxConfig fxConfig = fxConfigService
                .findByUnique(distributionDto.getElectricCode(), distributionDto.getCbepcomCode(),
                        distributionDto.getTpl(), distributionDto.getCustomsCode(),
                        String.valueOf(distributionDto.getBusiMode()));
        BusinessAssert.assertNotNull(fxConfig, "订单未找到对应分销配置!");


        //删除临时表的记录
        orderTempService.deleteById(orderTemp.getId());
        OrderDistribution orderDistribution = new OrderDistribution();
        saveOrderData(distributionDto, orderDistribution,fxConfig.getForwardSystem());

        /*//更新转发系统
        orderYouzanService
                .updateForwardSystem(youZanOrder.getId(), transferConfig.getForwardSystem());*/
        QueueEnum queueEnum = QueueEnum.getQueueEnumByName(
                "ocp.fx.order.to." + fxConfig.getForwardSystem().getQueue());
        messageSender.send(queueEnum, BasicMessage.newBuilder().businessId(orderDistribution.getId()).businessCode(orderDistribution.getCode()).build());

        // 分销订单推送跨境宝
        /*messageSender.send(
                QueueEnum.OCP_FX_ORDER_TO_KJB,
                new BasicMessage(orderDistribution.getId(), orderDistribution.getCode()));*/
    }

    /**
     * 跨进宝分销订单下发接口
     */
    @Override
    @Transactional
    public BaseResponse sendOrderKjb(OrderDistribution orderDistribution) {
        // 订单明细
        List<OrderDistributionItem> itemList = orderDistributionItemService
                .selectByOrder(orderDistribution.getId());

        KjbFxRequest requestDto = FxDtoConverter.convertKjb(orderDistribution, itemList);
        BaseResponse response = kjbApiService
                .sendFxOrder(requestDto, orderDistribution.getOrderId(), orderDistribution.getCode());

        OrderStatusEnum orderStatus =
                response.isSuccess() ? OrderStatusEnum.SEND_SUCCESS : OrderStatusEnum.SEND_FAILURE;
        // 更新订单下发状态
        OrderDistribution updateOrder = new OrderDistribution();
        updateOrder.setId(orderDistribution.getId());
        updateOrder.setPushStatus(orderStatus);
        //updateOrder.setPushSystem(SourceEnum.KJB.getCode());
        updateOrder.setPushNotes(response.getMessage());
        orderDistributionService.update(updateOrder);

        //如果没有成功，则直接返回
        if (!response.isSuccess()) {
            return response;
        }

        return response;
    }

    /**
     * 分销订单重推跨境宝
     * @param ids
     * @return
     */
    @Override
    public String rePush(List<Long> ids) {
        StringBuilder msg = new StringBuilder();
        List<OrderDistribution> orderDistributionList = orderDistributionService.selectByIds(ids);
        orderDistributionList.forEach(orderDistribution -> {
            //下发成功的订单才允许重新推送
            if (!OrderStatusEnum.SEND_SUCCESS.equals(orderDistribution.getPushStatus())) {
                msg.append(orderDistribution.getOrderId()).append(PunctuateConstant.COMMA);
                return;
            }
            // 重新下发跨境宝
            /*messageSender.send(
                    QueueEnum.OCP_FX_ORDER_TO_KJB,
                    new BasicMessage(orderDistribution.getId(), orderDistribution.getCode()));*/
            QueueEnum queueEnum = QueueEnum.getQueueEnumByName(
                    "ocp.fx.order.to." + orderDistribution.getPushSystem().getQueue());
            messageSender.send(queueEnum, BasicMessage.newBuilder().businessId(orderDistribution.getId()).businessCode(orderDistribution.getCode()).build());


        });

        return StringUtils.isEmpty(msg) ? msg.toString() : msg.append("订单当前状态不允许重推！").toString();
    }




    private void saveOrderData(FxReceiveDto request, OrderDistribution distribution,
            ForwardSystemEnum forwardSystemEnum) {
        BeanUtils.copyProperties(request, distribution);
        distribution.setEbpCode(request.getCbepcomCode());
        // 电商企业
        distribution.setEbcCode(request.getElectricCode());
        distribution.setFreight(
                request.getFreightFcy() == null ? BigDecimal.ZERO : request.getFreightFcy());
        distribution.setTax(request.getTaxFcy() == null ? BigDecimal.ZERO : request.getTaxFcy());
        distribution.setDiscount(
                request.getDiscount() == null ? BigDecimal.ZERO : request.getDiscount());
        distribution.setInsuredFee(
                request.getInsuredFee() == null ? BigDecimal.ZERO : request.getInsuredFee());
        distribution.setOtherRate(
                request.getOtherRate() == null ? BigDecimal.ZERO : request.getOtherRate());
        distribution.setOtherPayment(
                request.getOtherPayment() == null ? BigDecimal.ZERO : request.getOtherPayment());
        distribution.setFcyCurrency(StringUtils.isNotBlank(request.getfCode()) ? request.getfCode()
                : SystemConstants.CURRENCY);
        distribution.setFreightCurrency(
                StringUtils.isNotBlank(request.getFreightFcode()) ? request.getFreightFcode()
                        : SystemConstants.CURRENCY);
        distribution.setTaxCurrency(
                StringUtils.isNotBlank(request.getTaxFcode()) ? request.getTaxFcode()
                        : SystemConstants.CURRENCY);
        distribution.setOrdExcStatus(
                StringUtils.isNotBlank(request.getOrdExcStatus()) ? request.getOrdExcStatus()
                        : BigConvertConstant.EXCSTATUS);
        distribution.setOtherPaymentCurr(
                StringUtils.isNotBlank(request.getOtherPaymentCurr()) ? request
                        .getOtherPaymentCurr() : SystemConstants.CURRENCY);
        distribution.setOpType(StringUtils.isNotBlank(request.getOpType()) ? request.getOpType()
                : BigConvertConstant.VERSION);
        distribution.setPayType(StringUtils.isNotBlank(request.getPayType()) ? request.getPayType()
                : BigConvertConstant.PAYTYPE);
        distribution.setPayStatus(
                StringUtils.isNotBlank(request.getPayStatus()) ? request.getPayStatus()
                        : BigConvertConstant.PAYSTATUS);
        distribution.setPayCurr(StringUtils.isNotBlank(request.getPayCurr()) ? request.getPayCurr()
                : SystemConstants.CURRENCY);
        distribution.setOrderDate(request.getOrderDateFmt());
        distribution.setInputDate(request.getInputDateFmt());
        distribution.setPayDate(request.getPayDateFmt());
        distribution.setShipDate(request.getShipDateFmt());
        //枚举转换
        distribution.setOrderType(BigOrderTypeEnum.getValueEnum(request.getOrderType()));
        distribution.setOrderStatus(BigOrderStatusEnum.getValueEnum(request.getOrderStatus()));
        distribution.setBuyerIdType(
                ReceiveIdTypeEnum.getValueEnum(request.getBuyerIdType().toString()));
        distribution.setCustomsType(CustomsTypeEnum.getValueEnum(request.getCustomsType()));
        distribution.setCustomsCode(CustomsCodeEnum.getValueEnum(request.getCustomsCode()));
        distribution.setCiqbCode(request.getCiqbCode());
        distribution.setBusiMode(request.getBusiMode());
        distribution.setFromEplat(request.getFromEplat() == null ? null : YesOrNoEnum
                .getItem(request.getFromEplat().toString()));
        distribution.setIsStoreStrategy(request.getIsStoreStrategy() == null ? null
                : YesOrNoEnum.getItem(request.getIsStoreStrategy().toString()));
        distribution.setOwnerFlag(request.getOwnerFlag() == null ? null
                : YesOrNoEnum.getItem(request.getOwnerFlag().toString()));
        distribution.setVmiFlag(request.getVmiFlag() == null ? null
                : YesOrNoEnum.getItem(request.getVmiFlag().toString()));
        //京东多渠道的单处理
        /*BigJdChannelConfig channelConfig = bigJdChannelConfigService
                .getBigJdChannelConfigModel(request.getCbepcomCode(), request.getElectricCode(),
                        request.getTpl());
        if (channelConfig != null) {
            //京东多渠道的单处理字段:三方平台编码+店铺编码
            distribution.setPlatformId(channelConfig.getPlatformCode());
            distribution.setShopCode(channelConfig.getShopCode());
            distribution.setOrderType(BigOrderTypeEnum.DQD);//默认5-京东多渠道
        }*/
        //加密手机和身份证
        distribution.setBuyerIdNumber(
                StringUtils.isNotBlank(request.getBuyerIdNumber()) ? AES256Util
                        .encrypt(request.getBuyerIdNumber()) : null);
        distribution.setBuyerTelephone(
                StringUtils.isNotBlank(request.getBuyerTelephone()) ? AES256Util
                        .encrypt(request.getBuyerTelephone()) : null);
        distribution.setShipperPhone(StringUtils.isNotBlank(request.getShipperPhone()) ? AES256Util
                .encrypt(request.getShipperPhone()) : null);
        //收货信息
        FxRecipientDto recipient = request.getRecipient();
        distribution.setReceiverName(recipient.getName());
        distribution.setReceiverIdType(
                ReceiveIdTypeEnum.getValueEnum(recipient.getReceiveType().toString()));
        //未收到receiveNo，将buyerIdNumber赋值给receiveNo
        distribution.setReceiverIdNumber(
                StringUtils.isNotBlank(recipient.getReceiveNo()) ? AES256Util
                        .encrypt(recipient.getReceiveNo())
                        : AES256Util.encrypt(request.getBuyerIdNumber()));
        distribution.setReceiverMobile(
                StringUtils.isNotBlank(recipient.getMobilePhone()) ? AES256Util
                        .encrypt(recipient.getMobilePhone()) : null);
        distribution.setReceiverPhone(recipient.getPhone());
        distribution.setCountry(recipient.getCountry());
        distribution.setProvince(recipient.getProvince());
        distribution.setCity(recipient.getCity());
        distribution.setDistrict(recipient.getDistrict());
        distribution.setAddress(recipient.getAddress());
        distribution.setPostCode(recipient.getPostCode());
        distribution.setTotalFavourable(recipient.getTotalFavourable());
        distribution.setSender(recipient.getSender());
        distribution.setReceiver(recipient.getReceiver());
        distribution.setCongratulations(recipient.getCongratulations());
        distribution.setTransportDay(recipient.getTransportDay());
        distribution.setRecipientProvincesCode(recipient.getRecipientProvincesCode());
        distribution.setPod(recipient.getPod());
        distribution.setPayNotes(request.getPayNots());

        // 商品详情
        List<FxGoodsDto> goodList = request.getGoodList();
        List<OrderDistributionItem> itemList = new ArrayList<>();
        BigDecimal grossWeightTotal = BigDecimal.ZERO;
        BigDecimal netWeightTotal = BigDecimal.ZERO;
        for (FxGoodsDto good : goodList) {
            OrderDistributionItem item = new OrderDistributionItem();
            BeanUtils.copyProperties(good, item);
            item.setIsGoodsRecord(YesOrNoEnum.getItem(good.getIsGoodsRecord()));
            item.setProductionTime(good.getProductionTimeFmt());
            item.setExpDate(good.getExpDateFmt());
            item.setgQty1(StringUtils.isNotBlank(good.getgQty1()) ? new BigDecimal(good.getgQty1())
                    : null);
            item.setgQty2(StringUtils.isNotBlank(good.getgQty2()) ? new BigDecimal(good.getgQty2())
                    : null);
            item.setDecTotal(good.getDecTotal() == null ? BigDecimal.ZERO : good.getDecTotal());
            if (null != good.getGrossWeight()) {
                grossWeightTotal = good.getGrossWeight().add(grossWeightTotal);
            }
            if (null != good.getNetWeight()) {
                netWeightTotal = good.getNetWeight().add(netWeightTotal);
            }
            itemList.add(item);
        }
        distribution.setNetWeight(netWeightTotal);
        distribution.setGrossWeight(grossWeightTotal);
        distribution.setPushSystem(forwardSystemEnum);
        //保存主表
        orderDistributionService.insert(distribution);
        for (OrderDistributionItem item : itemList) {
            item.setHeadId(distribution.getId());
            item.setOrderId(distribution.getOrderId());
        }
        //保存商品明细
        orderDistributionItemService.insertItems(itemList);
    }


    /**
     * 分销订单接口必填校验
     */
    private void validateOrderRequest(FxReceiveDto request) {
        BusinessAssert.assertNotEmpty(request.getGoodList(), "货品信息【goodList】不能为空！");
        BusinessAssert.assertNotNull(request.getCustomsType(), "海关类型【customsType】不能为空！");
        BusinessAssert.assertIsFalse(!CollectionUtils
                        .containsInstance(CustomsTypeEnum.getEnumValues(), request.getCustomsType()),
                "海关类型【" + request.getCustomsType() + "】不在码表中：" + CustomsTypeEnum.getEnumInfo());
        //总署版的非空校验
        if (CustomsTypeEnum.V_ZS.getValue().equals(request.getCustomsType())) {
            BusinessAssert.assertNotEmpty(request.getBuyerName(),
                    "海关类型【customsType】为 1-总署版，订购人姓名【buyerName】不能为空！");
            BusinessAssert.assertNotEmpty(request.getBuyerIdNumber(),
                    "海关类型【customsType】为 1-总署版，订购人证件号码【buyerIdNumber】不能为空！");
            BusinessAssert.assertNotEmpty(request.getBuyerTelephone(),
                    "海关类型【customsType】为 1-总署版，订购人电话【buyerTelephone】不能为空！");
        }
        //进出口公共校验
        BusinessAssert.assertNotEmpty(request.getOrderId(), "企业订单编号【orderId】不能为空！");
        BusinessAssert.assertNotEmpty(request.getOrderDate(), "订单生成时间【orderDate】不能为空！");
        BusinessAssert.assertNotEmpty(request.getTpl(), "第三方物流商编码【tpl】不能为空！");
        BusinessAssert.assertNotEmpty(request.getElectricCode(), "电商企业编码【electricCode】不能为空！");
        BusinessAssert.assertNotEmpty(request.getOrderStatus(), "订单状态【orderStatus】不能为空！");
        BusinessAssert.assertNotNull(request.getBusiMode(), "进口模式【busiMode】不能为空！");
        //进出口分开校验
        if (BigBusiTypeEnum.EXP.getValue().equals(request.getBusiMode()) || BigBusiTypeEnum.BBC_OUT.getValue().equals(request.getBusiMode())) {//出口验证
            checkExpOrderData(request);
        } else {
            //进口验证
            checkImportOrderData(request);
        }

        // 企业+平台+业务模式+订单号做幂等
        OrderDistribution orderDistribution = orderDistributionService.selectByConditon(request.getElectricCode(), request.getCbepcomCode(),
                String.valueOf(request.getBusiMode()), request.getOrderId());
        BusinessAssert.assertIsNull(orderDistribution, "已存在相同企业+平台+业务模式+订单号的订单！");
    }

    /**
     * 出口数据校验
     */
    private void checkExpOrderData(FxReceiveDto request) {
        BusinessAssert.assertNotEmpty(request.getCbepcomCode(), "电商平台编码【cbepcomCode】不能为空！");
        BusinessAssert.assertNotNull(request.getFreightFcy(), "运费【freightFcy】不能为空！");
        BusinessAssert.assertNotEmpty(request.getFreightFcode(), "运费币制【freightFcode】不能为空！");
        BusinessAssert.assertNotNull(request.getInsuredFee(), "保费【insuredFee】不能为空！");
        BusinessAssert.assertNotEmpty(request.getInsurCurr(), "保费币制【insurCurr】不能为空！");
        BusinessAssert.assertNotEmpty(request.getCustomsCode(), "申报关区【customsCode】不能为空！");
        BusinessAssert.assertNotEmpty(request.getfCode(), "货款币制【fCode】不能为空！");
        BusinessAssert.assertNotEmpty(request.getChangeFlag(), "换单标志【changeFlag】不能为空！");
        BusinessAssert.assertNotEmpty(request.getPayCurr(), "支付币制【payCurr】不能为空！");
        BusinessAssert.assertNotNull(request.getActuralPaid(), "实际支付金额【acturalPaid】不能为空！");
        BusinessAssert.assertNotEmpty(request.getStatisticsFlag(), "申报业务类型【statisticsFlag】不能为空！");
        BusinessAssert.assertNotNull(request.getPackNo(), "件数【packNo】不能为空！");
        BusinessAssert.assertNotEmpty(request.getOrderBatchNo(), "发货订单批次【orderBatchNo】不能为空！");
        BusinessAssert.assertNotNull(request.getActuralPaid(), "实际支付金额【acturalPaid】不能为空！");
        if (request.getChangeFlag().equals("0")) {
            BusinessAssert.assertNotEmpty(request.getLogisticsNo(), "国际运单号【logisticsNo】不能为空！");
        }
        //收货信息
        FxRecipientDto recipient = request.getRecipient();
        if (BigChangeFlagEnum.YES.getValue().equals(request.getChangeFlag())) {//换单标志=1
            BusinessAssert.assertNotNull(recipient, "换单标志=1，收货信息【recipient】不能为空！");
            BusinessAssert.assertNotEmpty(recipient.getName(), "换单标志=1，收货信息-收货人姓名【name】不能为空！");
            BusinessAssert.assertNotEmpty(recipient.getMobilePhone(),
                    "换单标志=1，收货信息-手机号码【mobilePhone】不能为空！");
            BusinessAssert.assertNotEmpty(recipient.getAddress(), "换单标志=1，收货信息-地址【address】不能为空！");
        }
        //货品信息
        List<FxGoodsDto> goodList = request.getGoodList();
        for (FxGoodsDto good : goodList) {
            BusinessAssert.assertNotNull(good.getGnum(), "货品信息-序号【gnum】不能为空！");
            BusinessAssert.assertNotNull(good.getAmount(), "货品信息-数量【amount】不能为空！");
            BusinessAssert.assertNotNull(good.getPrice(), "货品信息-售价（实际售价)【price】不能为空！");
            BusinessAssert.assertNotNull(good.getDecTotal(), "货品信息-商品总价【decTotal】不能为空！");
            BusinessAssert
                    .assertNotEmpty(good.getIsGoodsRecord(), "货品信息-是否指定备案信息【isGoodsRecord】不能为空！");
            BusinessAssert.assertNotEmpty(good.getBarCode(), "货品信息-条形码【barCode】不能为空！");
            BusinessAssert.assertNotEmpty(good.getCopGName(), "货品信息-商品名称【copGName】不能为空！");
            BusinessAssert.assertNotEmpty(good.getQtyUnit(), "货品信息-法定计量单位【qtyUnit】不能为空！");
            if (IsGoodsRecordEnum.YES.getValue().equals(good.getIsGoodsRecord())) {//商品备案
                BusinessAssert.assertNotEmpty(good.getHsCode(), "货品信息-海关编码【hsCode】不能为空！");
                BusinessAssert.assertNotEmpty(good.getSpec(), "货品信息-商品规格型号【spec】不能为空！");
                BusinessAssert.assertNotEmpty(good.getUnit1(), "货品信息-第一法定单位【unit1】不能为空！");
                BusinessAssert.assertIsFalse(
                        (null == good.getGrossWeight() || good.getGrossWeight().compareTo(
                                BigDecimal.ZERO) == 0), "货品信息-毛重（Kg）【grossWeight】不能为空或者为0！");
                BusinessAssert.assertIsFalse((null == good.getNetWeight()
                                || good.getNetWeight().compareTo(BigDecimal.ZERO) == 0),
                        "货品信息-净重（Kg）【netWeight】不能为空或者为0！");
                BusinessAssert.assertIsFalse(
                        (null == good.getQty1() || good.getQty1().compareTo(BigDecimal.ZERO) == 0),
                        "货品信息-第一法定单位数量【qty1】不能为空或者为0！");
                BusinessAssert.assertIsFalse((StringUtils.isBlank(good.getgQty1())
                                || new BigDecimal(good.getgQty1()).compareTo(BigDecimal.ZERO) == 0),
                        "货品信息-商品第一数量【gQty1】不能为空或者为0！");
                //第二法定单位不为空则第二法定数量不为空
                if (StringUtils.isNotBlank(good.getUnit2())) {
                    BusinessAssert.assertIsFalse((null == good.getQty2()
                                    || good.getQty2().compareTo(BigDecimal.ZERO) == 0),
                            "货品信息-第二法定单位数量【qty2】不能为空或者为0！");
                    BusinessAssert.assertIsFalse((StringUtils.isBlank(good.getgQty2())
                                    || new BigDecimal(good.getgQty2()).compareTo(BigDecimal.ZERO) == 0),
                            "货品信息-商品第二数量【gQty2】不能为空或者为0！");
                }
            }
        }
    }

    /**
     * 进口数据校验
     */
    private void checkImportOrderData(FxReceiveDto request) {
        FxRecipientDto recipient = request.getRecipient();
        List<FxGoodsDto> goodList = request.getGoodList();
        //收货信息
        BusinessAssert.assertNotNull(recipient, "收货信息【recipient】不能为空！");
        BusinessAssert.assertNotEmpty(recipient.getName(), "收货信息-收货人姓名【name】不能为空！");
        BusinessAssert.assertNotNull(recipient.getReceiveType(), "收货信息-证件类型【receiveType】不能为空！");
        BusinessAssert.assertNotEmpty(recipient.getMobilePhone(), "收货信息-手机号码【mobilePhone】不能为空！");
        BusinessAssert.assertNotEmpty(recipient.getCountry(), "收货信息-国家【country】不能为空！");
        BusinessAssert.assertNotEmpty(recipient.getProvince(), "收货信息-省份【province】不能为空！");
        BusinessAssert.assertNotEmpty(recipient.getCity(), "收货信息-市【city】不能为空！");
        BusinessAssert.assertNotEmpty(recipient.getAddress(), "收货信息-地址【address】不能为空！");
        //货品信息
        for (FxGoodsDto good : goodList) {
            BusinessAssert.assertNotNull(good.getGnum(), "货品信息-序号【gnum】不能为空！");
            BusinessAssert.assertNotNull(good.getAmount(), "货品信息-数量【amount】不能为空！");
            BusinessAssert.assertNotNull(good.getPrice(), "货品信息-售价（实际售价)【price】不能为空！");
        }
        //业务模式分开校验
        //BBC
        if (BigBusiTypeEnum.BBC.getValue().equals(request.getBusiMode())) {
            BusinessAssert.assertNotNull(request.getOrderType(), "是否自运营订单【orderType】不能为空！");
            BusinessAssert.assertNotEmpty(request.getCbepcomCode(), "电商平台编码【cbepcomCode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getCiqbCode(), "申报国检【ciqbCode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getCustomsCode(), "申报关区【customsCode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getFreightFcode(), "运费币制【freightFcode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getBuyerName(), "订购人姓名【buyerName】不能为空！");
            BusinessAssert
                    .assertNotEmpty(request.getBuyerIdNumber(), "订购人证件号码【buyerIdNumber】不能为空！");
            BusinessAssert
                    .assertNotEmpty(request.getBuyerTelephone(), "订购人电话【buyerTelephone】不能为空！");
            //货品信息
            StringBuilder warns = new StringBuilder();
            for (FxGoodsDto good : goodList) {
                BusinessAssert.assertIsFalse((checkZhStringLength(good.getSpec(), LENGTH_LIMIT)),
                        "商品规格型号【spec】长度超出限制255！");
                if (StringUtils.isEmpty(good.getGoodId())) {
                    warns.append(good.getGnum()).append(":").append(good.getNotes())
                            .append("货号为空|");
                    continue;
                }
                if (good.getGoodId().length() > 32) {
                    warns.append(good.getGnum()).append(":").append(good.getNotes())
                            .append("货号超长|");
                }
            }
            BusinessAssert.assertEmpty(warns.toString(), warns.toString());
        }

        //BC
        if (BigBusiTypeEnum.BC.getValue().equals(request.getBusiMode())) {
            BusinessAssert.assertNotNull(request.getOrderType(), "是否自运营订单【orderType】不能为空！");
            BusinessAssert.assertNotEmpty(request.getCbepcomCode(), "电商平台编码【cbepcomCode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getCiqbCode(), "申报国检【ciqbCode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getCustomsCode(), "申报关区【customsCode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getFreightFcode(), "运费币制【freightFcode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getInsurCurr(), "保费币制【insurCurr】不能为空！");
            BusinessAssert.assertNotEmpty(request.getBuyerName(), "订购人姓名【buyerName】不能为空！");
            BusinessAssert
                    .assertNotEmpty(request.getBuyerIdNumber(), "订购人证件号码【buyerIdNumber】不能为空！");
            BusinessAssert
                    .assertNotEmpty(request.getBuyerTelephone(), "订购人电话【buyerTelephone】不能为空！");
            BusinessAssert.assertNotEmpty(request.getShippernCode(), "发货人所在国【shippernCode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getShipDate(), "发货日期【shipDate】不能为空！");
            BusinessAssert.assertNotEmpty(request.getInputDate(), "录入日期【inputDate】不能为空！");
            BusinessAssert.assertNotEmpty(request.getTrans(), "运输方式【trans】不能为空！");
            BusinessAssert.assertNotEmpty(request.getChangeFlag(), "换单标志【changeFlag】不能为空！");
            BusinessAssert.assertNotEmpty(request.getTradeMode(), "贸易模式【tradeMode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getShipperName(), "发货人名称【shipperName】不能为空！");
            BusinessAssert
                    .assertNotEmpty(request.getShipperAddress(), "发货人地址【shipperAddress】不能为空！");
            BusinessAssert.assertNotEmpty(request.getShipperPhone(), "发货人电话【shipperPhone】不能为空！");
            BusinessAssert.assertNotNull(request.getActuralPaid(), "实际支付金额【acturalPaid】不能为空！");
            if (request.getChangeFlag().equals("0")) {
                BusinessAssert.assertNotEmpty(request.getDeliveryCode(), "运单号【deliveryCode】不能为空！");
            }
            //货品信息
            for (FxGoodsDto good : goodList) {
                BusinessAssert.assertNotEmpty(good.getIsGoodsRecord(),
                        "货品信息-是否指定备案信息【isGoodsRecord】不能为空！");
                if (IsGoodsRecordEnum.NO.getValue().equals(good.getIsGoodsRecord())) {
                    BusinessAssert.assertNotEmpty(good.getGoodId(), "商品货号【goodId】不能为空！");
                } else if (IsGoodsRecordEnum.YES.getValue().equals(good.getIsGoodsRecord())) {
                    BusinessAssert.assertNotEmpty(good.getCopGName(), "货品信息-商品名称【copGName】不能为空！");
                    BusinessAssert.assertNotEmpty(good.getHsCode(), "货品信息-海关编码【hsCode】不能为空！");
                    BusinessAssert.assertNotEmpty(good.getSpec(), "货品信息-商品规格型号【spec】不能为空！");
                    BusinessAssert.assertNotEmpty(good.getUnit1(), "货品信息-第一法定单位【unit1】不能为空！");
                    BusinessAssert
                            .assertNotEmpty(good.getCiqGoodsNo(), "货品信息-国检商品备案号【ciqGoodsNo】不能为空！");
                    BusinessAssert.assertNotEmpty(good.getBrand(), "货品信息-品牌【brand】不能为空！");
                    BusinessAssert.assertNotEmpty(good.getCurrency(), "货品信息-币种【currency】不能为空！");
                    BusinessAssert.assertNotEmpty(good.getQtyUnit(), "货品信息-法定计量单位【qtyUnit】不能为空！");
                    BusinessAssert.assertNotEmpty(good.getCiqAssemCountry(),
                            "货品信息-国检原产地【ciqAssemCountry】不能为空！");
                    BusinessAssert.assertIsFalse((null == good.getDecTotal()
                                    || good.getDecTotal().compareTo(BigDecimal.ZERO) == 0),
                            "货品信息-商品总价【decTotal】不能为空或者为0！");
                    BusinessAssert.assertIsFalse((null == good.getGrossWeight()
                                    || good.getGrossWeight().compareTo(BigDecimal.ZERO) == 0),
                            "货品信息-毛重（Kg）【grossWeight】不能为空或者为0！");
                    BusinessAssert.assertIsFalse((null == good.getNetWeight()
                                    || good.getNetWeight().compareTo(BigDecimal.ZERO) == 0),
                            "货品信息-净重（Kg）【netWeight】不能为空或者为0！");
                    BusinessAssert.assertIsFalse((null == good.getQty1()
                                    || good.getQty1().compareTo(BigDecimal.ZERO) == 0),
                            "货品信息-第一法定单位数量【qty1】不能为空或者为0！");
                    BusinessAssert.assertIsFalse((StringUtils.isBlank(good.getgQty1())
                                    || new BigDecimal(good.getgQty1()).compareTo(BigDecimal.ZERO) == 0),
                            "货品信息-商品第一数量【gQty1】不能为空或者为0！");
                    //第二法定单位不为空则第二法定数量不为空
                    if (StringUtils.isNotBlank(good.getUnit2())) {
                        BusinessAssert.assertIsFalse((null == good.getQty2()
                                        || good.getQty2().compareTo(BigDecimal.ZERO) == 0),
                                "货品信息-第二法定单位数量【qty2】不能为空或者为0！");
                        BusinessAssert.assertIsFalse((StringUtils.isBlank(good.getgQty2())
                                        || new BigDecimal(good.getgQty2()).compareTo(BigDecimal.ZERO) == 0),
                                "货品信息-商品第二数量【gQty2】不能为空或者为0！");
                    }
                }
            }
        }

        if (BigBusiTypeEnum.CC.getValue().equals(request.getBusiMode())) {//CC
            BusinessAssert.assertNotEmpty(request.getDeliveryCode(), "运单号【deliveryCode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getTradeMode(), "贸易模式【tradeMode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getShipperName(), "发货人名称【shipperName】不能为空！");
            BusinessAssert.assertNotEmpty(request.getCutMode(), "征免性质分类【cutMode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getTransMode(), "成交方式【transMode】不能为空！");
            BusinessAssert.assertNotEmpty(request.getWrapType(), "包装种类【wrapType】不能为空！");
            BusinessAssert.assertNotEmpty(request.getGoodsInfo(), "主要货物名称【goodsInfo】不能为空！");
            for (FxGoodsDto good : goodList) {
                BusinessAssert.assertNotEmpty(good.getGoodId(), "商品货号【goodId】不能为空！");
                BusinessAssert.assertNotEmpty(good.getCopGName(), "货品信息-商品名称【copGName】不能为空！");
                BusinessAssert.assertNotEmpty(good.getHsCode(), "货品信息-海关编码【hsCode】不能为空！");
                BusinessAssert.assertNotEmpty(good.getCodeTs(), "货品信息-行邮税号【codeTs】不能为空！");
                BusinessAssert
                        .assertNotEmpty(good.getAssemCountry(), "货品信息-原厂国【assemCountry】不能为空！");
                BusinessAssert.assertNotEmpty(good.getQtyUnit(), "货品信息-法定计量单位【qtyUnit】不能为空！");
                BusinessAssert.assertNotEmpty(good.getSpec(), "货品信息-商品规格型号【spec】不能为空！");
                BusinessAssert.assertNotEmpty(good.getBrand(), "货品信息-品牌【brand】不能为空！");
                BusinessAssert.assertNotEmpty(good.getUnit1(), "货品信息-第一法定单位【unit1】不能为空！");
                BusinessAssert.assertNotEmpty(good.getUnit2(), "货品信息-第一法定单位【unit2】不能为空！");
                BusinessAssert.assertNotEmpty(good.getPackageType(), "货品信息-包装类型【packageType】不能为空！");
            }
        }
    }

    /**
     * 校验中文长度
     *
     * @param str 字符串
     * @param limit 限制长度
     * @return 是否超长
     */
    private boolean checkZhStringLength(String str, int limit) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        int length = str.getBytes().length;
        return length > (limit * 3);
    }


    /***发送订单到ofc*****/
    @Override
    @Transactional
    public BaseResponse sendOrderOfc(OrderDistribution orderDistribution) {

        //查询默认下发OCP配置,获取默认值配置.如果默认值配置不为空则将报文中的值替换成配置
        FxConfig fxConfig = fxConfigService
                .findByUnique(orderDistribution.getEbcCode(), orderDistribution.getEbpCode(),
                        orderDistribution.getTpl(), orderDistribution.getCustomsCode().getValue(),
                        String.valueOf(orderDistribution.getBusiMode()));
        if (fxConfig == null) {
            orderDistributionService.updateOrderStatus(orderDistribution.getId(), OrderStatusEnum.SEND_FAILURE.getValue(),"订单推送OFC未找到对应转发配置");
            return BaseResponse.responseFailure("订单推送OFC未找到对应转发配置");
        }
        //有赞订单商品明细表
        List<OrderDistributionItem> orderItems = orderDistributionItemService.selectByOrder(orderDistribution.getId());

        //获取下发的配置
        FxDefaultConfig defaultConfig = JacksonUtils.readValue(fxConfig.getConfiguration(), FxDefaultConfig.class);
        //转换为订单请求
        OfcRequest req = FxDtoConverter.convertOfc(orderDistribution,orderItems, defaultConfig);

        BaseResponse response = ofcApiService.sendOrder(req, orderDistribution.getOrderId(), orderDistribution.getCode());

        OrderStatusEnum orderStatus = OrderStatusEnum.SEND_FAILURE;
        if (response.isSuccess()) {
            orderStatus = OrderStatusEnum.SEND_SUCCESS;
        }
        orderDistributionService.updateOrderStatus(orderDistribution.getId(), orderStatus.getValue(),response.getMessage());
        return response;
    }

    /**
     * 跨进宝分销订单下发接口
     */
    @Override
    @Transactional
    public BaseResponse sendOrderEw(OrderDistribution orderDistribution) {
        // 订单明细
        List<OrderDistributionItem> itemList = orderDistributionItemService
                .selectByOrder(orderDistribution.getId());

        EwFxRequest requestDto = FxDtoConverter.convertEw(orderDistribution, itemList);
        BaseResponse response = ewApiService
                .sendFxOrder(requestDto, orderDistribution.getOrderId(), orderDistribution.getCode());

        OrderStatusEnum orderStatus =
                response.isSuccess() ? OrderStatusEnum.SEND_SUCCESS : OrderStatusEnum.SEND_FAILURE;
        // 更新订单下发状态
        OrderDistribution updateOrder = new OrderDistribution();
        updateOrder.setId(orderDistribution.getId());
        updateOrder.setPushStatus(orderStatus);
        //updateOrder.setPushSystem(SourceEnum.KJB.getCode());
        updateOrder.setPushNotes(response.getMessage());
        orderDistributionService.update(updateOrder);

        //如果没有成功，则直接返回
        if (!response.isSuccess()) {
            return response;
        }

        return response;
    }

}
