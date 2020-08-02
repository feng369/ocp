package com.topideal.supplychain.ocp.dxy.converter;

import com.google.common.collect.Lists;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.ofc.dto.OfcGoodsReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto.Builder;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.order.model.OrderDxy;
import com.topideal.supplychain.ocp.order.model.OrderDxyItem;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName DtoConverter
 * @TODO dto转换器
 * @Author zhangzhihao
 * @DATE 2019/12/3 17:38
 * @Version 1.0
 **/

public class DxyDtoConverter {


    /*
     *
     * @param
     * @param orderItems
     * @param defaultConfig
     * @return
     */
    public static OfcRequest convertOfc(OrderDxy orderDxy, List<OrderDxyItem> orderItems, TransferDefaultConfig defaultConfig,String storeCode,String storeName) {

        BusinessAssert.assertNotEmpty(defaultConfig.getOrderSource(),"转单默认配置订单来源[orderSource]未配置");

        Builder build = OfcOrderReqDto.newBuilder()
                .busiMode(orderDxy.getBusiMode().getValue())
                .buyerIdNumber(orderDxy.getBuyerIdCard())
                .buyerIdType("1")
                .buyerName(orderDxy.getBuyerName())
                .buyerTelephone(orderDxy.getBuyerMobile())
                .buyerRegNo(null)
                .ciqbCode(defaultConfig.getCiqCode())
                .province(orderDxy.getProvince())
                .city(orderDxy.getCity())
                .district(orderDxy.getArea())
                .postCode(orderDxy.getPostCode())
                .consignee(orderDxy.getReceiverName())
                .consigneeAddress(orderDxy.getAddress())
                .consigneeTelephone(orderDxy.getReceiverMobile())
                .customsCode(orderDxy.getCustomsCode())
                .discount(orderDxy.getDiscountAmount())
                .acturalPaid(orderDxy.getRealPayAmount())
                .payCurr("CNY")
                .ebpCode(defaultConfig.getPlatformCode())
                .ebpName(null)
                .ebcCode(defaultConfig.getMerchantCode())
                .ebcName(null)
                .freight(orderDxy.getFreightAmount())
                .freightCurr("CNY")
                .ieFlag("I")
                .insuredFee(BigDecimal.ZERO)
                .insurCurr("CNY")
                .key(null)
                .declTime(orderDxy.getOrderCreateTime())
                .logisticsCode(defaultConfig.getLogisticsCode())
                .logisticsName(null)
                .logisticsNo(null)
                .logisticsNote(null)
                .orderNo(orderDxy.getOrderId())
                .orderSource(Long.valueOf(defaultConfig.getOrderSource()))
                .orderType(2L)
                .packNo(1L)
                .payCode(orderDxy.getPayCompanyCode())
                .payName(null)
                .payTransactionId(orderDxy.getPaymentNo())
                .payTime(orderDxy.getPayTime())
                .receiveNo(orderDxy.getReceiverIdCard())
                .storeCode(defaultConfig.getWarehouseCode())
                .country("中国")
                .taxTotal(orderDxy.getTaxAmount())
                .taxCurr("CNY")
                .userId(storeCode)
                .userName(storeName)
                //.features(JacksonUtils.toJSon(jsonMap))
                ;


        OfcOrderReqDto ofcOrderReqDto = build.build();

        List<OfcGoodsReqDto> list = Lists.newArrayList();
        for (int i = 0; i < orderItems.size(); i++) {
            OrderDxyItem orderDxyItem = orderItems.get(i);
            OfcGoodsReqDto ofcGoodsReqDto = OfcGoodsReqDto.newBuilder()
                    .gnum(String.valueOf(i + 1))
                    .goodsId(orderDxyItem.getSkuCode())
                    .goodsName(orderDxyItem.getCommodityName())
                    .barCode(null)
                    .currency("CNY")
                    .qty(orderDxyItem.getQuantity())
                    .qtyUnit(null)
                    .price(orderDxyItem.getPrice())
                    .totalPrice(orderDxyItem.getAmount())
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
}









