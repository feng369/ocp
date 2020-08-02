package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderYouzanChildMapper;
import com.topideal.supplychain.ocp.order.model.OrderYouzanChild;
import com.topideal.supplychain.ocp.order.service.OrderYouzanChildService;
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
public class OrderYouzanChildServiceImpl implements OrderYouzanChildService {

    @Autowired
    private OrderYouzanChildMapper orderYouzanChildMapper;


    @Override
    @Transactional
    public void insert(OrderYouzanChild orderYouzanChild) {
        orderYouzanChildMapper.insert(orderYouzanChild);
    }

    @Override
    @Transactional
    public int batchInsert(List<OrderYouzanChild> childOrders) {
        return orderYouzanChildMapper.batchInsert(childOrders);
    }
}
