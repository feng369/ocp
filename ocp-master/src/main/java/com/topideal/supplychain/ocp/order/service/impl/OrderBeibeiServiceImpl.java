package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.order.dto.OrderBeibeiDto;
import com.topideal.supplychain.ocp.order.mapper.OrderBeibeiMapper;
import com.topideal.supplychain.ocp.order.model.OrderBeibei;
import com.topideal.supplychain.ocp.order.service.OrderBeibeiService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2020/3/19
 * @description
 **/
@Service
public class OrderBeibeiServiceImpl implements OrderBeibeiService {

    @Autowired
    private OrderBeibeiMapper mapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    public List<OrderBeibeiDto> pageList(OrderBeibeiDto filter) {
        return mapper.pageList(filter);
    }

    @Override
    public OrderBeibei selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<OrderBeibei> selectByOId(String id) {
        return mapper.selectByOId(id);
    }

    @Override
    public OrderBeibeiDto selectDtoById(Long id) {
        return mapper.selectDtoById(id);
    }

    @Override
    @Transactional
    public void insert(OrderBeibei orderBeibei) {
        orderBeibei.setCreateBy(Authentication.getUserId());
        orderBeibei.setCreateTime(DateUtils.getNowTime());
        orderBeibei.setCode(codeGeneratorService.generate(CodeRuleConstants.RULE_BB));
        mapper.insert(orderBeibei);
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long id, String status, String reason) {
        mapper.updateOrderStatus(id,status,reason);
    }

    @Override
    public List<OrderBeibei> selectByIds(List<Long> ids) {
        return mapper.selectByIds(ids);
    }
}
