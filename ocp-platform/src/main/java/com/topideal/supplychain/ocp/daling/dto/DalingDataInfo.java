package com.topideal.supplychain.ocp.daling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author xuxiaoyan
 * @date 2019-12-16 10:13
 */
public class DalingDataInfo implements Serializable{

    private static final long serialVersionUID = -4634834405047372479L;

    @JsonProperty("result")
    private Integer result;

    @JsonProperty("sale_platform")
    private String salePlatform;

    @JsonProperty("order_no")
    private String orderNo;

    @JsonProperty("customer_name")
    private String customerName;

    @JsonProperty("postcode")
    private String postCode;

    @JsonProperty("contact_phone")
    private String contactPhone;

    @JsonProperty("address")
    private String address;

    @JsonProperty("id_card")
    private String idCard;

    @JsonProperty("status")
    private String status;

    @JsonProperty("logistics_company")
    private String logisticsCompany;

    @JsonProperty("logistics_number")
    private String logisticsNumber;

    @JsonProperty("created_date")
    private String createdDate;

    @JsonProperty("is_box")
    private String isBox;

    @JsonProperty("is_hide_ao")
    private String isHideAo;

    @JsonProperty("order_amount")
    private BigDecimal orderAmount;

    @JsonProperty("tax_amount")
    private BigDecimal taxAmount;

    @JsonProperty("payment_amount")
    private BigDecimal paymentAmount;

    @JsonProperty("mode_of_payment")
    private String modeOfPayment;

    @JsonProperty("payment_transaction")
    private String paymentTransaction;

    @JsonProperty("discount_total_price")
    private BigDecimal discountTotalPrice;

    @JsonProperty("freight_price")
    private BigDecimal freightPrice;

    @JsonProperty("vendor_code")
    private String vendorCode;

    @JsonProperty("transport_type")
    private String transportType;

    @JsonProperty("buyer_id_number")
    private String buyerIdNumber;

    @JsonProperty("buyer_name")
    private String buyerName;

    @JsonProperty("orderItem")
    private List<DalingDataInfoItem> orderItem;

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getSalePlatform() {
        return salePlatform;
    }

    public void setSalePlatform(String salePlatform) {
        this.salePlatform = salePlatform;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getIsBox() {
        return isBox;
    }

    public void setIsBox(String isBox) {
        this.isBox = isBox;
    }

    public String getIsHideAo() {
        return isHideAo;
    }

    public void setIsHideAo(String isHideAo) {
        this.isHideAo = isHideAo;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getPaymentTransaction() {
        return paymentTransaction;
    }

    public void setPaymentTransaction(String paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }

    public BigDecimal getDiscountTotalPrice() {
        return discountTotalPrice;
    }

    public void setDiscountTotalPrice(BigDecimal discountTotalPrice) {
        this.discountTotalPrice = discountTotalPrice;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getBuyerIdNumber() {
        return buyerIdNumber;
    }

    public void setBuyerIdNumber(String buyerIdNumber) {
        this.buyerIdNumber = buyerIdNumber;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public List<DalingDataInfoItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(
            List<DalingDataInfoItem> orderItem) {
        this.orderItem = orderItem;
    }
}
