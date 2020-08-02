package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderPddWare;

import java.util.List;

/**
 * @author klw
 * @date 2019/11/18 11:41
 * @description: 拼多多子货品service
 */
public interface OrderPddWareService {
    int insertBatch(List<OrderPddWare> list, Long orderId);

    OrderPddWare selectByOrderId(Long orderId);
}
