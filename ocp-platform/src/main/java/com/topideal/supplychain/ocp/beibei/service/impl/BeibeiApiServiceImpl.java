package com.topideal.supplychain.ocp.beibei.service.impl;

import com.google.common.base.Throwables;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.beibei.dto.BeibeiDetailResponseDto;
import com.topideal.supplychain.ocp.beibei.dto.BeibeiOrderDto;
import com.topideal.supplychain.ocp.beibei.dto.BeibeiResponseDto;
import com.topideal.supplychain.ocp.beibei.service.BeibeiApiService;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author wanzhaozhang
 * @date 2020/3/23
 * @description
 **/
@Service
public class BeibeiApiServiceImpl implements BeibeiApiService {
    private final static Logger LOGGER = LoggerFactory.getLogger(BeibeiApiServiceImpl.class);
    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ExpInvokeLogService expInvokeLogService;

    @Override
    public BeibeiResponseDto getOrder(TreeMap<String, String> treeMap) {
        BeibeiResponseDto responseDto = new BeibeiResponseDto();
        List<BeibeiOrderDto> beibeiOrderDtoList = null;
        String reqUrl = ""; //请求地址
        //贝贝抓单地址
        reqUrl = interfaceUrlService.getUrl("beibei.order.url");
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.BEIBEI001)
                .setCode(ExpCodeEnum.BEIBEI001.getCode()).setName(ExpCodeEnum.BEIBEI001.getName())
                .setSource(SourceEnum.OCP.getCode()).setTarget(SourceEnum.BEIBEI.getCode())
                .setStartTime(DateUtils.getNowTime()).setInterfaceUrl(reqUrl).setBusinessCode("").setOrderCode("")
                .setFlag(SuccessFailureEnum.FAILURE);
        BusinessAssert.assertNotEmpty(reqUrl, "贝贝抓单[beibei.order.url]接口地址未配置");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        expInvokeLog.setRequestData(JacksonUtils.toJSon(treeMap));
        try {
            String result = restTemplate.postForObject(reqUrl, new HttpEntity(getMultiValueMap(treeMap), headers), String.class);
            expInvokeLog.setResponseData(result);
            responseDto = JacksonUtils.readValue(result, BeibeiResponseDto.class);
            if (responseDto != null && responseDto.isSuccess()) {
                expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            }
            beibeiOrderDtoList = responseDto.getData();

        } catch (Exception e) {
            LOGGER.error("贝贝抓单接口调用失败", e);
            expInvokeLog.setResponseData(expInvokeLog.getResponseData() + Throwables.getStackTraceAsString(e));
        } finally {
            Date end = DateUtils.getNowTime();
            expInvokeLog.setEndTime(end).setResponseTime(end.getTime() - expInvokeLog.getStartTime().getTime());
            if (CollectionUtils.isEmpty(beibeiOrderDtoList)) {
                expInvokeLogService.insert(expInvokeLog);
            } else {
                beibeiOrderDtoList.forEach(dataDto -> {
                    expInvokeLog.setOrderCode(String.valueOf(dataDto.getOid())).setBusinessCode(String.valueOf(dataDto.getOid()));
                    expInvokeLogService.insert(expInvokeLog);
                });
            }
        }
        return responseDto;
    }

    @Override
    @HttpLog
    public BeibeiDetailResponseDto getOrderDetail(TreeMap<String, String> treeMap) {
        String reqUrl = ""; //请求地址
        //贝贝抓单地址
        reqUrl = interfaceUrlService.getUrl("beibei.order.url");
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.BEIBEI002)
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.BEIBEI.getCode())
                .setInterfaceUrl(reqUrl)
                .setBusinessCode(treeMap.get("oid"))
                .setOrderCode(treeMap.get("oid"))
                .setRequestData(JacksonUtils.toJSon(treeMap));
        BusinessAssert.assertNotEmpty(reqUrl, "贝贝抓单[beibei.order.url]接口地址未配置");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String result = restTemplate.postForObject(reqUrl, new HttpEntity(getMultiValueMap(treeMap), headers), String.class);
        expInvokeLog.setResponseData(result);
        BeibeiDetailResponseDto beibeiDetailResponseDto = JacksonUtils.readValue(result, BeibeiDetailResponseDto.class);
        if (beibeiDetailResponseDto == null || !beibeiDetailResponseDto.isSuccess()
                || beibeiDetailResponseDto.getData() == null
                || CollectionUtils.isEmpty(beibeiDetailResponseDto.getData().getItem())) {
            throw new BusinessException("贝贝抓取订单详情失败：" + result);
        }
        expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
        return beibeiDetailResponseDto;
    }

    /**
     * treemap转为multiValueMap
     *
     * @param treeMap
     * @return
     */
    public MultiValueMap<String, Object> getMultiValueMap(TreeMap<?, ?> treeMap) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        Iterator iter = treeMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            map.add(entry.getKey().toString(), null == entry.getValue() ? "null" : entry.getValue().toString());
        }
        return map;
    }
}
