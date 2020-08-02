package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.order.dto.OrderDxyDto;
import com.topideal.supplychain.ocp.order.mapper.OrderDxyMapper;
import com.topideal.supplychain.ocp.order.model.OrderDxy;
import com.topideal.supplychain.ocp.order.service.OrderDxyService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @ClassName OrderDxyService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/6/30 18:24
 * @Version 1.0
 **/
@Service
public class OrderDxyServiceImpl implements OrderDxyService {

    @Autowired
    private OrderDxyMapper orderDxyMapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    @Transactional
    public void insert(OrderDxy orderDxy) {
        orderDxy.setCreateBy(Authentication.getUserId());
        orderDxy.setCreateTime(DateUtils.getNowTime());
        orderDxy.setTenantId(Authentication.getUser().getTenantId());
        orderDxy.setCode(codeGeneratorService.generate(CodeRuleConstants.RULE_DXY));
        orderDxy.setStatus(OrderStatusEnum.INIT);
        orderDxyMapper.insert(orderDxy);
    }

    @Override
    public boolean isExist(String orderId) {
        return orderDxyMapper.isExist(orderId);
    }

    @Override
    public OrderDxy selectById(Long businessId) {
        return orderDxyMapper.selectById(businessId);
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long id, String status) {
        orderDxyMapper.updateOrderStatus(id,status);
    }

    @Override
    public List<OrderDxy> pageList(OrderDxyDto filter) {
        return orderDxyMapper.pageList(filter);
    }

    @Override
    public List<OrderDxy> selectByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return orderDxyMapper.selectByIds(ids);
    }
}
