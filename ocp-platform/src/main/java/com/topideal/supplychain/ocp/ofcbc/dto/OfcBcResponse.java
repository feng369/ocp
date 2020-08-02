package com.topideal.supplychain.ocp.ofcbc.dto;

import java.io.Serializable;

/**
 * 标题：BC-OFC转单响应
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.ofcbc.dto
 * 作者：songping
 * 创建日期：2020/1/20 14:30
 *
 * @version 1.0
 */
public class OfcBcResponse implements Serializable {
    //订单状态
    private String flag;
    //订单
    private String code;
    //msg
    private String message;
    //错误信息
    private String errorInfo;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
