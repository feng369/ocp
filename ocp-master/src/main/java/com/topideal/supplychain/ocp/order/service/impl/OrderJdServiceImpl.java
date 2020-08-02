package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.mq.MessageSender;
import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.mq.QueueConstants.QueueEnum;
import com.topideal.supplychain.ocp.order.dto.OrderJdDto;
import com.topideal.supplychain.ocp.order.mapper.OrderJdMapper;
import com.topideal.supplychain.ocp.order.model.OrderJd;
import com.topideal.supplychain.ocp.order.service.OrderJdService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderJdServiceImpl implements OrderJdService {

    @Autowired
    private OrderJdMapper mapper;

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Autowired
    private MessageSender messageSender;

    @Override
    @Transactional
    public void insert(OrderJd orderJd) {
        orderJd.setCreateBy(Authentication.getUserId());
        orderJd.setCreateTime(DateUtils.getNowTime());
        orderJd.setTenantId(Authentication.getUser().getTenantId());
        orderJd.setCode(codeGeneratorService.generate(CodeRuleConstants.RULR_JD));
        mapper.insert(orderJd);
    }

    @Override
    public List<OrderJd> pageList(OrderJdDto filter) {
        return mapper.pageList(filter);
    }

    @Override
    public OrderJd selectById(Long id) {
        return mapper.selectById(id);
    }

    @Transactional
    @Override
    public void updateOrderStatus(Long id, String status, String msg) {
        mapper.updateOrderStatus(id, status, msg);
    }

    @Override
    @Transactional
    public void rePush(Long[] ids) {
        List<OrderJd> list = mapper.selectListByIds(ids);
        for (OrderJd orderJd : list) {
            BusinessAssert.assertIsTrue(OrderStatusEnum.SEND_SUCCESS == orderJd.getStatus(),String.format("订单%s不是下发成功状态",orderJd.getCode()));
            messageSender.send(QueueEnum.OCP_JD_ORDER_TO_OFC,new BasicMessage(orderJd.getId(),orderJd.getCode()));
        }
    }

    @Override
    public boolean isExist(String orderId,OrderStatusEnum orderStatus) {
        return mapper.isExist(orderId,orderStatus.getValue());
    }

    /*@Override
    public String selectSensitiveData(String propertyName, long orderId) {
        return mapper.selectSensitiveData(propertyName,orderId);
    }*/
}
