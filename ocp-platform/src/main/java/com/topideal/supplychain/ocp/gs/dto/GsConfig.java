package com.topideal.supplychain.ocp.gs.dto;

import java.util.Date;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2020 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.gs.dto</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2020-01-07 17:01</p>
 *
 * @version 1.0
 */
public class GsConfig {

    /**
     * 调用者身份
     */
    private String appKey;

    /**
     * secret
     */
    private String appSecret;
    /**
     * 手工抓单开始时间
     */
    private Date manualStartTime;
    /**
     * 定时任务抓单时间间隔
     */
    //private Integer intervals;


    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Date getManualStartTime() {
        return manualStartTime;
    }

    public void setManualStartTime(Date manualStartTime) {
        this.manualStartTime = manualStartTime;
    }
}
