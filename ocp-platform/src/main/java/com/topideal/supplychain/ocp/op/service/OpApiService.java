



package com.topideal.supplychain.ocp.op.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.op.dto.OpRequest;

/**
 * @ClassName OpApiService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/9 16:51
 * @Version 1.0
 **/
public interface OpApiService {

    BaseResponse sendOrder(OpRequest req, String subOrderNo, String code);
}






