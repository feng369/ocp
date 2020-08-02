package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.mq.QueueConstants.QueueEnum;
import com.topideal.supplychain.ocp.order.dto.OrderYouzanDto;
import com.topideal.supplychain.ocp.order.mapper.OrderYouzanMapper;
import com.topideal.supplychain.ocp.order.model.OrderYouzan;
import com.topideal.supplychain.ocp.order.service.OrderYouzanService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName OrderYouzanServiceImpl
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/22 14:33
 * @Version 1.0
 **/
@Service
public class OrderYouzanServiceImpl implements OrderYouzanService {

    @Autowired
    private OrderYouzanMapper orderYouzanMapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;
    @Autowired
    private MessageSender messageSender;

    @Override
    public List<OrderYouzanDto> pageList(OrderYouzanDto orderYouzanDto) {
        return orderYouzanMapper.pageList(orderYouzanDto);
    }

    @Override
    @Transactional
    public void insert(OrderYouzan youZanOrder) {
        youZanOrder.setCode(codeGeneratorService.generate(CodeRuleConstants.RULE_YZ));
        youZanOrder.setCreateBy(Authentication.getUserId());
        youZanOrder.setCreateTime(DateUtils.getNowTime());
        youZanOrder.setTenantId(Authentication.getUser().getTenantId());
        orderYouzanMapper.insert(youZanOrder);
    }

    @Override
    public boolean isExist(String tid, String subOrderNo,String... orderStatus) {
        return orderYouzanMapper.isExist(tid, subOrderNo,orderStatus);
    }

    @Override
    public OrderYouzan selectByTid(String tid) {
        return orderYouzanMapper.selectByTid(tid);
    }

    @Override
    public OrderYouzan orderIsExist(String tid) {
        return orderYouzanMapper.orderIsExist(tid);
    }

    @Override
    public OrderYouzan selectById(Long id) {
        return orderYouzanMapper.selectById(id);
    }

    @Override
    public OrderYouzanDto selectDtoById(Long id) {
        return orderYouzanMapper.selectDtoById(id);
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long id, String status, String reason) {
        orderYouzanMapper.updateOrderStatus(id, status, reason,null);
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long id, String status, String reason,String kjbStatus) {
        orderYouzanMapper.updateOrderStatus(id, status, reason,kjbStatus);
    }

    @Override
    @Transactional
    public void updateForwardSystem(Long id, ForwardSystemEnum forwardSystem) {
        orderYouzanMapper.updateForwardSystem(id,forwardSystem);
    }

    @Override
    @Transactional
    public void rePush(Long[] ids) {
        List<OrderYouzan> list = orderYouzanMapper.selectListByIds(ids);
        for (OrderYouzan orderYouzan : list) {
            BusinessAssert.assertIsTrue(OrderStatusEnum.SEND_SUCCESS == orderYouzan.getToStatus(),String.format("订单%s不是下发成功状态",orderYouzan.getCode()));
            QueueEnum queueEnum = QueueEnum.getQueueEnumByName("ocp.youzan.order.send."+orderYouzan.getToSystem().getQueue());
            messageSender.send(queueEnum,new BasicMessage(orderYouzan.getId(),orderYouzan.getCode()));
        }
    }
}