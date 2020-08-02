package com.topideal.supplychain.ocp.xiaomi.service.impl;

import com.google.common.base.Throwables;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.xiaomi.dto.DataDto;
import com.topideal.supplychain.ocp.xiaomi.dto.PartnerInfo;
import com.topideal.supplychain.ocp.xiaomi.dto.ResponseDto;
import com.topideal.supplychain.ocp.xiaomi.dto.ResultDto;
import com.topideal.supplychain.ocp.xiaomi.service.XiaomiApiService;
import com.topideal.supplychain.ocp.xiaomi.util.AesUtils;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.config.service.InterfaceUrlService;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import com.topideal.supplychain.web.log.service.ExpInvokeLogService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author wanzhaozhang
 * @date 2019/12/27
 * @description
 **/
@Service
public class XiaomiApiServiceImpl implements XiaomiApiService {
    private final static Logger LOGGER = LoggerFactory.getLogger(XiaomiApiServiceImpl.class);

    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ExpInvokeLogService expInvokeLogService;

    /**
     * 请求小米平台订单
     *
     * @param params       请求参数
     * @param info         小米平台对接参数
     * @param expInvokeLog 接口日志
     * @param method       方法
     * @return
     * @throws Exception
     */
    private BaseResponse<ResponseDto> sendRequest(Map<String, String> params, PartnerInfo info, ExpInvokeLog expInvokeLog, String method) throws Exception {
        BaseResponse<ResponseDto> baseResponse = BaseResponse.responseFailure("接口错误");
        expInvokeLog.setInterfaceEnum(ExpCodeEnum.XIAOMI001).setCode(ExpCodeEnum.XIAOMI001.getCode()).setName(ExpCodeEnum.XIAOMI001.getName())
                .setSource(SourceEnum.OCP.getCode()).setTarget(SourceEnum.XIAOMI.getCode()).setStartTime(DateUtils.getNowTime())
                .setBusinessCode("").setOrderCode("").setFlag(SuccessFailureEnum.FAILURE);
        //参数加密,加签
        Map<String, String> parseParam = parseParam(params, info);
        //洋码头抓单地址
        String orderUrl = interfaceUrlService.getUrl("xiaomi.order.url");
        BusinessAssert.assertNotEmpty(orderUrl, "小米订单接口地址未配置");
        // 添加参数
        List<NameValuePair> pairList = new ArrayList<>();
        URIBuilder builder = new URIBuilder(orderUrl + method);
        expInvokeLog.setInterfaceUrl(builder.toString());
        if (parseParam != null && parseParam.size() > 0) {
            for (Map.Entry<String, String> entry : parseParam.entrySet()) {
                pairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            builder.setParameters(pairList);
        }
            /*
            1.这里不用字符串，防止restTemplate在请求时，特殊字符被自动转义
            2.headers要不要，post/get请求都可以请求成功，为格式统一，按以下写法
             */
        URI reqUrl = builder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        headers.add("DATA", JacksonUtils.toJSon(params));
        expInvokeLog.setRequestData(JacksonUtils.toJSon(params));
        String result = restTemplate.postForObject(reqUrl, new HttpEntity(headers), String.class);
        expInvokeLog.setResponseData(result);
        ResponseDto responseDto = JacksonUtils.readValue(result, ResponseDto.class);
        if (responseDto != null && responseDto.isSuccess()) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            baseResponse.setFlag(BaseResponse.SUCCESS);
        }
        baseResponse.setData(responseDto);
        return baseResponse;
    }

    /**
     * 抓单
     *
     * @param mapParam 请求参数
     * @param info     小米平台对接参数
     * @param isBatch  true 批量抓单  false 根据id集合抓单
     * @return
     */
    @Override
    public List<DataDto> grab(Map<String, String> mapParam, PartnerInfo info, Boolean isBatch) {
        ExpInvokeLog expInvokeLog = new ExpInvokeLog();
        List<DataDto> dataDtos = null;
        try {
            BaseResponse<ResponseDto> baseResponse = sendRequest(mapParam, info, expInvokeLog, isBatch ? "/orderlist" : "/orderstatus");
            if (baseResponse.isSuccess()) {
                ResultDto resultDto = baseResponse.getData().getResult();
                dataDtos = resultDto != null ? resultDto.getData() : null;
            }

        } catch (Exception e) {
            LOGGER.error("小米抓单接口调用失败", e);
            expInvokeLog.setResponseData(expInvokeLog.getResponseData() + Throwables.getStackTraceAsString(e));
        } finally {
            Date end = DateUtils.getNowTime();
            expInvokeLog.setEndTime(end).setResponseTime(end.getTime() - expInvokeLog.getStartTime().getTime());
            if (CollectionUtils.isEmpty(dataDtos)) {
                expInvokeLogService.insert(expInvokeLog);
            } else {
                dataDtos.forEach(dataDto -> {
                    expInvokeLog.setOrderCode(String.valueOf(dataDto.getOrder_id())).setBusinessCode(String.valueOf(dataDto.getOrder_id()));
                    expInvokeLogService.insert(expInvokeLog);
                });
            }
        }
        return dataDtos;
    }

    private Map<String, String> parseParam(Map<String, String> param, PartnerInfo info) throws Exception {
        String dataValue = AesUtils.encrypt(info.getAesKey(), urlEncoded(param));
        String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        Map<String, String> signParam = new TreeMap<>(Comparator.naturalOrder());
        signParam.put("data", dataValue);
        signParam.put("timestamp", timeStamp);
        signParam.put("partner_id", info.getPartnerId());
        String sign = DigestUtils.md5Hex((urlEncoded(signParam) + info.getKey()).getBytes());
        signParam.put("sign", sign);
        return signParam;
    }

    private String urlEncoded(Map<String, String> param) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            builder.append("&");
            builder.append(entry.getKey());
            builder.append("=");
            builder.append(entry.getValue());
        }
        return builder.toString().substring(1);
    }

}
