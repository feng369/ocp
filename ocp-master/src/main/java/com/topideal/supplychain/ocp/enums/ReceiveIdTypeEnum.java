package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.StringEnum;

/**
 * @description: 收件人证件类型枚举
 * @author: syq
 * @create: 2019-12-09 15:53
 **/
public enum ReceiveIdTypeEnum implements StringEnum {

    ID_CARD("1", "身份证"),
    OFFICER_CARD("2", "军官证"),
    PASSPORT("3", "护照"),
    OTHER("4", "其他"),
    ;

    private String value;
    private String desc;

    ReceiveIdTypeEnum(String value, String desc) {
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

    public static ReceiveIdTypeEnum getValueEnum(String value){
        for (ReceiveIdTypeEnum idType : ReceiveIdTypeEnum.values()) {
            if (idType.value.equals(value)){
                return idType;
            }
        }
        return null;
    }
}
