package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.dto.OrderYouzanDto;
import com.topideal.supplychain.ocp.order.mapper.OrderYouzanExMapper;
import com.topideal.supplychain.ocp.order.mapper.OrderYouzanMapper;
import com.topideal.supplychain.ocp.order.model.OrderYouzan;
import com.topideal.supplychain.ocp.order.model.OrderYouzanEx;
import com.topideal.supplychain.ocp.order.service.OrderYouzanExService;
import com.topideal.supplychain.ocp.order.service.OrderYouzanService;
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
public class OrderYouzanExServiceImpl implements OrderYouzanExService {

    @Autowired
    private OrderYouzanExMapper orderYouzanExMapper;

    @Override
    @Transactional
    public void insert(OrderYouzanEx youZanOrderEx) {
        orderYouzanExMapper.insert(youZanOrderEx);
    }

    @Override
    public OrderYouzanEx selectByOrderId(Long orderId) {
        return orderYouzanExMapper.selectByOrderId(orderId);
    }
}
