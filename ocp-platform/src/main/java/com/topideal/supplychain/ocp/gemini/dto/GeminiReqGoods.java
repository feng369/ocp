package com.topideal.supplychain.ocp.gemini.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wanzhaozhang
 * @date 2019/12/9
 * @description
 **/
public class GeminiReqGoods implements Serializable {
    //商品id
    private Long id;
    //商品编码
    private String outerItemId;
    //商品名称
    private String outerItemName;
    //含税单价
    private BigDecimal feePrice;
    //含税总价
    private BigDecimal feeTotalPrice;
    //数量
    private BigDecimal num;

    private GeminiReqGoods(Builder builder) {
        id = builder.id;
        outerItemId = builder.outerItemId;
        outerItemName = builder.outerItemName;
        feePrice = builder.feePrice;
        feeTotalPrice = builder.feeTotalPrice;
        num = builder.num;
    }

    public static Builder newBuilder(){return new Builder();}

    public static final class Builder {
        private Long id;
        private String outerItemId;
        private String outerItemName;
        private BigDecimal feePrice;
        private BigDecimal feeTotalPrice;
        private BigDecimal num;

        public Builder() {
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder outerItemId(String val) {
            outerItemId = val;
            return this;
        }

        public Builder outerItemName(String val) {
            outerItemName = val;
            return this;
        }

        public Builder feePrice(BigDecimal val) {
            feePrice = val;
            return this;
        }

        public Builder feeTotalPrice(BigDecimal val) {
            feeTotalPrice = val;
            return this;
        }

        public Builder num(BigDecimal val) {
            num = val;
            return this;
        }

        public GeminiReqGoods build() {
            return new GeminiReqGoods(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getOuterItemId() {
        return outerItemId;
    }

    public String getOuterItemName() {
        return outerItemName;
    }

    public BigDecimal getFeePrice() {
        return feePrice;
    }

    public BigDecimal getFeeTotalPrice() {
        return feeTotalPrice;
    }

    public BigDecimal getNum() {
        return num;
    }
}
