package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;

import java.math.BigDecimal;

public class OrderXiaomiGoods extends BaseEntity {

    /**
     * 序号
     */
    private Long gnum;

    /**
     * 商品id
     */
    private Long gid;

    /**
     * 产品ID
     */
    private Long pid;

    /**
     * 商品数量
     */
    private Long count;

    /**
     * 售卖价格
     */
    private BigDecimal salePrice;

    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品支付价格
     */
    private BigDecimal price;
    /**
     * 用户id
     */
    private Long uid;

    /**
     * 订单id
     */
    private Long orderId;

    public Long getGnum() {
        return gnum;
    }

    public void setGnum(Long gnum) {
        this.gnum = gnum;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}