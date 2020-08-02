package com.topideal.supplychain.ocp.order.service;


import com.topideal.supplychain.ocp.order.model.OrderKjbItem;

import java.util.List;

/**
 * @description: 跨境宝订单明细
 * @author: syq
 * @create: 2019-12-17 11:28
 **/
public interface OrderKjbItemService {

    List<OrderKjbItem> selectByOrderId(Long orderId);

    int batchInsert(List<OrderKjbItem> list);
}
