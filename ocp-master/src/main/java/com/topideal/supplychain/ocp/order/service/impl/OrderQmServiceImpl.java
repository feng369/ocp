package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderQmMapper;
import com.topideal.supplychain.ocp.order.model.OrderQm;
import com.topideal.supplychain.ocp.order.service.OrderQmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 奇门订单
 * @author: syq
 * @create: 2019-12-12 11:29
 **/
@Service
public class OrderQmServiceImpl implements OrderQmService {

    @Autowired
    private OrderQmMapper orderQmMapper;

    @Override
    public List<OrderQm> pageList(OrderQm orderQm) {
        return orderQmMapper.pageList(orderQm);
    }

    @Override
    public OrderQm selectById(Long id) {
        return orderQmMapper.selectById(id);
    }
}
