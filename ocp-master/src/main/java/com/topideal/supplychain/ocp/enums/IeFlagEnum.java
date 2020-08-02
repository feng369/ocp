package com.topideal.supplychain.ocp.enums;

import com.topideal.supplychain.enumeration.StringEnum;

/**
 * @ClassName IeFlagEnum
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/5/21 15:11
 * @Version 1.0
 **/
public enum IeFlagEnum implements StringEnum {
    E("E","出口"),
    I("I","进口"),;

    private String value;

    private String desc;

    IeFlagEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
