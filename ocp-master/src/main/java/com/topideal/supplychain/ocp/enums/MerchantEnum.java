package com.topideal.supplychain.ocp.enums;

/**
 * @ClassName MerchantEnum
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/18 11:29
 * @Version 1.0
 **/
public enum MerchantEnum {

    VM("VM00000001","全企业"),
    KJB("kjb","跨境宝")
    ;

    private String code;

    private String name;

    MerchantEnum(String code, String name) {
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
