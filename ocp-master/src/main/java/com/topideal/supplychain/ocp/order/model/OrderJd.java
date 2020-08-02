package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.BuyeridTypeEnum;
import com.topideal.supplychain.ocp.enums.CustomModelEnum;
import com.topideal.supplychain.ocp.enums.CustomsTypeEnum;
import com.topideal.supplychain.ocp.enums.JdOrderEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import java.math.BigDecimal;
import java.util.Date;

public class OrderJd extends BaseEntity {

    private String code;

    private String orderId;

    private Date orderCreateTime;

    private String ieFlag;

    private CustomsTypeEnum customsType;

    private String logisticsCode;//EntRecordNo

    private String logisticsNo;//waybillNo;

    private String logisticsName;

    private JdOrderEnum orderType;

    private BigDecimal shouldPay;

    private BigDecimal goodsValue;

    private BigDecimal otherPrice;

    private BigDecimal discount;

    private String discountNote;

    private BigDecimal freight;

    private String freightCurr;

    private BigDecimal tax;

    private String taxCurr;

    private String goodInfo;

    private BigDecimal insuredFee;

    private String buyerPhone;

    private String buyerName;

    private BuyeridTypeEnum buyerIdType;

    private String buyerIdNumber;

    private String buyerRegNo;

    private String consigneeName;//recipientName;

    //private String RecipientProvincesName;

    private String recipientProvincesCode;

    //private String RecipientDetailedAddress;

    private String consigneePhone;//recipientPhone;

    private String consigneeAddress;

    private String consigneeCountry;

    private String consigneeEmail;

    private BuyeridTypeEnum consigneeIdType;

    private String consigneeIdNumber;

    private String consigneePostCode;

    private String consigneeProvince;

    private String consigneeCity;

    private String consigneeCounty;

    private String consigneeTown;

    private String shipperCountryCode;

    private BigDecimal packNo;

    private String venderId;

    private CustomModelEnum customModel;

    private String vQyState;

    private String ciqbCode;

    private String customsCode;

    private String payCode;

    private String payName;

    private String payTransactionId;

    private String ebcCode;

    private String ebcName;

    private String ebpCode;

    private String ebpName;

    private String dpdm;

    private String internetCode;

    private String eclpCode;


    private String currency;

    private String warehouseNo;

    private String storeId;

    private String isTax;

    private String grabKey;

    private String thirdPlatformCode;

    private String thirdPlatformName;
    /**
     * 订单状态，10、20、60、70对应制单、已抓取详细、下发失败、下发成功
     */
    private OrderStatusEnum status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getIeFlag() {
        return ieFlag;
    }

    public void setIeFlag(String ieFlag) {
        this.ieFlag = ieFlag;
    }

    public CustomsTypeEnum getCustomsType() {
        return customsType;
    }

    public void setCustomsType(CustomsTypeEnum customsType) {
        this.customsType = customsType;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public JdOrderEnum getOrderType() {
        return orderType;
    }

    public void setOrderType(JdOrderEnum orderType) {
        this.orderType = orderType;
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

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getFreightCurr() {
        return freightCurr;
    }

    public void setFreightCurr(String freightCurr) {
        this.freightCurr = freightCurr;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public String getTaxCurr() {
        return taxCurr;
    }

    public void setTaxCurr(String taxCurr) {
        this.taxCurr = taxCurr;
    }

    public String getGoodInfo() {
        return goodInfo;
    }

    public void setGoodInfo(String goodInfo) {
        this.goodInfo = goodInfo;
    }

    public BigDecimal getInsuredFee() {
        return insuredFee;
    }

    public void setInsuredFee(BigDecimal insuredFee) {
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

    public BuyeridTypeEnum getBuyerIdType() {
        return buyerIdType;
    }

    public void setBuyerIdType(BuyeridTypeEnum buyerIdType) {
        this.buyerIdType = buyerIdType;
    }

    public String getBuyerIdNumber() {
        return buyerIdNumber;
    }

    public void setBuyerIdNumber(String buyerIdNumber) {
        this.buyerIdNumber = buyerIdNumber;
    }

    public String getBuyerRegNo() {
        return buyerRegNo;
    }

    public void setBuyerRegNo(String buyerRegNo) {
        this.buyerRegNo = buyerRegNo;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getRecipientProvincesCode() {
        return recipientProvincesCode;
    }

    public void setRecipientProvincesCode(String recipientProvincesCode) {
        this.recipientProvincesCode = recipientProvincesCode;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneeCountry() {
        return consigneeCountry;
    }

    public void setConsigneeCountry(String consigneeCountry) {
        this.consigneeCountry = consigneeCountry;
    }

    public String getConsigneeEmail() {
        return consigneeEmail;
    }

    public void setConsigneeEmail(String consigneeEmail) {
        this.consigneeEmail = consigneeEmail;
    }

    public BuyeridTypeEnum getConsigneeIdType() {
        return consigneeIdType;
    }

    public void setConsigneeIdType(BuyeridTypeEnum consigneeIdType) {
        this.consigneeIdType = consigneeIdType;
    }

    public String getConsigneeIdNumber() {
        return consigneeIdNumber;
    }

    public void setConsigneeIdNumber(String consigneeIdNumber) {
        this.consigneeIdNumber = consigneeIdNumber;
    }

    public String getConsigneePostCode() {
        return consigneePostCode;
    }

    public void setConsigneePostCode(String consigneePostCode) {
        this.consigneePostCode = consigneePostCode;
    }

    public String getConsigneeProvince() {
        return consigneeProvince;
    }

    public void setConsigneeProvince(String consigneeProvince) {
        this.consigneeProvince = consigneeProvince;
    }

    public String getConsigneeCity() {
        return consigneeCity;
    }

    public void setConsigneeCity(String consigneeCity) {
        this.consigneeCity = consigneeCity;
    }

    public String getConsigneeCounty() {
        return consigneeCounty;
    }

    public void setConsigneeCounty(String consigneeCounty) {
        this.consigneeCounty = consigneeCounty;
    }

    public String getConsigneeTown() {
        return consigneeTown;
    }

    public void setConsigneeTown(String consigneeTown) {
        this.consigneeTown = consigneeTown;
    }

    public String getShipperCountryCode() {
        return shipperCountryCode;
    }

    public void setShipperCountryCode(String shipperCountryCode) {
        this.shipperCountryCode = shipperCountryCode;
    }

    public BigDecimal getPackNo() {
        return packNo;
    }

    public void setPackNo(BigDecimal packNo) {
        this.packNo = packNo;
    }

    public String getVenderId() {
        return venderId;
    }

    public void setVenderId(String venderId) {
        this.venderId = venderId;
    }

    public CustomModelEnum getCustomModel() {
        return customModel;
    }

    public void setCustomModel(CustomModelEnum customModel) {
        this.customModel = customModel;
    }

    public String getvQyState() {
        return vQyState;
    }

    public void setvQyState(String vQyState) {
        this.vQyState = vQyState;
    }

    public String getCiqbCode() {
        return ciqbCode;
    }

    public void setCiqbCode(String ciqbCode) {
        this.ciqbCode = ciqbCode;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
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

    public String getEbpCode() {
        return ebpCode;
    }

    public void setEbpCode(String ebpCode) {
        this.ebpCode = ebpCode;
    }

    public String getEbpName() {
        return ebpName;
    }

    public void setEbpName(String ebpName) {
        this.ebpName = ebpName;
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

    public String getEclpCode() {
        return eclpCode;
    }

    public void setEclpCode(String eclpCode) {
        this.eclpCode = eclpCode;
    }


    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public String getIsTax() {
        return isTax;
    }

    public void setIsTax(String isTax) {
        this.isTax = isTax;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public String getGrabKey() {
        return grabKey;
    }

    public void setGrabKey(String grabKey) {
        this.grabKey = grabKey;
    }

    public String getThirdPlatformCode() {
        return thirdPlatformCode;
    }

    public void setThirdPlatformCode(String thirdPlatformCode) {
        this.thirdPlatformCode = thirdPlatformCode;
    }

    public String getThirdPlatformName() {
        return thirdPlatformName;
    }

    public void setThirdPlatformName(String thirdPlatformName) {
        this.thirdPlatformName = thirdPlatformName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getDiscountNote() {
        return discountNote;
    }

    public void setDiscountNote(String discountNote) {
        this.discountNote = discountNote;
    }
}
