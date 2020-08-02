package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.order.mapper.OrderGlobalMapper;
import com.topideal.supplychain.ocp.order.model.OrderGlobal;
import com.topideal.supplychain.ocp.order.service.OrderGlobalService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.service.impl</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/10 08:52</p>
 *
 * @version 1.0
 */
@Service
public class OrderGlobalServiceImpl implements OrderGlobalService {

    @Autowired
    private OrderGlobalMapper mapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    public List<OrderGlobal> pageList(OrderGlobal filter) {
        return mapper.pageList(filter);
    }

    @Override
    public List<OrderGlobal> selectByIds(List<Long> ids) {
        return mapper.selectByIds(ids);
    }

    @Override
    @Transactional
    public void insert(OrderGlobal orderGlobal) {
        if (StringUtils.isEmpty(orderGlobal.getCode())) {
            String code = codeGeneratorService.generate(CodeRuleConstants.RULE_GB);
            orderGlobal.setCode(code);
        }
        orderGlobal.setCreateTime(new Date());
        orderGlobal.setCreateBy(Authentication.getUserId());
        mapper.insert(orderGlobal);
    }

    @Override
    public OrderGlobal selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    @Transactional
    public void update(OrderGlobal orderGlobal) {
        orderGlobal.setUpdateBy(Authentication.getUserId());
        orderGlobal.setUpdateTime(new Date());
        mapper.update(orderGlobal);
    }

    /**
     * 如果存在则不新增
     * @param orderGlobal
     * @return
     */
    @Override
    @Transactional
    public int insertNotExist(OrderGlobal orderGlobal) {
        if (StringUtils.isEmpty(orderGlobal.getCode())) {
            String code = codeGeneratorService.generate(CodeRuleConstants.RULE_GB);
            orderGlobal.setCode(code);
        }
        orderGlobal.setCreateTime(new Date());
        orderGlobal.setCreateBy(Authentication.getUserId());
        return mapper.insertNotExist(orderGlobal);
    }
}
