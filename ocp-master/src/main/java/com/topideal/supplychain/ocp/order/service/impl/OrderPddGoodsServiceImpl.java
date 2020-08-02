package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderPddGoodsMapper;
import com.topideal.supplychain.ocp.order.model.OrderPddGoods;
import com.topideal.supplychain.ocp.order.service.OrderPddGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author klw
 * @date 2019/12/11 17:08
 * @description: 拼多多抓单商品明细service
 */
@Service
public class OrderPddGoodsServiceImpl implements OrderPddGoodsService {

    @Autowired
    private OrderPddGoodsMapper orderPddGoodsMapper;

    /**
     * 批量插入
     * @param list
     * @param orderId
     * @return
     */
    @Override
    @Transactional
    public int insertBatch(List<OrderPddGoods> list, Long orderId) {
        return orderPddGoodsMapper.insertBatch(list, orderId, new Date());
    }

    /**
     * 根据订单id查询
     * @param orderId
     * @return
     */
    @Override
    public List<OrderPddGoods> selectByOrderId(Long orderId) {
        return orderPddGoodsMapper.selectByOrderId(orderId);
    }
}
