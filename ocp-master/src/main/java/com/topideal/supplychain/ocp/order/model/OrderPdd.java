package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.enums.OrderTypeEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author klw
 * @date 2019/12/11 16:01
 * @description: 拼多多订单实体类
 */
public class OrderPdd extends BaseEntity {

    private String code;

    private OrderStatusEnum status;

    private OrderTypeEnum type;

    private String orderId;

    private String orderSn;

    private String cbepcomCode;

    private String electricCode;

    private String sid;

    private String logisticsNo;

    private String tpl;

    private String orderBatchNo;

    private String pod;

    private String payNo;

    private String deliveryCode;

    private String trackingNumber;

    private String activePayComp;

    private String payCopNo;

    private String payPcomName;

    private String agentCode;

    private String agentName;

    private String stationbCode;

    private String warehouseId;

    private BigDecimal acturalPaid;

    private BigDecimal discount;

    private BigDecimal postage;

    private BigDecimal freightFcy;

    private String freightFcode;

    private BigDecimal geminiTotalPrice;

    private BigDecimal taxFcy;

    private String taxFcode;

    private BigDecimal insuredFee;

    private String insurCurr;

    private String insurMark;

    private BigDecimal otherRate;

    private String otherCurr;

    private String otherMark;

    private BigDecimal otherPayment;

    private String otherPaymentCurr;

    private BigDecimal goodsAmount;

    private BigDecimal totalValue;

    private String fCode;

    private String payCurr;

    private BigDecimal platformDiscount;

    private BigDecimal sellerDiscount;

    private BigDecimal totalFavourable;

    private Integer packNo;

    private Date orderDate;

    private Date inputDate;

    private Date shipDate;

    private String shippingTime;

    private String confirmTime;

    private String receiveTime;

    private Date payDate;

    private BigDecimal grossWeight;

    private BigDecimal netWeight;

    private String busiMode;

    private String buyerIdNumber;

    private String buyerIdType;

    private String buyerName;

    private String buyerRegNo;

    private String buyerTelephone;

    private String buyerMemo;

    private String payorName;

    private String receiveType;

    private Long catId1;

    private Long catId2;

    private Long catId3;

    private Long catId4;

    private String customsCode;

    private String ciqbCode;

    private String bakbCode;

    private Integer customsType;
    //是否自运营订单
    private String orderType;

    private String name;

    private String sender;

    private String receiver;

    private String receiveNo;

    private String mobilePhone;

    private String phone;

    private String address;

    private String receiverAddress;

    private String printHeader;

    private String postCode;

    private String recipientProvincesCode;

    private String country;

    private String province;

    private String city;

    private String district;

    private Integer countryId;

    private Integer provinceId;

    private Integer cityId;

    private Integer townId;

    private String shipperName;

    private String shipperPhone;

    private String shippernCode;

    private String shipperAddress;

    private String sendCity;

    private String orderStatus;

    private Integer afterSalesStatus;

    private Integer confirmStatus;

    private String tradeMode;

    private Integer isStoreStrategy;

    private Integer ownerFlag;

    private Integer fromEplat;

    private String reDeclare;

    private String statisticsFlag;

    private Integer vmiFlag;

    private String wrapType;

    private String trans;

    private String transMode;

    private String transNo;

    private Date transportDay;

    private BigDecimal deliveryInstallValue;

    private BigDecimal deliveryHomeValue;

    private BigDecimal homeInstallValue;

    private Integer homeDeliveryType;

    private BigDecimal capitalFreeDiscount;

    private Integer returnFreightPayer;

    private Integer supportNationwideWarranty;

    private Integer freeSf;

    private Integer invoiceStatus;

    private Integer isLuckyFlag;

    private Integer tradeType;

    private Integer isPreSale;

    private Integer isStockOut;

    private Integer groupStatus;

    private Integer refundStatus;

    private Integer stockOutHandleStatus;

    private Integer deliveryStatus;

    private Integer onlySupportReplace;

    private String lastShipTime;

    private String preSaleTime;

    private String logistCode;

    private Long logisticsId;

    private BigDecimal advancedPaidFee;

    private BigDecimal stepPaidFee;

    private BigDecimal stepDiscountAmount;

    private Integer stepTradeStatus;

    private String wareSn;

    private Integer wareType;

    private String wareName;

    private String wareId;

    private String depotCode;

    private String depotName;

    private String depotId;

    private Integer depotType;

    private String blno;

    private String changeFlag;

    private String congratulations;

    private String cutMode;

    private String failReason;

    private String forSellComp;

    private String forSellCompName;

    private String goodsInfo;

    private String packingMaterial;

    private String opType;

    private String ordExcStatus;

    private String sendState;

    private String payStatus;

    private String payType;

    private String tradeUnitCode;

    private String tradeUnitName;

    private String remark;

    private String notes;

    private String payNots;

    private String logNots;

    private String sendSystem;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public OrderTypeEnum getType() {
        return type;
    }

    public void setType(OrderTypeEnum type) {
        this.type = type;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public String getCbepcomCode() {
        return cbepcomCode;
    }

    public void setCbepcomCode(String cbepcomCode) {
        this.cbepcomCode = cbepcomCode == null ? null : cbepcomCode.trim();
    }

    public String getElectricCode() {
        return electricCode;
    }

    public void setElectricCode(String electricCode) {
        this.electricCode = electricCode == null ? null : electricCode.trim();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo == null ? null : logisticsNo.trim();
    }

    public String getTpl() {
        return tpl;
    }

    public void setTpl(String tpl) {
        this.tpl = tpl == null ? null : tpl.trim();
    }

    public String getOrderBatchNo() {
        return orderBatchNo;
    }

    public void setOrderBatchNo(String orderBatchNo) {
        this.orderBatchNo = orderBatchNo == null ? null : orderBatchNo.trim();
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod == null ? null : pod.trim();
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo == null ? null : payNo.trim();
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode == null ? null : deliveryCode.trim();
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber == null ? null : trackingNumber.trim();
    }

    public String getActivePayComp() {
        return activePayComp;
    }

    public void setActivePayComp(String activePayComp) {
        this.activePayComp = activePayComp == null ? null : activePayComp.trim();
    }

    public String getPayCopNo() {
        return payCopNo;
    }

    public void setPayCopNo(String payCopNo) {
        this.payCopNo = payCopNo == null ? null : payCopNo.trim();
    }

    public String getPayPcomName() {
        return payPcomName;
    }

    public void setPayPcomName(String payPcomName) {
        this.payPcomName = payPcomName == null ? null : payPcomName.trim();
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode == null ? null : agentCode.trim();
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public String getStationbCode() {
        return stationbCode;
    }

    public void setStationbCode(String stationbCode) {
        this.stationbCode = stationbCode == null ? null : stationbCode.trim();
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId == null ? null : warehouseId.trim();
    }

    public BigDecimal getActuralPaid() {
        return acturalPaid;
    }

    public void setActuralPaid(BigDecimal acturalPaid) {
        this.acturalPaid = acturalPaid;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public BigDecimal getFreightFcy() {
        return freightFcy;
    }

    public void setFreightFcy(BigDecimal freightFcy) {
        this.freightFcy = freightFcy;
    }

    public String getFreightFcode() {
        return freightFcode;
    }

    public void setFreightFcode(String freightFcode) {
        this.freightFcode = freightFcode == null ? null : freightFcode.trim();
    }

    public BigDecimal getGeminiTotalPrice() {
        return geminiTotalPrice;
    }

    public void setGeminiTotalPrice(BigDecimal geminiTotalPrice) {
        this.geminiTotalPrice = geminiTotalPrice;
    }

    public BigDecimal getTaxFcy() {
        return taxFcy;
    }

    public void setTaxFcy(BigDecimal taxFcy) {
        this.taxFcy = taxFcy;
    }

    public String getTaxFcode() {
        return taxFcode;
    }

    public void setTaxFcode(String taxFcode) {
        this.taxFcode = taxFcode == null ? null : taxFcode.trim();
    }

    public BigDecimal getInsuredFee() {
        return insuredFee;
    }

    public void setInsuredFee(BigDecimal insuredFee) {
        this.insuredFee = insuredFee;
    }

    public String getInsurCurr() {
        return insurCurr;
    }

    public void setInsurCurr(String insurCurr) {
        this.insurCurr = insurCurr == null ? null : insurCurr.trim();
    }

    public String getInsurMark() {
        return insurMark;
    }

    public void setInsurMark(String insurMark) {
        this.insurMark = insurMark == null ? null : insurMark.trim();
    }

    public BigDecimal getOtherRate() {
        return otherRate;
    }

    public void setOtherRate(BigDecimal otherRate) {
        this.otherRate = otherRate;
    }

    public String getOtherCurr() {
        return otherCurr;
    }

    public void setOtherCurr(String otherCurr) {
        this.otherCurr = otherCurr == null ? null : otherCurr.trim();
    }

    public String getOtherMark() {
        return otherMark;
    }

    public void setOtherMark(String otherMark) {
        this.otherMark = otherMark == null ? null : otherMark.trim();
    }

    public BigDecimal getOtherPayment() {
        return otherPayment;
    }

    public void setOtherPayment(BigDecimal otherPayment) {
        this.otherPayment = otherPayment;
    }

    public String getOtherPaymentCurr() {
        return otherPaymentCurr;
    }

    public void setOtherPaymentCurr(String otherPaymentCurr) {
        this.otherPaymentCurr = otherPaymentCurr == null ? null : otherPaymentCurr.trim();
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode == null ? null : fCode.trim();
    }

    public String getPayCurr() {
        return payCurr;
    }

    public void setPayCurr(String payCurr) {
        this.payCurr = payCurr == null ? null : payCurr.trim();
    }

    public BigDecimal getPlatformDiscount() {
        return platformDiscount;
    }

    public void setPlatformDiscount(BigDecimal platformDiscount) {
        this.platformDiscount = platformDiscount;
    }

    public BigDecimal getSellerDiscount() {
        return sellerDiscount;
    }

    public void setSellerDiscount(BigDecimal sellerDiscount) {
        this.sellerDiscount = sellerDiscount;
    }

    public BigDecimal getTotalFavourable() {
        return totalFavourable;
    }

    public void setTotalFavourable(BigDecimal totalFavourable) {
        this.totalFavourable = totalFavourable;
    }

    public Integer getPackNo() {
        return packNo;
    }

    public void setPackNo(Integer packNo) {
        this.packNo = packNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public String getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(String shippingTime) {
        this.shippingTime = shippingTime == null ? null : shippingTime.trim();
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime == null ? null : confirmTime.trim();
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime == null ? null : receiveTime.trim();
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    public String getBusiMode() {
        return busiMode;
    }

    public void setBusiMode(String busiMode) {
        this.busiMode = busiMode == null ? null : busiMode.trim();
    }

    public String getBuyerIdNumber() {
        return buyerIdNumber;
    }

    public void setBuyerIdNumber(String buyerIdNumber) {
        this.buyerIdNumber = buyerIdNumber == null ? null : buyerIdNumber.trim();
    }

    public String getBuyerIdType() {
        return buyerIdType;
    }

    public void setBuyerIdType(String buyerIdType) {
        this.buyerIdType = buyerIdType == null ? null : buyerIdType.trim();
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName == null ? null : buyerName.trim();
    }

    public String getBuyerRegNo() {
        return buyerRegNo;
    }

    public void setBuyerRegNo(String buyerRegNo) {
        this.buyerRegNo = buyerRegNo == null ? null : buyerRegNo.trim();
    }

    public String getBuyerTelephone() {
        return buyerTelephone;
    }

    public void setBuyerTelephone(String buyerTelephone) {
        this.buyerTelephone = buyerTelephone == null ? null : buyerTelephone.trim();
    }

    public String getBuyerMemo() {
        return buyerMemo;
    }

    public void setBuyerMemo(String buyerMemo) {
        this.buyerMemo = buyerMemo == null ? null : buyerMemo.trim();
    }

    public String getPayorName() {
        return payorName;
    }

    public void setPayorName(String payorName) {
        this.payorName = payorName == null ? null : payorName.trim();
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public Long getCatId1() {
        return catId1;
    }

    public void setCatId1(Long catId1) {
        this.catId1 = catId1;
    }

    public Long getCatId2() {
        return catId2;
    }

    public void setCatId2(Long catId2) {
        this.catId2 = catId2;
    }

    public Long getCatId3() {
        return catId3;
    }

    public void setCatId3(Long catId3) {
        this.catId3 = catId3;
    }

    public Long getCatId4() {
        return catId4;
    }

    public void setCatId4(Long catId4) {
        this.catId4 = catId4;
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode == null ? null : customsCode.trim();
    }

    public String getCiqbCode() {
        return ciqbCode;
    }

    public void setCiqbCode(String ciqbCode) {
        this.ciqbCode = ciqbCode == null ? null : ciqbCode.trim();
    }

    public String getBakbCode() {
        return bakbCode;
    }

    public void setBakbCode(String bakbCode) {
        this.bakbCode = bakbCode == null ? null : bakbCode.trim();
    }

    public Integer getCustomsType() {
        return customsType;
    }

    public void setCustomsType(Integer customsType) {
        this.customsType = customsType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo == null ? null : receiveNo.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    public String getPrintHeader() {
        return printHeader;
    }

    public void setPrintHeader(String printHeader) {
        this.printHeader = printHeader == null ? null : printHeader.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getRecipientProvincesCode() {
        return recipientProvincesCode;
    }

    public void setRecipientProvincesCode(String recipientProvincesCode) {
        this.recipientProvincesCode = recipientProvincesCode == null ? null : recipientProvincesCode.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName == null ? null : shipperName.trim();
    }

    public String getShipperPhone() {
        return shipperPhone;
    }

    public void setShipperPhone(String shipperPhone) {
        this.shipperPhone = shipperPhone == null ? null : shipperPhone.trim();
    }

    public String getShippernCode() {
        return shippernCode;
    }

    public void setShippernCode(String shippernCode) {
        this.shippernCode = shippernCode == null ? null : shippernCode.trim();
    }

    public String getShipperAddress() {
        return shipperAddress;
    }

    public void setShipperAddress(String shipperAddress) {
        this.shipperAddress = shipperAddress == null ? null : shipperAddress.trim();
    }

    public String getSendCity() {
        return sendCity;
    }

    public void setSendCity(String sendCity) {
        this.sendCity = sendCity == null ? null : sendCity.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public Integer getAfterSalesStatus() {
        return afterSalesStatus;
    }

    public void setAfterSalesStatus(Integer afterSalesStatus) {
        this.afterSalesStatus = afterSalesStatus;
    }

    public Integer getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Integer confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public String getTradeMode() {
        return tradeMode;
    }

    public void setTradeMode(String tradeMode) {
        this.tradeMode = tradeMode;
    }

    public Integer getIsStoreStrategy() {
        return isStoreStrategy;
    }

    public void setIsStoreStrategy(Integer isStoreStrategy) {
        this.isStoreStrategy = isStoreStrategy;
    }

    public Integer getOwnerFlag() {
        return ownerFlag;
    }

    public void setOwnerFlag(Integer ownerFlag) {
        this.ownerFlag = ownerFlag;
    }

    public Integer getFromEplat() {
        return fromEplat;
    }

    public void setFromEplat(Integer fromEplat) {
        this.fromEplat = fromEplat;
    }

    public String getReDeclare() {
        return reDeclare;
    }

    public void setReDeclare(String reDeclare) {
        this.reDeclare = reDeclare;
    }

    public String getStatisticsFlag() {
        return statisticsFlag;
    }

    public void setStatisticsFlag(String statisticsFlag) {
        this.statisticsFlag = statisticsFlag == null ? null : statisticsFlag.trim();
    }

    public Integer getVmiFlag() {
        return vmiFlag;
    }

    public void setVmiFlag(Integer vmiFlag) {
        this.vmiFlag = vmiFlag;
    }

    public String getWrapType() {
        return wrapType;
    }

    public void setWrapType(String wrapType) {
        this.wrapType = wrapType == null ? null : wrapType.trim();
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans == null ? null : trans.trim();
    }

    public String getTransMode() {
        return transMode;
    }

    public void setTransMode(String transMode) {
        this.transMode = transMode == null ? null : transMode.trim();
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo == null ? null : transNo.trim();
    }

    public Date getTransportDay() {
        return transportDay;
    }

    public void setTransportDay(Date transportDay) {
        this.transportDay = transportDay;
    }

    public BigDecimal getDeliveryInstallValue() {
        return deliveryInstallValue;
    }

    public void setDeliveryInstallValue(BigDecimal deliveryInstallValue) {
        this.deliveryInstallValue = deliveryInstallValue;
    }

    public BigDecimal getDeliveryHomeValue() {
        return deliveryHomeValue;
    }

    public void setDeliveryHomeValue(BigDecimal deliveryHomeValue) {
        this.deliveryHomeValue = deliveryHomeValue;
    }

    public BigDecimal getHomeInstallValue() {
        return homeInstallValue;
    }

    public void setHomeInstallValue(BigDecimal homeInstallValue) {
        this.homeInstallValue = homeInstallValue;
    }

    public Integer getHomeDeliveryType() {
        return homeDeliveryType;
    }

    public void setHomeDeliveryType(Integer homeDeliveryType) {
        this.homeDeliveryType = homeDeliveryType;
    }

    public BigDecimal getCapitalFreeDiscount() {
        return capitalFreeDiscount;
    }

    public void setCapitalFreeDiscount(BigDecimal capitalFreeDiscount) {
        this.capitalFreeDiscount = capitalFreeDiscount;
    }

    public Integer getReturnFreightPayer() {
        return returnFreightPayer;
    }

    public void setReturnFreightPayer(Integer returnFreightPayer) {
        this.returnFreightPayer = returnFreightPayer;
    }

    public Integer getSupportNationwideWarranty() {
        return supportNationwideWarranty;
    }

    public void setSupportNationwideWarranty(Integer supportNationwideWarranty) {
        this.supportNationwideWarranty = supportNationwideWarranty;
    }

    public Integer getFreeSf() {
        return freeSf;
    }

    public void setFreeSf(Integer freeSf) {
        this.freeSf = freeSf;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Integer getIsLuckyFlag() {
        return isLuckyFlag;
    }

    public void setIsLuckyFlag(Integer isLuckyFlag) {
        this.isLuckyFlag = isLuckyFlag;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public Integer getIsPreSale() {
        return isPreSale;
    }

    public void setIsPreSale(Integer isPreSale) {
        this.isPreSale = isPreSale;
    }

    public Integer getIsStockOut() {
        return isStockOut;
    }

    public void setIsStockOut(Integer isStockOut) {
        this.isStockOut = isStockOut;
    }

    public Integer getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Integer groupStatus) {
        this.groupStatus = groupStatus;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Integer getStockOutHandleStatus() {
        return stockOutHandleStatus;
    }

    public void setStockOutHandleStatus(Integer stockOutHandleStatus) {
        this.stockOutHandleStatus = stockOutHandleStatus;
    }

    public Integer getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Integer getOnlySupportReplace() {
        return onlySupportReplace;
    }

    public void setOnlySupportReplace(Integer onlySupportReplace) {
        this.onlySupportReplace = onlySupportReplace;
    }

    public String getLastShipTime() {
        return lastShipTime;
    }

    public void setLastShipTime(String lastShipTime) {
        this.lastShipTime = lastShipTime == null ? null : lastShipTime.trim();
    }

    public String getPreSaleTime() {
        return preSaleTime;
    }

    public void setPreSaleTime(String preSaleTime) {
        this.preSaleTime = preSaleTime == null ? null : preSaleTime.trim();
    }

    public String getLogistCode() {
        return logistCode;
    }

    public void setLogistCode(String logistCode) {
        this.logistCode = logistCode == null ? null : logistCode.trim();
    }

    public Long getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Long logisticsId) {
        this.logisticsId = logisticsId;
    }

    public BigDecimal getAdvancedPaidFee() {
        return advancedPaidFee;
    }

    public void setAdvancedPaidFee(BigDecimal advancedPaidFee) {
        this.advancedPaidFee = advancedPaidFee;
    }

    public BigDecimal getStepPaidFee() {
        return stepPaidFee;
    }

    public void setStepPaidFee(BigDecimal stepPaidFee) {
        this.stepPaidFee = stepPaidFee;
    }

    public BigDecimal getStepDiscountAmount() {
        return stepDiscountAmount;
    }

    public void setStepDiscountAmount(BigDecimal stepDiscountAmount) {
        this.stepDiscountAmount = stepDiscountAmount;
    }

    public Integer getStepTradeStatus() {
        return stepTradeStatus;
    }

    public void setStepTradeStatus(Integer stepTradeStatus) {
        this.stepTradeStatus = stepTradeStatus;
    }

    public String getWareSn() {
        return wareSn;
    }

    public void setWareSn(String wareSn) {
        this.wareSn = wareSn == null ? null : wareSn.trim();
    }

    public Integer getWareType() {
        return wareType;
    }

    public void setWareType(Integer wareType) {
        this.wareType = wareType;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName == null ? null : wareName.trim();
    }

    public String getWareId() {
        return wareId;
    }

    public void setWareId(String wareId) {
        this.wareId = wareId == null ? null : wareId.trim();
    }

    public String getDepotCode() {
        return depotCode;
    }

    public void setDepotCode(String depotCode) {
        this.depotCode = depotCode == null ? null : depotCode.trim();
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName == null ? null : depotName.trim();
    }

    public String getDepotId() {
        return depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId == null ? null : depotId.trim();
    }

    public Integer getDepotType() {
        return depotType;
    }

    public void setDepotType(Integer depotType) {
        this.depotType = depotType;
    }

    public String getBlno() {
        return blno;
    }

    public void setBlno(String blno) {
        this.blno = blno == null ? null : blno.trim();
    }

    public String getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(String changeFlag) {
        this.changeFlag = changeFlag == null ? null : changeFlag.trim();
    }

    public String getCongratulations() {
        return congratulations;
    }

    public void setCongratulations(String congratulations) {
        this.congratulations = congratulations == null ? null : congratulations.trim();
    }

    public String getCutMode() {
        return cutMode;
    }

    public void setCutMode(String cutMode) {
        this.cutMode = cutMode == null ? null : cutMode.trim();
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getForSellComp() {
        return forSellComp;
    }

    public void setForSellComp(String forSellComp) {
        this.forSellComp = forSellComp == null ? null : forSellComp.trim();
    }

    public String getForSellCompName() {
        return forSellCompName;
    }

    public void setForSellCompName(String forSellCompName) {
        this.forSellCompName = forSellCompName == null ? null : forSellCompName.trim();
    }

    public String getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(String goodsInfo) {
        this.goodsInfo = goodsInfo == null ? null : goodsInfo.trim();
    }

    public String getPackingMaterial() {
        return packingMaterial;
    }

    public void setPackingMaterial(String packingMaterial) {
        this.packingMaterial = packingMaterial == null ? null : packingMaterial.trim();
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType == null ? null : opType.trim();
    }

    public String getOrdExcStatus() {
        return ordExcStatus;
    }

    public void setOrdExcStatus(String ordExcStatus) {
        this.ordExcStatus = ordExcStatus == null ? null : ordExcStatus.trim();
    }

    public String getSendState() {
        return sendState;
    }

    public void setSendState(String sendState) {
        this.sendState = sendState == null ? null : sendState.trim();
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public String getTradeUnitCode() {
        return tradeUnitCode;
    }

    public void setTradeUnitCode(String tradeUnitCode) {
        this.tradeUnitCode = tradeUnitCode == null ? null : tradeUnitCode.trim();
    }

    public String getTradeUnitName() {
        return tradeUnitName;
    }

    public void setTradeUnitName(String tradeUnitName) {
        this.tradeUnitName = tradeUnitName == null ? null : tradeUnitName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public String getPayNots() {
        return payNots;
    }

    public void setPayNots(String payNots) {
        this.payNots = payNots == null ? null : payNots.trim();
    }

    public String getLogNots() {
        return logNots;
    }

    public void setLogNots(String logNots) {
        this.logNots = logNots == null ? null : logNots.trim();
    }

    public String getSendSystem() {
        return sendSystem;
    }

    public void setSendSystem(String sendSystem) {
        this.sendSystem = sendSystem;
    }
}