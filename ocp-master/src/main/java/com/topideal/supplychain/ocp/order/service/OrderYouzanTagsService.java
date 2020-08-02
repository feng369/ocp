package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.ocp.order.model.OrderYouzanTags;

/**
 * @ClassName OrderYouzanService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/22 14:32
 * @Version 1.0
 **/
public interface OrderYouzanTagsService {

    void insert(OrderYouzanTags youZanOrderTags);

    OrderYouzanTags selectByOrderId(Long id);
}
