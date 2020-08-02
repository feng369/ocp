package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import java.math.BigDecimal;
import java.util.Date;

public class OrderAmway extends BaseEntity {

    /**
     * 订单状态，10、20、60、70对应制单、已抓取详细、下发失败、下发成功
     */
    private OrderStatusEnum status;

    /**
     * 发送系统
     */
    private ForwardSystemEnum sendSystem;

    /**
     * 订单内码
     */
    private String orderCode;

    /**
     * 报文ID
     */
    private String messageId;

    /**
     * 报文签名
     */
    private String sign;

    /**
     * 报文类型
     */
    private String messageType;

    /**
     * 发送人
     */
    private String senderId;

    /**
     * 接收人
     */
    private String receiverId;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 出货仓库代码
     */
    private String warehouseId;

    /**
     * 推送代码
     */
    private String businessType;

    /**
     * 接口版本号
     */
    private String versionId;

    /**
     * 团单编号
     */
    private String groupOrderNumber;

    private String isGroupOrderFlag;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 订单类型
     */
    private String orderType;

    /**
     * 办理人编号
     */
    private String distributorNumber;

    /**
     * 办理人名称
     */
    private String distributorName;

    /**
     * 配偶名称
     */
    private String distributorSpouseName;

    /**
     * 服务场所名称
     */
    private String serviceName;

    /**
     * 办理人手机号
     */
    private String orderDstMobile;

    /**
     * 办理人固定电话
     */
    private String orderDstLandNo;

    /**
     * 收货人编号
     */
    private String shipToDst;

    /**
     * 收货人姓名
     */
    private String consigneeName;

    /**
     * 收货地址省份
     */
    private String shipToAddress1;

    /**
     * 收货地址城市
     */
    private String shipToAddress2;

    /**
     * 收货地址区域
     */
    private String shipToAddress3;

    /**
     * 收货地址街道
     */
    private String shipToAddress4;

    /**
     * 地址备用字段
     */
    private String shipToAddress5;

    /**
     * 收货地址邮编
     */
    private String postCode;

    /**
     * 收货人手机号码
     */
    private String consigneeMobile;

    /**
     * 收货人座机号码
     */
    private String consigneeLandNo;

    /**
     * 下单时间
     */
    private Date saleDate;

    /**
     * 期望送货时间
     */
    private String expectedDeliveryPeriod;

    /**
     * 运费
     */
    private BigDecimal deliveryFee;

    /**
     * 保价费
     */
    private BigDecimal deliveryInsuranceFee;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 订单BV
     */
    private String orderBv;

    /**
     * 订单PV
     */
    private String orderPv;

    /**
     * 订单折扣金额
     */
    private BigDecimal orderDiscount;


    private String couponBv;

    private String voucherBv;

    private String promotionBv;

    private String couponPv;

    private String voucherPv;

    private String promotionPv;
    /**
     * 获得积分
     */
    private String giftPointGenerated;

    /**
     * 使用积分
     */
    private String giftPointUsed;

    /**
     * 累计积分
     */
    private String giftPointLeft;

    /**
     * pos编号
     */
    private String posCode;

    /**
     * 人员编号
     */
    private String staffNumber;

    /**
     * 订单金额
     */
    private BigDecimal orderOriginalPrice;

    /**
     * 备用字段
     */
    private String rsvst1;

    private String rsvst2;

    private String rsvst3;

    private String rsvst4;

    private String rsvst5;

    private String rsvdc1;

    private String rsvdc2;

    private String rsvdc3;

    private String rsvdc4;

    private String rsvdc5;

    private String systemFlag;

    /**
     * 收货人地址乡镇
     */
    private String shipToAddress31;

    private String shipToAddressType;

    private String shipToCombinedId;

    /**
     * ocp内部订单号
     */
    private String code;


    /**
     * 进口模式
     */
    private String importMode;

    /**
     * 关区
     */
    private String customs;

    /**
     * 税费
     */
    private BigDecimal taxFee;

    /**
     * 订购人身份证号
     */
    private String customerId;

    /**
     * 起运国
     */
    private String country;

    /**
     * 运输方式
     */
    private String transportMethod;

    /**
     * 发货人姓名
     */
    private String shipName;

    /**
     * 发货人地址
     */
    private String shipAddress;

    /**
     * 发货人电话
     */
    private String shipTel;

    /**
     * 电商企业
     */
    private String ebcCode;

    /**
     * 电商平台
     */
    private String ebpCode;

    /**
     * 配送商编码
     */
    private String deliveryCode;

    /**
     * 配送商编码
     */
    private BigDecimal paymentHeaderAmount;

    /**
     * 付款时间
     */
    private Date paymentDateTime;


    public BigDecimal getPaymentHeaderAmount() {
        return paymentHeaderAmount;
    }

    public void setPaymentHeaderAmount(BigDecimal paymentHeaderAmount) {
        this.paymentHeaderAmount = paymentHeaderAmount;
    }

    public Date getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(Date paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getEbcCode() {
        return ebcCode;
    }

    public void setEbcCode(String ebcCode) {
        this.ebcCode = ebcCode;
    }

    public String getEbpCode() {
        return ebpCode;
    }

    public void setEbpCode(String ebpCode) {
        this.ebpCode = ebpCode;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId == null ? null : senderId.trim();
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId == null ? null : receiverId.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId == null ? null : warehouseId.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId == null ? null : versionId.trim();
    }

    public String getGroupOrderNumber() {
        return groupOrderNumber;
    }

    public void setGroupOrderNumber(String groupOrderNumber) {
        this.groupOrderNumber = groupOrderNumber == null ? null : groupOrderNumber.trim();
    }

    public String getIsGroupOrderFlag() {
        return isGroupOrderFlag;
    }

    public void setIsGroupOrderFlag(String isGroupOrderFlag) {
        this.isGroupOrderFlag = isGroupOrderFlag == null ? null : isGroupOrderFlag.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getDistributorNumber() {
        return distributorNumber;
    }

    public void setDistributorNumber(String distributorNumber) {
        this.distributorNumber = distributorNumber == null ? null : distributorNumber.trim();
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName == null ? null : distributorName.trim();
    }

    public String getDistributorSpouseName() {
        return distributorSpouseName;
    }

    public void setDistributorSpouseName(String distributorSpouseName) {
        this.distributorSpouseName = distributorSpouseName == null ? null : distributorSpouseName.trim();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getOrderDstMobile() {
        return orderDstMobile;
    }

    public void setOrderDstMobile(String orderDstMobile) {
        this.orderDstMobile = orderDstMobile == null ? null : orderDstMobile.trim();
    }

    public String getOrderDstLandNo() {
        return orderDstLandNo;
    }

    public void setOrderDstLandNo(String orderDstLandNo) {
        this.orderDstLandNo = orderDstLandNo == null ? null : orderDstLandNo.trim();
    }

    public String getShipToDst() {
        return shipToDst;
    }

    public void setShipToDst(String shipToDst) {
        this.shipToDst = shipToDst == null ? null : shipToDst.trim();
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName == null ? null : consigneeName.trim();
    }

    public String getShipToAddress1() {
        return shipToAddress1;
    }

    public void setShipToAddress1(String shipToAddress1) {
        this.shipToAddress1 = shipToAddress1 == null ? null : shipToAddress1.trim();
    }

    public String getShipToAddress2() {
        return shipToAddress2;
    }

    public void setShipToAddress2(String shipToAddress2) {
        this.shipToAddress2 = shipToAddress2 == null ? null : shipToAddress2.trim();
    }

    public String getShipToAddress3() {
        return shipToAddress3;
    }

    public void setShipToAddress3(String shipToAddress3) {
        this.shipToAddress3 = shipToAddress3 == null ? null : shipToAddress3.trim();
    }

    public String getShipToAddress4() {
        return shipToAddress4;
    }

    public void setShipToAddress4(String shipToAddress4) {
        this.shipToAddress4 = shipToAddress4 == null ? null : shipToAddress4.trim();
    }

    public String getShipToAddress5() {
        return shipToAddress5;
    }

    public void setShipToAddress5(String shipToAddress5) {
        this.shipToAddress5 = shipToAddress5 == null ? null : shipToAddress5.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile == null ? null : consigneeMobile.trim();
    }

    public String getConsigneeLandNo() {
        return consigneeLandNo;
    }

    public void setConsigneeLandNo(String consigneeLandNo) {
        this.consigneeLandNo = consigneeLandNo == null ? null : consigneeLandNo.trim();
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public String getExpectedDeliveryPeriod() {
        return expectedDeliveryPeriod;
    }

    public void setExpectedDeliveryPeriod(String expectedDeliveryPeriod) {
        this.expectedDeliveryPeriod = expectedDeliveryPeriod;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public BigDecimal getDeliveryInsuranceFee() {
        return deliveryInsuranceFee;
    }

    public void setDeliveryInsuranceFee(BigDecimal deliveryInsuranceFee) {
        this.deliveryInsuranceFee = deliveryInsuranceFee;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getOrderBv() {
        return orderBv;
    }

    public void setOrderBv(String orderBv) {
        this.orderBv = orderBv == null ? null : orderBv.trim();
    }

    public String getOrderPv() {
        return orderPv;
    }

    public void setOrderPv(String orderPv) {
        this.orderPv = orderPv == null ? null : orderPv.trim();
    }

    public BigDecimal getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(BigDecimal orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    public String getCouponBv() {
        return couponBv;
    }

    public void setCouponBv(String couponBv) {
        this.couponBv = couponBv == null ? null : couponBv.trim();
    }

    public String getVoucherBv() {
        return voucherBv;
    }

    public void setVoucherBv(String voucherBv) {
        this.voucherBv = voucherBv == null ? null : voucherBv.trim();
    }

    public String getPromotionBv() {
        return promotionBv;
    }

    public void setPromotionBv(String promotionBv) {
        this.promotionBv = promotionBv == null ? null : promotionBv.trim();
    }

    public String getCouponPv() {
        return couponPv;
    }

    public void setCouponPv(String couponPv) {
        this.couponPv = couponPv == null ? null : couponPv.trim();
    }

    public String getVoucherPv() {
        return voucherPv;
    }

    public void setVoucherPv(String voucherPv) {
        this.voucherPv = voucherPv == null ? null : voucherPv.trim();
    }

    public String getPromotionPv() {
        return promotionPv;
    }

    public void setPromotionPv(String promotionPv) {
        this.promotionPv = promotionPv == null ? null : promotionPv.trim();
    }

    public String getGiftPointGenerated() {
        return giftPointGenerated;
    }

    public void setGiftPointGenerated(String giftPointGenerated) {
        this.giftPointGenerated = giftPointGenerated == null ? null : giftPointGenerated.trim();
    }

    public String getGiftPointUsed() {
        return giftPointUsed;
    }

    public void setGiftPointUsed(String giftPointUsed) {
        this.giftPointUsed = giftPointUsed == null ? null : giftPointUsed.trim();
    }

    public String getGiftPointLeft() {
        return giftPointLeft;
    }

    public void setGiftPointLeft(String giftPointLeft) {
        this.giftPointLeft = giftPointLeft == null ? null : giftPointLeft.trim();
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode == null ? null : posCode.trim();
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber == null ? null : staffNumber.trim();
    }

    public BigDecimal getOrderOriginalPrice() {
        return orderOriginalPrice;
    }

    public void setOrderOriginalPrice(BigDecimal orderOriginalPrice) {
        this.orderOriginalPrice = orderOriginalPrice;
    }

    public String getRsvst1() {
        return rsvst1;
    }

    public void setRsvst1(String rsvst1) {
        this.rsvst1 = rsvst1 == null ? null : rsvst1.trim();
    }

    public String getRsvst2() {
        return rsvst2;
    }

    public void setRsvst2(String rsvst2) {
        this.rsvst2 = rsvst2 == null ? null : rsvst2.trim();
    }

    public String getRsvst3() {
        return rsvst3;
    }

    public void setRsvst3(String rsvst3) {
        this.rsvst3 = rsvst3 == null ? null : rsvst3.trim();
    }

    public String getRsvst4() {
        return rsvst4;
    }

    public void setRsvst4(String rsvst4) {
        this.rsvst4 = rsvst4 == null ? null : rsvst4.trim();
    }

    public String getRsvst5() {
        return rsvst5;
    }

    public void setRsvst5(String rsvst5) {
        this.rsvst5 = rsvst5 == null ? null : rsvst5.trim();
    }

    public String getRsvdc1() {
        return rsvdc1;
    }

    public void setRsvdc1(String rsvdc1) {
        this.rsvdc1 = rsvdc1 == null ? null : rsvdc1.trim();
    }

    public String getRsvdc2() {
        return rsvdc2;
    }

    public void setRsvdc2(String rsvdc2) {
        this.rsvdc2 = rsvdc2 == null ? null : rsvdc2.trim();
    }

    public String getRsvdc3() {
        return rsvdc3;
    }

    public void setRsvdc3(String rsvdc3) {
        this.rsvdc3 = rsvdc3 == null ? null : rsvdc3.trim();
    }

    public String getRsvdc4() {
        return rsvdc4;
    }

    public void setRsvdc4(String rsvdc4) {
        this.rsvdc4 = rsvdc4 == null ? null : rsvdc4.trim();
    }

    public String getRsvdc5() {
        return rsvdc5;
    }

    public void setRsvdc5(String rsvdc5) {
        this.rsvdc5 = rsvdc5 == null ? null : rsvdc5.trim();
    }

    public String getSystemFlag() {
        return systemFlag;
    }

    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag == null ? null : systemFlag.trim();
    }

    public String getShipToAddress31() {
        return shipToAddress31;
    }

    public void setShipToAddress31(String shipToAddress31) {
        this.shipToAddress31 = shipToAddress31 == null ? null : shipToAddress31.trim();
    }

    public String getShipToAddressType() {
        return shipToAddressType;
    }

    public void setShipToAddressType(String shipToAddressType) {
        this.shipToAddressType = shipToAddressType == null ? null : shipToAddressType.trim();
    }

    public String getShipToCombinedId() {
        return shipToCombinedId;
    }

    public void setShipToCombinedId(String shipToCombinedId) {
        this.shipToCombinedId = shipToCombinedId == null ? null : shipToCombinedId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getImportMode() {
        return importMode;
    }

    public void setImportMode(String importMode) {
        this.importMode = importMode == null ? null : importMode.trim();
    }

    public String getCustoms() {
        return customs;
    }

    public void setCustoms(String customs) {
        this.customs = customs == null ? null : customs.trim();
    }

    public BigDecimal getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(BigDecimal taxFee) {
        this.taxFee = taxFee;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getTransportMethod() {
        return transportMethod;
    }

    public void setTransportMethod(String transportMethod) {
        this.transportMethod = transportMethod == null ? null : transportMethod.trim();
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName == null ? null : shipName.trim();
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress == null ? null : shipAddress.trim();
    }

    public String getShipTel() {
        return shipTel;
    }

    public void setShipTel(String shipTel) {
        this.shipTel = shipTel == null ? null : shipTel.trim();
    }

    public ForwardSystemEnum getSendSystem() {
        return sendSystem;
    }

    public void setSendSystem(ForwardSystemEnum sendSystem) {
        this.sendSystem = sendSystem;
    }
}