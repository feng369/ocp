package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderXiaomiGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderXiaomiGoodsMapper {

    List<OrderXiaomiGoods> selectAllByOrderId(Long orderId);

    int insert(OrderXiaomiGoods record);


    OrderXiaomiGoods selectById(Long id);

}