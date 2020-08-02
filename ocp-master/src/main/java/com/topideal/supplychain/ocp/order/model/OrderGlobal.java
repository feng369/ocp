package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 全区仓订单
 */
public class OrderGlobal extends BaseEntity {

    /**
     * 内部订单号
     */
    private String code;

    /**
     * 申报单号
     */
    private String declareCode;

    /**
     * 订单日期
     */
    private Date orderDate;

    /**
     * 口岸编码(NBPORT:宁波口岸、CQPORT:重庆口岸、GZPORT:广州口岸、HZPORT:杭州口岸、BJPORT:北京口岸、CDPORT:成都口岸...)
     */
    private String portCode;

    /**
     * 电商平台名称
     */
    private String ebpName;

    /**
     * 电商平台编码
     */
    private String ebpCode;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 申报支付方式(ALIPAY：支付宝，WEIXINPAY：微信)
     */
    private String declarePayType;

    /**
     * 支付单申报的支付金额(单位:分)
     */
    private BigDecimal declarePayAmount;

    /**
     * 申报支付流水
     */
    private String declarePayNo;

    /**
     * 收件人姓名
     */
    private String name;

    /**
     * 收件人手机
     */
    private String phone;

    /**
     * 收件人省
     */
    private String province;

    /**
     * 收件人市
     */
    private String city;

    /**
     * 收件人区
     */
    private String district;

    /**
     * 收件人地址
     */
    private String street;

    /**
     * 支付人证件类型(1：身份证，2：护照，3，其他)
     */
    private Integer payerIdType;

    /**
     * 支付人证件号码
     */
    private String payerIdNo;

    /**
     * 支付人姓名
     */
    private String payerName;

    /**
     * 实际交易金额，单位分
     */
    private BigDecimal tradeAmount;

    /**
     * 商品总金额，单位分
     */
    private BigDecimal goodsTotalAmount;

    /**
     * 折扣金额，单位分
     */
    private BigDecimal discountAmount;

    /**
     * 运费，单位分
     */
    private BigDecimal freightAmount;

    /**
     * 保险费用，单位分
     */
    private BigDecimal insuranceAmount;

    /**
     * 总税金，单位分
     */
    private BigDecimal taxAmount;

    /**
     * 店铺编码
     */
    private String storeCode;

    /**
     * 电商企业代码
     */
    private String ebcCode;

    /**
     * 电商企业名称
     */
    private String ebcName;

    /**
     * 物流企业代码
     */
    private String logisticsCode;

    /**
     * 物流企业名称
     */
    private String logisticsName;

    /**
     * 申报海关代码
     */
    private String customsCode;

    /**
     * 业务模式
     */
    private BusiModeEnum busiMode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 订单状态:10 未同步；60下发失败；70下发成功；100回抛失败；110回抛成功
     */
    private OrderStatusEnum status;

    /**
     * 国检检区代码
     */
    private String ciqCode;

    /**
     * 转发系统
     */
    private ForwardSystemEnum sendSystem;

    /**
     * 海关类型
     */
    private String customsType;

    /**
     * 仓库编码
     */
    private String warehouseCode;

    /**
     * 下发时间
     */
    private Date sendTime;

    /**
     * 商品列表
     */
    private List<OrderGlobalItem> itemList;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeclareCode() {
        return declareCode;
    }

    public void setDeclareCode(String declareCode) {
        this.declareCode = declareCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPortCode() {
        return portCode;
    }

    public void setPortCode(String portCode) {
        this.portCode = portCode == null ? null : portCode.trim();
    }

    public String getEbpName() {
        return ebpName;
    }

    public void setEbpName(String ebpName) {
        this.ebpName = ebpName == null ? null : ebpName.trim();
    }

    public String getEbpCode() {
        return ebpCode;
    }

    public void setEbpCode(String ebpCode) {
        this.ebpCode = ebpCode == null ? null : ebpCode.trim();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getDeclarePayType() {
        return declarePayType;
    }

    public void setDeclarePayType(String declarePayType) {
        this.declarePayType = declarePayType == null ? null : declarePayType.trim();
    }

    public BigDecimal getDeclarePayAmount() {
        return declarePayAmount;
    }

    public void setDeclarePayAmount(BigDecimal declarePayAmount) {
        this.declarePayAmount = declarePayAmount;
    }

    public String getDeclarePayNo() {
        return declarePayNo;
    }

    public void setDeclarePayNo(String declarePayNo) {
        this.declarePayNo = declarePayNo == null ? null : declarePayNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public Integer getPayerIdType() {
        return payerIdType;
    }

    public void setPayerIdType(Integer payerIdType) {
        this.payerIdType = payerIdType;
    }

    public String getPayerIdNo() {
        return payerIdNo;
    }

    public void setPayerIdNo(String payerIdNo) {
        this.payerIdNo = payerIdNo == null ? null : payerIdNo.trim();
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName == null ? null : payerName.trim();
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public BigDecimal getGoodsTotalAmount() {
        return goodsTotalAmount;
    }

    public void setGoodsTotalAmount(BigDecimal goodsTotalAmount) {
        this.goodsTotalAmount = goodsTotalAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }

    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }

    public BigDecimal getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(BigDecimal insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode == null ? null : storeCode.trim();
    }

    public String getEbcCode() {
        return ebcCode;
    }

    public void setEbcCode(String ebcCode) {
        this.ebcCode = ebcCode == null ? null : ebcCode.trim();
    }

    public String getEbcName() {
        return ebcName;
    }

    public void setEbcName(String ebcName) {
        this.ebcName = ebcName == null ? null : ebcName.trim();
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode == null ? null : logisticsCode.trim();
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName == null ? null : logisticsName.trim();
    }

    public String getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(String customsCode) {
        this.customsCode = customsCode == null ? null : customsCode.trim();
    }

    public BusiModeEnum getBusiMode() {
        return busiMode;
    }

    public void setBusiMode(BusiModeEnum busiMode) {
        this.busiMode = busiMode;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCiqCode() {
        return ciqCode;
    }

    public void setCiqCode(String ciqCode) {
        this.ciqCode = ciqCode == null ? null : ciqCode.trim();
    }

    public ForwardSystemEnum getSendSystem() {
        return sendSystem;
    }

    public void setSendSystem(ForwardSystemEnum sendSystem) {
        this.sendSystem = sendSystem;
    }

    public String getCustomsType() {
        return customsType;
    }

    public void setCustomsType(String customsType) {
        this.customsType = customsType == null ? null : customsType.trim();
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode == null ? null : warehouseCode.trim();
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public List<OrderGlobalItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderGlobalItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}