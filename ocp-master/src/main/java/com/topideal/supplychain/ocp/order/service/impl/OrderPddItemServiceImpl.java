package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderPddItemMapper;
import com.topideal.supplychain.ocp.order.model.OrderPddItem;
import com.topideal.supplychain.ocp.order.service.OrderPddItemService;
import com.topideal.supplychain.security.authorization.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author klw
 * @date 2019/12/11 17:12
 * @description: 拼多多订单明细service
 */
@Service
public class OrderPddItemServiceImpl implements OrderPddItemService {

    @Autowired
    private OrderPddItemMapper orderPddItemMapper;

    /**
     * 批量插入
     * @param list
     * @return
     */
    @Override
    @Transactional
    public int insertBatch(List<OrderPddItem> list, Long orderId) {
        return orderPddItemMapper.insertBatch(list, orderId, new Date());
    }

    /**
     * 根据订单id查询
     * @param orderId
     * @return
     */
    @Override
    public List<OrderPddItem> selectByOrderId(Long orderId) {
        return orderPddItemMapper.selectByOrderId(orderId);
    }

    /**
     * 更新
     * @param record
     * @return
     */
    @Override
    @Transactional
    public int update(OrderPddItem record) {
        record.setUpdateBy(Authentication.getUserId());
        record.setUpdateTime(new Date());
        return orderPddItemMapper.update(record);
    }

    @Override
    @Transactional
    public int updateTax(Long id, BigDecimal totalPrice, BigDecimal price) {
        return orderPddItemMapper.updateTax(id, totalPrice, price);
    }
}
