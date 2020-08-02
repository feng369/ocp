package com.topideal.supplychain.ocp.ymatou.converter;

import com.google.common.collect.Lists;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.gemini.dto.GeminiReqGoods;
import com.topideal.supplychain.ocp.gemini.dto.GeminiRequest;
import com.topideal.supplychain.ocp.kjb.dto.KjbReqGoods;
import com.topideal.supplychain.ocp.kjb.dto.KjbRequest;
import com.topideal.supplychain.ocp.ofc.dto.OfcGoodsReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto.Builder;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.op.dto.OpCommonFieldDto;
import com.topideal.supplychain.ocp.op.dto.OpGoodsItemDto;
import com.topideal.supplychain.ocp.op.dto.OpRecipientDto;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.order.dto.OrderYmatouDto;
import com.topideal.supplychain.ocp.order.model.OrderYmatouItem;
import com.topideal.supplychain.util.AES256Util;
import com.topideal.supplychain.util.JacksonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DtoConverter
 * @TODO dto转换器
 * @Author zhangzhihao
 * @DATE 2019/12/3 17:38
 * @Version 1.0
 **/

public class YmatouDtoConverter {

    /**
     * 组装税金接口报文
     */
    public static GeminiRequest convertGemini(OrderYmatouDto order) {

        GeminiRequest.Builder builderReq = GeminiRequest.newBuilder().ebpCode(order.getEbpCode())
                .electricCode(order.getEbcCode()).tid(order.getOrderNo())
                .busiMode(order.getBusiType().getValue()).customerCode(order.getCustomerCode());
        List<GeminiReqGoods> goodsList = new ArrayList<>();
        for (OrderYmatouItem orderItem : order.getOrderItemsInfo()) {
            BigDecimal num = new BigDecimal(orderItem.getNum());
            GeminiReqGoods item = GeminiReqGoods.newBuilder().id(orderItem.getId())
                    .outerItemId(orderItem.getOuterSkuId())
                    .outerItemName(orderItem.getProductTitle())
                    .feePrice(orderItem.getPrice())
                    .feeTotalPrice(orderItem.getPrice().multiply(num))
                    .num(num).build();
            goodsList.add(item);
        }
        builderReq.totalPrice(order.getAmount()).goods(goodsList);
        return builderReq.build();
    }


    /**
     * 组装OFC接口报文
     */
    public static OfcRequest convertOfc(OrderYmatouDto dto,List<OrderYmatouItem> oldItemList, TransferDefaultConfig transferConfig) {
        List<OrderYmatouItem> orders = dto.getOrderItemsInfo();

        /**
         * 使用原始数据计算折扣金额
         */
        BigDecimal discount = BigDecimal.ZERO;
        for (OrderYmatouItem orderYmatouItem : oldItemList) {
            discount = discount
                    .add(orderYmatouItem.getpCouponDiscount() == null ? BigDecimal.ZERO : orderYmatouItem.getpCouponDiscount())
                    .add(orderYmatouItem.getmCouponDiscount() == null ? BigDecimal.ZERO : orderYmatouItem.getmCouponDiscount())
                    .add(orderYmatouItem.getpPromotionDiscount() == null ? BigDecimal.ZERO : orderYmatouItem.getpPromotionDiscount())
                    .add(orderYmatouItem.getmPromotionDiscount() == null ? BigDecimal.ZERO : orderYmatouItem.getmPromotionDiscount())
                    .add(orderYmatouItem.getRebateAmount() == null ? BigDecimal.ZERO : orderYmatouItem.getRebateAmount());
            if (BigDecimal.ZERO.compareTo(orderYmatouItem.getmAdjustDiscount()) > 0) {
                discount = discount.subtract(orderYmatouItem.getmAdjustDiscount());
            }
        }


        Builder build = OfcOrderReqDto.newBuilder()
                .key(null)
                .orderNo(dto.getOrderNo())
                .declTime(dto.getOrderTime())
                .buyerRegNo(dto.getBuyerRegNo())
                .buyerIdType("1")
                .buyerIdNumber(AES256Util.decrypt(dto.getBuyerIdNumber()))
                .buyerName(dto.getReceiverName())
                .buyerTelephone(dto.getReceiverMobile())
                .receiveNo(dto.getReceiveNo())
                .consignee(dto.getReceiverName())
                .consigneeTelephone(dto.getReceiverMobile())
                .pod(null)
                .consigneeAddress(dto.getReceiverAddress())
                .country("中国")
                .province(dto.getProvince())
                .city(dto.getCity())
                .district(dto.getDistrict())
                .postCode(dto.getReceiverZip())
                .goodsValue(dto.getGoodsValue() != null ? dto.getGoodsValue() : null)
                .freight(dto.getFreight() != null ? dto.getFreight() : null)
                .discount(discount/*dto.getYhPrice() != null ? dto.getYhPrice() : null*/)
                .taxTotal(dto.getTaxTotal() != null ? dto.getTaxTotal() : null)
                .taxCurr("CNY")
                .acturalPaid(dto.getPayment() != null ? dto.getPayment() : null)
                .payCurr("CNY")
                .insuredFee(dto.getInsuredFee() != null ? dto.getInsuredFee() : null)
                .insurCurr("CNY")
                .freightCurr("CNY")
                .fCode("CNY")
                .ebpCode(dto.getEbpCode())
                .ebpName(null)
                .ebcCode(dto.getEbcCode())
                .ebcName(null)
                .userId(dto.getStoreId())
                .userName(null)
                .logisticsCode(null)
                .logisticsName(null)
                .logisticsNo(null)
                .logisticsNote(null)
                .payCode(null)
                .payName(null)
                .payTransactionId(dto.getPayTransactionId())
                .payTime(dto.getPaidTime())
                .payNote(null)
                .customsCode(dto.getCustomerCode())
                .ciqbCode("000069")
                .ieFlag("I")
                .busiMode(BusiModeEnum.BBC.getValue())
                .orderType((transferConfig != null && StringUtils
                        .isNotEmpty(transferConfig.getOrderType())) ? Long
                        .valueOf(transferConfig.getOrderType()) : 1L)
                .orderStatus("S")
                .customsType(1L)
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
                .packNo(1L)
                .grossWeight(null)
                .netWeight(null)
                .fromEplat(null)
                .printHeader(null)
                .changeFlag(null)
                .traceFlag(null)
                .reDeclare(null)
                .vmiFlag(null)
                .ownerFlag(null)
                .isStoreStrategy(null)
                .orderCode(dto.getOrderId())
                .storeCode(transferConfig != null ? transferConfig.getWarehouseCode() : null)
                .platformOrderType(null)
                .orderSource((transferConfig != null && StringUtils
                        .isNotEmpty(transferConfig.getOrderSource())) ? Long
                        .valueOf(transferConfig.getOrderSource()) : 101L)
                .ownerUserId(null)
                .ownerUserName(null)
                .lpCode(null)
                .deliveryTimePreference(null)
                .isPackage(null)
                .transportday(null)
                .note(null)
                .tradeMode("1")
                .commonField(null)
                .orderBatchNo(null)
                .businessType(null)
                .cBCode(null)
                .declareFormNo(null)
                .udf1(null)
                .udf2(null);
        /**
         * 给默认配置
         */
        if (transferConfig != null && StringUtils.isNotEmpty(transferConfig.getLogisticsCode())) {
            build.logisticsCode(transferConfig.getLogisticsCode());
        }
        if (transferConfig != null && StringUtils.isNotEmpty(transferConfig.getMerchantCode())) {
            build.ebcCode(transferConfig.getMerchantCode());
        }
        if (transferConfig != null && StringUtils.isNotEmpty(transferConfig.getPlatformCode())) {
            build.ebpCode(transferConfig.getPlatformCode());
        }
        if (transferConfig != null && StringUtils.isNotEmpty(transferConfig.getFromEplat())) {
            build.fromEplat(Long.valueOf(transferConfig.getFromEplat()));
        }

        OfcOrderReqDto ofcOrderReqDto = build.build();

        List<OfcGoodsReqDto> list = Lists.newArrayList();
        for (int i = 0; i < orders.size(); i++) {
            OrderYmatouItem orderYmatouItem = orders.get(i);

            OfcGoodsReqDto ofcGoodsReqDto = OfcGoodsReqDto.newBuilder()
                    .gnum(String.valueOf(i + 1))
                    .goodsId(orderYmatouItem.getOuterSkuId())
                    .goodsName(orderYmatouItem.getProductTitle())
                    .barCode(null)
                    .currency(null)
                    .qty(new BigDecimal(orderYmatouItem.getNum()))
                    .qtyUnit(null)
                    .price(StringUtils.isNotEmpty(orderYmatouItem.getRePrice())
                            ? new BigDecimal(orderYmatouItem.getRePrice()) : BigDecimal.ZERO)
                    .totalPrice(StringUtils.isNotEmpty(orderYmatouItem.getReTotalPrice())
                            ? new BigDecimal(orderYmatouItem.getReTotalPrice()) : BigDecimal.ZERO)
                    .batchNo(null)
                    .storeStrategyId(null)
                    .productionTime(null)
                    .expDate(null)
                    .ebcCode(null)
                    .virtualOwner(null)
                    .inventoryType(null)
                    .itemVersion(null)
                    .note(null)
                    .hsCode(null)
                    .ciqGoodsNo(null)
                    .assemCountry(null)
                    .ciqAssemCountry(null)
                    .spec(null)
                    .brand(null)
                    .packageType(null)
                    .qty1(null)
                    .unit1(null)
                    .gQty1(null)
                    .qty2(null)
                    .gQty2(null)
                    .unit2(null)
                    .grossWeight(null)
                    .netWeight(null)
                    .isGoodsRecord(null)
                    .tradeUrl(null)
                    .billNo(null)
                    .declareCode(null)
                    .ciqDeclareCode(null)
                    .ciqInboundDeclareCode(null).build();
            list.add(ofcGoodsReqDto);
        }

        return new OfcRequest(ofcOrderReqDto, list);
    }

    /**
     * 组装请求组套接口对象
     */
    public static KjbRequest convertKjb(OrderYmatouDto order) {
        KjbRequest reqKjb = null;
        if (!CollectionUtils.isEmpty(order.getOrderItemsInfo())) {
            List<KjbReqGoods> reqGoodsList = new ArrayList<>();
            for (OrderYmatouItem item : order.getOrderItemsInfo()) {
                KjbReqGoods reqGoods = KjbReqGoods.newBuilder().goods_id(item.getOuterSkuId())
                        .goods_name(item.getProductTitle())
                        .goods_num(item.getNum().toString())
                        .goods_price(item.getPrice()).build();
                reqGoodsList.add(reqGoods);
            }
            reqKjb = KjbRequest.newBuilder().order_id(order.getOrderNo())
                    .cbepcomCode(order.getEbcCode()).payment_goods(order.getAmount())
                    .good_list(reqGoodsList).build();
        }
        return reqKjb;
    }

    public static OpRequest convertOp(OrderYmatouDto order, TransferDefaultConfig transferConfig) {
        OpRequest.Builder builder = OpRequest.newBuilder().orderId(order.getPaymentOrderNo())
                .orderDate(order.getPaidTime()).packingMaterial(null).warehouseId(null)
                .tpl(null).orderType(1).orderStatus("S").customsType(1)
                .electricCode(order.getEbcCode())
                .cbepcomCode(order.getEbpCode()).busiMode(10).customsCode(order.getCustomerCode())
                .ciqbCode(transferConfig != null ? transferConfig.getCiqCode() : null)
                .stationbCode(null).deliveryCode(null).notes(order.getStoreId())
                .freightFcy(null).freightFcode("CNY").insuredFee(null).insurCurr(null)
                .insurMark(null)
                .taxFcy(null).taxFcode("CNY").otherRate(null).otherCurr(null).otherMark(null)
                .otherPayment(null).otherPaymentCurr(null).fCode("CNY").discount(null)
                .buyerName(order.getReceiverName())
                .buyerIdType(1).buyerIdNumber(AES256Util.decrypt(order.getBuyerIdNumber()))
                .buyerTelephone(order.getReceiverMobile()).buyerRegNo(order.getBuyerRegNo())
                .grossWeight(null).netWeight(null).bakbCode(null).ordExcStatus(null)
                .forSellComp(null).forSellCompName(null)
                .tradeUnitCode(null).tradeUnitName(null).shippernCode(null).shipDate(null)
                .inputDate(null).logNots(null).blno(null).trans(null)
                .transNo(null).changeFlag(null).tradeMode(null).shipperName(null)
                .shipperAddress(null).shipperPhone(null).agentCode(null)
                .agentName(null).payNo(order.getPaymentTransactionNo()).payPcomName(null)
                .payCopNo(null).opType(null).payType(null)
                .payStatus(null).payorName(null).activePayComp(null).acturalPaid(null).payCurr(null)
                .payNots(null).payDate(null)
                .fromEplat(null).printHeader(null).commonField(null).isStoreStrategy(null)
                .vmiFlag(null).ownerFlag(null).cutMode(null)
                .transMode(null).packNo(null).wrapType(null).sendCity(null).totalValue(null)
                .goodsInfo(null).orderBatchNo(null).reDeclare(null)
                .ebptpl(null);
        //电商企业、电商平台、第三方物流编码转换
        if (transferConfig != null) {
            if (StringUtils.isNotEmpty(transferConfig.getLogisticsCode())) {
                builder.tpl(transferConfig.getLogisticsCode());
            }
            if (StringUtils.isNotEmpty(transferConfig.getMerchantCode())) {
                builder.electricCode(transferConfig.getMerchantCode());
            }
            if (StringUtils.isNotEmpty(transferConfig.getPlatformCode())) {
                builder.cbepcomCode(transferConfig.getPlatformCode());
            }
        }
        //币值根据系数转换
        builder.freightFcy(
                order.getShippingFee() == null ? BigDecimal.ZERO : order.getShippingFee());
        builder.discount(order.getYhPrice() == null ? BigDecimal.ZERO : order.getYhPrice());
        builder.taxFcy(order.getTaxTotalPrice() == null ? BigDecimal.ZERO
                : new BigDecimal(order.getTaxTotalPrice()));
        builder.acturalPaid(order.getPayment() == null ? BigDecimal.ZERO : order.getPayment());
        builder.totalValue(BigDecimal.ZERO);

        OpCommonFieldDto opCommonFieldDto = OpCommonFieldDto.newBuilder().fx_kdt_id(null)
                .fx_order_no(null).isfx(null).tid(null).platformNo(order.getOrderId())
                .transaction(null).build();
        builder.commonField(JacksonUtils.toJSon(opCommonFieldDto));

        List<OrderYmatouItem> itemList = order.getOrderItemsInfo();
        List<OpGoodsItemDto> itemDtoList = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            OrderYmatouItem itemVo = itemList.get(i);
            OpGoodsItemDto dto = OpGoodsItemDto.newBuilder().gnum(i + 1 + "")
                    .goodId(itemVo.getOuterSkuId())
                    .amount(Integer.valueOf(itemVo.getNum().toString()))
                    .price(itemVo.getRePrice() == null ? null : new BigDecimal(itemVo.getRePrice()))
                    .goodPrice(null).copGName(null).hsCode(null).codeTs(null)
                    .decTotal(null).custGoodsNo(null).ciqGoodsNo(null).batchNo(null)
                    .assemCountry(null).qtyUnit(null).spec(null)
                    .storeStrategyId(null).productionTime(null).expDate(null).ownerCode(null)
                    .brand(null).packageType(null)
                    .qty1(null).unit1(null).qty2(null).unit2(null).ggrossWt(null).build();
            itemDtoList.add(dto);
        }
        OpRecipientDto recipientDto = OpRecipientDto.newBuilder()
                .name(order.getReceiverName()).receiveType(1).receiveNo(order.getReceiveNo())
                .mobilePhone(order.getReceiverMobile())
                .phone(order.getReceiverPhone()).country("中国").province(order.getProvince())
                .city(order.getCity()).district(order.getDistrict())
                .address(order.getReceiverAddress()).postCode(null).totalFavourable(null)
                .sender(null).receiver(null).congratulations(null)
                .transportDay(null).recipientProvincesCode(null).build();
        builder.goodList(itemDtoList).recipient(recipientDto);
        return builder.build();
    }
}
