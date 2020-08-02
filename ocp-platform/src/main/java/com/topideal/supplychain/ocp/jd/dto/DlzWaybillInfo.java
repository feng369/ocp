package com.topideal.supplychain.ocp.jd.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName WaybillInfo
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/2/26 15:59
 * @Version 1.0
 **/
public class DlzWaybillInfo implements Serializable {

    private String logisticsId;
    @JsonAlias({"OrderId","orderId"})
    private String orderId;
    @JsonAlias({"CreateTime","createTime"})
    @JsonFormat(pattern="yyyyMMddHHmmss",timezone="GMT+8")
    private Date createTime;
    @JsonAlias({"IEFlag","ieFlag"})
    private String ieFlag;
    @JsonAlias({"CustomsType","customsType"})
    private String customsType;
    @JsonAlias({"EntRecordNo","carrier"})
    private String entRecordNo;
    @JsonAlias({"OrderType","orderType"})
    private String orderType;
    @JsonAlias({"WayBillNo","wayBillNo"})
    private String wayBillNo;
    @JsonAlias({"Freight","freight"})
    private String freight;
    @JsonAlias({"FreightCurr","freightCurr"})
    private String freightCurr;
    @JsonAlias({"Tax","tax"})
    private String tax;
    @JsonAlias({"TaxCurr","taxCurr"})
    private String taxCurr;
    @JsonAlias({"Num","num"})
    private String num;
    @JsonAlias({"GoodInfo","goodInfo"})
    private String goodInfo;
    @JsonAlias({"InsuredFee","insuredFee"})
    private String insuredFee;
    @JsonAlias({"BuyerPhone","buyerPhone"})
    private String buyerPhone;
    @JsonAlias({"BuyerName","buyerName"})
    private String buyerName;
    @JsonAlias({"BuyerIdType","buyerIdType"})
    private String buyerIdType;
    @JsonAlias({"BuyerIdNumber","buyerIdNumber"})
    private String buyerIdNumber;
    @JsonAlias({"RecipientName","recipientName"})
    private String recipientName;
    @JsonAlias({"RecipientProvincesName","recipientProvincesName"})
    private String recipientProvincesName;
    @JsonAlias({"RecipientProvincesCode","recipientProvincesCode"})
    private String recipientProvincesCode;
    @JsonAlias({"RecipientDetailedAddress","recipientDetailedAddress"})
    private String recipientDetailedAddress;
    @JsonAlias({"RecipientPhone","recipientPhone"})
    private String recipientPhone;
    @JsonAlias({"ShipperCountryCode","shipperCountryCode"})
    private String shipperCountryCode;
    @JsonAlias({"VenderId","venderId"})
    private String venderId;
    @JsonAlias({"ModelId","modelId"})
    private String modelId;
    @JsonAlias({"StoreId","storeId"})
    private String storeId;
    @JsonAlias("eclpCode")
    private String eclpCode;
    @JsonAlias("platformId")
    private String platformId;
    @JsonAlias("platformName")
    private String platformName;
    @JsonAlias("orderSum")
    private BigDecimal orderSum;
    @JsonAlias("discount")
    private BigDecimal discount;
    @JsonAlias("discountNote")
    private String discountNote;


    public String getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(String logisticsId) {
        this.logisticsId = logisticsId;
    }

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

    public String getIeFlag() {
        return ieFlag;
    }

    public void setIeFlag(String ieFlag) {
        this.ieFlag = ieFlag;
    }

    public String getCustomsType() {
        return customsType;
    }

    public void setCustomsType(String customsType) {
        this.customsType = customsType;
    }

    public String getEntRecordNo() {
        return entRecordNo;
    }

    public void setEntRecordNo(String entRecordNo) {
        this.entRecordNo = entRecordNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getWayBillNo() {
        return wayBillNo;
    }

    public void setWayBillNo(String wayBillNo) {
        this.wayBillNo = wayBillNo;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getFreightCurr() {
        return freightCurr;
    }

    public void setFreightCurr(String freightCurr) {
        this.freightCurr = freightCurr;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTaxCurr() {
        return taxCurr;
    }

    public void setTaxCurr(String taxCurr) {
        this.taxCurr = taxCurr;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getGoodInfo() {
        return goodInfo;
    }

    public void setGoodInfo(String goodInfo) {
        this.goodInfo = goodInfo;
    }

    public String getInsuredFee() {
        return insuredFee;
    }

    public void setInsuredFee(String insuredFee) {
        this.insuredFee = insuredFee;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerIdType() {
        return buyerIdType;
    }

    public void setBuyerIdType(String buyerIdType) {
        this.buyerIdType = buyerIdType;
    }

    public String getBuyerIdNumber() {
        return buyerIdNumber;
    }

    public void setBuyerIdNumber(String buyerIdNumber) {
        this.buyerIdNumber = buyerIdNumber;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientProvincesName() {
        return recipientProvincesName;
    }

    public void setRecipientProvincesName(String recipientProvincesName) {
        this.recipientProvincesName = recipientProvincesName;
    }

    public String getRecipientProvincesCode() {
        return recipientProvincesCode;
    }

    public void setRecipientProvincesCode(String recipientProvincesCode) {
        this.recipientProvincesCode = recipientProvincesCode;
    }

    public String getRecipientDetailedAddress() {
        return recipientDetailedAddress;
    }

    public void setRecipientDetailedAddress(String recipientDetailedAddress) {
        this.recipientDetailedAddress = recipientDetailedAddress;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getShipperCountryCode() {
        return shipperCountryCode;
    }

    public void setShipperCountryCode(String shipperCountryCode) {
        this.shipperCountryCode = shipperCountryCode;
    }

    public String getVenderId() {
        return venderId;
    }

    public void setVenderId(String venderId) {
        this.venderId = venderId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getEclpCode() {
        return eclpCode;
    }

    public void setEclpCode(String eclpCode) {
        this.eclpCode = eclpCode;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public BigDecimal getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(BigDecimal orderSum) {
        this.orderSum = orderSum;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDiscountNote() {
        return discountNote;
    }

    public void setDiscountNote(String discountNote) {
        this.discountNote = discountNote;
    }
}
