package com.topideal.supplychain.ocp.amway.dto;

import java.util.List;

public class PaymentHeader
{
    private String				paymentHeaderAmount;	//实际支付金额
    private String				paymentDateTime;		//付款时间
    private List<PaymentLine>	paymentLine;

    public String getPaymentHeaderAmount()
    {
        return paymentHeaderAmount;
    }

    public void setPaymentHeaderAmount(String paymentHeaderAmount)
    {
        this.paymentHeaderAmount = paymentHeaderAmount;
    }

    public String getPaymentDateTime()
    {
        return paymentDateTime;
    }

    public void setPaymentDateTime(String paymentDateTime)
    {
        this.paymentDateTime = paymentDateTime;
    }

    public List<PaymentLine> getPaymentLine()
    {
        return paymentLine;
    }

    public void setPaymentLine(List<PaymentLine> paymentLine)
    {
        this.paymentLine = paymentLine;
    }
}

