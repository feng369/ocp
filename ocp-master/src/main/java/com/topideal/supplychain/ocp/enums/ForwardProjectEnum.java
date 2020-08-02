package com.topideal.supplychain.ocp.enums;


import com.topideal.supplychain.enumeration.StringEnum;

/**
 * @author xiao yu qiang
 * @time 2019/4/10 15:45
 * @description 转发系统枚举-发送的队列
 * @table
 */
public enum ForwardProjectEnum implements StringEnum {
    TOOP("01","OCP推送OP接口队列")
    ;

    ForwardProjectEnum(String value, String desc) {
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

    public static ForwardProjectEnum setEnum(String code){
        for(ForwardProjectEnum forwardProjectEnum :ForwardProjectEnum.values()){
            if(forwardProjectEnum.getValue().equals(code)){
                return forwardProjectEnum;
            }
        }
        return null;
    }
}
