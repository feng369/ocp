
package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;

import java.math.BigDecimal;

/**
 * 贝贝订单商品明细
 */
public class OrderBeibeiItem extends BaseEntity {


    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 序号
     */
    private Integer gnum;

    /**
     * 商品 sku_id
     */
    private String skuId;

    /**
     * 商品ID
     */
    private String iid;

    /**
     * 商品 url
     */
    private String url;

    /**
     * 商家编码/商品货号
     */
    private String outerId;

    /**
     * 货号
     */
    private String goodsNum;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品价格
     */
    private BigDecimal price;

    private BigDecimal declareAmount;

    /**
     * 商品原价
     */
    private BigDecimal originPrice;

    /**
     * 购买该商品的数量
     */
    private Integer num;

    /**
     * 退款状态，0无退款、1退款中、2退款成功、3退款关闭
     */
    private String refundStatus;

    /**
     * 小计 =item.price*item.num-平摊优惠(贝贝现金券+积分) (order.payment=item.subtotal累加)
     */
    private BigDecimal subtotal;

    /**
     * 该商品的商家实际所得（经过优惠平摊）
     */
    private BigDecimal totalFee;

    /**
     * 商品sku属性
     */
    private String skuProperties;

    /**
     * 【全球购】发货地属性
     */
    private String shipCityProperty;

    /**
     * 商品图片
     */
    private String img;

    /**
     * 商品品牌
     */
    private String brand;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getGnum() {
        return gnum;
    }

    public void setGnum(Integer gnum) {
        this.gnum = gnum;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getSkuProperties() {
        return skuProperties;
    }

    public void setSkuProperties(String skuProperties) {
        this.skuProperties = skuProperties;
    }

    public String getShipCityProperty() {
        return shipCityProperty;
    }

    public void setShipCityProperty(String shipCityProperty) {
        this.shipCityProperty = shipCityProperty;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getDeclareAmount() {
        return declareAmount;
    }

    public void setDeclareAmount(BigDecimal declareAmount) {
        this.declareAmount = declareAmount;
    }
}