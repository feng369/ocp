package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.SyncStatusEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 奇门订单
 * @author: syq
 * @create: 2019-12-11 15:45
 **/
public class OrderQm extends BaseEntity {

    private String code;//ocp系统内部单号

    private String orderCode;//出库单号

    private String preErpOrderCode;//原出库单号（erp分配）

    private String preWmsOrderCode;//原出库单号（wms分配）

    private String orderType;//出库单类型

    private String warehouseCode;//仓库编码

    private String orderFlag;//订单标记

    private String sourcePlatformCode;//订单来源平台编码

    private String sourcePlatformName;//订单来源平台名称

    private Date orderCreateTime;//出库单创建时间

    private Date placeOrderTime;//前台订单创建时间
    private String placeOrderBeginTime;
    private String placeOrderEndTime;

    private Date payTime;//订单支付时间

    private String payNo;//订单支付交易号

    private String serviceCode;//服务编码

    private String operatorCode;//操作员编码

    private String operatorName;//操作员名称

    private Date operateTime;//操作时间

    private String shopNick;//店铺名称

    private String sellerNick;//卖家名称

    private String buyerNick;//买家名称

    private BigDecimal totalAmount;//订单总金额（元）

    private BigDecimal itemAmount;//商品总金额（元）

    private BigDecimal discountAmount;//订单折扣金额（元）

    private BigDecimal freight;//快递费用（元）

    private BigDecimal arAmount;//应收金额（元）

    private BigDecimal gotAmount;//已收金额（元）

    private BigDecimal serviceFee;//服务费（元）

    private String logisticsCode;//物流公司编码

    private String logisticsName;//物流公司名称

    private String logisticsNo;//运单号

    private String logisticsAreaCode;//快递区域编码

    private Integer scheduleType;//投递时延要求

    private String scheduleDay;//要求送达日期YYYY-MM--DD

    private String scheduleStartTime;//投递时间范围（开始时间HH:mm:ss）

    private String scheduleEndTime;//投递时间范围（结束时间HH:mm:ss）

    private String deliveryType;//发货服务类型

    private Boolean isUrgency;//是否紧急

    private Boolean invoiceFlag;//是否需要发票

    private Boolean insuranceFlag;//是否需要保险

    private String buyerMessage;//买家留言

    private String sellerMessage;//卖家留言

    private String remark;//备注

    private String senderCompany;//发件人公司名称

    private String senderName;//发件人名称

    private String senderZip;//发件人邮编

    private String senderTel;//发件人固定电话

    private String senderMobile;//发件人移动电话

    private String senderEmail;//发件人电子邮箱

    private String senderCountryCode;//发件人国家二字码

    private String senderProvince;//发件人省

    private String senderCity;//发件人市

    private String senderArea;//发件人区域

    private String senderTown;//发件人村

    private String senderDetailAddress;//发件人详细地址

    private String receiverCompany;//收件人公司名称

    private String receiverName;//收件人名称

    private String receiverZip;//收件人邮编

    private String receiverTel;//收件人固定电话

    private String receiverMobile;//收件人移动电话

    private String receiverIdType;//收件人证件类型

    private String receiverIdNumber;//收件人证件号码

    private String receiverEmail;//收件人邮箱

    private String receiverCountryCode;//收件人国家二字码

    private String receiverProvince;//收件人省

    private String receiverCity;//收件人市

    private String receiverArea;//收件人区域

    private String receiverTown;//收件人村

    private String receiverDetailAddress;//收件人详细地址

    private SyncStatusEnum status;//状态

    private Date requestTimestamp;//请求时间

    private String requestCustomerId;//请求参数中的customerId

    private String requestAppKey;//请求参数中的app_key

    private String createBeginTime;
    private String createEndTime;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPreErpOrderCode() {
        return preErpOrderCode;
    }

    public void setPreErpOrderCode(String preErpOrderCode) {
        this.preErpOrderCode = preErpOrderCode;
    }

    public String getPreWmsOrderCode() {
        return preWmsOrderCode;
    }

    public void setPreWmsOrderCode(String preWmsOrderCode) {
        this.preWmsOrderCode = preWmsOrderCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag;
    }

    public String getSourcePlatformCode() {
        return sourcePlatformCode;
    }

    public void setSourcePlatformCode(String sourcePlatformCode) {
        this.sourcePlatformCode = sourcePlatformCode;
    }

    public String getSourcePlatformName() {
        return sourcePlatformName;
    }

    public void setSourcePlatformName(String sourcePlatformName) {
        this.sourcePlatformName = sourcePlatformName;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Date getPlaceOrderTime() {
        return placeOrderTime;
    }

    public void setPlaceOrderTime(Date placeOrderTime) {
        this.placeOrderTime = placeOrderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getShopNick() {
        return shopNick;
    }

    public void setShopNick(String shopNick) {
        this.shopNick = shopNick;
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(BigDecimal itemAmount) {
        this.itemAmount = itemAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getArAmount() {
        return arAmount;
    }

    public void setArAmount(BigDecimal arAmount) {
        this.arAmount = arAmount;
    }

    public BigDecimal getGotAmount() {
        return gotAmount;
    }

    public void setGotAmount(BigDecimal gotAmount) {
        this.gotAmount = gotAmount;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
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

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public String getLogisticsAreaCode() {
        return logisticsAreaCode;
    }

    public void setLogisticsAreaCode(String logisticsAreaCode) {
        this.logisticsAreaCode = logisticsAreaCode;
    }

    public Integer getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(Integer scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getScheduleDay() {
        return scheduleDay;
    }

    public void setScheduleDay(String scheduleDay) {
        this.scheduleDay = scheduleDay;
    }

    public String getScheduleStartTime() {
        return scheduleStartTime;
    }

    public void setScheduleStartTime(String scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public String getScheduleEndTime() {
        return scheduleEndTime;
    }

    public void setScheduleEndTime(String scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Boolean getUrgency() {
        return isUrgency;
    }

    public void setUrgency(Boolean urgency) {
        isUrgency = urgency;
    }

    public Boolean getInvoiceFlag() {
        return invoiceFlag;
    }

    public void setInvoiceFlag(Boolean invoiceFlag) {
        this.invoiceFlag = invoiceFlag;
    }

    public Boolean getInsuranceFlag() {
        return insuranceFlag;
    }

    public void setInsuranceFlag(Boolean insuranceFlag) {
        this.insuranceFlag = insuranceFlag;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public String getSellerMessage() {
        return sellerMessage;
    }

    public void setSellerMessage(String sellerMessage) {
        this.sellerMessage = sellerMessage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSenderCompany() {
        return senderCompany;
    }

    public void setSenderCompany(String senderCompany) {
        this.senderCompany = senderCompany;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderZip() {
        return senderZip;
    }

    public void setSenderZip(String senderZip) {
        this.senderZip = senderZip;
    }

    public String getSenderTel() {
        return senderTel;
    }

    public void setSenderTel(String senderTel) {
        this.senderTel = senderTel;
    }

    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderCountryCode() {
        return senderCountryCode;
    }

    public void setSenderCountryCode(String senderCountryCode) {
        this.senderCountryCode = senderCountryCode;
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

    public String getSenderArea() {
        return senderArea;
    }

    public void setSenderArea(String senderArea) {
        this.senderArea = senderArea;
    }

    public String getSenderTown() {
        return senderTown;
    }

    public void setSenderTown(String senderTown) {
        this.senderTown = senderTown;
    }

    public String getSenderDetailAddress() {
        return senderDetailAddress;
    }

    public void setSenderDetailAddress(String senderDetailAddress) {
        this.senderDetailAddress = senderDetailAddress;
    }

    public String getReceiverCompany() {
        return receiverCompany;
    }

    public void setReceiverCompany(String receiverCompany) {
        this.receiverCompany = receiverCompany;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverIdType() {
        return receiverIdType;
    }

    public void setReceiverIdType(String receiverIdType) {
        this.receiverIdType = receiverIdType;
    }

    public String getReceiverIdNumber() {
        return receiverIdNumber;
    }

    public void setReceiverIdNumber(String receiverIdNumber) {
        this.receiverIdNumber = receiverIdNumber;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getReceiverCountryCode() {
        return receiverCountryCode;
    }

    public void setReceiverCountryCode(String receiverCountryCode) {
        this.receiverCountryCode = receiverCountryCode;
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

    public String getReceiverArea() {
        return receiverArea;
    }

    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea;
    }

    public String getReceiverTown() {
        return receiverTown;
    }

    public void setReceiverTown(String receiverTown) {
        this.receiverTown = receiverTown;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }

    public SyncStatusEnum getStatus() {
        return status;
    }

    public void setStatus(SyncStatusEnum status) {
        this.status = status;
    }

    public Date getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(Date requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public String getRequestCustomerId() {
        return requestCustomerId;
    }

    public void setRequestCustomerId(String requestCustomerId) {
        this.requestCustomerId = requestCustomerId;
    }

    public String getRequestAppKey() {
        return requestAppKey;
    }

    public void setRequestAppKey(String requestAppKey) {
        this.requestAppKey = requestAppKey;
    }

    public String getPlaceOrderBeginTime() {
        return placeOrderBeginTime;
    }

    public void setPlaceOrderBeginTime(String placeOrderBeginTime) {
        this.placeOrderBeginTime = placeOrderBeginTime;
    }

    public String getPlaceOrderEndTime() {
        return placeOrderEndTime;
    }

    public void setPlaceOrderEndTime(String placeOrderEndTime) {
        this.placeOrderEndTime = placeOrderEndTime;
    }
}
