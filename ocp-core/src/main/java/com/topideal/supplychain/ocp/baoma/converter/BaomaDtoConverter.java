package com.topideal.supplychain.ocp.baoma.converter;

import com.google.common.collect.Lists;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.ofc.dto.OfcGoodsReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto.Builder;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.op.dto.OpCommonFieldDto;
import com.topideal.supplychain.ocp.op.dto.OpGoodsItemDto;
import com.topideal.supplychain.ocp.op.dto.OpRecipientDto;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.order.dto.OrderBaomaDto;
import com.topideal.supplychain.ocp.order.model.OrderBaomaGoods;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/13
 * @description 请求参数转换器
 **/
public class BaomaDtoConverter {

    public static OfcRequest convertOfc(OrderBaomaDto dto, TransferDefaultConfig config) {

        /***计算总的税金***/
        BigDecimal taxTotal = BigDecimal.ZERO;
        List<OrderBaomaGoods> goodsList = dto.getOrderDetail();
        for (OrderBaomaGoods goods : goodsList) {
            taxTotal = taxTotal.add(goods.getDuty());
        }
        Builder build = OfcOrderReqDto.newBuilder()
                .key(null)
                .orderNo(dto.getOrderNo())
                .declTime(DateUtils.formatStringToDate(dto.getOrderTime(), DateUtils.Y_M_D_HMS))
                .buyerRegNo(dto.getPayerIdCard())
                .buyerIdType("1")
                .buyerIdNumber(dto.getPayerIdCard())
                .buyerName(dto.getPayerName())
                .buyerTelephone(dto.getPayerTel())
                .receiveNo(dto.getConsigneeIdCard())
                .consignee(dto.getConsigneeName())
                .consigneeTelephone(dto.getConsigneeTel())
                .pod(null)
                .consigneeAddress(dto.getAddress())
                .country(null)
                .province(dto.getProvinceName())
                .city(dto.getCityName())
                .district(dto.getDistrictName())
                .postCode(null)
                .goodsValue(dto.getTotalFee())
                .freight(dto.getFreight())
                .discount(BigDecimal.ZERO)
                .taxTotal(taxTotal)
                .taxCurr("CNY")
                .acturalPaid(null)
                .payCurr(null)
                .insuredFee(BigDecimal.ZERO)
                .insurCurr(null)
                .freightCurr("CNY")
                .fCode("CNY")
                .ebpCode(dto.getEbpCode())
                .ebpName(dto.getEbpName())
                .ebcCode(dto.getEnterCode())
                .ebcName(null)
                .userId(dto.getStoreCode())
                .userName(null)
                .logisticsCode(null)
                .logisticsName(null)
                .logisticsNo(null)
                .logisticsNote(null)
                .payCode(null)
                .payName(null)
                .payTransactionId(null)
                .payTime(null)
                .payNote(null)
                .customsCode(dto.getCustomsCode())
                .ciqbCode(dto.getCiqbCode())
                .ieFlag("I")
                .busiMode(BusiModeEnum.BBC.getValue())
                .orderType(dto.getOrderType() != null ? dto.getOrderType() : 1L)
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
                .storeCode(dto.getStoreCodeOfc())
                .platformOrderType(null)
                .orderSource(dto.getOrderSource() != null ? dto.getOrderSource() : 101L)
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
        //赋值默认配置
        if (config != null) {
            if(StringUtils.isNotEmpty(config.getLogisticsCode())){
                build.logisticsCode(config.getLogisticsCode());
            }
            if(StringUtils.isNotEmpty(config.getPlatformCode())){
                build.ebpCode(config.getPlatformCode());
            }
            if(StringUtils.isNotEmpty(config.getMerchantCode())){
                build.ebcCode(config.getMerchantCode());
            }
            if(StringUtils.isNotEmpty(config.getFromEplat())){
                build.fromEplat(Long.valueOf(config.getFromEplat()));
            }

        }
        OfcOrderReqDto ofcOrderReqDto = build.build();

        List<OfcGoodsReqDto> list = Lists.newArrayList();
        for (int i = 0; i < goodsList.size(); i++) {
            OrderBaomaGoods orderGoods = goodsList.get(i);

            OfcGoodsReqDto ofcGoodsReqDto = OfcGoodsReqDto.newBuilder()
                    .gnum(String.valueOf(i + 1))
                    .goodsId(orderGoods.getThirdPartyGoodsCode())
                    .goodsName(orderGoods.getGoodsName())
                    .barCode(null)
                    .currency(null)
                    .qty(new BigDecimal(orderGoods.getQuantity()))
                    .qtyUnit(null)
                    .price(orderGoods.getSellPrice())
                    .totalPrice(orderGoods.getSellPrice().multiply(new BigDecimal(orderGoods.getQuantity())))
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


    public static OpRequest convertOp(OrderBaomaDto order, TransferDefaultConfig config) {
        /***计算总的税金***/
        BigDecimal taxTotal = BigDecimal.ZERO;
        List<OrderBaomaGoods> goodsList = order.getOrderDetail();
        for (OrderBaomaGoods goods : goodsList) {
            taxTotal = taxTotal.add(goods.getDuty());
        }

        OpRequest.Builder builder = OpRequest.newBuilder().orderId(order.getOrderNo())
                .orderDate(DateUtils.formatStringToDate(order.getOrderTime(), DateUtils.Y_M_D_HMS)).packingMaterial(null).warehouseId(null)
                .tpl(null).orderType(1).orderStatus("S").customsType(1).electricCode(order.getEnterCode())
                .cbepcomCode(order.getEbpCode()).busiMode(StringUtils.isNotEmpty(order.getConfigMode()) ? Integer.valueOf(order.getConfigMode()) : null)
                .customsCode(order.getConfigCustoms()).ciqbCode(order.getCiqbCode()).stationbCode(null).deliveryCode(null).notes(null)
                .freightFcy(order.getFreight() == null ? BigDecimal.ZERO : order.getFreight()).freightFcode("CNY").insuredFee(null).insurCurr(null).insurMark(null)
                .taxFcy(taxTotal).taxFcode("CNY").otherRate(null).otherCurr(null).otherMark(null)
                .otherPayment(null).otherPaymentCurr(null).fCode(null).discount(BigDecimal.ZERO).buyerName(order.getConsigneeName())
                .buyerIdType(1).buyerIdNumber(order.getConsigneeIdCard()).buyerTelephone(order.getConsigneeTel()).buyerRegNo(order.getConsigneeTel())
                .grossWeight(null).netWeight(null).bakbCode(null).ordExcStatus(null).forSellComp(null).forSellCompName(null)
                .tradeUnitCode(null).tradeUnitName(null).shippernCode(null).shipDate(null).inputDate(null).logNots(null).blno(null).trans(null)
                .transNo(null).changeFlag(null).tradeMode(null).shipperName(null).shipperAddress(null).shipperPhone(null).agentCode(null)
                .agentName(null).payNo(order.getPayStreamNo()).payPcomName(null).payCopNo(null).opType(null).payType(null)
                .payStatus(null).payorName(null).activePayComp(null).acturalPaid(order.getTotalFee() != null ? order.getTotalFee() : BigDecimal.ZERO)
                .payCurr(null).payNots(null).payDate(null)
                .fromEplat(null).printHeader(null).commonField(null).isStoreStrategy(null).vmiFlag(null).ownerFlag(null).cutMode(null)
                .transMode(null).packNo(null).wrapType(null).sendCity(null).totalValue(BigDecimal.ZERO).goodsInfo(null).orderBatchNo(null).reDeclare(null)
                .ebptpl(null);


        OpCommonFieldDto opCommonFieldDto = OpCommonFieldDto.newBuilder().fx_kdt_id(null).fx_order_no(null).isfx(null).tid(null).platformNo(null).transaction(null).build();
        builder.commonField(JacksonUtils.toJSon(opCommonFieldDto));

        /**
         * 给默认配置
         */
        if (config != null) {
            if(StringUtils.isNotEmpty(config.getLogisticsCode())){
                builder.tpl(config.getLogisticsCode());
            }
            if(StringUtils.isNotEmpty(config.getPlatformCode())){
                builder.cbepcomCode(config.getPlatformCode());
            }
            if(StringUtils.isNotEmpty(config.getMerchantCode())){
                builder.electricCode(config.getMerchantCode());
            }
        }
        List<OpGoodsItemDto> itemDtoList = new ArrayList<>();
        for (int i = 0; i < goodsList.size(); i++) {
            OrderBaomaGoods itemVo = goodsList.get(i);
            BigDecimal decTotal = null;
            if (itemVo.getSellPrice() != null && itemVo.getQuantity() != null) {
                itemVo.getSellPrice().multiply(BigDecimal.valueOf(itemVo.getQuantity()));
            }
            OpGoodsItemDto dto = OpGoodsItemDto.newBuilder().gnum(String.valueOf(i + 1)).goodId(itemVo.getThirdPartyGoodsCode())
                    .amount(itemVo.getQuantity() != null ? itemVo.getQuantity().intValue() : null).price(itemVo.getSellPrice())
                    .goodPrice(null).copGName(null).hsCode(null).codeTs(null).decTotal(decTotal).custGoodsNo(null).ciqGoodsNo(null)
                    .batchNo(null).assemCountry(null).qtyUnit(null).spec(null).storeStrategyId(null).productionTime(null).expDate(null)
                    .ownerCode(null).brand(null).packageType(null).qty1(null).unit1(null).qty2(null).unit2(null).ggrossWt(null).build();
            itemDtoList.add(dto);
        }
        OpRecipientDto recipientDto = OpRecipientDto.newBuilder()
                .name(order.getConsigneeName()).receiveType(1).receiveNo(order.getConsigneeIdCard()).mobilePhone(order.getConsigneeTel())
                .phone(null).country("中国").province(order.getProvinceName()).city(order.getCityName()).district(order.getDistrictName())
                .address(order.getAddress()).postCode(null).totalFavourable(null).sender(null).receiver(null).congratulations(null)
                .transportDay(null).recipientProvincesCode(null).build();
        builder.goodList(itemDtoList).recipient(recipientDto);
        return builder.build();
    }

}









