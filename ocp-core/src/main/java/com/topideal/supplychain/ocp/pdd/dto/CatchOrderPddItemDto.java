package com.topideal.supplychain.ocp.pdd.dto;

import java.math.BigDecimal;

/**
 * @author klw
 * @date 2019/12/13 16:51
 * @description:
 */
public class CatchOrderPddItemDto {
    private Long goodsId;

    private Long skuId;

    private String outerId;

    private String outerGoodsId;

    private String goodsName;

    private BigDecimal goodsPrice;

    private String goodsSpec;

    private Long goodsCount;

    private String goodsImg;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    public String getOuterGoodsId() {
        return outerGoodsId;
    }

    public void setOuterGoodsId(String outerGoodsId) {
        this.outerGoodsId = outerGoodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsSpec() {
        return goodsSpec;
    }

    public void setGoodsSpec(String goodsSpec) {
        this.goodsSpec = goodsSpec;
    }

    public Long getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Long goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }
}
