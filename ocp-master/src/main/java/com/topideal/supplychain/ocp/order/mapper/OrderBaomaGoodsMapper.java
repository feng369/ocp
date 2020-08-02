package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderBaomaGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderBaomaGoodsMapper {


    List<OrderBaomaGoods>  selectAllByOrderId(Long orderId);

    int insert(OrderBaomaGoods record);


    OrderBaomaGoods selectById(Long id);



    int updateById(OrderBaomaGoods record);
}