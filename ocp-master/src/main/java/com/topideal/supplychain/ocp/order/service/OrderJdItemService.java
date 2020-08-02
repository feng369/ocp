package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderJdItem;

import java.util.List;

public interface OrderJdItemService {
    void insertList(List<OrderJdItem> itemList);

    List<OrderJdItem> selectByOrderId(Long orderId);
}
