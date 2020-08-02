package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.dto.OrderDxyDto;
import com.topideal.supplychain.ocp.order.model.OrderDistribution;
import com.topideal.supplychain.ocp.order.model.OrderDxy;
import com.topideal.supplychain.ocp.order.model.OrderJd;
import java.util.List;

/**
 * @ClassName OrderDxyService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/6/30 18:24
 * @Version 1.0
 **/
public interface OrderDxyService {

    void insert(OrderDxy orderDxy);

    boolean isExist(String orderId);

    OrderDxy selectById(Long businessId);

    void updateOrderStatus(Long id, String value);

    List<OrderDxy> pageList(OrderDxyDto filter);

    List<OrderDxy> selectByIds(List<Long> ids);
}

