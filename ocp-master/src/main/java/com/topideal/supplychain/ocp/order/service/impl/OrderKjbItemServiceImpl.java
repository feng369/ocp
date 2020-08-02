package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderKjbItemMapper;
import com.topideal.supplychain.ocp.order.model.OrderKjbItem;
import com.topideal.supplychain.ocp.order.service.OrderKjbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @description: 跨境宝订单明细
 * @author: syq
 * @create: 2019-12-12 11:29
 **/
@Service
public class OrderKjbItemServiceImpl implements OrderKjbItemService {

    @Autowired
    private OrderKjbItemMapper orderKjbItemMapper;

    @Override
    public List<OrderKjbItem> selectByOrderId(Long orderId) {
        return orderKjbItemMapper.selectByOrderId(orderId);
    }

    @Override
    public int batchInsert(List<OrderKjbItem> list) {
        return orderKjbItemMapper.batchInsert(list);
    }
}
