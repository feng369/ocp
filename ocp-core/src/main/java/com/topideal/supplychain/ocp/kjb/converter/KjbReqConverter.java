package com.topideal.supplychain.ocp.kjb.converter;

import com.topideal.supplychain.ocp.enums.EsdOrderSourceEnum;
import com.topideal.supplychain.ocp.esd.dto.*;
import com.topideal.supplychain.ocp.order.model.OrderKjb;
import com.topideal.supplychain.ocp.order.model.OrderKjbItem;
import com.topideal.supplychain.util.AES256Util;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @description: 跨境宝订单接口请求报文转换器
 * @author: syq
 * @create: 2019-12-17 17:10
 **/
public class KjbReqConverter {

    /**
     * 订单发送esd请求报文
     * @param orderKjb
     * @param orderKjbItemList
     * @return
     */
    public static EsdRequest convertEsd(OrderKjb orderKjb, List<OrderKjbItem> orderKjbItemList) {

        //发货人信息
        EsdSender esdSender = EsdSender.newBuilder()
                .name(StringUtils.isNotEmpty(orderKjb.getSenderName()) ? AES256Util.decrypt(orderKjb.getSenderName()) : null)
                .phone(StringUtils.isNotEmpty(orderKjb.getSenderPhone()) ? AES256Util.decrypt(orderKjb.getSenderPhone()) : null)
                .mobile(StringUtils.isNotEmpty(orderKjb.getSenderMobile()) ? AES256Util.decrypt(orderKjb.getSenderMobile()) : null)
                .country(orderKjb.getSenderCountry())
                .province(orderKjb.getSenderProvince())
                .city(orderKjb.getSenderCity())
                .county(orderKjb.getSenderDistrict())
                .address(StringUtils.isNotEmpty(orderKjb.getSenderAddress()) ? AES256Util.decrypt(orderKjb.getSenderAddress()) : null)
                .zip_code(orderKjb.getSenderZip())
                .build();

        //收货人信息
        EsdReceiver esdReceiver = EsdReceiver.newBuilder()
                .name(StringUtils.isNotEmpty(orderKjb.getReceiverName()) ? AES256Util.decrypt(orderKjb.getReceiverName()) : null)
                .phone(StringUtils.isNotEmpty(orderKjb.getReceiverPhone()) ? AES256Util.decrypt(orderKjb.getReceiverPhone()) : null)
                .mobile(StringUtils.isNotEmpty(orderKjb.getReceiverMobile()) ? AES256Util.decrypt(orderKjb.getReceiverMobile()) : null)
                .identity_no(StringUtils.isNotEmpty(orderKjb.getReceiverIdentityNo()) ? AES256Util.decrypt(orderKjb.getReceiverIdentityNo()) : null)
                .identity_no_back(orderKjb.getReceiverIdentityBackUrl())
                .identity_no_front(orderKjb.getReceiverIdentityFrontUrl())
                .country(orderKjb.getReceiverCountry())
                .province(orderKjb.getReceiverProvince())
                .city(orderKjb.getReceiverCity())
                .county(orderKjb.getReceiverDistrict())
                .address(StringUtils.isNotEmpty(orderKjb.getReceiverAddress()) ? AES256Util.decrypt(orderKjb.getReceiverAddress()) : null)
                .zip_code(orderKjb.getReceiverZip())
                .build();

        //购买人信息
        EsdBuyer esdBuyer = EsdBuyer.newBuilder()
                .name(StringUtils.isNotEmpty(orderKjb.getBuyerName()) ? AES256Util.decrypt(orderKjb.getBuyerName()) : null)
                .phone(StringUtils.isNotEmpty(orderKjb.getBuyerPhone()) ? AES256Util.decrypt(orderKjb.getBuyerPhone()) : null)
                .mobile(orderKjb.getBuyerMobile())
                .id_type(orderKjb.getBuyerIdType())
                .identity_no(StringUtils.isNotEmpty(orderKjb.getBuyerIdentityNo()) ? AES256Util.decrypt(orderKjb.getBuyerIdentityNo()) : null)
                .build();

        //服务类型
        List<EsdServiceType> esdServiceTypeList = new ArrayList<>();
        List<String> serviceTypeList = Arrays.asList(orderKjb.getServiceType().split(","));
        for (String serviceType : serviceTypeList) {
            EsdServiceType esdServiceType = EsdServiceType.newBuilder()
                    .service_type(Integer.valueOf(serviceType))
                    .build();
            esdServiceTypeList.add(esdServiceType);
        }

        //订单明细信息
        List<EsdGood> esdGoodList = new ArrayList<>();
        for (OrderKjbItem item : orderKjbItemList) {
            EsdGood esdGood = EsdGood.newBuilder()
                    .index(item.getItemNo())
                    .item(item.getGoodsCode())
                    .is_presente(item.getIsPresente().getValue())
                    .item_name(item.getGoodsName())
                    .item_category(item.getCategoryNo())
                    .hscode(item.getHscode())
                    .cust_no(item.getCustNo())
                    .ciq_no(item.getCiqNo())
                    .item_quantity(item.getQty())
                    .item_net_weight(item.getNetWeight())
                    .item_weight(item.getGrossWeight())
                    .price_declare(item.getPrice())
                    .price_code(item.getPriceCurrency())
                    .unit(item.getUnit())
                    .unit1(item.getUnit1())
                    .qty1(item.getQty1())
                    .unit2(item.getUnit2())
                    .qty2(item.getQty2())
                    .brand(item.getBrand())
                    .item_tax(item.getTax())
                    .assem_country(item.getOriginCountry())
                    .assem_area(item.getCiqOriginArea())
                    .spec(item.getSpec())
                    .bar_code(item.getBarCode())
                    .build();
            esdGoodList.add(esdGood);
        }

        EsdRequest esdRequest = EsdRequest.newBuilder()
                .source(EsdOrderSourceEnum.KJB_ORDER.getValue())
                .dno(orderKjb.getStoreCode())
                .cp_code(orderKjb.getLogisticsCode())
                .mail_no(orderKjb.getLogisticsNo())
                .tran_no(orderKjb.getInterLogisticsNo())
                .logistics_id(orderKjb.getCainiaoLogisticsNo())
                .seller_id(orderKjb.getTbStoreCode())
                .order_create_time(orderKjb.getOrderCreateTime())
                .storehouse_id(orderKjb.getOverseaHouseCode())
                .esdno(orderKjb.getEsdNo())
                .trade_no(orderKjb.getTradeNo())
                .payment_enterprise(orderKjb.getPayMerchantCode())
                .payment_enterprise_name(orderKjb.getPayMerchantName())
                .payment_transaction(orderKjb.getPayNo())
                .payment_remark(orderKjb.getPayRemark())
                .out_way_bill_url(orderKjb.getWaybillUrl())
                .declare_scheme_sid(orderKjb.getDeclareSchemeSid())
                .product_code(orderKjb.getProductCode())
                .total_freight(orderKjb.getFreight())
                .total_code(orderKjb.getFreightCurrency())
                .premium_fee(orderKjb.getPremium())
                .premium_code(orderKjb.getPremiumCurrency())
                .totai_taxes_reference(orderKjb.getTax())
                .totai_code(orderKjb.getTaxCurrency())
                .discount_fee(orderKjb.getDiscount())
                .discount_code(orderKjb.getDiscountCurrency())
                .net_weight(orderKjb.getNetWeight())
                .itemsum_weight(orderKjb.getGrossWeight())
                .bill(orderKjb.getBillNo())
                .platform(orderKjb.getPlatformCode())
                .is_trace_source(orderKjb.getIsTraceSource().getValue())
                .zcode(orderKjb.getZcode())
                .totai_taxes_pay(orderKjb.getActualPaid())
                .totai_pay_code(orderKjb.getActualPaidCurrency())
                .is_tran(orderKjb.getIsTran().getValue().toString())
                .remark(orderKjb.getRemark())
                .destion_code(orderKjb.getDestinationCode())
                .sender(esdSender)
                .receiver(esdReceiver)
                .buyer(esdBuyer)
                .serciveList(esdServiceTypeList)
                .itemList(esdGoodList)
                .build();
        return esdRequest;
    }
}
