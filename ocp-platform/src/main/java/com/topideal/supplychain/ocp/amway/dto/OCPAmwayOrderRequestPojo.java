package com.topideal.supplychain.ocp.amway.dto;

import java.io.Serializable;

public class OCPAmwayOrderRequestPojo implements Serializable
{
    private MessageHeader	messageHeader;
    private MessageDetail	messageDetail;

    public MessageHeader getMessageHeader()
    {
        return messageHeader;
    }

    public void setMessageHeader(MessageHeader messageHeader)
    {
        this.messageHeader = messageHeader;
    }

    public MessageDetail getMessageDetail()
    {
        return messageDetail;
    }

    public void setMessageDetail(MessageDetail messageDetail)
    {
        this.messageDetail = messageDetail;
    }
}
