package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.enums.YouZanOrderNewEnum;
import com.topideal.supplychain.ocp.order.model.OrderYouzanItem;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderYouzanItemMapper {
    int deleteById(Long id);

    int insert(OrderYouzanItem record);

    OrderYouzanItem selectById(Long id);

    int updateById(OrderYouzanItem record);

    int batchInsert(List<OrderYouzanItem> list);

    List<OrderYouzanItem> selectByOrderId(@Param("orderId") Long orderId, @Param("orderNewEnum") YouZanOrderNewEnum old);
}