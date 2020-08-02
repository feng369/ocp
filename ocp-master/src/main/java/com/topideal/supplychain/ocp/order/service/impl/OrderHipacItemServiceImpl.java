package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderHipacItemMapper;
import com.topideal.supplychain.ocp.order.model.OrderHipacItem;
import com.topideal.supplychain.ocp.order.service.OrderHipacItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 海拍客订单商品明细
 *
 * @author xuxiaoyan
 * @date 2019-12-16 15:18
 */
@Service
public class OrderHipacItemServiceImpl implements OrderHipacItemService {

    @Autowired
    private OrderHipacItemMapper orderHipacItemMapper;

    @Override
    public List<OrderHipacItem> selectByOrderId(Long orderId) {
        return orderHipacItemMapper.selectByOrderId(orderId);
    }

    @Override
    @Transactional
    public void insertList(List<OrderHipacItem> itemList) {
        orderHipacItemMapper.insertList(itemList);
    }
}
