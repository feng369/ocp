package com.topideal.supplychain.ocp.amway.dto;

import java.util.List;

public class OrderHeader
{
    private String						warehouseId;			//对应的出货仓库代码
    private String						businessType;			//推送代码
    private String						versionId;				//接口版本号
    private String						groupOrderNumber;		//团单编号
    private String						isGroupOrderFlag;		//只会传
    private String						orderNumber;			//订单号
    private String						orderType;				//订单类型
    private String						distributorNumber;		//办理人编号
    private String						distributorName;		//办理人姓名
    private String						distributorSpouseName;	//配偶名称
    private String						serviceName;			//服务场所名称
    private String						orderDstMobile;		//办理人手机号
    private String						orderDstLandNo;		//办理人固定电话
    private String						shipToDst;				//收货人编号
    private String						consigneeName;			//收货人姓名
    private String						shipToAddress1;		//收货地址省份
    private String						shipToAddress2;		//收货地址城市
    private String						shipToAddress3;		//收货地址区域
    private String						shipToAddress4;		//收货地址街道
    private String						shipToAddress5;		//地址备用字段
    private String						postCode;				//收货地址邮编
    private String						consigneeMobile;		//收货人手机号码
    private String						consigneeLandNo;		//收货人座机号码
    private String						saleDate;				//下单时间
    private String						expectedDeliveryPeriod; //期望送货时间
    private String						deliveryFee;			//运费
    private String						deliveryInsuranceFee;	//保价费
    private String						orderStatus;			//订单状态
    private String						orderBv;				//订单BV
    private String						orderPv;				//订单PV
    private String						orderDiscount;			//订单折扣金额
    private String						couponBv;
    private String						voucherBv;
    private String						promotionBv;
    private String						couponPv;
    private String						voucherPv;
    private String						promotionPv;
    private String						giftPointGenerated;	//获得积分
    private String						giftPointUsed;			//使用积分
    private String						giftPointLeft;			//累计积分
    private String						posCode;				//pos编号
    private String						staffNumber;			//人员编号
    private String						orderOriginalPrice;	//订单金额
    private String						rsvst1;				//备用字段
    private String						rsvst2;
    private String						rsvst3;
    private String						rsvst4;
    private String						rsvst5;
    private String						rsvdc1;
    private String						rsvdc2;
    private String						rsvdc3;
    private String						rsvdc4;
    private String						rsvdc5;
    private List<OrderLine>             orderLine;				//产品明细
    private List<PrintDescriptionPojo>	printDescription;		//打印描述
    // OCP-205，接口新增systemFlag
    private String 						systemFlag;				//系统标志
    // OCP-212，接口新增shipToAddress3_1
    private String 						shipToAddress3_1;		//收货人地址乡镇
    private String 						shipToAddressType;
    private String 						shipToCombinedId;

    public String getSystemFlag()
    {
        return systemFlag;
    }

    public void setSystemFlag(String systemFlag)
    {
        this.systemFlag = systemFlag;
    }

    public String getWarehouseId()
    {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId)
    {
        this.warehouseId = warehouseId;
    }

    public String getBusinessType()
    {
        return businessType;
    }

    public void setBusinessType(String businessType)
    {
        this.businessType = businessType;
    }

    public String getVersionId()
    {
        return versionId;
    }

    public void setVersionId(String versionId)
    {
        this.versionId = versionId;
    }

    public String getGroupOrderNumber()
    {
        return groupOrderNumber;
    }

    public void setGroupOrderNumber(String groupOrderNumber)
    {
        this.groupOrderNumber = groupOrderNumber;
    }

    public String getIsGroupOrderFlag()
    {
        return isGroupOrderFlag;
    }

    public void setIsGroupOrderFlag(String isGroupOrderFlag)
    {
        this.isGroupOrderFlag = isGroupOrderFlag;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderType()
    {
        return orderType;
    }

    public void setOrderType(String orderType)
    {
        this.orderType = orderType;
    }

    public String getDistributorNumber()
    {
        return distributorNumber;
    }

    public void setDistributorNumber(String distributorNumber)
    {
        this.distributorNumber = distributorNumber;
    }

    public String getDistributorName()
    {
        return distributorName;
    }

    public void setDistributorName(String distributorName)
    {
        this.distributorName = distributorName;
    }

    public String getDistributorSpouseName()
    {
        return distributorSpouseName;
    }

    public void setDistributorSpouseName(String distributorSpouseName)
    {
        this.distributorSpouseName = distributorSpouseName;
    }

    public String getServiceName()
    {
        return serviceName;
    }

    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getOrderDstMobile()
    {
        return orderDstMobile;
    }

    public void setOrderDstMobile(String orderDstMobile)
    {
        this.orderDstMobile = orderDstMobile;
    }

    public String getOrderDstLandNo()
    {
        return orderDstLandNo;
    }

    public void setOrderDstLandNo(String orderDstLandNo)
    {
        this.orderDstLandNo = orderDstLandNo;
    }

    public String getShipToDst()
    {
        return shipToDst;
    }

    public void setShipToDst(String shipToDst)
    {
        this.shipToDst = shipToDst;
    }

    public String getConsigneeName()
    {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName)
    {
        this.consigneeName = consigneeName;
    }

    public String getShipToAddress1()
    {
        return shipToAddress1;
    }

    public void setShipToAddress1(String shipToAddress1)
    {
        this.shipToAddress1 = shipToAddress1;
    }

    public String getShipToAddress2()
    {
        return shipToAddress2;
    }

    public void setShipToAddress2(String shipToAddress2)
    {
        this.shipToAddress2 = shipToAddress2;
    }

    public String getShipToAddress3()
    {
        return shipToAddress3;
    }

    public void setShipToAddress3(String shipToAddress3)
    {
        this.shipToAddress3 = shipToAddress3;
    }

    public String getShipToAddress4()
    {
        return shipToAddress4;
    }

    public void setShipToAddress4(String shipToAddress4)
    {
        this.shipToAddress4 = shipToAddress4;
    }

    public String getShipToAddress5()
    {
        return shipToAddress5;
    }

    public void setShipToAddress5(String shipToAddress5)
    {
        this.shipToAddress5 = shipToAddress5;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    public String getConsigneeMobile()
    {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile)
    {
        this.consigneeMobile = consigneeMobile;
    }

    public String getConsigneeLandNo()
    {
        return consigneeLandNo;
    }

    public void setConsigneeLandNo(String consigneeLandNo)
    {
        this.consigneeLandNo = consigneeLandNo;
    }

    public String getSaleDate()
    {
        return saleDate;
    }

    public void setSaleDate(String saleDate)
    {
        this.saleDate = saleDate;
    }

    public String getExpectedDeliveryPeriod()
    {
        return expectedDeliveryPeriod;
    }

    public void setExpectedDeliveryPeriod(String expectedDeliveryPeriod)
    {
        this.expectedDeliveryPeriod = expectedDeliveryPeriod;
    }

    public String getDeliveryFee()
    {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee)
    {
        this.deliveryFee = deliveryFee;
    }

    public String getDeliveryInsuranceFee()
    {
        return deliveryInsuranceFee;
    }

    public void setDeliveryInsuranceFee(String deliveryInsuranceFee)
    {
        this.deliveryInsuranceFee = deliveryInsuranceFee;
    }

    public String getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public String getOrderBv()
    {
        return orderBv;
    }

    public void setOrderBv(String orderBv)
    {
        this.orderBv = orderBv;
    }

    public String getOrderPv()
    {
        return orderPv;
    }

    public void setOrderPv(String orderPv)
    {
        this.orderPv = orderPv;
    }

    public String getOrderDiscount()
    {
        return orderDiscount;
    }

    public void setOrderDiscount(String orderDiscount)
    {
        this.orderDiscount = orderDiscount;
    }

    public String getCouponBv()
    {
        return couponBv;
    }

    public void setCouponBv(String couponBv)
    {
        this.couponBv = couponBv;
    }

    public String getVoucherBv()
    {
        return voucherBv;
    }

    public void setVoucherBv(String voucherBv)
    {
        this.voucherBv = voucherBv;
    }

    public String getPromotionBv()
    {
        return promotionBv;
    }

    public void setPromotionBv(String promotionBv)
    {
        this.promotionBv = promotionBv;
    }

    public String getCouponPv()
    {
        return couponPv;
    }

    public void setCouponPv(String couponPv)
    {
        this.couponPv = couponPv;
    }

    public String getVoucherPv()
    {
        return voucherPv;
    }

    public void setVoucherPv(String voucherPv)
    {
        this.voucherPv = voucherPv;
    }

    public String getPromotionPv()
    {
        return promotionPv;
    }

    public void setPromotionPv(String promotionPv)
    {
        this.promotionPv = promotionPv;
    }

    public String getGiftPointGenerated()
    {
        return giftPointGenerated;
    }

    public void setGiftPointGenerated(String giftPointGenerated)
    {
        this.giftPointGenerated = giftPointGenerated;
    }

    public String getGiftPointUsed()
    {
        return giftPointUsed;
    }

    public void setGiftPointUsed(String giftPointUsed)
    {
        this.giftPointUsed = giftPointUsed;
    }

    public String getGiftPointLeft()
    {
        return giftPointLeft;
    }

    public void setGiftPointLeft(String giftPointLeft)
    {
        this.giftPointLeft = giftPointLeft;
    }

    public String getPosCode()
    {
        return posCode;
    }

    public void setPosCode(String posCode)
    {
        this.posCode = posCode;
    }

    public String getStaffNumber()
    {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber)
    {
        this.staffNumber = staffNumber;
    }

    public String getOrderOriginalPrice()
    {
        return orderOriginalPrice;
    }

    public void setOrderOriginalPrice(String orderOriginalPrice)
    {
        this.orderOriginalPrice = orderOriginalPrice;
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

    public String getRsvst4()
    {
        return rsvst4;
    }

    public void setRsvst4(String rsvst4)
    {
        this.rsvst4 = rsvst4;
    }

    public String getRsvst5()
    {
        return rsvst5;
    }

    public void setRsvst5(String rsvst5)
    {
        this.rsvst5 = rsvst5;
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

    public String getRsvdc4()
    {
        return rsvdc4;
    }

    public void setRsvdc4(String rsvdc4)
    {
        this.rsvdc4 = rsvdc4;
    }

    public String getRsvdc5()
    {
        return rsvdc5;
    }

    public void setRsvdc5(String rsvdc5)
    {
        this.rsvdc5 = rsvdc5;
    }

    public List<OrderLine> getOrderLine()
    {
        return orderLine;
    }

    public void setOrderLine(List<OrderLine> orderLine)
    {
        this.orderLine = orderLine;
    }

    public List<PrintDescriptionPojo> getPrintDescription()
    {
        return printDescription;
    }

    public void setPrintDescription(List<PrintDescriptionPojo> printDescription)
    {
        this.printDescription = printDescription;
    }

    public String getShipToAddress3_1()
    {
        return shipToAddress3_1;
    }

    public void setShipToAddress3_1(String shipToAddress3_1)
    {
        this.shipToAddress3_1 = shipToAddress3_1;
    }

    public String getShipToAddressType()
    {
        return shipToAddressType;
    }

    public String getShipToCombinedId()
    {
        return shipToCombinedId;
    }

    public void setShipToAddressType(String shipToAddressType)
    {
        this.shipToAddressType = shipToAddressType;
    }

    public void setShipToCombinedId(String shipToCombinedId)
    {
        this.shipToCombinedId = shipToCombinedId;
    }

}