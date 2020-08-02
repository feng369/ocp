package com.topideal.supplychain.ocp.jd.dto;

import java.io.Serializable;

/**
 * @ClassName JdHeader
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/2/26 15:52
 * @Version 1.0
 **/
public class JdHeader implements Serializable {

    private String resultMsg;

    private String resultStatus;

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }
}
