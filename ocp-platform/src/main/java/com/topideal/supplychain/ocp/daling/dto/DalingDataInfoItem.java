package com.topideal.supplychain.ocp.daling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author xuxiaoyan
 * @date 2019-12-16 10:22
 */
public class DalingDataInfoItem implements Serializable {

    private static final long serialVersionUID = 1400181486667426883L;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("upc")
    private String upc;

    @JsonProperty("vendor_sku")
    private String vendorSku;

    @JsonProperty("qty")
    private Integer qty;

    @JsonProperty("unit_price")
    private BigDecimal unitPrice;

    @JsonProperty("payment_unit_price")
    private BigDecimal paymentUnitPrice;

    @JsonProperty("unit_tax_price")
    private BigDecimal unitTaxPrice;

    @JsonProperty("discount_price")
    private BigDecimal discountPrice;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getVendorSku() {
        return vendorSku;
    }

    public void setVendorSku(String vendorSku) {
        this.vendorSku = vendorSku;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getPaymentUnitPrice() {
        return paymentUnitPrice;
    }

    public void setPaymentUnitPrice(BigDecimal paymentUnitPrice) {
        this.paymentUnitPrice = paymentUnitPrice;
    }

    public BigDecimal getUnitTaxPrice() {
        return unitTaxPrice;
    }

    public void setUnitTaxPrice(BigDecimal unitTaxPrice) {
        this.unitTaxPrice = unitTaxPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }
}
