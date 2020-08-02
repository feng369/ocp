package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.dto.OrderBeibeiDto;
import com.topideal.supplychain.ocp.order.model.OrderBeibei;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2020/3/18
 * @description 贝贝订单
 **/

public interface OrderBeibeiService {

    List<OrderBeibeiDto> pageList(OrderBeibeiDto filter);

    OrderBeibei selectById(Long id);

    List<OrderBeibei> selectByOId(String id);

    OrderBeibeiDto selectDtoById(Long id);

    void insert(OrderBeibei orderBeibei);

    void updateOrderStatus(Long id,String status,String reason);

    List<OrderBeibei> selectByIds(List<Long> ids);
}
