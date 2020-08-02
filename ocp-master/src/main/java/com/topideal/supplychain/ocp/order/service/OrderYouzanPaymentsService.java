package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderYouzan;
import com.topideal.supplychain.ocp.order.model.OrderYouzanPayments;
import java.util.List;

/**
 * @ClassName OrderYouzanService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/22 14:32
 * @Version 1.0
 **/
public interface OrderYouzanPaymentsService {

    void insert(OrderYouzanPayments orderYouzanPayments);

    int batchInsert(List<OrderYouzanPayments> phasePayments);

    List<OrderYouzanPayments> selectByOrderId(Long id);
}
