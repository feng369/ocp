package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.YouZanOrderNewEnum;
import java.math.BigDecimal;

public class OrderYouzanItem extends BaseEntity {

    private Long orderId;

    private String oid;

    private Long itemType;

    private String title;

    private BigDecimal num;

    private String outerSkuId;

    private String buyerMessages;

    private BigDecimal price;

    private BigDecimal totalFee;

    private BigDecimal payment;

    private Long itemId;

    private Long skuId;

    private String skuPropertiesName;

    private String outerItemId;

    private BigDecimal pointsPrice;

    private String picPath;

    private String goodsUrl;

    private String alias;

    private Boolean isPresent;

    private BigDecimal discountPrice;

    private String skuUniqueCode;

    private String preSaleType;

    private String isPreSale;

    private String isCrossBorder;

    private String customsCode;

    private String crossBorderTradeMode;

    private String subOrderNo;

    private BigDecimal fenxiaoPrice;

    private BigDecimal fenxiaoPayment;

    private BigDecimal rePrice;

    private BigDecimal reTotalPrice;

    private String goodsUnit;

    private YouZanOrderNewEnum sendKjbFlag;

    private BigDecimal taxTotal;

    private BigDecimal freight;

    private BigDecimal discount;

    private BigDecimal fenxiaoTaxTotal;

    private BigDecimal fenxiaoFreight;

    private BigDecimal fenxiaoDiscount;

    private BigDecimal fenxiaoDiscountPrice;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Long getItemType() {
        return itemType;
    }

    public void setItemType(Long itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public String getOuterSkuId() {
        return outerSkuId;
    }

    public void setOuterSkuId(String outerSkuId) {
        this.outerSkuId = outerSkuId;
    }

    public String getBuyerMessages() {
        return buyerMessages;
    }

    public void setBuyerMessages(String buyerMessages) {
        this.buyerMessages = buyerMessages;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getSkuPropertiesName() {
        return skuPropertiesName;
    }

    public void setSkuPropertiesName(String skuPropertiesName) {
        this.skuPropertiesName = skuPropertiesName;
    }

    public String getOuterItemId() {
        return outerItemId;
    }

    public void setOuterItemId(String outerItemId) {
        this.outerItemId = outerItemId;
    }

    public BigDecimal getPointsPrice() {
        return pointsPrice;
    }

    public void setPointsPrice(BigDecimal pointsPrice) {
        this.pointsPrice = pointsPrice;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getSkuUniqueCode() {
        return skuUniqueCode;
    }

    public void setSkuUniqueCode(String skuUniqueCode) {
        this.skuUniqueCode = skuUniqueCode;
    }

    public String getPreSaleType() {
        return preSaleType;
    }

    public void setPreSaleType(String preSaleType) {
        this.preSaleType = preSaleType;
    }

    public String getIsPreSale() {
        return isPreSale;
    }

    public void setIsPreSale(String isPreSale) {
        this.isPreSale = isPreSale;
    }

    public String getIsCrossBorder() {
        return isCrossBorder;
    }

    public void setIsCrossBorder(String isCrossBorder) {
        this.isCrossBorder = isCrossBorder;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public String getCrossBorderTradeMode() {
        return crossBorderTradeMode;
    }

    public void setCrossBorderTradeMode(String crossBorderTradeMode) {
        this.crossBorderTradeMode = crossBorderTradeMode;
    }

    public String getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo;
    }

    public BigDecimal getFenxiaoPrice() {
        return fenxiaoPrice;
    }

    public void setFenxiaoPrice(BigDecimal fenxiaoPrice) {
        this.fenxiaoPrice = fenxiaoPrice;
    }

    public BigDecimal getFenxiaoPayment() {
        return fenxiaoPayment;
    }

    public void setFenxiaoPayment(BigDecimal fenxiaoPayment) {
        this.fenxiaoPayment = fenxiaoPayment;
    }

    public BigDecimal getRePrice() {
        return rePrice;
    }

    public void setRePrice(BigDecimal rePrice) {
        this.rePrice = rePrice;
    }

    public BigDecimal getReTotalPrice() {
        return reTotalPrice;
    }

    public void setReTotalPrice(BigDecimal reTotalPrice) {
        this.reTotalPrice = reTotalPrice;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public YouZanOrderNewEnum getSendKjbFlag() {
        return sendKjbFlag;
    }

    public void setSendKjbFlag(YouZanOrderNewEnum sendKjbFlag) {
        this.sendKjbFlag = sendKjbFlag;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getFenxiaoTaxTotal() {
        return fenxiaoTaxTotal;
    }

    public void setFenxiaoTaxTotal(BigDecimal fenxiaoTaxTotal) {
        this.fenxiaoTaxTotal = fenxiaoTaxTotal;
    }

    public BigDecimal getFenxiaoFreight() {
        return fenxiaoFreight;
    }

    public void setFenxiaoFreight(BigDecimal fenxiaoFreight) {
        this.fenxiaoFreight = fenxiaoFreight;
    }

    public BigDecimal getFenxiaoDiscount() {
        return fenxiaoDiscount;
    }

    public void setFenxiaoDiscount(BigDecimal fenxiaoDiscount) {
        this.fenxiaoDiscount = fenxiaoDiscount;
    }

    public BigDecimal getFenxiaoDiscountPrice() {
        return fenxiaoDiscountPrice;
    }

    public void setFenxiaoDiscountPrice(BigDecimal fenxiaoDiscountPrice) {
        this.fenxiaoDiscountPrice = fenxiaoDiscountPrice;
    }
}