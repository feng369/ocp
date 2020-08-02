package com.topideal.supplychain.ocp.hipac.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xuxiaoyan
 * @date 2019-12-18 17:12
 */
@XmlRootElement(name = "Supplier")
public class HipacSupplier {

    private String supplierSenderID;

    public String getSupplierSenderID() {
        return supplierSenderID;
    }

    public void setSupplierSenderID(String supplierSenderID) {
        this.supplierSenderID = supplierSenderID;
    }
}
