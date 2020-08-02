package com.topideal.supplychain.ocp.gemini.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.gemini.dto.GeminiRequest;
import com.topideal.supplychain.ocp.gemini.dto.GeminiResponse;
import com.topideal.supplychain.ocp.gemini.service.GeminiApiService;
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
 * @author wanzhaozhang
 * @date 2019/12/9
 * @description
 **/
@Service
public class GeminiApiServiceImpl implements GeminiApiService {

    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 发送订单到Gemini 调用税金接口
     *
     * @param dto
     * @param businessCode
     * @param orderCode
     * @return
     */
    @Override
    @HttpLog
    public BaseResponse<GeminiResponse> sendOrder(GeminiRequest dto, String businessCode, String orderCode) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.GEMINI.getCode())
                .setInterfaceEnum(ExpCodeEnum.OCP005)
                .setBusinessCode(businessCode)
                .setOrderCode(orderCode)
                .setRequestData(JacksonUtils.toJSon(dto));
        //ocp推税金分离接口地址
        String targetUrl = interfaceUrlService.getUrl("gemini.send.order");
        expInvokeLog.setInterfaceUrl(targetUrl);
        BusinessAssert.assertNotEmpty(targetUrl, "税金分离[gemini.send.order]接口地址未配置");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        String responseStr = restTemplate.postForObject(targetUrl, new HttpEntity(dto, headers), String.class);
        expInvokeLog.setResponseData(responseStr);
        GeminiResponse geminiResponse = JacksonUtils.readValue(responseStr, GeminiResponse.class);

        BaseResponse response = BaseResponse.responseFailure("");
        if (geminiResponse == null) {
            geminiResponse = new GeminiResponse();
            geminiResponse.setFlag(BaseResponse.FAILURE);
            geminiResponse.setFailMessage("接口异常:回执为空");
        }else if (BaseResponse.SUCCESS.equals(geminiResponse.getFlag())) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            response.setFlag(BaseResponse.SUCCESS);
        }
        response.setData(geminiResponse);
        response.setMessage(geminiResponse.getFailMessage());
        return response;
    }
}
