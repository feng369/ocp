package com.topideal.supplychain.ocp.beibei.converter;

import com.google.common.collect.Lists;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.ofc.dto.OfcGoodsReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.order.dto.OrderBeibeiDto;
import com.topideal.supplychain.ocp.order.model.OrderBeibeiItem;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2020/3/21
 * @description
 **/
public class BeibeiDtoConverter {

    public static OfcRequest convertOfc(OrderBeibeiDto dto, TransferDefaultConfig transferConfig) {
        List<OrderBeibeiItem> goodsList = dto.getItem();
        OfcOrderReqDto.Builder build = OfcOrderReqDto.newBuilder()
                .key(null)
                .orderNo(dto.getOid())
                .declTime(dto.getOrderTime())
                .buyerRegNo(dto.getUser())
                .buyerIdType("1")
                .buyerIdNumber(dto.getUserMemberCard())
                .buyerName(dto.getUserName())
                .buyerTelephone(dto.getReceiverPhone())
                .receiveNo(dto.getUserMemberCard())
                .consignee(dto.getReceiverName())
                .consigneeTelephone(dto.getReceiverPhone())
                .pod(null)
                .consigneeAddress(dto.getReceiverAddress())
                .country(null)
                .province(dto.getProvince())
                .city(dto.getCity())
                .district(dto.getCounty())
                .postCode(null)
                .goodsValue(null)
                .freight(dto.getShippingFee())
                .discount(dto.getDiscount())
                .taxTotal(dto.getTaxFee())
                .taxCurr("CNY")
                .acturalPaid(dto.getPayment())
                .payCurr("CNY")
                .insuredFee(BigDecimal.ZERO)
                .insurCurr("CNY")
                .freightCurr("CNY")
                .fCode("CNY")
                .ebpCode(dto.getEbpCode())
                .ebpName(null)
                .ebcCode(dto.getEbcCode())
                .ebcName(null)
                .userId(dto.getStoreCode())
                .userName(null)
                .logisticsCode(null)
                .logisticsName(null)
                .logisticsNo(null)
                .logisticsNote(null)
                .payCode(null)
                .payName(dto.getChannelName())
                .payTransactionId(dto.getChannelInfo())
                .payTime(dto.getPayTime())
                .payNote(null)
                .customsCode(dto.getCustomsCode())
                .ciqbCode("")
                .ieFlag("I")
                .busiMode(BusiModeEnum.BBC.getValue())
                .orderType(2L)
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
                .orderCode(null)
                .storeCode(null)
                .platformOrderType(null)
                .orderSource(null)
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
         * 转单配置赋值
         */
        if (transferConfig != null) {
            if (StringUtils.isNotEmpty(transferConfig.getPlatformCode())) {
                build.ebpCode(transferConfig.getPlatformCode());
            }
            if (StringUtils.isNotEmpty(transferConfig.getMerchantCode())) {
                build.ebcCode(transferConfig.getMerchantCode());
            }
            if (StringUtils.isNotEmpty(transferConfig.getLogisticsCode())) {
                build.logisticsCode(transferConfig.getLogisticsCode());
            }
            if (StringUtils.isNotEmpty(transferConfig.getWarehouseCode())) {
                build.storeCode(transferConfig.getWarehouseCode());
            }
            if (StringUtils.isNotEmpty(transferConfig.getOrderSource())) {
                build.orderSource(Long.valueOf(transferConfig.getOrderSource()));
            }
            if (StringUtils.isNotEmpty(transferConfig.getFromEplat())) {
                build.fromEplat(Long.valueOf(transferConfig.getFromEplat()));
            }
            if (StringUtils.isNotEmpty(transferConfig.getCiqCode())) {
                build.ciqbCode(transferConfig.getCiqCode());
            }
        }


        List<OfcGoodsReqDto> list = Lists.newArrayList();
        for (int i = 0; i < goodsList.size(); i++) {
            OrderBeibeiItem orderGoods = goodsList.get(i);
            OfcGoodsReqDto ofcGoodsReqDto = OfcGoodsReqDto.newBuilder()
                    .gnum(String.valueOf(orderGoods.getGnum()))
                    .goodsId(orderGoods.getOuterId())
                    .goodsName(null)
                    .barCode(null)
                    .currency("CNY")
                    .qty(new BigDecimal(orderGoods.getNum()))
                    .qtyUnit(null)
                    .price(orderGoods.getDeclareAmount() != null ? orderGoods.getDeclareAmount().divide(new BigDecimal(orderGoods.getNum()),2, BigDecimal.ROUND_HALF_UP):orderGoods.getPrice())
                    .totalPrice(orderGoods.getDeclareAmount()!=null ?orderGoods.getDeclareAmount():orderGoods.getPrice().multiply(new BigDecimal(orderGoods.getNum())))
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
        //折扣价
        OfcOrderReqDto ofcOrderReqDto = build.build();
        return new OfcRequest(ofcOrderReqDto, list);
    }
}
