package com.topideal.supplychain.ocp.ew.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.big.dto.BigReceiveRequestDto;
import com.topideal.supplychain.ocp.ew.dto.EwFxRequest;
import com.topideal.supplychain.ocp.kjb.dto.KjbFxRequest;
import com.topideal.supplychain.ocp.kjb.dto.KjbRequest;
import com.topideal.supplychain.ocp.kjb.dto.KjbResponse;
import com.topideal.supplychain.ocp.kjb.dto.OrderKjbReq;

/**
 * @ClassName EwApiService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/6 11:22
 * @Version 1.0
 **/
public interface EwApiService {

    BaseResponse sendFxOrder(EwFxRequest requestDto, String orderId, String code);
}
