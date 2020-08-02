package com.topideal.supplychain.ocp.order.dto;

import com.topideal.supplychain.ocp.order.model.OrderDaling;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 达令家
 *
 * @author xuxiaoyan
 * @date 2019-11-29 15:54
 */
public class OrderDalingDto extends OrderDaling {

    /**
     * 制单时间（查询条件）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;

    /**
     * 订单生成时间（查询条件）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDateEnd;

    private BigDecimal freightPrice;
    private BigDecimal taxAmount;
    private BigDecimal discountTotalPrice;
    private String customerName;
    private BigDecimal paymentAmount;
    private String address;
    private BigDecimal orderAmount;
    private String modeOfPayment;
    private String paymentTransaction;
    private String vendorCode;
    private String postCode;
    private String transportType;

    private String customsCodeStr;

    private String busiModeStr;

    private String statusStr;

    public String getCustomsCodeStr() {
        return customsCodeStr;
    }

    public void setCustomsCodeStr(String customsCodeStr) {
        this.customsCodeStr = customsCodeStr;
    }

    public String getBusiModeStr() {
        return busiModeStr;
    }

    public void setBusiModeStr(String busiModeStr) {
        this.busiModeStr = busiModeStr;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Date getCreateDateStart() {
        return createDateStart;
    }

    public void setCreateDateStart(Date createDateStart) {
        this.createDateStart = createDateStart;
    }

    public Date getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(Date createDateEnd) {
        this.createDateEnd = createDateEnd;
    }

    public BigDecimal getFreightPrice() {
        return super.getOrderDetail().getFreightPrice();
    }

    public BigDecimal getTaxAmount() {
        return super.getOrderDetail().getTaxAmount();
    }

    public BigDecimal getDiscountTotalPrice() {
        return super.getOrderDetail().getDiscountTotalPrice();
    }

    public String getCustomerName() {
        return super.getOrderDetail().getCustomerName();
    }

    public BigDecimal getPaymentAmount() {
        return super.getOrderDetail().getPaymentAmount();
    }

    public String getAddress() {
        return super.getOrderDetail().getAddress();
    }

    public BigDecimal getOrderAmount() {
        return super.getOrderDetail().getOrderAmount();
    }

    public String getModeOfPayment() {
        return super.getOrderDetail().getModeOfPayment();
    }

    public String getPaymentTransaction() {
        return super.getOrderDetail().getPaymentTransaction();
    }

    public String getVendorCode() {
        return super.getOrderDetail().getVendorCode();
    }

    public String getPostCode() {
        return super.getOrderDetail().getPostCode();
    }

    public String getTransportType() {
        return super.getOrderDetail().getTransportType();
    }
}
