package com.topideal.supplychain.ocp.gemini.dto;

import com.topideal.supplychain.ocp.kjb.dto.KjbReqGoods;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/9
 * @description
 **/
public class GeminiRequest implements Serializable {
    //电商平台编码
    private String ebpCode;
    //电商企业编码
    private String electricCode;
    //订单编号
    private String tid;
    //业务类型
    private String busiMode;
    //申报地海关
    private String customerCode;
    //含税货价
    private BigDecimal totalPrice;
    //商品
    private List<GeminiReqGoods> goods;

    private GeminiRequest(Builder builder) {
        ebpCode = builder.ebpCode;
        electricCode = builder.electricCode;
        tid = builder.tid;
        busiMode = builder.busiMode;
        customerCode = builder.customerCode;
        totalPrice = builder.totalPrice;
        goods = builder.goods;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
    public static final class Builder {
        private String ebpCode;
        private String electricCode;
        private String tid;
        private String busiMode;
        private String customerCode;
        private BigDecimal totalPrice;
        private List<GeminiReqGoods> goods;

        public Builder() {
        }

        public Builder ebpCode(String ebpCode) {
            this.ebpCode = ebpCode;
            return this;
        }

        public Builder electricCode(String electricCode) {
            this.electricCode = electricCode;
            return this;
        }

        public Builder tid(String tid) {
            this.tid = tid;
            return this;
        }

        public Builder busiMode(String busiMode) {
            this.busiMode = busiMode;
            return this;
        }

        public Builder customerCode(String customerCode) {
            this.customerCode = customerCode;
            return this;
        }

        public Builder totalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder goods(List<GeminiReqGoods> goods) {
            this.goods = goods;
            return this;
        }

        public GeminiRequest build() {
            return new GeminiRequest(this);
        }
    }

    public String getEbpCode() {
        return ebpCode;
    }

    public String getElectricCode() {
        return electricCode;
    }

    public String getTid() {
        return tid;
    }

    public String getBusiMode() {
        return busiMode;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<GeminiReqGoods> getGoods() {
        return goods;
    }
}
