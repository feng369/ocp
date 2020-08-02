package com.topideal.supplychain.ocp.pdd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author klw
 * @date 2019/12/17 15:13
 * @description:
 */
public class PddReceiveRecipient {
    //收货人姓名
    private String name;
    //证件类型
    private String receiveType;
    //收件人证件号
    private String receiveNo;
    //手机号码
    private String mobilePhone;
    //座机
    private String phone;
    //国家
    private String country;
    //指运港代码
    private String pod;
    //省份
    private String province;
    //市
    private String city;
    //区/县
    private String district;
    //地址
    private String address;
    //邮编
    private String postCode;
    //订单优惠金额
    private BigDecimal totalFavourable;
    //送礼人
    private String sender;
    //收礼人
    private String receiver;
    //祝贺语
    private String congratulations;
    //配送时间
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date transportDay;
    //收货人省市代码
    private String recipientProvincesCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public BigDecimal getTotalFavourable() {
        return totalFavourable;
    }

    public void setTotalFavourable(BigDecimal totalFavourable) {
        this.totalFavourable = totalFavourable;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getCongratulations() {
        return congratulations;
    }

    public void setCongratulations(String congratulations) {
        this.congratulations = congratulations;
    }

    public Date getTransportDay() {
        return transportDay;
    }

    public void setTransportDay(Date transportDay) {
        this.transportDay = transportDay;
    }

    public String getRecipientProvincesCode() {
        return recipientProvincesCode;
    }

    public void setRecipientProvincesCode(String recipientProvincesCode) {
        this.recipientProvincesCode = recipientProvincesCode;
    }
}
