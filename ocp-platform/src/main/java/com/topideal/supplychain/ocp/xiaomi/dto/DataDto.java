package com.topideal.supplychain.ocp.xiaomi.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/30
 * @description 小米订单信息 ，需要的字段
 **/
public class DataDto implements Serializable {
    //订单收货人信息
    private AddressDto addressDto;
    private String address;
    //用户身份证信息
    private Consignee consignee_id_card;
    // 支付信息
    private paymentInfo payment_info;
    // 订单优惠金额
    private BigDecimal coupon_amount;
    // 订单创建时间
    private Long ctime;
    // 订单支付时间
    private Long ftime;
    // 订单ID
    private Long order_id;
    // 订单运费
    private BigDecimal ship_fee;
    // 订单总价
    private BigDecimal total_price;
    // 订单购买的产品详情列表
    private List<ProductDto> products;

    /**
     *  用户身份证信息(跨境电商专用)
     */
    public static class Consignee{
        // 身份证号码
        private String card_id;
        // 身份证姓名
        private String card_name;

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }
    }

    public static class paymentInfo{
        private List<String> importation_type;

        public List<String> getImportation_type() {
            return importation_type;
        }

        public void setImportation_type(List<String> importation_type) {
            this.importation_type = importation_type;
        }
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * getter && setter
     */


    public Consignee getConsignee_id_card() {
        return consignee_id_card;
    }

    public void setConsignee_id_card(Consignee consignee_id_card) {
        this.consignee_id_card = consignee_id_card;
    }

    public paymentInfo getPayment_info() {
        return payment_info;
    }

    public void setPayment_info(paymentInfo payment_info) {
        this.payment_info = payment_info;
    }

    public BigDecimal getCoupon_amount() {
        return coupon_amount;
    }

    public void setCoupon_amount(BigDecimal coupon_amount) {
        this.coupon_amount = coupon_amount;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getFtime() {
        return ftime;
    }

    public void setFtime(Long ftime) {
        this.ftime = ftime;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public BigDecimal getShip_fee() {
        return ship_fee;
    }

    public void setShip_fee(BigDecimal ship_fee) {
        this.ship_fee = ship_fee;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
