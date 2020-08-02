package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderYmatouIndentity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderYmatouIndentityMapper {


    int insert(OrderYmatouIndentity record);


    OrderYmatouIndentity selectById(Long id);


    int update(OrderYmatouIndentity record);

    List<OrderYmatouIndentity> selectByMsgOrderId(Long msgOrderId);
}