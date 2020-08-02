package com.topideal.supplychain.ocp.order.service.impl;

import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.order.dto.OrderDalingDto;
import com.topideal.supplychain.ocp.order.mapper.OrderDalingMapper;
import com.topideal.supplychain.ocp.order.model.OrderDaling;
import com.topideal.supplychain.ocp.order.service.OrderDalingService;
import com.topideal.supplychain.security.authorization.Authentication;
import java.util.Date;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 达令家
 *
 * @author xuxiaoyan
 * @date 2019-11-29 15:26
 */
@Service
public class OrderDalingServiceImpl implements OrderDalingService {

    @Autowired
    private OrderDalingMapper orderDalingMapper;

    /**
     * 分页查询
     *
     * @param filter
     * @return
     */
    @Override
    public List<OrderDalingDto> pageList(OrderDalingDto filter) {
        return orderDalingMapper.pageList(filter);
    }

    @Override
    public OrderDaling selectById(Long id) {
        return orderDalingMapper.selectById(id);
    }

    @Override
    public List<String> selectExistOrderNo(List<String> orderNoList) {
        return orderDalingMapper.selectExistOrderNo(orderNoList);
    }

    @Override
    public String selectInfoDataWithId(long id) {
        return orderDalingMapper.selectInfoDataWithId(id);
    }

    @Override
    public List<OrderDaling> selectByIds(Long[] ids) {
        return orderDalingMapper.selectByIds(ids);
    }

    /**
     * 更新订单状态
     *
     * @param id
     * @param value
     * @param message
     */
    @Override
    @Transactional
    public void updateOrderStatus(Long id, String value, String message) {
        orderDalingMapper.updateOrderStatus(id, value, message);
    }

    /**
     * 更新详情信息
     *
     * @param orderId
     * @param grabInfo
     * @param status
     * @param logisticsNumber
     */
    @Override
    @Transactional
    public void updateInfoData(Long orderId, OrderStatusEnum status, String grabInfo,
                               String logisticsNumber,String sendSystem) {
        orderDalingMapper.updateInfoData(orderId, status, grabInfo, logisticsNumber,sendSystem);
    }

    @Override
    public void insertOrderNoWithDefaultValue(List<OrderDaling> orderList) {
        if (CollectionUtils.isNotEmpty(orderList)) {
            orderDalingMapper.insertOrderNoWithDefaultValue(orderList);
        }
    }

    @Override
    @Transactional
    public void insert(OrderDaling orderDaling) {
        orderDaling.setCreateTime(new Date());
        orderDaling.setCreateBy(Authentication.getUserId());
        orderDalingMapper.insert(orderDaling);
    }

}
