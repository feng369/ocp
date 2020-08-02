package com.topideal.supplychain.ocp.ofc.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OfcGoodsReqDto implements Serializable {
        //序号
        private String gnum;
        //商品编码
        private String goodsId;
        //商品名称
        private String goodsName;
        //条形码
        private String barCode;
        //币制
        private String currency;
        //数量
        private BigDecimal qty;
        //计量单位
        private String qtyUnit;
        //单价
        private BigDecimal price;
        //总价
        private BigDecimal totalPrice;
        //商品批次号
        private String batchNo;
        //指定库存策略号
        private String storeStrategyId;
        //生产日期
        private Date productionTime;
        //到期日期
        private Date expDate;
        //电商企业编码
        private String ebcCode;
        //虚拟货主
        private String virtualOwner;
        //库存类型
        private String inventoryType;
        //商品版本
        private String itemVersion;
        //备注
        private String note;
        //海关编码
        private String hsCode;
        //国检商品备案号
        private String ciqGoodsNo;
        //原产国
        private String assemCountry;
        //国检原产地
        private String ciqAssemCountry;
        //商品规格型号
        private String spec;
        //品牌
        private String brand;
        //包装类型
        private String packageType;
        //第一法定单位数量
        private BigDecimal qty1;
        //第一法定单位
        private String unit1;
        //商品第一数量
        private String gQty1;
        //第二法定单位数量
        private BigDecimal qty2;
        //商品第二数量
        private String gQty2;
        //第二法定单位
        private String unit2;
        //毛重（克）
        private BigDecimal grossWeight;
        //净重（克）
        private BigDecimal netWeight;
        //是否商品备案
        private Long isGoodsRecord;
        //销售网址
        private String tradeUrl;
        //提单号
        private String billNo;
        //报关单号
        private String declareCode;
        //报检单号
        private String ciqDeclareCode;
        //国检批次号
        private String ciqInboundDeclareCode;

        private String udf1;
        private String udf2;
        private String udf3;

        private OfcGoodsReqDto(Builder builder) {
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
            storeStrategyId = builder.storeStrategyId;
            productionTime = builder.productionTime;
            expDate = builder.expDate;
            ebcCode = builder.ebcCode;
            virtualOwner = builder.virtualOwner;
            inventoryType = builder.inventoryType;
            itemVersion = builder.itemVersion;
            note = builder.note;
            hsCode = builder.hsCode;
            ciqGoodsNo = builder.ciqGoodsNo;
            assemCountry = builder.assemCountry;
            ciqAssemCountry = builder.ciqAssemCountry;
            spec = builder.spec;
            brand = builder.brand;
            packageType = builder.packageType;
            qty1 = builder.qty1;
            unit1 = builder.unit1;
            gQty1 = builder.gQty1;
            qty2 = builder.qty2;
            gQty2 = builder.gQty2;
            unit2 = builder.unit2;
            grossWeight = builder.grossWeight;
            netWeight = builder.netWeight;
            isGoodsRecord = builder.isGoodsRecord;
            tradeUrl = builder.tradeUrl;
            billNo = builder.billNo;
            declareCode = builder.declareCode;
            ciqDeclareCode = builder.ciqDeclareCode;
            ciqInboundDeclareCode = builder.ciqInboundDeclareCode;
            udf1=builder.udf1;
            udf2=builder.udf2;
            udf3=builder.udf3;
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
            private BigDecimal qty;
            private String qtyUnit;
            private BigDecimal price;
            private BigDecimal totalPrice;
            private String batchNo;
            private String storeStrategyId;
            private Date productionTime;
            private Date expDate;
            private String ebcCode;
            private String virtualOwner;
            private String inventoryType;
            private String itemVersion;
            private String note;
            private String hsCode;
            private String ciqGoodsNo;
            private String assemCountry;
            private String ciqAssemCountry;
            private String spec;
            private String brand;
            private String packageType;
            private BigDecimal qty1;
            private String unit1;
            private String gQty1;
            private BigDecimal qty2;
            private String gQty2;
            private String unit2;
            private BigDecimal grossWeight;
            private BigDecimal netWeight;
            private Long isGoodsRecord;
            private String tradeUrl;
            private String billNo;
            private String declareCode;
            private String ciqDeclareCode;
            private String ciqInboundDeclareCode;
            private String udf1;
            private String udf2;
            private String udf3;
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

            public Builder qty(BigDecimal val) {
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

            public Builder storeStrategyId(String val) {
                storeStrategyId = val;
                return this;
            }

            public Builder productionTime(Date val) {
                productionTime = val;
                return this;
            }

            public Builder expDate(Date val) {
                expDate = val;
                return this;
            }

            public Builder ebcCode(String val) {
                ebcCode = val;
                return this;
            }

            public Builder virtualOwner(String val) {
                virtualOwner = val;
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

            public Builder note(String val) {
                note = val;
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

            public Builder unit1(String val) {
                unit1 = val;
                return this;
            }

            public Builder gQty1(String val) {
                gQty1 = val;
                return this;
            }

            public Builder qty2(BigDecimal val) {
                qty2 = val;
                return this;
            }

            public Builder gQty2(String val) {
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

            public Builder isGoodsRecord(Long val) {
                isGoodsRecord = val;
                return this;
            }

            public Builder tradeUrl(String val) {
                tradeUrl = val;
                return this;
            }

            public Builder billNo(String val) {
                billNo = val;
                return this;
            }

            public Builder declareCode(String val) {
                declareCode = val;
                return this;
            }

            public Builder ciqDeclareCode(String val) {
                ciqDeclareCode = val;
                return this;
            }

            public Builder ciqInboundDeclareCode(String val) {
                ciqInboundDeclareCode = val;
                return this;
            }

            public Builder udf1(String val) {
                udf1 = val;
                return this;
            }

            public Builder udf2(String val) {
                udf2 = val;
                return this;
            }

            public Builder udf3(String val) {
                udf3 = val;
                return this;
            }

            public OfcGoodsReqDto build() {
                return new OfcGoodsReqDto(this);
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

        public BigDecimal getQty() {
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

        public String getStoreStrategyId() {
            return storeStrategyId;
        }

        public Date getProductionTime() {
            return productionTime;
        }

        public Date getExpDate() {
            return expDate;
        }

        public String getEbcCode() {
            return ebcCode;
        }

        public String getVirtualOwner() {
            return virtualOwner;
        }

        public String getInventoryType() {
            return inventoryType;
        }

        public String getItemVersion() {
            return itemVersion;
        }

        public String getNote() {
            return note;
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

        public String getUnit1() {
            return unit1;
        }

        public String getgQty1() {
            return gQty1;
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

        public BigDecimal getNetWeight() {
            return netWeight;
        }

        public Long getIsGoodsRecord() {
            return isGoodsRecord;
        }

        public String getTradeUrl() {
            return tradeUrl;
        }

        public String getBillNo() {
            return billNo;
        }

        public String getDeclareCode() {
            return declareCode;
        }

        public String getCiqDeclareCode() {
            return ciqDeclareCode;
        }

        public String getCiqInboundDeclareCode() {
            return ciqInboundDeclareCode;
        }

        public String getUdf1() {
        return udf1;
        }

        public String getUdf2() {
        return udf2;
        }

        public String getUdf3() {
        return udf3;
        }
    }

