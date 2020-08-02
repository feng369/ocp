package com.topideal.supplychain.ocp.dxy.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.order.model.OrderDxy;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import java.util.List;

/**
 * @ClassName DxyService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/6/30 15:27
 * @Version 1.0
 **/
public interface DxyService {

    //抓取订单
    BaseResponse getOrder(CatchOrderConfig catchOrderConfig);


    void processOrder(OrderTemp orderTemp,CatchOrderConfig catchOrderConfig);

    BaseResponse sendOrderOfc(OrderDxy orderDxy);

    String rePush(List<Long> ids);

    BaseResponse getMissOrder(CatchOrderConfig catchOrderConfig);
}
