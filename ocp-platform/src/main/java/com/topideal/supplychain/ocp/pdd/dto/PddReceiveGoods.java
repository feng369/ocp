package com.topideal.supplychain.ocp.pdd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author klw
 * @date 2019/12/17 15:01
 * @description:
 */
public class PddReceiveGoods {
    //序号
    private Integer gnum;
    //商品货号
    private String goodId;
    //条形码
    private String barCode;
    //数量
    private Integer amount;
    //售价（实际售价）
    private BigDecimal price;
    //商品售价（展示售价）
    private BigDecimal goodPrice;
    //商品名称
    private String copGName;
    //币种
    private String currency;
    //海关编码
    private String hsCode;
    //行邮税号
    private String codeTs;
    //商品总价
    private BigDecimal decTotal;
    //备注
    private String nots;
    //商品海关备案号
    private String custGoodsNo;
    //国检商品备案号
    private String ciqGoodsNo;
    //商品批次号
    private String batchNo;
    //原产国
    private String assemCountry;
    //国检原产地
    private String ciqAssemCountry;
    //法定计量单位
    private String qtyUnit;
    //商品规格型号
    private String spec;
    //指定库存策略号
    private String storeStrategyId;
    //生产日期
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date productionTime;
    //到期日期
    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date expDate;
    //货主
    private String ownerCode;
    //虚拟货主
    private String virtualOwner;
    //品牌
    private String brand;
    //包装类型
    private String packageType;
    //第一法定单位数量
    private BigDecimal qty1;
    //商品第一数量
    private BigDecimal gQty1;
    //第一法定单位
    private String unit1;
    //第二法定单位数量
    private BigDecimal qty2;
    //商品第二数量
    private BigDecimal gQty2;
    //第二法定单位
    private String unit2;
    //毛重（Kg）
    private BigDecimal grossWeight;
    //净重（Kg）
    private BigDecimal netWeight;
    //是否指定备案信息
    private Integer isGoodsRecord;

    public Integer getGnum() {
        return gnum;
    }

    public void setGnum(Integer gnum) {
        this.gnum = gnum;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(BigDecimal goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getCopGName() {
        return copGName;
    }

    public void setCopGName(String copGName) {
        this.copGName = copGName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getHsCode() {
        return hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public String getCodeTs() {
        return codeTs;
    }

    public void setCodeTs(String codeTs) {
        this.codeTs = codeTs;
    }

    public BigDecimal getDecTotal() {
        return decTotal;
    }

    public void setDecTotal(BigDecimal decTotal) {
        this.decTotal = decTotal;
    }

    public String getNots() {
        return nots;
    }

    public void setNots(String nots) {
        this.nots = nots;
    }

    public String getCustGoodsNo() {
        return custGoodsNo;
    }

    public void setCustGoodsNo(String custGoodsNo) {
        this.custGoodsNo = custGoodsNo;
    }

    public String getCiqGoodsNo() {
        return ciqGoodsNo;
    }

    public void setCiqGoodsNo(String ciqGoodsNo) {
        this.ciqGoodsNo = ciqGoodsNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getAssemCountry() {
        return assemCountry;
    }

    public void setAssemCountry(String assemCountry) {
        this.assemCountry = assemCountry;
    }

    public String getCiqAssemCountry() {
        return ciqAssemCountry;
    }

    public void setCiqAssemCountry(String ciqAssemCountry) {
        this.ciqAssemCountry = ciqAssemCountry;
    }

    public String getQtyUnit() {
        return qtyUnit;
    }

    public void setQtyUnit(String qtyUnit) {
        this.qtyUnit = qtyUnit;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getStoreStrategyId() {
        return storeStrategyId;
    }

    public void setStoreStrategyId(String storeStrategyId) {
        this.storeStrategyId = storeStrategyId;
    }

    public Date getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(Date productionTime) {
        this.productionTime = productionTime;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getVirtualOwner() {
        return virtualOwner;
    }

    public void setVirtualOwner(String virtualOwner) {
        this.virtualOwner = virtualOwner;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public BigDecimal getQty1() {
        return qty1;
    }

    public void setQty1(BigDecimal qty1) {
        this.qty1 = qty1;
    }

    public BigDecimal getgQty1() {
        return gQty1;
    }

    public void setgQty1(BigDecimal gQty1) {
        this.gQty1 = gQty1;
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public BigDecimal getQty2() {
        return qty2;
    }

    public void setQty2(BigDecimal qty2) {
        this.qty2 = qty2;
    }

    public BigDecimal getgQty2() {
        return gQty2;
    }

    public void setgQty2(BigDecimal gQty2) {
        this.gQty2 = gQty2;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2;
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

    public Integer getIsGoodsRecord() {
        return isGoodsRecord;
    }

    public void setIsGoodsRecord(Integer isGoodsRecord) {
        this.isGoodsRecord = isGoodsRecord;
    }
}
