package com.topideal.supplychain.ocp.op.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName OpGoodsItemDto
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/9 16:09
 * @Version 1.0
 **/
@JsonInclude(Include.NON_NULL)
public class OpGoodsItemDto implements Serializable {

    private String  gnum;
    private String  goodId;//商品货号
    private Integer  amount;//数量
    private BigDecimal price;//售价（实际售价）
    private BigDecimal  goodPrice;//商品售价（展示售价）
    private String  copGName;//商品名称
    private String  hsCode;//海关编码
    private String  codeTs;//行邮税号
    private BigDecimal  decTotal;//商品总价
    private String  custGoodsNo;//商品海关备案号
    private String  ciqGoodsNo;//国检商品备案号
    private String  batchNo;//商品批次号
    private String  assemCountry;//原厂国
    private String  qtyUnit;//法定计量单位
    private String  spec;//商品规格型号
    private String  storeStrategyId;//指定库存策略号
    private Date productionTime;//生产日期
    private Date  expDate;//到期日期
    private String  ownerCode;//货主
    private String  brand;//品牌
	private String  packageType;//包装    
    private String  qty1;//第一法定单位数量
    private String  unit1;//第一法定单位
    private String  qty2;//第二法定单位数量
    private String  unit2;//第二法定单位
    private BigDecimal  ggrossWt;//商品毛重
    private String  virtualOwner;//虚拟货主

    private OpGoodsItemDto(Builder builder) {
        gnum = builder.gnum;
        goodId = builder.goodId;
        amount = builder.amount;
        price = builder.price;
        goodPrice = builder.goodPrice;
        copGName = builder.copGName;
        hsCode = builder.hsCode;
        codeTs = builder.codeTs;
        decTotal = builder.decTotal;
        custGoodsNo = builder.custGoodsNo;
        ciqGoodsNo = builder.ciqGoodsNo;
        batchNo = builder.batchNo;
        assemCountry = builder.assemCountry;
        qtyUnit = builder.qtyUnit;
        spec = builder.spec;
        storeStrategyId = builder.storeStrategyId;
        productionTime = builder.productionTime;
        expDate = builder.expDate;
        ownerCode = builder.ownerCode;
        brand = builder.brand;
        packageType = builder.packageType;
        qty1 = builder.qty1;
        unit1 = builder.unit1;
        qty2 = builder.qty2;
        unit2 = builder.unit2;
        ggrossWt = builder.ggrossWt;
        virtualOwner = builder.virtualOwner;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {

        private String gnum;
        private String goodId;
        private Integer amount;
        private BigDecimal price;
        private BigDecimal goodPrice;
        private String copGName;
        private String hsCode;
        private String codeTs;
        private BigDecimal decTotal;
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
        private String brand;
        private String packageType;
        private String qty1;
        private String unit1;
        private String qty2;
        private String unit2;
        private BigDecimal ggrossWt;
        private String  virtualOwner;

        private Builder() {
        }

        public Builder gnum(String gnum) {
            this.gnum = gnum;
            return this;
        }

        public Builder goodId(String goodId) {
            this.goodId = goodId;
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

        public Builder codeTs(String codeTs) {
            this.codeTs = codeTs;
            return this;
        }

        public Builder decTotal(BigDecimal decTotal) {
            this.decTotal = decTotal;
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

        public Builder productionTime(Date productionTime) {
            this.productionTime = productionTime;
            return this;
        }

        public Builder expDate(Date expDate) {
            this.expDate = expDate;
            return this;
        }

        public Builder ownerCode(String ownerCode) {
            this.ownerCode = ownerCode;
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

        public Builder qty1(String qty1) {
            this.qty1 = qty1;
            return this;
        }

        public Builder unit1(String unit1) {
            this.unit1 = unit1;
            return this;
        }

        public Builder qty2(String qty2) {
            this.qty2 = qty2;
            return this;
        }

        public Builder unit2(String unit2) {
            this.unit2 = unit2;
            return this;
        }

        public Builder ggrossWt(BigDecimal ggrossWt) {
            this.ggrossWt = ggrossWt;
            return this;
        }

        public Builder virtualOwner(String virtualOwner) {
            this.virtualOwner = virtualOwner;
            return this;
        }

        public OpGoodsItemDto build() {
            return new OpGoodsItemDto(this);
        }
    }

    public String getGnum() {
        return gnum;
    }

    public String getGoodId() {
        return goodId;
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

    public String getCodeTs() {
        return codeTs;
    }

    public BigDecimal getDecTotal() {
        return decTotal;
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

    public String getQtyUnit() {
        return qtyUnit;
    }

    public String getSpec() {
        return spec;
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

    public String getOwnerCode() {
        return ownerCode;
    }

    public String getBrand() {
        return brand;
    }

    public String getPackageType() {
        return packageType;
    }

    public String getQty1() {
        return qty1;
    }

    public String getUnit1() {
        return unit1;
    }

    public String getQty2() {
        return qty2;
    }

    public String getUnit2() {
        return unit2;
    }

    public BigDecimal getGgrossWt() {
        return ggrossWt;
    }

    public String getVirtualOwner() {
        return virtualOwner;
    }
}
