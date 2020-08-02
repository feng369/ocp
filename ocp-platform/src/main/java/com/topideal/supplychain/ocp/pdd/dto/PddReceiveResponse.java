package com.topideal.supplychain.ocp.pdd.dto;

/**
 * @author klw
 * @date 2019/12/17 13:40
 * @description: 拼多多接单回执
 */
public class PddReceiveResponse {

    //订单ID
    private String orderId;
    //接收状态:1：成功，2：失败
    private String status;
    //如失败，则放错误信息
    private String notes;

    public PddReceiveResponse(String orderId, String status, String notes) {
        this.orderId = orderId;
        this.status = status;
        this.notes = notes;
    }

    public PddReceiveResponse() {
    }

    public static PddReceiveResponse buildFailure(String orderId, String notes){
        return new PddReceiveResponse(orderId, "2", notes);
    }
    public static PddReceiveResponse buildFailure(String notes){
        return new PddReceiveResponse(null, "2", notes);
    }

    public static PddReceiveResponse buildSuccess(String orderId, String notes){
        return new PddReceiveResponse(orderId, "1", notes);
    }

    public static PddReceiveResponse buildSuccess(String notes){
        return buildSuccess(null, null);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
