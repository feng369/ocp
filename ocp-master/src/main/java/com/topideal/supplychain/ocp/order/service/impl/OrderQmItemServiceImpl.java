package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderQmItemMapper;
import com.topideal.supplychain.ocp.order.model.OrderQmItem;
import com.topideal.supplychain.ocp.order.service.OrderQmItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @description: 奇门订单
 * @author: syq
 * @create: 2019-12-12 11:29
 **/
@Service
public class OrderQmItemServiceImpl implements OrderQmItemService {

    @Autowired
    private OrderQmItemMapper orderQmItemMapper;

    @Override
    public List<OrderQmItem> selectByOrderId(Long orderId) {
        return orderQmItemMapper.selectByOrderId(orderId);
    }
}
