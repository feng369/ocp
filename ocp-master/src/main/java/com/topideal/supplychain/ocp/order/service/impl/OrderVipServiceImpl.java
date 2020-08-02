package com.topideal.supplychain.ocp.order.service.impl;

import com.thoughtworks.xstream.core.SequenceGenerator;
import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.order.mapper.OrderVipMapper;
import com.topideal.supplychain.ocp.order.model.OrderVip;
import com.topideal.supplychain.ocp.order.service.OrderVipService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.service.impl</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/11/27 18:29</p>
 *
 * @version 1.0
 */
@Service
public class OrderVipServiceImpl implements OrderVipService {

    @Autowired
    private OrderVipMapper mapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    public List<OrderVip> pageList(OrderVip filter) {
        return mapper.pageList(filter);
    }

    @Override
    public List<OrderVip> selectByIds(List<Long> ids) {
        return mapper.selectByIds(ids);
    }

    @Override
    public OrderVip selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    @Transactional
    public void update(OrderVip updateEntity) {
        mapper.update(updateEntity);
    }

    @Override
    @Transactional
    public void insert(OrderVip orderVip) {
        if (StringUtils.isEmpty(orderVip.getCode())) {
            String code = codeGeneratorService.generate(CodeRuleConstants.RULE_VIP);
            orderVip.setCode(code);
        }
        orderVip.setCreateTime(new Date());
        orderVip.setCreateBy(Authentication.getUserId());
        mapper.insert(orderVip);
    }

    @Override
    @Transactional
    public void updateByIds(OrderVip orderVip) {
        mapper.updateByIds(orderVip);
    }

}
