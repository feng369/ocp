package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderYmatouItem;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/3
 * @description
 **/
public interface OrderYmatouItemService {

    void insert(OrderYmatouItem order);

    void batchInsert(List<OrderYmatouItem> list);

    List<OrderYmatouItem> selectByOrderIdAndStatus(Long orderId,String status);

    void updateById(OrderYmatouItem item);
}
