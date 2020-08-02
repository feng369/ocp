package com.topideal.supplychain.ocp.config.model;

import com.topideal.supplychain.common.model.BaseEntity;
import com.topideal.supplychain.enumeration.EnableOrDisableEnum;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 标题：组套商品配置model
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.config.model
 * 作者：songping
 * 创建日期：2019/12/23 14:37
 *
 * @version 1.0
 */
public class CombineGoodsConfig extends BaseEntity {

    /**
     * id集合
     */
    private Long[] ids;

    /**
     * 配置编号
     */
    private String code;

    /**
     * 组合商品编码
     */
    private String combineCode;

    /**
     * 拆分数量
     */
    private Integer apartQty;

    /**
     * 商品编码
     */
    private String itemCode;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 启用标识
     */
    private EnableOrDisableEnum startFlag;

    /**
     * 备注
     */
    private String remark;


    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCombineCode() {
        return combineCode;
    }

    public void setCombineCode(String combineCode) {
        this.combineCode = combineCode;
    }

    public Integer getApartQty() {
        return apartQty;
    }

    public void setApartQty(Integer apartQty) {
        this.apartQty = apartQty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
