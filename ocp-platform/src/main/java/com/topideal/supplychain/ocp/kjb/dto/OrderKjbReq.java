package com.topideal.supplychain.ocp.kjb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2020/1/2
 * @description 下发跨境宝订单类
 **/
public class OrderKjbReq implements Serializable {
    //小米订单号
    private String orderId;
    //订单创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate;
    //支付日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payDate;
    //电商企业编码
    private String electricCode;
    //店铺编码
    private String shopid;
    //运费，单位分
    private BigDecimal freightFcy;
    //优惠金额，单位分
    private BigDecimal discount;
    //货值，单位分
    private BigDecimal totalValue;
    //订购人名
    private String buyerName;
    //订购人身份证
    private String buyerIdNumber;
    //支付企业名称
    private String payPcomName;
    //订购人注册号
    private Long buyerRegNo;
    //商品
    private List<GoodsReq> goodList;
    //收件信息
    private RecipientReq recipient;

    public OrderKjbReq() {
    }

    public String getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public String getElectricCode() {
        return electricCode;
    }

    public String getShopid() {
        return shopid;
    }

    public BigDecimal getFreightFcy() {
        return freightFcy;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerIdNumber() {
        return buyerIdNumber;
    }

    public String getPayPcomName() {
        return payPcomName;
    }

    public Long getBuyerRegNo() {
        return buyerRegNo;
    }

    public List<GoodsReq> getGoodList() {
        return goodList;
    }

    public RecipientReq getRecipient() {
        return recipient;
    }

    private OrderKjbReq(Builder builder) {
        orderId = builder.orderId;
        orderDate = builder.orderDate;
        payDate = builder.payDate;
        electricCode = builder.electricCode;
        shopid = builder.shopid;
        freightFcy = builder.freightFcy;
        discount = builder.discount;
        totalValue = builder.totalValue;
        buyerName = builder.buyerName;
        buyerIdNumber = builder.buyerIdNumber;
        payPcomName = builder.payPcomName;
        buyerRegNo = builder.buyerRegNo;
        goodList = builder.goodList;
        recipient = builder.recipient;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class GoodsReq{
        private Long gnum;
        private Long gId;
        private Long pId;
        private Long amount;
        //售价（实际售价），单位分 ,取sale_price
        private BigDecimal price;
        //商品售价（展示售价），单位分   取price
        private BigDecimal goodPrice;

        public GoodsReq() {
        }

        private GoodsReq(Builder builder) {
            gnum = builder.gnum;
            gId = builder.gId;
            pId = builder.pId;
            amount = builder.amount;
            price = builder.price;
            goodPrice = builder.goodPrice;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static final class Builder {
            private Long gnum;
            private Long gId;
            private Long pId;
            private Long amount;
            private BigDecimal price;
            private BigDecimal goodPrice;

            private Builder() {
            }

            public Builder gnum(Long gnum) {
                this.gnum = gnum;
                return this;
            }

            public Builder gId(Long gId) {
                this.gId = gId;
                return this;
            }

            public Builder pId(Long pId) {
                this.pId = pId;
                return this;
            }

            public Builder amount(Long amount) {
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

            public GoodsReq build() {
                return new GoodsReq(this);
            }
        }

        public Long getGnum() {
            return gnum;
        }

        public Long getgId() {
            return gId;
        }

        public Long getpId() {
            return pId;
        }

        public Long getAmount() {
            return amount;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public BigDecimal getGoodPrice() {
            return goodPrice;
        }
    }

    public static final class RecipientReq{
        private String name;
        private String mobilePhone;
        private String province;
        private String city;
        private String district;
        private String address;
        private String postCode;

        public String getName() {
            return name;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public String getProvince() {
            return province;
        }

        public String getCity() {
            return city;
        }

        public String getDistrict() {
            return district;
        }

        public String getAddress() {
            return address;
        }

        public String getPostCode() {
            return postCode;
        }

        private RecipientReq(Builder builder) {
            name = builder.name;
            mobilePhone = builder.mobilePhone;
            province = builder.province;
            city = builder.city;
            district = builder.district;
            address = builder.address;
            postCode = builder.postCode;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public RecipientReq() {
        }

        public static final class Builder {
            private String name;
            private String mobilePhone;
            private String province;
            private String city;
            private String district;
            private String address;
            private String postCode;

            private Builder() {
            }

            public Builder name(String name) {
                this.name = name;
                return this;
            }

            public Builder mobilePhone(String mobilePhone) {
                this.mobilePhone = mobilePhone;
                return this;
            }

            public Builder province(String province) {
                this.province = province;
                return this;
            }

            public Builder city(String city) {
                this.city = city;
                return this;
            }

            public Builder district(String district) {
                this.district = district;
                return this;
            }

            public Builder address(String address) {
                this.address = address;
                return this;
            }

            public Builder postCode(String postCode) {
                this.postCode = postCode;
                return this;
            }

            public RecipientReq build() {
                return new RecipientReq(this);
            }
        }
    }


    public static final class Builder {
        private String orderId;
        private Date orderDate;
        private Date payDate;
        private String electricCode;
        private String shopid;
        private BigDecimal freightFcy;
        private BigDecimal discount;
        private BigDecimal totalValue;
        private String buyerName;
        private String buyerIdNumber;
        private String payPcomName;
        private Long buyerRegNo;
        private List<GoodsReq> goodList;
        private RecipientReq recipient;

        private Builder() {
        }

        public Builder orderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder orderDate(Date orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder payDate(Date payDate) {
            this.payDate = payDate;
            return this;
        }

        public Builder electricCode(String electricCode) {
            this.electricCode = electricCode;
            return this;
        }

        public Builder shopid(String shopid) {
            this.shopid = shopid;
            return this;
        }

        public Builder freightFcy(BigDecimal freightFcy) {
            this.freightFcy = freightFcy;
            return this;
        }

        public Builder discount(BigDecimal discount) {
            this.discount = discount;
            return this;
        }

        public Builder totalValue(BigDecimal totalValue) {
            this.totalValue = totalValue;
            return this;
        }

        public Builder buyerName(String buyerName) {
            this.buyerName = buyerName;
            return this;
        }

        public Builder buyerIdNumber(String buyerIdNumber) {
            this.buyerIdNumber = buyerIdNumber;
            return this;
        }

        public Builder payPcomName(String payPcomName) {
            this.payPcomName = payPcomName;
            return this;
        }

        public Builder buyerRegNo(Long buyerRegNo) {
            this.buyerRegNo = buyerRegNo;
            return this;
        }

        public Builder goodList(List<GoodsReq> goodList) {
            this.goodList = goodList;
            return this;
        }

        public Builder recipient(RecipientReq recipient) {
            this.recipient = recipient;
            return this;
        }

        public OrderKjbReq build() {
            return new OrderKjbReq(this);
        }
    }
}
