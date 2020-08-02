package com.topideal.supplychain.ocp.pdd.converter;

import com.google.common.collect.Lists;
import com.topideal.supplychain.ocp.config.dto.TransferDefaultConfig;
import com.topideal.supplychain.ocp.config.model.TransferConfig;
import com.topideal.supplychain.ocp.enums.OrderTypeEnum;
import com.topideal.supplychain.ocp.esd.dto.*;
import com.topideal.supplychain.ocp.gemini.dto.GeminiReqGoods;
import com.topideal.supplychain.ocp.gemini.dto.GeminiRequest;
import com.topideal.supplychain.ocp.kjb.dto.KjbReqGoods;
import com.topideal.supplychain.ocp.kjb.dto.KjbRequest;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.ofc.dto.OfcGoodsReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcOrderReqDto;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.op.dto.OpGoodsItemDto;
import com.topideal.supplychain.ocp.op.dto.OpRecipientDto;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.order.model.OrderPdd;
import com.topideal.supplychain.ocp.order.model.OrderPddGoods;
import com.topideal.supplychain.ocp.order.model.OrderPddItem;
import com.topideal.supplychain.util.AES256Util;
import com.topideal.supplychain.util.JacksonUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author klw
 * @date 2019/12/16 13:53
 * @description:
 */
public class PddDtoConverter {

    /**
     * 推送kjb做商品拆分的报文
     * @param order
     * @param goodsList
     * @return
     */
    public static KjbRequest buildKjbRequest(OrderPdd order, List<OrderPddGoods> goodsList){
        BigDecimal price = BigDecimal.ZERO;
        List<KjbReqGoods> kjbReqGoodsList = Lists.newArrayList();
        for (OrderPddGoods pddGoods : goodsList){
            KjbReqGoods goods = KjbReqGoods.newBuilder()
                    .goods_id(pddGoods.getOuterId())
                    .goods_name(pddGoods.getGoodsName())
                    .goods_num(pddGoods.getGoodsCount().toString())
                    .goods_price(pddGoods.getGoodsPrice())
                    .build();
            price = price.add(pddGoods.getGoodsPrice().multiply(BigDecimal.valueOf(pddGoods.getGoodsCount())));
            kjbReqGoodsList.add(goods);
        }
        return KjbRequest.newBuilder()
                .cbepcomCode(order.getElectricCode())
                .order_id(order.getOrderId())
                .good_list(kjbReqGoodsList)
                .payment_goods(price)
                .build();
    }

    /**
     * 推送GEMINI做税价分离的报文
     * @param order
     * @param itemList
     * @return
     */
    public static GeminiRequest buildGeminiRequest(OrderPdd order, List<OrderPddItem> itemList){
        List<GeminiReqGoods> goodsList = Lists.newArrayList();
        for (OrderPddItem item : itemList){
            GeminiReqGoods goods = GeminiReqGoods.newBuilder()
                    .id(item.getId())
                    .outerItemId(item.getGoodId())
                    .outerItemName(item.getCopGName())
                    .feePrice(item.getGoodPrice())
                    .num(BigDecimal.valueOf(item.getAmount()))
                    .feeTotalPrice(item.getGoodPrice().multiply(BigDecimal.valueOf(item.getAmount())))
                    .build();
            goodsList.add(goods);
        }
        return GeminiRequest.newBuilder()
                .ebpCode(order.getCbepcomCode())
                .electricCode(order.getElectricCode())
                .tid(order.getOrderId())
                .busiMode(order.getBusiMode())
                .customerCode(order.getCustomsCode())
                .totalPrice(order.getGoodsAmount())
                .goods(goodsList)
                .build();
    }

    /**
     * 拼多多下发ofc报文转换
     * @param order
     * @param itemList
     * @return
     */
    public static OfcRequest buildOfcRequest(OrderPdd order, List<OrderPddItem> itemList, TransferConfig transferConfig){

        OfcOrderReqDto.Builder orderBuilder = OfcOrderReqDto.newBuilder()
                .acturalPaid(order.getActuralPaid())
                .busiMode(order.getBusiMode())
                .buyerIdNumber(AES256Util.decrypt(order.getBuyerIdNumber()))
                .buyerIdType(order.getBuyerIdType())
                .buyerName(order.getBuyerName())
                .buyerRegNo(AES256Util.decrypt(order.getBuyerTelephone()))
                .buyerTelephone(AES256Util.decrypt(order.getBuyerTelephone()))
                .ciqbCode(order.getCiqbCode())
                .city(order.getCity())
                .consignee(order.getName())
                .consigneeAddress(order.getAddress())
                .consigneeTelephone(order.getMobilePhone())
                .country(order.getCountry())
                .customsCode(order.getCustomsCode())
                .customsType(1L)
                .declTime(order.getOrderDate())
                .discount(order.getDiscount())
                .district(order.getDistrict())
                .ebcCode(order.getElectricCode())
                .ebpCode(order.getCbepcomCode())
                .freight(order.getPostage())
                .freightCurr(order.getFreightFcode())
                .fCode("CNY")
                .fromEplat(order.getFromEplat() == null ? null : Long.valueOf(order.getFromEplat()))
                .goodsValue(order.getGeminiTotalPrice())
                .ieFlag("I")
                .insurCurr(order.getInsurCurr())
                .insuredFee(order.getInsuredFee())
                .logisticsCode(order.getTpl())
                .logisticsNo(order.getLogisticsNo())
                .orderBatchNo(order.getOrderBatchNo())
                .orderCode(order.getOrderSn())
                .orderNo(order.getOrderId())
                .orderStatus(order.getOrderStatus())
                .orderSource(101L)
                .orderType(Long.valueOf(order.getOrderType()))
                .ownerFlag(order.getOwnerFlag() == null ? null : Long.valueOf(order.getOwnerFlag()))
                .packNo(1L)
                .payCurr(order.getPayCurr())
                .pod(order.getPod())
                .postCode(order.getPostCode())
                .province(order.getProvince())
                .receiveNo(order.getReceiveNo())
                .taxCurr(order.getTaxFcode())
                .taxTotal(order.getTaxFcy())
                .tradeMode("1")
                .userId(order.getSid())
                .payTransactionId(order.getPayNo());

        if (OrderTypeEnum.CATCH.equals(order.getType())){
            orderBuilder.consigneeAddress(order.getReceiverAddress())
                    .logisticsCode(order.getLogistCode());
        }else {
            orderBuilder.reDeclare(order.getReDeclare() == null ? 0 : Long.valueOf(order.getReDeclare()));
        }

        if (StringUtils.isNotEmpty(transferConfig.getConfiguration())){
            TransferDefaultConfig config = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
            orderBuilder.orderSource(StringUtils.isEmpty(config.getOrderSource()) ? 101L : Long.valueOf(config.getOrderSource()));
            orderBuilder.orderType(stringToLong(config.getOrderType(), order.getOrderType()));
            if (StringUtils.isNotEmpty(config.getLogisticsCode())) orderBuilder.logisticsCode(config.getLogisticsCode());
            if (StringUtils.isNotEmpty(config.getMerchantCode())) orderBuilder.ebcCode(config.getMerchantCode());
            if (StringUtils.isNotEmpty(config.getPlatformCode())) orderBuilder.ebpCode(config.getPlatformCode());
            if (StringUtils.isNotEmpty(config.getFromEplat())) orderBuilder.fromEplat(Long.valueOf(config.getFromEplat()));
            if (StringUtils.isNotEmpty(config.getWarehouseCode())) orderBuilder.storeCode(config.getWarehouseCode());
            //接单接口，取自定义内容的tpl
            if(OrderTypeEnum.RECEIVE.equals(order.getType())){
                orderBuilder.logisticsCode(config.getTpl());
            }
        }

        List<OfcGoodsReqDto> goodsList = Lists.newArrayList();
        for (OrderPddItem item : itemList){
            OfcGoodsReqDto goods = OfcGoodsReqDto.newBuilder()
                    .gnum(String.valueOf(goodsList.size() + 1))
                    .goodsId(item.getGoodId())
                    .goodsName(item.getCopGName())
                    .barCode(item.getBarCode())
                    .currency(item.getCurrency())
                    .qty(BigDecimal.valueOf(item.getAmount()))
                    .qtyUnit(null)
                    .price(item.getPrice() == null ? BigDecimal.ZERO : item.getPrice())
                    .totalPrice(item.getTotalPrice())
                    .ebcCode(item.getEbcCode())
                    .ciqAssemCountry(item.getCiqAssemCountry())
                    .spec(item.getSpec())
                    .grossWeight(item.getGrossWeight())
                    .netWeight(item.getNetWeight())
                    .isGoodsRecord(item.getIsGoodsRecord() == null ? null : Long.valueOf(item.getIsGoodsRecord()))
                    .build();
            goodsList.add(goods);
        }
        return new OfcRequest(orderBuilder.build(), goodsList);
    }

    /**
     * 拼多多下发op请求报文转换
     * @param order
     * @param itemList
     * @param transferConfig
     * @return
     */
    public static OpRequest buildOpRequest(OrderPdd order, List<OrderPddItem> itemList, TransferConfig transferConfig){
        OpRequest.Builder orderBuilder = OpRequest.newBuilder()
                .orderId(order.getOrderId())
                .orderDate(order.getOrderDate())
                .orderType(1)
                .orderStatus("S")
                .customsType(1)
                .electricCode(order.getElectricCode())
                .cbepcomCode(order.getCbepcomCode())
                .busiMode(StringUtils.isEmpty(order.getBusiMode()) ? null : Integer.valueOf(order.getBusiMode()))
                .customsCode(order.getCustomsCode())
                .ciqbCode(order.getCiqbCode())
                .freightFcode("CNY")
                .taxFcy(order.getTaxFcy())
                .taxFcode("CNY")
                .discount(order.getDiscount())
                .buyerName(order.getBuyerName())
                .buyerIdType(1)
                .buyerIdNumber(AES256Util.decrypt(order.getBuyerIdNumber()))
                .buyerTelephone(AES256Util.decrypt(order.getBuyerTelephone()))
                .acturalPaid(order.getActuralPaid())
                .fromEplat(0)
                .notes(order.getOrderSn());

        OpRecipientDto.Builder recipientBuilder = OpRecipientDto.newBuilder().name(order.getName())
                .receiveType(1)
                .receiveNo(AES256Util.decrypt(order.getBuyerIdNumber()))
                .mobilePhone(AES256Util.decrypt(order.getBuyerTelephone()))
                .country(order.getCountry())
                .province(order.getProvince())
                .city(order.getCity())
                .district(order.getDistrict())
                .address(order.getAddress())
                .postCode(order.getPostCode())
                .totalFavourable(order.getDiscount());

        if (OrderTypeEnum.CATCH.equals(order.getType())){
            orderBuilder.tpl(order.getLogisticsId() == null ? null : order.getLogisticsId().toString())
                    .payNo(order.getPayNo())
                    .freightFcy(order.getPostage())
                    .buyerRegNo(AES256Util.decrypt(order.getBuyerTelephone()));

            recipientBuilder.address(order.getReceiverAddress());
        }else {
            orderBuilder.tpl(order.getTpl())
                    .freightFcy(order.getFreightFcy())
                    .insuredFee(BigDecimal.ZERO)
                    .otherRate(BigDecimal.ZERO)
                    .otherPayment(BigDecimal.ZERO)
                    .fCode("CNY")
                    .ordExcStatus("10")
                    .tradeMode("1")
                    .opType("1")
                    .payType("M")
                    .payStatus("D")
                    .payCurr("CNY")
                    .isStoreStrategy(0)
                    .vmiFlag(0)
                    .ownerFlag(0)
                    .totalValue(order.getTotalValue());
        }

        if (StringUtils.isNotEmpty(transferConfig.getConfiguration())){
            TransferDefaultConfig config = JacksonUtils.readValue(transferConfig.getConfiguration(), TransferDefaultConfig.class);
            if (StringUtils.isNotEmpty(config.getLogisticsCode())) orderBuilder.tpl(config.getLogisticsCode());
            if (StringUtils.isNotEmpty(config.getMerchantCode())) orderBuilder.electricCode(config.getMerchantCode());
            if (StringUtils.isNotEmpty(config.getPlatformCode())) orderBuilder.cbepcomCode(config.getPlatformCode());
            //接单接口，取自定义内容的tpl
            if(OrderTypeEnum.RECEIVE.equals(order.getType())){
                orderBuilder.tpl(config.getTpl());
            }
        }



        List<OpGoodsItemDto> goodsList = Lists.newArrayList();
        for (OrderPddItem item : itemList){
            OpGoodsItemDto itemDto = OpGoodsItemDto.newBuilder().gnum(String.valueOf(goodsList.size() + 1))
                    .goodId(item.getGoodId())
                    .amount(item.getAmount())
                    .price(item.getPrice())
                    .copGName(item.getCopGName())
                    .spec(item.getSpec())
                    .decTotal(OrderTypeEnum.CATCH.equals(order.getType()) ? item.getTotalPrice() : item.getDecTotal())
                    .build();
            goodsList.add(itemDto);
        }
        return orderBuilder.recipient(recipientBuilder.build()).goodList(goodsList).build();
    }

    /**
     * 拼多多下发op请求报文转换
     * @param order
     * @param itemList
     * @param transferConfig
     * @return
     */
    public static EsdRequest buildEsdRequest(OrderPdd order, List<OrderPddItem> itemList, TransferConfig transferConfig, String overseaHouseCode){
        EsdRequest.Builder orderBuilder = EsdRequest.newBuilder()
                .storehouse_id(overseaHouseCode)
                .source(70)
                .dno(order.getSid())
                .order_create_time(order.getOrderDate())
                .trade_no(order.getOrderId())
                .declare_scheme_sid("GZ-HP-BC")
                .total_freight(order.getFreightFcy())
                .total_code(order.getFreightFcode())
                .premium_fee(order.getInsuredFee())
                .premium_code("CNY")
                .totai_taxes_reference(order.getTaxFcy())
                .totai_code(order.getTaxFcode())
                .discount_fee(order.getDiscount())
                .discount_code(order.getfCode())
                .net_weight(order.getNetWeight())
                .itemsum_weight(order.getGrossWeight())
                .platform(order.getCbepcomCode())
                .is_trace_source(2)
                .totai_taxes_pay(order.getActuralPaid())
                .totai_pay_code(order.getfCode())
                .is_tran("2")
                .destion_code("HP01")
                .serciveList(Lists.newArrayList(EsdServiceType.newBuilder().service_type(0).build()));
        //平台取配置
        if (StringUtils.isNotEmpty(transferConfig.getPlatformCode())) orderBuilder.platform(transferConfig.getPlatformCode());

        EsdSender sender = EsdSender.newBuilder().name("Ison YI")
                .phone("852-24724606")
                .city("新界")
                .country("香港")
                .address("香港新界元朗流浮山路D.D.129Lot no.2963")
                .build();

        EsdReceiver receiver = EsdReceiver.newBuilder().name(order.getName())
                .mobile(order.getMobilePhone())
                .identity_no(AES256Util.decrypt(order.getBuyerIdNumber()))
                .country(order.getCountry())
                .county(order.getDistrict())
                .city(order.getCity())
                .province(order.getProvince())
                .address(order.getAddress())
                .zip_code(order.getPostCode())
                .build();

        EsdBuyer buyer = EsdBuyer.newBuilder().name(order.getBuyerName())
                .phone(AES256Util.decrypt(order.getBuyerTelephone()))
                .mobile(order.getBuyerRegNo())
                .id_type(order.getBuyerIdType())
                .identity_no(AES256Util.decrypt(order.getBuyerIdNumber()))
                .build();

        List<EsdGood> goodsList = Lists.newArrayList();
        for (OrderPddItem item : itemList){
            EsdGood goods = EsdGood.newBuilder().index(item.getGnum().toString())
                    .item(item.getGoodId())
                    .item_quantity(item.getAmount())
                    .price_declare(item.getPrice())
                    .price_code(order.getfCode())
                    .nots(order.getNotes())
                    .build();
            goodsList.add(goods);
        }
        return orderBuilder.sender(sender).receiver(receiver).buyer(buyer).itemList(goodsList).build();
    }

    private static Long stringToLong(String... strs){
        for (String str : strs){
            if (StringUtils.isNotEmpty(str)){
                return Long.valueOf(str);
            }
        }
        return null;
    }
}
