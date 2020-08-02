package com.topideal.supplychain.ocp.order.model;

import com.topideal.supplychain.common.model.BaseEntity;
/**
 * @author wanzhaozhang
 * @date 2019/12/3
 * @description id_card
 **/
public class OrderYmatouIndentity extends BaseEntity {

    /**
     * 订单主表id
     */
    private Long msgOrderId;

    /**
     * 收件人证件类型 1:身份证, 2:护照
     */
    private Long receiverIdType;

    /**
     * 收件人证件号
     */
    private String receiverIdNo;

    public Long getMsgOrderId() {
        return msgOrderId;
    }

    public void setMsgOrderId(Long msgOrderId) {
        this.msgOrderId = msgOrderId;
    }

    public Long getReceiverIdType() {
        return receiverIdType;
    }

    public void setReceiverIdType(Long receiverIdType) {
        this.receiverIdType = receiverIdType;
    }

    public String getReceiverIdNo() {
        return receiverIdNo;
    }

    public void setReceiverIdNo(String receiverIdNo) {
        this.receiverIdNo = receiverIdNo;
    }
}