package com.topideal.supplychain.ocp.ymatou.service.impl;

import com.google.common.base.Throwables;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.order.dto.OrderYmatouDto;
import com.topideal.supplychain.ocp.ymatou.dto.OrderAcceptRep;
import com.topideal.supplychain.ocp.ymatou.dto.OrderPayRep;
import com.topideal.supplychain.ocp.ymatou.dto.YmatouRep;
import com.topideal.supplychain.ocp.ymatou.service.YmatouApiService;
import com.topideal.supplychain.ocp.ymatou.util.JsonUtils;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.config.service.InterfaceUrlService;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import com.topideal.supplychain.web.log.service.ExpInvokeLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wanzhaozhang
 * @date 2019/12/2
 * @description
 **/
@Service
public class YmatouApiServiceImpl implements YmatouApiService {
    private final static Logger LOGGER = LoggerFactory.getLogger(YmatouApiServiceImpl.class);

    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ExpInvokeLogService expInvokeLogService;

    /**
     * 发送抓单请求
     *
     * @param pargsMap
     * @param expInvokeLog
     * @return
     */
    private BaseResponse<YmatouRep> requestYmatouHttp(Map<String, String> pargsMap, ExpInvokeLog expInvokeLog) {
        BaseResponse baseResponse = BaseResponse.responseFailure("接口错误");
        //洋码头抓单地址
        String reqUrl = interfaceUrlService.getUrl("ymatou.grab.url");
        BusinessAssert.assertNotEmpty(reqUrl, "接口地址未配置");
        reqUrl += "?" + "app_id=" + pargsMap.get("app_id") + "&method=" + pargsMap.get("method");
        pargsMap.remove("app_id");
        pargsMap.remove("method");
        pargsMap.remove("app_secret");

        expInvokeLog.setInterfaceUrl(reqUrl).setRequestData(JacksonUtils.toJSon(pargsMap)).setOrderCode("").setBusinessCode("");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String result = restTemplate.postForObject(reqUrl, new HttpEntity(pargsMap, headers), String.class);
        expInvokeLog.setResponseData(result);
        YmatouRep repVo = JacksonUtils.readValue(JsonUtils.convert(result).toString(), YmatouRep.class);
        if (repVo != null && "0000".equals(repVo.getCode()) && repVo.getContent() != null) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            baseResponse.setFlag(BaseResponse.SUCCESS);
        }
        baseResponse.setData(repVo);
        return baseResponse;
    }

    /**
     * 批量抓单
     *
     * @param pargsMap
     * @return
     */
    @Override
    public List<OrderYmatouDto> batchGrab(Map<String, String> pargsMap) {
        ExpInvokeLog expInvokeLog = new ExpInvokeLog()
                .setInterfaceEnum(ExpCodeEnum.YMATOU001)
                .setCode(ExpCodeEnum.YMATOU001.getCode())
                .setName(ExpCodeEnum.YMATOU001.getName())
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.YMATOU.getCode())
                .setStartTime(DateUtils.getNowTime())
                .setFlag(SuccessFailureEnum.FAILURE);
        List<OrderYmatouDto> orderYmatouDtos = null;
        try {
            BaseResponse<YmatouRep> baseResponse = requestYmatouHttp(pargsMap, expInvokeLog);
            YmatouRep repVo = baseResponse.getData();
            if (baseResponse.isSuccess()) {
                expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
                orderYmatouDtos = repVo.getContent().getOrdersInfo();
            }
        } catch (Exception e) {
            LOGGER.error("洋码头抓单接口调用失败", e);
            expInvokeLog.setResponseData(expInvokeLog.getResponseData() + Throwables.getStackTraceAsString(e));
        } finally {
            Date end = DateUtils.getNowTime();
            expInvokeLog.setEndTime(end).setResponseTime(end.getTime() - expInvokeLog.getStartTime().getTime());
            if (CollectionUtils.isEmpty(orderYmatouDtos)) {
                expInvokeLogService.insert(expInvokeLog);
            } else {
                for (OrderYmatouDto order : orderYmatouDtos) {
                    expInvokeLog.setOrderCode(order.getPaymentOrderNo()).setBusinessCode(order.getPaymentOrderNo());
                    expInvokeLogService.insert(expInvokeLog);
                }
            }
        }
        return orderYmatouDtos;
    }

    /**
     * 单票抓单
     *
     * @param pargsMap
     * @return
     */
    @Override
    @HttpLog
    public OrderYmatouDto oneGrab(Map<String, String> pargsMap, String orderId) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.YMATOU001)
                .setFlag(SuccessFailureEnum.FAILURE);
        BaseResponse<YmatouRep> baseResponse = requestYmatouHttp(pargsMap, expInvokeLog);
        expInvokeLog.setOrderCode(orderId).setBusinessCode(orderId);
        if (baseResponse.isSuccess()) {
            return baseResponse.getData().getContent().getOrderInfo();
        }
        return null;
    }

    /**
     * 订单接单
     *
     * @param pargsMap
     * @param orderId
     * @return
     */
    @Override
    @HttpLog
    public BaseResponse takeOrder(Map<String, String> pargsMap, String orderId) {
        BaseResponse baseResponse = BaseResponse.responseFailure("接口错误");
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.YMATOU002)
                .setSource(SourceEnum.OCP.getCode()).setTarget(SourceEnum.YMATOU.getCode())
                .setOrderCode(orderId).setBusinessCode(orderId);
        //洋码头抓单地址
        String reqUrl = interfaceUrlService.getUrl("ymatou.grab.url");
        BusinessAssert.assertNotEmpty(reqUrl, "接口地址未配置");
        reqUrl += "?" + "app_id=" + pargsMap.get("app_id") + "&method=" + pargsMap.get("method");
        pargsMap.remove("app_id");
        pargsMap.remove("method");
        pargsMap.remove("app_secret");
        expInvokeLog.setInterfaceUrl(reqUrl).setRequestData(JacksonUtils.toJSon(pargsMap));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String result = restTemplate.postForObject(reqUrl, new HttpEntity(pargsMap, headers), String.class);
        expInvokeLog.setResponseData(result);
        OrderAcceptRep orderAcceptRep = JacksonUtils.readValue(result, OrderAcceptRep.class);
        if (orderAcceptRep != null && "0000".equals(orderAcceptRep.getCode())) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            baseResponse.setFlag(BaseResponse.SUCCESS);
        }

        return baseResponse;
    }

    /**
     * 订单支付
     *
     * @param pargsMap
     * @param orderId
     * @return
     */
    @Override
    @HttpLog
    public BaseResponse<OrderPayRep> payOrder(Map<String, String> pargsMap, String orderId) {
        BaseResponse baseResponse = BaseResponse.responseFailure("");
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.YMATOU003)
                .setSource(SourceEnum.OCP.getCode()).setTarget(SourceEnum.YMATOU.getCode())
                .setOrderCode(orderId).setBusinessCode(orderId);
        //洋码头抓单地址
        String reqUrl = interfaceUrlService.getUrl("ymatou.grab.url");
        BusinessAssert.assertNotEmpty(reqUrl, "接口地址未配置");
        reqUrl += "?" + "app_id=" + pargsMap.get("app_id") + "&method=" + pargsMap.get("method");
        pargsMap.remove("app_id");
        pargsMap.remove("method");
        pargsMap.remove("app_secret");
        expInvokeLog.setInterfaceUrl(reqUrl).setRequestData(JacksonUtils.toJSon(pargsMap));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String result = restTemplate.postForObject(reqUrl, new HttpEntity(pargsMap, headers), String.class);
        expInvokeLog.setResponseData(result);
        OrderPayRep orderPayRep = JacksonUtils.readValue(result, OrderPayRep.class);
        boolean flag = null != orderPayRep && "0000".equals(orderPayRep.getCode()) && null != orderPayRep.getContent()
                && null != orderPayRep.getContent().getResult() && !StringUtils.isEmpty(orderPayRep.getContent().getResult().getOrder_no());
        if (flag) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            baseResponse.setFlag(BaseResponse.SUCCESS);
        } else {
            baseResponse.setMessage("洋码头订单支付接口调用失败：接口回执码不为0000或order_no无值！");
        }
        baseResponse.setData(orderPayRep);
        return baseResponse;
    }
}
