package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.StringEnum;

/**
 * 标题：大订单状态枚举
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.enums
 * 作者：songping
 * 创建日期：2020/1/14 17:32
 *
 * @version 1.0
 */
public enum BigOrderStatusEnum implements StringEnum {
    S("S", "新增"),
    UPDATE("update", "更新"),
    ;

    private String value;
    private String desc;

    BigOrderStatusEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    @JsonValue
    public String getDesc() {
        return desc;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static BigOrderStatusEnum getValueEnum(String value){
        for (BigOrderStatusEnum orderStatus : BigOrderStatusEnum.values()) {
            if (orderStatus.value.equals(value)){
                return orderStatus;
            }
        }
        return null;
    }
}
