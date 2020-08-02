package com.topideal.supplychain.ocp.jd.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName GuangZhouYunXiaoOrderEntity
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/3/4 16:40
 * @Version 1.0
 **/
public class GuangZhouYunXiaoOrderEntity implements Serializable {

    private String spSoNo;
    private String customModel;
    private String ebcCode;
    private String ebcName;
    private String ecpCode;
    private String ecpName;
    private String logisticsCode;
    private String logisticsName;
    private String dpdm;
    private String internetCode;
    private String customsCode;
    private String ciqOrgCode;
    private Date salesPlatformCreateTime;
    private String venderId;
    private String warehouseNo;
    private String buyerIdNumber;
    private String buyerIdType;
    private String buyerName;
    private String buyerPhone;
    private String buyerRegNo;
    private String consigneeAddress;
    private String consigneecountry;
    private String consigneeEmail;
    private String consigneeMobile;
    private String consigneeName;
    private String consigneeIdType;
    private String consigneeIdNumber;
    private String consigneePostcode;
    private String addressProvince;
    private String addressCity;
    private String addressCounty;
    private String addressTown;
    private String payCode;
    private String payName;
    private String payTransactionId;
    private String currency;
    private BigDecimal shouldPay;
    private BigDecimal goodsValue;
    private BigDecimal otherPrice;
    private BigDecimal discount;
    private String istax;
    private BigDecimal taxTotal;
    private BigDecimal freight;
    private BigDecimal insuredFee;
    private BigDecimal packNo;
    private String logisticsNo;

    public String getSpSoNo() {
        return spSoNo;
    }

    public void setSpSoNo(String spSoNo) {
        this.spSoNo = spSoNo;
    }

    public String getCustomModel() {
        return customModel;
    }

    public void setCustomModel(String customModel) {
        this.customModel = customModel;
    }

    public String getEbcCode() {
        return ebcCode;
    }

    public void setEbcCode(String ebcCode) {
        this.ebcCode = ebcCode;
    }

    public String getEbcName() {
        return ebcName;
    }

    public void setEbcName(String ebcName) {
        this.ebcName = ebcName;
    }

    public String getEcpCode() {
        return ecpCode;
    }

    public void setEcpCode(String ecpCode) {
        this.ecpCode = ecpCode;
    }

    public String getEcpName() {
        return ecpName;
    }

    public void setEcpName(String ecpName) {
        this.ecpName = ecpName;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getDpdm() {
        return dpdm;
    }

    public void setDpdm(String dpdm) {
        this.dpdm = dpdm;
    }

    public String getInternetCode() {
        return internetCode;
    }

    public void setInternetCode(String internetCode) {
        this.internetCode = internetCode;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public String getCiqOrgCode() {
        return ciqOrgCode;
    }

    public void setCiqOrgCode(String ciqOrgCode) {
        this.ciqOrgCode = ciqOrgCode;
    }

    public Date getSalesPlatformCreateTime() {
        return salesPlatformCreateTime;
    }

    public void setSalesPlatformCreateTime(Date salesPlatformCreateTime) {
        this.salesPlatformCreateTime = salesPlatformCreateTime;
    }

    public String getVenderId() {
        return venderId;
    }

    public void setVenderId(String venderId) {
        this.venderId = venderId;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public String getBuyerIdNumber() {
        return buyerIdNumber;
    }

    public void setBuyerIdNumber(String buyerIdNumber) {
        this.buyerIdNumber = buyerIdNumber;
    }

    public String getBuyerIdType() {
        return buyerIdType;
    }

    public void setBuyerIdType(String buyerIdType) {
        this.buyerIdType = buyerIdType;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerRegNo() {
        return buyerRegNo;
    }

    public void setBuyerRegNo(String buyerRegNo) {
        this.buyerRegNo = buyerRegNo;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneecountry() {
        return consigneecountry;
    }

    public void setConsigneecountry(String consigneecountry) {
        this.consigneecountry = consigneecountry;
    }

    public String getConsigneeEmail() {
        return consigneeEmail;
    }

    public void setConsigneeEmail(String consigneeEmail) {
        this.consigneeEmail = consigneeEmail;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeIdType() {
        return consigneeIdType;
    }

    public void setConsigneeIdType(String consigneeIdType) {
        this.consigneeIdType = consigneeIdType;
    }

    public String getConsigneeIdNumber() {
        return consigneeIdNumber;
    }

    public void setConsigneeIdNumber(String consigneeIdNumber) {
        this.consigneeIdNumber = consigneeIdNumber;
    }

    public String getConsigneePostcode() {
        return consigneePostcode;
    }

    public void setConsigneePostcode(String consigneePostcode) {
        this.consigneePostcode = consigneePostcode;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressCounty() {
        return addressCounty;
    }

    public void setAddressCounty(String addressCounty) {
        this.addressCounty = addressCounty;
    }

    public String getAddressTown() {
        return addressTown;
    }

    public void setAddressTown(String addressTown) {
        this.addressTown = addressTown;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPayTransactionId() {
        return payTransactionId;
    }

    public void setPayTransactionId(String payTransactionId) {
        this.payTransactionId = payTransactionId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getShouldPay() {
        return shouldPay;
    }

    public void setShouldPay(BigDecimal shouldPay) {
        this.shouldPay = shouldPay;
    }

    public BigDecimal getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(BigDecimal goodsValue) {
        this.goodsValue = goodsValue;
    }

    public BigDecimal getOtherPrice() {
        return otherPrice;
    }

    public void setOtherPrice(BigDecimal otherPrice) {
        this.otherPrice = otherPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getIstax() {
        return istax;
    }

    public void setIstax(String istax) {
        this.istax = istax;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getInsuredFee() {
        return insuredFee;
    }

    public void setInsuredFee(BigDecimal insuredFee) {
        this.insuredFee = insuredFee;
    }

    public BigDecimal getPackNo() {
        return packNo;
    }

    public void setPackNo(BigDecimal packNo) {
        this.packNo = packNo;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }
}
