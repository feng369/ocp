package com.topideal.supplychain.ocp.kjb.service.impl;

import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.big.dto.BigReceiveRequestDto;
import com.topideal.supplychain.ocp.big.dto.BigReceiveResponseDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.enums.KjbSendStausEnum;
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
public class KjbApiServiceImpl implements KjbApiService {

    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 调用跨境宝接口推单订单
     * @param dto
     * @param businessCode
     * @param orderCode
     * @return
     */
    @Override
    @HttpLog
    public BaseResponse<KjbResponse> sendOrder(KjbRequest dto,String businessCode,String orderCode) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get()
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.KJB.getCode())
                .setInterfaceEnum(ExpCodeEnum.OCP004)
                .setBusinessCode(businessCode)
                .setOrderCode(orderCode)
                .setRequestData(JacksonUtils.toJSon(dto));

        String targetUrl = interfaceUrlService.getUrl("kjb.send.order");
        expInvokeLog.setInterfaceUrl(targetUrl);

        BusinessAssert.assertNotEmpty(targetUrl,"订单发送跨境宝，订单组套商品接口失败，kjb.send.order未找到请求URL");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String res = restTemplate.postForObject(targetUrl,new HttpEntity(dto, headers),String.class,new Object[0]);
        expInvokeLog.setResponseData(res);
        KjbResponse kjbResponse = JacksonUtils.readValue(res, KjbResponse.class);

        BaseResponse<KjbResponse> response = BaseResponse.responseFailure("");
        if (kjbResponse == null) {
            kjbResponse = new KjbResponse();
            kjbResponse.setStatus(KjbSendStausEnum.FAIL_DEAL);
            kjbResponse.setNotes("接口异常:回执为空");
        }else if (KjbSendStausEnum.NO_DEAL.equals(kjbResponse.getStatus()) || KjbSendStausEnum.SUCCESS_DEAL.equals(kjbResponse.getStatus())) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            response.setFlag(BaseResponse.SUCCESS);
        }
        response.setData(kjbResponse);
        response.setMessage(kjbResponse.getNotes());
        return response;
    }

    /**
     * 转为跨境宝订单
     *
     * @param dto          跨境宝订单
     * @param businessCode 业务编码（取自订单编码）
     * @param orderCode    内部订单号
     * @return
     */
    @Override
    @HttpLog
    public BaseResponse sendOrder2Kjb(OrderKjbReq dto, String businessCode, String orderCode) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get()
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.KJB.getCode())
                .setInterfaceEnum(ExpCodeEnum.OCP006)
                .setBusinessCode(businessCode)
                .setOrderCode(orderCode)
                .setRequestData(JacksonUtils.toJSon(dto));
        String targetUrl = interfaceUrlService.getUrl("order.to.kjb");
        expInvokeLog.setInterfaceUrl(targetUrl);
        BusinessAssert.assertNotEmpty(targetUrl,"订单下发跨境宝失败，order.to.kjb未找到请求URL");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String res = restTemplate.postForObject(targetUrl,new HttpEntity(dto, headers),String.class,new Object[0]);
        expInvokeLog.setResponseData(res);
        OrderKjbResp resp = JacksonUtils.readValue(res, OrderKjbResp.class);
        BaseResponse response = BaseResponse.responseFailure("");
        if (resp == null) {
            resp = new OrderKjbResp();
            resp.setNotes("接口异常:回执为空");
        }else if(resp.isSuccess()){
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            response.setFlag(BaseResponse.SUCCESS);
        }
        response.setMessage(resp.getNotes());
        return response;
    }

    /**
     * 分销订单下发跨境宝接口
     * @param requestDto
     * @param orderId
     * @param code
     * @return
     */
    @Override
    @HttpLog
    public BaseResponse sendFxOrder(KjbFxRequest requestDto,
            String orderId, String code) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get()
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.KJB.getCode())
                .setInterfaceEnum(ExpCodeEnum.OCP102)
                .setBusinessCode(orderId)
                .setOrderCode(code)
                .setRequestData(JacksonUtils.toJSon(requestDto));
        String targetUrl = interfaceUrlService.getUrl("fx.order.to.kjb");
        expInvokeLog.setInterfaceUrl(targetUrl);
        BusinessAssert.assertNotEmpty(targetUrl,"分销订单下发跨境宝失败，fx.order.to.kjb未找到请求URL");

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
