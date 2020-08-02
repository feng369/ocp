package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;

import java.math.BigDecimal;

public class OrderAmwayPaymentLine extends BaseEntity {

    /**
     * 付款方式
     */
    private String paymentMethod;

    /**
     * 付款金额
     */
    private BigDecimal paymentLineAmount;

    /**
     * 付款名称
     */
    private String paymentMethodName;

    private String orderNumber;

    private Long orderId;


    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod == null ? null : paymentMethod.trim();
    }

    public BigDecimal getPaymentLineAmount() {
        return paymentLineAmount;
    }

    public void setPaymentLineAmount(BigDecimal paymentLineAmount) {
        this.paymentLineAmount = paymentLineAmount;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName == null ? null : paymentMethodName.trim();
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