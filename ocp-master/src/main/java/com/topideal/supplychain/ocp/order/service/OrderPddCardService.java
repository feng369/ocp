package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderPddCard;

import java.util.List;

/**
 * @author klw
 * @date 2019/11/18 11:41
 * @description: 拼多多抓单卡密service
 */
public interface OrderPddCardService {
    int insertBatch(List<OrderPddCard> list, Long orderId);

    OrderPddCard selectByOrderId(Long orderId);
}
