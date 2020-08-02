package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.order.dto.OrderGsDto;
import com.topideal.supplychain.ocp.order.mapper.OrderGsMapper;
import com.topideal.supplychain.ocp.order.model.OrderGs;
import com.topideal.supplychain.ocp.order.service.OrderGsService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2020 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.service.impl</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2020-01-13 17:47</p>
 *
 * @version 1.0
 */
@Service
public class OrderGsServiceImpl implements OrderGsService {
    @Autowired
    private OrderGsMapper mapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    public List<OrderGs> pageList(OrderGsDto orderGsDto) {
        return mapper.pageList(orderGsDto);
    }

    @Override
    @Transactional
    public void insert(OrderGs orderGs) {
        orderGs.setCode(codeGeneratorService.generate(CodeRuleConstants.RULR_GS));
        orderGs.setCreateTime(new Date());
        orderGs.setCreateBy(Authentication.getUserId());
        orderGs.setTenantId(Authentication.getUser().getTenantId());
        mapper.insert(orderGs);
    }

    @Override
    public void updateOrderStatus(Long id, String status, String reason, String kjbStatus) {
        mapper.updateOrderStatus(id, status, reason, kjbStatus);
    }

    @Override
    public void updateOrderStatus(Long id, String status, String reason) {
        mapper.updateOrderStatus(id, status, reason, null);
    }

    @Override
    public void updateForwardSystem(Long id, ForwardSystemEnum forwardSystem) {
        mapper.updateForwardSystem(id, forwardSystem);
    }

    @Override
    public List<OrderGs> selectByIds(List<Long> ids) {
        return mapper.selectByIds(ids);
    }

    @Override
    public OrderGs selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<OrderGs> selectByConsignCode(String consignCode) {
        return mapper.selectByConsignCode(consignCode);
    }
}
