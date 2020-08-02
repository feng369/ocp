package com.topideal.supplychain.ocp.config.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.enumeration.EnableOrDisableEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 标题：京东多渠道大订单配置
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.config.model
 * 作者：songping
 * 创建日期：2019/12/24 14:02
 *
 * @version 1.0
 */
public class BigJdChannelConfig extends BaseEntity {

    /**
     * ids集合
     */
    private List<Long> ids;

    /**
     * 配置编码
     */
    private String code;

    /**
     * 电商企业
     */
    private String ebcCode;

    /**
     * 电商平台
     */
    private String ebpCode;

    /**
     * 物流企业
     */
    private String logisticsCode;

    /**
     * 第三方平台编码
     */
    private String platformCode;

    /**
     * 店铺编码
     */
    private String shopCode;

    /**
     * 启用标识
     */
    private EnableOrDisableEnum startFlag;

    /**
     * 备注
     */
    private String remark;


    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEbcCode() {
        return ebcCode;
    }

    public void setEbcCode(String ebcCode) {
        this.ebcCode = ebcCode;
    }

    public String getEbpCode() {
        return ebpCode;
    }

    public void setEbpCode(String ebpCode) {
        this.ebpCode = ebpCode;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public EnableOrDisableEnum getStartFlag() {
        return startFlag;
    }

    public void setStartFlag(EnableOrDisableEnum startFlag) {
        this.startFlag = startFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
