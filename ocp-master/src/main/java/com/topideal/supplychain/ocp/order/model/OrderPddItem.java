package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @author klw
 * @date 2019/12/11 16:01
 * @description: 拼多多订单明细实体类
 */
public class OrderPddItem extends BaseEntity {

    private Long orderId;

    private Integer gnum;

    private String goodId;

    private Integer amount;

    private BigDecimal price;

    private BigDecimal goodPrice;

    private BigDecimal decTotal;

    private BigDecimal totalPrice;

    private String copGName;

    private String hsCode;

    private String codeTs;

    private String nots;

    private String custGoodsNo;

    private String ciqGoodsNo;

    private String batchNo;

    private String assemCountry;

    private String qtyUnit;

    private String spec;

    private String storeStrategyId;

    private Date productionTime;

    private Date expDate;

    private String ownerCode;

    private String virtualOwner;

    private String brand;

    private String packageType;

    private BigDecimal qty1;

    private String unit1;

    private BigDecimal qty2;

    private String unit2;

    private BigDecimal grossWeight;

    private String barCode;

    private String currency;

    private String ciqAssemCountry;

    private BigDecimal gQty1;

    private BigDecimal gQty2;

    private BigDecimal netWeight;

    private Integer isGoodsRecord;

    /****
     * 非数据库字段，传值用
     * @return
     */
    private String ebcCode;

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

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId == null ? null : goodId.trim();
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

    public BigDecimal getDecTotal() {
        return decTotal;
    }

    public void setDecTotal(BigDecimal decTotal) {
        this.decTotal = decTotal;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCopGName() {
        return copGName;
    }

    public void setCopGName(String copGName) {
        this.copGName = copGName == null ? null : copGName.trim();
    }

    public String getHsCode() {
        return hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode == null ? null : hsCode.trim();
    }

    public String getCodeTs() {
        return codeTs;
    }

    public void setCodeTs(String codeTs) {
        this.codeTs = codeTs == null ? null : codeTs.trim();
    }

    public String getNots() {
        return nots;
    }

    public void setNots(String nots) {
        this.nots = nots == null ? null : nots.trim();
    }

    public String getCustGoodsNo() {
        return custGoodsNo;
    }

    public void setCustGoodsNo(String custGoodsNo) {
        this.custGoodsNo = custGoodsNo == null ? null : custGoodsNo.trim();
    }

    public String getCiqGoodsNo() {
        return ciqGoodsNo;
    }

    public void setCiqGoodsNo(String ciqGoodsNo) {
        this.ciqGoodsNo = ciqGoodsNo == null ? null : ciqGoodsNo.trim();
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public String getAssemCountry() {
        return assemCountry;
    }

    public void setAssemCountry(String assemCountry) {
        this.assemCountry = assemCountry == null ? null : assemCountry.trim();
    }

    public String getQtyUnit() {
        return qtyUnit;
    }

    public void setQtyUnit(String qtyUnit) {
        this.qtyUnit = qtyUnit == null ? null : qtyUnit.trim();
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public String getStoreStrategyId() {
        return storeStrategyId;
    }

    public void setStoreStrategyId(String storeStrategyId) {
        this.storeStrategyId = storeStrategyId == null ? null : storeStrategyId.trim();
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
        this.ownerCode = ownerCode == null ? null : ownerCode.trim();
    }

    public String getVirtualOwner() {
        return virtualOwner;
    }

    public void setVirtualOwner(String virtualOwner) {
        this.virtualOwner = virtualOwner == null ? null : virtualOwner.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType == null ? null : packageType.trim();
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

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getCiqAssemCountry() {
        return ciqAssemCountry;
    }

    public void setCiqAssemCountry(String ciqAssemCountry) {
        this.ciqAssemCountry = ciqAssemCountry == null ? null : ciqAssemCountry.trim();
    }

    public BigDecimal getgQty1() {
        return gQty1;
    }

    public void setgQty1(BigDecimal gQty1) {
        this.gQty1 = gQty1;
    }

    public BigDecimal getgQty2() {
        return gQty2;
    }

    public void setgQty2(BigDecimal gQty2) {
        this.gQty2 = gQty2;
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

    public String getEbcCode() {
        return ebcCode;
    }

    public void setEbcCode(String ebcCode) {
        this.ebcCode = ebcCode;
    }
}