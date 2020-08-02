package com.topideal.supplychain.ocp.hipac.dto;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xuxiaoyan
 * @date 2019-12-18 17:15
 */
@XmlRootElement(name = "Order")
public class HipacOrder {

    private String orderNum;

    private String orderDate;

    private BigDecimal totalOrderAmount;

    private BigDecimal totalTaxAmount;

    private BigDecimal totalPayAmount;

    private BigDecimal logisticsAmount;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public BigDecimal getTotalTaxAmount() {
        return totalTaxAmount;
    }

    public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
        this.totalTaxAmount = totalTaxAmount;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public BigDecimal getLogisticsAmount() {
        return logisticsAmount;
    }

    public void setLogisticsAmount(BigDecimal logisticsAmount) {
        this.logisticsAmount = logisticsAmount;
    }
}
