package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.order.dto.OrderJdDto;
import com.topideal.supplychain.ocp.order.model.OrderJd;

import java.util.List;

public interface OrderJdService {
    void insert(OrderJd orderJd);

    List<OrderJd> pageList(OrderJdDto filter);

    OrderJd selectById(Long id);

    void updateOrderStatus(Long id, String status, String msg);

    void rePush(Long[] ids);

    boolean isExist(String orderId, OrderStatusEnum orderStatus);

    //String selectSensitiveData(String propertyName, long orderId);
}
