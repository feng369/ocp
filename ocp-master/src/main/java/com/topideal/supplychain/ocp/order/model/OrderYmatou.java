package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wanzhaozhang
 * @date 2019/11/28
 * @description 洋码头订单 实体bean
 **/
public class OrderYmatou extends BaseEntity {

    /**
     * 买手名称
     */
    private String sellerId;

    /**
     * 内部订单号
     */
    private String code;

    //申报单号
    private String orderNo;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 主单号，合并支付产生的id
     */
    private String tradeId;

    /**
     * 订单状态
     */
    private Long orderStatus;

    /**
     * 订单金额
     */
    private BigDecimal amount;

    /**
     * 买家应付金额
     */
    private BigDecimal payment;

    /**
     * 订单运费金额
     */
    private BigDecimal shippingFee;

    /**
     * 平台优惠券分摊金额
     */
    private BigDecimal pCouponDiscount;

    /**
     * 买手优惠券分摊金额
     */
    private BigDecimal mCouponDiscount;

    /**
     * 平台促销活动分摊金额
     */
    private BigDecimal pPromotionDiscount;

    /**
     * 买手促销活动分摊金额
     */
    private BigDecimal mPromotionDiscount;

    /**
     * 买手调价金额
     */
    private BigDecimal mAdjustDiscount;

    /**
     * 安利返点金额
     */
    private BigDecimal rebateAmount;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 付款时间
     */
    private Date paidTime;

    /**
     * 发货时间
     */
    private Date shippingTime;

    /**
     * 取消时间
     */
    private Date cancelTime;

    /**
     * 接单时间
     */
    private Date acceptTime;

    /**
     * 买手备注
     */
    private String sellerMemo;

    /**
     * 买家留言
     */
    private String buyerRemark;

    /**
     * 买家id
     */
    private String buyerId;

    /**
     * 收件人姓名
     */
    private String receiverName;

    /**
     * 收件人国家
     */
    private String receiverState;

    /**
     * 收件人地址
     */
    private String receiverAddress;

    /**
     * 收件人邮编
     */
    private String receiverZip;

    /**
     * 收件人手机
     */
    private String receiverMobile;

    /**
     * 收件人电话
     */
    private String receiverPhone;

    /**
     * 收件人邮箱
     */
    private String receiverEmail;

    /**
     * 支付订单号
     */
    private String paymentOrderNo;

    /**
     * 支付公司流水号,支付宝/微信的支付流水号
     */
    private String paymentTransactionNo;
    //支付单流水号
    private String payTransactionId;

    /**
     * 支付类型 CmbPay:招行一网通, Alipay:支付宝, Weixin:微信, ApplePay:ApplePay
     */
    private String payType;

    /**
     * 是否国内段已发货:1-true、0-false
     */
    private Boolean domesticDelivered;

    /**
     * 是否预订,1-是，0-否
     */
    private Boolean preSale;

    /**
     * 发送状态
     */
    private OrderStatusEnum sendStatus;

    /**
     * 发送系统
     */
    private ForwardSystemEnum sendSystem;

    /**
     * 发送原因
     */
    private String sendReason;

    /**
     * 发送跨境宝接收状态: 0：不处理，1：成功，2：失败
     */
    private String sendKjbStatus;

    /**
     * 回传商品价格
     */
    private String goodsValuePrice;

    /**
     * 回传总价
     */
    private String taxTotalPrice;

    /**
     * 电商平台编码
     */
    private String ebpCode;

    /**
     * 电商企业编码
     */
    private String ebcCode;

    private String ebcName;

    /**
     * 业务类型
     */
    private BusiModeEnum busiType;

    /**
     * 关区代码
     */
    private String customerCode;

    /**
     * 店铺编码
     */
    private String storeId;

    /**
     * 优惠金额
     */
    private BigDecimal yhPrice;

    //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }


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

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public Long getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Long orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public BigDecimal getpCouponDiscount() {
        return pCouponDiscount;
    }

    public void setpCouponDiscount(BigDecimal pCouponDiscount) {
        this.pCouponDiscount = pCouponDiscount;
    }

    public BigDecimal getmCouponDiscount() {
        return mCouponDiscount;
    }

    public void setmCouponDiscount(BigDecimal mCouponDiscount) {
        this.mCouponDiscount = mCouponDiscount;
    }

    public BigDecimal getpPromotionDiscount() {
        return pPromotionDiscount;
    }

    public void setpPromotionDiscount(BigDecimal pPromotionDiscount) {
        this.pPromotionDiscount = pPromotionDiscount;
    }

    public BigDecimal getmPromotionDiscount() {
        return mPromotionDiscount;
    }

    public void setmPromotionDiscount(BigDecimal mPromotionDiscount) {
        this.mPromotionDiscount = mPromotionDiscount;
    }

    public BigDecimal getmAdjustDiscount() {
        return mAdjustDiscount;
    }

    public void setmAdjustDiscount(BigDecimal mAdjustDiscount) {
        this.mAdjustDiscount = mAdjustDiscount;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public Date getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Date shippingTime) {
        this.shippingTime = shippingTime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getSellerMemo() {
        return sellerMemo;
    }

    public void setSellerMemo(String sellerMemo) {
        this.sellerMemo = sellerMemo;
    }

    public String getBuyerRemark() {
        return buyerRemark;
    }

    public void setBuyerRemark(String buyerRemark) {
        this.buyerRemark = buyerRemark;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getPaymentOrderNo() {
        return paymentOrderNo;
    }

    public void setPaymentOrderNo(String paymentOrderNo) {
        this.paymentOrderNo = paymentOrderNo;
    }

    public String getPaymentTransactionNo() {
        return paymentTransactionNo;
    }

    public void setPaymentTransactionNo(String paymentTransactionNo) {
        this.paymentTransactionNo = paymentTransactionNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Boolean getDomesticDelivered() {
        return domesticDelivered;
    }

    public void setDomesticDelivered(Boolean domesticDelivered) {
        this.domesticDelivered = domesticDelivered;
    }

    public Boolean getPreSale() {
        return preSale;
    }

    public void setPreSale(Boolean preSale) {
        this.preSale = preSale;
    }

    public OrderStatusEnum getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(OrderStatusEnum sendStatus) {
        this.sendStatus = sendStatus;
    }

    public ForwardSystemEnum getSendSystem() {
        return sendSystem;
    }

    public void setSendSystem(ForwardSystemEnum sendSystem) {
        this.sendSystem = sendSystem;
    }

    public String getSendReason() {
        return sendReason;
    }

    public void setSendReason(String sendReason) {
        this.sendReason = sendReason;
    }

    public String getSendKjbStatus() {
        return sendKjbStatus;
    }

    public void setSendKjbStatus(String sendKjbStatus) {
        this.sendKjbStatus = sendKjbStatus;
    }

    public String getGoodsValuePrice() {
        return goodsValuePrice;
    }

    public void setGoodsValuePrice(String goodsValuePrice) {
        this.goodsValuePrice = goodsValuePrice;
    }

    public String getTaxTotalPrice() {
        return taxTotalPrice;
    }

    public void setTaxTotalPrice(String taxTotalPrice) {
        this.taxTotalPrice = taxTotalPrice;
    }

    public String getEbpCode() {
        return ebpCode;
    }

    public void setEbpCode(String ebpCode) {
        this.ebpCode = ebpCode;
    }

    public String getEbcCode() {
        return ebcCode;
    }

    public void setEbcCode(String ebcCode) {
        this.ebcCode = ebcCode;
    }

    public String getEbcName() {
       return ebcName;
    }

    public void setEbcName(String ebcName) {
        this.ebcName = ebcName;
    }

    public BusiModeEnum getBusiType() {
        return busiType;
    }

    public void setBusiType(BusiModeEnum busiType) {
        this.busiType = busiType;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getYhPrice() {
        return BigDecimal.ZERO.add(getpCouponDiscount()!=null?getpCouponDiscount():BigDecimal.ZERO)
                .add(getmCouponDiscount()!=null?getmCouponDiscount():BigDecimal.ZERO)
                .add(getmPromotionDiscount()!=null?getmPromotionDiscount():BigDecimal.ZERO)
                .add(getpPromotionDiscount()!=null?getpPromotionDiscount():BigDecimal.ZERO)
                .add(getmAdjustDiscount()!=null?getmAdjustDiscount():BigDecimal.ZERO)
                .add(getRebateAmount()!=null?getRebateAmount():BigDecimal.ZERO);
    }

    public void setYhPrice(BigDecimal yhPrice) {
        this.yhPrice = yhPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public BigDecimal getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(BigDecimal rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayTransactionId() {
        return payTransactionId;
    }

    public void setPayTransactionId(String payTransactionId) {
        this.payTransactionId = payTransactionId;
    }
}
