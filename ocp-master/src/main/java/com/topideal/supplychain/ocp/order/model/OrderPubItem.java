package com.topideal.supplychain.ocp.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.topideal.supplychain.enumeration.YesOrNoEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

public class OrderPubItem {
    /**
     * 
     */
    private Long id;

    /**
     * 订单记录id
     */
    private Long orderId;

    /**
     * 交易订单号
     */
    private String tradeNo;

    /**
     * 序号
     */
    private String itemNo;

    /**
     * 商品货号
     */
    private String goodsCode;

    /**
     * 是否商品备案 1是，2否
     */
    private Integer isPresente;

    private String isPretence;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 行邮税号
     */
    private String itemCategory;

    /**
     * hs编码
     */
    private String hsCode;

    /**
     * 海关商品备案号
     */
    private String custNo;

    /**
     * 国检商品备案号
     */
    private String ciqNo;

    /**
     * 商品数量
     */
    private Integer qty;

    /**
     * 单价
     */
    private BigDecimal unitPrice;

    /**
     * 单价币制
     */
    private String currency;

    /**
     * 法定第一数量
     */
    private BigDecimal qty1;

    /**
     * 法定第一单位
     */
    private String unit1;

    /**
     * 法定第二数量
     */
    private BigDecimal qty2;

    /**
     * 法定第二单位
     */
    private String unit2;

    /**
     * 品牌名称
     */
    private String brand;

    /**
     * 规格型号
     */
    private String spec;

    /**
     * 计量单位
     */
    private String unit;

    /**
     * 商品毛重
     */
    private BigDecimal grossWeight;

    /**
     * 商品净重
     */
    private BigDecimal netWeight;

    /**
     * 商品税金
     */
    private String tax;

    /**
     * 原产国
     */
    private String oriCountry;

    /**
     * 国检原产地
     */
    private String oriArea;

    /**
     * 条形码
     */
    private String barCode;

    /**
     * 记录保存时间
     */
    private Date createTime;

    /**
     * 商品备注
     */
    private String remark;

    private String itemEnname;//申报英文名称

    private String prodUse;

    private String prodMaterial;

    /**
     * 是否删除
     */
    private Integer isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo == null ? null : itemNo.trim();
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public Integer getIsPresente() {
        return isPresente;
    }

    public void setIsPresente(Integer isPresente) {
        this.isPresente = isPresente;
    }

    public String getIsPretence() {
        if (isPresente != null) {
            return isPresente == 1 ? "是" : "否";
        }
        return "";
    }

    public void setIsPretence(String isPretence) {
        this.isPretence = isPretence;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory == null ? null : itemCategory.trim();
    }

    public String getHsCode() {
        return hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode == null ? null : hsCode.trim();
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo == null ? null : custNo.trim();
    }

    public String getCiqNo() {
        return ciqNo;
    }

    public void setCiqNo(String ciqNo) {
        this.ciqNo = ciqNo == null ? null : ciqNo.trim();
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public BigDecimal getQty1() {
        return qty1;
    }

    public void setQty1(BigDecimal qty1) {
        this.qty1 = qty1;
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1 == null ? null : unit1.trim();
    }

    public BigDecimal getQty2() {
        return qty2;
    }

    public void setQty2(BigDecimal qty2) {
        this.qty2 = qty2;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2 == null ? null : unit2.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax == null ? null : tax.trim();
    }

    public String getOriCountry() {
        return oriCountry;
    }

    public void setOriCountry(String oriCountry) {
        this.oriCountry = oriCountry == null ? null : oriCountry.trim();
    }

    public String getOriArea() {
        return oriArea;
    }

    public void setOriArea(String oriArea) {
        this.oriArea = oriArea == null ? null : oriArea.trim();
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getItemEnname() {
        return itemEnname;
    }

    public void setItemEnname(String itemEnname) {
        this.itemEnname = itemEnname;
    }

    public String getProdUse() {
        return prodUse;
    }

    public void setProdUse(String prodUse) {
        this.prodUse = prodUse;
    }

    public String getProdMaterial() {
        return prodMaterial;
    }

    public void setProdMaterial(String prodMaterial) {
        this.prodMaterial = prodMaterial;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}