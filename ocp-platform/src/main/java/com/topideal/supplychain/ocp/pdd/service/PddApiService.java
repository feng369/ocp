package com.topideal.supplychain.ocp.pdd.service;

import com.pdd.pop.sdk.http.api.request.PddOrderInformationGetRequest;
import com.pdd.pop.sdk.http.api.request.PddOrderNumberListIncrementGetRequest;
import com.pdd.pop.sdk.http.api.response.PddOrderInformationGetResponse;
import com.pdd.pop.sdk.http.api.response.PddOrderNumberListIncrementGetResponse;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.ocp.pdd.model.PddStoreArg;

import java.util.List;

/**
 * @author klw
 * @date 2019/11/18 11:41
 * @description:
 */
public interface PddApiService {
    List<PddOrderNumberListIncrementGetResponse.OrderSnIncrementGetResponseOrderSnListItem>
        sendCatchRequest(PddOrderNumberListIncrementGetRequest request, PddStoreArg storeArg);

    BaseResponse<PddOrderInformationGetResponse.OrderInfoGetResponseOrderInfo>
        sendCatchSingRequest(PddOrderInformationGetRequest request, PddStoreArg storeArg,
                             String businessCode, String orderCode) throws Exception;
}
