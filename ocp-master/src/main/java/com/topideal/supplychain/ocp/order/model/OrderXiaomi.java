package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.util.Date;

public class OrderXiaomi extends BaseEntity {

    /**
     * ocp内部订单号
     */
    private String code;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 店铺编码
     */
    private String storeCode;

    /**
     * 电商企业编码
     */
    private String ebcCode;

    /**
     * 收货地址省
     */
    private String provinceName;

    /**
     * 收货地址市
     */
    private String cityName;

    /**
     * 收货地址区
     */
    private String districtName;

    /**
     * 县镇 	3.31新增
     */
    private String areaName;

    /**
     * 收货详细地址
     */
    private String address;

    /**
     * 收件人电话
     */
    private String tel;

    /**
     * 收件人名字
     */
    private String consignee;

    /**
     * 邮编
     */
    private String zipcode;

    /**
     * 订购人身份证号码
     */
    private String cardId;

    /**
     * 订购人名字
     */
    private String cardName;

    /**
     * 支付类型
     */
    private String importationType;

    /**
     * 优惠金额
     */
    private BigDecimal couponAmount;

    /**
     * 运费 单位：分
     */
    private BigDecimal shipFee;

    /**
     * 订单总价 单位：分
     */
    private BigDecimal totalPrice;

    /**
     * 订单创建时间
     */
    private Date ctime;

    /**
     * 订单支付时间
     */
    private Date ftime;

    /**
     * 发送系统
     */
    private ForwardSystemEnum sendSystem;

    /**
     * 发送状态
     */
    private OrderStatusEnum sendStatus;

    /**
     * 失败原因
     */
    private String failReason;


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

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getEbcCode() {
        return ebcCode;
    }

    public void setEbcCode(String ebcCode) {
        this.ebcCode = ebcCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getImportationType() {
        return importationType;
    }

    public void setImportationType(String importationType) {
        this.importationType = importationType;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getShipFee() {
        return shipFee;
    }

    public void setShipFee(BigDecimal shipFee) {
        this.shipFee = shipFee;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getFtime() {
        return ftime;
    }

    public void setFtime(Date ftime) {
        this.ftime = ftime;
    }

    public ForwardSystemEnum getSendSystem() {
        return sendSystem;
    }

    public void setSendSystem(ForwardSystemEnum sendSystem) {
        this.sendSystem = sendSystem;
    }

    public OrderStatusEnum getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(OrderStatusEnum sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}