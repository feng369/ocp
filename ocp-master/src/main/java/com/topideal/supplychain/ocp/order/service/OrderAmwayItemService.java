package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderAmwayItem;

import java.util.List;

public interface OrderAmwayItemService {
    void insertList(List<OrderAmwayItem> itemList);
    List<OrderAmwayItem> selectByOrderId(Long orderId);
}
