package com.topideal.supplychain.ocp.beibei.dto;

import java.io.Serializable;

/**
 * @author wanzhaozhang
 * @date 2020/3/23
 * @description
 **/
public class BeibeiOrderItemDto implements Serializable {
    private String sku_id;
    private String iid;
    private String url;
    private String outer_id;
    private String goods_num;
    private String title;
    private String price;
    private String origin_price;
    private String num;
    private String refund_status;
    private String subtotal;
    private String total_fee;
    private String sku_properties;
    private String ship_city_property;
    private String img;
    private String brand;
    private String ship_city_code;
    private String declare_city_code;
    private String declare_amount;

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOuter_id() {
        return outer_id;
    }

    public void setOuter_id(String outer_id) {
        this.outer_id = outer_id;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(String goods_num) {
        this.goods_num = goods_num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrigin_price() {
        return origin_price;
    }

    public void setOrigin_price(String origin_price) {
        this.origin_price = origin_price;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getSku_properties() {
        return sku_properties;
    }

    public void setSku_properties(String sku_properties) {
        this.sku_properties = sku_properties;
    }

    public String getShip_city_property() {
        return ship_city_property;
    }

    public void setShip_city_property(String ship_city_property) {
        this.ship_city_property = ship_city_property;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getShip_city_code() {
        return ship_city_code;
    }

    public void setShip_city_code(String ship_city_code) {
        this.ship_city_code = ship_city_code;
    }

    public String getDeclare_city_code() {
        return declare_city_code;
    }

    public void setDeclare_city_code(String declare_city_code) {
        this.declare_city_code = declare_city_code;
    }

    public String getDeclare_amount() {
        return declare_amount;
    }

    public void setDeclare_amount(String declare_amount) {
        this.declare_amount = declare_amount;
    }
}
