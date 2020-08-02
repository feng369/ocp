package com.topideal.supplychain.ocp.amway.dto;

public class CrossBorder {
    private String	importMode;		//进口模式
    private String	customs;			//关区
    private String	taxFee;			//税费
    private String	customerID;		//订购人身份证号
    private String	country;			//起运国
    private String	transportMethod;	//运输方式
    private String	shipName;			//发货人姓名
    private String	shipAddress;		//发货人地址
    private String	shipTel;			//发货人电话

    public String getImportMode()
    {
        return importMode;
    }

    public void setImportMode(String importMode)
    {
        this.importMode = importMode;
    }

    public String getCustoms()
    {
        return customs;
    }

    public void setCustoms(String customs)
    {
        this.customs = customs;
    }

    public String getTaxFee()
    {
        return taxFee;
    }

    public void setTaxFee(String taxFee)
    {
        this.taxFee = taxFee;
    }

    public String getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(String customerID)
    {
        this.customerID = customerID;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getTransportMethod()
    {
        return transportMethod;
    }

    public void setTransportMethod(String transportMethod)
    {
        this.transportMethod = transportMethod;
    }

    public String getShipName()
    {
        return shipName;
    }

    public void setShipName(String shipName)
    {
        this.shipName = shipName;
    }

    public String getShipAddress()
    {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress)
    {
        this.shipAddress = shipAddress;
    }

    public String getShipTel()
    {
        return shipTel;
    }

    public void setShipTel(String shipTel)
    {
        this.shipTel = shipTel;
    }
}
