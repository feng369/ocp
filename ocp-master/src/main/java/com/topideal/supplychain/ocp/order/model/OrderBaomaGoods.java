package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;

import java.math.BigDecimal;

public class OrderBaomaGoods extends BaseEntity {

    /**
     * order_Id
     */
    private Long orderId;

    /**
     * 电商企业的商品货号
     */
    private String thirdPartyGoodsCode;

    /**
     * 数量
     */
    private Long quantity;

    /**
     * 税金
     */
    private BigDecimal duty;

    /**
     * 销售价
     */
    private BigDecimal sellPrice;

    /**
     * 成本价
     */
    private BigDecimal costPrice;

    /**
     * 箱规
     */
    private String packCount;

    /**
     * 商品名称
     */
    private String goodsName;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getThirdPartyGoodsCode() {
        return thirdPartyGoodsCode;
    }

    public void setThirdPartyGoodsCode(String thirdPartyGoodsCode) {
        this.thirdPartyGoodsCode = thirdPartyGoodsCode;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDuty() {
        return duty;
    }

    public void setDuty(BigDecimal duty) {
        this.duty = duty;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public String getPackCount() {
        return packCount;
    }

    public void setPackCount(String packCount) {
        this.packCount = packCount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}