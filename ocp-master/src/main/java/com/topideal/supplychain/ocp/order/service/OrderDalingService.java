package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.enums.OrderStatusEnum;
import com.topideal.supplychain.ocp.order.dto.OrderDalingDto;
import com.topideal.supplychain.ocp.order.model.OrderDaling;
import java.util.List;

/**
 * 达令家
 *
 * @author xuxiaoyan
 * @date 2019-11-29 15:25
 */
public interface OrderDalingService {

    List<OrderDalingDto> pageList(OrderDalingDto filter);

    OrderDaling selectById(Long id);

    List<String> selectExistOrderNo(List<String> orderNoList);

    /**
     * 查询订单明细
     * @param id
     * @return
     */
    String selectInfoDataWithId(long id);

    List<OrderDaling> selectByIds(Long[] ids);

    void updateOrderStatus(Long id, String value, String message);

    void updateInfoData(Long orderId, OrderStatusEnum grabInfo, String s, String logisticsNumber,String sendSystem);

    void insertOrderNoWithDefaultValue(List<OrderDaling> orderList);

    void insert(OrderDaling orderDaling);
}
