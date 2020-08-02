package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.order.mapper.OrderKjbMapper;
import com.topideal.supplychain.ocp.order.model.OrderKjb;
import com.topideal.supplychain.ocp.order.service.OrderKjbService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @description: 跨境宝订单
 * @author: syq
 * @create: 2019-12-12 11:29
 **/
@Service
public class OrderKjbServiceImpl implements OrderKjbService {

    @Autowired
    private OrderKjbMapper orderKjbMapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    public List<OrderKjb> pageList(OrderKjb orderKjb) {
        return orderKjbMapper.pageList(orderKjb);
    }

    @Override
    public OrderKjb selectById(Long id) {
        return orderKjbMapper.selectById(id);
    }

    @Override
    @Transactional
    public void update(OrderKjb orderKjb) {
        orderKjb.setUpdateTime(new Date());
        orderKjb.setUpdateBy(Authentication.getUserId());
        orderKjb.setTenantId(Authentication.getUser().getTenantId());
        orderKjbMapper.update(orderKjb);
    }

    @Override
    public OrderKjb selectExist(String tradeNo, String storeCode, String requestTime) {
        return orderKjbMapper.selectExist(tradeNo, storeCode, requestTime);
    }

    @Override
    @Transactional
    public int insert(OrderKjb record) {
        record.setCode(codeGeneratorService.generate(CodeRuleConstants.RULE_KJB));
        record.setCreateTime(new Date());
        record.setCreateBy(Authentication.getUserId());
        record.setTenantId(Authentication.getUser().getTenantId());
        return orderKjbMapper.insert(record);
    }

    @Override
    public List<OrderKjb> selectByIds(List<Long> ids) {
        return orderKjbMapper.selectByIds(ids);
    }
}
