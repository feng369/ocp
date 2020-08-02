package com.topideal.supplychain.ocp.amway.dto;

import java.io.Serializable;

public class OCPAmwayResponsePojo implements Serializable {
    private String	resultCode;//0:成功,1:失败
    private String	messageID;
    private String	sign;
    private String	errorMessage;

    public String getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(String resultCode)
    {
        this.resultCode = resultCode;
    }

    public String getMessageID()
    {
        return messageID;
    }

    public void setMessageID(String messageID)
    {
        this.messageID = messageID;
    }

    public String getSign()
    {
        return sign;
    }

    public void setSign(String sign)
    {
        this.sign = sign;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}
