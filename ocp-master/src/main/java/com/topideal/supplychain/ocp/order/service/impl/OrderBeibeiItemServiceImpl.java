package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderBeibeiItemMapper;
import com.topideal.supplychain.ocp.order.model.OrderBeibeiItem;
import com.topideal.supplychain.ocp.order.service.OrderBeibeiItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2020/3/19
 * @description
 **/
@Service
public class OrderBeibeiItemServiceImpl implements OrderBeibeiItemService {
    @Autowired
    private OrderBeibeiItemMapper mapper;

    @Override
    public List<OrderBeibeiItem> selectAllByOrderId(Long orderId) {
        return mapper.selectByOrderId(orderId);
    }

    @Override
    @Transactional
    public void insert(OrderBeibeiItem orderBeibeiItem) {
      mapper.insert(orderBeibeiItem);
    }
}
