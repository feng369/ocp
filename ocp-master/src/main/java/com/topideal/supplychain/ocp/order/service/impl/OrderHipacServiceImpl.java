package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.constants.CodeRuleConstants;
import com.topideal.supplychain.ocp.order.dto.OrderHipacDto;
import com.topideal.supplychain.ocp.order.mapper.OrderHipacMapper;
import com.topideal.supplychain.ocp.order.model.OrderHipac;
import com.topideal.supplychain.ocp.order.service.OrderHipacService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.web.system.service.impl.CodeGeneratorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 海拍客订单
 *
 * @author xuxiaoyan
 * @date 2019-12-16 15:17
 */
@Service
public class OrderHipacServiceImpl implements OrderHipacService {

    @Autowired
    private OrderHipacMapper orderHipacMapper;
    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Override
    public List<OrderHipacDto> pageList(OrderHipacDto filter) {
        return orderHipacMapper.pageList(filter);
    }

    @Override
    public OrderHipac selectById(Long id) {
        return orderHipacMapper.selectById(id);
    }

    @Override
    public String selectSensitiveData(String propertyName, long orderId) {
        return orderHipacMapper.selectSensitiveData(propertyName, orderId);
    }

    @Override
    @Transactional
    public void insert(OrderHipac orderHipac) {
        orderHipac.setCreateBy(Authentication.getUserId());
        orderHipac.setCreateTime(DateUtils.getNowTime());
        orderHipac.setUpdateBy(Authentication.getUserId());
        orderHipac.setUpdateTime(DateUtils.getNowTime());
        orderHipac.setTenantId(Authentication.getUser().getTenantId());
        orderHipac.setCode(codeGeneratorService.generate(CodeRuleConstants.RULE_HIPAC));
        orderHipacMapper.insert(orderHipac);
    }

    @Override
    public List<OrderHipac> selectByIds(List<Long> ids) {
        return orderHipacMapper.selectByIds(ids);
    }

    /**
     * 修改订单状态
     * @param id
     * @param status
     * @param msg
     */
    @Override
    @Transactional
    public void updateOrderStatus(Long id, String status, String msg) {
        orderHipacMapper.updateOrderStatus(id, status, msg);
    }
}
