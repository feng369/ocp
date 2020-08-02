package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.enums.ForwardSystemEnum;
import com.topideal.supplychain.ocp.order.dto.OrderYouzanDto;
import com.topideal.supplychain.ocp.order.model.OrderYouzan;

import java.util.List;

/**
 * @ClassName OrderYouzanService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/22 14:32
 * @Version 1.0
 **/
public interface OrderYouzanService {

    List<OrderYouzanDto> pageList(OrderYouzanDto orderYouzanDto);

    void insert(OrderYouzan youZanOrder);

    boolean isExist(String tid, String subOrderNo, String... orderStatus);

    OrderYouzan selectByTid(String tid);

    OrderYouzan orderIsExist(String tid);

    OrderYouzan selectById(Long id);

    void updateOrderStatus(Long id, String status, String reason);

    void updateOrderStatus(Long id, String status, String reason,String kjbStatus);

    OrderYouzanDto selectDtoById(Long id);

    void rePush(Long[] ids);

    void updateForwardSystem(Long id, ForwardSystemEnum forwardSystem);
}
