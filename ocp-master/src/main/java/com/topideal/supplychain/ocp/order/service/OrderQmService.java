package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderQm;

import java.util.List;

/**
 * @description: 奇门订单
 * @author: syq
 * @create: 2019-12-12 11:28
 **/
public interface OrderQmService {

    List<OrderQm> pageList(OrderQm orderQm);

    OrderQm selectById(Long id);
}
