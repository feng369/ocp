package com.topideal.supplychain.ocp.master.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.ocp.enums.BusiModeEnum;
import com.topideal.supplychain.ocp.enums.CustomsCodeEnum;

/**
 * 商品业主
 */
public class GoodsCustomerConfig extends BaseEntity {

    /**
     * 企业编码
     */
    private String enterpriseCode;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 商品货号
     */
    private String goodsCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 关区
     */
    private CustomsCodeEnum customerCode;

    /**
     * 贸易模式
     */
    private BusiModeEnum busiMode;

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode == null ? null : enterpriseCode.trim();
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public CustomsCodeEnum getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(CustomsCodeEnum customerCode) {
        this.customerCode = customerCode;
    }

    public BusiModeEnum getBusiMode() {
        return busiMode;
    }

    public void setBusiMode(BusiModeEnum busiMode) {
        this.busiMode = busiMode;
    }
}