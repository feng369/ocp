package com.topideal.supplychain.ocp.youzan.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.order.dto.OrderYouzanDto;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.model.OrderYouzan;

/**
 * @ClassName YouzanService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/22 11:05
 * @Version 1.0
 **/
public interface YouzanService {

    void getOrder(CatchOrderConfig catchOrderConfig);

    void processOrder(Store store, OrderTemp orderTemp);

    BaseResponse getOrder(Store store, String tid);

    void getMissOrder(CatchOrderConfig catchOrderConfig);

    BaseResponse sendOrderOfc(OrderYouzanDto orderYouzan);

    BaseResponse sendOrderKjb(OrderYouzanDto orderYouzan);

    BaseResponse sendOrderOp(OrderYouzanDto orderYouzan);

    BaseResponse sendOrderEsd(OrderYouzanDto orderYouzan);
}
