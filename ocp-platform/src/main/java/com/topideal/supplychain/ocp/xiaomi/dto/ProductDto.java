package com.topideal.supplychain.ocp.xiaomi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wanzhaozhang
 * @date 2019/12/30
 * @description 小米订单产品详情 需要字段
 **/
public class ProductDto implements Serializable {
    //商品数量
    private Long count;
    // 商品ID
    private Long gid;
    // 产品ID
    private Long pid;
    // 用户id
    private Long uid;
    // 下单时该商品售卖价格，单位：分
    private BigDecimal sale_price;
    // 产品支付价格（pid支付金额小计），单位：分
    private BigDecimal price;
    // 产品名称
    private String name;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public BigDecimal getSale_price() {
        return sale_price;
    }

    public void setSale_price(BigDecimal sale_price) {
        this.sale_price = sale_price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
