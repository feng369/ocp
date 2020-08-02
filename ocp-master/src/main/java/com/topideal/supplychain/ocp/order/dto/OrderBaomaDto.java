package com.topideal.supplychain.ocp.order.dto;

import com.topideal.supplychain.ocp.order.model.OrderBaoma;

/**
 * @author wanzhaozhang
 * @date 2019/12/16
 * @description
 **/
public class OrderBaomaDto extends OrderBaoma {
    /**
     * 下发Ofc需要的字段 （下发配置）
     */
    private Long orderType;
    private Long orderSource;
    private String storeCodeOfc;

    /**
     * 下发OP需要的字段 （下发配置）
     */

    //配置的业务模式
    private String configMode;
    // 配置的关区
    private String configCustoms;

    public Long getOrderType() {
        return orderType;
    }

    public void setOrderType(Long orderType) {
        this.orderType = orderType;
    }

    public Long getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Long orderSource) {
        this.orderSource = orderSource;
    }

    public String getStoreCodeOfc() {
        return storeCodeOfc;
    }

    public void setStoreCodeOfc(String storeCodeOfc) {
        this.storeCodeOfc = storeCodeOfc;
    }

    public String getConfigMode() {
        return configMode;
    }

    public void setConfigMode(String configMode) {
        this.configMode = configMode;
    }

    public String getConfigCustoms() {
        return configCustoms;
    }

    public void setConfigCustoms(String configCustoms) {
        this.configCustoms = configCustoms;
    }
}
