package com.topideal.supplychain.ocp.kjb.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName KjbRequestDto
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/6 10:30
 * @Version 1.0
 **/
public class KjbRequest implements Serializable {

    //货主编号
    private String cbepcomCode;
    //订单号
    private String order_id;
    //订单总货款
    private BigDecimal payment_goods;
    //商品
    private List<KjbReqGoods> good_list;

    private KjbRequest(Builder builder) {
        cbepcomCode = builder.cbepcomCode;
        order_id = builder.order_id;
        payment_goods = builder.payment_goods;
        good_list = builder.good_list;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {

        private String cbepcomCode;
        private String order_id;
        private BigDecimal payment_goods;
        private List<KjbReqGoods> good_list;

        private Builder() {
        }

        public Builder cbepcomCode(String val) {
            cbepcomCode = val;
            return this;
        }

        public Builder order_id(String val) {
            order_id = val;
            return this;
        }

        public Builder payment_goods(BigDecimal val) {
            payment_goods = val;
            return this;
        }

        public Builder good_list(List<KjbReqGoods> val) {
            good_list = val;
            return this;
        }

        public KjbRequest build() {
            return new KjbRequest(this);
        }
    }

    public String getCbepcomCode() {
        return cbepcomCode;
    }

    public String getOrder_id() {
        return order_id;
    }

    public BigDecimal getPayment_goods() {
        return payment_goods;
    }

    public List<KjbReqGoods> getGood_list() {
        return good_list;
    }
}
