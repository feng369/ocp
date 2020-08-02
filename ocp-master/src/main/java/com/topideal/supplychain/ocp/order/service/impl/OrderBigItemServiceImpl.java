package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.order.mapper.OrderBigItemMapper;
import com.topideal.supplychain.ocp.order.model.OrderBigItem;
import com.topideal.supplychain.ocp.order.service.OrderBigItemService;
import com.topideal.supplychain.security.authorization.Authentication;
import com.topideal.supplychain.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 标题：大订单明细service
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.order.service.impl
 * 作者：songping
 * 创建日期：2019/12/19 17:51
 *
 * @version 1.0
 */
@Service
public class OrderBigItemServiceImpl implements OrderBigItemService {

    @Autowired
    private OrderBigItemMapper goodsBigMapper;

    @Override
    @Transactional
    public int insert(OrderBigItem record) {
        record.setCreateTime(DateUtils.getNowTime());
        record.setCreateBy(Authentication.getUserId());
        return goodsBigMapper.insert(record);
    }

    @Override
    @Transactional
    public void insertOrderBigItems(List<OrderBigItem> goods) {
        if (CollectionUtils.isEmpty(goods)){
            return;
        }
        Date nowTime = DateUtils.getNowTime();
        Long userId = Authentication.getUserId();
        for (OrderBigItem item : goods) {
            item.setCreateBy(userId);
            item.setCreateTime(nowTime);
        }
        goodsBigMapper.insertBatch(goods);
    }

    @Override
    public OrderBigItem selectById(Long id) {
        if (id == null) {
            return null;
        }
        return goodsBigMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderBigItem> selectByMainId(Long id) {
        if (id == null) {
            return null;
        }
        return goodsBigMapper.selectByMainId(id);
    }

    @Override
    public List<OrderBigItem> selectByOrderId(String orderId) {
        if (orderId == null) {
            return null;
        }
        return goodsBigMapper.selectByOrderId(orderId);
    }

    @Override
    @Transactional
    public int update(OrderBigItem record) {
        return goodsBigMapper.update(record);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return goodsBigMapper.delete(id);
    }
}
