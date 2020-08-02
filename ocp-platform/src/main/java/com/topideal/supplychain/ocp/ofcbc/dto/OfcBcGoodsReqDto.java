package com.topideal.supplychain.ocp.ofcbc.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 标题：OFC-BC商品明细
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.ofc.dto
 * 作者：songping
 * 创建日期：2020/1/9 16:38
 *
 * @version 1.0
 */
public class OfcBcGoodsReqDto implements Serializable {

    private String gnum;
    private String goodsId;
    private String goodsName;
    private String barCode;
    private String currency;
    private Integer qty;
    private String qtyUnit;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private String batchNo;
    private String note;
    private String ebcCode;
    private String inventoryType;
    private String itemVersion;
    private String virtualOwner;
    private String storeStrategyId;
    private Date productionTime;
    private String traceSourceCode;
    private Date expDate;
    private String hsCode;
    private String ciqGoodsNo;
    private String assemCountry;
    private String ciqAssemCountry;
    private String spec;
    private String brand;
    private String packageType;
    private BigDecimal qty1;
    private BigDecimal gQty1;
    private String unit1;
    private BigDecimal qty2;
    private BigDecimal gQty2;
    private String unit2;
    private BigDecimal grossWeight;
    private BigDecimal netWeight;
    private Integer isGoodsRecord;

    private OfcBcGoodsReqDto(Builder builder) {
        gnum = builder.gnum;
        goodsId = builder.goodsId;
        goodsName = builder.goodsName;
        barCode = builder.barCode;
        currency = builder.currency;
        qty = builder.qty;
        qtyUnit = builder.qtyUnit;
        price = builder.price;
        totalPrice = builder.totalPrice;
        batchNo = builder.batchNo;
        note = builder.note;
        ebcCode = builder.ebcCode;
        inventoryType = builder.inventoryType;
        itemVersion = builder.itemVersion;
        virtualOwner = builder.virtualOwner;
        storeStrategyId = builder.storeStrategyId;
        productionTime = builder.productionTime;
        traceSourceCode = builder.traceSourceCode;
        expDate = builder.expDate;
        hsCode = builder.hsCode;
        ciqGoodsNo = builder.ciqGoodsNo;
        assemCountry = builder.assemCountry;
        ciqAssemCountry = builder.ciqAssemCountry;
        spec = builder.spec;
        brand = builder.brand;
        packageType = builder.packageType;
        qty1 = builder.qty1;
        gQty1 = builder.gQty1;
        unit1 = builder.unit1;
        qty2 = builder.qty2;
        gQty2 = builder.gQty2;
        unit2 = builder.unit2;
        grossWeight = builder.grossWeight;
        netWeight = builder.netWeight;
        isGoodsRecord = builder.isGoodsRecord;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private String gnum;
        private String goodsId;
        private String goodsName;
        private String barCode;
        private String currency;
        private Integer qty;
        private String qtyUnit;
        private BigDecimal price;
        private BigDecimal totalPrice;
        private String batchNo;
        private String note;
        private String ebcCode;
        private String inventoryType;
        private String itemVersion;
        private String virtualOwner;
        private String storeStrategyId;
        private Date productionTime;
        private String traceSourceCode;
        private Date expDate;
        private String hsCode;
        private String ciqGoodsNo;
        private String assemCountry;
        private String ciqAssemCountry;
        private String spec;
        private String brand;
        private String packageType;
        private BigDecimal qty1;
        private BigDecimal gQty1;
        private String unit1;
        private BigDecimal qty2;
        private BigDecimal gQty2;
        private String unit2;
        private BigDecimal grossWeight;
        private BigDecimal netWeight;
        private Integer isGoodsRecord;

        private Builder() {
        }

        public Builder gnum(String val) {
            gnum = val;
            return this;
        }

        public Builder goodsId(String val) {
            goodsId = val;
            return this;
        }

        public Builder goodsName(String val) {
            goodsName = val;
            return this;
        }

        public Builder barCode(String val) {
            barCode = val;
            return this;
        }

        public Builder currency(String val) {
            currency = val;
            return this;
        }

        public Builder qty(Integer val) {
            qty = val;
            return this;
        }

        public Builder qtyUnit(String val) {
            qtyUnit = val;
            return this;
        }

        public Builder price(BigDecimal val) {
            price = val == null ? null : val.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        public Builder totalPrice(BigDecimal val) {
            totalPrice = val == null ? null : val.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        public Builder batchNo(String val) {
            batchNo = val;
            return this;
        }

        public Builder note(String val) {
            note = val;
            return this;
        }

        public Builder ebcCode(String val) {
            ebcCode = val;
            return this;
        }

        public Builder inventoryType(String val) {
            inventoryType = val;
            return this;
        }

        public Builder itemVersion(String val) {
            itemVersion = val;
            return this;
        }

        public Builder virtualOwner(String val) {
            virtualOwner = val;
            return this;
        }

        public Builder storeStrategyId(String val) {
            storeStrategyId = val;
            return this;
        }

        public Builder productionTime(Date val) {
            productionTime = val;
            return this;
        }

        public Builder traceSourceCode(String val) {
            traceSourceCode = val;
            return this;
        }

        public Builder expDate(Date val) {
            expDate = val;
            return this;
        }

        public Builder hsCode(String val) {
            hsCode = val;
            return this;
        }

        public Builder ciqGoodsNo(String val) {
            ciqGoodsNo = val;
            return this;
        }

        public Builder assemCountry(String val) {
            assemCountry = val;
            return this;
        }

        public Builder ciqAssemCountry(String val) {
            ciqAssemCountry = val;
            return this;
        }

        public Builder spec(String val) {
            spec = val;
            return this;
        }

        public Builder brand(String val) {
            brand = val;
            return this;
        }

        public Builder packageType(String val) {
            packageType = val;
            return this;
        }

        public Builder qty1(BigDecimal val) {
            qty1 = val;
            return this;
        }

        public Builder gQty1(BigDecimal val) {
            gQty1 = val;
            return this;
        }

        public Builder unit1(String val) {
            unit1 = val;
            return this;
        }

        public Builder qty2(BigDecimal val) {
            qty2 = val;
            return this;
        }

        public Builder gQty2(BigDecimal val) {
            gQty2 = val;
            return this;
        }

        public Builder unit2(String val) {
            unit2 = val;
            return this;
        }

        public Builder grossWeight(BigDecimal val) {
            grossWeight = val == null ? null : val.multiply(BigDecimal.valueOf(1000));
            return this;
        }

        public Builder netWeight(BigDecimal val) {
            netWeight = val == null ? null : val.multiply(BigDecimal.valueOf(1000));
            return this;
        }

        public Builder isGoodsRecord(Integer val) {
            isGoodsRecord = val;
            return this;
        }

        public OfcBcGoodsReqDto build() {
            return new OfcBcGoodsReqDto(this);
        }
    }

    public String getGnum() {
        return gnum;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getCurrency() {
        return currency;
    }

    public Integer getQty() {
        return qty;
    }

    public String getQtyUnit() {
        return qtyUnit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public String getNote() {
        return note;
    }

    public String getEbcCode() {
        return ebcCode;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public String getItemVersion() {
        return itemVersion;
    }

    public String getVirtualOwner() {
        return virtualOwner;
    }

    public String getStoreStrategyId() {
        return storeStrategyId;
    }

    public Date getProductionTime() {
        return productionTime;
    }

    public String getTraceSourceCode() {
        return traceSourceCode;
    }

    public Date getExpDate() {
        return expDate;
    }

    public String getHsCode() {
        return hsCode;
    }

    public String getCiqGoodsNo() {
        return ciqGoodsNo;
    }

    public String getAssemCountry() {
        return assemCountry;
    }

    public String getCiqAssemCountry() {
        return ciqAssemCountry;
    }

    public String getSpec() {
        return spec;
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

    public BigDecimal getgQty1() {
        return gQty1;
    }

    public String getUnit1() {
        return unit1;
    }

    public BigDecimal getQty2() {
        return qty2;
    }

    public BigDecimal getgQty2() {
        return gQty2;
    }

    public String getUnit2() {
        return unit2;
    }

    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    public BigDecimal getNetWeight() {
        return netWeight;
    }

    public Integer getIsGoodsRecord() {
        return isGoodsRecord;
    }
}
