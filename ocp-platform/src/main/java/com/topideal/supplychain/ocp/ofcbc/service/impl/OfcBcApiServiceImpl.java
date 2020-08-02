package com.topideal.supplychain.ocp.ofcbc.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.ofcbc.dto.OfcBcExpRequest;
import com.topideal.supplychain.ocp.ofcbc.dto.OfcBcRequest;
import com.topideal.supplychain.ocp.ofcbc.dto.OfcBcResponse;
import com.topideal.supplychain.ocp.ofcbc.service.OfcBcApiService;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.config.service.InterfaceUrlService;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 标题：OfcBcApiService
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.ofcbc.service.impl
 * 作者：songping
 * 创建日期：2020/1/20 14:32
 *
 * @version 1.0
 */
@Service
public class OfcBcApiServiceImpl implements OfcBcApiService {

    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HttpLog
    public BaseResponse sendBcOrder(OfcBcRequest dto, String businessCode, String orderCode) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.OCP007).setBusinessCode(businessCode).
                setOrderCode(orderCode).setSource(SourceEnum.OCP.getCode()).setTarget(SourceEnum.OFC_BC.getCode())
                ;

        //post请求
        String req = JacksonUtils.toJSon(dto);
        expInvokeLog.setRequestData(req);
        //接口地址
        String url = interfaceUrlService.getUrl("ofc-bc.send.order");
        expInvokeLog.setInterfaceUrl(url);
        BusinessAssert.assertNotEmpty(url, "接口地址[ofc-bc.send.order]未配置");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> entity = new HttpEntity<>(req, headers);
        String res = restTemplate.postForObject(url, entity, String.class);
        expInvokeLog.setResponseData(res);
        OfcBcResponse ofcBcResponse = JacksonUtils.readValue(res, OfcBcResponse.class);
        if (ofcBcResponse == null) {
            return BaseResponse.responseFailure("接口回执异常");
        }
        if (SuccessFailureEnum.SUCCESS.getName().equals(ofcBcResponse.getFlag())) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            return BaseResponse.responseSuccess(ofcBcResponse.getMessage());
        }
        //记录异常信息
        return BaseResponse.responseFailure(ofcBcResponse.getErrorInfo());
    }

    @Override
    @HttpLog
    public BaseResponse sendBcExpOrder(OfcBcExpRequest dto, String businessCode, String orderCode) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.OCP008).setBusinessCode(businessCode).
                setOrderCode(orderCode).setSource(SourceEnum.OCP.getCode()).setTarget(SourceEnum.OFC_BC.getCode())
                ;

        //post请求
        String req = JacksonUtils.toJSon(new Object[]{dto.getOfcBcExpOrderReqDto(), dto.getOfcBcExpGoodsReqDtoList()});
        expInvokeLog.setRequestData(req);
        //接口地址
        String url = interfaceUrlService.getUrl("ofc-bc.send.exp-order");
        expInvokeLog.setInterfaceUrl(url);
        BusinessAssert.assertNotEmpty(url, "接口地址[ofc-bc.send.exp-order]未配置");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> entity = new HttpEntity<>(req, headers);
        String res = restTemplate.postForObject(url, entity, String.class);
        expInvokeLog.setResponseData(res);
        OfcBcResponse ofcBcResponse = JacksonUtils.readValue(res,OfcBcResponse.class);
        if (ofcBcResponse == null) {
            return BaseResponse.responseFailure("接口回执异常");
        }
        if (SuccessFailureEnum.SUCCESS.getName().equals(ofcBcResponse.getFlag())) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            return BaseResponse.responseSuccess(ofcBcResponse.getMessage());
        }
        //记录异常信息
        return BaseResponse.responseFailure(ofcBcResponse.getErrorInfo());
    }
}
