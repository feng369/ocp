package com.topideal.supplychain.ocp.enums;

import com.topideal.supplychain.enumeration.StringEnum;

public enum GrabIdEnum implements StringEnum {
    JD_ZY("JD-ZY","京东自营",false),
    JD_FZY("JD-FZY","京东非自营",false),
    JD_SY("JD-SY","京东黄埔",false),
    JD_YXNS("JD-YXNS","京东云霄南沙",true),
    JD_YXHP("JD-YXHP","京东云霄黄埔",true),
    JD_DLZ("JD-DLZ","京东独立站多渠道",false)
    ;
    private String value;
    private String desc;
    private boolean isYx;



    GrabIdEnum(String value, String desc,boolean isYx) {
        this.value = value;
        this.desc = desc;
        this.isYx = isYx;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    public boolean isYx() {
        return isYx;
    }

    public static GrabIdEnum getValueEnum(String value) {
        for (GrabIdEnum grabIdEnum : GrabIdEnum.values()) {
            if (grabIdEnum.value.equals(value)) {
                return grabIdEnum;
            }
        }
        return null;
    }
}
