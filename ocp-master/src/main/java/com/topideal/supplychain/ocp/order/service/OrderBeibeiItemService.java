package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderBeibeiItem;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2020/3/19
 * @description 贝贝订单明细
 **/
public interface OrderBeibeiItemService {

    List<OrderBeibeiItem> selectAllByOrderId(Long orderId);

    void insert(OrderBeibeiItem orderBeibeiItem);

}
