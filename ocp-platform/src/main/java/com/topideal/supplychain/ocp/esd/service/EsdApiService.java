package com.topideal.supplychain.ocp.esd.service;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.esd.dto.EsdRequest;
import com.topideal.supplychain.ocp.master.model.Store;

/**
 * @ClassName EsdApiService
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/10 19:06
 * @Version 1.0
 **/
public interface EsdApiService {

    BaseResponse sendOrder(EsdRequest req,String businessCode,String orderCode, Store store);

    BaseResponse sendOrder(EsdRequest req,String businessCode,String orderCode, String appId,String appKey);

    BaseResponse sendOutOrder(EsdRequest req,String businessCode,String orderCode,Store store);
}
