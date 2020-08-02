




package com.topideal.supplychain.ocp.op.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.op.dto.OpRequest;
import com.topideal.supplychain.ocp.op.dto.OpResponse;
import com.topideal.supplychain.ocp.op.service.OpApiService;
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
 * @ClassName OpApiServiceImpl
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/9 16:51
 * @Version 1.0
 **/
@Service
public class OpApiServiceImpl implements OpApiService {

    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 订单推送到OFC
     *
     * @param
     */
    @Override
    @HttpLog
    public BaseResponse sendOrder(OpRequest dto,String businessCode,String orderCode) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.OCP002).setBusinessCode(businessCode).
                setOrderCode(orderCode).setSource(SourceEnum.OCP.getCode()).setTarget(SourceEnum.OP.getCode())
                ;

        //post请求
        String req = JacksonUtils.toJSon(dto);
        expInvokeLog.setRequestData(req);
        //接口地址
        String url = interfaceUrlService.getUrl("op.send.order");
        expInvokeLog.setInterfaceUrl(url);
        BusinessAssert.assertNotEmpty(url, "接口地址未配置:op.send.order  ");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/xml;charset=UTF-8"));
        HttpEntity<String> entity = new HttpEntity<>(req, headers);

        String res = restTemplate.postForObject(url, entity, String.class);
        expInvokeLog.setResponseData(res);
        OpResponse opResponse = JacksonUtils.readValue(res, OpResponse.class);
        if (opResponse == null) {
            return BaseResponse.responseFailure("接口回执异常");
        }
        if (Integer.valueOf(SuccessFailureEnum.SUCCESS.getValue()).equals(opResponse.getStatus())) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            return BaseResponse.responseSuccess(opResponse.getNotes());
        }
        //记录成功信息
        return BaseResponse.responseFailure(opResponse.getNotes());
    }

}