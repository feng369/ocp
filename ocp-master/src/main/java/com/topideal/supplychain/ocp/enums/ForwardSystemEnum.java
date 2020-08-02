package com.topideal.supplychain.ocp.enums;


import com.topideal.supplychain.enumeration.StringEnum;

public enum ForwardSystemEnum implements StringEnum {
    OP("OP","op","OP"),
    OFC("OFC","ofc","OFC"),
    OFC_BC("OFC-BC","ofc-bc","OFC-BC"),
    ESD("ESD","esd","ESD"),
    KJB("KJB","kjb","KJB"),
    EW("EW","ew","E仓"),
    ;

    ForwardSystemEnum(String value,String queue, String desc) {
        this.value = value;
        this.queue = queue;
        this.desc = desc;
    }

    private String value;
    private String desc;
    private String queue;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public String getQueue() {
        return queue;
    }

    public static ForwardSystemEnum setEnum(String code){
        for(ForwardSystemEnum forwardSystemEnum :ForwardSystemEnum.values()){
            if(forwardSystemEnum.getValue().equals(code)){
                return forwardSystemEnum;
            }
        }
        return null;
    }
}
