package com.topideal.supplychain.ocp.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.topideal.supplychain.enumeration.StringEnum;

/**
 * @time 2019/6/24 14:56
 * @description 调用跨境宝返回状态
 * @table
 */
public enum KjbSendStausEnum implements StringEnum {

    NO_DEAL("0","不处理"),
    SUCCESS_DEAL("1","处理成功"),
    FAIL_DEAL("2","处理失败"),
    ;

    KjbSendStausEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private String value;
    private String desc;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @JsonCreator
    public static KjbSendStausEnum getEnum(String value){
        for (KjbSendStausEnum sendStausEnum : KjbSendStausEnum.values()){
            if(sendStausEnum.getValue().equals(value)){
                return sendStausEnum;
            }
        }
        return null;
    }
}
