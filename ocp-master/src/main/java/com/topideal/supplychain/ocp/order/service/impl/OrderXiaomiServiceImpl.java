package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.order.dto.OrderXiaomiDto;
import com.topideal.supplychain.ocp.order.mapper.OrderXiaomiMapper;
import com.topideal.supplychain.ocp.order.model.OrderXiaomi;
import com.topideal.supplychain.ocp.order.service.OrderXiaomiService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/30
 * @description
 **/
@Service
public class OrderXiaomiServiceImpl implements OrderXiaomiService {
    @Autowired
    private OrderXiaomiMapper mapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    public List<OrderXiaomiDto> pageList(OrderXiaomiDto filter) {
        return mapper.pageList(filter);
    }

    @Override
    public OrderXiaomi selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public OrderXiaomi selectByOrderId(String id) {
        return mapper.selectByOrderId(id);
    }

    @Override
    public OrderXiaomiDto selectDtoById(Long id) {
        return mapper.selectDtoById(id);
    }

    @Override
    @Transactional
    public void insert(OrderXiaomi orderXiaomi) {
        orderXiaomi.setCode(codeGeneratorService.generate(CodeRuleConstants.RULE_XM));
        orderXiaomi.setCreateBy(Authentication.getUserId());
        orderXiaomi.setCreateTime(DateUtils.getNowTime());
        mapper.insert(orderXiaomi);
    }

    /**
     * 更新状态
     *
     * @param id
     * @param status
     * @param reason
     */
    @Override
    @Transactional
    public void updateOrderStatus(Long id, String status, String reason) {
        mapper.updateOrderStatus(id, status, reason);

    }

    @Override
    public List<OrderXiaomi> selectByIds(List<Long> ids) {
        return mapper.selectByIds(ids);
    }
}
