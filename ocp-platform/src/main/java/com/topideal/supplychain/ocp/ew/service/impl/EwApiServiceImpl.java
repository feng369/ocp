package com.topideal.supplychain.ocp.ew.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.big.dto.BigReceiveResponseDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.enums.KjbSendStausEnum;
import com.topideal.supplychain.ocp.ew.dto.EwFxRequest;
import com.topideal.supplychain.ocp.ew.service.EwApiService;
import com.topideal.supplychain.ocp.kjb.dto.KjbFxRequest;
import com.topideal.supplychain.ocp.kjb.dto.KjbRequest;
import com.topideal.supplychain.ocp.kjb.dto.KjbResponse;
import com.topideal.supplychain.ocp.kjb.dto.OrderKjbReq;
import com.topideal.supplychain.ocp.kjb.dto.OrderKjbResp;
import com.topideal.supplychain.ocp.kjb.service.KjbApiService;
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
 * @ClassName KjbApiServiceImpl
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/6 11:22
 * @Version 1.0
 **/
@Service
public class EwApiServiceImpl implements EwApiService {

    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 分销订单下发跨境宝接口
     * @param requestDto
     * @param orderId
     * @param code
     * @return
     */
    @Override
    @HttpLog
    public BaseResponse sendFxOrder(EwFxRequest requestDto,
            String orderId, String code) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get()
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.EW.getCode())
                .setInterfaceEnum(ExpCodeEnum.OCP103)
                .setBusinessCode(orderId)
                .setOrderCode(code)
                .setRequestData(JacksonUtils.toJSon(requestDto));
        String targetUrl = interfaceUrlService.getUrl("fx.order.to.ew");
        expInvokeLog.setInterfaceUrl(targetUrl);
        BusinessAssert.assertNotEmpty(targetUrl,"分销订单下发第E仓失败，[fx.order.to.ew]未找到请求URL");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String res = restTemplate.postForObject(targetUrl,new HttpEntity(requestDto, headers),String.class,new Object[0]);
        expInvokeLog.setResponseData(res);
        BigReceiveResponseDto resp = JacksonUtils.readValue(res, BigReceiveResponseDto.class);
        BaseResponse response = BaseResponse.responseFailure("");
        if (null == resp) {
            resp = new BigReceiveResponseDto();
            resp.setNotes("接口异常:回执为空");
        }else if(resp.getStatus().compareTo(1) == 0){
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            response.setFlag(BaseResponse.SUCCESS);
        }
        response.setMessage(resp.getNotes());
        return response;
    }
}
