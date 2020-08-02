package com.topideal.supplychain.ocp.fx.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 标题：大订单接单请求收货信息DTO 模块：ocp-parent 版权: Copyright © 2019 topideal 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.big.dto 作者：songping 创建日期：2019/12/26 17:26
 *
 * @version 1.0
 */

public class FxRecipientDto implements Serializable {

    /**
     * 收货人姓名
     */
    private String name;
    /**
     * 收货人证件类型
     */
    private Integer receiveType;
    /**
     * 收件人证件号
     */
    private String receiveNo;
    /**
     * 收货人手机号码
     */
    private String mobilePhone;
    /**
     * 收货人座机
     */
    private String phone;
    /**
     * 收货人国家
     */
    private String country;
    /**
     * 指运港代码
     */
    private String pod;
    /**
     * 收货人省份
     */
    private String province;
    /**
     * 收货人市
     */
    private String city;
    /**
     * 收货人区/县
     */
    private String district;
    /**
     * 收货人地址
     */
    private String address;
    /**
     * 收货人邮编
     */
    private String postCode;
    /**
     * 订单优惠金额
     */
    private BigDecimal totalFavourable;
    /**
     * 送礼人
     */
    private String sender;
    /**
     * 收礼人
     */
    private String receiver;
    /**
     * 祝贺语
     */
    private String congratulations;
    /**
     * 配送时间
     */
    private String transportDay;
    /**
     * 收货人省市代码
     */
    private String recipientProvincesCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
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

    public String getTransportDay() {
        return transportDay;
    }

    public void setTransportDay(String transportDay) {
        this.transportDay = transportDay;
    }

    public String getRecipientProvincesCode() {
        return recipientProvincesCode;
    }

    public void setRecipientProvincesCode(String recipientProvincesCode) {
        this.recipientProvincesCode = recipientProvincesCode;
    }
}
