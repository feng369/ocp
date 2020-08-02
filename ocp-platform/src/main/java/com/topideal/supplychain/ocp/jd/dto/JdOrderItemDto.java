package com.topideal.supplychain.ocp.jd.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName JdOrderItem
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/2/26 16:11
 * @Version 1.0
 **/
public class JdOrderItemDto {

    private String unit1;
    private String unit2;
    private String amount1;
    private String amount2;
    @JsonAlias({"GIndex","goIndex"})
    private String gIndex;
    @JsonAlias({"Qty","qty"})
    private String qty;
    @JsonAlias({"DecPrice","decPrice"})
    private String decPrice;
    @JsonAlias({"DecTotal","decTotalPrice"})
    private String decTotal;
    @JsonAlias({"Curr","curr"})
    private String curr;
    @JsonAlias({"CopGNo","sellerRecord"})
    private String copGNo;
    @JsonAlias({"V_GOODS_REGIST_NO","skuCustomerCode"})
    private String vGoodsRegistNo;
    @JsonAlias({"V_UNIT","voUnit"})
    private String vUnit;
    @JsonAlias({"G_NO","goNo"})
    private String gNo;
    @JsonAlias({"V_GOODS_NAME","goodsName"})
    private String vGoodsName;
    @JsonAlias({"V_SP_MOD","spe"})
    private String vSpMod;
    @JsonAlias({"Country","country"})
    private String country;
    @JsonAlias({"NetWeight","netWeight"})
    private String netWeight;
    @JsonAlias({"SkuId","skuId"})
    private String skuId;

    private String brand;

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

    public String getAmount1() {
        return amount1;
    }

    public void setAmount1(String amount1) {
        this.amount1 = amount1;
    }

    public String getAmount2() {
        return amount2;
    }

    public void setAmount2(String amount2) {
        this.amount2 = amount2;
    }

    public String getgIndex() {
        return gIndex;
    }

    public void setgIndex(String gIndex) {
        this.gIndex = gIndex;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getDecPrice() {
        return decPrice;
    }

    public void setDecPrice(String decPrice) {
        this.decPrice = decPrice;
    }

    public String getDecTotal() {
        return decTotal;
    }

    public void setDecTotal(String decTotal) {
        this.decTotal = decTotal;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    public String getCopGNo() {
        return copGNo;
    }

    public void setCopGNo(String copGNo) {
        this.copGNo = copGNo;
    }

    public String getvGoodsRegistNo() {
        return vGoodsRegistNo;
    }

    public void setvGoodsRegistNo(String vGoodsRegistNo) {
        this.vGoodsRegistNo = vGoodsRegistNo;
    }

    public String getvUnit() {
        return vUnit;
    }

    public void setvUnit(String vUnit) {
        this.vUnit = vUnit;
    }

    public String getgNo() {
        return gNo;
    }

    public void setgNo(String gNo) {
        this.gNo = gNo;
    }

    public String getvGoodsName() {
        return vGoodsName;
    }

    public void setvGoodsName(String vGoodsName) {
        this.vGoodsName = vGoodsName;
    }

    public String getvSpMod() {
        return vSpMod;
    }

    public void setvSpMod(String vSpMod) {
        this.vSpMod = vSpMod;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
