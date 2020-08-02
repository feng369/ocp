package com.topideal.supplychain.ocp.daling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 达令家抓取详情回执
 *
 * @author xuxiaoyan
 * @date 2019-12-16 09:57
 */
public class DalingOrderResponseDto implements Serializable{

    private static final long serialVersionUID = 8712247253203527852L;

    /**
     * 版本
     */
    private String version;

    /**
     * 客户标识
     */
    private String clientFrom;

    private String timestamp;

    /**
     * get请求的原始URL
     */
    @JsonProperty("get_url")
    private String getUrl;

    private Integer status;

    private String errmsg;

    /**
     * 接口接到请求到返回数据花销的毫秒数
     */
    private Integer elapsed;

    /**
     * 返回tomcat服务器IP D段值
     */
    @JsonProperty("acc_point")
    private String accPoint;

    /**
     * 用于表示本次会话的唯一ID
     */
    @JsonProperty("track_id")
    private String trackId;

    /**
     * 安全限制
     */
    @JsonProperty("secur_type")
    private Integer securType;

    /**
     * 接口返回内容
     */
    private DalingOrderData data;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClientFrom() {
        return clientFrom;
    }

    public void setClientFrom(String clientFrom) {
        this.clientFrom = clientFrom;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getGetUrl() {
        return getUrl;
    }

    public void setGetUrl(String getUrl) {
        this.getUrl = getUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Integer getElapsed() {
        return elapsed;
    }

    public void setElapsed(Integer elapsed) {
        this.elapsed = elapsed;
    }

    public String getAccPoint() {
        return accPoint;
    }

    public void setAccPoint(String accPoint) {
        this.accPoint = accPoint;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public Integer getSecurType() {
        return securType;
    }

    public void setSecurType(Integer securType) {
        this.securType = securType;
    }

    public DalingOrderData getData() {
        return data;
    }

    public void setData(DalingOrderData data) {
        this.data = data;
    }
}
