package com.topideal.supplychain.ocp.common;

/**
 * @Author: annie
 * @Description: 订单推ESD返回码，及返回码描述
 * @Date: Created in 2018/12/5
 */
public enum ResponseCodeEnum {
    SUCCESS("10000", "成功"),
    SERVICE_NOT_AVAILABLE("20000", "服务不可用"),
    NO_PERMISSION("20001", "无权限"),
    AUTH_FAIL("20002", "权限验证失败"),
    PARAMETER_ERROR("30000", "参数错误"),
    PARAMETER_MISS("30001", "参数缺失"),
    PASSWORD_ERROR("30002", "密码错误"),
    BUSINESS_ERROR("40000", "业务处理"),
    ;
    private String code;
    private String subMsg;
    ResponseCodeEnum(String code, String subMsg){
        this.code = code;
        this.subMsg = subMsg;
    }

    public String getCode() {
        return code;
    }

    public String getSubMsg() {
        return subMsg;
    }
}
