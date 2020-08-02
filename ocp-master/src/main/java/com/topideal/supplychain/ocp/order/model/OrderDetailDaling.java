package com.topideal.supplychain.ocp.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单明细
 *
 * @author xuxiaoyan
 * @date 2019-11-29 11:34
 */
public class OrderDetailDaling {

    //返回状态码
    @JsonProperty("result")
    private int result;

    //订单号
    @JsonProperty("order_no")
    private String orderNo;

    //顾客姓名
    @JsonProperty("customer_name")
    private String customerName;

    //顾客邮编
    @JsonProperty("postcode")
    private String postCode;

    //详细地址
    @JsonProperty("address")
    private String address;

    //顾客身份证号
    @JsonProperty("id_card")
    private String idCard;

    //支付人姓名
    @JsonProperty("buyer_name")
    private String buyerName;

    //支付人身份证号
    @JsonProperty("buyer_id_number")
    private String buyerIdNumber;

    //订单状态:1有效 2无效
    @JsonProperty("status")
    private String status;

    //顾客联系电话
    @JsonProperty("contact_phone")
    private String contactPhone;

    //物流公司
    @JsonProperty("logistics_company")
    private String logisticsCompany;

    //物流单号
    @JsonProperty("logistics_number")
    private String logisticsNumber;

    //订单商品金额
    @JsonProperty("order_amount")
    private BigDecimal orderAmount;

    //税额
    @JsonProperty("tax_amount")
    private BigDecimal taxAmount;

    //实际支付
    @JsonProperty("payment_amount")
    private BigDecimal paymentAmount;

    //支付方式
    @JsonProperty("mode_of_payment")
    private String modeOfPayment;

    //支付流水号
    @JsonProperty("payment_transaction")
    private String paymentTransaction;

    //是否精品盒（Y：是，N:否）
    @JsonProperty("is_box")
    private String isBox;

    //生单时间（格式 yyyy-MM-dd HH:mm:ss）
    @JsonProperty("created_date")
    private String createdDate;

    //是否隐藏发货单价格(Y:是，N:否)
    @JsonProperty("is_hide_ao")
    private String isHideAo;

    //订单总优惠金额
    @JsonProperty("discount_total_price")
    private BigDecimal discountTotalPrice;

    //运费
    @JsonProperty("freight_price")
    private BigDecimal freightPrice;

    //供应商编码
    @JsonProperty("vendor_code")
    private String vendorCode;

    //订单类型，订单海外、国内发货区分
    @JsonProperty("transport_type")
    private String transportType;

    //商品
    private List<goods> orderItem;

    public static class goods {

        //达令规格编码
        private String sku;

        //供应商产品编码
        @JsonProperty("vendor_sku")
        private String vendorSku;

        //厂商条码
        private String upc;

        //产品名称
        @JsonProperty("product_name")
        private String productName;

        //购买数量
        private int qty;

        //商品售价(单价)
        @JsonProperty("unit_price")
        private BigDecimal unitPrice;

        //商品支付金
        @JsonProperty("payment_unit_price")
        private BigDecimal paymentUnitPrice;

        //商品税额(税额行小计)
        @JsonProperty("unit_tax_price")
        private BigDecimal unitTaxPrice;

        //商品优惠金额（优惠行小计）
        @JsonProperty("discount_price")
        private BigDecimal discountPrice;

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getVendorSku() {
            return vendorSku;
        }

        public void setVendorSku(String vendorSku) {
            this.vendorSku = vendorSku;
        }

        public String getUpc() {
            return upc;
        }

        public void setUpc(String upc) {
            this.upc = upc;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public BigDecimal getPaymentUnitPrice() {
            return paymentUnitPrice;
        }

        public void setPaymentUnitPrice(BigDecimal paymentUnitPrice) {
            this.paymentUnitPrice = paymentUnitPrice;
        }

        public BigDecimal getUnitTaxPrice() {
            return unitTaxPrice;
        }

        public void setUnitTaxPrice(BigDecimal unitTaxPrice) {
            this.unitTaxPrice = unitTaxPrice;
        }

        public BigDecimal getDiscountPrice() {
            return discountPrice;
        }

        public void setDiscountPrice(BigDecimal discountPrice) {
            this.discountPrice = discountPrice;
        }
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerIdNumber() {
        return buyerIdNumber;
    }

    public void setBuyerIdNumber(String buyerIdNumber) {
        this.buyerIdNumber = buyerIdNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getPaymentTransaction() {
        return paymentTransaction;
    }

    public void setPaymentTransaction(String paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }

    public String getIsBox() {
        return isBox;
    }

    public void setIsBox(String isBox) {
        this.isBox = isBox;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getIsHideAo() {
        return isHideAo;
    }

    public void setIsHideAo(String isHideAo) {
        this.isHideAo = isHideAo;
    }

    public BigDecimal getDiscountTotalPrice() {
        return discountTotalPrice;
    }

    public void setDiscountTotalPrice(BigDecimal discountTotalPrice) {
        this.discountTotalPrice = discountTotalPrice;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public List<goods> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(
            List<goods> orderItem) {
        this.orderItem = orderItem;
    }
}
