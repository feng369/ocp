package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderJdItemMapper;
import com.topideal.supplychain.ocp.order.model.OrderJdItem;
import com.topideal.supplychain.ocp.order.service.OrderJdItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class OrderItemServiceImpl implements OrderJdItemService {
    @Autowired
    private OrderJdItemMapper mapper;

    @Override
    @Transactional
    public void insertList(List<OrderJdItem> itemList) {
        mapper.insertList(itemList);
    }

    @Override
    public List<OrderJdItem> selectByOrderId(Long orderId) {
        return mapper.selectByOrderId(orderId);
    }
}
