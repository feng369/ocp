package com.topideal.supplychain.ocp.master.dto;

import com.topideal.supplychain.dict.DataDict;
import com.topideal.supplychain.ocp.master.model.Store;

import java.util.List;

/**
 * @author klw
 * @date 2019/11/18 11:41
 * @description: 店铺信息dto
 */
public class StoreDto extends Store {
    /**平台名称*/
    private String platformName;
    /**商家名称*/
    private String merchantName;
    @DataDict(dict = "business.mode",source = "busiType", split = ",")
    private String busiModeName;

    private List<String> platformCodeList;

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

    public String getBusiModeName() {
        return busiModeName;
    }

    public void setBusiModeName(String busiModeName) {
        this.busiModeName = busiModeName;
    }

    public List<String> getPlatformCodeList() {
        return platformCodeList;
    }

    public void setPlatformCodeList(List<String> platformCodeList) {
        this.platformCodeList = platformCodeList;
    }
}
