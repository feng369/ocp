package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderBaomaGoodsMapper;
import com.topideal.supplychain.ocp.order.model.OrderBaomaGoods;
import com.topideal.supplychain.ocp.order.service.OrderBaomaGoodsService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/13
 * @description
 **/
@Service
public class OrderBaomaGoodsServiceImpl implements OrderBaomaGoodsService {

    @Autowired
    private OrderBaomaGoodsMapper mapper;

    @Override
    public List<OrderBaomaGoods> selectAllByOrderId(Long orderId) {
        return mapper.selectAllByOrderId(orderId);
    }

    @Override
    public OrderBaomaGoods selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    @Transactional
    public void insert(OrderBaomaGoods orderBaomaGoods) {
        orderBaomaGoods.setCreateTime(DateUtils.getNowTime());
        orderBaomaGoods.setCreateBy(Authentication.getUserId());
        mapper.insert(orderBaomaGoods);
    }
}
