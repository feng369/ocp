package com.topideal.supplychain.ocp.pub.converter;

import com.topideal.supplychain.ocp.constants.PunctuateConstant;
import com.topideal.supplychain.ocp.enums.EsdOrderSourceEnum;
import com.topideal.supplychain.ocp.esd.dto.*;
import com.topideal.supplychain.ocp.order.model.OrderPubBox;
import com.topideal.supplychain.ocp.order.model.OrderPubItem;
import com.topideal.supplychain.ocp.order.model.OrderPub;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.CollectionUtils;

/**
 * @ClassName DtoConverter
 * @TODO dto转换器
 * @Author zhangli
 * @DATE 2019/12/19 17:38
 * @Version 1.0
 **/

public class PubDtoConverter {

    /**
     * 组装ESD订单报文
     *
     * @param dto
     * @return
     */
    public static EsdRequest convertEsd(OrderPub dto) {
        EsdSender sender = EsdSender.newBuilder()
                .name(dto.getSenderName())
                .phone(dto.getSenderPhone())
                .mobile(dto.getSenderMobile())
                .province(dto.getSenderProvince())
                .city(dto.getSenderCity())
                .county(dto.getSenderCounty())
                .zip_code(dto.getSenderZipCode())
                .country(dto.getSenderCountry())
                .address(dto.getSenderAddress()).build();

        EsdReceiver receiver = EsdReceiver.newBuilder()
                .name(dto.getReceiverName())
                .phone(dto.getReceiverPhone())
                .mobile(dto.getReceiverMobile())
                .identity_no(dto.getReceiverIdentityNo())
                .identity_no_front(dto.getReceiverIdentityNoFront())
                .identity_no_back(dto.getReceiverIdentityNoBack())
                .country(dto.getReceiverCountry())
                .province(dto.getReceiverProvince())
                .city(dto.getReceiverCity())
                .county(dto.getReceiverCounty())
                .address(dto.getReceiverAddress())
                .zip_code(dto.getReceiverZipCode()).build();

        EsdBuyer buyer = EsdBuyer.newBuilder()
                .name(dto.getBuyerName())
                .phone(dto.getBuyerPhone())
                .mobile(dto.getBuyerMobile())
                .id_type(dto.getBuyerIdType())
                .identity_no(dto.getBuyerIdentityNo()).build();

        List<EsdServiceType> serviceTypesList = new ArrayList<>();
        if (StringUtils.isNotEmpty(dto.getServiceType())) {
            String[] services = StringUtils.split(dto.getServiceType(), PunctuateConstant.COMMA);
            for (String service : services) {
                if (StringUtils.isEmpty(service)) {
                    continue;
                }
                EsdServiceType serviceType = EsdServiceType.newBuilder().service_type(Integer.valueOf(service)).build();
                serviceTypesList.add(serviceType);
            }
        }

        /**
         * 组装商品报文
         */
        List<EsdGood> itemList = new ArrayList<>();
        for (OrderPubItem orderPubItem : dto.getGoodsList()) {
            EsdGood good = EsdGood.newBuilder()
                    .index(orderPubItem.getItemNo())
                    .item(orderPubItem.getGoodsCode())
                    .is_presente(orderPubItem.getIsPresente())
                    .item_name(orderPubItem.getGoodsName())
                    .item_category(orderPubItem.getItemCategory())
                    .hscode(orderPubItem.getHsCode())
                    .cust_no(orderPubItem.getCustNo())
                    .ciq_no(orderPubItem.getCiqNo())
                    .item_quantity(Integer.valueOf(orderPubItem.getQty().toString()))
                    .price_declare(orderPubItem.getUnitPrice())
                    .item_weight(orderPubItem.getGrossWeight())
                    .item_net_weight(orderPubItem.getNetWeight())
                    .price_code(orderPubItem.getCurrency())
                    .unit(orderPubItem.getUnit())
                    .unit1(orderPubItem.getUnit1())
                    .unit2(orderPubItem.getUnit2())
                    .qty1(orderPubItem.getQty1())
                    .qty2(orderPubItem.getQty2())
                    .brand(orderPubItem.getBrand())
                    .item_tax(orderPubItem.getTax())
                    .assem_country(orderPubItem.getOriCountry())
                    .assem_area(orderPubItem.getOriArea())
                    .spec(orderPubItem.getSpec())
                    .bar_code(orderPubItem.getBarCode())
                    .nots(orderPubItem.getRemark())
                    .build();
            itemList.add(good);
        }

        return EsdRequest.newBuilder()
                .source(dto.getSource() == null ? EsdOrderSourceEnum.STD_ORDER.getValue() : dto.getSource() )
                .dno(dto.getStoreCode())
                .cp_code(dto.getLogisticsCode())
                .mail_no(dto.getLogisticsNo())
                .tran_no(dto.getTransportNo())
                .logistics_id(dto.getLogisticsId())
                .seller_id(dto.getSellerCode())
                .order_create_time(dto.getOrderCreateTime())
                .storehouse_id(dto.getWarehouseCode())
                .esdno(dto.getEsdNo())
                .trade_no(dto.getOrderNo())
                .payment_enterprise(dto.getPaymentEnterprise())
                .payment_enterprise_name(dto.getPaymentEnterpriseName())
                .tid(dto.getTid())
                .payment_transaction(dto.getPaymentTransaction())
                .payment_remark(dto.getPaymentRemark())
                .out_way_bill_url(dto.getWayBillUrl())
                .declare_scheme_sid(dto.getDeclareSchemeSid())
                .product_code(dto.getProductCode())
                .total_freight(dto.getTotalFreight())
                .total_code(dto.getTotalCode())
                .premium_fee(dto.getPremiumFee())
                .premium_code(dto.getPremiumCurrency())
                .totai_taxes_reference(dto.getTotalTaxes())
                .totai_code(dto.getTaxCurrency())
                .discount_fee(dto.getDiscountFee())
                .discount_code(dto.getDiscountCurrency())
                .net_weight(dto.getNetWeight())
                .itemsum_weight(dto.getGrossWeight())
                .bill(dto.getBillNo())
                .platform(dto.getPlatformCode())
                .is_trace_source(dto.getIsTraceSource())
                .zcode(dto.getZcode())
                .totai_taxes_pay(dto.getTotalTaxesPay())
                .totai_pay_code(dto.getTotalPayCode())
                .is_tran(StringUtils.isEmpty(dto.getIsTransfer()) ? "2" : dto.getIsTransfer())
                .destion_code(dto.getDestinationCode())
                .remark(dto.getRemark())
                .package_no(dto.getPackageNo())
                .payment_time(dto.getPaymentTime())
                .buyer(buyer)
                .receiver(receiver)
                .sender(sender)
                .serciveList(serviceTypesList)
                .itemList(itemList).build();
    }


    /**
     * 组装ESD出口订单报文
     *
     * @param dto
     * @return
     */
    public static EsdRequest convertEsdOut(OrderPub dto) {
        EsdSender sender = EsdSender.newBuilder()
                .name(dto.getSenderName())
                .phone(dto.getSenderPhone())
                .mobile(dto.getSenderMobile())
                .province(dto.getSenderProvince())
                .city(dto.getSenderCity())
                .county(dto.getSenderCounty())
                .zip_code(dto.getSenderZipCode())
                .country(dto.getSenderCountry())
                .address(dto.getSenderAddress())
                .street(dto.getSenderStreet())
                .send_num(dto.getSendNum()).build();

        EsdReceiver receiver = EsdReceiver.newBuilder()
                .name(dto.getReceiverName())
                .phone(dto.getReceiverPhone())
                .mobile(dto.getReceiverMobile())
                .identity_no(dto.getReceiverIdentityNo())
                .identity_no_front(dto.getReceiverIdentityNoFront())
                .identity_no_back(dto.getReceiverIdentityNoBack())
                .country(dto.getReceiverCountry())
                .province(dto.getReceiverProvince())
                .city(dto.getReceiverCity())
                .county(dto.getReceiverCounty())
                .address(dto.getReceiverAddress())
                .zip_code(dto.getReceiverZipCode())
                .houseno(dto.getReceiverHouseNo())
                .identity_type(dto.getIdentityType())
                .province_code(dto.getReceiverProvinceCode())
                .street(dto.getReceiverStreet())
                .address2(dto.getAddress2())
                .address3(dto.getAddress3())
                .email(dto.getReceiverEmail()).build();

        EsdBuyer buyer = EsdBuyer.newBuilder()
                .name(dto.getBuyerName())
                .phone(dto.getBuyerPhone())
                .mobile(dto.getBuyerMobile())
                .id_type(dto.getBuyerIdType())
                .identity_no(dto.getBuyerIdentityNo()).build();

        List<EsdServiceType> serviceTypesList = new ArrayList<>();
        if (StringUtils.isNotEmpty(dto.getServiceType())) {
            String[] services = StringUtils.split(dto.getServiceType(), PunctuateConstant.COMMA);
            for (String service : services) {
                if (StringUtils.isEmpty(service)) {
                    continue;
                }
                EsdServiceType serviceType = EsdServiceType.newBuilder().service_type(Integer.valueOf(service)).build();
                serviceTypesList.add(serviceType);
            }
        }

        /**
         * 组装商品报文
         */
        List<EsdGood> itemList = new ArrayList<>();
        for (OrderPubItem orderPubItem : dto.getGoodsList()) {
            EsdGood good = EsdGood.newBuilder()
                    .index(orderPubItem.getItemNo())
                    .item(orderPubItem.getGoodsCode())
                    .is_presente(orderPubItem.getIsPresente())
                    .item_name(orderPubItem.getGoodsName())
                    .item_category(orderPubItem.getItemCategory())
                    .hscode(orderPubItem.getHsCode())
                    .cust_no(orderPubItem.getCustNo())
                    .ciq_no(orderPubItem.getCiqNo())
                    .item_quantity(Integer.valueOf(orderPubItem.getQty().toString()))
                    .price_declare(orderPubItem.getUnitPrice())
                    .item_weight(orderPubItem.getGrossWeight())
                    .item_net_weight(orderPubItem.getNetWeight())
                    .price_code(orderPubItem.getCurrency())
                    .unit(orderPubItem.getUnit())
                    .unit1(orderPubItem.getUnit1())
                    .unit2(orderPubItem.getUnit2())
                    .qty1(orderPubItem.getQty1())
                    .qty2(orderPubItem.getQty2())
                    .brand(orderPubItem.getBrand())
                    .item_tax(orderPubItem.getTax())
                    .assem_country(orderPubItem.getOriCountry())
                    .assem_area(orderPubItem.getOriArea())
                    .spec(orderPubItem.getSpec())
                    .bar_code(orderPubItem.getBarCode())
                    .nots(orderPubItem.getRemark())
                    .item_enname(orderPubItem.getItemEnname())
                    .prod_use(orderPubItem.getProdUse())
                    .prod_material(orderPubItem.getProdMaterial())
                    .build();
            itemList.add(good);
        }

        /**
         * 组装商品报文
         */
        List<EsdBox> boxList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dto.getBoxList())) {
            for (OrderPubBox orderPubBox : dto.getBoxList()) {
                EsdBox box = EsdBox.newBuilder()
                        .client_height(orderPubBox.getClientHeight())
                        .client_length(orderPubBox.getClientLength())
                        .client_weight(orderPubBox.getClientWeight())
                        .client_width(orderPubBox.getClientWidth())
                        .number(orderPubBox.getNumber())
                        .build();
                boxList.add(box);
            }
        }

        return EsdRequest.newBuilder()
                //.source(EsdOrderSourceEnum.STD_ORDER.getValue())
                .dno(dto.getStoreCode())
                .cp_code(dto.getLogisticsCode())
                .mail_no(dto.getLogisticsNo())
                .tran_no(dto.getTransportNo())
                .logistics_id(dto.getLogisticsId())
                .seller_id(dto.getSellerCode())
                .order_create_time(dto.getOrderCreateTime())
                .storehouse_id(dto.getWarehouseCode())
                .esdno(dto.getEsdNo())
                .trade_no(dto.getOrderNo())
                .payment_enterprise(dto.getPaymentEnterprise())
                .payment_enterprise_name(dto.getPaymentEnterpriseName())
                .tid(dto.getTid())
                .payment_transaction(dto.getPaymentTransaction())
                .payment_remark(dto.getPaymentRemark())
                .out_way_bill_url(dto.getWayBillUrl())
                .declare_scheme_sid(dto.getDeclareSchemeSid())
                .product_code(dto.getProductCode())
                .total_freight(dto.getTotalFreight())
                .total_code(dto.getTotalCode())
                .premium_fee(dto.getPremiumFee())
                .premium_code(dto.getPremiumCurrency())
                .totai_taxes_reference(dto.getTotalTaxes())
                .totai_code(dto.getTaxCurrency())
                .discount_fee(dto.getDiscountFee())
                .discount_code(dto.getDiscountCurrency())
                .net_weight(dto.getNetWeight())
                .itemsum_weight(dto.getGrossWeight())
                .bill(dto.getBillNo())
                .platform(dto.getPlatformCode())
                .is_trace_source(dto.getIsTraceSource())
                .zcode(dto.getZcode())
                .totai_taxes_pay(dto.getTotalTaxesPay())
                .totai_pay_code(dto.getTotalPayCode())
                //.is_tran(StringUtils.isEmpty(dto.getIsTransfer()) ? "2" : dto.getIsTransfer())
                //.destion_code(dto.getDestinationCode())
                .remark(dto.getRemark())
                .ieflag(dto.getIeFlag())
                .package_type(dto.getPackageType())
                .parcel_count(dto.getParcelCount())
                .order_code(dto.getOrderCode())
                .length(dto.getLength())
                .width(dto.getWidth())
                .height(dto.getHeight())
                .taxId(dto.getTaxId())
                .total_package_no(dto.getTotalPackageNo())
                .package_kind(dto.getPackageKind())
                .isreturn(dto.getIsReturn())
                .delayed_time(dto.getDelayedTime())
                .package_no(dto.getPackageNo())
                .payment_time(dto.getPaymentTime())
                .buyer(buyer)
                .receiver(receiver)
                .sender(sender)
                .serciveList(serviceTypesList)
                .itemList(itemList)
                .boxList(boxList).build();
    }
}
