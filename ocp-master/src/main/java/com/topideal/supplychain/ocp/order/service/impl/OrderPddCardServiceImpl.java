package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderPddCardMapper;
import com.topideal.supplychain.ocp.order.model.OrderPddCard;
import com.topideal.supplychain.ocp.order.service.OrderPddCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author klw
 * @date 2019/12/11 17:04
 * @description: 拼多多抓单卡密service
 */
@Service
public class OrderPddCardServiceImpl implements OrderPddCardService {

    @Autowired
    private OrderPddCardMapper orderPddCardMapper;

    /**
     * 批量插入
     * @param list
     * @param orderId
     * @return
     */
    @Override
    @Transactional
    public int insertBatch(List<OrderPddCard> list, Long orderId) {
        return orderPddCardMapper.insertBatch(list, orderId, new Date());
    }

    /**
     * 根据订单id查询
     * @param orderId
     * @return
     */
    @Override
    public OrderPddCard selectByOrderId(Long orderId) {
        return orderPddCardMapper.selectByOrderId(orderId);
    }
}
