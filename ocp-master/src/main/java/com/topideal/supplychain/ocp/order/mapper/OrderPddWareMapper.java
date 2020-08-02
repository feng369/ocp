package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderPddWare;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderPddWareMapper {

    int insertBatch(@Param("list") List<OrderPddWare> list, @Param("orderId")Long orderId,
                    @Param("createTime") Date createTime);

    OrderPddWare selectByOrderId(Long orderId);
}