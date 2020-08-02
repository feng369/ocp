package com.topideal.supplychain.ocp.amway.dto;

public class OrderLine {
    private String	itemSku;					//产品SKU编号
    private String	unitDp;					//产品单价
    private String	orderingItemName;			//产品名称
    private String	orderingItemNumber;		//产品下单编号
    private String	isReturn;					//是否可退货
    private String	orderQty;					//申报数量
    private String	rsvst1;					//备用字段
    private String	rsvst2;
    private String	rsvst3;
    private String	rsvdc1;
    private String	rsvdc2;
    private String	rsvdc3;
    private String	masterOrderingItemNumber;	//主产品编号
    private String	masterOrderQty;			//主产品数量
    private String	masterOrderingItemName;	//主产品名称
    private String	masterOrderingPrice;		//主产品价格
    // OCP-196，接口新增转发字段
    private String mixLogisticsCode;
    private String mixLogisticsName;

    public String getItemSku()
    {
        return itemSku;
    }

    public void setItemSku(String itemSku)
    {
        this.itemSku = itemSku;
    }

    public String getUnitDp()
    {
        return unitDp;
    }

    public void setUnitDp(String unitDp)
    {
        this.unitDp = unitDp;
    }

    public String getOrderingItemName()
    {
        return orderingItemName;
    }

    public void setOrderingItemName(String orderingItemName)
    {
        this.orderingItemName = orderingItemName;
    }

    public String getOrderingItemNumber()
    {
        return orderingItemNumber;
    }

    public void setOrderingItemNumber(String orderingItemNumber)
    {
        this.orderingItemNumber = orderingItemNumber;
    }

    public String getIsReturn()
    {
        return isReturn;
    }

    public void setIsReturn(String isReturn)
    {
        this.isReturn = isReturn;
    }

    public String getOrderQty()
    {
        return orderQty;
    }

    public void setOrderQty(String orderQty)
    {
        this.orderQty = orderQty;
    }

    public String getRsvst1()
    {
        return rsvst1;
    }

    public void setRsvst1(String rsvst1)
    {
        this.rsvst1 = rsvst1;
    }

    public String getRsvst2()
    {
        return rsvst2;
    }

    public void setRsvst2(String rsvst2)
    {
        this.rsvst2 = rsvst2;
    }

    public String getRsvst3()
    {
        return rsvst3;
    }

    public void setRsvst3(String rsvst3)
    {
        this.rsvst3 = rsvst3;
    }

    public String getRsvdc1()
    {
        return rsvdc1;
    }

    public void setRsvdc1(String rsvdc1)
    {
        this.rsvdc1 = rsvdc1;
    }

    public String getRsvdc2()
    {
        return rsvdc2;
    }

    public void setRsvdc2(String rsvdc2)
    {
        this.rsvdc2 = rsvdc2;
    }

    public String getRsvdc3()
    {
        return rsvdc3;
    }

    public void setRsvdc3(String rsvdc3)
    {
        this.rsvdc3 = rsvdc3;
    }

    public String getMasterOrderingItemNumber()
    {
        return masterOrderingItemNumber;
    }

    public void setMasterOrderingItemNumber(String masterOrderingItemNumber)
    {
        this.masterOrderingItemNumber = masterOrderingItemNumber;
    }

    public String getMasterOrderQty()
    {
        return masterOrderQty;
    }

    public void setMasterOrderQty(String masterOrderQty)
    {
        this.masterOrderQty = masterOrderQty;
    }

    public String getMasterOrderingItemName()
    {
        return masterOrderingItemName;
    }

    public void setMasterOrderingItemName(String masterOrderingItemName)
    {
        this.masterOrderingItemName = masterOrderingItemName;
    }

    public String getMasterOrderingPrice()
    {
        return masterOrderingPrice;
    }

    public void setMasterOrderingPrice(String masterOrderingPrice)
    {
        this.masterOrderingPrice = masterOrderingPrice;
    }

    public String getMixLogisticsCode()
    {
        return mixLogisticsCode;
    }

    public void setMixLogisticsCode(String mixLogisticsCode)
    {
        this.mixLogisticsCode = mixLogisticsCode;
    }

    public String getMixLogisticsName()
    {
        return mixLogisticsName;
    }

    public void setMixLogisticsName(String mixLogisticsName)
    {
        this.mixLogisticsName = mixLogisticsName;
    }
}
