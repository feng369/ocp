package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderYmatouDeliveryMapper;
import com.topideal.supplychain.ocp.order.model.OrderYmatouDelivery;
import com.topideal.supplychain.ocp.order.service.OrderYmatouDeliveryService;
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
public class OrderYmatouDeliveryServiceImpl implements OrderYmatouDeliveryService {
    @Autowired
    private OrderYmatouDeliveryMapper mapper;

    @Override
    @Transactional
    public void insert(OrderYmatouDelivery bean) {
        mapper.insert(bean);
    }

    /**
     * 通过主订单id查找
     *
     * @param msgOrderId
     * @return
     */
    @Override
    public List<OrderYmatouDelivery> selectByMsgOrderId(Long msgOrderId) {
        return mapper.selectByMsgOrderId(msgOrderId);
    }
}
