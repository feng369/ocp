package com.topideal.supplychain.ocp.gs.service.impl;

import com.google.common.collect.Lists;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.gs.dto.GsConfig;
import com.topideal.supplychain.ocp.gs.dto.GsGrabReqDto;
import com.topideal.supplychain.ocp.gs.dto.GsGrabReqDto.GsGrabArgs;
import com.topideal.supplychain.ocp.gs.dto.GsResponse;
import com.topideal.supplychain.ocp.gs.dto.OrderGsReqDto;
import com.topideal.supplychain.ocp.gs.service.GsApiService;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.config.service.InterfaceUrlService;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import com.topideal.supplychain.web.log.service.ExpInvokeLogService;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <p>标题: TODO</p>
 * <p>功能: TODO</p>
 * <p>模块: ocp2</p>
 * <p>版权: Copyright © 2020 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.gs.service.impl</p>
 * <p>作者: topIdeal</p>
 * <p>创建日期: 2020-01-07 16:56</p>
 *
 * @version 1.0
 */
@Service
public class GsApiServiceImpl implements GsApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GsApiServiceImpl.class);
    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private ExpInvokeLogService expInvokeLogService;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 定时任务抓单
     * @param catchOrderConfig
     * @return
     */
    @Override
    public List<OrderGsReqDto> grabOrders(GsGrabArgs gsGrabArgs,CatchOrderConfig catchOrderConfig) {
        // 解析环球捕手抓单参数
        GsConfig gsConfig = JacksonUtils.readValue(catchOrderConfig.getPlatformArguments(), GsConfig.class);
        // 如果参数解析失败重试处理
        BusinessAssert.assertNotNull(gsConfig, "环球捕手平台参数未配置");

        GsGrabReqDto gsRequestDto = new GsGrabReqDto();
        gsRequestDto.setParams(gsGrabArgs);
        gsRequestDto.setPartner(gsConfig.getAppKey());
        gsRequestDto.setTimestamp(DateUtils.getNowTime());

        return executeRequest(JacksonUtils.toJSon(gsRequestDto), gsConfig,catchOrderConfig.getCode());
    }


    /**
     * 构建环球捕手抓单参数
     *
     * 环球捕手请求参数示例：
     * https://github.com/gegejia/ggj-open-api/wiki/%E6%89%B9%E9%87%8F%E8%8E%B7%E5%8F%96%E8%AE%A2%E5%8D%95
     * {
     *     "params":{
     *         "startTime":"2017-12-10 00:00:00",
     *         "endTime":"2017-12-11 00:00:00",
     *         "page":1,
     *         "pageSize":50,
     *         "status":2
     *     },
     *     "partner":"GGJ_test",
     *     "timestamp":"2016-12-11 15:20:00"
     * }
     */
    /*private String buildGrabReq(GsConfig gsConfig ,Integer interval, String consignCode) {
        BusinessAssert.assertNotNull(interval,"抓单间隔时间未配置");
        // 抓单时间，开始时间 = 当前时间 - 时间间隔
        String startTime = DateUtils.dateToString(DateUtils.addMinute(new Date(), interval * -1), DateUtils.DATETIME_PATTERN);
        String nowTime = DateUtils.dateToString(new Date(), DateUtils.DATETIME_PATTERN);
        GsGrabReqDto gsRequestDto = new GsGrabReqDto();
        GsGrabReqDto.GsGrabArgs gsGrabReqDto = new GsGrabReqDto.GsGrabArgs();
        // 手工抓单参数
        gsGrabReqDto.setConsignCode(consignCode);
        // 定时任务抓单--startTime，手工抓单--配置参数中的startTime
        gsGrabReqDto.setStartTime(StringUtils.isNotEmpty(consignCode) ? DateUtils.dateToString(gsConfig.getManualStartTime(), DateUtils.DATETIME_PATTERN) : startTime);
        gsGrabReqDto.setEndTime(nowTime);
        // 定时任务抓单--status=2，手工抓单--status=100
        gsGrabReqDto.setStatus(StringUtils.isNotEmpty(consignCode) ? gsConfig.getManualStatus() : gsConfig.getStatus());
        gsGrabReqDto.setBondedArea(gsConfig.getBondedArea());
        gsRequestDto.setParams(gsGrabReqDto);
        gsRequestDto.setPartner(gsConfig.getAppKey());
        gsRequestDto.setTimestamp(nowTime);
        return JacksonUtils.toJSon(gsRequestDto);
    }*/

    /**
     * 调用环球捕手抓单接口
     * @param requestData
     *
     * 环球捕手响应结果示例：
     * {"code":"1","message":"操作成功","data":{"rows":[],"total":0}}
     * {
     *     "code": "1",
     *     "message": "操作成功",
     *     "data": {
     *         "rows": [
     *             {
     *                 "shopOrderId": 306077665171017,
     *                 "consignCode": "306077665171017P1",
     *                 "status": 100,
     *                 "freezeStatus": 1,
     *                 "payTime": 1577807575000,
     *                 "createTime": 1577807576000,
     *                 "bondedAreaShipExpense": 0,
     *                 "bondedAreaPayCash": 62975,
     *                 "bondedAreaGoodsPrice": 66178,
     *                 "bondedAreaNonCashDeduct": 9225,
     *                 "bondedAreaTax": 6022,
     *                 "shipAddress": {
     *                     "shipToName": "xxx",
     *                     "shipToMobile": "xxx",
     *                     "shipToZip": "",
     *                     "shipToProvince": "广西壮族自治区",
     *                     "shipToCity": "南宁市",
     *                     "shipToDistrict": "江南区",
     *                     "shipToTown": "",
     *                     "shipToAddress": "xxx",
     *                     "certification": {
     *                         "idCardNO": "xxx",
     *                         "idCardName": "xxx",
     *                         "positiveUrl": null,
     *                         "otherSideUrl": null
     *                     }
     *                 },
     *                 "orderSkuList": [
     *                     {
     *                         "skuTitle": "（a2旗舰店）a2白金装婴儿奶粉1段 3罐装",
     *                         "deliverCode": "ASCMAL0103",
     *                         "quantity": 1,
     *                         "purchaseCount": 1,
     *                         "price": 72200,
     *                         "shipExpenseShare": 0,
     *                         "shopPromotionAmount": 5000,
     *                         "acrossPromotionAmount": 0,
     *                         "platformPromotionAmount": 4225,
     *                         "payPrice": 62975,
     *                         "actualIncomePrice": 67200,
     *                         "skuProperty": "罐装-1段",
     *                         "bondedAreaGoodsPrice": 66178,
     *                         "bondedAreaNonCashDeduct": 9225,
     *                         "bondedAreaTax": 6022,
     *                         "realCashAmount": 62975
     *                     }
     *                 ],
     *                 "packageList": [],
     *                 "payChannel": "国内支付宝",
     *                 "tradeNo": "2019123122001499941406302103",
     *                 "buyerComment": "",
     *                 "sellerComment": "",
     *                 "orderCreateTime": 1577807517000
     *             }
     *         ],
     *         "total": 1
     *     }
     * }
     */
    private List<OrderGsReqDto> executeRequest(String requestData, GsConfig gsConfig,String catchCode) {
        ExpInvokeLog expInvokeLog = new ExpInvokeLog()
                .setInterfaceEnum(ExpCodeEnum.GS101)
                .setCode(ExpCodeEnum.GS101.getCode())
                .setName(ExpCodeEnum.GS101.getName())
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.GS.getCode())
                .setStartTime(DateUtils.getNowTime())
                .setFlag(SuccessFailureEnum.FAILURE)
                .setRequestData(requestData);

        GsResponse gsResponse = null;
        try {
            // 接口地址
            String url = interfaceUrlService.getUrl("gs.order.url");
            BusinessAssert.assertNotEmpty(url, "接口地址未配置");
            expInvokeLog.setInterfaceUrl(url);
            BusinessAssert.assertNotEmpty(gsConfig.getAppSecret(), "AppSecret未配置");

            // 抓单参数加签 key + 请求体 + key
            String sign = DigestUtils.md5Hex(gsConfig.getAppSecret() + requestData + gsConfig.getAppSecret()).toUpperCase();

            // 环球捕手示例代码 -- start
            List<Charset> charset = new ArrayList<>();
            charset.add(Charset.forName("utf-8"));//设置字符集，utf-8

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);//设置返回类型,json格式 编码
            headers.setAcceptCharset(charset);
            headers.add("sign", sign);

            HttpEntity<String> entity = new HttpEntity<>(requestData, headers);
            String responseBody = restTemplate.postForObject(url, entity, String.class);
            // 环球捕手示例代码 -- end

            // 记录接口日志
            expInvokeLog.setResponseData(responseBody);
            gsResponse = JacksonUtils.readValue(responseBody, GsResponse.class);
            if (gsResponse != null && "1".equals(gsResponse.getCode())) {
                expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
                return gsResponse.getData().getRows();
            }
        } catch (Exception e) {
            LOGGER.error("环球捕手接口调用异常：", e);
            expInvokeLog.setResponseData(expInvokeLog.getResponseData() + e.getMessage());
        } finally {
            Date end = DateUtils.getNowTime();
            // 记录接口日志
            expInvokeLog.setEndTime(end).setResponseTime(end.getTime() - expInvokeLog.getStartTime().getTime());
            // 响应体为空，或code不等于1，则调用失败
             if (gsResponse == null || !"1".equals(gsResponse.getCode())){
                expInvokeLog.setFlag(SuccessFailureEnum.FAILURE).setOrderCode(catchCode).setBusinessCode(catchCode);
                expInvokeLogService.insert(expInvokeLog);
            } else {
                if (gsResponse.getData().getTotal().compareTo(0) == 0) {
                    // 抓取成功但未抓到订单
                    expInvokeLog.setOrderCode(catchCode).setBusinessCode(catchCode);
                    expInvokeLogService.insert(expInvokeLog);
                } else {
                    // 抓取成功且抓到订单
                    for (OrderGsReqDto orderGs : gsResponse.getData().getRows()) {
                        expInvokeLog.setOrderCode(orderGs.getConsignCode())
                                .setFlag(SuccessFailureEnum.SUCCESS)
                                .setBusinessCode(orderGs.getConsignCode());
                        expInvokeLogService.insert(expInvokeLog);
                    }
                }
            }
        }
        return Lists.newArrayList();
    }


    /**
     * 手工抓单
     * @param catchOrderConfig
     * @param consignCode
     * @return
     */
    @Override
    @HttpLog
    public List<OrderGsReqDto> grabOrder(GsGrabArgs gsGrabArgs,CatchOrderConfig catchOrderConfig, String consignCode) {

        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get()
                .setInterfaceEnum(ExpCodeEnum.GS103)
                .setCode(ExpCodeEnum.GS103.getCode())
                .setName(ExpCodeEnum.GS103.getName())
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.GS.getCode())
                .setStartTime(DateUtils.getNowTime())
                .setFlag(SuccessFailureEnum.FAILURE)
                .setOrderCode(consignCode)
                .setBusinessCode(consignCode)
                ;
        // 解析环球捕手抓单参数
        GsConfig gsConfig = JacksonUtils.readValue(catchOrderConfig.getPlatformArguments(), GsConfig.class);
        // 如果参数解析失败重试处理
        BusinessAssert.assertNotNull(gsConfig, "环球捕手平台参数未配置");
        GsGrabReqDto gsRequestDto = new GsGrabReqDto();
        gsRequestDto.setParams(gsGrabArgs);
        gsRequestDto.setPartner(gsConfig.getAppKey());
        gsRequestDto.setTimestamp(DateUtils.getNowTime());
        String requestData = JacksonUtils.toJSon(gsRequestDto);
        // 接口地址
        String url = interfaceUrlService.getUrl("gs.order.url");
        BusinessAssert.assertNotEmpty(url, "接口地址未配置");
        expInvokeLog.setInterfaceUrl(url).setRequestData(requestData);
        BusinessAssert.assertNotEmpty(gsConfig.getAppSecret(), "AppSecret未配置");

        // 抓单参数加签 key + 请求体 + key
        String sign = DigestUtils.md5Hex(gsConfig.getAppSecret() + requestData + gsConfig.getAppSecret()).toUpperCase();

        // 环球捕手示例代码 -- start
        List<Charset> charset = new ArrayList<>();
        charset.add(Charset.forName("utf-8"));//设置字符集，utf-8

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);//设置返回类型,json格式 编码
        headers.setAcceptCharset(charset);
        headers.add("sign", sign);

        HttpEntity<String> entity = new HttpEntity<>(requestData, headers);
        String responseBody = restTemplate.postForObject(url, entity, String.class);
        // 环球捕手示例代码 -- end

        // 记录接口日志
        expInvokeLog.setResponseData(responseBody);
        GsResponse gsResponse = JacksonUtils.readValue(responseBody, GsResponse.class);
        if (gsResponse != null && "1".equals(gsResponse.getCode())) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            return gsResponse.getData().getRows();
        }
        // 抓单接口调用
        return Lists.newArrayList();
    }

}
