package com.topideal.supplychain.ocp.restful;

import com.topideal.supplychain.access.AccessPermission;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.config.model.CatchOrderConfig;
import com.topideal.supplychain.ocp.config.service.CatchOrderConfigService;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.hipac.dto.HipacArgsDto;
import com.topideal.supplychain.ocp.hipac.dto.HipacBody;
import com.topideal.supplychain.ocp.hipac.dto.HipacHead;
import com.topideal.supplychain.ocp.hipac.dto.HipacReceiveRequest;
import com.topideal.supplychain.ocp.hipac.dto.HipacReceiveResponse;
import com.topideal.supplychain.ocp.hipac.dto.HipacResponseBody;
import com.topideal.supplychain.ocp.hipac.dto.HipacResponseHead;
import com.topideal.supplychain.ocp.hipac.service.HipacService;
import com.topideal.supplychain.ocp.master.model.Platform;
import com.topideal.supplychain.ocp.master.service.PlatformService;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.util.MD5Utils;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.xml.bind.JAXB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xuxiaoyan
 * @date 2019-12-19 14:43
 */
@Controller
@RequestMapping("/rest/hipac")
public class HipacApiController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HipacApiController.class);

    @Autowired
    private HipacService hipacService;
    @Autowired
    private CatchOrderConfigService catchOrderConfigService;
    @Autowired
    private PlatformService platformService;

    /**
     * 海拍客接单入口
     * @param requestStr
     * @return
     */
    @HttpLog
    @ResponseBody
    @RequestMapping("/orderReceive")
    public String hipacOrderReceive(@RequestBody String requestStr) {
        // 接口日志
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.HIPAC001)
                .setSource(SourceEnum.HIPAC.getCode())
                .setTarget(SourceEnum.OCP.getCode()).setRequestData(requestStr);
        // 报文转对象
        HipacReceiveRequest req = null;
        String responseBody;
        try {
            req = JAXB.unmarshal(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(requestStr.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8)),
                    HipacReceiveRequest.class);
            validateHipacReq(req);
            //校验签名信息，接收订单
            hipacService.receiveOrder(req, requestStr);
            String orderNum = req.getBody().getHipacOrder().getOrderNum();
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS).setBusinessCode(orderNum).setOrderCode(orderNum);
            responseBody = buildResponseXml(req, "SUCCESS", "接收订单信息成功！");
        } catch (BusinessException e) {
            LOGGER.error("海拍客订单接单异常：" + e.getMessage(), e);
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            responseBody = buildResponseXml(req, "FAIL", e.getMessage());
        } catch (Exception e) {
            LOGGER.error("海拍客订单接单异常：" + e.getMessage(), e);
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            responseBody = buildResponseXml(req, "FAIL", "系统异常，请稍后再试！");
        }

        expInvokeLog.setResponseData(responseBody);

        return responseBody;
    }

    /**
     * javabean转xml格式str
     * @param receiveResponse
     * @return
     */
    private String responseToXml(HipacReceiveResponse receiveResponse) {
        StringBuilder ret = new StringBuilder();
        ret.append("<HipacPush><Head><version>");
        ret.append(receiveResponse.getHead().getVersion()).append("</version>").append("<service>");
        ret.append(receiveResponse.getHead().getService()).append("</service>").append("<sendID>");
        ret.append(receiveResponse.getHead().getSendID()).append("</sendID>").append("<appKey>");
        ret.append(receiveResponse.getHead().getAppKey()).append("</appKey>").append("<retCode>");
        ret.append(receiveResponse.getHead().getRetCode()).append("</retCode>").append("<sign>");
        ret.append(receiveResponse.getHead().getSign()).append("</sign>").append("</Head>").append("<Body>").append("<orderNum>");
        ret.append(receiveResponse.getBody().getOrderNum()).append("</orderNum>").append("<bizCode>");
        ret.append(receiveResponse.getBody().getBizCode()).append("</bizCode>").append("<bizMsg>");
        ret.append(receiveResponse.getBody().getBizMsg()).append("</bizMsg>").append("</Body>").append("</HipacPush>");
        return ret.toString();
    }

    /**
     * 海拍客接单校验
     * @param req
     */
    private void validateHipacReq(HipacReceiveRequest req) {
        BusinessAssert.assertNotNull(req, "报文为空！");
        HipacHead head = req.getHead();
        HipacBody body = req.getBody();
        BusinessAssert.assertNotNull(head, "报文Head为空！");
        BusinessAssert.assertNotNull(body, "报文Body为空！");
        BusinessAssert.assertNotNull(body.getHipacCustomer(), "报文Customer为空！");
        BusinessAssert.assertNotNull(body.getHipacSupplier(), "报文Supplier为空！");
        BusinessAssert.assertNotNull(body.getHipacOrder(), "报文Order为空！");
        BusinessAssert.assertNotNull(body.getHipacPayInfo(), "报文PayInfo为空！");
        BusinessAssert.assertIsFalse(CollectionUtils.isEmpty(body.getOrderItems()), "报文商品明细为空！");
    }

    /**
     * 构建返回报文
     * @param req
     * @param flag
     * @param msg
     * @return
     */
    private String buildResponseXml(HipacReceiveRequest req, String flag, String msg) {
        HipacReceiveResponse receiveResponse = new HipacReceiveResponse();
        HipacResponseHead head = new HipacResponseHead();
        HipacResponseBody body = new HipacResponseBody();

        String appKey = "";
        String md5Key = "";
        // 根据虚拟平台编码解析接单配置
        List<Platform> platforms = platformService
                .findByVirtualCode(PlatformEnum.HIPAC.getCode());
        if (!CollectionUtils.isEmpty(platforms)) {
            List<CatchOrderConfig> catchOrderConfigs = catchOrderConfigService
                    .selectByPlatform(platforms);
            if (!CollectionUtils.isEmpty(catchOrderConfigs) && !StringUtils.isEmpty(catchOrderConfigs.get(0).getPlatformArguments())) {
                String platformArguments = catchOrderConfigs.get(0).getPlatformArguments();
                HipacArgsDto hipacArgsDto = JacksonUtils.readValue(platformArguments, HipacArgsDto.class);
                appKey = hipacArgsDto.getAppKey();
                md5Key = hipacArgsDto.getSecret();
            }
        }
        head.setAppKey(appKey);

        if (null != req) {
            HipacHead reqHead = req.getHead();
            HipacBody reqBody = req.getBody();
            if (null != reqHead) {
                head.setVersion(reqHead.getVersion());
                head.setService(reqHead.getService());
                head.setSendID(reqHead.getSendID());
                String sign = MD5Utils
                        .md5Hex(String.format("appKey=%s&sendID=%s&key=%s", appKey, reqHead.getSendID(),
                                md5Key));
                head.setSign(sign);
            }
            if (null != reqBody) {
                body.setOrderNum(reqBody.getHipacOrder().getOrderNum());
            }

        }

        head.setRetCode(flag);
        body.setBizCode(flag);
        body.setBizMsg(msg);
        receiveResponse.setHead(head);
        receiveResponse.setBody(body);
        String xmlStr = responseToXml(receiveResponse);

        return new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(xmlStr)
                .toString();
    }

}
