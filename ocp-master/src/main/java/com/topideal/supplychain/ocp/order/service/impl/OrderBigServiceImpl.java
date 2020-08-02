package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.order.dto.OrderBigPageRequestDto;
import com.topideal.supplychain.ocp.order.mapper.OrderBigMapper;
import com.topideal.supplychain.ocp.order.model.OrderBig;
import com.topideal.supplychain.ocp.order.service.OrderBigService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 标题：大订单service
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.order.service.impl
 * 作者：songping
 * 创建日期：2019/12/16 14:21
 *
 * @version 1.0
 */
@Service
public class OrderBigServiceImpl implements OrderBigService {

    @Autowired
    private OrderBigMapper orderBigMapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    public List<OrderBig> pageList(OrderBigPageRequestDto filter) {
        return orderBigMapper.pageList(filter);
    }

    @Override
    public OrderBig selectById(Long id) {
        return orderBigMapper.selectByPrimaryKey(id);
    }

    @Override
    public OrderBig selectByCode(String code) {
        return orderBigMapper.selectByCode(code);
    }

    @Override
    public List<OrderBig> selectByIds(List<Long> ids) {
        if (null==ids || ids.size()==0)
        {
            return null;
        }
        return orderBigMapper.selectByIds(ids);
    }

    @Override
    public String selectSensitiveData(String propertyName, Long id) {
        return orderBigMapper.selectSensitiveData(propertyName, id);
    }

    @Override
    @Transactional
    public void update(OrderBig updateOrder) {
        updateOrder.setUpdateBy(Authentication.getUserId());
        updateOrder.setUpdateTime(DateUtils.getNowTime());
        orderBigMapper.update(updateOrder);
    }

    @Override
    @Transactional
    public void insert(OrderBig order) {
        order.setCode(codeGeneratorService.generate(CodeRuleConstants.RULE_BIG));
        order.setPushStatus(OrderStatusEnum.INIT);
        order.setCreateTime(DateUtils.getNowTime());
        order.setCreateBy(Authentication.getUserId());
        orderBigMapper.insert(order);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        orderBigMapper.delete(id);
    }

    @Override
    @Transactional
    public void updatePushStatus(Long id, String pushSystem, OrderStatusEnum pushStatus, String notes) {
        OrderBig orderBig = new OrderBig();
        orderBig.setId(id);
        orderBig.setPushSystem(pushSystem);
        orderBig.setPushStatus(pushStatus);
        orderBig.setPushNotes(notes);
        this.update(orderBig);
    }

    @Override
    public OrderBig selectByConditon(String electricCode, String cbepcomCode, String busiMode,
            String orderId) {
        return orderBigMapper.selectByConditon(electricCode, cbepcomCode, busiMode, orderId);
    }
}
