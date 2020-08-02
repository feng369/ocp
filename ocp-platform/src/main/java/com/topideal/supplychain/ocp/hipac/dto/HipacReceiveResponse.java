package com.topideal.supplychain.ocp.hipac.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 海拍客接单回执
 *
 * @author xuxiaoyan
 * @date 2019-12-18 16:34
 */
@XmlRootElement(name = "HipacPush")
public class HipacReceiveResponse implements Serializable{

    private static final long serialVersionUID = 3913647704369825283L;

    private HipacResponseHead head;

    private HipacResponseBody body;

    public HipacResponseHead getHead() {
        return head;
    }

    @XmlElement(name = "Head")
    public void setHead(HipacResponseHead head) {
        this.head = head;
    }

    public HipacResponseBody getBody() {
        return body;
    }

    @XmlElement(name = "Body")
    public void setBody(HipacResponseBody body) {
        this.body = body;
    }
}
