package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.order.dto.OrderDistributionPageRequestDto;
import com.topideal.supplychain.ocp.order.mapper.OrderDistributionMapper;
import com.topideal.supplychain.ocp.order.model.OrderDistribution;
import com.topideal.supplychain.ocp.order.service.OrderDistributionService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 分销订单
 *
 * @author xuxiaoyan
 * @date 2020-05-21 15:14
 */
@Service
public class OrderDistributionServiceImpl implements OrderDistributionService {

    @Autowired
    private OrderDistributionMapper orderDistributionMapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;


    @Override
    public List<OrderDistribution> pageList(OrderDistributionPageRequestDto filter) {
        return orderDistributionMapper.pageList(filter);
    }

    @Override
    @Transactional
    public void insert(OrderDistribution distribution) {
        distribution.setCode(codeGeneratorService.generate(CodeRuleConstants.RULE_FX));
        distribution.setPushStatus(OrderStatusEnum.INIT);
        distribution.setCreateTime(DateUtils.getNowTime());
        distribution.setCreateBy(Authentication.getUserId());
        distribution.setUpdateTime(DateUtils.getNowTime());
        distribution.setUpdateBy(Authentication.getUserId());
        orderDistributionMapper.insert(distribution);
    }

    @Override
    public OrderDistribution selectById(Long businessId) {
        return orderDistributionMapper.selectById(businessId);
    }

    @Override
    @Transactional
    public void update(OrderDistribution updateOrder) {
        updateOrder.setUpdateTime(DateUtils.getNowTime());
        updateOrder.setUpdateBy(Authentication.getUserId());
        orderDistributionMapper.update(updateOrder);
    }

    @Override
    public List<OrderDistribution> selectByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return orderDistributionMapper.selectByIds(ids);
    }

    @Override
    public OrderDistribution selectByConditon(String electricCode, String cbepcomCode, String busiMode,
            String orderId) {
        return orderDistributionMapper.selectByConditon(electricCode, cbepcomCode, busiMode, orderId);
    }

    @Override
    @Transactional
    public void updateOrderStatus(Long id, String status, String msg) {
        orderDistributionMapper.updateOrderStatus(id, status, msg);
    }
}
