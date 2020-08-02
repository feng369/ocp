package com.topideal.supplychain.ocp.config.model;

import com.topideal.supplychain.common.model.BaseEntity;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 抓单配置
 */
public class CatchOrderConfig extends BaseEntity {

    //抓单id
    private String grabId;

    //手动录入的定时任务id
    private String code;

    //定时任务名称
    private String name;

    //平台编码
    private String platformCode;

    //平台名称
    private String platformName;

    //电商企业编码
    private String merchantCode;

    //电商企业名称
    private String merchantName;

    /*//定时任务cron表达式
    private String cron;*/

    /*//定时任务执行类
    private String executeClass;

    //单个抓单方法名
    private String singleMethod;

    //批量抓单方法名
    private String multipleMethod;

    //上次执行时间
    private Date lastRunTime;*/

    //上次查询结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastQueryTime;

    /*//默认查询开始时间
    private Date defaultQueryTime;*/

    //间隔时间
    private Integer intervalCount;

    /*//延时时间
    private Integer delay;*/

    //每页查询条数
    private Integer pageSize;

   /* //地址
    private String url;
*/
    private String remark;

    //店铺编码
    private String storeCode;

    //店铺id
    private Long storeId;

    //默认值配置
    private String defaultArguments;

    /*//定时任务配置json
    private String jobArguments;*/

    //平台参数 token appkey appsecret等等
    private String platformArguments;

    /*private String createBeginTime;
    private String createEndTime;
    private String updateBeginTime;
    private String updateEndTime;*/

    public String getGrabId() {
        return grabId;
    }

    public void setGrabId(String grabId) {
        this.grabId = grabId;
    }

    public String getPlatformArguments() {
        return platformArguments;
    }

    public void setPlatformArguments(String platformArguments) {
        this.platformArguments = platformArguments;
    }

    /*public String getJobArguments() {
        return jobArguments;
    }

    public void setJobArguments(String jobArguments) {
        this.jobArguments = jobArguments;
    }*/

    /*public String getCreateBeginTime() {
        return createBeginTime;
    }

    public void setCreateBeginTime(String createBeginTime) {
        this.createBeginTime = createBeginTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public String getUpdateBeginTime() {
        return updateBeginTime;
    }

    public void setUpdateBeginTime(String updateBeginTime) {
        this.updateBeginTime = updateBeginTime;
    }

    public String getUpdateEndTime() {
        return updateEndTime;
    }

    public void setUpdateEndTime(String updateEndTime) {
        this.updateEndTime = updateEndTime;
    }
*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode == null ? null : platformCode.trim();
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName == null ? null : platformName.trim();
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode == null ? null : merchantCode.trim();
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    /*public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron == null ? null : cron.trim();
    }

    public String getExecuteClass() {
        return executeClass;
    }

    public void setExecuteClass(String executeClass) {
        this.executeClass = executeClass == null ? null : executeClass.trim();
    }

    public String getSingleMethod() {
        return singleMethod;
    }

    public void setSingleMethod(String singleMethod) {
        this.singleMethod = singleMethod == null ? null : singleMethod.trim();
    }*/

    /*public String getMultipleMethod() {
        return multipleMethod;
    }

    public void setMultipleMethod(String multipleMethod) {
        this.multipleMethod = multipleMethod == null ? null : multipleMethod.trim();
    }

    public Date getLastRunTime() {
        return lastRunTime;
    }

    public void setLastRunTime(Date lastRunTime) {
        this.lastRunTime = lastRunTime;
    }*/

    public Date getLastQueryTime() {
        return lastQueryTime;
    }

    public void setLastQueryTime(Date lastQueryTime) {
        this.lastQueryTime = lastQueryTime;
    }

    /*public Date getDefaultQueryTime() {
        return defaultQueryTime;
    }

    public void setDefaultQueryTime(Date defaultQueryTime) {
        this.defaultQueryTime = defaultQueryTime;
    }*/

    public Integer getIntervalCount() {
        return intervalCount;
    }

    public void setIntervalCount(Integer intervalCount) {
        this.intervalCount = intervalCount;
    }

    /*public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }*/

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode == null ? null : storeCode.trim();
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaultArguments() {
        return defaultArguments;
    }

    public void setDefaultArguments(String defaultArguments) {
        this.defaultArguments = defaultArguments;
    }
}