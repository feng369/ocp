package com.topideal.supplychain.ocp.ofc.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.ofcbc.dto.OfcBcExpRequest;
import com.topideal.supplychain.ocp.ofcbc.dto.OfcBcRequest;
import com.topideal.supplychain.ocp.ofc.dto.OfcRequest;
import com.topideal.supplychain.ocp.ofc.dto.OfcResponse;
import com.topideal.supplychain.ocp.ofc.service.OfcApiService;
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
 * @ClassName Ofc
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/11/21 16:26
 * @Version 1.0
 **/
@Service
public class OfcApiServiceImpl implements OfcApiService {

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
    public BaseResponse sendOrder(OfcRequest dto,String businessCode,String orderCode) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.OCP001).setBusinessCode(businessCode).
                setOrderCode(orderCode).setSource(SourceEnum.OCP.getCode()).setTarget(SourceEnum.OFC.getCode())
                ;

        //post请求
        String req = JacksonUtils.toJSon(new Object[]{dto.getOfcOrderReqDto(), dto.getOfcGoodsReqDtos()});
        expInvokeLog.setRequestData(req);
        //接口地址
        String url = interfaceUrlService.getUrl("ofc.send.order");
        expInvokeLog.setInterfaceUrl(url);
        BusinessAssert.assertNotEmpty(url, "接口地址[ofc.send.order]未配置");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> entity = new HttpEntity<>(req, headers);
        String res = restTemplate.postForObject(url, entity, String.class);
        expInvokeLog.setResponseData(res);
        OfcResponse ofcResponse = JacksonUtils.readValue(res,OfcResponse.class);
        if (ofcResponse == null) {
            return BaseResponse.responseFailure("接口回执异常");
        }
        if (SuccessFailureEnum.SUCCESS.getName().equals(ofcResponse.getFlag())) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            return BaseResponse.responseSuccess(ofcResponse.getMessage());
        }
        //记录成功信息
        return BaseResponse.responseFailure(ofcResponse.getMessage());
    }


}
