package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.order.dto.OrderYouzanDto;
import com.topideal.supplychain.ocp.order.model.OrderYouzan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderYouzanMapper {

    int deleteById(Long id);

    int insert(OrderYouzan record);

    OrderYouzan selectById(Long id);

    int updateById(OrderYouzan record);

    List<OrderYouzanDto> pageList(OrderYouzanDto orderYouzanDto);

    //
    boolean isExist(@Param("tid") String tid,@Param("subNo") String subNo,@Param("toStatus") String... toStatus);

    OrderYouzan selectByTid(String tid);

    OrderYouzan orderIsExist(String tid);

    void updateOrderStatus(@Param("id")Long id, @Param("status")String status, @Param("reason")String reason,@Param("kjbStatus") String kjbStatus);

    OrderYouzanDto selectDtoById(Long id);

    List<OrderYouzan> selectListByIds(@Param("ids")Long[] ids);

    void updateForwardSystem(@Param("id")Long id, @Param("forwardSystem")ForwardSystemEnum forwardSystem);
}