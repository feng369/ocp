package com.topideal.supplychain.ocp.esd.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.math.BigDecimal;

@JsonInclude(Include.NON_NULL)
public class EsdBox implements Serializable {

    private String number;//箱号
    private BigDecimal client_weight;//箱子重量
    private BigDecimal client_length;//箱子长度
    private BigDecimal client_width;//箱子宽度
    private BigDecimal client_height;//箱子高度

    private EsdBox(Builder builder) {
        number = builder.number;
        client_weight = builder.client_weight;
        client_length = builder.client_length;
        client_width = builder.client_width;
        client_height = builder.client_height;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getClient_weight() {
        return client_weight;
    }

    public BigDecimal getClient_length() {
        return client_length;
    }

    public BigDecimal getClient_width() {
        return client_width;
    }

    public BigDecimal getClient_height() {
        return client_height;
    }


    public static final class Builder {

        private String number;
        private BigDecimal client_weight;
        private BigDecimal client_length;
        private BigDecimal client_width;
        private BigDecimal client_height;

        private Builder() {
        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Builder client_weight(BigDecimal client_weight) {
            if (client_weight != null) {
                this.client_weight = client_weight.multiply(new BigDecimal(1000));
            }
            return this;
        }

        public Builder client_length(BigDecimal client_length) {
            this.client_length = client_length;
            return this;
        }

        public Builder client_width(BigDecimal client_width) {
            this.client_width = client_width;
            return this;
        }

        public Builder client_height(BigDecimal client_height) {
            this.client_height = client_height;
            return this;
        }

        public EsdBox build() {
            return new EsdBox(this);
        }
    }
}