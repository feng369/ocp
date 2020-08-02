package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.enums.GsIsSendKjbEnum;
import com.topideal.supplychain.ocp.order.mapper.OrderGsItemMapper;
import com.topideal.supplychain.ocp.order.model.OrderGsItem;
import com.topideal.supplychain.ocp.order.service.OrderGsItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2020 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.order.service.impl</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2020-01-14 12:00</p>
 *
 * @version 1.0
 */
@Service
public class OrderGsItemServiceImpl implements OrderGsItemService {
    @Autowired
    private OrderGsItemMapper orderGsItemMapper;

    @Override
    public List<OrderGsItem> selectByOrderId(Long id, GsIsSendKjbEnum old) {
        return orderGsItemMapper.selectByOrderId(id, old);
    }

    @Override
    @Transactional
    public void batchInsert(List<OrderGsItem> goodsList) {
        orderGsItemMapper.batchInsert(goodsList);
    }
}
