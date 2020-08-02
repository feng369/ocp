package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.dto.OrderXiaomiDto;
import com.topideal.supplychain.ocp.order.model.OrderXiaomi;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/11/28
 * @description 小米订单
 **/
public interface OrderXiaomiService {

    List<OrderXiaomiDto> pageList(OrderXiaomiDto filter);


    OrderXiaomi selectById(Long id);

    OrderXiaomi selectByOrderId(String id);

    OrderXiaomiDto selectDtoById(Long id);

    void insert(OrderXiaomi orderXiaomi);

    /**
     * 更新状态
     * @param id
     * @param status
     * @param reason
     */
    void updateOrderStatus(Long id, String status, String reason);


    List<OrderXiaomi> selectByIds(List<Long> ids);
}
