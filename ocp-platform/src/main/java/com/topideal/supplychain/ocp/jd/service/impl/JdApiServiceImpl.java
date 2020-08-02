package com.topideal.supplychain.ocp.jd.service.impl;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.jd.dto.JdConfig;
import com.topideal.supplychain.ocp.jd.dto.JdDlzOrderDto;
import com.topideal.supplychain.ocp.jd.dto.JdOrderDto;
import com.topideal.supplychain.ocp.jd.dto.JdRequest;
import com.topideal.supplychain.ocp.jd.dto.JdResponse;
import com.topideal.supplychain.ocp.jd.service.JdApiService;
import com.topideal.supplychain.util.DateUtils;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.util.MD5Utils;
import com.topideal.supplychain.web.config.service.InterfaceUrlService;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import com.topideal.supplychain.web.log.service.ExpInvokeLogService;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class JdApiServiceImpl implements JdApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdApiServiceImpl.class);
    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ExpInvokeLogService expInvokeLogService;

    /**
     * 自营非自营抓单
     */
    @Override
    //@HttpLog
    public BaseResponse<List<String>>  grabOrder(JdRequest jdRequest,String code) {
        ExpInvokeLog expInvokeLog = new ExpInvokeLog()
                .setInterfaceEnum(ExpCodeEnum.JD001)
                .setCode(ExpCodeEnum.JD001.getCode())
                .setName(ExpCodeEnum.JD001.getName())
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.JD.getCode())
                .setStartTime(DateUtils.getNowTime())
                .setFlag(SuccessFailureEnum.FAILURE);

        List<String> list = Lists.newArrayList();
        BaseResponse<List<String>> baseResponse = new BaseResponse<>();
        try {
            JdResponse jdResponse = doPost(jdRequest,expInvokeLog);

            if (jdResponse != null &&
                    jdResponse.getJingdong_guangzhou_customs_queryOrderByParam_responce() != null &&
                    jdResponse.getJingdong_guangzhou_customs_queryOrderByParam_responce()
                            .getOrderListResult() != null &&
                    jdResponse.getJingdong_guangzhou_customs_queryOrderByParam_responce()
                            .getOrderListResult().getHeader() != null &&
                    "0".equals(jdResponse.getJingdong_guangzhou_customs_queryOrderByParam_responce()
                            .getCode())) {
                expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
                list = jdResponse.getJingdong_guangzhou_customs_queryOrderByParam_responce()
                        .getOrderListResult().getBody();
            }
            baseResponse.setFlag(BaseResponse.SUCCESS);
            baseResponse.setData(list);
            return baseResponse;
        } catch (Exception e) {
            LOGGER.error("京东自营非自营批量抓单接口调用失败", e);
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
                expInvokeLog.setOrderCode(code).setBusinessCode(code);
                expInvokeLogService.insert(expInvokeLog);
            } else {
                for (String order : list) {
                    expInvokeLog.setOrderCode(code)
                            .setBusinessCode(code);
                    JdOrderDto jdOrder = JacksonUtils.readValue(order,JdOrderDto.class);
                    if (jdOrder != null) {
                        String orderNo = jdOrder.getWaybillInfo().getOrderId();//主订单号
                        expInvokeLog.setOrderCode(orderNo).setBusinessCode(orderNo);
                    }
                    expInvokeLogService.insert(expInvokeLog);
                }
            }
        }
        return baseResponse;
    }

    /**
     * 云霄购抓单
     */
    @Override
    public BaseResponse<List<String>> grabYxOrder(JdRequest jdRequest,String code) {
        ExpInvokeLog expInvokeLog = new ExpInvokeLog()
                .setInterfaceEnum(ExpCodeEnum.JD002)
                .setCode(ExpCodeEnum.JD002.getCode())
                .setName(ExpCodeEnum.JD002.getName())
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.JD.getCode())
                .setStartTime(DateUtils.getNowTime())
                .setFlag(SuccessFailureEnum.FAILURE);

        List<String> list = Lists.newArrayList();
        BaseResponse<List<String>> baseResponse = new BaseResponse<>();
        try {

            JdResponse jdResponse = doPost(jdRequest,expInvokeLog);

            if (jdResponse != null &&
                    jdResponse.getJingdong_YunXiaoServiceProviderService_queryOrderByParam_responce() != null &&
                    jdResponse.getJingdong_YunXiaoServiceProviderService_queryOrderByParam_responce()
                            .getOrderListResult() != null &&
                    jdResponse.getJingdong_YunXiaoServiceProviderService_queryOrderByParam_responce()
                            .getOrderListResult().getHeader() != null &&
                    "0".equals(jdResponse.getJingdong_YunXiaoServiceProviderService_queryOrderByParam_responce()
                            .getCode())) {
                expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
                list = jdResponse.getJingdong_YunXiaoServiceProviderService_queryOrderByParam_responce()
                        .getOrderListResult().getBody();
            }
            baseResponse.setFlag(BaseResponse.SUCCESS);
            baseResponse.setData(list);
            return baseResponse;
        } catch (Exception e) {
            LOGGER.error("京东云霄购批量抓单接口调用失败", e);
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
                expInvokeLog.setOrderCode(code)
                        .setBusinessCode(code);
                expInvokeLogService.insert(expInvokeLog);
            } else {
                for (String order : list) {
                    JdOrderDto jdOrder = JacksonUtils.readValue(order,JdOrderDto.class);
                    expInvokeLog.setOrderCode(code)
                            .setBusinessCode(code);
                    if (jdOrder != null) {
                        String orderNo = jdOrder.getGuangZhouYunXiaoOrderEntity().getSpSoNo();//主订单号
                        expInvokeLog.setOrderCode(orderNo).setBusinessCode(orderNo);
                    }
                    expInvokeLogService.insert(expInvokeLog);
                }
            }
        }
        return baseResponse;
    }


    /**
     * 多渠道独立站抓单
     */
    @Override
    public BaseResponse<List<String>> grabDlzOrder(JdRequest jdRequest,String code) {
        ExpInvokeLog expInvokeLog = new ExpInvokeLog()
                .setInterfaceEnum(ExpCodeEnum.JD003)
                .setCode(ExpCodeEnum.JD003.getCode())
                .setName(ExpCodeEnum.JD003.getName())
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.JD.getCode())
                .setStartTime(DateUtils.getNowTime())
                .setFlag(SuccessFailureEnum.FAILURE);

        List<String> list = Lists.newArrayList();
        BaseResponse<List<String>> baseResponse = new BaseResponse<>();
        try {

            JdResponse jdResponse = doPost(jdRequest,expInvokeLog);

            if (jdResponse != null &&
                    jdResponse.getJingdong_dlz_guangzhou_customs_queryPeriodOrder_responce() != null &&
                    jdResponse.getJingdong_dlz_guangzhou_customs_queryPeriodOrder_responce()
                            .getQueryperiodorder_result() != null &&
                    jdResponse.getJingdong_dlz_guangzhou_customs_queryPeriodOrder_responce()
                            .getQueryperiodorder_result().getHeader() != null &&
                    "0".equals(jdResponse.getJingdong_dlz_guangzhou_customs_queryPeriodOrder_responce()
                            .getCode())) {
                expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
                list = jdResponse.getJingdong_dlz_guangzhou_customs_queryPeriodOrder_responce()
                        .getQueryperiodorder_result().getBody();
            }
            baseResponse.setFlag(BaseResponse.SUCCESS);
            baseResponse.setData(list);
            return baseResponse;
        } catch (Exception e) {
            LOGGER.error("京东独立站批量抓单接口调用失败", e);
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
                expInvokeLog.setOrderCode(code)
                        .setBusinessCode(code);
                expInvokeLogService.insert(expInvokeLog);
            } else {
                for (String order : list) {
                    JdDlzOrderDto jdOrder = JacksonUtils.readValue(order,JdDlzOrderDto.class);
                    expInvokeLog.setOrderCode(code)
                            .setBusinessCode(code);
                    if (jdOrder != null) {
                        String orderNo = jdOrder.getWaybillInfo().getOrderId();//主订单号
                        expInvokeLog.setOrderCode(orderNo).setBusinessCode(orderNo);
                    }
                    expInvokeLogService.insert(expInvokeLog);
                }
            }
        }
        return baseResponse;
    }


    private JdResponse doPost(JdRequest jdRequest,ExpInvokeLog expInvokeLog) {
        //调用京东抓单接口
        String jdUrl = this.interfaceUrlService.getUrl("jd.get.order");
        BusinessAssert.assertNotEmpty(jdUrl, "京东订单接口地址[jd.get.order]未配置");
        expInvokeLog.setInterfaceUrl(jdUrl);

        MultiValueMap<String, String> requestParams = buildRequest(jdRequest);

        expInvokeLog.setRequestData(JacksonUtils.toJSon(requestParams));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity<MultiValueMap<String, String>> r = new HttpEntity<MultiValueMap<String, String>>(
                requestParams, headers);
        expInvokeLog.setResponseData(JacksonUtils.toJSon(requestParams));
        //调用京东抓单接口
        String response = restTemplate.postForObject(jdUrl, r, String.class);
        //String response = "{\"jingdong_dlz_guangzhou_customs_queryPeriodOrder_responce\":{\"code\":\"0\",\"queryperiodorder_result\":{\"pageSize\":100,\"body\":[\"{\\\"waybillInfo\\\":{\\\"orderId\\\":\\\"14545189862118\\\",\\\"eclpCode\\\":\\\"EBU0000000005922\\\",\\\"platformId\\\":\\\"67\\\",\\\"platformName\\\":\\\"山姆会员商店\\\",\\\"createTime\\\":\\\"2020-03-10 16:04:31\\\",\\\"ieFlag\\\":\\\"I\\\",\\\"customsType\\\":\\\"1\\\",\\\"carrier\\\":\\\"0000090\\\",\\\"orderType\\\":\\\"5\\\",\\\"wayBillNo\\\":\\\"JDVB03069452255\\\",\\\"freight\\\":\\\"0.00000\\\",\\\"freightCurr\\\":\\\"CNY\\\",\\\"tax\\\":\\\"0.00000\\\",\\\"taxCurr\\\":\\\"CNY\\\",\\\"orderSum\\\":\\\"882.60000\\\",\\\"discount\\\":\\\"15.00000\\\",\\\"discountNote\\\":\\\"优惠金额\\\",\\\"num\\\":\\\"1\\\",\\\"goodInfo\\\":\\\"\\\",\\\"insuredFee\\\":\\\"0\\\",\\\"buyerPhone\\\":\\\"18920967581\\\",\\\"buyerName\\\":\\\"战经黎\\\",\\\"buyerIdType\\\":\\\"1\\\",\\\"buyerIdNumber\\\":\\\"120103197505190043\\\",\\\"recipientName\\\":\\\"战经黎\\\",\\\"recipientProvincesName\\\":\\\"天津 天津市 西青区\\\",\\\"recipientProvincesCode\\\":\\\"120000\\\",\\\"recipientDetailedAddress\\\":\\\"江湾路万绿花园东富力津门湖西子花园3号楼1门\\\",\\\"recipientPhone\\\":\\\"18920967581\\\",\\\"shipperCountryCode\\\":\\\"156\\\",\\\"venderId\\\":\\\"\\\",\\\"modelId\\\":\\\"beihuo\\\",\\\"storeId\\\":\\\"110007016\\\"},\\\"qingDanInfo\\\":{\\\"ebpCode\\\":\\\"4403140Q2R\\\",\\\"ebcCode\\\":\\\"4403140Q2R\\\",\\\"voIepc\\\":\\\"5165\\\",\\\"voQyState\\\":\\\"142\\\",\\\"ciqbCode\\\":\\\"000069\\\",\\\"payCode\\\":\\\"4403169D3W\\\",\\\"payName\\\":\\\"财付通支付科技有限公司\\\",\\\"paymentNo\\\":\\\"420000047220200310822634403700\\\",\\\"receiveNo\\\":\\\"120103197505190043\\\"},\\\"listGoods\\\":[{\\\"goIndex\\\":\\\"1\\\",\\\"qty\\\":\\\"2\\\",\\\"decPrice\\\":\\\"37.05000\\\",\\\"decTotalPrice\\\":\\\"74.10000\\\",\\\"curr\\\":\\\"CNY\\\",\\\"sellerRecord\\\":\\\"WMT4901201113369\\\",\\\"skuCustomerCode\\\":\\\"10442486\\\",\\\"voUnit\\\":\\\"\\\",\\\"goNo\\\":\\\"2101110000\\\",\\\"goodsName\\\":\\\"悠诗诗 UCC 速溶咖啡 耳挂滴落特殊浓香速溶咖啡 7g*18袋\\\",\\\"spe\\\":\\\"7g*18袋/盒\\\",\\\"country\\\":\\\"116\\\",\\\"netWeight\\\":\\\"0.126\\\",\\\"unit1\\\":\\\"036\\\",\\\"unit2\\\":\\\"\\\",\\\"amount1\\\":\\\"0\\\",\\\"amount2\\\":\\\"\\\",\\\"skuId\\\":\\\"ESG4418184188412\\\",\\\"brand\\\":\\\"\\\"},{\\\"goIndex\\\":\\\"2\\\",\\\"qty\\\":\\\"2\\\",\\\"decPrice\\\":\\\"13.60000\\\",\\\"decTotalPrice\\\":\\\"27.20000\\\",\\\"curr\\\":\\\"CNY\\\",\\\"sellerRecord\\\":\\\"WMT4902458005605\\\",\\\"skuCustomerCode\\\":\\\"10482960\\\",\\\"voUnit\\\":\\\"\\\",\\\"goNo\\\":\\\"1905310000\\\",\\\"goodsName\\\":\\\"Hokka北陆制果焦糖杏仁乳酸菌饼干 56g\\\",\\\"spe\\\":\\\"56g/包\\\",\\\"country\\\":\\\"116\\\",\\\"netWeight\\\":\\\"0.056\\\",\\\"unit1\\\":\\\"035\\\",\\\"unit2\\\":\\\"\\\",\\\"amount1\\\":\\\"0\\\",\\\"amount2\\\":\\\"\\\",\\\"skuId\\\":\\\"ESG4418193194738\\\",\\\"brand\\\":\\\"\\\"},{\\\"goIndex\\\":\\\"3\\\",\\\"qty\\\":\\\"1\\\",\\\"decPrice\\\":\\\"89.00000\\\",\\\"decTotalPrice\\\":\\\"89.00000\\\",\\\"curr\\\":\\\"CNY\\\",\\\"sellerRecord\\\":\\\"WMT27917026718\\\",\\\"skuCustomerCode\\\":\\\"10142921\\\",\\\"voUnit\\\":\\\"\\\",\\\"goNo\\\":\\\"1704900000\\\",\\\"goodsName\\\":\\\"Vitafusion 成人褪黑素软糖 140粒\\\",\\\"spe\\\":\\\"140粒/瓶\\\",\\\"country\\\":\\\"502\\\",\\\"netWeight\\\":\\\"0.43\\\",\\\"unit1\\\":\\\"035\\\",\\\"unit2\\\":\\\"\\\",\\\"amount1\\\":\\\"0\\\",\\\"amount2\\\":\\\"\\\",\\\"skuId\\\":\\\"ESG4418153291529\\\",\\\"brand\\\":\\\"\\\"},{\\\"goIndex\\\":\\\"4\\\",\\\"qty\\\":\\\"1\\\",\\\"decPrice\\\":\\\"284.00000\\\",\\\"decTotalPrice\\\":\\\"284.00000\\\",\\\"curr\\\":\\\"CNY\\\",\\\"sellerRecord\\\":\\\"WMT4908049338886\\\",\\\"skuCustomerCode\\\":\\\"10400261\\\",\\\"voUnit\\\":\\\"\\\",\\\"goNo\\\":\\\"2106909090\\\",\\\"goodsName\\\":\\\"FANCL50岁女性综合维生素胶囊 210粒\\\",\\\"spe\\\":\\\"210粒/袋\\\",\\\"country\\\":\\\"116\\\",\\\"netWeight\\\":\\\"0.065\\\",\\\"unit1\\\":\\\"035\\\",\\\"unit2\\\":\\\"\\\",\\\"amount1\\\":\\\"0\\\",\\\"amount2\\\":\\\"\\\",\\\"skuId\\\":\\\"ESG4418198405015\\\",\\\"brand\\\":\\\"\\\"},{\\\"goIndex\\\":\\\"5\\\",\\\"qty\\\":\\\"1\\\",\\\"decPrice\\\":\\\"257.60000\\\",\\\"decTotalPrice\\\":\\\"257.60000\\\",\\\"curr\\\":\\\"CNY\\\",\\\"sellerRecord\\\":\\\"WMT4908049338855\\\",\\\"skuCustomerCode\\\":\\\"WMT4908049338855\\\",\\\"voUnit\\\":\\\"\\\",\\\"goNo\\\":\\\"2106909090\\\",\\\"goodsName\\\":\\\"FANCL芳珂 40岁男性综合营养包 30包\\\",\\\"spe\\\":\\\"30包/袋\\\",\\\"country\\\":\\\"日本奈良县\\\",\\\"netWeight\\\":\\\"0.0723\\\",\\\"unit1\\\":\\\"035\\\",\\\"unit2\\\":\\\"\\\",\\\"amount1\\\":\\\"0.0723\\\",\\\"amount2\\\":\\\"\\\",\\\"skuId\\\":\\\"ESG4418198414783\\\",\\\"brand\\\":\\\"\\\"},{\\\"goIndex\\\":\\\"6\\\",\\\"qty\\\":\\\"2\\\",\\\"decPrice\\\":\\\"61.75000\\\",\\\"decTotalPrice\\\":\\\"123.50000\\\",\\\"curr\\\":\\\"CNY\\\",\\\"sellerRecord\\\":\\\"WMT9311770599009\\\",\\\"skuCustomerCode\\\":\\\"10209387\\\",\\\"voUnit\\\":\\\"\\\",\\\"goNo\\\":\\\"2106909090\\\",\\\"goodsName\\\":\\\"Swisse高强度西芹籽精华胶囊 50粒\\\",\\\"spe\\\":\\\"50粒/瓶\\\",\\\"country\\\":\\\"601\\\",\\\"netWeight\\\":\\\"0.2\\\",\\\"unit1\\\":\\\"035\\\",\\\"unit2\\\":\\\"\\\",\\\"amount1\\\":\\\"0\\\",\\\"amount2\\\":\\\"\\\",\\\"skuId\\\":\\\"EMG4418050128245\\\",\\\"brand\\\":\\\"\\\"},{\\\"goIndex\\\":\\\"7\\\",\\\"qty\\\":\\\"2\\\",\\\"decPrice\\\":\\\"13.60000\\\",\\\"decTotalPrice\\\":\\\"27.20000\\\",\\\"curr\\\":\\\"CNY\\\",\\\"sellerRecord\\\":\\\"WMT4902458001720\\\",\\\"skuCustomerCode\\\":\\\"10482953\\\",\\\"voUnit\\\":\\\"\\\",\\\"goNo\\\":\\\"1905310000\\\",\\\"goodsName\\\":\\\"Hokka北陆制果树莓味饼干 75g\\\",\\\"spe\\\":\\\"75g/包\\\",\\\"country\\\":\\\"116\\\",\\\"netWeight\\\":\\\"0.075\\\",\\\"unit1\\\":\\\"035\\\",\\\"unit2\\\":\\\"\\\",\\\"amount1\\\":\\\"0\\\",\\\"amount2\\\":\\\"\\\",\\\"skuId\\\":\\\"ESG4418198118824\\\",\\\"brand\\\":\\\"\\\"}]}\"],\"totalCount\":5,\"page\":1,\"header\":{\"resultMsg\":\"成功\",\"resultStatus\":\"0\"}}}}";
        expInvokeLog.setResponseData(response);
        JdResponse jdResponse = JacksonUtils.readValue(response, JdResponse.class);
        return  jdResponse;
    }

    /**
     * 拼接京东请求
     */
    private MultiValueMap<String, String> buildRequest(JdRequest jdRequest) {
        JdConfig jdConfig = JacksonUtils
                .readValue(jdRequest.getCatchOrderConfig().getPlatformArguments(), JdConfig.class);

        String appSecret = jdConfig.getAppSecret();
        String appKey = jdConfig.getAppKey();
        String accessToken = jdConfig.getAccessToken();

        BusinessAssert.assertNotEmpty(appKey, "接单配置没有配置appKey参数");
        BusinessAssert.assertNotEmpty(appSecret, "接单配置没有配置appSecret参数");
        BusinessAssert.assertNotEmpty(accessToken, "接单配置没有配置accessToken参数");
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();

        requestParams.add("timestamp",
                DateUtils.dateToString(DateUtils.getNowTime(), DateUtils.DATETIME_PATTERN));
        requestParams.add("v", "2.0");
        requestParams.add("method", jdRequest.getMethod());
        requestParams.add("app_key", appKey);
        requestParams.add("access_token", accessToken);
        if (jdRequest.isYx()) {
            requestParams.add("customsId", jdConfig.getCustomsId());
            requestParams.add("serviceId", jdConfig.getServiceId());
        }
        if (jdRequest.isBatch()) {
            requestParams.add("360buy_param_json", JacksonUtils.toJSon(batchParam(jdRequest)));
        }else {
            requestParams.add("360buy_param_json", JacksonUtils.toJSon(singleParam(jdRequest)));
        }
        //requestParams.add("360buy_param_json", JacksonUtils.toJSon(params));
        //加签
        setSign(requestParams, appSecret);
        return requestParams;
    }

    /**批量抓取的参数**/
    private Map<String, Object> batchParam(JdRequest jdRequest) {
        /*CatchOrderConfig catchOrderConfig = jdRequest.getCatchOrderConfig();
        int intervalCount = catchOrderConfig.getIntervalCount();*/
        Map<String, Object> params = new HashMap<String, Object>();
        /*Date beginDate = catchOrderConfig.getLastQueryTime() == null ? DateUtils.addMinute(DateUtils.getNowTime(), -10)
                : catchOrderConfig.getLastQueryTime();*/
        //Date beginDate = catchOrderConfig.getLastQueryTime();
        /*catchOrderConfig.setLastQueryTime(beginDate);*/
        params.put("beginDate", jdRequest.getBeginDate().getTime());
        //long endDate = DateUtils.addMinute(beginDate, intervalCount).getTime();
        params.put("endDate", jdRequest.getEndDate().getTime());
        params.put("page", jdRequest.getPage());
        //type:查询类型[1:订单创建时间 2:服务商报文创建时间] 
        params.put("type", 2);
        return params;
    }

    /**单条抓取的参数**/
    private Map<String, Object> singleParam(JdRequest jdRequest){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("orderId", jdRequest.getOrderId());
        if (jdRequest.isDlz()) {
            params.put("platformId", jdRequest.getPlatformId());
        }
        return params;
    }

    /**
     * 加签
     */
    private void setSign(MultiValueMap<String, String> requestParams, String appSecret) {
        Set<String> keySet = requestParams.keySet();
        String[] keys = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keys);
        String sign = "";
        for (String key : keys) {
            Object value = requestParams.get(key);
            if (value == null) {
                requestParams.remove(key);
                continue;
            }
            String valueStr = null;
            if (value instanceof Map) {
                valueStr = JacksonUtils.toJSon(value);
            } else {
                valueStr = String.valueOf(value);
            }
            sign = StringUtils.join(sign, key, valueStr);
        }
        sign = StringUtils.join(appSecret, sign, appSecret);
        try {
            sign = MD5Utils.md5Hex(sign.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("sign进行MD5加密出错!");
        }
        requestParams.add("sign", sign.toUpperCase());

    }

    @Override
    @HttpLog
    public List<String> grabOneOrder(JdRequest jdRequest) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get()
                .setInterfaceEnum(ExpCodeEnum.JD004)
                .setCode(ExpCodeEnum.JD004.getCode())
                .setName(ExpCodeEnum.JD004.getName())
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.JD.getCode())
                .setStartTime(DateUtils.getNowTime())
                .setFlag(SuccessFailureEnum.FAILURE)
                .setOrderCode(jdRequest.getOrderId())
                .setBusinessCode(jdRequest.getOrderId());
        JdResponse jdResponse = doPost(jdRequest,expInvokeLog);

        List<String> list = Lists.newArrayList();
        if (jdResponse != null &&
                jdResponse.getJingdong_guangzhou_customs_queryOrderByOrderId_responce() != null &&
                jdResponse.getJingdong_guangzhou_customs_queryOrderByOrderId_responce()
                        .getOrderResult() != null &&
                jdResponse.getJingdong_guangzhou_customs_queryOrderByOrderId_responce()
                        .getOrderResult().getHeader() != null &&
                "0".equals(jdResponse.getJingdong_guangzhou_customs_queryOrderByOrderId_responce()
                        .getCode())) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            list = jdResponse.getJingdong_guangzhou_customs_queryOrderByOrderId_responce()
                    .getOrderResult().getBody();
        }
        return list;
    }

    @Override
    @HttpLog
    public List<String> grabOneYxOrder(JdRequest jdRequest) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get()
                .setInterfaceEnum(ExpCodeEnum.JD005)
                .setCode(ExpCodeEnum.JD005.getCode())
                .setName(ExpCodeEnum.JD005.getName())
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.JD.getCode())
                .setStartTime(DateUtils.getNowTime())
                .setFlag(SuccessFailureEnum.FAILURE)
                .setOrderCode(jdRequest.getOrderId())
                .setBusinessCode(jdRequest.getOrderId());
        JdResponse jdResponse = doPost(jdRequest,expInvokeLog);

        List<String> list = Lists.newArrayList();
        if (jdResponse != null &&
                jdResponse.getJingdong_YunXiaoServiceProviderService_queryOrderByOrderId_responce() != null &&
                jdResponse.getJingdong_YunXiaoServiceProviderService_queryOrderByOrderId_responce()
                        .getOrderResult() != null &&
                jdResponse.getJingdong_YunXiaoServiceProviderService_queryOrderByOrderId_responce()
                        .getOrderResult().getHeader() != null &&
                "0".equals(jdResponse.getJingdong_YunXiaoServiceProviderService_queryOrderByOrderId_responce()
                        .getCode())) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            list = jdResponse.getJingdong_YunXiaoServiceProviderService_queryOrderByOrderId_responce()
                    .getOrderResult().getBody();
        }
        return list;
    }

    @Override
    @HttpLog
    public List<String> grabOneDlzOrder(JdRequest jdRequest) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get()
                .setInterfaceEnum(ExpCodeEnum.JD006)
                .setCode(ExpCodeEnum.JD006.getCode())
                .setName(ExpCodeEnum.JD006.getName())
                .setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.JD.getCode())
                .setStartTime(DateUtils.getNowTime())
                .setFlag(SuccessFailureEnum.FAILURE)
                .setOrderCode(jdRequest.getOrderId())
                .setBusinessCode(jdRequest.getOrderId());
        JdResponse jdResponse = doPost(jdRequest,expInvokeLog);

        List<String> list = Lists.newArrayList();
        if (jdResponse != null &&
                jdResponse.getJingdong_dlz_guangzhou_customs_queryOrderByOrderId_responce() != null &&
                jdResponse.getJingdong_dlz_guangzhou_customs_queryOrderByOrderId_responce()
                        .getQueryorder_result() != null &&
                jdResponse.getJingdong_dlz_guangzhou_customs_queryOrderByOrderId_responce()
                        .getQueryorder_result().getHeader() != null &&
                "0".equals(jdResponse.getJingdong_dlz_guangzhou_customs_queryOrderByOrderId_responce()
                        .getCode())) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            list = jdResponse.getJingdong_dlz_guangzhou_customs_queryOrderByOrderId_responce()
                    .getQueryorder_result().getBody();
        }
        return list;
    }
}
