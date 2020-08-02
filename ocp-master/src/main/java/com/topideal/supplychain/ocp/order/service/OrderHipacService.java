package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.dto.OrderHipacDto;
import com.topideal.supplychain.ocp.order.model.OrderHipac;
import java.util.List;

/**
 * 海拍客订单
 * @author xuxiaoyan
 * @date 2019-12-16 15:16
 */
public interface OrderHipacService {

    List<OrderHipacDto> pageList(OrderHipacDto filter);

    OrderHipac selectById(Long id);

    String selectSensitiveData(String propertyName, long orderId);

    void insert(OrderHipac orderHipac);

    List<OrderHipac> selectByIds(List<Long> ids);

    void updateOrderStatus(Long id, String value, String msg);
}
