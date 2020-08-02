package com.topideal.supplychain.ocp.fx.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 标题：大订单接单请求商品DTO
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.big.dto
 * 作者：songping
 * 创建日期：2019/12/26 17:26
 *
 * @version 1.0
 */
public class FxGoodsDto implements Serializable {

    private static DateFormat df1	= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateFormat	df2	= new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 序号
     */
    private Integer				gnum;
    /**
     * 商品货号
     */
    private String				goodId;
    /**
     * 条形码
     */
    private String				barCode;
    /**
     * 数量
     */
    private Integer				amount;
    /**
     * 售价（实际售价）
     */
    private BigDecimal			price;
    /**
     * 商品售价（展示售价）
     */
    private BigDecimal			goodPrice;
    /**
     * 商品名称
     */
    private String				copGName;
    /**
     * 海关编码
     */
    private String				hsCode;
    /**
     * 商品总价
     */
    private BigDecimal			decTotal;
    /**
     * 备注
     */
    @JsonAlias({"notes","nots"})
    private String				notes;
    /**
     * 海关商品备案号
     */
    private String				custGoodsNo;
    /**
     * 国检商品备案号
     */
    private String				ciqGoodsNo;
    /**
     * 商品批次号
     */
    private String				batchNo;
    /**
     * 原厂国
     */
    private String				assemCountry;
    /**
     * 国检原产国
     */
    private String				ciqAssemCountry;
    /**
     * 法定计量单位
     */
    private String				qtyUnit;
    /**
     * 商品规格型号
     */
    private String				spec;
    /**
     * 指定库存策略号
     */
    private String				storeStrategyId;
    /**
     * 生产日期
     */
    private String				productionTime;
    /**
     * 到期日期
     */
    private String				expDate;
    /**
     * 货主
     */
    private String				ownerCode;
    /**
     * 虚拟货主
     */
    private String				virtualOwner;
    /**
     * 行邮税号
     */
    private String				codeTs;
    /**
     * 品牌
     */
    private String				brand;
    /**
     * 包装类型
     */
    private String				packageType;
    /**
     * 第一法定单位数量
     */
    private BigDecimal			qty1;
    /**
     * 商品第一数量
     */
    private String				gQty1;
    /**
     * 第一法定单位
     */
    private String				unit1;
    /**
     * 第二法定单位数量
     */
    private BigDecimal			qty2;
    /**
     * 商品第二数量
     */
    private String				gQty2;
    /**
     * 第二法定单位
     */
    private String				unit2;
    /**
     * 毛重
     */
    private BigDecimal			grossWeight;
    /**
     * 币种
     */
    private String				currency;
    /**
     * 净重
     */
    private BigDecimal          netWeight;
    /**
     * 是否商品备案
     */
    private String				isGoodsRecord;

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

    public String getHsCode() {
        return hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public BigDecimal getDecTotal() {
        return decTotal;
    }

    public void setDecTotal(BigDecimal decTotal) {
        this.decTotal = decTotal;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public String getProductionTime() {
        return productionTime;
    }

    @JsonIgnore
    public Date getProductionTimeFmt()
    {
        if (productionTime != null && productionTime.trim().length() > 0)
        {
            try
            {
                return productionTime.trim().length() == 10 ? df2.parse(productionTime) : df1.parse(productionTime);
            } catch (ParseException e)
            {
                throw new RuntimeException("请求参数productionTime格式错误！");
            }
        }
        return null;
    }

    public void setProductionTime(String productionTime) {
        this.productionTime = productionTime;
    }

    public String getExpDate() {
        return expDate;
    }

    @JsonIgnore
    public Date getExpDateFmt()
    {
        if (expDate != null && expDate.trim().length() > 0)
        {
            try
            {
                return expDate.trim().length() == 10 ? df2.parse(expDate) : df1.parse(expDate);
            } catch (ParseException e)
            {
                throw new RuntimeException("请求参数expDate格式错误！");
            }
        }
        return null;
    }

    public void setExpDate(String expDate) {
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

    public String getCodeTs() {
        return codeTs;
    }

    public void setCodeTs(String codeTs) {
        this.codeTs = codeTs;
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

    public String getgQty1() {
        return gQty1;
    }

    public void setgQty1(String gQty1) {
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

    public String getgQty2() {
        return gQty2;
    }

    public void setgQty2(String gQty2) {
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
    }

    public String getIsGoodsRecord() {
        return isGoodsRecord;
    }

    public void setIsGoodsRecord(String isGoodsRecord) {
        this.isGoodsRecord = isGoodsRecord;
    }
}
