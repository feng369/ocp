package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderYmatou;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/11/28
 * @description 洋码头 逻辑层
 **/
public interface OrderYmatouService {

    List<OrderYmatou> pageList(OrderYmatou filter);

    void insert(OrderYmatou orderYmatou);

    OrderYmatou selectById(Long id);

    void updateById(OrderYmatou orderYmatou);

    List<OrderYmatou> selectByIds(List<Long> ids);
}
