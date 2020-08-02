package com.topideal.supplychain.ocp.ofc.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.ofcbc.dto.OfcBcExpRequest;
import com.topideal.supplychain.ocp.ofcbc.dto.OfcBcRequest;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;

/**
 * @ClassName OfcApiService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/21 16:25
 * @Version 1.0
 **/
public interface OfcApiService {

    /**
     * BBC
     * @param req
     * @param businessCode
     * @param orderCode
     * @return
     */
    BaseResponse sendOrder(OfcRequest req,String businessCode,String orderCode);

}
