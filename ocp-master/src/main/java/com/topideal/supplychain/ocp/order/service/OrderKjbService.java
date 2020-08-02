package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderKjb;

import java.util.List;

/**
 * @description: 跨境宝订单
 * @author: syq
 * @create: 2019-12-12 11:28
 **/
public interface OrderKjbService {

    List<OrderKjb> pageList(OrderKjb orderKjb);

    OrderKjb selectById(Long id);

    void update(OrderKjb orderKjb);

    OrderKjb selectExist(String tradeNo, String storeCode, String requestTime);

    int insert(OrderKjb record);

    List<OrderKjb> selectByIds(List<Long> ids);
}
