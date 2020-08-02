package com.topideal.supplychain.ocp.enums;

/**
 * @ClassName LogisticsEnum
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/18 11:33
 * @Version 1.0
 **/
public enum LogisticsEnum {

    VL("VL00000001","全物流"),
    ;

    private String code;

    private String name;

    LogisticsEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
