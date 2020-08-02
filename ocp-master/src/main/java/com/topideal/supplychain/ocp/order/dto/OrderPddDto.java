package com.topideal.supplychain.ocp.order.dto;

import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.order.model.OrderPdd;

import java.util.Date;

/**
 * @author klw
 * @date 2019/12/19 16:25
 * @description:
 */
public class OrderPddDto extends OrderPdd {

    private String platformName;

    private String merchantName;

    private BusiModeEnum busiModeEnum;

    private String createBeginTime;

    private String createEndTime;

    private Long configId;

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public BusiModeEnum getBusiModeEnum() {
        return busiModeEnum;
    }

    public void setBusiModeEnum(BusiModeEnum busiModeEnum) {
        this.busiModeEnum = busiModeEnum;
    }

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public String getCreateBeginTime() {
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
}
