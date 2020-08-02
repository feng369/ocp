package com.topideal.supplychain.ocp.pdd.service.impl;

import com.google.common.collect.Lists;
import com.pdd.pop.sdk.http.PopHttpClient;
import com.pdd.pop.sdk.http.api.request.PddOrderInformationGetRequest;
import com.pdd.pop.sdk.http.api.request.PddOrderNumberListIncrementGetRequest;
import com.pdd.pop.sdk.http.api.response.PddOrderInformationGetResponse;
import com.pdd.pop.sdk.http.api.response.PddOrderNumberListIncrementGetResponse;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.pdd.model.PddStoreArg;
import com.topideal.supplychain.ocp.pdd.service.PddApiService;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.config.service.InterfaceUrlService;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import com.topideal.supplychain.web.log.service.ExpInvokeLogService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author klw
 * @date 2019/12/19 19:48
 * @description: 拼多多api接口调用service
 */
@Service
public class PddApiServiceImpl implements PddApiService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PddApiServiceImpl.class);

    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private ExpInvokeLogService expInvokeLogService;

    /**
     * 调用拼多多抓单接口
     * @param request
     * @param storeArg
     * @return
     */
    @Override
    public List<PddOrderNumberListIncrementGetResponse.OrderSnIncrementGetResponseOrderSnListItem>
        sendCatchRequest(PddOrderNumberListIncrementGetRequest request, PddStoreArg storeArg) {

        ExpInvokeLog expInvokeLog = new ExpInvokeLogDto()
                .setCode(ExpCodeEnum.PDD002.getCode())
                .setName(ExpCodeEnum.PDD002.getName())
                .setSource(ExpCodeEnum.PDD002.getSource().getCode())
                .setTarget(ExpCodeEnum.PDD002.getTarget().getCode())
                .setFlag(SuccessFailureEnum.FAILURE)
                .setRequestData(JacksonUtils.toJSon(request))
                .setStartTime(new Date());
        PddOrderNumberListIncrementGetResponse response = null;
        //设置拼多多请求客户端
        PopHttpClient client = getPddClient(storeArg, interfaceUrlService.getUrl("pdd.get.order"));
        try {
            //调用接口抓取订单
            response = client.syncInvoke(request, storeArg.getAccessToken());
            if (response != null && response.getOrderSnIncrementGetResponse() != null
                    && response.getOrderSnIncrementGetResponse().getTotalCount() > 0
                    && CollectionUtils.isNotEmpty(response.getOrderSnIncrementGetResponse().getOrderSnList())){
                expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
                return response.getOrderSnIncrementGetResponse().getOrderSnList();
            }
            return Lists.newArrayList();
        } catch (Exception e) {
            LOGGER.error("拼多多抓取订单失败", e);
            return Lists.newArrayList();
        } finally {
            expInvokeLog.setEndTime(new Date()).setResponseTime(expInvokeLog.getEndTime().getTime() - expInvokeLog.getStartTime().getTime());
            if (SuccessFailureEnum.SUCCESS.equals(expInvokeLog.getFlag())){
                for (PddOrderNumberListIncrementGetResponse.OrderSnIncrementGetResponseOrderSnListItem order: response.getOrderSnIncrementGetResponse().getOrderSnList()){
                    expInvokeLog.setBusinessCode(order.getInnerTransactionId())
                            .setOrderCode(order.getInnerTransactionId())
                            .setResponseData(JacksonUtils.toJSon(order));
                    expInvokeLogService.insert(expInvokeLog);
                }
            }else {
                expInvokeLog.setResponseData(JacksonUtils.toJSon(response));
                if(response != null) expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
                expInvokeLogService.insert(expInvokeLog);
            }
        }
    }

    @Override
    @Transactional
    @HttpLog
    public BaseResponse<PddOrderInformationGetResponse.OrderInfoGetResponseOrderInfo>
        sendCatchSingRequest(PddOrderInformationGetRequest request, PddStoreArg storeArg,
                         String businessCode, String orderCode) throws Exception {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get()
                .setInterfaceEnum(ExpCodeEnum.PDD003)
                .setBusinessCode(businessCode)
                .setOrderCode(orderCode)
                .setSource(ExpCodeEnum.PDD003.getSource().getCode())
                .setTarget(ExpCodeEnum.PDD003.getTarget().getCode())
                .setExceptionCallback(msg -> BaseResponse.responseFailure("接口调用失败"))
                .setRequestData(JacksonUtils.toJSon(request));
        //设置拼多多请求客户端
        PopHttpClient client = getPddClient(storeArg, interfaceUrlService.getUrl("pdd.get.order"));
        PddOrderInformationGetResponse response = client.syncInvoke(request, storeArg.getAccessToken());
        expInvokeLog.setResponseData(JacksonUtils.toJSon(response));
        if (response == null || response.getOrderInfoGetResponse() == null){
            return BaseResponse.responseFailure("接口调用回执为空");
        }
        if (response.getOrderInfoGetResponse().getOrderInfo() == null){
            return BaseResponse.responseFailure("抓单失败:" + response.getErrorResponse().getErrorMsg());
        }
        expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
        return BaseResponse.responseSuccess("").setData(response.getOrderInfoGetResponse().getOrderInfo());
    }

    /**
     * 获取拼多多请求客户端
     * @param storeArg
     * @return
     */
    private PopHttpClient getPddClient(PddStoreArg storeArg, String url) {
        if (StringUtils.isNotEmpty(url)) {
            return new PopHttpClient(url, storeArg.getClientId(), storeArg.getClientSecret());
        }
        return new PopHttpClient(storeArg.getClientId(), storeArg.getClientSecret());
    }
}
