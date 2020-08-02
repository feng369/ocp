package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderBigItem;

import java.util.List;

/**
 * 标题：大订单明细service
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.order.service
 * 作者：songping
 * 创建日期：2019/12/19 17:32
 *
 * @version 1.0
 */
public interface OrderBigItemService {

    int insert(OrderBigItem record);

    void insertOrderBigItems(List<OrderBigItem> goods);

    OrderBigItem selectById(Long id);

    List<OrderBigItem> selectByMainId(Long id);

    List<OrderBigItem> selectByOrderId(String orderId);

    int update(OrderBigItem record);

    int delete(Long id);
}
