package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderPddGoods;

import java.util.List;

/**
 * @author klw
 * @date 2019/11/18 11:41
 * @description: 拼多多抓单商品明细service
 */
public interface OrderPddGoodsService {

    int insertBatch(List<OrderPddGoods> list, Long orderId);

    List<OrderPddGoods> selectByOrderId(Long orderId);
}
