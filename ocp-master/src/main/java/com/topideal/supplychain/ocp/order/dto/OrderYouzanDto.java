package com.topideal.supplychain.ocp.order.dto;

import com.topideal.supplychain.enumeration.YesOrNoEnum;
import com.topideal.supplychain.ocp.order.model.OrderYouzanItem;
import com.topideal.supplychain.ocp.order.model.OrderYouzan;
import com.topideal.supplychain.ocp.order.model.OrderYouzanChild;
import com.topideal.supplychain.ocp.order.model.OrderYouzanEx;
import com.topideal.supplychain.ocp.order.model.OrderYouzanPayments;
import com.topideal.supplychain.ocp.order.model.OrderYouzanTags;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;


public class OrderYouzanDto extends OrderYouzan {

    private String fxAppId;

    private String idCardName;

    private YesOrNoEnum isPurchaseOrder;

    private String electricName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fromCreateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date toCreateTime;

    private List<OrderYouzanItem> orderYouzanItemList;

    private OrderYouzanTags orderYouzanTags;

    private OrderYouzanEx orderYouzanEx;

    private List<OrderYouzanChild> orderYouzanChildList;

    private List<OrderYouzanPayments> orderYouzanPaymentsList;

    private List<OrderYouzanItem> orderItemKjb;

    public String getFxAppId() {
        return fxAppId;
    }

    public void setFxAppId(String fxAppId) {
        this.fxAppId = fxAppId;
    }

    public String getIdCardName() {
        return idCardName;
    }

    public void setIdCardName(String idCardName) {
        this.idCardName = idCardName;
    }

    public YesOrNoEnum getIsPurchaseOrder() {
        return isPurchaseOrder;
    }

    public void setIsPurchaseOrder(YesOrNoEnum isPurchaseOrder) {
        this.isPurchaseOrder = isPurchaseOrder;
    }

    public String getElectricName() {
        return electricName;
    }

    public void setElectricName(String electricName) {
        this.electricName = electricName;
    }

    public Date getFromCreateTime() {
        return fromCreateTime;
    }

    public void setFromCreateTime(Date fromCreateTime) {
        this.fromCreateTime = fromCreateTime;
    }

    public Date getToCreateTime() {
        return toCreateTime;
    }

    public void setToCreateTime(Date toCreateTime) {
        this.toCreateTime = toCreateTime;
    }

    public List<OrderYouzanItem> getOrderYouzanItemList() {
        return orderYouzanItemList;
    }

    public void setOrderYouzanItemList(
            List<OrderYouzanItem> orderYouzanItemList) {
        this.orderYouzanItemList = orderYouzanItemList;
    }

    public OrderYouzanTags getOrderYouzanTags() {
        return orderYouzanTags;
    }

    public void setOrderYouzanTags(OrderYouzanTags orderYouzanTags) {
        this.orderYouzanTags = orderYouzanTags;
    }

    public OrderYouzanEx getOrderYouzanEx() {
        return orderYouzanEx;
    }

    public void setOrderYouzanEx(OrderYouzanEx orderYouzanEx) {
        this.orderYouzanEx = orderYouzanEx;
    }

    public List<OrderYouzanChild> getOrderYouzanChildList() {
        return orderYouzanChildList;
    }

    public void setOrderYouzanChildList(
            List<OrderYouzanChild> orderYouzanChildList) {
        this.orderYouzanChildList = orderYouzanChildList;
    }

    public List<OrderYouzanPayments> getOrderYouzanPaymentsList() {
        return orderYouzanPaymentsList;
    }

    public void setOrderYouzanPaymentsList(
            List<OrderYouzanPayments> orderYouzanPaymentsList) {
        this.orderYouzanPaymentsList = orderYouzanPaymentsList;
    }

    public List<OrderYouzanItem> getOrderItemKjb() {
        return orderItemKjb;
    }

    public void setOrderItemKjb(
            List<OrderYouzanItem> orderItemKjb) {
        this.orderItemKjb = orderItemKjb;
    }
}