package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderYouzanTags;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderYouzanTagsMapper {

    int deleteById(Long id);

    int insert(OrderYouzanTags record);

    OrderYouzanTags selectById(Long id);

    int updateById(OrderYouzanTags record);

    OrderYouzanTags selectByOrderId(Long orderId);
}