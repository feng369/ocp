package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderYmatouDelivery;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/3
 * @description
 **/
public interface OrderYmatouDeliveryService {

    void insert(OrderYmatouDelivery bean);

    /**
     * 通过主订单id查找
     * @param msgOrderId
     * @return
     */
    List<OrderYmatouDelivery> selectByMsgOrderId(Long msgOrderId);
}
