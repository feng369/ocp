package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderYouzanChild;
import java.util.List;

/**
 * @ClassName OrderYouzanService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/22 14:32
 * @Version 1.0
 **/
public interface OrderYouzanChildService {

    void insert(OrderYouzanChild youZanOrderChild);

    int batchInsert(List<OrderYouzanChild> childOrders);
}
