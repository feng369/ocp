package com.topideal.supplychain.ocp.distribution.converter;

import com.google.common.collect.Lists;
import com.topideal.supplychain.ocp.big.dto.BigGoodsRequestDto;
import com.topideal.supplychain.ocp.big.dto.BigReceiveRequestDto;
import com.topideal.supplychain.ocp.big.dto.BigRecipientRequestDto;
import com.topideal.supplychain.ocp.config.dto.FxDefaultConfig;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.ew.dto.EwFxGoods;
import com.topideal.supplychain.ocp.ew.dto.EwFxRecipient;
import com.topideal.supplychain.ocp.ew.dto.EwFxRequest;
import com.topideal.supplychain.ocp.kjb.dto.KjbFxGoods;
import com.topideal.supplychain.ocp.kjb.dto.KjbFxRecipient;
import com.topideal.supplychain.ocp.kjb.dto.KjbFxRequest;
import com.topideal.supplychain.ocp.ofc.dto.OfcGoodsReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.order.model.OrderAmway;
import com.topideal.supplychain.ocp.order.model.OrderAmwayItem;
import com.topideal.supplychain.ocp.order.model.OrderDistribution;
import com.topideal.supplychain.ocp.order.model.OrderDistributionItem;
import com.topideal.supplychain.ocp.order.model.OrderDxyItem;
import com.topideal.supplychain.ocp.service.SystemConfigService;
import com.topideal.supplychain.util.AES256Util;
import com.topideal.supplychain.util.DateUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

public class FxDtoConverter {


    public static OfcRequest convertOfc(OrderDistribution dto, List<OrderDistributionItem> itemList,
            FxDefaultConfig defaultConfig) {

        OfcOrderReqDto.Builder build = OfcOrderReqDto.newBuilder()
                .key(null)
                .orderNo(dto.getOrderId())
                .declTime(dto.getOrderDate())
                .buyerRegNo(dto.getBuyerRegNo())
                .buyerIdType(dto.getBuyerIdType().getValue())
                .buyerName(dto.getBuyerName())
                .buyerIdNumber(StringUtils.isNotBlank(dto.getBuyerIdNumber()) ? AES256Util
                        .decrypt(dto.getBuyerIdNumber()) : null)
                .buyerTelephone(StringUtils.isNotBlank(dto.getBuyerTelephone()) ? AES256Util
                        .decrypt(dto.getBuyerTelephone()) : null)
                .shipperPhone(StringUtils.isNotBlank(dto.getShipperPhone()) ? AES256Util
                        .decrypt(dto.getShipperPhone()) : null)
                .receiveNo(StringUtils.isNotBlank(dto.getReceiverIdNumber()) ? AES256Util
                        .decrypt(dto.getReceiverIdNumber()) : null)
                .consignee(dto.getReceiverName())
                .consigneeTelephone(StringUtils.isNotBlank(dto.getReceiverMobile()) ? AES256Util
                        .decrypt(dto.getReceiverMobile()) : null)
                .consigneeAddress(dto.getAddress())
                .country(dto.getCountry())
                .province(dto.getProvince())
                .city(dto.getCity())
                .district(dto.getDistrict())
                .postCode(dto.getPostCode())
                .goodsValue(null)
                .freight(dto.getFreight().add(dto.getInsuredFee()).add(dto.getOtherRate()))
                .discount(dto.getDiscount())
                .taxTotal(dto.getTax())
                .taxCurr(dto.getTaxCurrency())
                .acturalPaid(dto.getActuralPaid())
                .payCurr(dto.getPayCurr())
                .insuredFee(dto.getInsuredFee())
                .insurCurr(dto.getInsurCurr())
                .freightCurr(dto.getFreightCurrency())
                .fCode(dto.getFcyCurrency())
                .ebpCode(dto.getEbpCode())
                .ebcCode(dto.getEbcCode())
                .customsCode(dto.getCustomsCode().getValue())
                .logisticsCode(dto.getTpl())
                .logisticsNo(dto.getLogisticsNo())
                .payCode(dto.getPayCopNo())
                .payName(dto.getPayPcomName())
                .payTransactionId(dto.getPayNo())
                .payTime(dto.getPayDate())
                .ciqbCode(dto.getCiqbCode())
                .ieFlag("I")
                .busiMode(String.valueOf(dto.getBusiMode()))
                .orderType(Long.valueOf(dto.getOrderType().getValue()))
                .orderStatus(dto.getOrderStatus().getValue())
                .packNo(1L)
                .customsType(Long.valueOf(dto.getCustomsType().getValue()))
                .orderSource(Long.valueOf(defaultConfig.getOrderSource()))
                .storeCode(dto.getWarehouseId())
                .grossWeight(dto.getGrossWeight())
                .netWeight(dto.getNetWeight())
                .tradeMode(dto.getTradeMode());
        OfcOrderReqDto ofcOrderReqDto = build.build();

        List<OfcGoodsReqDto> list = Lists.newArrayList();
        for (int i = 0; i < itemList.size(); i++) {
            OrderDistributionItem item = itemList.get(i);

            OfcGoodsReqDto ofcGoodsReqDto = OfcGoodsReqDto.newBuilder()
                    .gnum(String.valueOf(i + 1))
                    .goodsId(item.getGoodId())
                    //.goodsName(item.getOrderingItemName())
                    //.currency("CNY")
                    .price(item.getPrice())
                    .qty(new BigDecimal(item.getAmount()))
                    .totalPrice(item.getDecTotal())
                    .ebcCode(null)
                    .inventoryType(null)
                    .itemVersion(null)
                    //.udf1(item.getOrderingItemNumber())
                    //.udf2(item.getMixLogisticsCode())
                    //.udf3(item.getMixLogisticsName())
                    .barCode(item.getBarCode())
                    .qtyUnit(null)
                    .batchNo(item.getBatchNo())
                    .storeStrategyId(null)
                    .productionTime(null)
                    .expDate(null)
                    .virtualOwner(null)
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
     * 跨境宝分销下发接口字段跟分销接单接口一致
     */
    public static EwFxRequest convertEw(OrderDistribution orderDistribution,
            List<OrderDistributionItem> itemList) {
        // 商品信息
        List<EwFxGoods> goodList = new ArrayList<>();
        for (OrderDistributionItem item : itemList) {
            //BigGoodsRequestDto itemDto = new BigGoodsRequestDto()
            //BeanUtils.copyProperties(item, itemDto)
            EwFxGoods ewFxGoods = EwFxGoods.newBuilder()
                    .gnum(item.getGnum())
                    .goodId(item.getGoodId())
                    .barCode(item.getBarCode())
                    .amount(item.getAmount())
                    .price(item.getPrice())
                    .goodPrice(item.getGoodPrice())
                    .copGName(item.getCopGName())
                    .hsCode(item.getHsCode())
                    .decTotal(item.getDecTotal())
                    .notes(item.getNotes())
                    .custGoodsNo(item.getCustGoodsNo())
                    .ciqGoodsNo(item.getCiqGoodsNo())
                    .batchNo(item.getBatchNo())
                    .assemCountry(item.getAssemCountry())
                    .ciqAssemCountry(item.getCiqAssemCountry())
                    .qtyUnit(item.getQtyUnit())
                    .spec(item.getSpec())
                    .storeStrategyId(item.getStoreStrategyId())
                    .ownerCode(item.getOwnerCode())
                    .virtualOwner(item.getVirtualOwner())
                    .codeTs(item.getCodeTs())
                    .brand(item.getBrand())
                    .packageType(item.getPackageType())
                    .qty1(item.getQty1())
                    .unit1(item.getUnit1())
                    .qty2(item.getQty2())
                    .unit2(item.getUnit2())
                    .grossWeight(item.getGrossWeight())
                    .currency(item.getCurrency())
                    .netWeight(item.getNetWeight())
                    .productionTime(DateUtils.dateToString(item.getProductionTime(), DateUtils.DATETIME_PATTERN))
                    .expDate(DateUtils.dateToString(item.getExpDate(), DateUtils.DATETIME_PATTERN))
                    .gQty1(null == item.getgQty1() ? "" : String.valueOf(item.getgQty1()))
                    .gQty2(null == item.getgQty2() ? "" : String.valueOf(item.getgQty2()))
                    .isGoodsRecord(String.valueOf(item.getIsGoodsRecord().getValue()))
                    .build();
            goodList.add(ewFxGoods);
        }

        // 收货人信息
        EwFxRecipient ewFxRecipient = EwFxRecipient.newBuilder()
            .name(orderDistribution.getReceiverName())
            .receiveType(Integer.valueOf(orderDistribution.getReceiverIdType().getValue()))
            .receiveNo(StringUtils.isNotBlank(orderDistribution.getReceiverIdNumber()) ? AES256Util
                .decrypt(orderDistribution.getReceiverIdNumber()) : null)
            .phone(orderDistribution.getReceiverPhone())
            .country(orderDistribution.getCountry())
            .province(orderDistribution.getProvince())
            .city(orderDistribution.getCity())
            .district(orderDistribution.getDistrict())
            .address(orderDistribution.getAddress())
            .postCode(orderDistribution.getPostCode())
            .totalFavourable(orderDistribution.getTotalFavourable())
            .sender(orderDistribution.getSender())
            .receiver(orderDistribution.getReceiver())
            .congratulations(orderDistribution.getCongratulations())
            .transportDay(orderDistribution.getTransportDay())
            .recipientProvincesCode(orderDistribution.getRecipientProvincesCode())
            .pod(orderDistribution.getPod())
            .mobilePhone(StringUtils.isNotBlank(orderDistribution.getReceiverMobile()) ? AES256Util
                .decrypt(orderDistribution.getReceiverMobile()) : null)
                .build();

        return EwFxRequest.newBuilder()
                .orderId(orderDistribution.getOrderId())
                //.orderDate(orderDistribution.getOrderDate())
                .packingMaterial(orderDistribution.getPackingMaterial())
                .warehouseId(orderDistribution.getWarehouseId())
                .tpl(orderDistribution.getTpl())
                //.orderType(orderDistribution.getOrderType())
                //.customsType(orderDistribution.getCustomsType())
                //.electricCode(orderDistribution.getElectricCode())
                //.cbepcomCode(orderDistribution.getCbepcomCode())
                .deliveryCode(orderDistribution.getDeliveryCode())
                .notes(orderDistribution.getNotes())
                //.freightFcy(orderDistribution.getFreightFcy())
                //.freightFcode(orderDistribution.getFreightFcode())
                .insuredFee(orderDistribution.getInsuredFee())
                //.taxFcy(orderDistribution.getTaxFcy())
                .discount(orderDistribution.getDiscount())
                .buyerName(orderDistribution.getBuyerName())
                //.buyerIdType(orderDistribution.getBuyerIdType())
                //.buyerIdNumber(orderDistribution.getBuyerIdNumber())
                //.buyerTelephone(orderDistribution.getBuyerTelephone())
                .buyerRegNo(orderDistribution.getBuyerRegNo())
                //.grossWeight(orderDistribution.getGrossWeight())
                //.netWeight(orderDistribution.getNetWeight())
                //.orderStatus(orderDistribution.getOrderStatus())
                //.busiMode(orderDistribution.getBusiMode())
                //.customsCode(orderDistribution.getCustomsCode())
                .ciqbCode(orderDistribution.getCiqbCode())
                .stationbCode(orderDistribution.getStationbCode())
                .statisticsFlag(orderDistribution.getStatisticsFlag())
                .bakbCode(orderDistribution.getBakbCode())
                .logisticsNo(orderDistribution.getLogisticsNo())
                .ordExcStatus(orderDistribution.getOrdExcStatus())
                .forSellComp(orderDistribution.getForSellComp())
                .forSellCompName(orderDistribution.getForSellCompName())
                .tradeUnitCode(orderDistribution.getTradeUnitCode())
                .tradeUnitName(orderDistribution.getTradeUnitName())
                .shippernCode(orderDistribution.getShippernCode())
                //.fCode(orderDistribution.getFCode())
                //.taxFcode(orderDistribution.getTaxFcode())
                //.payDate(orderDistribution.getPayDate())
                //.shipDate(orderDistribution.getShipDate())
                //.inputDate(orderDistribution.getInputDate())
                //.payNots(orderDistribution.getPayNots())
                .logNots(orderDistribution.getLogNots())
                .blno(orderDistribution.getBlno())
                .trans(orderDistribution.getTrans())
                .transNo(orderDistribution.getTransNo())
                .changeFlag(orderDistribution.getChangeFlag())
                .reDeclare(orderDistribution.getReDeclare())
                .tradeMode(orderDistribution.getTradeMode())
                .shipperName(orderDistribution.getShipperName())
                .shipperAddress(orderDistribution.getShipperAddress())
                //.shipperPhone(orderDistribution.getShipperPhone())
                .insurCurr(orderDistribution.getInsurCurr())
                .insurMark(orderDistribution.getInsurMark())
                .otherCurr(orderDistribution.getOtherCurr())
                .otherRate(orderDistribution.getOtherRate())
                .otherMark(orderDistribution.getOtherMark())
                .otherPaymentCurr(orderDistribution.getOtherPaymentCurr())
                .otherPayment(orderDistribution.getOtherPayment())
                .payNo(orderDistribution.getPayNo())
                .payPcomName(orderDistribution.getPayPcomName())
                .payCopNo(orderDistribution.getPayCopNo())
                .agentCode(orderDistribution.getAgentCode())
                .agentName(orderDistribution.getAgentName())
                .opType(orderDistribution.getOpType())
                .payType(orderDistribution.getPayType())
                .payStatus(orderDistribution.getPayStatus())
                .payCurr(orderDistribution.getPayCurr())
                .payorName(orderDistribution.getPayorName())
                .activePayComp(orderDistribution.getActivePayComp())
                .acturalPaid(orderDistribution.getActuralPaid())
                //.fromEplat(orderDistribution.getFromEplat())
                .printHeader(orderDistribution.getPrintHeader())
                .commonField(orderDistribution.getCommonField())
                //.isStoreStrategy(orderDistribution.getIsStoreStrategy())
                //.vmiFlag(orderDistribution.getVmiFlag())
                //.ownerFlag(orderDistribution.getOwnerFlag())
                .cutMode(orderDistribution.getCutMode())
                .transMode(orderDistribution.getTransMode())
                .packNo(orderDistribution.getPackNo())
                .wrapType(orderDistribution.getWrapType())
                .sendCity(orderDistribution.getSendCity())
                .totalValue(orderDistribution.getTotalValue())
                .goodsInfo(orderDistribution.getGoodsInfo())
                .orderBatchNo(orderDistribution.getOrderBatchNo())
                .shopId(orderDistribution.getShopId())

                .orderDate(DateUtils.dateToString(orderDistribution.getOrderDate(), DateUtils.DATETIME_PATTERN))
                .inputDate(DateUtils.dateToString(orderDistribution.getInputDate(), DateUtils.DATETIME_PATTERN))
                .payDate(DateUtils.dateToString(orderDistribution.getPayDate(), DateUtils.DATETIME_PATTERN))
                .shipDate(DateUtils.dateToString(orderDistribution.getShipDate(), DateUtils.DATETIME_PATTERN))
                .cbepcomCode(orderDistribution.getEbpCode())
                .electricCode(orderDistribution.getEbcCode())
                // 枚举类型
                .orderType(orderDistribution.getOrderType().getValue())
                .customsType(orderDistribution.getCustomsType().getValue())
                /*.recipient(orderDistribution.getRecipient())
                .goodList(orderDistribution.getGoodList())*/

                .freightFcy(orderDistribution.getFreight())
                .taxFcy(orderDistribution.getTax())
                .fCode(orderDistribution.getFcyCurrency())
                .freightFcode(orderDistribution.getFreightCurrency())
                .taxFcode(orderDistribution.getTaxCurrency())


                .orderStatus(orderDistribution.getOrderStatus().getValue())
                .buyerIdType(Integer.valueOf(orderDistribution.getBuyerIdType().getValue()))

                .customsCode(orderDistribution.getCustomsCode().getValue())
                .busiMode(orderDistribution.getBusiMode())
                .fromEplat(orderDistribution.getFromEplat().getValue())
                .isStoreStrategy(orderDistribution.getIsStoreStrategy().getValue())
                .ownerFlag(orderDistribution.getOwnerFlag().getValue())
                .vmiFlag(orderDistribution.getVmiFlag().getValue())
                .netWeight(orderDistribution.getNetWeight())
                .grossWeight(orderDistribution.getGrossWeight())
                // 解密私密信息
                .buyerIdNumber(StringUtils.isNotBlank(orderDistribution.getBuyerIdNumber()) ? AES256Util
                    .decrypt(orderDistribution.getBuyerIdNumber()) : null)
                .buyerTelephone(StringUtils.isNotBlank(orderDistribution.getBuyerTelephone()) ? AES256Util
                    .decrypt(orderDistribution.getBuyerTelephone()) : null)
                .shipperPhone(StringUtils.isNotBlank(orderDistribution.getShipperPhone()) ? AES256Util
                    .decrypt(orderDistribution.getShipperPhone()) : null)
                .payNots(orderDistribution.getPayNotes())
                .recipient(ewFxRecipient)
                .goodList(goodList)
                .build();

    }



    /**
     * 跨境宝分销下发接口字段跟分销接单接口一致
     */
    public static KjbFxRequest convertKjb(OrderDistribution orderDistribution,
            List<OrderDistributionItem> itemList) {
        //BeanUtils.copyProperties(orderDistribution, requestDto);

        // 商品信息
        List<KjbFxGoods> goodList = new ArrayList<>();
        for (OrderDistributionItem item : itemList) {
            //BigGoodsRequestDto itemDto = new BigGoodsRequestDto()
            //BeanUtils.copyProperties(item, itemDto)
            KjbFxGoods kjbFxGoods = KjbFxGoods.newBuilder()
                    .gnum(item.getGnum())
                    .goodId(item.getGoodId())
                    .barCode(item.getBarCode())
                    .amount(item.getAmount())
                    .price(item.getPrice())
                    .goodPrice(item.getGoodPrice())
                    .copGName(item.getCopGName())
                    .hsCode(item.getHsCode())
                    .decTotal(item.getDecTotal())
                    .notes(item.getNotes())
                    .custGoodsNo(item.getCustGoodsNo())
                    .ciqGoodsNo(item.getCiqGoodsNo())
                    .batchNo(item.getBatchNo())
                    .assemCountry(item.getAssemCountry())
                    .ciqAssemCountry(item.getCiqAssemCountry())
                    .qtyUnit(item.getQtyUnit())
                    .spec(item.getSpec())
                    .storeStrategyId(item.getStoreStrategyId())
                    .ownerCode(item.getOwnerCode())
                    .virtualOwner(item.getVirtualOwner())
                    .codeTs(item.getCodeTs())
                    .brand(item.getBrand())
                    .packageType(item.getPackageType())
                    .qty1(item.getQty1())
                    .unit1(item.getUnit1())
                    .qty2(item.getQty2())
                    .unit2(item.getUnit2())
                    .grossWeight(item.getGrossWeight())
                    .currency(item.getCurrency())
                    .netWeight(item.getNetWeight())
                    .productionTime(DateUtils.dateToString(item.getProductionTime(), DateUtils.DATETIME_PATTERN))
                    .expDate(DateUtils.dateToString(item.getExpDate(), DateUtils.DATETIME_PATTERN))
                    .gQty1(null == item.getgQty1() ? "" : String.valueOf(item.getgQty1()))
                    .gQty2(null == item.getgQty2() ? "" : String.valueOf(item.getgQty2()))
                    .isGoodsRecord(String.valueOf(item.getIsGoodsRecord().getValue()))
                    .build();
            goodList.add(kjbFxGoods);
        }

        // 收货人信息
        KjbFxRecipient kjbFxRecipient = KjbFxRecipient.newBuilder()
                .name(orderDistribution.getReceiverName())
                .receiveType(Integer.valueOf(orderDistribution.getReceiverIdType().getValue()))
                .receiveNo(StringUtils.isNotBlank(orderDistribution.getReceiverIdNumber()) ? AES256Util
                        .decrypt(orderDistribution.getReceiverIdNumber()) : null)
                .phone(orderDistribution.getReceiverPhone())
                .country(orderDistribution.getCountry())
                .province(orderDistribution.getProvince())
                .city(orderDistribution.getCity())
                .district(orderDistribution.getDistrict())
                .address(orderDistribution.getAddress())
                .postCode(orderDistribution.getPostCode())
                .totalFavourable(orderDistribution.getTotalFavourable())
                .sender(orderDistribution.getSender())
                .receiver(orderDistribution.getReceiver())
                .congratulations(orderDistribution.getCongratulations())
                .transportDay(orderDistribution.getTransportDay())
                .recipientProvincesCode(orderDistribution.getRecipientProvincesCode())
                .pod(orderDistribution.getPod())
                .mobilePhone(StringUtils.isNotBlank(orderDistribution.getReceiverMobile()) ? AES256Util
                        .decrypt(orderDistribution.getReceiverMobile()) : null)
                .build();

        return KjbFxRequest.newBuilder()
                .orderId(orderDistribution.getOrderId())
                //.orderDate(orderDistribution.getOrderDate())
                .packingMaterial(orderDistribution.getPackingMaterial())
                .warehouseId(orderDistribution.getWarehouseId())
                .tpl(orderDistribution.getTpl())
                //.orderType(orderDistribution.getOrderType())
                //.customsType(orderDistribution.getCustomsType())
                //.electricCode(orderDistribution.getElectricCode())
                //.cbepcomCode(orderDistribution.getCbepcomCode())
                .deliveryCode(orderDistribution.getDeliveryCode())
                .notes(orderDistribution.getNotes())
                //.freightFcy(orderDistribution.getFreightFcy())
                //.freightFcode(orderDistribution.getFreightFcode())
                .insuredFee(orderDistribution.getInsuredFee())
                //.taxFcy(orderDistribution.getTaxFcy())
                .discount(orderDistribution.getDiscount())
                .buyerName(orderDistribution.getBuyerName())
                //.buyerIdType(orderDistribution.getBuyerIdType())
                //.buyerIdNumber(orderDistribution.getBuyerIdNumber())
                //.buyerTelephone(orderDistribution.getBuyerTelephone())
                .buyerRegNo(orderDistribution.getBuyerRegNo())
                //.grossWeight(orderDistribution.getGrossWeight())
                //.netWeight(orderDistribution.getNetWeight())
                //.orderStatus(orderDistribution.getOrderStatus())
                //.busiMode(orderDistribution.getBusiMode())
                //.customsCode(orderDistribution.getCustomsCode())
                .ciqbCode(orderDistribution.getCiqbCode())
                .stationbCode(orderDistribution.getStationbCode())
                .statisticsFlag(orderDistribution.getStatisticsFlag())
                .bakbCode(orderDistribution.getBakbCode())
                .logisticsNo(orderDistribution.getLogisticsNo())
                .ordExcStatus(orderDistribution.getOrdExcStatus())
                .forSellComp(orderDistribution.getForSellComp())
                .forSellCompName(orderDistribution.getForSellCompName())
                .tradeUnitCode(orderDistribution.getTradeUnitCode())
                .tradeUnitName(orderDistribution.getTradeUnitName())
                .shippernCode(orderDistribution.getShippernCode())
                //.fCode(orderDistribution.getFCode())
                //.taxFcode(orderDistribution.getTaxFcode())
                //.payDate(orderDistribution.getPayDate())
                //.shipDate(orderDistribution.getShipDate())
                //.inputDate(orderDistribution.getInputDate())
                //.payNots(orderDistribution.getPayNots())
                .logNots(orderDistribution.getLogNots())
                .blno(orderDistribution.getBlno())
                .trans(orderDistribution.getTrans())
                .transNo(orderDistribution.getTransNo())
                .changeFlag(orderDistribution.getChangeFlag())
                .reDeclare(orderDistribution.getReDeclare())
                .tradeMode(orderDistribution.getTradeMode())
                .shipperName(orderDistribution.getShipperName())
                .shipperAddress(orderDistribution.getShipperAddress())
                //.shipperPhone(orderDistribution.getShipperPhone())
                .insurCurr(orderDistribution.getInsurCurr())
                .insurMark(orderDistribution.getInsurMark())
                .otherCurr(orderDistribution.getOtherCurr())
                .otherRate(orderDistribution.getOtherRate())
                .otherMark(orderDistribution.getOtherMark())
                .otherPaymentCurr(orderDistribution.getOtherPaymentCurr())
                .otherPayment(orderDistribution.getOtherPayment())
                .payNo(orderDistribution.getPayNo())
                .payPcomName(orderDistribution.getPayPcomName())
                .payCopNo(orderDistribution.getPayCopNo())
                .agentCode(orderDistribution.getAgentCode())
                .agentName(orderDistribution.getAgentName())
                .opType(orderDistribution.getOpType())
                .payType(orderDistribution.getPayType())
                .payStatus(orderDistribution.getPayStatus())
                .payCurr(orderDistribution.getPayCurr())
                .payorName(orderDistribution.getPayorName())
                .activePayComp(orderDistribution.getActivePayComp())
                .acturalPaid(orderDistribution.getActuralPaid())
                //.fromEplat(orderDistribution.getFromEplat())
                .printHeader(orderDistribution.getPrintHeader())
                .commonField(orderDistribution.getCommonField())
                //.isStoreStrategy(orderDistribution.getIsStoreStrategy())
                //.vmiFlag(orderDistribution.getVmiFlag())
                //.ownerFlag(orderDistribution.getOwnerFlag())
                .cutMode(orderDistribution.getCutMode())
                .transMode(orderDistribution.getTransMode())
                .packNo(orderDistribution.getPackNo())
                .wrapType(orderDistribution.getWrapType())
                .sendCity(orderDistribution.getSendCity())
                .totalValue(orderDistribution.getTotalValue())
                .goodsInfo(orderDistribution.getGoodsInfo())
                .orderBatchNo(orderDistribution.getOrderBatchNo())
                .shopId(orderDistribution.getShopId())

                .orderDate(DateUtils.dateToString(orderDistribution.getOrderDate(), DateUtils.DATETIME_PATTERN))
                .inputDate(DateUtils.dateToString(orderDistribution.getInputDate(), DateUtils.DATETIME_PATTERN))
                .payDate(DateUtils.dateToString(orderDistribution.getPayDate(), DateUtils.DATETIME_PATTERN))
                .shipDate(DateUtils.dateToString(orderDistribution.getShipDate(), DateUtils.DATETIME_PATTERN))
                .cbepcomCode(orderDistribution.getEbpCode())
                .electricCode(orderDistribution.getEbcCode())
                // 枚举类型
                .orderType(orderDistribution.getOrderType().getValue())
                .customsType(orderDistribution.getCustomsType().getValue())
                /*.recipient(orderDistribution.getRecipient())
                .goodList(orderDistribution.getGoodList())*/

                .freightFcy(orderDistribution.getFreight())
                .taxFcy(orderDistribution.getTax())
                .fCode(orderDistribution.getFcyCurrency())
                .freightFcode(orderDistribution.getFreightCurrency())
                .taxFcode(orderDistribution.getTaxCurrency())


                .orderStatus(orderDistribution.getOrderStatus().getValue())
                .buyerIdType(Integer.valueOf(orderDistribution.getBuyerIdType().getValue()))

                .customsCode(orderDistribution.getCustomsCode().getValue())
                .busiMode(orderDistribution.getBusiMode())
                .fromEplat(orderDistribution.getFromEplat().getValue())
                .isStoreStrategy(orderDistribution.getIsStoreStrategy().getValue())
                .ownerFlag(orderDistribution.getOwnerFlag().getValue())
                .vmiFlag(orderDistribution.getVmiFlag().getValue())
                .netWeight(orderDistribution.getNetWeight())
                .grossWeight(orderDistribution.getGrossWeight())
                // 解密私密信息
                .buyerIdNumber(StringUtils.isNotBlank(orderDistribution.getBuyerIdNumber()) ? AES256Util
                        .decrypt(orderDistribution.getBuyerIdNumber()) : null)
                .buyerTelephone(StringUtils.isNotBlank(orderDistribution.getBuyerTelephone()) ? AES256Util
                        .decrypt(orderDistribution.getBuyerTelephone()) : null)
                .shipperPhone(StringUtils.isNotBlank(orderDistribution.getShipperPhone()) ? AES256Util
                        .decrypt(orderDistribution.getShipperPhone()) : null)
                .payNots(orderDistribution.getPayNotes())
                .recipient(kjbFxRecipient)
                .goodList(goodList)
                .build();

    }
}
