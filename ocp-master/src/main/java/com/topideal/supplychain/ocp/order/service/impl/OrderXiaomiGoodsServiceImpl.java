package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderXiaomiGoodsMapper;
import com.topideal.supplychain.ocp.order.model.OrderXiaomiGoods;
import com.topideal.supplychain.ocp.order.service.OrderXiaomiGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/30
 * @description
 **/
@Service
public class OrderXiaomiGoodsServiceImpl implements OrderXiaomiGoodsService {
    @Autowired
    private OrderXiaomiGoodsMapper mapper;

    @Override
    public List<OrderXiaomiGoods> selectAllByOrderId(Long orderId) {
        return mapper.selectAllByOrderId(orderId);
    }

    @Override
    public OrderXiaomiGoods selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    @Transactional
    public void insert(OrderXiaomiGoods orderXiaomiGoods) {
      mapper.insert(orderXiaomiGoods);
    }

}
