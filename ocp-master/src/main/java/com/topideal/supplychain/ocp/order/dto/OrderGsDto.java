package com.topideal.supplychain.ocp.order.dto;

import com.topideal.supplychain.ocp.order.model.OrderGs;
import com.topideal.supplychain.ocp.order.model.OrderGsItem;

import java.util.Date;
import java.util.List;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.dto</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2019-12-17 10:32</p>
 *
 * @version 1.0
 */
public class OrderGsDto extends OrderGs {

    private String idCardNO;
    /**
     * 创建时间(环球捕手)
     */
    private Date createTime;

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIdCardNO() {
        return idCardNO;
    }

    public void setIdCardNO(String idCardNO) {
        this.idCardNO = idCardNO;
    }
}
