package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.CustomsCodeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 海拍客订单model
 *
 * @author xuxiaoyan
 * @date 2019-12-16 15:06
 */
public class OrderHipac extends BaseEntity {

    /**
     * OCP内部订单号
     */
    private String code;

    /**
     * 企业订单号，取接口报文orderNum
     */
    private String orderNum;
    /**
     * 订单生成时间,取接口报文orderDate
     */
    private Date orderDate;
    /**
     * 供应商/店铺标识,对应第三方物流编码、电商企业编码
     */
    private String supplierSenderID;
    /**
     * 订单状态，10、20、60、70对应制单、已抓取详细、下发失败、下发成功
     */
    private OrderStatusEnum status;
    /**
     * 电商平台编码，系统设置默认值
     */
    private String cbePcomCode;
    /**
     * 进口模式，系统设置默认值
     */
    private BusiModeEnum busiMode;
    /**
     * 申报关区，系统设置默认值
     */
    private CustomsCodeEnum customsCode;
    /**
     * 申报国检，系统设置默认值
     */
    private String ciqbCode;
    /**
     * 运费,取接口报文logisticsAmount
     */
    private BigDecimal logisticsAmount;
    /**
     * 税费,取接口报文totalTaxAmount
     */
    private BigDecimal totalTaxAmount;
    /**
     * 优惠减免金额
     */
    private BigDecimal discount;
    /**
     * 实际支付金额,取接口报文totalPayAmount
     */
    private BigDecimal totalPayAmount;
    /**
     * 订购人姓名、收货人姓名，取接口报文custName
     */
    private String custName;
    /**
     * 订购人身份证号，取接口报文custIdNum，加密存储
     */
    private String custIdNum;
    /**
     * 订购人手机号，取接口报文custPhone
     */
    private String custPhone;
    /**
     * 省，取接口报文custProvice
     */
    private String custProvice;
    /**
     * 市，取接口报文custCity
     */
    private String custCity;
    /**
     * 区，取接口报文custArea
     */
    private String custArea;
    /**
     * 详细地址，取接口报文custAddress
     */
    private String custAddress;
    /**
     * 货值，取接口报文totalOrderAmount
     */
    private BigDecimal totalOrderAmount;
    /**
     * 运单号
     */
    private String deliveryCode;
    /**
     * 支付方式，取接口报文payType
     */
    private String payType;
    /**
     * 支付流水号，取接口报文payNo
     */
    private String payNo;
    /**
     * 支付时间，取接口报文payTime
     */
    private Date payTime;
    /**
     * 支付公司，取接口报文payCompanyName
     */
    private String payCompanyName;
    /**
     * 推op失败原因
     */
    private String opReason;

    /**
     * 订单商品信息
     **/
    private List<OrderHipacItem> orderItemList;

    /**
     * 店铺名称
     **/
    private String shopName;

    /**
     * 店铺编码
     **/
    private String shopNum;

    /**
     * 转发系统
     * @return
     */
    private ForwardSystemEnum sendSystem;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getSupplierSenderID() {
        return supplierSenderID;
    }

    public void setSupplierSenderID(String supplierSenderID) {
        this.supplierSenderID = supplierSenderID;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public String getCbePcomCode() {
        return cbePcomCode;
    }

    public void setCbePcomCode(String cbePcomCode) {
        this.cbePcomCode = cbePcomCode;
    }

    public BusiModeEnum getBusiMode() {
        return busiMode;
    }

    public void setBusiMode(BusiModeEnum busiMode) {
        this.busiMode = busiMode;
    }

    public CustomsCodeEnum getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(CustomsCodeEnum customsCode) {
        this.customsCode = customsCode;
    }

    public String getCiqbCode() {
        return ciqbCode;
    }

    public void setCiqbCode(String ciqbCode) {
        this.ciqbCode = ciqbCode;
    }

    public BigDecimal getLogisticsAmount() {
        return logisticsAmount;
    }

    public void setLogisticsAmount(BigDecimal logisticsAmount) {
        this.logisticsAmount = logisticsAmount;
    }

    public BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustIdNum() {
        return custIdNum;
    }

    public void setCustIdNum(String custIdNum) {
        this.custIdNum = custIdNum;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustProvice() {
        return custProvice;
    }

    public void setCustProvice(String custProvice) {
        this.custProvice = custProvice;
    }

    public String getCustCity() {
        return custCity;
    }

    public void setCustCity(String custCity) {
        this.custCity = custCity;
    }

    public String getCustArea() {
        return custArea;
    }

    public void setCustArea(String custArea) {
        this.custArea = custArea;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public BigDecimal getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayCompanyName() {
        return payCompanyName;
    }

    public void setPayCompanyName(String payCompanyName) {
        this.payCompanyName = payCompanyName;
    }

    public String getOpReason() {
        return opReason;
    }

    public void setOpReason(String opReason) {
        this.opReason = opReason;
    }

    public List<OrderHipacItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(
            List<OrderHipacItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopNum() {
        return shopNum;
    }

    public void setShopNum(String shopNum) {
        this.shopNum = shopNum;
    }

    public ForwardSystemEnum getSendSystem() {
        return sendSystem;
    }

    public void setSendSystem(ForwardSystemEnum sendSystem) {
        this.sendSystem = sendSystem;
    }
}
