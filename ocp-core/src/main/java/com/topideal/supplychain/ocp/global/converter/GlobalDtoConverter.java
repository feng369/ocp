package com.topideal.supplychain.ocp.global.converter;

import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.ofc.dto.OfcGoodsReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.order.model.OrderGlobal;
import com.topideal.supplychain.ocp.order.model.OrderGlobalItem;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName DtoConverter
 * 全球仓dto转换器
 * @Author zhangli
 * @DATE 2019/12/10 17:38
 * @Version 1.0
 **/

public class GlobalDtoConverter {

    public static OfcRequest convertOfc(OrderGlobal orderGlobal, TransferDefaultConfig config) {
        OfcOrderReqDto ofcOrderReqDto = OfcOrderReqDto.newBuilder()
                .key(null)
                .orderNo(orderGlobal.getDeclareCode())
                .declTime(orderGlobal.getOrderDate())
                .buyerRegNo(orderGlobal.getPhone())
                .buyerIdType("1")
                .buyerIdNumber(orderGlobal.getPayerIdNo())
                .buyerName(orderGlobal.getPayerName())
                .buyerTelephone(orderGlobal.getPhone())
                .receiveNo(orderGlobal.getPayerIdNo())
                .consignee(orderGlobal.getName())
                .consigneeTelephone(orderGlobal.getPhone())
                .pod(null)
                .consigneeAddress(orderGlobal.getStreet())
                .country("中国")
                .province(orderGlobal.getProvince())
                .city(orderGlobal.getCity())
                .district(orderGlobal.getDistrict())
                .postCode(null)
                .goodsValue(orderGlobal.getGoodsTotalAmount())
                .freight(orderGlobal.getFreightAmount())
                .discount(orderGlobal.getDiscountAmount())
                .taxTotal(orderGlobal.getTaxAmount())
                .taxCurr("CNY")
                .acturalPaid(null)
                .payCurr("CNY")
                .insuredFee(orderGlobal.getInsuranceAmount())
                .insurCurr("CNY")
                .freightCurr("CNY")
                .fCode("CNY")
                .ebpCode(StringUtils.isNotEmpty(config.getPlatformCode()) ? config.getPlatformCode() : orderGlobal.getEbpCode())
                .ebpName(orderGlobal.getEbpName())
                .ebcCode(orderGlobal.getEbcCode())//全球仓不取转单配置的商家
                .ebcName(orderGlobal.getEbcName())
                .userId(orderGlobal.getStoreCode())
                .userName(null)
                .logisticsCode(config.getLogisticsCode())
                .logisticsName(config.getLogisticsName())
                .logisticsNo(null)
                .logisticsNote(null)
                .payCode(null)
                .payName(orderGlobal.getDeclarePayType())
                .payTransactionId(orderGlobal.getDeclarePayNo())
                .payTime(null)
                .payNote(null)
                .customsCode(orderGlobal.getCustomsCode())
                .ciqbCode(config.getCiqCode())
                .ieFlag("I")
                .busiMode(orderGlobal.getBusiMode().getValue())
                .orderType(StringUtils.isNotEmpty(config.getOrderType()) ? Long.valueOf(config.getOrderType()) : 1L)
                .orderStatus("S")
                .customsType(orderGlobal.getCustomsType() == null ? null : Long.valueOf(orderGlobal.getCustomsType()))
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
                .orderCode(null)
                .storeCode(StringUtils.isNotEmpty(config.getWarehouseCode()) ? config.getWarehouseCode() : orderGlobal.getWarehouseCode())
                .platformOrderType(null)
                .orderSource(StringUtils.isNotEmpty(config.getOrderSource()) ? Long.valueOf(config.getOrderSource()) : 101L)
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
                .udf2(null).build();
        List<OfcGoodsReqDto> goodsList = Lists.newArrayList();
        int num = 0;
        for (OrderGlobalItem item : orderGlobal.getItemList()) {
            OfcGoodsReqDto ofcGoodsReqDto = OfcGoodsReqDto.newBuilder()
                    .gnum(String.valueOf(++num))
                    .goodsId(item.getSkuId())
                    .goodsName(item.getName())
                    .barCode(item.getBarcode())
                    .currency(null)
                    .qty(item.getCount() == null ? null : BigDecimal.valueOf(item.getCount()))
                    .qtyUnit(null)
                    .price(item.getDeclarePrice())
                    .totalPrice(item.getTotalPrice())
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
            goodsList.add(ofcGoodsReqDto);
        }
        return new OfcRequest(ofcOrderReqDto, goodsList);
    }
}
