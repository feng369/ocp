package com.topideal.supplychain.ocp.hipac.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author xuxiaoyan
 * @date 2019-12-18 18:37
 */
@XmlRootElement(name = "Body")
public class HipacResponseBody {

    private String bizCode;

    private String bizMsg;

    private String orderNum;

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizMsg() {
        return bizMsg;
    }

    public void setBizMsg(String bizMsg) {
        this.bizMsg = bizMsg;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
