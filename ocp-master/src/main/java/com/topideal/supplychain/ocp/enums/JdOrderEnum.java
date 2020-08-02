package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.StringEnum;

public enum JdOrderEnum implements StringEnum {
    ZY("1", "自营",2L),
    FZY("2", "非自营",1L),
    ECLP("3", "ECLP",4L),
    MULTILCHAN("4", "多渠道",5L),
    DLZ("5", "独立站",6L),
    ZHOUZHI("6","卓志",3L),
    SHIES("7", "云霄",7L),
    ;
    private String value;
    private String desc;
    private Long omsValue;

    JdOrderEnum(String value, String desc,Long omsValue) {
        this.value = value;
        this.desc = desc;
        this.omsValue = omsValue;
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

    public Long getOmsValue() {
        return omsValue;
    }

    public static JdOrderEnum getValueEnum(String value) {
        for (JdOrderEnum jdOrderEnum : JdOrderEnum.values()) {
            if (jdOrderEnum.value.equals(value)) {
                return jdOrderEnum;
            }
        }
        return null;
    }
}
