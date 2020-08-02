package com.topideal.supplychain.ocp.enums;


import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.IntegerEnum;

/**
 * @ClassName: KjbYesNoEnum
 * @Description: 跨境宝是否枚举
 */
public enum KjbYesNoEnum implements IntegerEnum {
    YES(1, "是"),
    NO(2, "否"),
    ;

    private Integer value;
    private String desc;

    KjbYesNoEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
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

    public static KjbYesNoEnum getValueEnum(Integer value) {
        for (KjbYesNoEnum kjbYesNoEnum : KjbYesNoEnum.values()) {
            if (kjbYesNoEnum.value.equals(value)) {
                return kjbYesNoEnum;
            }
        }
        return null;
    }

}
