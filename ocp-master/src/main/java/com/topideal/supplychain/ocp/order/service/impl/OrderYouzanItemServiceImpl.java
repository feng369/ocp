package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.enums.YouZanOrderNewEnum;
import com.topideal.supplychain.ocp.order.mapper.OrderYouzanItemMapper;
import com.topideal.supplychain.ocp.order.model.OrderYouzanItem;
import com.topideal.supplychain.ocp.order.service.OrderYouzanItemService;
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
public class OrderYouzanItemServiceImpl implements OrderYouzanItemService {

    @Autowired
    private OrderYouzanItemMapper orderYouzanItemMapper;

    @Override
    @Transactional
    public void insert(OrderYouzanItem youZanItemOrder) {
        youZanItemOrder.setCreateBy(Authentication.getUserId());
        youZanItemOrder.setCreateTime(DateUtils.getNowTime());
        youZanItemOrder.setTenantId(Authentication.getUser().getTenantId());
        orderYouzanItemMapper.insert(youZanItemOrder);
    }

    @Override
    @Transactional
    public int batchInsert(List<OrderYouzanItem> orderItems) {
        return orderYouzanItemMapper.batchInsert(orderItems);
    }

    @Override
    public List<OrderYouzanItem> selectByOrderId(Long orderId, YouZanOrderNewEnum old) {
        return orderYouzanItemMapper.selectByOrderId(orderId,old);
    }
}
