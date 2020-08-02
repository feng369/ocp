package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.order.dto.OrderBaomaDto;
import com.topideal.supplychain.ocp.order.mapper.OrderBaomaMapper;
import com.topideal.supplychain.ocp.order.model.OrderBaoma;
import com.topideal.supplychain.ocp.order.service.OrderBaomaService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/12
 * @description
 **/
@Service
public class OrderBaomaServiceImpl implements OrderBaomaService {

    @Autowired
    private OrderBaomaMapper mapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    public List<OrderBaoma> pageList(OrderBaoma filter) {
        return mapper.pageList(filter);
    }

    @Override
    public OrderBaoma selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public OrderBaomaDto selectDtoById(Long id) {
        return mapper.selectDtoById(id);
    }

    @Override
    @Transactional
    public void insert(OrderBaoma orderBaoma) {
        orderBaoma.setCreateBy(Authentication.getUserId());
        orderBaoma.setCreateTime(DateUtils.getNowTime());
        orderBaoma.setCode(codeGeneratorService.generate(CodeRuleConstants.RULE_BM));
        mapper.insert(orderBaoma);
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
      mapper.updateOrderStatus(id,status,reason);
    }

    /**
     * 更新发送系统
     *
     * @param id
     * @param sendSystem
     */
    @Override
    public void updateSendSystem(Long id, String sendSystem) {
        mapper.updateSendSystem(id,sendSystem);
    }

    @Override
    public List<OrderBaoma> selectByIds(List<Long> ids) {
        return mapper.selectByIds(ids);
    }
}
