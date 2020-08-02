package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import java.util.Date;

public class OrderYouzanEx extends BaseEntity {

    private Long orderId;

    private String isFromCart;

    private String cashierId;

    private String cashierName;

    private String invoiceTitle;

    private Date settleTime;

    private String isParentOrder;

    private String isSubOrder;

    private String fxOrderNo;

    private String fxKdtId;

    private String parentOrderNo;

    private String purchaseOrderNo;

    private String deptId;

    private String createDeviceId;

    private String isPointsOrder;

    private String idCardNumber;

    private String buyerName;

    private String isMember;

    private Long tmCash;

    private Long tCash;

    private Long cash;

    private String ordersCombineId;

    private String kdtDimensionCombineId;

    private String promotionCombineId;

    private String idCardName;

    private String fxOuterTransactionNo;

    private String fxInnerTransactionNo;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getIsFromCart() {
        return isFromCart;
    }

    public void setIsFromCart(String isFromCart) {
        this.isFromCart = isFromCart == null ? null : isFromCart.trim();
    }

    public String getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId == null ? null : cashierId.trim();
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName == null ? null : cashierName.trim();
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    public Date getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

    public String getIsParentOrder() {
        return isParentOrder;
    }

    public void setIsParentOrder(String isParentOrder) {
        this.isParentOrder = isParentOrder == null ? null : isParentOrder.trim();
    }

    public String getIsSubOrder() {
        return isSubOrder;
    }

    public void setIsSubOrder(String isSubOrder) {
        this.isSubOrder = isSubOrder == null ? null : isSubOrder.trim();
    }

    public String getFxOrderNo() {
        return fxOrderNo;
    }

    public void setFxOrderNo(String fxOrderNo) {
        this.fxOrderNo = fxOrderNo == null ? null : fxOrderNo.trim();
    }

    public String getFxKdtId() {
        return fxKdtId;
    }

    public void setFxKdtId(String fxKdtId) {
        this.fxKdtId = fxKdtId == null ? null : fxKdtId.trim();
    }

    public String getParentOrderNo() {
        return parentOrderNo;
    }

    public void setParentOrderNo(String parentOrderNo) {
        this.parentOrderNo = parentOrderNo == null ? null : parentOrderNo.trim();
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo == null ? null : purchaseOrderNo.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getCreateDeviceId() {
        return createDeviceId;
    }

    public void setCreateDeviceId(String createDeviceId) {
        this.createDeviceId = createDeviceId == null ? null : createDeviceId.trim();
    }

    public String getIsPointsOrder() {
        return isPointsOrder;
    }

    public void setIsPointsOrder(String isPointsOrder) {
        this.isPointsOrder = isPointsOrder == null ? null : isPointsOrder.trim();
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber == null ? null : idCardNumber.trim();
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember == null ? null : isMember.trim();
    }

    public Long getTmCash() {
        return tmCash;
    }

    public void setTmCash(Long tmCash) {
        this.tmCash = tmCash;
    }

    public Long gettCash() {
        return tCash;
    }

    public void settCash(Long tCash) {
        this.tCash = tCash;
    }

    public Long getCash() {
        return cash;
    }

    public void setCash(Long cash) {
        this.cash = cash;
    }

    public String getOrdersCombineId() {
        return ordersCombineId;
    }

    public void setOrdersCombineId(String ordersCombineId) {
        this.ordersCombineId = ordersCombineId == null ? null : ordersCombineId.trim();
    }

    public String getKdtDimensionCombineId() {
        return kdtDimensionCombineId;
    }

    public void setKdtDimensionCombineId(String kdtDimensionCombineId) {
        this.kdtDimensionCombineId = kdtDimensionCombineId == null ? null : kdtDimensionCombineId.trim();
    }

    public String getPromotionCombineId() {
        return promotionCombineId;
    }

    public void setPromotionCombineId(String promotionCombineId) {
        this.promotionCombineId = promotionCombineId == null ? null : promotionCombineId.trim();
    }

    public String getIdCardName() {
        return idCardName;
    }

    public void setIdCardName(String idCardName) {
        this.idCardName = idCardName == null ? null : idCardName.trim();
    }

    public String getFxOuterTransactionNo() {
        return fxOuterTransactionNo;
    }

    public void setFxOuterTransactionNo(String fxOuterTransactionNo) {
        this.fxOuterTransactionNo = fxOuterTransactionNo == null ? null : fxOuterTransactionNo.trim();
    }

    public String getFxInnerTransactionNo() {
        return fxInnerTransactionNo;
    }

    public void setFxInnerTransactionNo(String fxInnerTransactionNo) {
        this.fxInnerTransactionNo = fxInnerTransactionNo == null ? null : fxInnerTransactionNo.trim();
    }
}