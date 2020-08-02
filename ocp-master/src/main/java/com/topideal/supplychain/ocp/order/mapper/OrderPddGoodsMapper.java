package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderPddGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
@Mapper
public interface OrderPddGoodsMapper {

    int insertBatch(@Param("list") List<OrderPddGoods> list, @Param("orderId") Long orderId,
                    @Param("createTime")Date createTime);

    List<OrderPddGoods> selectByOrderId(Long orderId);
}