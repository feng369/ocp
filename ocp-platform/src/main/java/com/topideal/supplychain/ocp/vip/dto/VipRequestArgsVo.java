package com.topideal.supplychain.ocp.vip.dto;

import java.util.List;

/**
 * @author xiao yu qiang
 * @time 2019/5/28 10:27
 * @description 唯品企业参数配置
 * @table
 */
public class VipRequestArgsVo {

    //调用者身份
    private String appKey;

    //secrect
    private String appSecrect;

    //调用格式
    private String format;

    //服务名
    private String service;

    //响应回写方法名
    private String feedbackMethod;

    //抓单方法名
    private String orderMethod;

    //版本
    private String version;

    //参数
    private List<Args> argsList;

    public static class Args{

        //海关编号
        private String customsCode;

        //进出口类
        private String tradeMode;

        public String getCustomsCode() {
            return customsCode;
        }

        public void setCustomsCode(String customsCode) {
            this.customsCode = customsCode;
        }

        public String getTradeMode() {
            return tradeMode;
        }

        public void setTradeMode(String tradeMode) {
            this.tradeMode = tradeMode;
        }
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecrect() {
        return appSecrect;
    }

    public void setAppSecrect(String appSecrect) {
        this.appSecrect = appSecrect;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getFeedbackMethod() {
        return feedbackMethod;
    }

    public void setFeedbackMethod(String feedbackMethod) {
        this.feedbackMethod = feedbackMethod;
    }

    public String getOrderMethod() {
        return orderMethod;
    }

    public void setOrderMethod(String orderMethod) {
        this.orderMethod = orderMethod;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Args> getArgsList() {
        return argsList;
    }

    public void setArgsList(
        List<Args> argsList) {
        this.argsList = argsList;
    }

}
