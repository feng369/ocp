package com.topideal.supplychain.ocp.order.mapper;

import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.order.dto.OrderDalingDto;
import com.topideal.supplychain.ocp.order.model.OrderDaling;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 达令家
 *
 * @author xuxiaoyan
 * @date 2019-11-29 15:27
 */
@Mapper
public interface OrderDalingMapper {

    List<OrderDalingDto> pageList(OrderDalingDto filter);

    OrderDaling selectById(Long id);

    String selectInfoDataWithId(long id);

    List<OrderDaling> selectByIds(@Param("ids") Long[] ids);

    void updateOrderStatus(@Param("id") Long id,@Param("status") String value,@Param("reason") String message);

    void updateInfoData(@Param("id") Long orderId, @Param("status") OrderStatusEnum status, @Param("infoData") String grabInfo,
            @Param("logisticsNumber") String logisticsNumber,@Param("sendSystem") String sendSystem);

    void insertOrderNoWithDefaultValue(@Param("list") List<OrderDaling> orderList);

    List<String> selectExistOrderNo(@Param("orderNoList")List<String> orderNoList);

    void insert(OrderDaling orderDaling);
}
