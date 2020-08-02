package com.topideal.supplychain.ocp.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.SyncStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderPub extends BaseEntity {

    /**
     * 系统内部单号
     */
    private String code;


    /**
     * 店铺编码
     */
    private String storeCode;

    /**
     * 物流公司编码
     */
    private String logisticsCode;

    /**
     * 物流运单号
     */
    private String logisticsNo;

    /**
     * 国际快递单号
     */
    private String transportNo;

    /**
     * 菜鸟物流单号
     */
    private String logisticsId;

    /**
     * 淘宝店铺编码
     */
    private String sellerCode;

    /**
     * 订单创建时间
     */
    private Date orderCreateTime;

    /**
     * 海外仓编码
     */
    private String warehouseCode;

    /**
     * 卓志速运订单号
     */
    private String esdNo;

    /**
     * 交易订单号
     */
    private String orderNo;

    /**
     * 支付企业编码
     */
    private String paymentEnterprise;

    /**
     * 支付企业名称
     */
    private String paymentEnterpriseName;

    /**
     * 支付流水号
     */
    private String paymentTransaction;

    /**
     * 支付信息备注
     */
    private String paymentRemark;

    /**
     * 面单url
     */
    private String wayBillUrl;

    /**
     * 申报方案编码
     */
    private String declareSchemeSid;

    /**
     * 产品代码
     */
    private String productCode;

    /**
     * 运费
     */
    private BigDecimal totalFreight;

    /**
     * 运费币制
     */
    private String totalCode;

    /**
     * 保费
     */
    private BigDecimal premiumFee;

    /**
     * 保费币制
     */
    private String premiumCurrency;

    /**
     * 税费
     */
    private BigDecimal totalTaxes;

    /**
     * 税费币制
     */
    private String taxCurrency;

    /**
     * 优惠减免金额
     */
    private BigDecimal discountFee;

    /**
     * 优惠减免金额币制
     */
    private String discountCurrency;

    /**
     * 包裹总净重
     */
    private BigDecimal netWeight;

    /**
     * 包裹总毛重
     */
    private BigDecimal grossWeight;

    /**
     * 提单号
     */
    private String billNo;

    /**
     * 电商平台编码
     */
    private String platformCode;

    /**
     * 是否推溯源(1是，2否)
     */
    private Integer isTraceSource;

    /**
     * 是否溯源页面展示
     */
    private String isTrace;

    /**
     * 溯源码值
     */
    private String zcode;

    /**
     * 实际支付金额
     */
    private BigDecimal totalTaxesPay;

    /**
     * 支付币制
     */
    private String totalPayCode;

    /**
     * 换单标志（1是；2否）
     */
    private String isTransfer;

    /**
     * 是否换单页面展示
     */
    private String isTrans;

    /**
     * 目的地代码
     */
    private String destinationCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 发件人姓名
     */
    private String senderName;

    /**
     * 发件人电话
     */
    private String senderPhone;

    /**
     * 发件人手机
     */
    private String senderMobile;

    /**
     * 发件人国
     */
    private String senderCountry;

    /**
     * 发件人省
     */
    private String senderProvince;

    /**
     * 发件人市
     */
    private String senderCity;

    /**
     * 发件人区
     */
    private String senderCounty;

    /**
     * 发件人地址
     */
    private String senderAddress;

    /**
     * 发件人邮编
     */
    private String senderZipCode;

    /**
     * 收件人姓名
     */
    private String receiverName;

    /**
     * 收件人电话
     */
    private String receiverPhone;

    /**
     * 收件人电话
     */
    private String receiverMobile;

    /**
     * 收件人身份证号码
     */
    private String receiverIdentityNo;

    /**
     * 收件人身份证正面url
     */
    private String receiverIdentityNoFront;

    /**
     * 收件人身份证反面url
     */
    private String receiverIdentityNoBack;

    /**
     * 收件人国
     */
    private String receiverCountry;

    /**
     * 收件人省
     */
    private String receiverProvince;

    /**
     * 收件人市
     */
    private String receiverCity;

    /**
     * 收件人区
     */
    private String receiverCounty;

    /**
     * 收件人地址
     */
    private String receiverAddress;

    /**
     * 收件人邮编
     */
    private String receiverZipCode;

    /**
     * 订购人姓名
     */
    private String buyerName;

    /**
     * 订购人电话
     */
    private String buyerPhone;

    /**
     * 订购人注册号
     */
    private String buyerMobile;

    /**
     * 订购人证件类型
     */
    private String buyerIdType;

    /**
     * 订购人证件号码
     */
    private String buyerIdentityNo;

    /**
     * 推单状态 10：未同步 20：已同步  30：同步失败
     */
    private SyncStatusEnum status;

    /**
     * 失败原因
     */
    private String failReason;

    private String ieFlag;//进出口标记

    private String packageType;//包裹类型

    private Integer parcelCount;//总箱数

    private String orderCode;//第三方订单号

    private BigDecimal length;//包裹长度

    private BigDecimal width;//包裹宽度

    private BigDecimal height;//包裹高度

    private String taxId;//中邮税号

    private String totalPackageNo;//总包号

    private String packageKind;//包裹属性

    private String isReturn;//是否可退回

    private Date delayedTime;//延迟发货时间

    private String senderStreet;//发件人街道

    private String sendNum;//发件人门牌号

    private String identityType;//证件类型，1:身份证；2:护照，默认1

    private String receiverProvinceCode;//收件人省份/州编码

    private String receiverStreet;//收件人所在街道
    private String address2;//收件人详细地址2
    private String address3;//收件人详细地址3
    private String receiverHouseNo;//收件人门牌号
    private String receiverEmail;//收件人邮箱


    private String tid;//订单号。部分平台使用

    private String packageNo;//包裹号，部分平台使用

    private Date paymentTime;//支付时间

    private Integer source;//订单来源

    /**
     * 服务类型
     */
    private String serviceType;

    /**
     * 商品列表
     */
    private List<OrderPubItem> goodsList;

    private List<OrderPubBox> boxList;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode == null ? null : storeCode.trim();
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode == null ? null : logisticsCode.trim();
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo == null ? null : logisticsNo.trim();
    }

    public String getTransportNo() {
        return transportNo;
    }

    public void setTransportNo(String transportNo) {
        this.transportNo = transportNo == null ? null : transportNo.trim();
    }

    public String getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(String logisticsId) {
        this.logisticsId = logisticsId == null ? null : logisticsId.trim();
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode == null ? null : sellerCode.trim();
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode == null ? null : warehouseCode.trim();
    }

    public String getEsdNo() {
        return esdNo;
    }

    public void setEsdNo(String esdNo) {
        this.esdNo = esdNo == null ? null : esdNo.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getPaymentEnterprise() {
        return paymentEnterprise;
    }

    public void setPaymentEnterprise(String paymentEnterprise) {
        this.paymentEnterprise = paymentEnterprise == null ? null : paymentEnterprise.trim();
    }

    public String getPaymentEnterpriseName() {
        return paymentEnterpriseName;
    }

    public void setPaymentEnterpriseName(String paymentEnterpriseName) {
        this.paymentEnterpriseName = paymentEnterpriseName == null ? null : paymentEnterpriseName.trim();
    }

    public String getPaymentTransaction() {
        return paymentTransaction;
    }

    public void setPaymentTransaction(String paymentTransaction) {
        this.paymentTransaction = paymentTransaction == null ? null : paymentTransaction.trim();
    }

    public String getPaymentRemark() {
        return paymentRemark;
    }

    public void setPaymentRemark(String paymentRemark) {
        this.paymentRemark = paymentRemark == null ? null : paymentRemark.trim();
    }

    public String getWayBillUrl() {
        return wayBillUrl;
    }

    public void setWayBillUrl(String wayBillUrl) {
        this.wayBillUrl = wayBillUrl == null ? null : wayBillUrl.trim();
    }

    public String getDeclareSchemeSid() {
        return declareSchemeSid;
    }

    public void setDeclareSchemeSid(String declareSchemeSid) {
        this.declareSchemeSid = declareSchemeSid == null ? null : declareSchemeSid.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public BigDecimal getTotalFreight() {
        return totalFreight;
    }

    public void setTotalFreight(BigDecimal totalFreight) {
        this.totalFreight = totalFreight;
    }

    public String getTotalCode() {
        return totalCode;
    }

    public void setTotalCode(String totalCode) {
        this.totalCode = totalCode == null ? null : totalCode.trim();
    }

    public BigDecimal getPremiumFee() {
        return premiumFee;
    }

    public void setPremiumFee(BigDecimal premiumFee) {
        this.premiumFee = premiumFee;
    }

    public String getPremiumCurrency() {
        return premiumCurrency;
    }

    public void setPremiumCurrency(String premiumCurrency) {
        this.premiumCurrency = premiumCurrency == null ? null : premiumCurrency.trim();
    }

    public BigDecimal getTotalTaxes() {
        return totalTaxes;
    }

    public void setTotalTaxes(BigDecimal totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public String getTaxCurrency() {
        return taxCurrency;
    }

    public void setTaxCurrency(String taxCurrency) {
        this.taxCurrency = taxCurrency == null ? null : taxCurrency.trim();
    }

    public BigDecimal getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(BigDecimal discountFee) {
        this.discountFee = discountFee;
    }

    public String getDiscountCurrency() {
        return discountCurrency;
    }

    public void setDiscountCurrency(String discountCurrency) {
        this.discountCurrency = discountCurrency == null ? null : discountCurrency.trim();
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
        this.billNo = billNo == null ? null : billNo.trim();
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode == null ? null : platformCode.trim();
    }

    public String getZcode() {
        return zcode;
    }

    public void setZcode(String zcode) {
        this.zcode = zcode == null ? null : zcode.trim();
    }

    public BigDecimal getTotalTaxesPay() {
        return totalTaxesPay;
    }

    public void setTotalTaxesPay(BigDecimal totalTaxesPay) {
        this.totalTaxesPay = totalTaxesPay;
    }

    public String getTotalPayCode() {
        return totalPayCode;
    }

    public void setTotalPayCode(String totalPayCode) {
        this.totalPayCode = totalPayCode == null ? null : totalPayCode.trim();
    }

    public Integer getIsTraceSource() {
        return isTraceSource;
    }

    public void setIsTraceSource(Integer isTraceSource) {
        this.isTraceSource = isTraceSource;
    }

    public String getIsTransfer() {
        return isTransfer;
    }

    public void setIsTransfer(String isTransfer) {
        this.isTransfer = isTransfer;
    }

    public String getIsTrans() {
        if (StringUtils.isNotEmpty(isTransfer)) {
            return "1".equals(isTransfer) ? "是" : "否";
        }
        return "";
    }

    public void setIsTrans(String isTrans) {
        this.isTrans = isTrans;
    }

    public String getIsTrace() {
        if (isTraceSource != null) {
            return isTraceSource == 1 ? "是" : "否";
        }
        return "";
    }

    public void setIsTrace(String isTrace) {
        this.isTrace = isTrace;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode == null ? null : destinationCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone == null ? null : senderPhone.trim();
    }

    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile == null ? null : senderMobile.trim();
    }

    public String getSenderCountry() {
        return senderCountry;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry == null ? null : senderCountry.trim();
    }

    public String getSenderProvince() {
        return senderProvince;
    }

    public void setSenderProvince(String senderProvince) {
        this.senderProvince = senderProvince == null ? null : senderProvince.trim();
    }

    public String getSenderCity() {
        return senderCity;
    }

    public void setSenderCity(String senderCity) {
        this.senderCity = senderCity == null ? null : senderCity.trim();
    }

    public String getSenderCounty() {
        return senderCounty;
    }

    public void setSenderCounty(String senderCounty) {
        this.senderCounty = senderCounty == null ? null : senderCounty.trim();
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress == null ? null : senderAddress.trim();
    }

    public String getSenderZipCode() {
        return senderZipCode;
    }

    public void setSenderZipCode(String senderZipCode) {
        this.senderZipCode = senderZipCode == null ? null : senderZipCode.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile == null ? null : receiverMobile.trim();
    }

    public String getReceiverIdentityNo() {
        return receiverIdentityNo;
    }

    public void setReceiverIdentityNo(String receiverIdentityNo) {
        this.receiverIdentityNo = receiverIdentityNo == null ? null : receiverIdentityNo.trim();
    }

    public String getReceiverIdentityNoFront() {
        return receiverIdentityNoFront;
    }

    public void setReceiverIdentityNoFront(String receiverIdentityNoFront) {
        this.receiverIdentityNoFront = receiverIdentityNoFront == null ? null : receiverIdentityNoFront.trim();
    }

    public String getReceiverIdentityNoBack() {
        return receiverIdentityNoBack;
    }

    public void setReceiverIdentityNoBack(String receiverIdentityNoBack) {
        this.receiverIdentityNoBack = receiverIdentityNoBack == null ? null : receiverIdentityNoBack.trim();
    }

    public String getReceiverCountry() {
        return receiverCountry;
    }

    public void setReceiverCountry(String receiverCountry) {
        this.receiverCountry = receiverCountry == null ? null : receiverCountry.trim();
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince == null ? null : receiverProvince.trim();
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity == null ? null : receiverCity.trim();
    }

    public String getReceiverCounty() {
        return receiverCounty;
    }

    public void setReceiverCounty(String receiverCounty) {
        this.receiverCounty = receiverCounty == null ? null : receiverCounty.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public String getReceiverZipCode() {
        return receiverZipCode;
    }

    public void setReceiverZipCode(String receiverZipCode) {
        this.receiverZipCode = receiverZipCode == null ? null : receiverZipCode.trim();
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone == null ? null : buyerPhone.trim();
    }

    public String getBuyerMobile() {
        return buyerMobile;
    }

    public void setBuyerMobile(String buyerMobile) {
        this.buyerMobile = buyerMobile == null ? null : buyerMobile.trim();
    }

    public String getBuyerIdType() {
        return buyerIdType;
    }

    public void setBuyerIdType(String buyerIdType) {
        this.buyerIdType = buyerIdType == null ? null : buyerIdType.trim();
    }

    public String getBuyerIdentityNo() {
        return buyerIdentityNo;
    }

    public void setBuyerIdentityNo(String buyerIdentityNo) {
        this.buyerIdentityNo = buyerIdentityNo == null ? null : buyerIdentityNo.trim();
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
        this.failReason = failReason == null ? null : failReason.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<OrderPubItem> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderPubItem> goodsList) {
        this.goodsList = goodsList;
    }

    public String getIeFlag() {
        return ieFlag;
    }

    public void setIeFlag(String ieFlag) {
        this.ieFlag = ieFlag;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public Integer getParcelCount() {
        return parcelCount;
    }

    public void setParcelCount(Integer parcelCount) {
        this.parcelCount = parcelCount;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getTotalPackageNo() {
        return totalPackageNo;
    }

    public void setTotalPackageNo(String totalPackageNo) {
        this.totalPackageNo = totalPackageNo;
    }

    public String getPackageKind() {
        return packageKind;
    }

    public void setPackageKind(String packageKind) {
        this.packageKind = packageKind;
    }


    public Date getDelayedTime() {
        return delayedTime;
    }

    public void setDelayedTime(Date delayedTime) {
        this.delayedTime = delayedTime;
    }

    public String getSenderStreet() {
        return senderStreet;
    }

    public void setSenderStreet(String senderStreet) {
        this.senderStreet = senderStreet;
    }

    public String getSendNum() {
        return sendNum;
    }

    public void setSendNum(String sendNum) {
        this.sendNum = sendNum;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }


    public String getReceiverStreet() {
        return receiverStreet;
    }

    public void setReceiverStreet(String receiverStreet) {
        this.receiverStreet = receiverStreet;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    public String getReceiverProvinceCode() {
        return receiverProvinceCode;
    }

    public void setReceiverProvinceCode(String receiverProvinceCode) {
        this.receiverProvinceCode = receiverProvinceCode;
    }

    public String getReceiverHouseNo() {
        return receiverHouseNo;
    }

    public void setReceiverHouseNo(String receiverHouseNo) {
        this.receiverHouseNo = receiverHouseNo;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public List<OrderPubBox> getBoxList() {
        return boxList;
    }

    public void setBoxList(List<OrderPubBox> boxList) {
        this.boxList = boxList;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}