package com.topideal.supplychain.ocp.amway.dto;


public class MessageHeader
{
	private String	messageID;
	private String	sender;
	private String	receiver;
	private String	messageType;
	private String	sendTime;
	private String	version;
	private String	sign;

	public String getMessageID()
	{
		return messageID;
	}

	public void setMessageID(String messageID)
	{
		this.messageID = messageID;
	}

	public String getSender()
	{
		return sender;
	}

	public void setSender(String sender)
	{
		this.sender = sender;
	}

	public String getReceiver()
	{
		return receiver;
	}

	public void setReceiver(String receiver)
	{
		this.receiver = receiver;
	}

	public String getMessageType()
	{
		return messageType;
	}

	public void setMessageType(String messageType)
	{
		this.messageType = messageType;
	}

	public String getSendTime()
	{
		return sendTime;
	}

	public void setSendTime(String sendTime)
	{
		this.sendTime = sendTime;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getSign()
	{
		return sign;
	}

	public void setSign(String sign)
	{
		this.sign = sign;
	}
}
