package com.topideal.supplychain.ocp.amway.dto;

public class PaymentLine {
    private String	paymentMethod;		//付款方式
    private String	paymentLineAmount;	//付款金额
    private String	paymentMethodName;	//付款名称

    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentLineAmount()
    {
        return paymentLineAmount;
    }

    public void setPaymentLineAmount(String paymentLineAmount)
    {
        this.paymentLineAmount = paymentLineAmount;
    }

    public String getPaymentMethodName()
    {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName)
    {
        this.paymentMethodName = paymentMethodName;
    }
}
