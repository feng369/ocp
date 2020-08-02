package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
/**
 * @author wanzhaozhang
 * @date 2019/12/3
 * @description 物流
 **/
public class OrderYmatouDelivery extends BaseEntity {

    /**
     * 主订单号
     */
    private Long msgOrderId;

    /**
     * 平台物流公司标识
     */
    private String logisticsCompanyCode;

    /**
     * 物流面单号
     */
    private String trackingNumber;

    /**
     * 发货时间 yyyy-MM-dd HH:mm:ss
     */
    private String deliveryTime;

    /**
     * 物流类型 1:国际段 2:国内段
     */
    private Long logisticsType;


    public Long getMsgOrderId() {
        return msgOrderId;
    }

    public void setMsgOrderId(Long msgOrderId) {
        this.msgOrderId = msgOrderId;
    }

    public String getLogisticsCompanyCode() {
        return logisticsCompanyCode;
    }

    public void setLogisticsCompanyCode(String logisticsCompanyCode) {
        this.logisticsCompanyCode = logisticsCompanyCode;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Long getLogisticsType() {
        return logisticsType;
    }

    public void setLogisticsType(Long logisticsType) {
        this.logisticsType = logisticsType;
    }
}