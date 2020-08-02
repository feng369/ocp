package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderYouzanTagsMapper;
import com.topideal.supplychain.ocp.order.model.OrderYouzanTags;
import com.topideal.supplychain.ocp.order.service.OrderYouzanTagsService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
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
public class OrderYouzanTagsServiceImpl implements OrderYouzanTagsService {

    @Autowired
    private OrderYouzanTagsMapper orderYouzanTagsMapper;


    @Override
    @Transactional
    public void insert(OrderYouzanTags youZanOrderTags) {
        orderYouzanTagsMapper.insert(youZanOrderTags);
    }

    @Override
    public OrderYouzanTags selectByOrderId(Long orderId) {
        return orderYouzanTagsMapper.selectByOrderId(orderId);
    }
}
