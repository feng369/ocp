package com.topideal.supplychain.ocp.hipac.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 海拍客接单请求报文
 *
 * @author xuxiaoyan
 * @date 2019-12-18 16:33
 */
@XmlRootElement(name = "HipacPush")
public class HipacReceiveRequest implements Serializable{

    private static final long serialVersionUID = -1008405783651012832L;

    private HipacHead head;

    private HipacBody body;

    public HipacHead getHead() {
        return head;
    }

    @XmlElement(name = "Head")
    public void setHead(HipacHead head) {
        this.head = head;
    }

    public HipacBody getBody() {
        return body;
    }

    @XmlElement(name = "Body")
    public void setBody(HipacBody body) {
        this.body = body;
    }
}
