package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.dto.OrderPddDto;
import com.topideal.supplychain.ocp.order.model.OrderPdd;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author klw
 * @date 2019/11/18 11:41
 * @description: 拼多多订单service
 */
public interface OrderPddService {

    int insert(OrderPdd record);

    OrderPdd selectById(Long id);

    List<OrderPdd> selectByIds(Long[] ids);

    OrderPdd selectByCode(String code);

    List<OrderPdd> selectByOrderId(String orderId);

    List<OrderPddDto> pageList(OrderPddDto condition);

    int update(OrderPdd record);

    int updateOrderStatus(Long id, String status, String reason);

    int updateTax(Long id, BigDecimal geminiTotalPrice, BigDecimal taxFcy);
}
