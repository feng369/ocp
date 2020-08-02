package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderTemp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderTempMapper {

    int deleteById(Long id);

    int insert(OrderTemp record);

    OrderTemp selectById(Long id);

}