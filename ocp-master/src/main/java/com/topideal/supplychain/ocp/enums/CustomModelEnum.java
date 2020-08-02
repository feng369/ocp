package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.StringEnum;

/**
 * @ClassName CustomModel
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/3/5 13:06
 * @Version 1.0
 **/
public enum CustomModelEnum implements StringEnum {

    BEIHUO("备货","beihuo",BusiModeEnum.BBC),
    ZHIYOU("直邮","zhiyou",BusiModeEnum.BC),
    /*JIHUO("集货","jihuo"),
    GRKUAIJIAN("个人快件","grkuaijian"),
    YOUZHENG("邮政","youzheng")*/
    ;

    CustomModelEnum(String desc,String value,BusiModeEnum busiModeEnum) {
        this.value = value;
        this.desc = desc;
        this.busiModeEnum = busiModeEnum;
    }

    private String value;

    private String desc;

    private BusiModeEnum busiModeEnum;

    @Override
    public String getValue() {
        return value;
    }

    @JsonValue
    @Override
    public String getDesc() {
        return desc;
    }

    public BusiModeEnum getBusiModeEnum() {
        return busiModeEnum;
    }

    public static CustomModelEnum getValueEnum(String value) {
        for (CustomModelEnum customModelEnum : CustomModelEnum.values()) {
            if (customModelEnum.value.equals(value)) {
                return customModelEnum;
            }
        }
        return null;
    }
}
