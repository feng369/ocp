package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderVipItemMapper;
import com.topideal.supplychain.ocp.order.model.OrderVipItem;
import com.topideal.supplychain.ocp.order.service.OrderVipItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.service.impl</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/11/28 09:47</p>
 *
 * @version 1.0
 */
@Service
public class OrderVipItemServiceImpl implements OrderVipItemService {
    @Autowired
    private OrderVipItemMapper mapper;


    @Override
    public List<OrderVipItem> selectByOrderId(Long orderId) {
        return mapper.selectByOrderId(orderId);
    }

    @Override
    @Transactional
    public void batchInsert(Long orderId, List<OrderVipItem> goodsList) {
        mapper.batchInsert(orderId, goodsList);
    }
}
