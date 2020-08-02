package com.topideal.supplychain.ocp.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.topideal.supplychain.enumeration.StringEnum;

/**
 * 业务模式枚举
 *
 * @author xuxiaoyan
 * @date 2019-11-14 15:56
 */
public enum BusiModeEnum implements StringEnum {

    BBC("10","BBC","保税","1210","003"),
    BC("20","BC","直邮","9610","001"),
    BN("30","保留","保留","保留","099"),
    CC("40","CC","CC","CC","008"),
    ;

    BusiModeEnum(String value, String desc,String memo,String yzCode,String vpCode) {
        this.value = value;
        this.desc = desc;
        this.memo = memo;
        this.yzCode = yzCode;
        this.vpCode = vpCode;
    }

    private String value;
    private String desc;
    private String memo;
    private String yzCode;
    private String vpCode;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @JsonValue
    public String getMemo() {
        return memo;
    }

    public String getYzCode() {
        return yzCode;
    }

    public String getVpCode() {
        return vpCode;
    }

    public static BusiModeEnum getNameEnum(String memo) {
        for (BusiModeEnum busiModeEnum : BusiModeEnum.values()) {
            if (busiModeEnum.memo.equals(memo)) {
                return busiModeEnum;
            }
        }
        return null;
    }

    public static BusiModeEnum getDescEnum(String desc) {
        for (BusiModeEnum busiModeEnum : BusiModeEnum.values()) {
            if (busiModeEnum.desc.equals(desc)) {
                return busiModeEnum;
            }
        }
        return null;
    }

    public static BusiModeEnum getValueEnum(String value) {
        for (BusiModeEnum busiModeEnum : BusiModeEnum.values()) {
            if (busiModeEnum.value.equals(value)) {
                return busiModeEnum;
            }
        }
        return null;
    }

    public static BusiModeEnum getYzEnum(String yzCode){
        for(BusiModeEnum busiModeEnum :BusiModeEnum.values()){
            if(busiModeEnum.getYzCode().equals(yzCode)){
                return busiModeEnum;
            }
        }
        return null;
    }

    public static BusiModeEnum getVpEnum(String vpCode){
        for(BusiModeEnum busiModeEnum :BusiModeEnum.values()){
            if(busiModeEnum.getVpCode().equals(vpCode)){
                return busiModeEnum;
            }
        }
        return null;
    }



}
