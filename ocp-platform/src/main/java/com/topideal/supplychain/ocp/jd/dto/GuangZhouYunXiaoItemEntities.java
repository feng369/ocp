package com.topideal.supplychain.ocp.jd.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName GuangZhouYunXiaoItemEntities
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/3/4 16:41
 * @Version 1.0
 **/
public class GuangZhouYunXiaoItemEntities implements Serializable {
    private String gnum;
    private String itemNo;
    private String itemDescribe;
    private String itemName;
    private String country;
    private String hscode;
    private String customRecord;
    private String qiRecord;
    private String spe;
    private String netWeight;
    private String weight;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private BigDecimal quantity;
    private String qty1;
    private String qty2;
    private String unit1;
    private String unit2;
    private String barCode;
    private String batchNumbers;
    private String note;

    public String getGnum() {
        return gnum;
    }

    public void setGnum(String gnum) {
        this.gnum = gnum;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemDescribe() {
        return itemDescribe;
    }

    public void setItemDescribe(String itemDescribe) {
        this.itemDescribe = itemDescribe;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHscode() {
        return hscode;
    }

    public void setHscode(String hscode) {
        this.hscode = hscode;
    }

    public String getCustomRecord() {
        return customRecord;
    }

    public void setCustomRecord(String customRecord) {
        this.customRecord = customRecord;
    }

    public String getQiRecord() {
        return qiRecord;
    }

    public void setQiRecord(String qiRecord) {
        this.qiRecord = qiRecord;
    }

    public String getSpe() {
        return spe;
    }

    public void setSpe(String spe) {
        this.spe = spe;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getQty1() {
        return qty1;
    }

    public void setQty1(String qty1) {
        this.qty1 = qty1;
    }

    public String getQty2() {
        return qty2;
    }

    public void setQty2(String qty2) {
        this.qty2 = qty2;
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBatchNumbers() {
        return batchNumbers;
    }

    public void setBatchNumbers(String batchNumbers) {
        this.batchNumbers = batchNumbers;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
