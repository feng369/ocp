package com.topideal.supplychain.ocp.jd.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName JdOrderResult
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/3/15 17:40
 * @Version 1.0
 **/
public class JdOrderResult implements Serializable {

    private JdHeader header;

    private List<String> body;

    public JdHeader getHeader() {
        return header;
    }

    public void setHeader(JdHeader header) {
        this.header = header;
    }

    public List<String> getBody() {
        return body;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }
}
