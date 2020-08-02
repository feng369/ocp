package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderAmwayItemMapper;
import com.topideal.supplychain.ocp.order.model.OrderAmwayItem;
import com.topideal.supplychain.ocp.order.service.OrderAmwayItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class OrderAmwayItemServiceImpl implements OrderAmwayItemService {

    @Autowired
    private OrderAmwayItemMapper mapper;

    @Override
    @Transactional
    public void insertList(List<OrderAmwayItem> itemList) {
        mapper.insertList(itemList);
    }

    @Override
    public List<OrderAmwayItem> selectByOrderId(Long orderId) {
        return mapper.selectByOrderId(orderId);
    }
}
