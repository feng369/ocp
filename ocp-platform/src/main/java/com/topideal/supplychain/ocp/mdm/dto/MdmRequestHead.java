package com.topideal.supplychain.ocp.mdm.dto;

import java.io.Serializable;

/**
 * @description: 主数据接口头信息
 * @author: syq
 * @create: 2019-12-09 15:08
 **/
public class MdmRequestHead  {

    private String messageID; //报文编号
    private String messageType; //报文类型
    private String traceId; //日志跟踪ID
    private String senderID; //发送者
    private String receiverID; //接受者
    private String sendTime; //发送时间
    private String version; //版本

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
