package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderYmatouItemMapper;
import com.topideal.supplychain.ocp.order.model.OrderYmatouItem;
import com.topideal.supplychain.ocp.order.service.OrderYmatouItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/3
 * @description
 **/
@Service
public class OrderYmatouItemServiceImpl implements OrderYmatouItemService {
    @Autowired
    private OrderYmatouItemMapper mapper;

    @Override
    @Transactional
    public void insert(OrderYmatouItem order) {
        mapper.insert(order);
    }

    @Override
    @Transactional
    public void batchInsert(List<OrderYmatouItem> list) {
        for(OrderYmatouItem item : list){
            insert(item);
        }
    }

    @Override
    public List<OrderYmatouItem> selectByOrderIdAndStatus(Long orderId, String status) {
        return mapper.selectByOrderIdAndStatus(orderId,status);
    }

    @Override
    @Transactional
    public void updateById(OrderYmatouItem item) {
        mapper.update(item);
    }
}
