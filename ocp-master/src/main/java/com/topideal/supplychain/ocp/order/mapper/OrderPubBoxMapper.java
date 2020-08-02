package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderPubBox;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderPubBoxMapper {

    int deleteById(Long id);

    int insert(OrderPubBox record);

    OrderPubBox selectById(Long id);

    int updateById(OrderPubBox record);

    List<OrderPubBox> selectByOrderId(Long orderId);

    void batchInsert(@Param("orderId") Long orderId, @Param("boxList") List<OrderPubBox> boxList);
}