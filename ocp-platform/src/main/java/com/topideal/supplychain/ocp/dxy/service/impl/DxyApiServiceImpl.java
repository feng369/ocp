package com.topideal.supplychain.ocp.dxy.service.impl;


import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.dxy.dto.DxyConfig;
import com.topideal.supplychain.ocp.dxy.dto.DxyOrderResponse;
import com.topideal.supplychain.ocp.dxy.dto.DxyRequest;
import com.topideal.supplychain.ocp.dxy.dto.DxyResponse;
import com.topideal.supplychain.ocp.dxy.service.DxyApiService;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.util.MD5Utils;
import com.topideal.supplychain.web.config.service.InterfaceUrlService;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import com.topideal.supplychain.web.log.service.ExpInvokeLogService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.topideal.supplychain.ocp.util.HttpUtil;

/**
 * @ClassName DxyApiServiceImpl
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2020/6/30 15:46
 * @Version 1.0
 **/
@Service
public class DxyApiServiceImpl implements DxyApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DxyApiServiceImpl.class);
    @Autowired
    private InterfaceUrlService interfaceUrlService;
    /*@Autowired
    private RestTemplate restTemplate;*/
    @Autowired
    private ExpInvokeLogService expInvokeLogService;

    @Override
    public BaseResponse<List<DxyOrderResponse>> getOrder(DxyRequest dxyRequest,CatchOrderConfig catchOrderConfig) {
        ExpInvokeLog expInvokeLog = new ExpInvokeLog()
                .setInterfaceEnum(ExpCodeEnum.DXY001)
                .setCode(ExpCodeEnum.DXY001.getCode())
                .setName(ExpCodeEnum.DXY001.getName())
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.DXY.getCode())
                .setStartTime(DateUtils.getNowTime())
                .setFlag(SuccessFailureEnum.FAILURE);
        List<DxyOrderResponse> list = Lists.newArrayList();
        BaseResponse<List<DxyOrderResponse>> baseResponse = new BaseResponse<>();
        HttpResponse httpResponse = null;
        try {
            //调用京东抓单接口
            String url = this.interfaceUrlService.getUrl("dxy.get.order");
            BusinessAssert.assertNotEmpty(url, "丁香医生订单接口地址[dxy.get.order]未配置");
            expInvokeLog.setInterfaceUrl(url);
            String requestStr = JacksonUtils.toJSon(dxyRequest);
            List<NameValuePair> requestParams = buildRequest(requestStr,catchOrderConfig);

            expInvokeLog.setRequestData(JacksonUtils.toJSon(requestParams));
            /*HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity(requestParams, headers);
            expInvokeLog.setResponseData(JacksonUtils.toJSon(requestParams));*/
            //List<NameValuePair> postDatas = createNamePair(str);
            httpResponse = HttpUtil.executePost(HttpUtil.createHttpPost(url, HttpUtil.DEFAULT_ENCODING, requestParams));
            String respMsg = HttpUtil.parseResponseBodyStr(httpResponse, HttpUtil.DEFAULT_ENCODING);
            expInvokeLog.setResponseData(respMsg);

            DxyResponse response = JacksonUtils.readValue(respMsg, DxyResponse.class);
            //调用抓单接口
            //DxyResponse response = restTemplate.postForObject(url, r, DxyResponse.class);
            //DxyResponse response = mock();
            //expInvokeLog.setResponseData(JacksonUtils.toJSon(response));

            if (response != null && response.isSuccess()) {
                expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
                list = response.getData();
            }
            baseResponse.setFlag(BaseResponse.SUCCESS);
            baseResponse.setData(list);
            return baseResponse;

        }catch (Exception e) {
            LOGGER.error("丁香医生批量抓单接口调用失败", e);
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            expInvokeLog.setResponseData(
                    expInvokeLog.getResponseData() + Throwables.getStackTraceAsString(e));
            baseResponse.setFlag(BaseResponse.FAILURE);
            baseResponse.setMessage(Throwables.getStackTraceAsString(e));
        } finally {
            Date end = DateUtils.getNowTime();
            /**补齐接口信息*/
            expInvokeLog.setEndTime(end)
                    .setResponseTime(end.getTime() - expInvokeLog.getStartTime().getTime());
            if (CollectionUtils.isEmpty(list)) {
                expInvokeLog.setOrderCode(catchOrderConfig.getCode())
                        .setBusinessCode(catchOrderConfig.getCode());
                expInvokeLogService.insert(expInvokeLog);
            } else {
                for (DxyOrderResponse order : list) {
                    expInvokeLog.setOrderCode(catchOrderConfig.getCode())
                            .setBusinessCode(catchOrderConfig.getCode());
                    if (order != null) {
                        String orderNo = order.getOrderId();//订单号
                        expInvokeLog.setOrderCode(orderNo).setBusinessCode(orderNo);
                    }
                    expInvokeLogService.insert(expInvokeLog);
                }
            }
        }

        return baseResponse;
    }

    /**
     * 拼接请求
     */
    private List<NameValuePair> buildRequest(String request,CatchOrderConfig catchOrderConfig) {
        DxyConfig dxyConfig = JacksonUtils
                .readValue(catchOrderConfig.getPlatformArguments(), DxyConfig.class);
        BusinessAssert.assertNotNull(dxyConfig,"请求参数未配置");
        BusinessAssert.assertNotEmpty(dxyConfig.getSupplierId(),"对接供应商Id[supplierId]未配置");
        BusinessAssert.assertNotEmpty(dxyConfig.getToken(),"对接供应商token[token]未配置");



        MultiValueMap<String, Object> requestParams = new LinkedMultiValueMap<>();
        long timestamp = DateUtils.getNowTime().getTime();

        List<NameValuePair> postDatas = new ArrayList<>();
        /*requestParams.add("timestamp", timestamp);
        requestParams.add("supplierId", dxyConfig.getSupplierId());
        requestParams.add("data", request);
        String sign = sign(dxyConfig,request,timestamp);
        requestParams.add("sign", sign);*/
        String sign = sign(dxyConfig,request,timestamp);
        postDatas.add(new BasicNameValuePair("timestamp", String.valueOf(timestamp)));
        postDatas.add(new BasicNameValuePair("supplierId", dxyConfig.getSupplierId()));
        postDatas.add(new BasicNameValuePair("data", request));
        postDatas.add(new BasicNameValuePair("sign", sign));

        return postDatas;
    }

    /**
     * 加签
     * @param dxyConfig
     * @param request
     * @param timestamp
     * @return
     */
    private String sign(DxyConfig dxyConfig,String request,long timestamp) {
        String signStr = new StringBuilder(dxyConfig.getToken())
                .append("data")
                .append(request)
                .append("supplierId")
                .append(dxyConfig.getSupplierId())
                .append("timestamp")
                .append(timestamp)
                .append(dxyConfig.getToken()).toString();
        String sign = MD5Utils.md5Hex(signStr);
        return sign;
    }

    private DxyResponse mock(){
        String s = "{\n"
                + "    \"data\":[\n"
                + "        {\n"
                + "            \"orderId\":3409025170189847223,\n"
                + "            \"createTime\":\"2020-04-21 14:37:29\",\n"
                + "            \"realPayAmount\":3,\n"
                + "            \"discountAmount\":0,\n"
                + "            \"taxAmount\":1,\n"
                + "            \"freightAmount\":0,\n"
                + "            \"userRemark\":\"\",\n"
                + "            \"province\":\"浙江省\",\n"
                + "            \"city\":\"杭州市\",\n"
                + "            \"area\":\"滨江区\",\n"
                + "            \"address\":\"长河街道 滨安路 756 号 世包大楼 A 区 3 楼-丁香园 TC 办\",\n"
                + "            \"postCode\":\"0\",\n"
                + "            \"buyerName\":\"康康\",\n"
                + "            \"buyerIdCard\":\"110102197501101519\",\n"
                + "            \"buyerMobile\":\"13811456812\",\n"
                + "            \"receiverName\":\"康神\",\n"
                + "            \"receiverIdCard\":\"110102197501101519\",\n"
                + "            \"receiverMobile\":\"13811456812\",\n"
                + "            \"payTime\":\"2020-04-21 14:37:43\",\n"
                + "            \"paymentNo\":\"4200000541202004210981129650\",\n"
                + "            \"payType\":1,\n"
                + "            \"payCompanyCode\":\"4403169D3W\",\n"
                + "            \"orderItems\":[\n"
                + "                {\n"
                + "                    \"skuCode\":\"skfjsdkjf2\",\n"
                + "                    \"commodityName\":\"MADNESS 5-PANEL CAP\",\n"
                + "                    \"specificationName\":\"100 片拼图-公主\",\n"
                + "                    \"quantity\":1,\n"
                + "                    \"price\":2,\n"
                + "                    \"costPrice\":1,\n"
                + "                    \"amount\":2.7,\n"
                + "                    \"discountAmount\":0,\n"
                + "                    \"taxAmount\":1\n"
                + "                }\n"
                + "            ],\n"
                + "            \"platformCode\":\"330136802E\",\n"
                + "            \"platformName\":\"杭州丁香健康管理有限公司\"\n"
                + "        }\n"
                + "    ],\n"
                + "    \"success\":true,\n"
                + "    \"message\":\"\",\n"
                + "    \"pageBean\":{\n"
                + "        \"pageNo\":1,\n"
                + "        \"pageSize\":100,\n"
                + "        \"totalCount\":1\n"
                + "    }\n"
                + "}";

        DxyResponse response = JacksonUtils.readValue(s,DxyResponse.class);
        return response;
    }
}
