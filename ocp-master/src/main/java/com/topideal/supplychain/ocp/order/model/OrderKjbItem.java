package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.KjbYesNoEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 跨境宝订单明细
 * @author: syq
 * @create: 2019-12-16 17:34
 **/
public class OrderKjbItem extends BaseEntity {

    private Long orderId;//订单id

    private String tradeNo;//交易订单号

    private String itemNo;//序号

    private String goodsCode;//商品编码

    private KjbYesNoEnum isPresente;//是否商品备案

    private String goodsName;//商品名称

    private String categoryNo;//行邮税号

    private String hscode;

    private String custNo;//海关商品备案号

    private String ciqNo;//国检商品备案号

    private Integer qty;//数量

    private BigDecimal price;//单价

    private String priceCurrency;//单价币制

    private BigDecimal qty1;//第一法定数量

    private String unit1;//第一法定单位

    private BigDecimal qty2;//第二法定数量

    private String unit2;//第二法定单位

    private String brand;//品牌

    private String spec;//规格型号

    private String unit;//计量单位

    private BigDecimal grossWeight;//毛重

    private BigDecimal netWeight;//净重

    private String tax;//税

    private String originCountry;//原产国

    private String ciqOriginArea;//国检原产地

    private String barCode;//条形码

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getHscode() {
        return hscode;
    }

    public void setHscode(String hscode) {
        this.hscode = hscode;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCiqNo() {
        return ciqNo;
    }

    public void setCiqNo(String ciqNo) {
        this.ciqNo = ciqNo;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public BigDecimal getQty1() {
        return qty1;
    }

    public void setQty1(BigDecimal qty1) {
        this.qty1 = qty1;
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public BigDecimal getQty2() {
        return qty2;
    }

    public void setQty2(BigDecimal qty2) {
        this.qty2 = qty2;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getCiqOriginArea() {
        return ciqOriginArea;
    }

    public void setCiqOriginArea(String ciqOriginArea) {
        this.ciqOriginArea = ciqOriginArea;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public KjbYesNoEnum getIsPresente() {
        return isPresente;
    }

    public void setIsPresente(KjbYesNoEnum isPresente) {
        this.isPresente = isPresente;
    }
}
