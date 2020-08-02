package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderYmatouIndentity;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/3
 * @description
 **/
public interface OrderYmatouIndentityService {

    void insert(OrderYmatouIndentity bean);

    /**
     * 通过主订单号查找
     * @return
     */
    List<OrderYmatouIndentity> selectByMsgOrderId(Long msgOrderId);
}
