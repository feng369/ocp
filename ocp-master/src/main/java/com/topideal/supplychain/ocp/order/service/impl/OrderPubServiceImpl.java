package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.order.mapper.OrderPubMapper;
import com.topideal.supplychain.ocp.order.model.OrderPub;
import com.topideal.supplychain.ocp.order.model.OrderVip;
import com.topideal.supplychain.ocp.order.service.OrderPubService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.DoubleStream;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.service.impl</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/2 17:26</p>
 *
 * @version 1.0
 */
@Service
public class OrderPubServiceImpl implements OrderPubService {

    @Autowired
    private OrderPubMapper mapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    public List<OrderPub> pageList(OrderPub filter) {
        return mapper.pageList(filter);
    }

    @Override
    public OrderPub selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public OrderPub selectByStoreAndCode(String storeCode, String orderNo) {
        return mapper.selectByStoreAndCode(storeCode, orderNo);
    }

    @Override
    @Transactional
    public void insert(OrderPub orderPub) {
        if (StringUtils.isEmpty(orderPub.getCode())) {
            String code = codeGeneratorService.generate(CodeRuleConstants.RULE_OCP);
            orderPub.setCode(code);
        }
        orderPub.setCreateBy(Authentication.getUserId());
        orderPub.setCreateTime(new Date());
        orderPub.setUpdateBy(Authentication.getUserId());
        orderPub.setUpdateTime(new Date());
        mapper.insert(orderPub);
    }

    @Override
    @Transactional
    public void update(OrderPub updateEntity) {
        updateEntity.setUpdateBy(Authentication.getUserId());
        updateEntity.setUpdateTime(new Date());
        mapper.update(updateEntity);
    }

    @Override
    public List<OrderPub> selectByIds(List<Long> ids) {
        return mapper.selectByIds(ids);
    }

}
