package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderYouzanChild;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderYouzanChildMapper {
    int deleteById(Long id);

    int insert(OrderYouzanChild record);

    OrderYouzanChild selectById(Long id);

    int updateById(OrderYouzanChild record);

    int batchInsert(List<OrderYouzanChild> list);

}