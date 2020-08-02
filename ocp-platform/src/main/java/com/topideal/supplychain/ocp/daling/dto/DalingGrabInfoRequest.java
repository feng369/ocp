package com.topideal.supplychain.ocp.daling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * 达令家抓取订单详情请求体
 *
 * @author xuxiaoyan
 * @date 2019-12-13 10:52
 */
public class DalingGrabInfoRequest implements Serializable {

    private static final long serialVersionUID = -1028348755367909276L;

    @JsonProperty("v")
    private String version;

    @JsonProperty("format")
    private String format;

    @JsonProperty("sign_method")
    private String signMethod;

    @JsonProperty("app_id")
    private String appId;

    @JsonProperty("timestamp")
    private String timeStamp;

    @JsonProperty("method")
    private String method;

    @JsonProperty("data")
    private DalingGrabInfoData data;

    @JsonProperty("sign")
    private String sign;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public DalingGrabInfoData getData() {
        return data;
    }

    public void setData(DalingGrabInfoData data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
