package com.topideal.supplychain.ocp.xiaomi.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.order.dto.OrderXiaomiDto;
import com.topideal.supplychain.ocp.order.model.OrderTemp;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/12/13
 * @description
 **/
public interface XiaomiService {



    //抓取订单
    void getOrder(CatchOrderConfig catchOrderConfig);

    /**
     * 根据orderId抓取订单
     * @param orderId 订单id，多个用逗号[,]隔开
     * @param storeId 店铺id
     * @return
     */
    BaseResponse grabOnlyOrder(String orderId,Long storeId);

    //推送kjb
    BaseResponse pushKjb(OrderXiaomiDto order);
    //重推订单
    BaseResponse rePush(List<Long> ids);

    /**
     *  保存订单信息
     * @param store 店铺
     * @param orderTemp 临时订单
     */
    void processOrder(Store store, OrderTemp orderTemp);

    /**
     * 漏单抓单
     * @param catchOrderConfig
     */
    void getMissOrder(CatchOrderConfig catchOrderConfig);
}
