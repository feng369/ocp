package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

public class OrderYouzanPayments extends BaseEntity {

    private Long orderId;

    private String innerTransactionNo;

    private Date payStartTime;

    private Date payEndTime;

    private String outerTransactionNo;

    private Long phase;

    private BigDecimal realPrice;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getInnerTransactionNo() {
        return innerTransactionNo;
    }

    public void setInnerTransactionNo(String innerTransactionNo) {
        this.innerTransactionNo = innerTransactionNo == null ? null : innerTransactionNo.trim();
    }

    public Date getPayStartTime() {
        return payStartTime;
    }

    public void setPayStartTime(Date payStartTime) {
        this.payStartTime = payStartTime;
    }

    public Date getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(Date payEndTime) {
        this.payEndTime = payEndTime;
    }

    public String getOuterTransactionNo() {
        return outerTransactionNo;
    }

    public void setOuterTransactionNo(String outerTransactionNo) {
        this.outerTransactionNo = outerTransactionNo == null ? null : outerTransactionNo.trim();
    }

    public Long getPhase() {
        return phase;
    }

    public void setPhase(Long phase) {
        this.phase = phase;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }


}