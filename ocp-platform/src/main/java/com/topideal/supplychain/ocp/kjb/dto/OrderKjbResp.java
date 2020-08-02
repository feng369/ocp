package com.topideal.supplychain.ocp.kjb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @author wanzhaozhang
 * @date 2020/1/2
 * @description
 **/
public class OrderKjbResp implements Serializable {
    //小米订单号
    private String orderId;
    //接收状态 1：成功，2：失败
    private Long status;
    //如失败，则放错误信息
    private String notes;

    @JsonIgnore
    public Boolean isSuccess(){
        return status == 1L;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
