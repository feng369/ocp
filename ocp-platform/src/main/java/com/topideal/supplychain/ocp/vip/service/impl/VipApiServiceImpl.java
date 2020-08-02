package com.topideal.supplychain.ocp.vip.service.impl;

import com.alibaba.fastjson.JSON;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.order.model.OrderVip;
import com.topideal.supplychain.ocp.vip.dto.Configure;
import com.topideal.supplychain.ocp.vip.dto.VipRequestArgsVo;
import com.topideal.supplychain.ocp.vip.dto.VipRequestDto;
import com.topideal.supplychain.ocp.vip.dto.VipResponseDto;
import com.topideal.supplychain.ocp.vip.service.VipApiService;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.config.service.InterfaceUrlService;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import com.topideal.supplychain.web.log.service.ExpInvokeLogService;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>标题: 唯品会接口</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.vip.service.impl</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/4 16:14</p>
 *
 * @version 1.0
 */
@Service
public class VipApiServiceImpl implements VipApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VipApiServiceImpl.class);
    @Autowired
    private InterfaceUrlService interfaceUrlService;
    @Autowired
    private ExpInvokeLogService expInvokeLogService;


    /**
     * 唯品订单抓取任务 {"returnCode": "0","result": {"responseData":"ewogICAgInJld......内容较长省略......="}
     * 成功回执 {"returnCode":"vipapis.appkey-not-exist","returnMessage":"AppKey does not exist,please
     * check it!"} 失败回执 {"head": {"responseTime":"2019-01-04 14:54:53","code": "200","msg":
     * "系统无任何异常"},"mpOrderList":* [......]
     */
    @Override
    public List<OrderVip> grabOrders(CatchOrderConfig config, VipRequestArgsVo requestArgsVo, VipRequestArgsVo.Args args) {
        List<OrderVip> orderVips = Lists.newArrayList();
        // 构建抓单请求参数
        String requestStr = buildGrabReq(config, requestArgsVo, args);
        // 执行接口调用
        executeRequest(ExpCodeEnum.VIP001, requestStr, requestArgsVo, orderVips);
        return orderVips;
    }

    /**
     * 抓单订单信息回抛
     *
     * @param orderVips
     * @param vipRequestArgsVo
     */
    @Override
    public boolean feedBack(List<OrderVip> orderVips, VipRequestArgsVo vipRequestArgsVo) {
        String requestStr = buildFeedBackReq(orderVips.stream().map(OrderVip::getOrderNo).collect(Collectors.toList()), vipRequestArgsVo);
        // 调用订单请求接口
        VipResponseDto.ResponseHeadDto responseHeadDto = executeRequest(ExpCodeEnum.VIP002, requestStr, vipRequestArgsVo, orderVips);
        return responseHeadDto != null && VipResponseDto.ResponseHeadDto.SUCCESS.equals(responseHeadDto.getCode());
    }

    /**
     * 构建抓单请求参数,请求参数参考跨境api文档 {requestData:"ewogICJ1c2VySWQiO.....省略.....gICJjb3VudCI6IDEwMAp9"}
     * {"appKey": "v123htc","customsCode": "5165","customsCode": "003","count":100}
     */
    private String buildGrabReq(CatchOrderConfig config, VipRequestArgsVo requestArgsVo, VipRequestArgsVo.Args args) {
        // 抓单请求头
        VipRequestDto vipRequestDto = new VipRequestDto();
        // 抓单请求体
        VipRequestDto.VipGrabReqDto vipGrabReqDto = new VipRequestDto.VipGrabReqDto();
        vipGrabReqDto.setAppKey(requestArgsVo.getAppKey());
        vipGrabReqDto.setCustomsCode(args.getCustomsCode());
        vipGrabReqDto.setTradeMode(args.getTradeMode());
        vipGrabReqDto.setCount(config.getPageSize());
        // 设置抓单请求体时需要加密
        vipRequestDto.setRequestData(Configure.VipUtil.encodeStr(JacksonUtils.toJSon(vipGrabReqDto)));
        return JacksonUtils.toJSon(vipRequestDto);
    }

    /**
     * 构建返回订单请求参数
     */
    private String buildFeedBackReq(List<String> orderSnList, VipRequestArgsVo requestArgsVo) {
        VipRequestDto vipRequestDto = new VipRequestDto();
        VipRequestDto.VipFeedbackReqDto vipFeedbackReqDto = new VipRequestDto.VipFeedbackReqDto();
        vipFeedbackReqDto.setAppKey(requestArgsVo.getAppKey());
        vipFeedbackReqDto.setOrderSnList(orderSnList);
        vipRequestDto.setRequestData(Configure.VipUtil.encodeStr(JacksonUtils.toJSon(vipFeedbackReqDto)));
        return JSON.toJSON(vipRequestDto).toString();
    }

    /**
     * 唯品会统一调用接口
     */
    private VipResponseDto.ResponseHeadDto executeRequest(ExpCodeEnum expCodeEnum, String requestData, VipRequestArgsVo requestArgsVo, List<OrderVip> orderVips) {
        String responseBody = "";
        //订单请求
        VipResponseDto.ResponseData responseInfo = new VipResponseDto.ResponseData();
        VipResponseDto.ResponseHeadDto responseHeadDto = new VipResponseDto.ResponseHeadDto();
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setCode(expCodeEnum.getCode()).setName(expCodeEnum.getName()).setSource(SourceEnum.VIP.getCode()).setTarget(SourceEnum.OCP.getCode()).setRequestData(requestData).setFlag(SuccessFailureEnum.FAILURE).setStartTime(new Date());
        try {
            //接口地址
            String url = interfaceUrlService.getUrl("vip.order.url");
            BusinessAssert.assertNotEmpty(url, "接口地址未配置");
            expInvokeLog.setInterfaceUrl(url);
            //唯品会配置信息
            Configure configure = Configure.build().setAppKey(requestArgsVo.getAppKey())
                    .setAppSecrect(requestArgsVo.getAppSecrect())
                    .setFormat(requestArgsVo.getFormat())
                    .setService(requestArgsVo.getService())
                    .setTimestamp(String.valueOf(System.currentTimeMillis() / 1000))
                    .setVersion(requestArgsVo.getVersion())// 版本号
                    .setUrl(url)// 访问URL
                    .setBusinessData(requestData)
                    .setMethod(ExpCodeEnum.VIP001.equals(expCodeEnum) ? requestArgsVo.getOrderMethod() : requestArgsVo.getFeedbackMethod());// 业务参数
            //加签
            Map<String, String> sysParams = doSign(configure);
            //生成post请求
            HttpPost post = new HttpPost(configure.getUrl() + "?" + Configure.VipUtil.getQueryString(sysParams));
            post.setEntity(new StringEntity(requestData, Configure.VipUtil.getContentType(configure.getFormat())));
            CloseableHttpResponse response = HttpClientBuilder.create().build().execute(post);
            // 处理响应结果
            responseBody = dealResponse(response);
            //判断响应是否正确，并记录接口日志
            VipResponseDto responseVO = JacksonUtils.readValue(responseBody, VipResponseDto.class);
            BusinessAssert.assertIsFalse(responseVO == null || !"0".equals(responseVO.getReturnCode()) || responseVO.getResult() == null, "唯品会接口调用失败");
            //对响应实体解密
            String responseData = Configure.VipUtil.decodeStr(responseVO.getResult().getResponseData());
            responseInfo = JacksonUtils.readValue(responseData, VipResponseDto.ResponseData.class);
            BusinessAssert.assertNotNull(responseInfo, "唯品订单信息为空！");
            //判断响应状态
            responseHeadDto = responseInfo.getHead();
            BusinessAssert.assertIsFalse(responseHeadDto == null || !VipResponseDto.ResponseHeadDto.SUCCESS.equals(responseHeadDto.getCode()), "唯品会接口返回失败");
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS).setResponseData(responseData);
        } catch (Exception e) {
            LOGGER.error("唯品会接口调用异常：", e);
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE).setResponseData(responseBody + e.getMessage());
        } finally {
            // 需要定义业务单号
            String businessCode;
            //抓单接口日志拆分
            switch (expCodeEnum) {
                case VIP001:  //唯品会转单接口日志
                    businessCode = "唯品抓单";
                    if (responseInfo == null || CollectionUtils.isEmpty(responseInfo.getMpOrderList())) {
                        expInvokeLog.setFlag(SuccessFailureEnum.FAILURE).setBusinessCode(businessCode).setOrderCode(businessCode).setEndTime(new Date());
                        expInvokeLogService.insert(expInvokeLog);
                    } else {
                        for (OrderVip orderVip : responseInfo.getMpOrderList()) {
                            businessCode = orderVip.getOrderNo();
                            orderVips.add(orderVip);
                            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS).setBusinessCode(businessCode).setOrderCode(businessCode).setEndTime(new Date());
                            expInvokeLogService.insert(expInvokeLog);
                        }
                    }
                    break;
                case VIP002:  //唯品会回推接口日志
                    businessCode = "唯品返回";
                    if (CollectionUtils.isEmpty(orderVips)) {
                        expInvokeLog.setFlag(SuccessFailureEnum.FAILURE).setBusinessCode(businessCode).setOrderCode(businessCode).setEndTime(new Date());
                        expInvokeLogService.insert(expInvokeLog);
                    } else {
                        for (OrderVip orderVip : orderVips) {
                            businessCode = orderVip.getOrderNo();
                            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS).setBusinessCode(businessCode).setOrderCode(businessCode).setEndTime(new Date());
                            expInvokeLogService.insert(expInvokeLog);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return responseHeadDto;
    }

    /**
     * 签名
     */
    private static Map<String, String> doSign(Configure configure) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        Map<String, String> params = getSysParam(configure);
        String data = Configure.VipUtil.convertToSortStr(params) + configure.getBusinessData();
        String sign = Configure.VipUtil.byte2hex(Configure.VipUtil.encryptHMAC(data, configure.getAppSecrect()));
        params.put("sign", sign);
        return params;
    }

    /**
     * 准备系统参数
     */
    private static Map<String, String> getSysParam(Configure configure) {
        Map<String, String> params = new HashMap<>();
        params.put(Configure.Constant.SERVICE_NAME, configure.getService());// addressService-getChannelOrderList
        params.put(Configure.Constant.METHOD_NAME, configure.getMethod());
        params.put(Configure.Constant.TIMESTAMP, String.valueOf(System.currentTimeMillis() / 1000));
        params.put(Configure.Constant.FORMAT, configure.getFormat());
        params.put(Configure.Constant.APP_KEY, configure.getAppKey());
        params.put(Configure.Constant.VERSION, configure.getVersion());
        return params;
    }

    /**
     * 唯品会接口调用
     */
    private static String dealResponse(HttpResponse res) throws Exception {
        int code = res.getStatusLine().getStatusCode();
        BusinessAssert.assertIsFalse(200 != code, "Request vipapis interface Exception【" + code + "】！");
        org.apache.http.HttpEntity entity = res.getEntity();
        BusinessAssert.assertIsFalse(entity.getContentLength() == 0, "business-Interface no response data!");
        return Configure.VipUtil.stream2Str(entity.getContent());
    }
}
