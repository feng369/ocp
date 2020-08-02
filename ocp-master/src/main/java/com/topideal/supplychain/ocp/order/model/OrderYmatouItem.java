package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;

import java.math.BigDecimal;

/**
 * @author wanzhaozhang
 * @date 2019/12/3
 * @description
 **/
public class OrderYmatouItem extends BaseEntity{
    /**
     * 序号
     */
    private Long gnum;

    /**
     * 主订单id
     */
    private Long msgOrderId;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 子订单编号
     */
    private String orderItemId;

    /**
     * 退货退款单ID
     */
    private String refundId;

    /**
     * 退货退款状态 null: 无退款,-1:退款审核拒绝, 0:退款审核中, 1:退款审核通过
     */
    private Long refundStatus;

    /**
     * 退货数量
     */
    private Long refundNum;

    /**
     * SkuId
     */
    private String skuId;

    /**
     * 买手商品编码
     */
    private String outerSkuId;

    /**
     *
     */
    private String productId;

    /**
     *
     */
    private String productTitle;

    /**
     * SKU的属性值
     */
    private String skuPropertiesName;

    /**
     * 商品物流方式
     */
    private Long deliveryType;

    /**
     * 拼邮类型
     */
    private Long deliverySubType;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品数量
     */
    private Long num;

    /**
     * 支付金额
     */
    private BigDecimal payment;

    /**
     * 运费分摊金额
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
     * 买手调整分摊金额
     */
    private BigDecimal mAdjustDiscount;

    /**
     * 订单类型，0-原始数据，1-新数据
     */
    private String sendKjbFlag;

    /**
     * 单位
     */
    private String goodsUnit;

    /**
     * 回传商品单价
     */
    private String rePrice;

    /**
     * 回传总价
     */
    private String reTotalPrice;

    /**
     * 安利返点金额
     */
    private BigDecimal rebateAmount;


    public Long getGnum() {
        return gnum;
    }

    public void setGnum(Long gnum) {
        this.gnum = gnum;
    }

    public Long getMsgOrderId() {
        return msgOrderId;
    }

    public void setMsgOrderId(Long msgOrderId) {
        this.msgOrderId = msgOrderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public Long getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Long refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Long getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Long refundNum) {
        this.refundNum = refundNum;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getOuterSkuId() {
        return outerSkuId;
    }

    public void setOuterSkuId(String outerSkuId) {
        this.outerSkuId = outerSkuId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getSkuPropertiesName() {
        return skuPropertiesName;
    }

    public void setSkuPropertiesName(String skuPropertiesName) {
        this.skuPropertiesName = skuPropertiesName;
    }

    public Long getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(Long deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Long getDeliverySubType() {
        return deliverySubType;
    }

    public void setDeliverySubType(Long deliverySubType) {
        this.deliverySubType = deliverySubType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
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

    public String getSendKjbFlag() {
        return sendKjbFlag;
    }

    public void setSendKjbFlag(String sendKjbFlag) {
        this.sendKjbFlag = sendKjbFlag;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getRePrice() {
        return rePrice;
    }

    public void setRePrice(String rePrice) {
        this.rePrice = rePrice;
    }

    public String getReTotalPrice() {
        return reTotalPrice;
    }

    public void setReTotalPrice(String reTotalPrice) {
        this.reTotalPrice = reTotalPrice;
    }

    public BigDecimal getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(BigDecimal rebateAmount) {
        this.rebateAmount = rebateAmount;
    }
}
