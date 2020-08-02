package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderVip;

import java.util.List;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.service</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/11/27 18:28</p>
 *
 * @version 1.0
 */
public interface OrderVipService {
    List<OrderVip> pageList(OrderVip filter);

    List<OrderVip> selectByIds(List<Long> ids);

    OrderVip selectById(Long id);

    void update(OrderVip updateEntity);

    void insert(OrderVip orderVip);

    void updateByIds(OrderVip orderVip);
}
