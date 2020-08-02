package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderYmatou;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderYmatouMapper {

    List<OrderYmatou> pageList(OrderYmatou order);


    int deleteById(Long id);


    int insert(OrderYmatou record);

    List<OrderYmatou> selectByIds(List<Long> ids);

    OrderYmatou selectById(Long id);

    int updateById(OrderYmatou record);


}