package com.topideal.supplychain.ocp.ew.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class EwFxGoods implements Serializable {

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

    private EwFxGoods(Builder builder) {
        gnum = builder.gnum;
        goodId = builder.goodId;
        barCode = builder.barCode;
        amount = builder.amount;
        price = builder.price;
        goodPrice = builder.goodPrice;
        copGName = builder.copGName;
        hsCode = builder.hsCode;
        decTotal = builder.decTotal;
        notes = builder.notes;
        custGoodsNo = builder.custGoodsNo;
        ciqGoodsNo = builder.ciqGoodsNo;
        batchNo = builder.batchNo;
        assemCountry = builder.assemCountry;
        ciqAssemCountry = builder.ciqAssemCountry;
        qtyUnit = builder.qtyUnit;
        spec = builder.spec;
        storeStrategyId = builder.storeStrategyId;
        productionTime = builder.productionTime;
        expDate = builder.expDate;
        ownerCode = builder.ownerCode;
        virtualOwner = builder.virtualOwner;
        codeTs = builder.codeTs;
        brand = builder.brand;
        packageType = builder.packageType;
        qty1 = builder.qty1;
        gQty1 = builder.gQty1;
        unit1 = builder.unit1;
        qty2 = builder.qty2;
        gQty2 = builder.gQty2;
        unit2 = builder.unit2;
        grossWeight = builder.grossWeight;
        currency = builder.currency;
        netWeight = builder.netWeight;
        isGoodsRecord = builder.isGoodsRecord;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getGnum() {
        return gnum;
    }

    public String getGoodId() {
        return goodId;
    }

    public String getBarCode() {
        return barCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getGoodPrice() {
        return goodPrice;
    }

    public String getCopGName() {
        return copGName;
    }

    public String getHsCode() {
        return hsCode;
    }

    public BigDecimal getDecTotal() {
        return decTotal;
    }

    public String getNotes() {
        return notes;
    }

    public String getCustGoodsNo() {
        return custGoodsNo;
    }

    public String getCiqGoodsNo() {
        return ciqGoodsNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public String getAssemCountry() {
        return assemCountry;
    }

    public String getCiqAssemCountry() {
        return ciqAssemCountry;
    }

    public String getQtyUnit() {
        return qtyUnit;
    }

    public String getSpec() {
        return spec;
    }

    public String getStoreStrategyId() {
        return storeStrategyId;
    }

    public String getProductionTime() {
        return productionTime;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public String getVirtualOwner() {
        return virtualOwner;
    }

    public String getCodeTs() {
        return codeTs;
    }

    public String getBrand() {
        return brand;
    }

    public String getPackageType() {
        return packageType;
    }

    public BigDecimal getQty1() {
        return qty1;
    }

    public String getgQty1() {
        return gQty1;
    }

    public String getUnit1() {
        return unit1;
    }

    public BigDecimal getQty2() {
        return qty2;
    }

    public String getgQty2() {
        return gQty2;
    }

    public String getUnit2() {
        return unit2;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public String getIsGoodsRecord() {
        return isGoodsRecord;
    }


    public static final class Builder {

        private Integer gnum;
        private String goodId;
        private String barCode;
        private Integer amount;
        private BigDecimal price;
        private BigDecimal goodPrice;
        private String copGName;
        private String hsCode;
        private BigDecimal decTotal;
        private String notes;
        private String custGoodsNo;
        private String ciqGoodsNo;
        private String batchNo;
        private String assemCountry;
        private String ciqAssemCountry;
        private String qtyUnit;
        private String spec;
        private String storeStrategyId;
        private String productionTime;
        private String expDate;
        private String ownerCode;
        private String virtualOwner;
        private String codeTs;
        private String brand;
        private String packageType;
        private BigDecimal qty1;
        private String gQty1;
        private String unit1;
        private BigDecimal qty2;
        private String gQty2;
        private String unit2;
        private BigDecimal grossWeight;
        private String currency;
        private BigDecimal netWeight;
        private String isGoodsRecord;

        private Builder() {
        }

        public Builder gnum(Integer gnum) {
            this.gnum = gnum;
            return this;
        }

        public Builder goodId(String goodId) {
            this.goodId = goodId;
            return this;
        }

        public Builder barCode(String barCode) {
            this.barCode = barCode;
            return this;
        }

        public Builder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder goodPrice(BigDecimal goodPrice) {
            this.goodPrice = goodPrice;
            return this;
        }

        public Builder copGName(String copGName) {
            this.copGName = copGName;
            return this;
        }

        public Builder hsCode(String hsCode) {
            this.hsCode = hsCode;
            return this;
        }

        public Builder decTotal(BigDecimal decTotal) {
            this.decTotal = decTotal;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder custGoodsNo(String custGoodsNo) {
            this.custGoodsNo = custGoodsNo;
            return this;
        }

        public Builder ciqGoodsNo(String ciqGoodsNo) {
            this.ciqGoodsNo = ciqGoodsNo;
            return this;
        }

        public Builder batchNo(String batchNo) {
            this.batchNo = batchNo;
            return this;
        }

        public Builder assemCountry(String assemCountry) {
            this.assemCountry = assemCountry;
            return this;
        }

        public Builder ciqAssemCountry(String ciqAssemCountry) {
            this.ciqAssemCountry = ciqAssemCountry;
            return this;
        }

        public Builder qtyUnit(String qtyUnit) {
            this.qtyUnit = qtyUnit;
            return this;
        }

        public Builder spec(String spec) {
            this.spec = spec;
            return this;
        }

        public Builder storeStrategyId(String storeStrategyId) {
            this.storeStrategyId = storeStrategyId;
            return this;
        }

        public Builder productionTime(String productionTime) {
            this.productionTime = productionTime;
            return this;
        }

        public Builder expDate(String expDate) {
            this.expDate = expDate;
            return this;
        }

        public Builder ownerCode(String ownerCode) {
            this.ownerCode = ownerCode;
            return this;
        }

        public Builder virtualOwner(String virtualOwner) {
            this.virtualOwner = virtualOwner;
            return this;
        }

        public Builder codeTs(String codeTs) {
            this.codeTs = codeTs;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder packageType(String packageType) {
            this.packageType = packageType;
            return this;
        }

        public Builder qty1(BigDecimal qty1) {
            this.qty1 = qty1;
            return this;
        }

        public Builder gQty1(String gQty1) {
            this.gQty1 = gQty1;
            return this;
        }

        public Builder unit1(String unit1) {
            this.unit1 = unit1;
            return this;
        }

        public Builder qty2(BigDecimal qty2) {
            this.qty2 = qty2;
            return this;
        }

        public Builder gQty2(String gQty2) {
            this.gQty2 = gQty2;
            return this;
        }

        public Builder unit2(String unit2) {
            this.unit2 = unit2;
            return this;
        }

        public Builder grossWeight(BigDecimal grossWeight) {
            this.grossWeight = grossWeight;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder netWeight(BigDecimal netWeight) {
            this.netWeight = netWeight;
            return this;
        }

        public Builder isGoodsRecord(String isGoodsRecord) {
            this.isGoodsRecord = isGoodsRecord;
            return this;
        }

        public EwFxGoods build() {
            return new EwFxGoods(this);
        }
    }
}
