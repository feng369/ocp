package com.topideal.supplychain.ocp.enums;


import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.StringEnum;

/**
 * 关区枚举
 */
public enum CustomsCodeEnum implements StringEnum {

    LJGQ("0904","连加工区"),
    LBHG("3100","宁波海关"),
    CSHG("4905","长沙海关"),
    LGHG("5130","萝岗海关"),
    JCHG("5141","机场海关"),
    NSLJ("5165","南沙旅检"),
    NSHG("5167","南沙货港"),
    PKFQ("5208","埔开发区"),
    YMYQ("8015","渝贸园区"),
    LBSG("0910","连保税港"),
    HQBB("2252","虹桥B保"),
    WFHG("4312","潍坊海关驻出口加工区办事处")
    ;

    CustomsCodeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private String value;
    private String desc;

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static CustomsCodeEnum getValueEnum(String value) {
        for (CustomsCodeEnum customsCodeEnum : CustomsCodeEnum.values()) {
            if (customsCodeEnum.value.equals(value)) {
                return customsCodeEnum;
            }
        }
        return null;
    }
}
