package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.model.OrderPddItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Mapper
public interface OrderPddItemMapper {

    int insertBatch(@Param("list") List<OrderPddItem> list,@Param("orderId") Long orderId, @Param("createTime") Date createTime);

    List<OrderPddItem> selectByOrderId(Long orderId);

    int update(OrderPddItem record);

    int updateTax(@Param("id") Long id, @Param("totalPrice")BigDecimal totalPrice, @Param("price") BigDecimal price);
}