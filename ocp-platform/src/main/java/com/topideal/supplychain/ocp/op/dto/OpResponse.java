




package com.topideal.supplychain.ocp.op.dto;

import java.io.Serializable;

/**
 * @ClassName OpResponse
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/9 14:14
 * @Version 1.0
 **/
public class OpResponse implements Serializable {

    private String orderId;

    private Integer status;

    private String notes;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}