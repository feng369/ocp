package com.topideal.supplychain.ocp.kjb.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.big.dto.BigReceiveRequestDto;
import com.topideal.supplychain.ocp.kjb.dto.KjbFxRequest;
import com.topideal.supplychain.ocp.kjb.dto.KjbRequest;
import com.topideal.supplychain.ocp.kjb.dto.KjbResponse;
import com.topideal.supplychain.ocp.kjb.dto.OrderKjbReq;

/**
 * @ClassName KjbApiService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/6 11:22
 * @Version 1.0
 **/
public interface KjbApiService {

    /**
     * 发送订单到跨境宝
     *
     * @param dto
     * @param businessCode
     * @param orderCode
     * @return
     */
    BaseResponse<KjbResponse> sendOrder(KjbRequest dto,String businessCode,String orderCode);

    /**
     * 转为跨境宝订单
     * @param dto 跨境宝订单
     * @param businessCode 业务编码（取自订单编码）
     * @param orderCode 内部订单号
     * @return
     */
    BaseResponse sendOrder2Kjb(OrderKjbReq dto, String businessCode, String orderCode);


    BaseResponse sendFxOrder(KjbFxRequest requestDto, String orderId, String code);
}
