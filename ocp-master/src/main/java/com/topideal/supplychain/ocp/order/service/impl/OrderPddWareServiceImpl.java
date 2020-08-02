package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderPddWareMapper;
import com.topideal.supplychain.ocp.order.model.OrderPddWare;
import com.topideal.supplychain.ocp.order.service.OrderPddWareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author klw
 * @date 2019/12/11 16:58
 * @description: 拼多多子货品service
 */
@Service
public class OrderPddWareServiceImpl implements OrderPddWareService {

    @Autowired
    private OrderPddWareMapper orderPddWareMapper;

    /**
     * 批量插入
     * @param list
     * @param orderId
     * @return
     */
    @Override
    @Transactional
    public int insertBatch(List<OrderPddWare> list, Long orderId) {
        return orderPddWareMapper.insertBatch(list, orderId, new Date());
    }

    /**
     * 根据订单id查询
     * @param orderId
     * @return
     */
    @Override
    public OrderPddWare selectByOrderId(Long orderId) {
        return orderPddWareMapper.selectByOrderId(orderId);
    }
}
