package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderAmwayPaymentLine;

import java.util.List;

public interface OrderAmwayPaymentLineService {
    void insertList(List<OrderAmwayPaymentLine> itemList);
    List<OrderAmwayPaymentLine> selectByOrderId(Long orderId);
}
