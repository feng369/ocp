package com.topideal.supplychain.ocp.pdd.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author klw
 * @date 2019/12/13 16:12
 * @description:  拼多多抓单实体，解决拼多多的抓单类某些字段类型与数据库及缺少的字段问题
 */
public class CatchOrderPddDto {

    private BigDecimal deliveryInstallValue;

    private BigDecimal deliveryHomeValue;

    private BigDecimal homeInstallValue;

    private String idCardNum;

    private String idCardName;

    private Integer homeDeliveryType;

    private Integer freeSf;

    private Integer groupStatus;

    private Integer confirmStatus;

    private Integer tradeType;

    private String payTime;

    private Integer supportNationwideWarranty;

    private Integer returnFreightPayer;

    private Integer onlySupportReplace;

    private Integer afterSalesStatus;

    private Integer isPreSale;

    private String preSaleTime;

    private Integer invoiceStatus;

    private String buyerMemo;

    private String innerTransactionId;

    private Long catId1;

    private Long catId2;

    private Long catId3;

    private Long catId4;

    private Integer stockOutHandleStatus;

    private Integer isStockOut;

    private Integer countryId;

    private Integer provinceId;

    private Integer cityId;

    private Integer townId;

    private String receiveTime;

    private BigDecimal capitalFreeDiscount;

    private String orderSn;

    private String confirmTime;

    private String receiverName;

    private Date createdTime;

    private String country;

    private String province;

    private String city;

    private String town;

    private String address;

    private String receiverPhone;

    private BigDecimal payAmount;

    private BigDecimal goodsAmount;

    private BigDecimal discountAmount;

    private BigDecimal postage;

    private String payNo;

    private String payType;

    private Long logisticsId;

    private String trackingNumber;

    private String shippingTime;

    private Integer orderStatus;

    private Integer isLuckyFlag;

    private Integer refundStatus;

    private String updatedAt;

    private String lastShipTime;

    private String remark;

    private BigDecimal platformDiscount;

    private BigDecimal sellerDiscount;

    private String receiverAddress;

    private List<CatchOrderPddItemDto> itemList;

    private CatchOrderPddDepotDto orderDepotInfo;

    private CatchOrderPddStepDto stepOrderInfo;

    private List<CatchOrderPddCardDto> cardInfoList;

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

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getIdCardName() {
        return idCardName;
    }

    public void setIdCardName(String idCardName) {
        this.idCardName = idCardName;
    }

    public Integer getHomeDeliveryType() {
        return homeDeliveryType;
    }

    public void setHomeDeliveryType(Integer homeDeliveryType) {
        this.homeDeliveryType = homeDeliveryType;
    }

    public Integer getFreeSf() {
        return freeSf;
    }

    public void setFreeSf(Integer freeSf) {
        this.freeSf = freeSf;
    }

    public Integer getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Integer groupStatus) {
        this.groupStatus = groupStatus;
    }

    public Integer getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Integer confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Integer getSupportNationwideWarranty() {
        return supportNationwideWarranty;
    }

    public void setSupportNationwideWarranty(Integer supportNationwideWarranty) {
        this.supportNationwideWarranty = supportNationwideWarranty;
    }

    public Integer getReturnFreightPayer() {
        return returnFreightPayer;
    }

    public void setReturnFreightPayer(Integer returnFreightPayer) {
        this.returnFreightPayer = returnFreightPayer;
    }

    public Integer getOnlySupportReplace() {
        return onlySupportReplace;
    }

    public void setOnlySupportReplace(Integer onlySupportReplace) {
        this.onlySupportReplace = onlySupportReplace;
    }

    public Integer getAfterSalesStatus() {
        return afterSalesStatus;
    }

    public void setAfterSalesStatus(Integer afterSalesStatus) {
        this.afterSalesStatus = afterSalesStatus;
    }

    public Integer getIsPreSale() {
        return isPreSale;
    }

    public void setIsPreSale(Integer isPreSale) {
        this.isPreSale = isPreSale;
    }

    public String getPreSaleTime() {
        return preSaleTime;
    }

    public void setPreSaleTime(String preSaleTime) {
        this.preSaleTime = preSaleTime;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getBuyerMemo() {
        return buyerMemo;
    }

    public void setBuyerMemo(String buyerMemo) {
        this.buyerMemo = buyerMemo;
    }

    public String getInnerTransactionId() {
        return innerTransactionId;
    }

    public void setInnerTransactionId(String innerTransactionId) {
        this.innerTransactionId = innerTransactionId;
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

    public Integer getStockOutHandleStatus() {
        return stockOutHandleStatus;
    }

    public void setStockOutHandleStatus(Integer stockOutHandleStatus) {
        this.stockOutHandleStatus = stockOutHandleStatus;
    }

    public Integer getIsStockOut() {
        return isStockOut;
    }

    public void setIsStockOut(Integer isStockOut) {
        this.isStockOut = isStockOut;
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

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public BigDecimal getCapitalFreeDiscount() {
        return capitalFreeDiscount;
    }

    public void setCapitalFreeDiscount(BigDecimal capitalFreeDiscount) {
        this.capitalFreeDiscount = capitalFreeDiscount;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Long getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Long logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(String shippingTime) {
        this.shippingTime = shippingTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getIsLuckyFlag() {
        return isLuckyFlag;
    }

    public void setIsLuckyFlag(Integer isLuckyFlag) {
        this.isLuckyFlag = isLuckyFlag;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLastShipTime() {
        return lastShipTime;
    }

    public void setLastShipTime(String lastShipTime) {
        this.lastShipTime = lastShipTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public List<CatchOrderPddItemDto> getItemList() {
        return itemList;
    }

    public void setItemList(List<CatchOrderPddItemDto> itemList) {
        this.itemList = itemList;
    }

    public CatchOrderPddDepotDto getOrderDepotInfo() {
        return orderDepotInfo;
    }

    public void setOrderDepotInfo(CatchOrderPddDepotDto orderDepotInfo) {
        this.orderDepotInfo = orderDepotInfo;
    }

    public CatchOrderPddStepDto getStepOrderInfo() {
        return stepOrderInfo;
    }

    public void setStepOrderInfo(CatchOrderPddStepDto stepOrderInfo) {
        this.stepOrderInfo = stepOrderInfo;
    }

    public List<CatchOrderPddCardDto> getCardInfoList() {
        return cardInfoList;
    }

    public void setCardInfoList(List<CatchOrderPddCardDto> cardInfoList) {
        this.cardInfoList = cardInfoList;
    }
}
