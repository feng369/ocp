package com.topideal.supplychain.ocp.kjb.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class KjbReqGoods implements Serializable {

    //商品货号
    private String goods_id;
    //商品名称
    private String goods_name;
    //商品数量
    private String goods_num;
    //商品单价
    private BigDecimal goods_price;

    private KjbReqGoods(Builder builder) {
        goods_id = builder.goods_id;
        goods_name = builder.goods_name;
        goods_num = builder.goods_num;
        goods_price = builder.goods_price;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {

        private String goods_id;
        private String goods_name;
        private String goods_num;
        private BigDecimal goods_price;

        private Builder() {
        }

        public Builder goods_id(String goods_id) {
            this.goods_id = goods_id;
            return this;
        }

        public Builder goods_name(String goods_name) {
            this.goods_name = goods_name;
            return this;
        }

        public Builder goods_num(String goods_num) {
            this.goods_num = goods_num;
            return this;
        }

        public Builder goods_price(BigDecimal goods_price) {
            this.goods_price = goods_price;
            return this;
        }

        public KjbReqGoods build() {
            return new KjbReqGoods(this);
        }
    }

    public String getGoods_id() {
        return goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public String getGoods_num() {
        return goods_num;
    }

    public BigDecimal getGoods_price() {
        return goods_price;
    }
}