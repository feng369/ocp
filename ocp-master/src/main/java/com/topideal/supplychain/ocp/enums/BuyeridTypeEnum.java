package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.StringEnum;

public enum BuyeridTypeEnum implements StringEnum {
    IDCARD("1","身份证"),
    OTHERS("2","其他")

    ;
    private String value;
    private String desc;



    BuyeridTypeEnum(String value, String desc) {
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


    public static BuyeridTypeEnum getValueEnum(String value) {
        for (BuyeridTypeEnum buyeridTypeEnum : BuyeridTypeEnum.values()) {
            if (buyeridTypeEnum.value.equals(value)) {
                return buyeridTypeEnum;
            }
        }
        return null;
    }
}
