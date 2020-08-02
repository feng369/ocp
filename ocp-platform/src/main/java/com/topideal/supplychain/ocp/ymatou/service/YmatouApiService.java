package com.topideal.supplychain.ocp.ymatou.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.order.dto.OrderYmatouDto;
import com.topideal.supplychain.ocp.ymatou.dto.OrderPayRep;

import java.util.List;
import java.util.Map;

/**
 * @author wanzhaozhang
 * @date 2019/12/2
 * @description
 **/
public interface YmatouApiService {

    /**
     * 批量抓单
     * @param pargsMap
     * @return
     */
    List<OrderYmatouDto> batchGrab(Map<String,String> pargsMap);

    /**
     * 单票抓单
     * @param pargsMap
     * @param orderId
     * @return
     */
    OrderYmatouDto oneGrab(Map<String,String> pargsMap,String orderId);

    /**
     * 订单接单
     * @param pargsMap
     * @param orderId
     * @return
     */
   BaseResponse takeOrder(Map<String,String> pargsMap, String orderId);

    /**
     * 订单支付
     * @param pargsMap
     * @param orderId
     * @return
     */
    BaseResponse<OrderPayRep> payOrder(Map<String,String> pargsMap, String orderId);
}
