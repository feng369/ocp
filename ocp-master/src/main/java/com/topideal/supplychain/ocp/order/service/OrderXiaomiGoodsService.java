package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderXiaomiGoods;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/11/28
 * @description 小米商品
 **/
public interface OrderXiaomiGoodsService {

    List<OrderXiaomiGoods> selectAllByOrderId(Long orderId);

    OrderXiaomiGoods selectById(Long id);

    void insert(OrderXiaomiGoods orderXiaomiGoods);


}
