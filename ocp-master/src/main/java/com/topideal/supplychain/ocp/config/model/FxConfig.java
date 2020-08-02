package com.topideal.supplychain.ocp.config.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.enumeration.EnableOrDisableEnum;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.CustomsCodeEnum;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FxConfig extends BaseEntity {

    /**
     * ids串
     */
    private String ids;

    /**
     * 转单配置编码
     */
    private String code;

    /**
     * 平台电商企业编码
     */
    private String electricCode;

    /**
     * 企业名称
     */
    private String electricName;

    /**
     * 平台电商平台编码
     */
    private String platformCode;

    /**
     * 平台名称
     */
    private String platformName;

    /**
     * 平台物流企业编码
     */
    private String logisticsCode;

    /**
     * 物流名称
     */
    private String logisticsName;

    /**
     * 下发转配置，json
     */
    private String configuration;

    /**
     * 申报关区，码表
     */
    private CustomsCodeEnum customsCode;

    /**
     * 业务模式，码表
     */
    private BusiModeEnum businessMode;

    /**
     * 转发系统代码
     */
    private ForwardSystemEnum forwardSystem;

    /**
     * 状态：1-启用，0-停用
     */
    private EnableOrDisableEnum status;

    /**
     * 备注
     */
    private String remark;


    /**
     * 店铺编码
     */
    private String storeCode;

    /**
     * 店铺名称
     */
    private String storeName;



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getElectricCode() {
        return electricCode;
    }

    public void setElectricCode(String electricCode) {
        this.electricCode = electricCode == null ? null : electricCode.trim();
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode == null ? null : platformCode.trim();
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode == null ? null : logisticsCode.trim();
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration == null ? null : configuration.trim();
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

    public CustomsCodeEnum getCustomsCode() {
        return customsCode;
    }

    public void setCustomsCode(CustomsCodeEnum customsCode) {
        this.customsCode = customsCode;
    }

    public BusiModeEnum getBusinessMode() {
        return businessMode;
    }

    public void setBusinessMode(BusiModeEnum businessMode) {
        this.businessMode = businessMode;
    }

    public ForwardSystemEnum getForwardSystem() {
        return forwardSystem;
    }

    public void setForwardSystem(ForwardSystemEnum forwardSystem) {
        this.forwardSystem = forwardSystem;
    }

    public EnableOrDisableEnum getStatus() {
        return status;
    }

    public void setStatus(EnableOrDisableEnum status) {
        this.status = status;
    }

    public String getElectricName() {
        return electricName;
    }

    public void setElectricName(String electricName) {
        this.electricName = electricName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}