package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderYouzanPaymentsMapper;
import com.topideal.supplychain.ocp.order.model.OrderYouzanPayments;
import com.topideal.supplychain.ocp.order.service.OrderYouzanPaymentsService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName OrderYouzanServiceImpl
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/22 14:33
 * @Version 1.0
 **/
@Service
public class OrderYouzanPaymentsServiceImpl implements OrderYouzanPaymentsService {

    @Autowired
    private OrderYouzanPaymentsMapper orderYouzanPaymentsMapper;


    @Override
    @Transactional
    public void insert(OrderYouzanPayments orderYouzanPayments) {
        orderYouzanPayments.setCreateBy(Authentication.getUserId());
        orderYouzanPayments.setCreateTime(DateUtils.getNowTime());
        orderYouzanPaymentsMapper.insert(orderYouzanPayments);
    }

    @Override
    public int batchInsert(List<OrderYouzanPayments> phasePayments) {
        return orderYouzanPaymentsMapper.batchInsert(phasePayments);
    }

    @Override
    public List<OrderYouzanPayments> selectByOrderId(Long orderId) {
        return orderYouzanPaymentsMapper.selectByOrderId(orderId);
    }
}

