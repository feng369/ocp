package com.topideal.supplychain.ocp.big.converter;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.topideal.supplychain.enumeration.YesOrNoEnum;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.enums.BigOrderTypeEnum;
import com.topideal.supplychain.ocp.ofc.dto.*;
import com.topideal.supplychain.ocp.ofcbc.dto.*;
import com.topideal.supplychain.ocp.op.dto.OpGoodsItemDto;
import com.topideal.supplychain.ocp.op.dto.OpRecipientDto;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.order.model.OrderBig;
import com.topideal.supplychain.ocp.order.model.OrderBigItem;
import com.topideal.supplychain.util.AES256Util;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 标题：大订单报文转换器
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.big.converter
 * 作者：songping
 * 创建日期：2020/1/7 14:12
 *
 * @version 1.0
 */
public class BigDtoConverter {

    /**
     * OP报文转换
     * @return
     */
    public static OpRequest convertOP(OrderBig orderBig, TransferDefaultConfig transferConfig)
    {
        List<OpGoodsItemDto> goodList = new ArrayList<>();//OP商品明细
        List<OrderBigItem> itemList = orderBig.getOrderBigItemList();//大订单商品明细
        // 收货人信息
        OpRecipientDto recipientDto = OpRecipientDto.newBuilder()
                .name(orderBig.getReceiverName())//收货人姓名
                .receiveType(orderBig.getReceiverIdType() == null ? 1 : Integer.parseInt(orderBig.getReceiverIdType().getValue()))//证件类型
                .receiveNo(AES256Util.decrypt(orderBig.getReceiverIdNumber()))//收件人证件号
                .mobilePhone(AES256Util.decrypt(orderBig.getReceiverMobile()))//手机号码
                .phone(orderBig.getReceiverPhone())//座机
                .country(StringUtils.isBlank(orderBig.getCountry()) ? "中国" : orderBig.getCountry())//国家
                .province(orderBig.getProvince())//省份
                .city(orderBig.getCity())//市
                .district(orderBig.getDistrict())//区/县
                .address(orderBig.getAddress())//地址
                .postCode(orderBig.getPostCode())//邮编
                .totalFavourable(orderBig.getTotalFavourable())//订单优惠金额
                .sender(orderBig.getSender())//送礼人
                .receiver(orderBig.getReceiver())//收礼人
                .congratulations(orderBig.getCongratulations())//祝贺语
                .transportDay(orderBig.getTransportDay())//配送时间
                .recipientProvincesCode(orderBig.getRecipientProvincesCode())//收货人省市代码
                .build();
        // 商品信息
        for (OrderBigItem item : itemList) {
            OpGoodsItemDto good = OpGoodsItemDto.newBuilder()
                    .gnum(String.valueOf(item.getGnum()))
                    .goodId(item.getGoodId())//商品货号
                    .amount(item.getAmount())//数量
                    .price(item.getPrice())//售价（实际售价）
                    .goodPrice(item.getGoodPrice())//商品售价（展示售价）
                    .copGName(item.getCopGName())//商品名称
                    .hsCode(item.getHsCode())//海关编码
                    .codeTs(item.getCodeTs())//行邮税号
                    .decTotal(item.getDecTotal())//商品总价
                    .custGoodsNo(item.getCustGoodsNo())//商品海关备案号
                    .ciqGoodsNo(item.getCiqGoodsNo())//国检商品备案号
                    .batchNo(item.getBatchNo())//商品批次号
                    .assemCountry(item.getAssemCountry())//原厂国
                    .qtyUnit(item.getQtyUnit())//法定计量单位
                    .spec(item.getSpec())//商品规格型号
                    .storeStrategyId(item.getStoreStrategyId())//指定库存策略号
                    .productionTime(item.getProductionTime())//生产日期
                    .expDate(item.getExpDate())//到期日期
                    .ownerCode(item.getOwnerCode())//货主
                    .brand(item.getBrand())//品牌
                    .packageType(item.getPackageType())//包装
                    .qty1(item.getQty1() == null ? null : item.getQty1().toPlainString())//第一法定单位数量
                    .unit1(item.getUnit1())//第一法定单位
                    .qty2(item.getQty2() == null ? null : item.getQty2().toPlainString())//第二法定单位数量
                    .unit2(item.getUnit2())//第二法定单位
                    .ggrossWt(item.getGrossWeight())//商品毛重
                    .build();
            goodList.add(good);
        }

        return OpRequest.newBuilder()
                .orderId(orderBig.getOrderId())//企业订单编号
                .orderDate(orderBig.getOrderDate())//订单生成时间
                .packingMaterial(orderBig.getPackingMaterial())//包材编码
                .warehouseId(orderBig.getWarehouseId())//仓库ID
                .tpl(StringUtils.isNotEmpty(transferConfig.getLogisticsCode()) ? transferConfig
                        .getLogisticsCode() : orderBig.getTpl())//第三方物流商编
                .orderType(BigConvertConstant.ORDERTYPE)//是否自运营订单
                .orderStatus(BigConvertConstant.ORDERSTATUS)//订单状态
                .customsType(BigConvertConstant.CUSTOMS_Type)//海关类型
                .electricCode(StringUtils.isNotEmpty(transferConfig.getMerchantCode()) ? transferConfig
                        .getMerchantCode() : orderBig.getEbcCode())//电商企业编码
                .cbepcomCode(StringUtils.isNotEmpty(transferConfig.getPlatformCode()) ? transferConfig
                        .getPlatformCode() : orderBig.getEbpCode())//电商平台编码
                .busiMode(Integer.valueOf(orderBig.getBusiMode()))//进口模式
                .customsCode(orderBig.getCustomsCode().getValue())//申报关区
                .ciqbCode(orderBig.getCiqbCode())//申报国检
                .stationbCode(orderBig.getStationbCode())//申报场站
                .deliveryCode(orderBig.getDeliveryCode())//运单号
                .notes(orderBig.getNotes())//备注
                .freightFcy(orderBig.getFreight())//运费
                .freightFcode(orderBig.getFreightCurrency())//运费币制
                .insuredFee(orderBig.getInsuredFee())//保费
                .insurCurr(BigConvertConstant.CURRENCY)//保费币制
                .insurMark(orderBig.getInsurMark())//(预留字段)
                .taxFcy(orderBig.getTax())//税费
                .taxFcode(orderBig.getTaxCurrency())//税费币种
                .otherRate(orderBig.getOtherRate())//杂费
                .otherCurr(orderBig.getOtherCurr())//杂费币制
                .otherMark(orderBig.getOtherMark())//(预留字段)
                .otherPayment(orderBig.getOtherPayment())//抵付金额
                .otherPaymentCurr(orderBig.getOtherPaymentCurr())//抵付币制
                .fCode(orderBig.getFcyCurrency())//货款币制
                .discount(orderBig.getDiscount())//优惠减免金额
                .buyerName(orderBig.getBuyerName())//订购人姓名
                .buyerIdType(Integer.parseInt(orderBig.getBuyerIdType().getValue()))//订购人证件类型
                .buyerIdNumber(AES256Util.decrypt(orderBig.getBuyerIdNumber()))//订购人证件号码
                .buyerTelephone(AES256Util.decrypt(orderBig.getBuyerTelephone()))//订购人电话
                .buyerRegNo(orderBig.getBuyerRegNo())//订购人注册号
                .grossWeight(orderBig.getGrossWeight())//毛重（公斤）
                .netWeight(orderBig.getNetWeight())//净重（公斤）
                .bakbCode(orderBig.getBakbCode())//商品备案地国检
                .ordExcStatus(orderBig.getOrdExcStatus())//执行状态
                .forSellComp(orderBig.getForSellComp())//国外卖方
                .forSellCompName(orderBig.getForSellCompName())//国外卖方名称
                .tradeUnitCode(orderBig.getTradeUnitCode())//外贸经营单位码
                .tradeUnitName(orderBig.getTradeUnitName())//外贸经营单位名称
                .shippernCode(orderBig.getShippernCode())//发货人所在国
                .shipDate(orderBig.getShipDate())//发货日期
                .inputDate(orderBig.getInputDate())//录入日期
                .logNots(orderBig.getLogNots())//物流信息备注
                .blno(orderBig.getBlno())//提单号
                .trans(orderBig.getTrans())//运输方式
                .transNo(orderBig.getTransNo())//运输工具名称
                .changeFlag(orderBig.getChangeFlag())//换单标志
                .tradeMode(orderBig.getTradeMode())//贸易模式
                .shipperName(orderBig.getShipperName())//发货人名称
                .shipperAddress(orderBig.getShipperAddress())//发货人地址
                .shipperPhone(AES256Util.decrypt(orderBig.getShipperPhone()))//发货人电话
                .agentCode(orderBig.getAgentCode())//报关企业代码
                .agentName(orderBig.getAgentName())//报关企业名称
                .payNo(orderBig.getPayNo())//支付流水号
                .payPcomName(orderBig.getPayPcomName())//支付企业名称
                .payCopNo(orderBig.getPayCopNo())//支付企业编码
                .opType(orderBig.getOpType())//申报类型
                .payType(orderBig.getPayType())//交易支付类型
                .payStatus(orderBig.getPayStatus())//支付交易状态
                .payorName(orderBig.getPayorName())//付款人
                .activePayComp(orderBig.getActivePayComp())//实际支付企业
                .acturalPaid(orderBig.getActuralPaid())//实际支付金额
                .payCurr(orderBig.getPayCurr())//支付币制
                .payNots(orderBig.getPayNots())//支付信息备注
                .payDate(orderBig.getPayDate())//支付日期
                .fromEplat(orderBig.getFromEplat().getValue())//来源第e仓
                .printHeader(orderBig.getPrintHeader())//打印抬头
                .commonField(orderBig.getCommonField())//通用字段
                .isStoreStrategy(orderBig.getIsStoreStrategy().getValue())//是否校验库存策略
                .vmiFlag(orderBig.getVmiFlag().getValue())//是否VMI模式
                .ownerFlag(orderBig.getOwnerFlag().getValue())//是否一单多业主
                .cutMode(orderBig.getCutMode())//征免性质分类
                .transMode(orderBig.getTransMode())//成交方式
                .packNo(orderBig.getPackNo())//件数
                .wrapType(orderBig.getWrapType())//包装种类
                .sendCity(orderBig.getSendCity())//发件人城市
                .totalValue(orderBig.getTotalValue())//货值
                .goodsInfo(orderBig.getGoodsInfo())//主要货物名称
                .orderBatchNo(orderBig.getOrderBatchNo())//发货订单批次
                .reDeclare(orderBig.getReDeclare() == null ? null : String.valueOf(orderBig.getReDeclare()))//重报标识
                .ebptpl(null)//平台物流商编码
                .recipient(recipientDto)
                .goodList(goodList)
                .build();
    }

    /**
     * BBC-OFC报文转换
     * @return
     */
    public static OfcRequest convertOfc(OrderBig orderBig,TransferDefaultConfig defaultConfig)
    {
        OfcOrderReqDto ofcOrderReqDto = OfcOrderReqDto.newBuilder()
                //orderType=5-->京东多渠道
                .key(orderBig.getOrderType().equals(BigOrderTypeEnum.DQD) ? ("JDDDDDQD-" + orderBig.getPlatformId() + "-" + orderBig.getShopCode()) : null)
                .orderNo(orderBig.getOrderId())
                .declTime(orderBig.getOrderDate())
                .buyerRegNo(orderBig.getBuyerRegNo())
                .buyerIdType(orderBig.getBuyerIdType().getValue())
                .buyerIdNumber(AES256Util.decrypt(orderBig.getBuyerIdNumber()))
                .buyerName(orderBig.getBuyerName())
                .buyerTelephone(AES256Util.decrypt(orderBig.getBuyerTelephone()))
                .receiveNo(AES256Util.decrypt(orderBig.getReceiverIdNumber()))
                .consignee(orderBig.getReceiverName())
                .consigneeTelephone(AES256Util.decrypt(orderBig.getReceiverMobile()))
                .pod(orderBig.getPod())
                .consigneeAddress(orderBig.getAddress())
                .country(orderBig.getCountry())
                .province(orderBig.getProvince())
                .city(orderBig.getCity())
                .district(orderBig.getDistrict())
                .postCode(orderBig.getPostCode())
                .goodsValue(null)
                .freight(orderBig.getFreight().add(orderBig.getInsuredFee()).add(orderBig.getOtherRate()))
                .discount(orderBig.getDiscount())
                .taxTotal(orderBig.getTax())
                .taxCurr(orderBig.getTaxCurrency())
                .acturalPaid(orderBig.getActuralPaid())
                .payCurr(orderBig.getPayCurr())
                .insuredFee(orderBig.getInsuredFee())
                .insurCurr(orderBig.getInsurCurr())
                .freightCurr(orderBig.getFreightCurrency())
                .fCode(orderBig.getFcyCurrency())
                .ebpCode(orderBig.getEbpCode())
                .ebpName(null)
                .ebcCode(orderBig.getEbcCode())
                .ebcName(null)
                .userId(orderBig.getOrderType().equals(BigOrderTypeEnum.DQD) ? orderBig.getShopCode() : orderBig.getShopId())
                .userName(null)
                .logisticsCode(orderBig.getTpl())
                .logisticsName(null)
                .logisticsNo(orderBig.getDeliveryCode())
                .logisticsNote(null)
                .payCode(orderBig.getPayCopNo())
                .payName(orderBig.getPayPcomName())
                .payTransactionId(orderBig.getPayNo())
                .payTime(orderBig.getPayDate())
                .payNote(orderBig.getPayNots())
                .customsCode(orderBig.getCustomsCode().getValue())
                .ciqbCode(orderBig.getCiqbCode())
                .ieFlag(BigConvertConstant.IEFLAG)
                .busiMode(orderBig.getBusiMode())
                .orderType(Long.valueOf(orderBig.getOrderType().getValue()))
                .orderStatus(orderBig.getOrderStatus().getValue())
                .customsType(Long.valueOf(orderBig.getCustomsType().getValue()))
                .statisticsFlag(null)
                .trafMode(null)
                .trafNo(null)
                .voyageNo(null)
                .billNo(null)
                .shippernCode(null)
                .wrapType(null)
                .shipDate(null)
                .inputDate(null)
                .shipperName(null)
                .shipperAddress(null)
                .shipperPhone(null)
                .packNo(BigConvertConstant.PACKNO)
                .grossWeight(orderBig.getGrossWeight())
                .netWeight(orderBig.getNetWeight())
                .fromEplat(orderBig.getFromEplat() == null ? null : Long.valueOf(orderBig.getFromEplat().getValue()))
                .printHeader(orderBig.getPrintHeader())
                .changeFlag(null)
                .traceFlag(null)
                .reDeclare(null)
                .vmiFlag(orderBig.getVmiFlag() == null ? null : Long.valueOf(orderBig.getVmiFlag().getValue()))
                .ownerFlag(orderBig.getOwnerFlag() == null ? null : Long.valueOf(orderBig.getOwnerFlag().getValue()))
                .isStoreStrategy(orderBig.getIsStoreStrategy() == null ? null : Long.valueOf(orderBig.getIsStoreStrategy().getValue()))
                .orderCode((parseCommonField(orderBig.getCommonField())).containsKey("orderCode") ? parseCommonField(orderBig.getCommonField()).get("orderCode") : null)
                .storeCode(defaultConfig.getWarehouseCode())//仓储编码：BBC取转单配置
                .platformOrderType(null)
                .orderSource(getOrderSource(orderBig.getFromEplat(), orderBig.getOrderType().getValue()))
                .ownerUserId(null)
                .ownerUserName(null)
                .lpCode((parseCommonField(orderBig.getCommonField())).containsKey("lpCode") ? parseCommonField(orderBig.getCommonField()).get("lpCode") : null)
                .deliveryTimePreference(null)
                .isPackage(null)
                .transportday(null)
                .note(orderBig.getNotes())
                .tradeMode(null)
                .commonField(null)
                .orderBatchNo(null)
                .businessType(null)
                .cBCode(null)
                .declareFormNo(null)
                .features(orderBig.getOrderType().equals(BigOrderTypeEnum.DQD) ? "\"{thirdPlatformCode\":" + "\"" + orderBig.getPlatformId() + "}\"" : null)
                .udf1(null)
                .udf2(null)
                .build();
        //货品
        List<OfcGoodsReqDto> goodsList = new ArrayList<>();
        for (OrderBigItem item : orderBig.getOrderBigItemList()) {
            OfcGoodsReqDto ofcGoodsReqDto = OfcGoodsReqDto.newBuilder()
                    .gnum(String.valueOf(item.getGnum()))
                    .goodsId(item.getGoodId())
                    .goodsName(item.getCopGName())
                    .barCode(item.getBarCode())
                    .currency(BigConvertConstant.CURRENCY)
                    .qty(new BigDecimal(item.getAmount()))
                    .qtyUnit(item.getQtyUnit())
                    .price(item.getPrice())
                    .totalPrice(item.getDecTotal())
                    .batchNo(item.getBatchNo())
                    .storeStrategyId(item.getStoreStrategyId())
                    .productionTime(item.getProductionTime())
                    .expDate(item.getExpDate())
                    .ebcCode(item.getOwnerCode())
                    .virtualOwner(item.getVirtualOwner())
                    .inventoryType(null)
                    .itemVersion(null)
                    .note(item.getNots())
                    .hsCode(item.getHsCode())
                    .ciqGoodsNo(item.getCiqGoodsNo())
                    .assemCountry(item.getAssemCountry())
                    .ciqAssemCountry(item.getCiqAssemCountry())
                    .spec(item.getSpec())
                    .brand(item.getBrand())
                    .packageType(item.getPackageType())
                    .qty1(item.getQty1())
                    .unit1(item.getUnit1())
                    .gQty1(item.getgQty1() == null ? null : item.getgQty1().toPlainString())
                    .qty2(null)
                    .gQty2(null)
                    .unit2(null)
                    .grossWeight(item.getGrossWeight())
                    .netWeight(item.getNetWeight())
                    .isGoodsRecord(item.getIsGoodsRecord() == null ? null : Long.valueOf(item.getIsGoodsRecord()))
                    .tradeUrl(null)
                    .billNo(null)
                    .declareCode(null)
                    .ciqDeclareCode(null)
                    .ciqInboundDeclareCode(null)
                    .build();
            goodsList.add(ofcGoodsReqDto);
        }
        return new OfcRequest(ofcOrderReqDto, goodsList);
    }

    /**
     * BC-OFC报文转换
     * @return
     */
    public static OfcBcRequest convertBcOfc(OrderBig orderBig) {

        //货品
        List<OfcBcGoodsReqDto> goodsReqDtoList = new ArrayList<>();
        for (OrderBigItem item: orderBig.getOrderBigItemList()) {
            OfcBcGoodsReqDto goodsReqDto = OfcBcGoodsReqDto.newBuilder()
                    .gnum(String.valueOf(item.getGnum()))
                    .goodsId(item.getGoodId())
                    .goodsName(item.getCopGName())
                    .barCode(item.getBarCode())
                    .currency(BigConvertConstant.CURRENCY)
                    .qty(item.getAmount())
                    .qtyUnit(item.getQtyUnit())
                    .price(item.getPrice())
                    .totalPrice(item.getDecTotal())
                    .batchNo(item.getBatchNo())
                    .note(item.getNots())
                    .ebcCode(item.getOwnerCode())
                    .inventoryType(null)
                    .itemVersion(null)
                    .virtualOwner(item.getVirtualOwner())
                    .storeStrategyId(item.getStoreStrategyId())
                    .productionTime(item.getProductionTime())
                    .traceSourceCode(null)
                    .expDate(item.getExpDate())
                    .hsCode(item.getHsCode())
                    .ciqGoodsNo(item.getCiqGoodsNo())
                    .assemCountry(item.getAssemCountry())
                    .ciqAssemCountry(item.getCiqAssemCountry())
                    .spec(item.getSpec())
                    .brand(item.getBrand())
                    .packageType(item.getPackageType())
                    .qty1(item.getQty1())
                    .gQty1(item.getgQty1())
                    .unit1(item.getUnit1())
                    .qty2(item.getQty2())
                    .gQty2(item.getgQty2())
                    .unit2(item.getUnit2())
                    .grossWeight(item.getGrossWeight())
                    .netWeight(item.getNetWeight())
                    .isGoodsRecord(item.getIsGoodsRecord() == null ? null : Integer.valueOf(item.getIsGoodsRecord()))
                    .build();
            goodsReqDtoList.add(goodsReqDto);
        }
        //订单
        OfcBcRequest ofcBcRequest = OfcBcRequest.newBuilder()
                .orderNo(orderBig.getOrderId())
                .declTime(orderBig.getOrderDate())
                .buyerRegNo(orderBig.getBuyerRegNo())
                .buyerIdType(orderBig.getBuyerIdType().getValue())
                .buyerIdNumber(AES256Util.decrypt(orderBig.getBuyerIdNumber()))
                .buyerName(orderBig.getBuyerName())
                .buyerTelephone(AES256Util.decrypt(orderBig.getBuyerTelephone()))
                .receiveNo(AES256Util.decrypt(orderBig.getReceiverIdNumber()))
                .consignee(orderBig.getReceiverName())
                .consigneeTelephone(AES256Util.decrypt(orderBig.getReceiverMobile()))
                .pod(orderBig.getPod())
                .consigneeAddress(orderBig.getAddress())
                .country(orderBig.getCountry())
                .province(orderBig.getProvince())
                .city(orderBig.getCity())
                .district(orderBig.getDistrict())
                .postCode(orderBig.getPostCode())
                .goodsValue(null)
                .freight(orderBig.getFreight().add(orderBig.getInsuredFee()).add(orderBig.getOtherRate()))
                .discount(orderBig.getDiscount())
                .taxTotal(orderBig.getTax())
                .taxCurr(orderBig.getTaxCurrency())
                .acturalPaid(orderBig.getActuralPaid())
                .payCurr(orderBig.getPayCurr())
                .insuredFee(orderBig.getInsuredFee())
                .insurCurr(orderBig.getInsurCurr())
                .freightCurr(orderBig.getFreightCurrency())
                .fCode(orderBig.getFcyCurrency())
                .ebpCode(orderBig.getEbpCode())
                .ebpName(null)
                .ebcCode(orderBig.getEbcCode())
                .ebcName(null)
                .userId(orderBig.getShopId())
                .userName(null)
                .logisticsCode(orderBig.getTpl())
                .logisticsName(null)
                .logisticsNo(orderBig.getDeliveryCode())
                .logisticsNote(orderBig.getLogNots())
                .payCode(orderBig.getPayCopNo())
                .payName(orderBig.getPayPcomName())
                .payTransactionId(orderBig.getPayNo())
                .payTime(orderBig.getPayDate())
                .payNote(orderBig.getPayNots())
                .customsCode(orderBig.getCustomsCode().getValue())
                .ciqbCode(orderBig.getCiqbCode())
                .ieFlag(BigConvertConstant.IEFLAG)
                .busiMode(orderBig.getBusiMode())
                .orderType(String.valueOf(orderBig.getOrderType().getValue()))
                .orderStatus(orderBig.getOrderStatus().getValue())
                .customsType(orderBig.getCustomsType().getValue())
                .statisticsFlag(null)
                .trafMode(orderBig.getTrans())
                .trafNo(orderBig.getTransNo())
                .voyageNo(null)
                .billNo(orderBig.getBlno())
                .shippernCode(orderBig.getShippernCode())
                .wrapType(null)
                .shipDate(orderBig.getShipDate())
                .inputDate(orderBig.getInputDate())
                .shipperName(orderBig.getShipperName())
                .shipperAddress(orderBig.getShipperAddress())
                .shipperPhone(orderBig.getShipperPhone())
                .packNo(BigConvertConstant.PACKNO)
                .grossWeight(orderBig.getGrossWeight())
                .netWeight(orderBig.getNetWeight())
                .fromEplat(orderBig.getFromEplat().getValue())
                .printHeader(orderBig.getPrintHeader())
                .changeFlag(orderBig.getChangeFlag() == null ? null : Integer.valueOf(orderBig.getChangeFlag()))
                .traceFlag(null)
                .reDeclare(orderBig.getReDeclare())
                .note(orderBig.getNotes())
                .vmiFlag(orderBig.getVmiFlag().getValue())
                .ownerFlag(orderBig.getOwnerFlag().getValue())
                .isStoreStrategy(orderBig.getIsStoreStrategy().getValue())
                .orderCode(null)
                .storeCode(orderBig.getStationbCode())//仓储编码：BC取申报场站
                .platformOrderType(null)
                .orderSource(getOrderSource(orderBig.getFromEplat(), 0).intValue())
                .ownerUserId(null)
                .ownerUserName(null)
                .lpCode(null)
                .deliveryTimePreference(null)
                .tradeMode(orderBig.getTradeMode())
                .commonField(orderBig.getCommonField())
                .orderBatchNo(null)
                .traceSourceCode(null)
                .bcTempSoItemVos(goodsReqDtoList)
                .build();

        return ofcBcRequest;
    }

    /**
     * EXP-OFC报文转换
     * @return
     */
    public static OfcBcExpRequest convertBcExpOfc(OrderBig orderBig)
    {
        //订单
        OfcBcExpOrderReqDto bcExpOrderReqDto = OfcBcExpOrderReqDto.newBuilder()
                .orderNo(orderBig.getOrderId())
                .declTime(orderBig.getOrderDate())
                .buyerRegNo(orderBig.getBuyerRegNo())
                .buyerIdType(orderBig.getBuyerIdType() == null ? null : orderBig.getBuyerIdType().getValue())
                .buyerIdNumber(AES256Util.decrypt(orderBig.getBuyerIdNumber()))
                .buyerName(orderBig.getBuyerName())
                .buyerTelephone(AES256Util.decrypt(orderBig.getBuyerTelephone()))
                .receiveNo(AES256Util.decrypt(orderBig.getReceiverIdNumber()))
                .consignee(orderBig.getReceiverName())
                .consigneeTelephone(AES256Util.decrypt(orderBig.getReceiverMobile()))
                .pod(orderBig.getPod())
                .consigneeAddress(orderBig.getAddress())
                .country(orderBig.getCountry())
                .province(orderBig.getProvince())
                .city(orderBig.getCity())
                .district(orderBig.getDistrict())
                .postCode(orderBig.getPostCode())
                .goodsValue(null)
                .freight(orderBig.getFreight().add(orderBig.getInsuredFee()).add(orderBig.getOtherRate()))
                .discount(orderBig.getDiscount())
                .taxTotal(orderBig.getTax())
                .taxCurr(orderBig.getTaxCurrency())
                .acturalPaid(orderBig.getActuralPaid())
                .payCurr(orderBig.getPayCurr())
                .insuredFee(orderBig.getInsuredFee())
                .insurCurr(orderBig.getInsurCurr())
                .freightCurr(orderBig.getFreightCurrency())
                .fCode(orderBig.getFcyCurrency())
                .ebpCode(orderBig.getEbpCode())
                .ebpName(null)
                .ebcCode(orderBig.getEbcCode())
                .ebcName(null)
                .userId(orderBig.getShopId())
                .userName(null)
                .logisticsCode(orderBig.getTpl())
                .logisticsName(null)
                .logisticsNo(orderBig.getDeliveryCode())
                .logisticsNote(orderBig.getLogNots())
                .payCode(orderBig.getPayCopNo())
                .payName(orderBig.getPayPcomName())
                .payTransactionId(orderBig.getPayNo())
                .payTime(orderBig.getPayDate())
                .payNote(orderBig.getPayNots())
                .customsCode(orderBig.getCustomsCode().getValue())
                .ciqbCode(orderBig.getCiqbCode())
                .ieFlag(BigConvertConstant.EXPFLAG)
                .busiMode(BigConvertConstant.EXP_BUSIMODE)
                .orderType(orderBig.getOrderType() == null ? null : String.valueOf(orderBig.getOrderType().getValue()))
                .orderStatus(orderBig.getOrderStatus().getValue())
                .customsType(orderBig.getCustomsType().getValue())
                .statisticsFlag(orderBig.getStatisticsFlag())
                .trafMode(orderBig.getTrans())
                .trafNo(orderBig.getTransNo())
                .voyageNo(null)
                .billNo(orderBig.getBlno())
                .shippernCode(orderBig.getShippernCode())
                .wrapType(null)
                .shipDate(orderBig.getShipDate())
                .inputDate(orderBig.getInputDate())
                .shipperName(orderBig.getShipperName())
                .shipperAddress(orderBig.getShipperAddress())
                .shipperPhone(orderBig.getShipperPhone())
                .packNo(orderBig.getPackNo() == null ? BigConvertConstant.PACKNO : Long.valueOf(orderBig.getPayNo()))
                .grossWeight(orderBig.getGrossWeight())
                .netWeight(orderBig.getNetWeight())
                .fromEplat(orderBig.getFromEplat().getValue())
                .printHeader(orderBig.getPrintHeader())
                .changeFlag(orderBig.getChangeFlag() == null ? null : Integer.valueOf(orderBig.getChangeFlag()))
                .traceFlag(null)
                .reDeclare(orderBig.getReDeclare())
                .note(orderBig.getNotes())
                .vmiFlag(orderBig.getVmiFlag().getValue())
                .ownerFlag(orderBig.getOwnerFlag().getValue())
                .isStoreStrategy(orderBig.getIsStoreStrategy().getValue())
                .orderCode(null)
                .storeCode(orderBig.getStationbCode())//仓储编码：BC取申报场站
                .platformOrderType(null)
                .orderSource(getOrderSource(orderBig.getFromEplat(), 0).intValue())
                .ownerUserId(null)
                .ownerUserName(null)
                .lpCode(null)
                .deliveryTimePreference(null)
                .tradeMode(orderBig.getTradeMode())
                .commonField(orderBig.getCommonField())
                .orderBatchNo(null)
                .traceSourceCode(null)
                .build();
        //货品
        List<OfcBcExpGoodsReqDto> goodsReqDtoList = new ArrayList<>();
        for (OrderBigItem item: orderBig.getOrderBigItemList()) {
            OfcBcExpGoodsReqDto bcExpGoodsReqDto = OfcBcExpGoodsReqDto.newBuilder()
                    .gnum(String.valueOf(item.getGnum()))
                    .goodsId(item.getGoodId())
                    .goodsName(item.getCopGName())
                    .barCode(item.getBarCode())
                    .currency(BigConvertConstant.CURRENCY)
                    .qty(new BigDecimal(item.getAmount()))
                    .qtyUnit(item.getQtyUnit())
                    .price(item.getPrice())
                    .totalPrice(item.getDecTotal())
                    .batchNo(item.getBatchNo())
                    .note(item.getNots())
                    .ebcCode(item.getOwnerCode())
                    .inventoryType(null)
                    .itemVersion(null)
                    .virtualOwner(item.getVirtualOwner())
                    .storeStrategyId(item.getStoreStrategyId())
                    .productionTime(item.getProductionTime())
                    .traceSourceCode(null)
                    .expDate(item.getExpDate())
                    .hsCode(item.getHsCode())
                    .ciqGoodsNo(item.getCiqGoodsNo())
                    .assemCountry(item.getAssemCountry())
                    .ciqAssemCountry(item.getCiqAssemCountry())
                    .spec(item.getSpec())
                    .brand(item.getBrand())
                    .packageType(item.getPackageType())
                    .qty1(item.getQty1())
                    .gQty1(item.getgQty1())
                    .unit1(item.getUnit1())
                    .qty2(item.getQty2())
                    .gQty2(item.getgQty2())
                    .unit2(item.getUnit2())
                    .grossWeight(item.getGrossWeight())
                    .netWeight(item.getNetWeight())
                    .isGoodsRecord(item.getIsGoodsRecord() == null ? null : Integer.valueOf(item.getIsGoodsRecord()))
                    .build();
            goodsReqDtoList.add(bcExpGoodsReqDto);
        }
        return new OfcBcExpRequest(bcExpOrderReqDto, goodsReqDtoList);
    }

    /**
     * 转换订单来源
     * @param fromEplat
     * @return
     */
    private static Long getOrderSource(YesOrNoEnum fromEplat, int orderType)
    {
        Long orderSource = null;
        if (fromEplat == null || fromEplat.getValue() == 0)
        {
            orderSource = 101L;
        } else if (fromEplat.getValue() == 1)
        {
            orderSource = 100L;
        }
        if (orderType == 5){//京东多渠道订单
            orderSource = 601L;
        }
        return orderSource;
    }

    /**
     * 解析扩展字段
     * @param commonfieid
     * @return
     */
    private static Map<String,String> parseCommonField(String commonfieid){
        Map<String,String> map = new HashMap<>();
        if (StringUtils.isBlank(commonfieid)){
            return map;
        }

        Map tempMap = JSON.parseObject(commonfieid, Map.class);
        if (tempMap.size() == 0) {
            return map;
        } else {
            return tempMap;
        }


//        map = Splitter.on(",")
//                .trimResults()
//                .withKeyValueSeparator(":")
//                .split(commonfieid.substring(1,commonfieid.length()-1));

    }
}
