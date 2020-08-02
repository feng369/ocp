package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.order.dto.OrderBaomaDto;
import com.topideal.supplychain.ocp.order.model.OrderBaoma;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderBaomaMapper {

    List<OrderBaoma> pageList(OrderBaoma order);

    int insert(OrderBaoma record);

    OrderBaoma selectById(Long id);

    OrderBaomaDto selectDtoById(Long id);

    int updateById(OrderBaoma record);

    void updateOrderStatus(@Param("id") Long id, @Param("status") String status, @Param("reason") String reason);

    void updateSendSystem(@Param("id") Long id, @Param("system") String system);

    List<OrderBaoma> selectByIds(List<Long> ids);
}