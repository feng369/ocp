package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 全球单订单明细
 */
public class OrderGlobalItem extends BaseEntity {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 货号
     */
    private String skuId;

    /**
     * SKU名称
     */
    private String name;

    /**
     * SKU外文名称
     */
    private String foreignName;

    /**
     * SKU面单名称
     */
    private String billName;

    /**
     * 商品条码
     */
    private String barcode;

    /**
     * 数量
     */
    private Long count;

    /**
     * 商户申报价格
     */
    private BigDecimal declarePrice;

    /**
     * 单种商品总折扣金额
     */
    private BigDecimal discountPrice;

    /**
     * SKU供货价
     */
    private BigDecimal price;

    /**
     * 商品总价
     */
    private BigDecimal totalPrice;

    /**
     * 货币单位
     */
    private String currencyUnit;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getForeignName() {
        return foreignName;
    }

    public void setForeignName(String foreignName) {
        this.foreignName = foreignName == null ? null : foreignName.trim();
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName == null ? null : billName.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getDeclarePrice() {
        return declarePrice;
    }

    public void setDeclarePrice(BigDecimal declarePrice) {
        this.declarePrice = declarePrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit == null ? null : currencyUnit.trim();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}