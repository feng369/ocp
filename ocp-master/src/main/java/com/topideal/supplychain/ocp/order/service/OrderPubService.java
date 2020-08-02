package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderPub;

import java.util.List;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.service</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/2 17:26</p>
 *
 * @version 1.0
 */
public interface OrderPubService {
    List<OrderPub> pageList(OrderPub filter);

    OrderPub selectById(Long id);

    OrderPub selectByStoreAndCode(String storeCode, String orderNo);

    void insert(OrderPub orderPub);

    void update(OrderPub updateEntity);

    List<OrderPub> selectByIds(List<Long> ids);
}
