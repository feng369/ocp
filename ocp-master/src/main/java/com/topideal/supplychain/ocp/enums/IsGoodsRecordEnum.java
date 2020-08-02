package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.StringEnum;

/**
 * 标题：是否指定商品备案枚举
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.enums
 * 作者：songping
 * 创建日期：2020/1/7 15:28
 *
 * @version 1.0
 */
public enum IsGoodsRecordEnum implements StringEnum {
    NO("0", "未指定商品备案"),
    YES("1", "指定商品备案"),
    ;

    private String value;
    private String desc;

    IsGoodsRecordEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonValue
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static IsGoodsRecordEnum getValueEnum(String value) {
        for (IsGoodsRecordEnum goodsRecordEnum : IsGoodsRecordEnum.values()) {
            if (goodsRecordEnum.value.equals(value)){
                return goodsRecordEnum;
            }
        }
        return null;
    }
}
