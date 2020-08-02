package com.topideal.supplychain.ocp.amway.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.amway.dto.OCPAmwayOrderRequestPojo;
import com.topideal.supplychain.ocp.order.model.OrderAmway;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import java.util.List;

public interface AmwayService {


    /**
     * 推送ofc
     */
    BaseResponse sendOrderOfc(OrderAmway orderAmway);

    /**
     * 重新推单(批量)
     */
    BaseResponse rePush(List<OrderAmway> orderAmwayList);

    /**
     * 将订单数据保存到数据库,并发送mq
     */
    void saveOrder(OCPAmwayOrderRequestPojo requestPojo);

    /**
     * 安利接单订单处理
     */
    void processOrder(OrderTemp orderTemp) throws Exception;
}

