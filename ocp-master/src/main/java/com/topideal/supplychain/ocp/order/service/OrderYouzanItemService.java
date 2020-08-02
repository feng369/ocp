package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.enums.YouZanOrderNewEnum;
import com.topideal.supplychain.ocp.order.model.OrderYouzanItem;
import java.util.List;

/**
 * @ClassName OrderYouzanService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/22 14:32
 * @Version 1.0
 **/
public interface OrderYouzanItemService {


    void insert(OrderYouzanItem youZanItemOrder);

    int batchInsert(List<OrderYouzanItem> orderItems);

    List<OrderYouzanItem> selectByOrderId(Long id, YouZanOrderNewEnum old);
}
