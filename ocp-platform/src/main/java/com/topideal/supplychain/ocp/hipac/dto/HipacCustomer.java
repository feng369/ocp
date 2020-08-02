package com.topideal.supplychain.ocp.hipac.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xuxiaoyan
 * @date 2019-12-18 17:15
 */
@XmlRootElement(name = "Customer")
public class HipacCustomer {

    private String custName;

    private String custIdNum;

    private String custPhone;

    private String custProvice;

    private String custCity;

    private String custArea;

    private String custAddress;

    private String shopName;

    private String shopNum;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopNum() {
        return shopNum;
    }

    public void setShopNum(String shopNum) {
        this.shopNum = shopNum;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustIdNum() {
        return custIdNum;
    }

    public void setCustIdNum(String custIdNum) {
        this.custIdNum = custIdNum;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustProvice() {
        return custProvice;
    }

    public void setCustProvice(String custProvice) {
        this.custProvice = custProvice;
    }

    public String getCustCity() {
        return custCity;
    }

    public void setCustCity(String custCity) {
        this.custCity = custCity;
    }

    public String getCustArea() {
        return custArea;
    }

    public void setCustArea(String custArea) {
        this.custArea = custArea;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }
}
