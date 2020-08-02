package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.GsIsSendKjbEnum;

import java.math.BigDecimal;

/**
 * <p>标题: 环球捕手订单实体</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.model</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2019-12-16 17:48</p>
 *
 * @version 1.0
 */
public class OrderGsItem extends BaseEntity {
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 内部订单号
     */
    private String orderCode;
    /**
     * 商品名称
     */
    private String skuTitle;
    /**
     * 发货编码
     */
    private String deliverCode;
    /**
     * 需要发货数量（若全部退款，可能为0）
     */
    private BigDecimal quantity;
    /**
     * 售价, 商品原价（单位：分）
     */
    private BigDecimal price;
    /**
     * 运费：商品级订单分摊到的运费值（单位：分）
     */
    private BigDecimal shipExpenseShare;
    /**
     * 店铺优惠金额：商品级订单店铺优惠总金额（打折、满减、优惠券）（单位：分）
     */
    private BigDecimal shopPromotionAmount;
    /**
     * 平台优惠金额：商品级订单平台优惠总金额（G币、优惠券、活动类优惠）（单位：分）
     */
    private BigDecimal platformPromotionAmount;
    /**
     * 支付金额（单位：分）
     */
    private BigDecimal payPrice;
    /**
     * 实收金额 （单位：分）
     */
    private BigDecimal actualIncomePrice;
    /**
     * 商品货款（不含税）
     */
    private BigDecimal bondedAreaGoodsPrice;
    /**
     * 商品非现金抵扣金额
     */
    private BigDecimal bondedAreaNonCashDeduct;
    /**
     * 商品税款
     */
    private BigDecimal bondedAreaTax;
    /**
     * 拆单标识
     */
    private GsIsSendKjbEnum sendKjbFlag;
    /**
     * 总价
     */
    private BigDecimal totalPrice;

    //TODO gs单方新增字段
    private BigDecimal purchaseCount;
    private BigDecimal acrossPromotionAmount;
    private String skuProperty;
    private BigDecimal realCashAmount;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public GsIsSendKjbEnum getSendKjbFlag() {
        return sendKjbFlag;
    }

    public void setSendKjbFlag(GsIsSendKjbEnum sendKjbFlag) {
        this.sendKjbFlag = sendKjbFlag;
    }

    public String getSkuTitle() {
        return skuTitle;
    }

    public void setSkuTitle(String skuTitle) {
        this.skuTitle = skuTitle;
    }

    public String getDeliverCode() {
        return deliverCode;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getShipExpenseShare() {
        return shipExpenseShare;
    }

    public void setShipExpenseShare(BigDecimal shipExpenseShare) {
        this.shipExpenseShare = shipExpenseShare;
    }

    public BigDecimal getShopPromotionAmount() {
        return shopPromotionAmount;
    }

    public void setShopPromotionAmount(BigDecimal shopPromotionAmount) {
        this.shopPromotionAmount = shopPromotionAmount;
    }

    public BigDecimal getPlatformPromotionAmount() {
        return platformPromotionAmount;
    }

    public void setPlatformPromotionAmount(BigDecimal platformPromotionAmount) {
        this.platformPromotionAmount = platformPromotionAmount;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public BigDecimal getActualIncomePrice() {
        return actualIncomePrice;
    }

    public void setActualIncomePrice(BigDecimal actualIncomePrice) {
        this.actualIncomePrice = actualIncomePrice;
    }

    public BigDecimal getBondedAreaGoodsPrice() {
        return bondedAreaGoodsPrice;
    }

    public void setBondedAreaGoodsPrice(BigDecimal bondedAreaGoodsPrice) {
        this.bondedAreaGoodsPrice = bondedAreaGoodsPrice;
    }

    public BigDecimal getBondedAreaNonCashDeduct() {
        return bondedAreaNonCashDeduct;
    }

    public void setBondedAreaNonCashDeduct(BigDecimal bondedAreaNonCashDeduct) {
        this.bondedAreaNonCashDeduct = bondedAreaNonCashDeduct;
    }

    public BigDecimal getBondedAreaTax() {
        return bondedAreaTax;
    }

    public void setBondedAreaTax(BigDecimal bondedAreaTax) {
        this.bondedAreaTax = bondedAreaTax;
    }

    public BigDecimal getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(BigDecimal purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public BigDecimal getAcrossPromotionAmount() {
        return acrossPromotionAmount;
    }

    public void setAcrossPromotionAmount(BigDecimal acrossPromotionAmount) {
        this.acrossPromotionAmount = acrossPromotionAmount;
    }

    public String getSkuProperty() {
        return skuProperty;
    }

    public void setSkuProperty(String skuProperty) {
        this.skuProperty = skuProperty;
    }

    public BigDecimal getRealCashAmount() {
        return realCashAmount;
    }

    public void setRealCashAmount(BigDecimal realCashAmount) {
        this.realCashAmount = realCashAmount;
    }
}
