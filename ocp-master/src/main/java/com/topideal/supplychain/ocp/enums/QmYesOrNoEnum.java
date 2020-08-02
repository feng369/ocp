package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.StringEnum;

/**
 * @description: 奇门是否枚举
 * @author: syq
 * @create: 2019-12-09 15:53
 **/
public enum QmYesOrNoEnum implements StringEnum {

    YES("1", "成功"),
    NO("2", "失败"),
    ;

    private String value;
    private String desc;

    QmYesOrNoEnum(String value, String desc) {
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
