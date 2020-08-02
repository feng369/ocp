package com.topideal.supplychain.ocp.order.mapper;


import com.topideal.supplychain.ocp.order.model.OrderVipItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderVipItemMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderVipItem record);

    OrderVipItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderVipItem record);

    int updateByPrimaryKey(OrderVipItem record);

    List<OrderVipItem> selectByOrderId(Long orderId);

    void batchInsert(@Param("orderId") Long orderId, @Param("goodsList") List<OrderVipItem> goodsList);
}