package com.topideal.supplychain.ocp.dxy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName DxyOrderItemDto
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/6/30 15:54
 * @Version 1.0
 **/
public class DxyOrderResponse implements Serializable {

    private String orderId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date createTime;
    private BigDecimal realPayAmount;
    private BigDecimal discountAmount;
    private BigDecimal taxAmount;
    private BigDecimal freightAmount;
    private String userRemark;
    private String province;
    private String city;
    private String area;
    private String address;
    private String postCode;
    private String buyerName;
    private String buyerIdCard;
    private String buyerMobile;
    private String receiverName;
    private String receiverIdCard;
    private String receiverMobile;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date payTime;
    private String paymentNo;
    private String payType;
    private String payCompanyCode;
    private List<DxyOrderItemResponse> orderItems;
    private String platformCode;
    private String platformName;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getRealPayAmount() {
        return realPayAmount;
    }

    public void setRealPayAmount(BigDecimal realPayAmount) {
        this.realPayAmount = realPayAmount;
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

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerIdCard() {
        return buyerIdCard;
    }

    public void setBuyerIdCard(String buyerIdCard) {
        this.buyerIdCard = buyerIdCard;
    }

    public String getBuyerMobile() {
        return buyerMobile;
    }

    public void setBuyerMobile(String buyerMobile) {
        this.buyerMobile = buyerMobile;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverIdCard() {
        return receiverIdCard;
    }

    public void setReceiverIdCard(String receiverIdCard) {
        this.receiverIdCard = receiverIdCard;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayCompanyCode() {
        return payCompanyCode;
    }

    public void setPayCompanyCode(String payCompanyCode) {
        this.payCompanyCode = payCompanyCode;
    }

    public List<DxyOrderItemResponse> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<DxyOrderItemResponse> orderItems) {
        this.orderItems = orderItems;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
}
