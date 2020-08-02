package com.topideal.supplychain.ocp.ofc.dto;

import java.io.Serializable;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.ofc.dto</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/11/29 15:12</p>
 *
 * @version 1.0
 */
public class OfcResponse implements Serializable {
    //订单状态 OrderStatusEnum values
    private String flag;
    //订单
    private String code;
    //原因
    private String message;

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
}
