package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderPddCard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderPddCardMapper {

    int insertBatch(@Param("list") List<OrderPddCard> list, @Param("orderId")Long orderId,
                    @Param("createTime")Date createTime);

    OrderPddCard selectByOrderId(Long orderId);
}