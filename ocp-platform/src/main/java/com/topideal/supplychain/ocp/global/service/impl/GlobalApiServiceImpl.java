package com.topideal.supplychain.ocp.global.service.impl;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.global.dto.GlobalParam;
import com.topideal.supplychain.ocp.global.dto.GlobalResponseDto;
import com.topideal.supplychain.ocp.global.service.GlobalApiService;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.config.service.InterfaceUrlService;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import com.topideal.supplychain.web.log.service.ExpInvokeLogService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.MessageDigest;
import java.util.*;

/**
 * <p>标题: 全球仓订单接口调用API</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.global.service.impl</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/10 14:12</p>
 *
 * @version 1.0
 */
@Service
public class GlobalApiServiceImpl implements GlobalApiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalApiServiceImpl.class);


    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ExpInvokeLogService expInvokeLogService;

    /**
     * 全球仓抓单接口
     *
     * @param param
     */
    @Override
    public BaseResponse<List<GlobalResponseDto.Content.OrderResult>> grabOrder(GlobalParam param) {
        //接口地址
        String serviceUrl = interfaceUrlService.getUrl("global.get.order");
        BusinessAssert.assertNotNull(serviceUrl, "global.get.order：接口地址未配置");
        //接口日志
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setCode(ExpCodeEnum.GLOBAL001.getCode()).
                setName(ExpCodeEnum.GLOBAL001.getName()).setSource(SourceEnum.OCP.getCode()).
                setTarget(SourceEnum.GLOBAL.getCode()).setFlag(SuccessFailureEnum.FAILURE).setStartTime(new Date()).setInterfaceUrl(serviceUrl);
        List<GlobalResponseDto.Content.OrderResult> orderList = Lists.newArrayList();
        //加入异常重试机制
        BaseResponse<List<GlobalResponseDto.Content.OrderResult>> baseResponse = new BaseResponse<>(BaseResponse.FAILURE);
        String result = "";
        try {
            //组装抓单请求
            UriComponents components = buildRequest(param, serviceUrl);
            expInvokeLog.setRequestData(JacksonUtils.toJSon(components.getQueryParams()));
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            HttpEntity<String> responseEntity = restTemplate.exchange(components.encode().toUri(), HttpMethod.GET, entity, String.class);
            result = responseEntity.getBody();
            GlobalResponseDto response = JacksonUtils.readValue(result, GlobalResponseDto.class);
            BusinessAssert.assertIsFalse(response == null || response.getStat() == null || !"0".equals(response.getStat().getCode()), "抓取订单信息失败,回执状态为失败");
            //跳过订单为空的
            if (CollectionUtils.isNotEmpty(response.getContent())) {
                response.getContent().forEach(content -> {
                    if (CollectionUtils.isNotEmpty(content.getOrders())) {
                        orderList.addAll(content.getOrders());
                    }
                });
            }
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS).setResponseData(result);
            baseResponse.setFlag(BaseResponse.SUCCESS);
            baseResponse.setData(orderList);
        } catch (Exception e) {
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE).setResponseData(result + Throwables.getStackTraceAsString(e));
            baseResponse.setFlag(BaseResponse.FAILURE);
            baseResponse.setData(orderList);
            baseResponse.setMessage(Throwables.getStackTraceAsString(e));
            LOGGER.error("全球仓抓单失败：", e);
        } finally {
            if (CollectionUtils.isEmpty(orderList)) {
                //记录操作日志
                expInvokeLog.setOrderCode("全球仓抓单").setBusinessCode("全球仓抓单").setEndTime(new Date());
                expInvokeLogService.insert(expInvokeLog);
            } else {
                //记录操作日志
                for (GlobalResponseDto.Content.OrderResult order : orderList) {
                    expInvokeLog.setOrderCode(order.getDeclareCode()).setBusinessCode(order.getDeclareCode()).setEndTime(new Date());
                    expInvokeLogService.insert(expInvokeLog);
                }
            }
        }
        return baseResponse;
    }

    /**
     * 组装请求
     *
     * @param param
     * @param serviceUrl
     * @return
     */
    private UriComponents buildRequest(GlobalParam param, String serviceUrl) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("_ft", "json");
        params.put("_vc", "1");
        params.put("_mt", "supplierOpenApi.getOrderListForSupplier");
        params.put("_sm", "md5");
        params.put("_tpid", param.getAppId());
        params.put("merchantId", param.getStoreCode());
        HashMap<String, Object> query = Maps.newHashMap();
        query.put("pageIndex", param.getPageNo());
        query.put("pageSize", param.getPageSize());
        query.put("orderStatus", "WAIT_STOCKOUT");
        query.put("createStartTime", param.getStartTime());
        query.put("createEndTime", param.getEndTime());
        String que = JacksonUtils.toJSon(query);
        params.put("query", que);
        String sign = sign(params, param.getSecretKey());
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(serviceUrl)
                .queryParam("_ft", "json")
                .queryParam("_vc", "1")
                .queryParam("_mt", "supplierOpenApi.getOrderListForSupplier")
                .queryParam("_sm", "md5")
                .queryParam("_tpid", param.getAppId())
                .queryParam("merchantId", param.getStoreCode())
                .queryParam("query", que)
                .queryParam("_sig", sign);
        return builder.build();
    }

    /**
     * 加签
     *
     * @param params
     * @return
     */
    private String sign(TreeMap<String, String> params, String secretKey) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry entry : params.entrySet()) {
            builder.append(entry.getKey());
            builder.append("=");
            builder.append(entry.getValue());
        }
        builder.append(secretKey);
        //md5
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MD5Encoder.encode(md.digest(builder.toString().getBytes()));
        } catch (Exception e) {
            LOGGER.error("全球仓抓单请求加密失败");
            return null;
        }
    }
}
