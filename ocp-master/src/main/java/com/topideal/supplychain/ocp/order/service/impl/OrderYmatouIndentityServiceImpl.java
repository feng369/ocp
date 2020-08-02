package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderYmatouIndentityMapper;
import com.topideal.supplychain.ocp.order.model.OrderYmatouIndentity;
import com.topideal.supplychain.ocp.order.service.OrderYmatouIndentityService;
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
public class OrderYmatouIndentityServiceImpl implements OrderYmatouIndentityService {
    @Autowired
    private OrderYmatouIndentityMapper mapper;

    @Override
    @Transactional
    public void insert(OrderYmatouIndentity bean) {
        mapper.insert(bean);
    }

    /**
     * 通过主订单号查找
     *
     * @param msgOrderId
     * @return
     */
    @Override
    public List<OrderYmatouIndentity> selectByMsgOrderId(Long msgOrderId) {
        return mapper.selectByMsgOrderId(msgOrderId);
    }
}
