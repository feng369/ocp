package com.topideal.supplychain.ocp.gs.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.topideal.supplychain.deserializer.JsonLongToDateDeserializer;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.dto</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2019-12-17 10:32</p>
 *
 * @version 1.0
 */
public class OrderGsReqDto implements Serializable {


    /**
     * 创建时间(环球捕手)
     */
    @JsonDeserialize(using = JsonLongToDateDeserializer.class)
    @JsonFormat(shape = Shape.NUMBER)
    private Date createTime;

    /**
     * 店铺订单id
     */
    private String shopOrderId;
    /**
     * 履约单号
     */
    private String consignCode;
    /**
     * 发货单状态 100：待发货 ，200：已发货，300：无需发货
     */
    private Integer status;
    /**
     * 冻结状态；0：未冻结，1：已冻结，2：已解冻，3：永久冻结
     */
    private Integer freezeStatus;
    /**
     * 支付时间
     */
    @JsonDeserialize(using = JsonLongToDateDeserializer.class)
    @JsonFormat(shape = Shape.NUMBER)
    private Date payTime;
    /**
     * 创建时间(环球捕手)
     */
    @JsonDeserialize(using = JsonLongToDateDeserializer.class)
    @JsonFormat(shape = Shape.NUMBER)
    private Date orderCreateTime;
    /**
     * 支付渠道
     */
    private String payChannel;
    /**
     * 支付交易号
     */
    private String tradeNo;
    /**
     * 发货单总运费
     */
    private BigDecimal bondedAreaShipExpense;
    /**
     * 发货单总货款（不含税）
     */
    private BigDecimal bondedAreaGoodsPrice;
    /**
     * 发货单总非现金抵扣金额
     */
    private BigDecimal bondedAreaNonCashDeduct;
    /**
     * 发货单总税款金额
     */
    private BigDecimal bondedAreaTax;
    /**
     * 发货单总实付金额
     */
    private BigDecimal bondedAreaPayCash;

    private String buyerComment;

    private String sellerComment;
    /**
     * 收货信息
     */
    private ShipInfo shipAddress;

    /**
     * 物流信息(只取第一条)
     */
    private List<LogisticsInfo> packageList;
    /**
     * 商品信息
     */
    private List<OrderSku> orderSkuList;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getShopOrderId() {
        return shopOrderId;
    }

    public void setShopOrderId(String shopOrderId) {
        this.shopOrderId = shopOrderId;
    }

    public String getConsignCode() {
        return consignCode;
    }

    public void setConsignCode(String consignCode) {
        this.consignCode = consignCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getFreezeStatus() {
        return freezeStatus;
    }

    public void setFreezeStatus(Integer freezeStatus) {
        this.freezeStatus = freezeStatus;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public BigDecimal getBondedAreaShipExpense() {
        return bondedAreaShipExpense;
    }

    public void setBondedAreaShipExpense(BigDecimal bondedAreaShipExpense) {
        this.bondedAreaShipExpense = bondedAreaShipExpense;
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

    public BigDecimal getBondedAreaPayCash() {
        return bondedAreaPayCash;
    }

    public void setBondedAreaPayCash(BigDecimal bondedAreaPayCash) {
        this.bondedAreaPayCash = bondedAreaPayCash;
    }

    public String getBuyerComment() {
        return buyerComment;
    }

    public void setBuyerComment(String buyerComment) {
        this.buyerComment = buyerComment;
    }

    public String getSellerComment() {
        return sellerComment;
    }

    public void setSellerComment(String sellerComment) {
        this.sellerComment = sellerComment;
    }

    public ShipInfo getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(ShipInfo shipAddress) {
        this.shipAddress = shipAddress;
    }

    public List<LogisticsInfo> getPackageList() {
        return packageList;
    }

    public void setPackageList(List<LogisticsInfo> packageList) {
        this.packageList = packageList;
    }

    public List<OrderSku> getOrderSkuList() {
        return orderSkuList;
    }

    public void setOrderSkuList(
            List<OrderSku> orderSkuList) {
        this.orderSkuList = orderSkuList;
    }
}
