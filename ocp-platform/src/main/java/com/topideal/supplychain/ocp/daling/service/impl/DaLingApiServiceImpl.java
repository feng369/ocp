package com.topideal.supplychain.ocp.daling.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.daling.dto.DalingInfoResponseDto;
import com.topideal.supplychain.ocp.daling.dto.DalingOrderResponseDto;
import com.topideal.supplychain.ocp.daling.service.DaLingApiService;
import com.topideal.supplychain.ocp.order.model.OrderDaling;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @description: ${description}
 * @author: wanwei
 * @create: 2020-03-12 16:09
 **/
@Service
public class DaLingApiServiceImpl implements DaLingApiService {


    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Transactional
    @HttpLog
    public BaseResponse grabInfo(String req, String url, OrderDaling orderDaling) {
        // 接口日志
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.DALING002)
                .setBusinessCode(orderDaling.getOrderNo()).setOrderCode(orderDaling.getCode()).setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.DALING.getCode()).setRequestData(req).setInterfaceUrl(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(req, headers);
        String responseStr = restTemplate.postForObject(url, entity, String.class);
        expInvokeLog.setResponseData(responseStr);
        DalingInfoResponseDto dalingInfoResponseDto = JacksonUtils
                .readValue(responseStr, DalingInfoResponseDto.class);
        if (null == dalingInfoResponseDto || null == dalingInfoResponseDto.getData()) {
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            return BaseResponse.responseFailure("接口回执为空！");
        }
        if(dalingInfoResponseDto.getStatus() != 0 && dalingInfoResponseDto.getStatus() != 202){
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            return BaseResponse.responseFailure(String.format("订单%s获取详细信息失败,获取状态有误", orderDaling.getCode()));
        }
        // 根据获取订单个数判断是否成功
        if (dalingInfoResponseDto.getData().getTotal().compareTo(0) <= 0) {
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            return BaseResponse.responseFailure(String.format("订单%s获取详细信息失败,获取到的订单个数为0", orderDaling.getCode()));
        }
        expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
        return BaseResponse.responseSuccess("").setData(dalingInfoResponseDto);
    }


    @Override
    @Transactional
    @HttpLog
    public BaseResponse grabOrder(String req, String url) {
        // 接口日志
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.DALING001)
                .setSource(SourceEnum.DALING.getCode())
                .setTarget(SourceEnum.OCP.getCode()).setRequestData(req).setInterfaceUrl(url);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(req, headers);
        String responseStr = restTemplate.postForObject(url, entity, String.class);
        expInvokeLog.setResponseData(responseStr);
        DalingOrderResponseDto dalingOrderResponseDto = JacksonUtils
                .readValue(responseStr, DalingOrderResponseDto.class);
        if (null == dalingOrderResponseDto || null == dalingOrderResponseDto.getData()) {
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            return BaseResponse.responseFailure("接口回执为空！");
        }
        if(dalingOrderResponseDto.getStatus() != 0 && dalingOrderResponseDto.getStatus() != 202){
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            return BaseResponse.responseFailure("抓取订单信息失败,获取状态有误");
        }
        // 根据获取订单个数判断是否成功
        if (dalingOrderResponseDto.getData().getTotal().compareTo(0) <= 0) {
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            return BaseResponse.responseFailure("抓取订单信息失败,获取到的订单个数为0");
        }
        expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
        return BaseResponse.responseSuccess("").setData(dalingOrderResponseDto.getData().getT());
    }
}
