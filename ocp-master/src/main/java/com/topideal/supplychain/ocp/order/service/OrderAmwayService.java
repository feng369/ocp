package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.order.dto.OrderAmwayDto;
import com.topideal.supplychain.ocp.order.model.OrderAmway;


import java.util.List;

public interface OrderAmwayService {
    void insert(OrderAmway orderAmway);

    OrderAmway selectById(Long id);

    List<OrderAmwayDto> pageList(OrderAmwayDto filter);

    List<OrderAmway> selectByIds(Long[] ids);


    void updateOrderStatus(Long id, String status);

    /**
     * 更新转发系统
     * @param id
     * @param forwardSystem
     */
    void updateForwardSystem(Long id, ForwardSystemEnum forwardSystem);
}
