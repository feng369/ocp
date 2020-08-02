package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.KjbYesNoEnum;
import com.topideal.supplychain.ocp.enums.SyncStatusEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 跨境宝订单
 * @author: syq
 * @create: 2019-12-16 17:34
 **/
public class OrderKjb extends BaseEntity {

    private String requestTimestamp;//请求时间戳

    private String code;//系统内部编码

    private String storeCode;//店铺编码

    private String logisticsCode;//物流企业编码

    private String logisticsNo;//运单号

    private String interLogisticsNo;//国际快递单号

    private String cainiaoLogisticsNo;//菜鸟物流单号

    private String tbStoreCode;//淘宝店铺编码

    private Date orderCreateTime;//订单创建时间

    private String overseaHouseCode;//海外仓编码

    private String esdNo;//卓志速运订单号

    private String tradeNo;//交易订单号

    private String payMerchantCode;//支付企业编码

    private String payMerchantName;//支付企业名称

    private String payNo;//支付流水号

    private String payRemark;//支付信息备注

    private String waybillUrl;//面单url

    private String declareSchemeSid;//申报方案编码

    private String productCode;//产品代码

    private BigDecimal freight;//运费

    private String freightCurrency;//运费币制

    private BigDecimal premium;//保费

    private String premiumCurrency;//保费币制

    private BigDecimal tax;//税费

    private String taxCurrency;//税费币制

    private BigDecimal discount;//折扣优惠金额

    private String discountCurrency;//折扣优惠金额币制

    private BigDecimal netWeight;//净重

    private BigDecimal grossWeight;//毛重

    private String billNo;//提单号

    private String platformCode;//平台编码

    private KjbYesNoEnum isTraceSource;//是否推溯源

    private String zcode;//真知码

    private BigDecimal actualPaid;//实际支付金额

    private String actualPaidCurrency;//实际支付金额币制

    private KjbYesNoEnum isTran;//是否换单

    private String remark;//备注

    private String senderName;//发件人名称

    private String senderPhone;//发件人电话

    private String senderMobile;//发件人手机

    private String senderCountry;//发件人国家

    private String senderProvince;//发件人省

    private String senderCity;//发件人市

    private String senderDistrict;//发件人区

    private String senderAddress;//发件人地址

    private String senderZip;//发件人邮编

    private String receiverName;//收货人名称

    private String receiverPhone;//收货人电话

    private String receiverMobile;//收货人手机

    private String receiverIdentityNo;//收货人证件号

    private String receiverIdentityFrontUrl;//收货人证件正面url

    private String receiverIdentityBackUrl;//收货人证件发面url

    private String receiverCountry;//收货人国家

    private String receiverProvince;//收货人省

    private String receiverCity;//收货人市

    private String receiverDistrict;//收货人区

    private String receiverAddress;//收货人地址

    private String receiverZip;//收货人邮编

    private String buyerName;//购买人名称

    private String buyerPhone;//购买人电话

    private String buyerMobile;//购买人手机

    private String buyerIdType;//购买人证件类型

    private String buyerIdentityNo;//购买人证件号

    private SyncStatusEnum status;//状态

    private String failReason;//失败原因

    private String serviceType;//服务类型

    private String destinationCode;//目的地编码

    private String createBeginTime;
    private String createEndTime;

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getCreateBeginTime() {
        return createBeginTime;
    }

    public void setCreateBeginTime(String createBeginTime) {
        this.createBeginTime = createBeginTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public String getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(String requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
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

    public String getInterLogisticsNo() {
        return interLogisticsNo;
    }

    public void setInterLogisticsNo(String interLogisticsNo) {
        this.interLogisticsNo = interLogisticsNo;
    }

    public String getCainiaoLogisticsNo() {
        return cainiaoLogisticsNo;
    }

    public void setCainiaoLogisticsNo(String cainiaoLogisticsNo) {
        this.cainiaoLogisticsNo = cainiaoLogisticsNo;
    }

    public String getTbStoreCode() {
        return tbStoreCode;
    }

    public void setTbStoreCode(String tbStoreCode) {
        this.tbStoreCode = tbStoreCode;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getOverseaHouseCode() {
        return overseaHouseCode;
    }

    public void setOverseaHouseCode(String overseaHouseCode) {
        this.overseaHouseCode = overseaHouseCode;
    }

    public String getEsdNo() {
        return esdNo;
    }

    public void setEsdNo(String esdNo) {
        this.esdNo = esdNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getPayMerchantCode() {
        return payMerchantCode;
    }

    public void setPayMerchantCode(String payMerchantCode) {
        this.payMerchantCode = payMerchantCode;
    }

    public String getPayMerchantName() {
        return payMerchantName;
    }

    public void setPayMerchantName(String payMerchantName) {
        this.payMerchantName = payMerchantName;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getPayRemark() {
        return payRemark;
    }

    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark;
    }

    public String getWaybillUrl() {
        return waybillUrl;
    }

    public void setWaybillUrl(String waybillUrl) {
        this.waybillUrl = waybillUrl;
    }

    public String getDeclareSchemeSid() {
        return declareSchemeSid;
    }

    public void setDeclareSchemeSid(String declareSchemeSid) {
        this.declareSchemeSid = declareSchemeSid;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getFreightCurrency() {
        return freightCurrency;
    }

    public void setFreightCurrency(String freightCurrency) {
        this.freightCurrency = freightCurrency;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public String getPremiumCurrency() {
        return premiumCurrency;
    }

    public void setPremiumCurrency(String premiumCurrency) {
        this.premiumCurrency = premiumCurrency;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public String getTaxCurrency() {
        return taxCurrency;
    }

    public void setTaxCurrency(String taxCurrency) {
        this.taxCurrency = taxCurrency;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDiscountCurrency() {
        return discountCurrency;
    }

    public void setDiscountCurrency(String discountCurrency) {
        this.discountCurrency = discountCurrency;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getZcode() {
        return zcode;
    }

    public void setZcode(String zcode) {
        this.zcode = zcode;
    }

    public BigDecimal getActualPaid() {
        return actualPaid;
    }

    public void setActualPaid(BigDecimal actualPaid) {
        this.actualPaid = actualPaid;
    }

    public String getActualPaidCurrency() {
        return actualPaidCurrency;
    }

    public void setActualPaidCurrency(String actualPaidCurrency) {
        this.actualPaidCurrency = actualPaidCurrency;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile;
    }

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry;
    }

    public String getSenderProvince() {
        return senderProvince;
    }

    public void setSenderProvince(String senderProvince) {
        this.senderProvince = senderProvince;
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity;
    }

    public String getSenderDistrict() {
        return senderDistrict;
    }

    public void setSenderDistrict(String senderDistrict) {
        this.senderDistrict = senderDistrict;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderZip() {
        return senderZip;
    }

    public void setSenderZip(String senderZip) {
        this.senderZip = senderZip;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverIdentityNo() {
        return receiverIdentityNo;
    }

    public void setReceiverIdentityNo(String receiverIdentityNo) {
        this.receiverIdentityNo = receiverIdentityNo;
    }

    public String getReceiverIdentityFrontUrl() {
        return receiverIdentityFrontUrl;
    }

    public void setReceiverIdentityFrontUrl(String receiverIdentityFrontUrl) {
        this.receiverIdentityFrontUrl = receiverIdentityFrontUrl;
    }

    public String getReceiverIdentityBackUrl() {
        return receiverIdentityBackUrl;
    }

    public void setReceiverIdentityBackUrl(String receiverIdentityBackUrl) {
        this.receiverIdentityBackUrl = receiverIdentityBackUrl;
    }

    public String getReceiverCountry() {
        return receiverCountry;
    }

    public void setReceiverCountry(String receiverCountry) {
        this.receiverCountry = receiverCountry;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip;
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

    public String getBuyerMobile() {
        return buyerMobile;
    }

    public void setBuyerMobile(String buyerMobile) {
        this.buyerMobile = buyerMobile;
    }

    public String getBuyerIdType() {
        return buyerIdType;
    }

    public void setBuyerIdType(String buyerIdType) {
        this.buyerIdType = buyerIdType;
    }

    public String getBuyerIdentityNo() {
        return buyerIdentityNo;
    }

    public void setBuyerIdentityNo(String buyerIdentityNo) {
        this.buyerIdentityNo = buyerIdentityNo;
    }

    public SyncStatusEnum getStatus() {
        return status;
    }

    public void setStatus(SyncStatusEnum status) {
        this.status = status;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public KjbYesNoEnum getIsTraceSource() {
        return isTraceSource;
    }

    public void setIsTraceSource(KjbYesNoEnum isTraceSource) {
        this.isTraceSource = isTraceSource;
    }

    public KjbYesNoEnum getIsTran() {
        return isTran;
    }

    public void setIsTran(KjbYesNoEnum isTran) {
        this.isTran = isTran;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }
}
