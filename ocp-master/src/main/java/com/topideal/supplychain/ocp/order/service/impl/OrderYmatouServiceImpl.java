package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.order.mapper.OrderYmatouMapper;
import com.topideal.supplychain.ocp.order.model.OrderYmatou;
import com.topideal.supplychain.ocp.order.service.OrderYmatouService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/11/29
 * @description
 **/
@Service
public class OrderYmatouServiceImpl implements OrderYmatouService {

    @Autowired
    private OrderYmatouMapper orderYmatouMapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    public List<OrderYmatou> pageList(OrderYmatou filter) {
        return orderYmatouMapper.pageList(filter);
    }

    @Override
    @Transactional
    public void insert(OrderYmatou orderYmatou) {
        orderYmatou.setCode(codeGeneratorService.generate(CodeRuleConstants.RULE_YMT));
        orderYmatou.setCreateBy(Authentication.getUserId());
        orderYmatou.setCreateTime(DateUtils.getNowTime());
        orderYmatou.setTenantId(Authentication.getUser().getTenantId());
        orderYmatouMapper.insert(orderYmatou);
    }

    @Override
    public OrderYmatou selectById(Long id) {
        return orderYmatouMapper.selectById(id);
    }

    @Override
    @Transactional
    public void updateById(OrderYmatou orderYmatou) {
        orderYmatou.setUpdateBy(Authentication.getUserId());
        orderYmatou.setUpdateTime(DateUtils.getNowTime());
        orderYmatouMapper.updateById(orderYmatou);
    }

    @Override
    public List<OrderYmatou> selectByIds(List<Long> ids) {
        return orderYmatouMapper.selectByIds(ids);
    }
}
