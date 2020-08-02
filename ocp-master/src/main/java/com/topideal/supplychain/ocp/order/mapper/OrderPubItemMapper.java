package com.topideal.supplychain.ocp.order.mapper;


import com.topideal.supplychain.ocp.order.model.OrderPubItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderPubItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderPubItem record);

    OrderPubItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderPubItem record);

    int updateByPrimaryKey(OrderPubItem record);

    List<OrderPubItem> selectByOrderId(Long orderId);

    void batchInsert(@Param("orderId") Long orderId, @Param("goodsList") List<OrderPubItem> goodsList);
}