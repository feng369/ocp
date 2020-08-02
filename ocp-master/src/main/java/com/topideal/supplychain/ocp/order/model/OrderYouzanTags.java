package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;

public class OrderYouzanTags extends BaseEntity {

    private Long orderId;

    private Boolean isVirtual;

    private Boolean isPurchaseOrder;

    private Boolean isFenxiaoOrder;

    private Boolean isMember;

    private Boolean isPreorder;

    private Boolean isOfflineOrder;

    private Boolean isMultiStore;

    private Boolean isSettle;

    private Boolean isPayed;

    private Boolean isSecuredTransactions;

    private Boolean isPostageFree;

    private Boolean isFeedback;

    private Boolean isRefund;

    private Boolean isDownPaymentPre;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Boolean getIsVirtual() {
        return isVirtual;
    }

    public void setIsVirtual(Boolean isVirtual) {
        this.isVirtual = isVirtual;
    }

    public Boolean getIsPurchaseOrder() {
        return isPurchaseOrder;
    }

    public void setIsPurchaseOrder(Boolean isPurchaseOrder) {
        this.isPurchaseOrder = isPurchaseOrder;
    }

    public Boolean getIsFenxiaoOrder() {
        return isFenxiaoOrder;
    }

    public void setIsFenxiaoOrder(Boolean isFenxiaoOrder) {
        this.isFenxiaoOrder = isFenxiaoOrder;
    }

    public Boolean getIsMember() {
        return isMember;
    }

    public void setIsMember(Boolean isMember) {
        this.isMember = isMember;
    }

    public Boolean getIsPreorder() {
        return isPreorder;
    }

    public void setIsPreorder(Boolean isPreorder) {
        this.isPreorder = isPreorder;
    }

    public Boolean getIsOfflineOrder() {
        return isOfflineOrder;
    }

    public void setIsOfflineOrder(Boolean isOfflineOrder) {
        this.isOfflineOrder = isOfflineOrder;
    }

    public Boolean getIsMultiStore() {
        return isMultiStore;
    }

    public void setIsMultiStore(Boolean isMultiStore) {
        this.isMultiStore = isMultiStore;
    }

    public Boolean getIsSettle() {
        return isSettle;
    }

    public void setIsSettle(Boolean isSettle) {
        this.isSettle = isSettle;
    }

    public Boolean getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Boolean isPayed) {
        this.isPayed = isPayed;
    }

    public Boolean getIsSecuredTransactions() {
        return isSecuredTransactions;
    }

    public void setIsSecuredTransactions(Boolean isSecuredTransactions) {
        this.isSecuredTransactions = isSecuredTransactions;
    }

    public Boolean getIsPostageFree() {
        return isPostageFree;
    }

    public void setIsPostageFree(Boolean isPostageFree) {
        this.isPostageFree = isPostageFree;
    }

    public Boolean getIsFeedback() {
        return isFeedback;
    }

    public void setIsFeedback(Boolean isFeedback) {
        this.isFeedback = isFeedback;
    }

    public Boolean getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(Boolean isRefund) {
        this.isRefund = isRefund;
    }

    public Boolean getIsDownPaymentPre() {
        return isDownPaymentPre;
    }

    public void setIsDownPaymentPre(Boolean isDownPaymentPre) {
        this.isDownPaymentPre = isDownPaymentPre;
    }
}