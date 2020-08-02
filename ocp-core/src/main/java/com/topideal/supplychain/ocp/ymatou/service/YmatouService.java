package com.topideal.supplychain.ocp.ymatou.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.order.dto.OrderYmatouDto;
import com.topideal.supplychain.ocp.order.model.OrderTemp;

import java.util.List;

/**
 * @author wanzhaozhang
 * @date 2019/11/29
 * @description 洋码头核心业务
 **/
public interface YmatouService {

    /**
     *  保存订单信息
     * @param store 店铺
     * @param orderTemp 洋码头临时订单
     */
    void processOrder(Store store, OrderTemp orderTemp);

    /**
     * 洋码头订单 调用组套接口
     * @param order 订单
     */
    BaseResponse toKjb(OrderYmatouDto order);

    /**
     * 洋码头订单 调用税金接口
     * @param order 订单
     */
    BaseResponse toGemini(OrderYmatouDto order);

    /**
     * 洋码头订单 调用OP接口
     * @param order 订单
     */
    BaseResponse toOp(OrderYmatouDto order);

    /**
     * 洋码头订单 调用ofc接口
     * @param order 订单
     */
    BaseResponse toOfc(OrderYmatouDto order);


    /**
     * 单票订单抓取
     * @param orderId 订单号
     * @param storeId 店铺id
     * @return
     */
    BaseResponse grabOnlyOrder(String orderId,Long storeId);

    /**
     * 抓取洋码头订单
     * @param catchOrderConfig 抓单配置
     */
    void grabOrder(CatchOrderConfig catchOrderConfig);

    /**
     * 重推订单
     * @param ids
     * @return
     */
    BaseResponse rePush(List<Long> ids);

    /**
     * 订单接单接口 订单状态为2时触发。
     * 该接口调用后，再调用订单支付接口
     * @param order
     * @param store
     * @return
     */
    BaseResponse takeOrder(OrderYmatouDto order,Store store);

    /**
     * 订单支付接口  状态为17时触发
     * @param order
     * @param store
     * @return
     */
    BaseResponse payOrder(OrderYmatouDto order,Store store);
}
