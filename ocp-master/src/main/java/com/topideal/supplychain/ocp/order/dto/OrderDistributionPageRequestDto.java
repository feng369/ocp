package com.topideal.supplychain.ocp.order.dto;

import com.topideal.supplychain.ocp.order.model.OrderDistribution;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 分销订单页面查询dto
 *
 * @author xuxiaoyan
 * @date 2020-05-21 16:02
 */
public class OrderDistributionPageRequestDto extends OrderDistribution {

    /**
     * 系统创建时间（查询条件）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeEnd;

    /**
     * 下单时间（查询条件）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDateEnd;

    /**
     * 平台订单号 页面批量查询
     */
    private String orderIds;
    private List<String> orderIdList;

    /**
     * 运单号 页面批量查询
     */
    private String deliveryCodes;
    private List<String> deliveryCodeList;

    public String getDeliveryCodes() {
        return deliveryCodes;
    }

    public void setDeliveryCodes(String deliveryCodes) {
        this.deliveryCodes = deliveryCodes;
    }

    public List<String> getDeliveryCodeList() {
        return deliveryCodeList;
    }

    public void setDeliveryCodeList(List<String> deliveryCodeList) {
        this.deliveryCodeList = deliveryCodeList;
    }

    public String getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(String orderIds) {
        this.orderIds = orderIds;
    }

    public List<String> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<String> orderIdList) {
        this.orderIdList = orderIdList;
    }

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Date getOrderDateStart() {
        return orderDateStart;
    }

    public void setOrderDateStart(Date orderDateStart) {
        this.orderDateStart = orderDateStart;
    }

    public Date getOrderDateEnd() {
        return orderDateEnd;
    }

    public void setOrderDateEnd(Date orderDateEnd) {
        this.orderDateEnd = orderDateEnd;
    }
}
