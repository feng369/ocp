package com.topideal.supplychain.ocp.jd.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.enums.GrabIdEnum;
import com.topideal.supplychain.ocp.order.model.OrderJd;
import com.topideal.supplychain.ocp.order.model.OrderTemp;

public interface JdService {
    //抓取订单
    BaseResponse getOrder(CatchOrderConfig catchOrderConfig);

    //处理抓取的订单
    void processOrder(OrderTemp orderTemp,String grabId);

    BaseResponse getYxOrder(CatchOrderConfig catchOrderConfig);

    void processYxOrder(OrderTemp orderTemp,String grabId);

    BaseResponse sendOrderOfc(OrderJd orderJd);

    BaseResponse grabDlzOrder(CatchOrderConfig catchOrderConfig);

    void processDlzOrder(OrderTemp orderTemp, String grabId);

    void getOneOrder(String orderId, String grabKey, String thirdPlatformCode,boolean isMc);
}
