package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.enumeration.YesOrNoEnum;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import java.math.BigDecimal;
import java.util.Date;


public class OrderYouzan extends BaseEntity {

    private String code;

    private String token;

    private String status;

    private Long type;

    private String tid;

    private String statusStr;

    private Long payType;

    private Long teamType;

    private Long closeType;

    private Long expressType;

    private Date created;

    private Date updated;

    private Date expiredTime;

    private Date payTime;

    private Date consignTime;

    private Date confirmTime;

    private Long refundState;

    private Boolean isRetailOrder;

    private Date successTime;

    private Long offlineId;

    private String payTypeStr;

    private Integer activityType;

    private Boolean isOfflineOrder;

    private String orderMark;

    private String bookKey;

    private String bizSource;

    private String platform;

    private String wxEntrance;

    private Long buyerId;

    private String buyerPhone;

    private Long fansType;

    private Long fansId;

    private String fansNickname;

    private String outerUserId;

    private BigDecimal totalFee;

    private BigDecimal postFee;

    private BigDecimal payment;

    private String transaction;

    private String outerTransactions;

    private String buyerMessage;

    private Integer star;

    private String tradeMemo;

    private String receiverName;

    private String receiverTel;

    private String deliveryProvince;

    private String deliveryCity;

    private String deliveryDistrict;

    private String deliveryAddress;

    private String addressExtra;

    private String deliveryPostalCode;

    private String selfFetchInfo;

    private Date deliveryStartTime;

    private Date deliveryEndTime;

    private String giftNo;

    private String giftSign;

    private OrderStatusEnum toStatus;

    private String failReason;

    private String electricCode;

    private String appId;

    private String appName;

    private String subOrderNo;

    private String customsCode;

    private String ciqCode;

    private BusiModeEnum orderTradeMode;

    private ForwardSystemEnum toSystem;

    private String ebpCode;

    private BigDecimal goodsValuePrice;

    private BigDecimal taxTotalPrice;

    private String dno;

    private String storehouseId;

    private String appKey;

    private String sendKjbStatus;

    private String oldTid;

    private String storeKey;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public Long getPayType() {
        return payType;
    }

    public void setPayType(Long payType) {
        this.payType = payType;
    }

    public Long getTeamType() {
        return teamType;
    }

    public void setTeamType(Long teamType) {
        this.teamType = teamType;
    }

    public Long getCloseType() {
        return closeType;
    }

    public void setCloseType(Long closeType) {
        this.closeType = closeType;
    }

    public Long getExpressType() {
        return expressType;
    }

    public void setExpressType(Long expressType) {
        this.expressType = expressType;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(Date consignTime) {
        this.consignTime = consignTime;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Long getRefundState() {
        return refundState;
    }

    public void setRefundState(Long refundState) {
        this.refundState = refundState;
    }

    public Boolean getIsRetailOrder() {
        return isRetailOrder;
    }

    public void setIsRetailOrder(Boolean isRetailOrder) {
        this.isRetailOrder = isRetailOrder;
    }

    public Date getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    public Long getOfflineId() {
        return offlineId;
    }

    public void setOfflineId(Long offlineId) {
        this.offlineId = offlineId;
    }

    public String getPayTypeStr() {
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Boolean getIsOfflineOrder() {
        return isOfflineOrder;
    }

    public void setIsOfflineOrder(Boolean isOfflineOrder) {
        this.isOfflineOrder = isOfflineOrder;
    }

    public String getOrderMark() {
        return orderMark;
    }

    public void setOrderMark(String orderMark) {
        this.orderMark = orderMark;
    }

    public String getBookKey() {
        return bookKey;
    }

    public void setBookKey(String bookKey) {
        this.bookKey = bookKey;
    }

    public String getBizSource() {
        return bizSource;
    }

    public void setBizSource(String bizSource) {
        this.bizSource = bizSource;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getWxEntrance() {
        return wxEntrance;
    }

    public void setWxEntrance(String wxEntrance) {
        this.wxEntrance = wxEntrance;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public Long getFansType() {
        return fansType;
    }

    public void setFansType(Long fansType) {
        this.fansType = fansType;
    }

    public Long getFansId() {
        return fansId;
    }

    public void setFansId(Long fansId) {
        this.fansId = fansId;
    }

    public String getFansNickname() {
        return fansNickname;
    }

    public void setFansNickname(String fansNickname) {
        this.fansNickname = fansNickname;
    }

    public String getOuterUserId() {
        return outerUserId;
    }

    public void setOuterUserId(String outerUserId) {
        this.outerUserId = outerUserId;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getPostFee() {
        return postFee;
    }

    public void setPostFee(BigDecimal postFee) {
        this.postFee = postFee;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getOuterTransactions() {
        return outerTransactions;
    }

    public void setOuterTransactions(String outerTransactions) {
        this.outerTransactions = outerTransactions;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getTradeMemo() {
        return tradeMemo;
    }

    public void setTradeMemo(String tradeMemo) {
        this.tradeMemo = tradeMemo;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getDeliveryProvince() {
        return deliveryProvince;
    }

    public void setDeliveryProvince(String deliveryProvince) {
        this.deliveryProvince = deliveryProvince;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryDistrict() {
        return deliveryDistrict;
    }

    public void setDeliveryDistrict(String deliveryDistrict) {
        this.deliveryDistrict = deliveryDistrict;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getAddressExtra() {
        return addressExtra;
    }

    public void setAddressExtra(String addressExtra) {
        this.addressExtra = addressExtra;
    }

    public String getDeliveryPostalCode() {
        return deliveryPostalCode;
    }

    public void setDeliveryPostalCode(String deliveryPostalCode) {
        this.deliveryPostalCode = deliveryPostalCode;
    }

    public String getSelfFetchInfo() {
        return selfFetchInfo;
    }

    public void setSelfFetchInfo(String selfFetchInfo) {
        this.selfFetchInfo = selfFetchInfo;
    }

    public Date getDeliveryStartTime() {
        return deliveryStartTime;
    }

    public void setDeliveryStartTime(Date deliveryStartTime) {
        this.deliveryStartTime = deliveryStartTime;
    }

    public Date getDeliveryEndTime() {
        return deliveryEndTime;
    }

    public void setDeliveryEndTime(Date deliveryEndTime) {
        this.deliveryEndTime = deliveryEndTime;
    }

    public String getGiftNo() {
        return giftNo;
    }

    public void setGiftNo(String giftNo) {
        this.giftNo = giftNo;
    }

    public String getGiftSign() {
        return giftSign;
    }

    public void setGiftSign(String giftSign) {
        this.giftSign = giftSign;
    }

    public OrderStatusEnum getToStatus() {
        return toStatus;
    }

    public void setToStatus(OrderStatusEnum toStatus) {
        this.toStatus = toStatus;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getElectricCode() {
        return electricCode;
    }

    public void setElectricCode(String electricCode) {
        this.electricCode = electricCode;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode;
    }

    public String getCiqCode() {
        return ciqCode;
    }

    public void setCiqCode(String ciqCode) {
        this.ciqCode = ciqCode;
    }

    public BusiModeEnum getOrderTradeMode() {
        return orderTradeMode;
    }

    public void setOrderTradeMode(BusiModeEnum orderTradeMode) {
        this.orderTradeMode = orderTradeMode;
    }

    public ForwardSystemEnum getToSystem() {
        return toSystem;
    }

    public void setToSystem(ForwardSystemEnum toSystem) {
        this.toSystem = toSystem;
    }

    public String getEbpCode() {
        return ebpCode;
    }

    public void setEbpCode(String ebpCode) {
        this.ebpCode = ebpCode;
    }

    public BigDecimal getGoodsValuePrice() {
        return goodsValuePrice;
    }

    public void setGoodsValuePrice(BigDecimal goodsValuePrice) {
        this.goodsValuePrice = goodsValuePrice;
    }

    public BigDecimal getTaxTotalPrice() {
        return taxTotalPrice;
    }

    public void setTaxTotalPrice(BigDecimal taxTotalPrice) {
        this.taxTotalPrice = taxTotalPrice;
    }

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }

    public String getStorehouseId() {
        return storehouseId;
    }

    public void setStorehouseId(String storehouseId) {
        this.storehouseId = storehouseId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSendKjbStatus() {
        return sendKjbStatus;
    }

    public void setSendKjbStatus(String sendKjbStatus) {
        this.sendKjbStatus = sendKjbStatus;
    }

    public String getOldTid() {
        return oldTid;
    }

    public void setOldTid(String oldTid) {
        this.oldTid = oldTid;
    }

    public String getStoreKey() {
        return storeKey;
    }

    public void setStoreKey(String storeKey) {
        this.storeKey = storeKey;
    }
}