package com.topideal.supplychain.ocp.order.service;

import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.ocp.mq.QueueConstants.QueueEnum;
import com.topideal.supplychain.ocp.order.model.OrderTemp;

/**
 * @ClassName OrderTempService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/22 14:32
 * @Version 1.0
 **/
public interface OrderTempService {

    void insert(OrderTemp orderTemp);

    OrderTemp selectById(Long id);

    int deleteById(Long id);

    void insertAndSendMq(OrderTemp orderTemp, QueueEnum queueEnum, BasicMessage basicMessage);
}
