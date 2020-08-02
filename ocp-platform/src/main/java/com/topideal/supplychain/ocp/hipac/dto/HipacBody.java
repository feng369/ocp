package com.topideal.supplychain.ocp.hipac.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author xuxiaoyan
 * @date 2019-12-18 17:10
 */
@XmlRootElement(name = "Body")
public class HipacBody {

    private HipacSupplier hipacSupplier;

    private HipacOrder hipacOrder;

    private HipacPayInfo hipacPayInfo;

    private HipacCustomer hipacCustomer;

    @XmlElementWrapper(name="OrderItemList")
    @XmlElement(name="OrderItem")
    private List<HipacOrderItem> orderItems;

    public HipacSupplier getHipacSupplier() {
        return hipacSupplier;
    }

    @XmlElement(name = "Supplier")
    public void setHipacSupplier(HipacSupplier hipacSupplier) {
        this.hipacSupplier = hipacSupplier;
    }

    public HipacOrder getHipacOrder() {
        return hipacOrder;
    }

    @XmlElement(name = "Order")
    public void setHipacOrder(HipacOrder hipacOrder) {
        this.hipacOrder = hipacOrder;
    }

    public HipacPayInfo getHipacPayInfo() {
        return hipacPayInfo;
    }

    @XmlElement(name = "PayInfo")
    public void setHipacPayInfo(HipacPayInfo hipacPayInfo) {
        this.hipacPayInfo = hipacPayInfo;
    }

    public HipacCustomer getHipacCustomer() {
        return hipacCustomer;
    }

    @XmlElement(name = "Customer")
    public void setHipacCustomer(HipacCustomer hipacCustomer) {
        this.hipacCustomer = hipacCustomer;
    }

    @XmlTransient
    public List<HipacOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(
            List<HipacOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
