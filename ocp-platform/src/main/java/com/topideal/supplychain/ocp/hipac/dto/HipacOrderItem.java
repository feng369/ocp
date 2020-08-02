package com.topideal.supplychain.ocp.hipac.dto;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xuxiaoyan
 * @date 2019-12-18 17:16
 */
@XmlRootElement(name = "OrderItem")
public class HipacOrderItem {

    private String itemName;

    private String itemSupplyNo;

    private BigDecimal itemQuantity;

    private BigDecimal addTaxRate;

    private BigDecimal exciseRate;

    private BigDecimal tariffRate;

    private String specNme;

    private BigDecimal specNum;

    private BigDecimal itemPrice;

    private BigDecimal itemTotal;

    private BigDecimal itemTotalTax;

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

    public BigDecimal getSpecNum() {
        return specNum;
    }

    public void setSpecNum(BigDecimal specNum) {
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
