package com.topideal.supplychain.ocp.daling.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.order.model.OrderDaling;

/**
 * @description: 达令家api
 * @author: wanwei
 * @create: 2020-03-12 16:07
 **/
public interface DaLingApiService {

    //单票抓单
    BaseResponse grabInfo(String req, String url, OrderDaling orderDaling);

    //多票抓单
    BaseResponse grabOrder(String req, String url);
}
