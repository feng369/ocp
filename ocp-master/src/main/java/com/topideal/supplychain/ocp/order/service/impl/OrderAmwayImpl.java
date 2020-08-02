package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.order.dto.OrderAmwayDto;
import com.topideal.supplychain.ocp.order.mapper.OrderAmwayMapper;
import com.topideal.supplychain.ocp.order.model.OrderAmway;
import com.topideal.supplychain.ocp.order.service.OrderAmwayService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderAmwayImpl implements OrderAmwayService {
    @Autowired
    private OrderAmwayMapper orderAmwayMapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    @Transactional
    public void insert(OrderAmway orderAmway) {
        orderAmway.setCode(codeGeneratorService.generate(CodeRuleConstants.RULE_AMWAY));
        orderAmway.setCreateBy(Authentication.getUserId());
        orderAmway.setCreateTime(DateUtils.getNowTime());
        orderAmway.setTenantId(Authentication.getUser().getTenantId());
        orderAmwayMapper.insert(orderAmway);
    }

    @Override
    public OrderAmway selectById(Long id) {
        return orderAmwayMapper.selectById(id);
    }

    @Override
    public List<OrderAmwayDto> pageList(OrderAmwayDto filter) {
        return orderAmwayMapper.pageList(filter);
    }

    @Override
    public List<OrderAmway> selectByIds(Long[] ids) {
        return orderAmwayMapper.selectByIds(ids);
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long id, String status) {
        orderAmwayMapper.updateOrderStatus(id,status);
    }

    /**
     * 更新转发系统
     * @param id
     * @param forwardSystem
     */
    @Override
    @Transactional
    public void updateForwardSystem(Long id, ForwardSystemEnum forwardSystem) {
        orderAmwayMapper.updateForwardSystem(id, forwardSystem);
    }


}
