package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.mq.QueueConstants.QueueEnum;
import com.topideal.supplychain.ocp.order.mapper.OrderTempMapper;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName OrderTempServiceImpl
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/22 14:33
 * @Version 1.0
 **/
@Service
public class OrderTempServiceImpl implements OrderTempService {

    @Autowired
    private OrderTempMapper orderTempMapper;
    @Autowired
    private MessageSender messageSender;

    @Override
    @Transactional
    public void insert(OrderTemp orderTemp) {
        orderTemp.setCreateTime(DateUtils.getNowTime());
        orderTemp.setCreateBy(Authentication.getUserId());
        orderTempMapper.insert(orderTemp);
    }


    @Override
    @Transactional
    public void insertAndSendMq(OrderTemp orderTemp, QueueEnum queueEnum,
            BasicMessage basicMessage) {
        this.insert(orderTemp);
        basicMessage.setBusinessId(orderTemp.getId());
        messageSender.send(queueEnum,basicMessage);
    }

    @Override
    public OrderTemp selectById(Long id) {
        return orderTempMapper.selectById(id);
    }

    @Override
    @Transactional
    public int deleteById(Long id) {
        return orderTempMapper.deleteById(id);
    }
}
