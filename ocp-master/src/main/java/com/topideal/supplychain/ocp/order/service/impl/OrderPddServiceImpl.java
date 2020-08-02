package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.dto.OrderPddDto;
import com.topideal.supplychain.ocp.order.mapper.OrderPddMapper;
import com.topideal.supplychain.ocp.order.model.OrderPdd;
import com.topideal.supplychain.ocp.order.service.OrderPddService;
import com.topideal.supplychain.security.authorization.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author klw
 * @date 2019/12/11 17:19
 * @description: 拼多多订单service
 */
@Service
public class OrderPddServiceImpl implements OrderPddService {

    @Autowired
    private OrderPddMapper orderPddMapper;

    /**
     * 新增
     * @param record
     * @return
     */
    @Override
    @Transactional
    public int insert(OrderPdd record) {
        record.setCreateBy(Authentication.getUserId());
        record.setCreateTime(new Date());
        return orderPddMapper.insert(record);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public OrderPdd selectById(Long id) {
        return orderPddMapper.selectById(id);
    }

    @Override
    public List<OrderPdd> selectByIds(Long[] ids) {
        return orderPddMapper.selectByIds(ids);
    }

    /**
     * 根据内部订单号查询
     * @param code
     * @return
     */
    @Override
    public OrderPdd selectByCode(String code) {
        return orderPddMapper.selectByCode(code);
    }

    /**
     * 根据订单号去查询
     * @param orderId
     * @return
     */
    @Override
    public List<OrderPdd> selectByOrderId(String orderId) {
        return orderPddMapper.selectByOrderId(orderId);
    }

    /**
     * 页面分页查询数据
     * @param condition
     * @return
     */
    @Override
    public List<OrderPddDto> pageList(OrderPddDto condition) {
        return orderPddMapper.pageList(condition);
    }

    /**
     * 更新
     * @param record
     * @return
     */
    @Override
    @Transactional
    public int update(OrderPdd record) {
        record.setUpdateBy(Authentication.getUserId());
        record.setUpdateTime(new Date());
        return orderPddMapper.update(record);
    }

    /**
     * 更新订单状态
     * @param id
     * @param status
     * @param reason
     * @return
     */
    @Override
    @Transactional
    public int updateOrderStatus(Long id, String status, String reason) {
        return orderPddMapper.updateOrderStatus(id, status, reason);
    }

    /**
     * 更新订单税价
     * @param id
     * @param geminiTotalPrice
     * @param taxFcy
     * @return
     */
    @Override
    @Transactional
    public int updateTax(Long id, BigDecimal geminiTotalPrice, BigDecimal taxFcy) {
        return orderPddMapper.updateTax(id, geminiTotalPrice, taxFcy);
    }
}
