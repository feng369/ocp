package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.dto.OrderBaomaDto;
import com.topideal.supplychain.ocp.order.model.OrderBaoma;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/11/28
 * @description 宝妈时光订单
 **/
public interface OrderBaomaService {

    List<OrderBaoma> pageList(OrderBaoma filter);


    OrderBaoma selectById(Long id);

    OrderBaomaDto selectDtoById(Long id);

    void insert(OrderBaoma orderBaoma);

    /**
     * 更新状态
     * @param id
     * @param status
     * @param reason
     */
    void updateOrderStatus(Long id,String status,String reason);

    /**
     * 更新发送系统
     * @param id
     * @param sendSystem
     */
    void updateSendSystem(Long id,String sendSystem);

    List<OrderBaoma> selectByIds(List<Long> ids);
}
