package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
import java.math.BigDecimal;

/**
 * 海拍客订单明细
 *
 * @author xuxiaoyan
 * @date 2019-12-16 15:10
 */
public class OrderHipacItem extends BaseEntity {

    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 订单号
     */
    private String orderNum;
    /**
     * 商品名称
     */
    private String itemName;
    /**
     * 供应商商品编码
     */
    private String itemSupplyNo;
    /**
     * 商品数量
     */
    private BigDecimal itemQuantity;
    /**
     * 增值税
     */
    private BigDecimal addTaxRate;
    /**
     * 消费税
     */
    private BigDecimal exciseRate;
    /**
     * 关税
     */
    private BigDecimal tariffRate;
    /**
     * 规格名称
     */
    private String specNme;
    /**
     * 规格数
     */
    private Integer specNum;
    /**
     * 商品单价
     */
    private BigDecimal itemPrice;
    /**
     * 商品总价
     */
    private BigDecimal itemTotal;
    /**
     * 总税款
     */
    private BigDecimal itemTotalTax;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSupplyNo() {
        return itemSupplyNo;
    }

    public void setItemSupplyNo(String itemSupplyNo) {
        this.itemSupplyNo = itemSupplyNo;
    }

    public BigDecimal getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(BigDecimal itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public BigDecimal getAddTaxRate() {
        return addTaxRate;
    }

    public void setAddTaxRate(BigDecimal addTaxRate) {
        this.addTaxRate = addTaxRate;
    }

    public BigDecimal getExciseRate() {
        return exciseRate;
    }

    public void setExciseRate(BigDecimal exciseRate) {
        this.exciseRate = exciseRate;
    }

    public BigDecimal getTariffRate() {
        return tariffRate;
    }

    public void setTariffRate(BigDecimal tariffRate) {
        this.tariffRate = tariffRate;
    }

    public String getSpecNme() {
        return specNme;
    }

    public void setSpecNme(String specNme) {
        this.specNme = specNme;
    }

    public Integer getSpecNum() {
        return specNum;
    }

    public void setSpecNum(Integer specNum) {
        this.specNum = specNum;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public BigDecimal getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(BigDecimal itemTotal) {
        this.itemTotal = itemTotal;
    }

    public BigDecimal getItemTotalTax() {
        return itemTotalTax;
    }

    public void setItemTotalTax(BigDecimal itemTotalTax) {
        this.itemTotalTax = itemTotalTax;
    }
}
