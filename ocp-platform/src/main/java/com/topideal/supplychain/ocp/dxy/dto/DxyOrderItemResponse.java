package com.topideal.supplychain.ocp.dxy.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName DxyOrderItemDto
 * @TODO 金额单位分
 * @Author zhangzhihao
 * @DATE 2020/6/30 15:54
 * @Version 1.0
 **/
public class DxyOrderItemResponse implements Serializable {

    private String skuCode;
    private String commodityName;
    private String specificationName;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal costPrice;
    private BigDecimal amount;
    private BigDecimal discountAmount;
    private BigDecimal taxAmount;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }
}
