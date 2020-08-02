package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderPddItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author klw
 * @date 2019/11/18 11:41
 * @description: 拼多多订单明细service
 */
public interface OrderPddItemService {

    int insertBatch(List<OrderPddItem> list, Long orderId);

    List<OrderPddItem> selectByOrderId(Long orderId);

    int update(OrderPddItem record);

    int updateTax(Long id, BigDecimal totalPrice, BigDecimal price);
}
