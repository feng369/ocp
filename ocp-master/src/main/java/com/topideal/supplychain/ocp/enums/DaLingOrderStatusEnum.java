package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.StringEnum;

/**
 * 达令订单状态
 *
 * @author xuxiaoyan
 * @date 2019-12-02 14:59
 */
public enum DaLingOrderStatusEnum implements StringEnum{

    VALID("1","有效"),
    NOT_VALID("2","无效"),
    ;

    private String value;
    private String desc;

    DaLingOrderStatusEnum(String value, String desc) {
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


    public static DaLingOrderStatusEnum getByValue(String value){
        for(DaLingOrderStatusEnum daLingOrderStatusEnum:DaLingOrderStatusEnum.values()){
            if(daLingOrderStatusEnum.value.equals(value)){
                return daLingOrderStatusEnum;
            }
        }
        return null;
    }
}
