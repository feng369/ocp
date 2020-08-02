package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderBaomaGoods;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/11/28
 * @description 宝妈时光商品
 **/
public interface OrderBaomaGoodsService {

    List<OrderBaomaGoods> selectAllByOrderId(Long orderId);


    OrderBaomaGoods selectById(Long id);

    void insert(OrderBaomaGoods orderBaomaGoods);

}
