package com.topideal.supplychain.ocp.order.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 标题：大订单明细 实体Bean
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.order.model
 * 作者：songping
 * 创建日期：2019/12/19 13:48
 *
 * @version 1.0
 */
public class OrderBigItem {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 主表ID
     */
    private Long mainId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 商品序号
     */
    private Integer gnum;

    /**
     * 商品货号
     */
    private String goodId;

    /**
     * 条形码
     */
    private String barCode;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 售价（实际售价）
     */
    private BigDecimal price;

    /**
     * 商品售价（展示售价）
     */
    private BigDecimal goodPrice;

    /**
     * 商品名称
     */
    private String copGName;

    /**
     * 海关编码
     */
    private String hsCode;

    /**
     * 商品总价
     */
    private BigDecimal decTotal;

    /**
     * 备注
     */
    private String nots;

    /**
     * 海关商品备案号
     */
    private String custGoodsNo;

    /**
     * 国检商品备案号
     */
    private String ciqGoodsNo;

    /**
     * 商品批次号
     */
    private String batchNo;

    /**
     * 原厂国
     */
    private String assemCountry;

    /**
     * 国检原产国
     */
    private String ciqAssemCountry;

    /**
     * 法定计量单位
     */
    private String qtyUnit;

    /**
     * 商品规格型号
     */
    private String spec;

    /**
     * 指定库存策略号
     */
    private String storeStrategyId;

    /**
     * 生产日期
     */
    private Date productionTime;

    /**
     * 到期日期
     */
    private Date expDate;

    /**
     * 货主
     */
    private String ownerCode;

    /**
     * 虚拟货主
     */
    private String virtualOwner;

    /**
     * 行邮税号
     */
    private String codeTs;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 包装类型
     */
    private String packageType;

    /**
     * 第一法定单位数量
     */
    private BigDecimal qty1;

    /**
     * 商品第一数量
     */
    private BigDecimal gQty1;

    /**
     * 第一法定单位
     */
    private String unit1;

    /**
     * 第二法定单位数量
     */
    private BigDecimal qty2;

    /**
     * 商品第二数量
     */
    private BigDecimal gQty2;

    /**
     * 第二法定单位
     */
    private String unit2;

    /**
     * 毛重
     */
    private BigDecimal grossWeight;

    /**
     * 币种
     */
    private BigDecimal netWeight;

    /**
     * 净重
     */
    private String currency;

    /**
     * 是否商品备案
     */
    private String isGoodsRecord;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIsGoodsRecord() {
        return isGoodsRecord;
    }

    public void setIsGoodsRecord(String isGoodsRecord) {
        this.isGoodsRecord = isGoodsRecord;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}