package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 奇门订单明细
 * @author: syq
 * @create: 2019-12-12 16:23
 **/
public class OrderQmItem extends BaseEntity {

    private Long orderId;//订单id

    private String itemNo;//明细行号

    private String sourceOrderCode;//交易平台订单编码

    private String subSourceOrderCode;//交易平台子订单编码

    private String payNo;//支付平台交易号

    private String ownerCode;//货主编码

    private String goodsCode;//商品编码

    private String wmsGoodsCode;//仓储系统商品编码

    private String stockType;//库存类型

    private String goodsName;//商品名称

    private String payGoodsCode;//交易平台商品编码

    private Integer planQty;//应发商品数量

    private BigDecimal retailPrice;//零售价

    private BigDecimal actualPrice;//实际成交价

    private BigDecimal discountAmount;//单间商品折扣金额

    private String batchCode;//批次编码

    private String produceCode;//生产批号

    private String productDate;//生产日期YYYY-MM-DD

    private String expireDate;//过期日期YYYY-MM-DD

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getSourceOrderCode() {
        return sourceOrderCode;
    }

    public void setSourceOrderCode(String sourceOrderCode) {
        this.sourceOrderCode = sourceOrderCode;
    }

    public String getSubSourceOrderCode() {
        return subSourceOrderCode;
    }

    public void setSubSourceOrderCode(String subSourceOrderCode) {
        this.subSourceOrderCode = subSourceOrderCode;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getWmsGoodsCode() {
        return wmsGoodsCode;
    }

    public void setWmsGoodsCode(String wmsGoodsCode) {
        this.wmsGoodsCode = wmsGoodsCode;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPayGoodsCode() {
        return payGoodsCode;
    }

    public void setPayGoodsCode(String payGoodsCode) {
        this.payGoodsCode = payGoodsCode;
    }

    public Integer getPlanQty() {
        return planQty;
    }

    public void setPlanQty(Integer planQty) {
        this.planQty = planQty;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getProduceCode() {
        return produceCode;
    }

    public void setProduceCode(String produceCode) {
        this.produceCode = produceCode;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}
