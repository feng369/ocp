package com.topideal.supplychain.ocp.order.service;


import com.topideal.supplychain.ocp.order.model.OrderQmItem;

import java.util.List;

/**
 * @description: 奇门订单明细
 * @author: syq
 * @create: 2019-12-12 11:28
 **/
public interface OrderQmItemService {

    List<OrderQmItem> selectByOrderId(Long orderId);

}
