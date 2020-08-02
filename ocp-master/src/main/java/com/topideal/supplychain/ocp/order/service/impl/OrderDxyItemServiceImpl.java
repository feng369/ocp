package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderDxyItemMapper;
import com.topideal.supplychain.ocp.order.model.OrderDxyItem;
import com.topideal.supplychain.ocp.order.service.OrderDxyItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName OrderDxyItemService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/6/30 18:24
 * @Version 1.0
 **/
@Service
public class OrderDxyItemServiceImpl implements OrderDxyItemService {

    @Autowired
    private OrderDxyItemMapper orderDxyItemMapper;

    @Override
    @Transactional
    public void insertList(List<OrderDxyItem> itemList) {
        orderDxyItemMapper.insertList(itemList);
    }

    @Override
    public List<OrderDxyItem> selectByOrderId(Long orderId) {
        return orderDxyItemMapper.selectByOrderId(orderId);
    }
}

