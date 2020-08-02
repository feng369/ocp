package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;

import java.math.BigDecimal;

public class OrderAmwayItem extends BaseEntity {

    /**
     * 产品SKU编号
     */
    private String itemSku;

    /**
     * 产品单价
     */
    private BigDecimal unitDp;

    /**
     * 产品名称
     */
    private String orderingItemName;

    /**
     * 产品下单编号
     */
    private String orderingItemNumber;

    /**
     * 是否可退货
     */
    private String isReturn;

    /**
     * 申报数量
     */
    private BigDecimal orderQty;

    /**
     * 备用字段
     */
    private String rsvst1;

    private String rsvst2;

    private String rsvst3;

    private String rsvdc1;

    private String rsvdc2;

    private String rsvdc3;

    /**
     * 主产品编号
     */
    private String masterOrderingItemNumber;

    /**
     * 主产品数量
     */
    private BigDecimal masterOrderQty;

    /**
     * 主产品名称
     */
    private String masterOrderingItemName;

    /**
     * 主产品价格
     */
    private BigDecimal masterOrderingPrice;

    private String mixLogisticsCode;

    private String mixLogisticsName;

    private String orderNumber;

    private Long orderId;

    public String getItemSku() {
        return itemSku;
    }

    public void setItemSku(String itemSku) {
        this.itemSku = itemSku == null ? null : itemSku.trim();
    }

    public BigDecimal getUnitDp() {
        return unitDp;
    }

    public void setUnitDp(BigDecimal unitDp) {
        this.unitDp = unitDp;
    }

    public String getOrderingItemName() {
        return orderingItemName;
    }

    public void setOrderingItemName(String orderingItemName) {
        this.orderingItemName = orderingItemName == null ? null : orderingItemName.trim();
    }

    public String getOrderingItemNumber() {
        return orderingItemNumber;
    }

    public void setOrderingItemNumber(String orderingItemNumber) {
        this.orderingItemNumber = orderingItemNumber == null ? null : orderingItemNumber.trim();
    }

    public String getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn == null ? null : isReturn.trim();
    }

    public BigDecimal getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(BigDecimal orderQty) {
        this.orderQty = orderQty;
    }

    public String getRsvst1() {
        return rsvst1;
    }

    public void setRsvst1(String rsvst1) {
        this.rsvst1 = rsvst1 == null ? null : rsvst1.trim();
    }

    public String getRsvst2() {
        return rsvst2;
    }

    public void setRsvst2(String rsvst2) {
        this.rsvst2 = rsvst2 == null ? null : rsvst2.trim();
    }

    public String getRsvst3() {
        return rsvst3;
    }

    public void setRsvst3(String rsvst3) {
        this.rsvst3 = rsvst3 == null ? null : rsvst3.trim();
    }

    public String getRsvdc1() {
        return rsvdc1;
    }

    public void setRsvdc1(String rsvdc1) {
        this.rsvdc1 = rsvdc1 == null ? null : rsvdc1.trim();
    }

    public String getRsvdc2() {
        return rsvdc2;
    }

    public void setRsvdc2(String rsvdc2) {
        this.rsvdc2 = rsvdc2 == null ? null : rsvdc2.trim();
    }

    public String getRsvdc3() {
        return rsvdc3;
    }

    public void setRsvdc3(String rsvdc3) {
        this.rsvdc3 = rsvdc3 == null ? null : rsvdc3.trim();
    }

    public String getMasterOrderingItemNumber() {
        return masterOrderingItemNumber;
    }

    public void setMasterOrderingItemNumber(String masterOrderingItemNumber) {
        this.masterOrderingItemNumber = masterOrderingItemNumber == null ? null : masterOrderingItemNumber.trim();
    }

    public BigDecimal getMasterOrderQty() {
        return masterOrderQty;
    }

    public void setMasterOrderQty(BigDecimal masterOrderQty) {
        this.masterOrderQty = masterOrderQty;
    }

    public String getMasterOrderingItemName() {
        return masterOrderingItemName;
    }

    public void setMasterOrderingItemName(String masterOrderingItemName) {
        this.masterOrderingItemName = masterOrderingItemName == null ? null : masterOrderingItemName.trim();
    }

    public BigDecimal getMasterOrderingPrice() {
        return masterOrderingPrice;
    }

    public void setMasterOrderingPrice(BigDecimal masterOrderingPrice) {
        this.masterOrderingPrice = masterOrderingPrice;
    }

    public String getMixLogisticsCode() {
        return mixLogisticsCode;
    }

    public void setMixLogisticsCode(String mixLogisticsCode) {
        this.mixLogisticsCode = mixLogisticsCode == null ? null : mixLogisticsCode.trim();
    }

    public String getMixLogisticsName() {
        return mixLogisticsName;
    }

    public void setMixLogisticsName(String mixLogisticsName) {
        this.mixLogisticsName = mixLogisticsName == null ? null : mixLogisticsName.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}