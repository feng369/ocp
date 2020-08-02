package com.topideal.supplychain.ocp.baoma.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.baoma.dto.BaomaResponse;
import com.topideal.supplychain.ocp.baoma.service.BaomaApiService;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.util.MD5Utils;
import com.topideal.supplychain.web.config.service.InterfaceUrlService;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author wanzhaozhang
 * @date 2019/12/13
 * @description
 **/
@Service
public class BaomaApiServiceImpl implements BaomaApiService {
    //抓单短地址
    private static final String pullOrder = "/api/v2/platform/pullOrder";

    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HttpLog
    public BaomaResponse getOrder(CatchOrderConfig catchOrderConfig, Store store) {

        String reqUrl = ""; //请求地址
        //洋码头抓单地址
        reqUrl = interfaceUrlService.getUrl("baoma.get.order");
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.BAOMA001)
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.BAOMA.getCode())
                .setInterfaceUrl(reqUrl)
                .setBusinessCode(store.getCode())
                .setOrderCode(store.getCode());
        BusinessAssert.assertNotEmpty(reqUrl, "宝妈时光抓单[baoma.get.order]接口地址未配置");
        long requestAt = System.currentTimeMillis();
        JsonNode platformArgumentsNode = JacksonUtils.readValue(catchOrderConfig.getPlatformArguments());
        String sign = signature(requestAt, pullOrder, store.getArguments(), platformArgumentsNode);
        //添加头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("DATE", String.valueOf(requestAt));
        headers.add("APP-KEY", platformArgumentsNode.get("appKey").asText());
        headers.add("X-SIGNATURE", sign);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("data", store.getArguments());
        expInvokeLog.setRequestData(store.getArguments());
        String result = restTemplate.postForObject(reqUrl, new HttpEntity(map, headers), String.class);
        expInvokeLog.setResponseData(result);
        BaomaResponse baomaResponse = JacksonUtils.readValue(result, BaomaResponse.class);
        if (baomaResponse == null || !baomaResponse.getSuccess()) {
            throw new BusinessException("宝妈时光抓取订单失败：" + result);
        }
        expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
        return baomaResponse;
    }


    /**
     * 签名
     */
    private String signature(long requestAt, String uri, String json, JsonNode platformArgumentsNode) {
        // 请求方法
        String method = "POST";
        String signatureUrl = uri;
        if (signatureUrl.contains("?")) {
            signatureUrl = uri.substring(0, uri.indexOf("?"));
        }
        StringBuilder data = new StringBuilder(method)
                .append("&")
                .append(requestAt)
                .append("&")
                .append(signatureUrl)
                .append("&")
                .append(json)
                .append("&")
                .append(platformArgumentsNode.get("appKey").asText())
                .append("&")
                .append(platformArgumentsNode.get("secret").asText());
        String signature = MD5Utils.md5Hex(data.toString());
        return signature;
    }
}
