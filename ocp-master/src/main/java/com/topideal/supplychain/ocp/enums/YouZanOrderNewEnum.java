package com.topideal.supplychain.ocp.enums;

import com.topideal.supplychain.enumeration.StringEnum;

/**
 * @author xiao yu qiang
 * @time 2019/6/24 14:59
 * @description 有赞调用跨境宝订单区分枚举
 * @table
 */
public enum YouZanOrderNewEnum implements StringEnum {
    OLD("0","原始数据"),
    NEW("1","调用后的数据")
    ;

    private String value;
    private String desc;

    YouZanOrderNewEnum(String value, String desc) {
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
