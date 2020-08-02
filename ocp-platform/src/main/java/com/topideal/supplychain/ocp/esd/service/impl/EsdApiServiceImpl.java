package com.topideal.supplychain.ocp.esd.service.impl;

import com.google.common.collect.Maps;
import com.topideal.supplychain.common.model.BaseResponse;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.esd.dto.EsdRequest;
import com.topideal.supplychain.ocp.esd.dto.EsdResponse;
import com.topideal.supplychain.ocp.esd.service.EsdApiService;
import com.topideal.supplychain.ocp.master.dto.EsdStoreConfig;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.util.MD5Utils;
import com.topideal.supplychain.web.config.service.InterfaceUrlService;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName EsdApiServiceImpl
 * @TODO TODO
 * @Author zhangzhihao
 * @DATE 2019/12/10 19:06
 * @Version 1.0
 **/
@Service
public class EsdApiServiceImpl implements EsdApiService {

    private Logger LOGGER = LoggerFactory.getLogger(EsdApiServiceImpl.class);

    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HttpLog
    public BaseResponse sendOrder(EsdRequest req, String businessCode, String orderCode,
            String appId, String appKey) {
        EsdStoreConfig esdStoreConfig = EsdStoreConfig.newBuilder().appId(appId).appKey(appKey).build();
        return sendOrder(req, businessCode, orderCode,esdStoreConfig);
    }

    @Override
    @HttpLog
    public BaseResponse sendOrder(EsdRequest esdRequest, String businessCode, String orderCode,Store store){
        EsdStoreConfig esdStoreConfig = JacksonUtils.readValue(store.getEsdArguments(),EsdStoreConfig.class);
        return sendOrder(esdRequest, businessCode, orderCode,esdStoreConfig);
    }


    private BaseResponse sendOrder(EsdRequest esdRequest, String businessCode, String orderCode,EsdStoreConfig esdStoreConfig) {

        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.OCP003)
                .setBusinessCode(businessCode).
                        setOrderCode(orderCode).setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.ESD.getCode());

        String req = JacksonUtils.toJSon(esdRequest);
        expInvokeLog.setRequestData(req);
        //接口地址
        String url = interfaceUrlService.getUrl("esd.send.order");
        expInvokeLog.setInterfaceUrl(url);
        BusinessAssert.assertNotEmpty(url, "接口地址未配置:esd.send.order  ");
        BusinessAssert.assertNotNull(esdStoreConfig,"店铺未配置ESD参数信息");
        BusinessAssert.assertNotEmpty(esdStoreConfig.getAppId(),"店铺未配置ESD[appId]参数信息");
        BusinessAssert.assertNotEmpty(esdStoreConfig.getAppKey(),"店铺未配置ESD[appKey]参数信息");
        Map<String, String> parameters = getCommonParam(esdStoreConfig.getAppId(), req);
        parameters = buildRequestPara(parameters, esdStoreConfig.getAppKey());

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.setAll(parameters);

        HttpHeaders headers = new HttpHeaders();
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity(map, headers);

        String resp = restTemplate.postForObject(url, entity, String.class);
        expInvokeLog.setResponseData(resp);
        EsdResponse esdResponse = JacksonUtils.readValue(resp, EsdResponse.class);
        if (esdResponse == null) {
            return BaseResponse.responseFailure("接口回执异常");
        }
        if (1 == esdResponse.getStatus()) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            return BaseResponse.responseSuccess(esdResponse.getSub_msg());
        }
        //记录成功信息
        return BaseResponse.responseFailure(esdResponse.getSub_msg());
    }

    private Map<String, String> getCommonParam(String appId, String biz_content) {
        Map<String, String> sParaTemp = Maps.newHashMap();
        sParaTemp.put("app_id", appId);
        sParaTemp.put("method", "esdex.receive.bjorder.create"); //方法名，根据业务
        sParaTemp.put("sign_type", "md5");
        sParaTemp.put("format", "json");
        sParaTemp.put("charset", "utf-8");
        sParaTemp.put("version", "1.0");
        sParaTemp.put("timestamp", String.valueOf(System.currentTimeMillis()));
        sParaTemp.put("biz_content", biz_content);
        return sParaTemp;
    }

    /**
     * 生成要请求给卓志速运达的参数数组
     *
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    private Map<String, String> buildRequestPara(Map<String, String> sParaTemp, String appKey) {
        //除去数组中的空值和不需要签名参数
        Map<String, String> sPara = paraFilter(sParaTemp);
        //生成签名结果
        String mysign = buildRequestMysign(sPara, appKey);
        //签名结果与签名方式加入请求提交参数组中
        sParaTemp.put("sign", mysign);

        return sParaTemp;
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = Maps.newHashMap();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("sign_type") || key.equalsIgnoreCase("biz_content")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * 生成签名结果
     *
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
    private String buildRequestMysign(Map<String, String> sPara, String appkey) {
        String prestr = createLinkString(sPara);
        try {
            String mysign = sign(prestr, appkey, "utf-8");
            return mysign;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new BusinessException("签名失败", e);
        }
    }

    /**
     * 签名字符串
     *
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    private String sign(String text, String key, String input_charset) throws Exception {
        text = text + key;
        return MD5Utils.md5Hex(text.getBytes(input_charset));
    }


    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    private String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }


    @Override
    @HttpLog
    public BaseResponse sendOutOrder(EsdRequest esdRequest, String businessCode, String orderCode,
            Store store) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.OCP009)
                .setBusinessCode(businessCode).
                        setOrderCode(orderCode).setSource(SourceEnum.OCP.getCode())
                .setTarget(SourceEnum.ESD.getCode());

        String req = JacksonUtils.toJSon(esdRequest);
        expInvokeLog.setRequestData(req);
        //接口地址
        String url = interfaceUrlService.getUrl("esd.send.out.order");
        expInvokeLog.setInterfaceUrl(url);
        BusinessAssert.assertNotEmpty(url, "接口地址未配置【esd.send.out.order】");
        EsdStoreConfig esdStoreConfig = JacksonUtils.readValue(store.getEsdArguments(),EsdStoreConfig.class);
        BusinessAssert.assertNotNull(esdStoreConfig,"店铺未配置ESD参数信息");
        BusinessAssert.assertNotEmpty(esdStoreConfig.getAppId(),"店铺未配置ESD[appId]参数信息");
        BusinessAssert.assertNotEmpty(esdStoreConfig.getAppKey(),"店铺未配置ESD[appKey]参数信息");
        Map<String, String> parameters = getCommonParam(esdStoreConfig.getAppId(), req);
        parameters = buildRequestPara(parameters, esdStoreConfig.getAppKey());

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.setAll(parameters);

        HttpHeaders headers = new HttpHeaders();
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity(map, headers);

        String resp = restTemplate.postForObject(url, entity, String.class);
        expInvokeLog.setResponseData(resp);
        EsdResponse esdResponse = JacksonUtils.readValue(resp, EsdResponse.class);
        if (esdResponse == null) {
            return BaseResponse.responseFailure("接口回执异常");
        }
        if (1 == esdResponse.getStatus()) {
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            return BaseResponse.responseSuccess(esdResponse.getSub_msg());
        }
        //记录成功信息
        return BaseResponse.responseFailure(esdResponse.getSub_msg());
    }
}
