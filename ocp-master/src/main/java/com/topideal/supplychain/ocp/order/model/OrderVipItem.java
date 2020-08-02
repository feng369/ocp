package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrderVipItem extends BaseEntity{


    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 序号
     */
    private Integer gnum;

    /**
     * 账册备案料号
     */
    private String itemRecordNo;

    /**
     * 企业商品货号
     */
    private String itemNo;

    /**
     * 商品名称
     */
    private String gname;

    /**
     * 海关备案商品名称
     */
    private String goodRecordName;

    /**
     * 商品编码
     */
    private String gcode;

    /**
     * 商品规格型号
     */
    private String gmodel;

    /**
     * 商品条码
     */
    private String barCode;

    /**
     * 原产国（地区）
     */
    private String originCountry;

    /**
     * 币制
     */
    private String currency;

    /**
     * 计量单位
     */
    private String unit;

    /**
     * 数量
     */
    private BigDecimal qty;

    /**
     * 法定计量单位
     */
    private String unit1;

    /**
     * 法定数量
     */
    private BigDecimal qty1;

    /**
     * 第二计量单位
     */
    private String unit2;

    /**
     * 第二数量
     */
    private BigDecimal qty2;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 检验检疫商品备案号
     */
    private String goodsRegNo;

    /**
     * 商品行邮税号
     */
    private String parcelTaxNo;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 商品净重（千克）
     */
    private BigDecimal goodsNetWeight;

    /**
     * 商品毛重（千克）
     */
    private BigDecimal goodsGrossWeight;

    /**
     * 备注
     */
    private String note;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getGnum() {
        return gnum;
    }

    public void setGnum(Integer gnum) {
        this.gnum = gnum;
    }

    public String getItemRecordNo() {
        return itemRecordNo;
    }

    public void setItemRecordNo(String itemRecordNo) {
        this.itemRecordNo = itemRecordNo == null ? null : itemRecordNo.trim();
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo == null ? null : itemNo.trim();
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname == null ? null : gname.trim();
    }

    public String getGoodRecordName() {
        return goodRecordName;
    }

    public void setGoodRecordName(String goodRecordName) {
        this.goodRecordName = goodRecordName == null ? null : goodRecordName.trim();
    }

    public String getGcode() {
        return gcode;
    }

    public void setGcode(String gcode) {
        this.gcode = gcode == null ? null : gcode.trim();
    }

    public String getGmodel() {
        return gmodel;
    }

    public void setGmodel(String gmodel) {
        this.gmodel = gmodel == null ? null : gmodel.trim();
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry == null ? null : originCountry.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1 == null ? null : unit1.trim();
    }

    public BigDecimal getQty1() {
        return qty1;
    }

    public void setQty1(BigDecimal qty1) {
        this.qty1 = qty1;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2 == null ? null : unit2.trim();
    }

    public BigDecimal getQty2() {
        return qty2;
    }

    public void setQty2(BigDecimal qty2) {
        this.qty2 = qty2;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getGoodsRegNo() {
        return goodsRegNo;
    }

    public void setGoodsRegNo(String goodsRegNo) {
        this.goodsRegNo = goodsRegNo == null ? null : goodsRegNo.trim();
    }

    public String getParcelTaxNo() {
        return parcelTaxNo;
    }

    public void setParcelTaxNo(String parcelTaxNo) {
        this.parcelTaxNo = parcelTaxNo == null ? null : parcelTaxNo.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public BigDecimal getGoodsNetWeight() {
        return goodsNetWeight;
    }

    public void setGoodsNetWeight(BigDecimal goodsNetWeight) {
        this.goodsNetWeight = goodsNetWeight;
    }

    public BigDecimal getGoodsGrossWeight() {
        return goodsGrossWeight;
    }

    public void setGoodsGrossWeight(BigDecimal goodsGrossWeight) {
        this.goodsGrossWeight = goodsGrossWeight;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}