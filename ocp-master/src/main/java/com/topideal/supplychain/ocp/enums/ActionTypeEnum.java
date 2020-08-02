package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.StringEnum;

/**
 * @description: 奇门商品操作类型枚举
 * @author: syq
 * @create: 2019-12-09 15:53
 **/
public enum ActionTypeEnum implements StringEnum {

    ADD("1", "add"),
    UPDATE("2", "update"),
    ;

    private String value;
    private String desc;

    ActionTypeEnum(String value, String desc) {
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


}
