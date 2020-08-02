package com.topideal.supplychain.ocp.xiaomi.converter;

import com.google.common.collect.Lists;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.kjb.dto.OrderKjbReq;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.order.dto.OrderXiaomiDto;
import com.topideal.supplychain.ocp.order.model.OrderXiaomiGoods;
import com.topideal.supplychain.ocp.xiaomi.dto.AddressDto;
import com.topideal.supplychain.ocp.xiaomi.dto.DataDto;
import com.topideal.supplychain.ocp.xiaomi.dto.ProductDto;
import com.topideal.supplychain.util.JacksonUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XiaomiDtoConverter {

    private static final BigDecimal SCALE_OF_MONEY = new BigDecimal(100);

    /**
     * 将订单数据转为系统需要的订单
     *
     * @param orderData 订单数据
     * @param store     店铺
     * @return 小米订单
     */
    public static OrderXiaomiDto convertOrder(DataDto orderData, Store store) {
        AddressDto address = orderData.getAddress() != null ? JacksonUtils.readValue(orderData.getAddress(), AddressDto.class) : new AddressDto();
        DataDto.Consignee consignee = orderData.getConsignee_id_card() != null ? orderData.getConsignee_id_card() : new DataDto.Consignee();
        DataDto.paymentInfo paymentInfo = orderData.getPayment_info() != null ? orderData.getPayment_info() : new DataDto.paymentInfo();
        List<ProductDto> products = orderData.getProducts();
        List<OrderXiaomiGoods> goodsList = null;
        if (!CollectionUtils.isEmpty(products)) {
            goodsList = Lists.newArrayList();
            for (ProductDto product : products) {
                int i = 1;
                OrderXiaomiGoods goods = new OrderXiaomiGoods();
                goods.setCount(product.getCount());
                goods.setGid(product.getGid());
                goods.setName(product.getName());
                goods.setPid(product.getPid());
                goods.setSalePrice(product.getSale_price() != null ? product.getSale_price().divide(SCALE_OF_MONEY) : BigDecimal.ZERO);
                goods.setUid(product.getUid());
                goods.setPrice(product.getPrice() != null ? product.getPrice().divide(SCALE_OF_MONEY) : BigDecimal.ZERO);
                goods.setGnum(new Long(i++));
                goodsList.add(goods);
            }
        }

        return OrderXiaomiDto.newBuilder().orderId(orderData.getOrder_id().toString())
                .storeCode(store.getCode())
                .ebcCode(store.getMerchantCode())
                .provinceName(address.getProvince() != null ? address.getProvince().getName() : "")
                .cityName(address.getCity() != null ? address.getCity().getName() : "")
                .districtName(address.getDistrict() != null ? address.getDistrict().getName() : "")
                .areaName(address.getArea() != null ? address.getArea().getName() : "")
                .address(address.getAddress())
                .tel(address.getTel())
                .consignee(address.getConsignee())
                .zipcode(address.getZipcode())
                .cardId(consignee.getCard_id())
                .cardName(consignee.getCard_name())
                .importationType(CollectionUtils.isEmpty(paymentInfo.getImportation_type()) ? null : paymentInfo.getImportation_type().get(0))
                .couponAmount(orderData.getCoupon_amount() != null ? orderData.getCoupon_amount().divide(SCALE_OF_MONEY) : BigDecimal.ZERO)
                .shipFee(orderData.getShip_fee() != null ? orderData.getShip_fee().divide(SCALE_OF_MONEY) : BigDecimal.ZERO)
                .totalPrice(orderData.getTotal_price() != null ? orderData.getTotal_price().divide(SCALE_OF_MONEY) : BigDecimal.ZERO)
                .ctime(new Date(orderData.getCtime() * 1000))
                .ftime(new Date(orderData.getFtime() * 1000))
                .sendSystem(ForwardSystemEnum.KJB)
                .sendStatus(OrderStatusEnum.INIT)
                .failReason(null)
                .products(goodsList)
                .build();

    }

    /**
     * 组装请求KJB对象
     *
     * @param order
     * @return
     */
    public static OrderKjbReq convertKjbOrder(OrderXiaomiDto order) {
        OrderKjbReq orderKjbReq = null;
        List<OrderKjbReq.GoodsReq> goodsReqList = new ArrayList<>();
        List<OrderXiaomiGoods> products = order.getProducts();
        Long buyerRegNo = null;
        if (!CollectionUtils.isEmpty(products)) {
            buyerRegNo = products.get(0).getUid();
            for (OrderXiaomiGoods product : products) {
                OrderKjbReq.GoodsReq goodsReq = OrderKjbReq.GoodsReq.newBuilder().gnum(product.getGnum()).gId(product.getGid()).pId(product.getPid())
                        .amount(product.getCount()).price(product.getSalePrice().multiply(SCALE_OF_MONEY)).goodPrice(product.getPrice().multiply(SCALE_OF_MONEY)).build();
                goodsReqList.add(goodsReq);
            }
        }
        OrderKjbReq.RecipientReq recipientReq = OrderKjbReq.RecipientReq.newBuilder().name(order.getConsignee()).mobilePhone(order.getTel())
                .province(order.getProvinceName()).city(order.getCityName()).district(order.getDistrictName())
                .address(order.getAreaName()+order.getAddress()).postCode(order.getZipcode()).build();

        orderKjbReq = OrderKjbReq.newBuilder()
                .orderId(order.getOrderId())
                .orderDate(order.getCtime())
                .payDate(order.getFtime())
                .electricCode(order.getEbcCode())
                .shopid(order.getStoreCode())
                .freightFcy(order.getShipFee().multiply(SCALE_OF_MONEY))
                .discount(order.getCouponAmount().multiply(SCALE_OF_MONEY))
                .totalValue(order.getTotalPrice().multiply(SCALE_OF_MONEY))
                .buyerName(order.getCardName())
                .buyerIdNumber(order.getCardId())
                .payPcomName(order.getImportationType())
                .buyerRegNo(buyerRegNo)
                .goodList(goodsReqList)
                .recipient(recipientReq)
                .build();

        return orderKjbReq;
    }

    public static void main(String[] args) {
        System.out.println(new BigDecimal(12005).divide(SCALE_OF_MONEY));
    }
}
