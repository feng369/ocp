package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import java.math.BigDecimal;

public class OrderPubBox extends BaseEntity {

    private Long orderId;

    private String number;

    private BigDecimal clientWeight;

    private BigDecimal clientLength;

    private BigDecimal clientWidth;

    private BigDecimal clientHeight;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public BigDecimal getClientWeight() {
        return clientWeight;
    }

    public void setClientWeight(BigDecimal clientWeight) {
        this.clientWeight = clientWeight;
    }

    public BigDecimal getClientLength() {
        return clientLength;
    }

    public void setClientLength(BigDecimal clientLength) {
        this.clientLength = clientLength;
    }

    public BigDecimal getClientWidth() {
        return clientWidth;
    }

    public void setClientWidth(BigDecimal clientWidth) {
        this.clientWidth = clientWidth;
    }

    public BigDecimal getClientHeight() {
        return clientHeight;
    }

    public void setClientHeight(BigDecimal clientHeight) {
        this.clientHeight = clientHeight;
    }
}