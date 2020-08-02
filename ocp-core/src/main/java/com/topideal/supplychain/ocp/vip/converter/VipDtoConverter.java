package com.topideal.supplychain.ocp.vip.converter;

import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.EsdOrderSourceEnum;
import com.topideal.supplychain.ocp.esd.dto.*;
import com.topideal.supplychain.ocp.ofc.dto.OfcGoodsReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.order.model.OrderVipItem;
import com.topideal.supplychain.ocp.order.model.OrderVip;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

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

public class VipDtoConverter {

    public static OfcRequest convertOfc(OrderVip orderVip, TransferDefaultConfig config) {
        OfcOrderReqDto ofcOrderReqDto = OfcOrderReqDto.newBuilder()
                .key(null)
                .orderNo(orderVip.getOrderNo())
                .declTime(orderVip.getReceiptTime())
                .buyerRegNo(null)
                .buyerIdType("1")
                .buyerIdNumber(orderVip.getBuyerIdNumber())
                .buyerName(orderVip.getBuyerName())
                .buyerTelephone(orderVip.getBuyerTelephone())
                .receiveNo(orderVip.getBuyerIdNumber())
                .consignee(orderVip.getConsigneeCname())
                .consigneeTelephone(orderVip.getConsigneeTel())
                .pod(null)
                .consigneeAddress(orderVip.getConsigneeAddress())
                .country("中国")
                .province(orderVip.getConsigneeProvince())
                .city(orderVip.getConsigneeCity())
                .district(orderVip.getConsigneeDistrict())
                .postCode(null)
                .goodsValue(null)
                .freight(orderVip.getFreight())
                .discount(orderVip.getFavourableMoney())
                .taxTotal(orderVip.getTaxFee())
                .taxCurr("CNY")
                .acturalPaid(orderVip.getPayMount())
                .payCurr("CNY")
                .insuredFee(BigDecimal.ZERO)
                .insurCurr("CNY")
                .freightCurr("CNY")
                .fCode("CNY")
                .ebpCode(StringUtils.isNotEmpty(config.getPlatformCode()) ? config.getPlatformCode() : orderVip.getEbpCode())
                .ebpName(orderVip.getEbpName())
                .ebcCode(StringUtils.isNotEmpty(config.getMerchantCode()) ? config.getMerchantCode() : orderVip.getEbcCode())
                .ebcName(orderVip.getEbcName())
                .userId(null)
                .userName(null)
                .logisticsCode(StringUtils.isNotEmpty(config.getLogisticsCode()) ? config.getLogisticsCode() : null)
                .logisticsName(StringUtils.isNotEmpty(config.getLogisticsName()) ? config.getLogisticsName() : null)
                .logisticsNo(null)
                .logisticsNote(null)
                .payCode(null)
                .payName(null)
                .payTransactionId(null)
                .payTime(null)
                .payNote(null)
                .customsCode(orderVip.getCustomsCode() == null ? null : orderVip.getCustomsCode().getValue())
                .ciqbCode(null)
                .ieFlag("I")
                .busiMode(BusiModeEnum.BBC.getValue())
                .orderType(StringUtils.isNotEmpty(config.getOrderType()) ? Long.valueOf(config.getOrderType()) : 1L)
                .orderStatus("2".equals(orderVip.getAppType()) ? "update" : "S")
                .customsType(null)
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
                .storeCode(StringUtils.isNotEmpty(config.getWarehouseCode()) ? config.getWarehouseCode() : null)
                .platformOrderType(null)
                .orderSource(StringUtils.isNotEmpty(config.getOrderSource()) ? Long.valueOf(config.getOrderSource()) : 701L)
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
        for (OrderVipItem orderVipItem : orderVip.getMpOrderGoodsList()) {
            OfcGoodsReqDto ofcGoodsReqDto = OfcGoodsReqDto.newBuilder()
                    .gnum(String.valueOf(++num))
                    .goodsId(orderVipItem.getItemNo())
                    .goodsName(orderVipItem.getGname())
                    .barCode(orderVipItem.getBarCode())
                    .currency(null)
                    .qty(orderVipItem.getQty())
                    .qtyUnit(null)
                    .price(orderVipItem.getPrice())
                    .totalPrice(orderVipItem.getTotalPrice())
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

    /**
     * 组装ESD订单报文
     *
     * @param dto
     * @param config
     * @return
     */
    public static EsdRequest convertEsd(OrderVip dto, TransferDefaultConfig config) {
        EsdSender sender = EsdSender.newBuilder()
                .name(dto.getConsigneeCname())
                .phone(null).mobile(dto.getConsignorTel())
                .city(null)
                .zip_code(null)
                .country(dto.getCountry())
                .address(dto.getConsignorAddress()).build();

        EsdReceiver receiver = EsdReceiver.newBuilder()
                .name(dto.getConsigneeCname())
                .mobile(dto.getConsigneeTel())
                .identity_no(dto.getBuyerIdNumber())
                .country(dto.getConsigneeCountry())
                .province(dto.getConsigneeProvince())
                .city(dto.getConsigneeCity())
                .county(dto.getConsigneeDistrict())
                .address(dto.getConsigneeAddress())
                .zip_code(null).build();

        EsdBuyer buyer = EsdBuyer.newBuilder()
                .name(dto.getBuyerName())
                .phone(dto.getBuyerTelephone())
                .mobile(null)
                .id_type(dto.getBuyerIdType())
                .identity_no(dto.getBuyerIdNumber()).build();

        List<EsdServiceType> serviceTypesList = new ArrayList<>();
        EsdServiceType serviceType = EsdServiceType.newBuilder().service_type(null).build();
        serviceTypesList.add(serviceType);

        /**
         * 组装商品报文
         */
        List<EsdGood> itemList = new ArrayList<>();
        for (OrderVipItem orderVipItem : dto.getMpOrderGoodsList()) {
            EsdGood good = EsdGood.newBuilder()
                    .index(orderVipItem.getGnum() != null ? String.valueOf(orderVipItem.getGnum()) : null)
                    .item(orderVipItem.getItemNo())
                    .is_presente(null)
                    .item_name(orderVipItem.getGname())
                    .item_category(orderVipItem.getParcelTaxNo())
                    .hscode(orderVipItem.getGcode())
                    .cust_no(orderVipItem.getItemRecordNo())
                    .ciq_no(orderVipItem.getGoodsRegNo())
                    .item_quantity(Integer.valueOf(orderVipItem.getQty().toString()))
                    .price_declare(orderVipItem.getPrice())
                    .item_weight(orderVipItem.getGoodsGrossWeight())
                    .item_net_weight(orderVipItem.getGoodsNetWeight())
                    .price_code(orderVipItem.getCurrency())
                    .unit(orderVipItem.getUnit())
                    .unit1(orderVipItem.getUnit1())
                    .unit2(orderVipItem.getUnit2())
                    .qty1(orderVipItem.getQty1())
                    .qty2(orderVipItem.getQty2())
                    .brand(orderVipItem.getBrand())
                    .assem_country(orderVipItem.getOriginCountry())
                    .assem_area(orderVipItem.getOriginCountry())
                    .spec(orderVipItem.getGmodel())
                    .bar_code(orderVipItem.getBarCode())
                    .nots(orderVipItem.getNote())
                    .build();
            itemList.add(good);
        }

        BigDecimal account = BigDecimal.ZERO;
        BigDecimal payMount = dto.getPayMount();
        if (payMount == null && CollectionUtils.isNotEmpty(dto.getMpOrderGoodsList())) {
            for (OrderVipItem goods : dto.getMpOrderGoodsList()) {
                account = account.add((goods.getPrice() == null ? BigDecimal.ZERO : goods.getPrice())
                        .multiply((goods.getQty() == null ? BigDecimal.ZERO : goods.getQty())));
            }
            account = account.add(dto.getFreight() == null ? BigDecimal.ZERO : dto.getFreight()).add(dto.getInsuredFee() == null ? BigDecimal.ZERO : dto.getInsuredFee()).add(dto.getTaxFee() == null ? BigDecimal.ZERO : dto.getTaxFee()).subtract(dto.getFavourableMoney() == null ? BigDecimal.ZERO : dto.getFavourableMoney());
        } else {
            account = payMount;
        }


        return EsdRequest.newBuilder()
                .source(EsdOrderSourceEnum.VP_ORDER.getValue())
                .dno(StringUtils.isNotEmpty(config.getWarehouseCode()) ? config.getWarehouseCode() : null)
                .cp_code(StringUtils.isNotEmpty(config.getLogisticsCode()) ? config.getLogisticsCode() : dto.getLogisticsCode())
                .mail_no(dto.getLogisticsNo())
                .order_create_time(dto.getCreateTime())
                .storehouse_id(dto.getStoreId())
                .trade_no(dto.getOrderNo())
                .tid(null)
                .payment_transaction(null)
                .declare_scheme_sid("GZ-HP-BC")
                .total_code(dto.getCurrency())
                .premium_fee(dto.getInsuredFee() == null ? BigDecimal.ZERO : dto.getInsuredFee())
                .premium_code(dto.getCurrency())
                .totai_taxes_reference(dto.getTaxFee() == null ? BigDecimal.ZERO : dto.getTaxFee())
                .totai_code(dto.getCurrency())
                .discount_fee(dto.getFavourableMoney() == null ? BigDecimal.ZERO : dto.getFavourableMoney())
                .discount_code(dto.getCurrency())
                .net_weight(dto.getNetWeight())
                .itemsum_weight(dto.getGrossWeight())
                .platform(StringUtils.isNotEmpty(config.getPlatformCode()) ? config.getPlatformCode()
                        : dto.getEbpCode())
                .is_trace_source(2)
                .totai_taxes_pay(account)
                .totai_pay_code(dto.getCurrency())
                .is_tran("2")
                .total_freight(dto.getFreight() == null ? BigDecimal.ZERO : dto.getFreight())
                .totai_code("CNY")
                .premium_fee(BigDecimal.ZERO)
                .buyer(buyer)
                .receiver(receiver)
                .sender(sender)
                .serciveList(serviceTypesList)
                .itemList(itemList).build();
    }
}
