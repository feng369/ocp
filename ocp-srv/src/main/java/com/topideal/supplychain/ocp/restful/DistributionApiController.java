package com.topideal.supplychain.ocp.restful;

import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.big.dto.BigReceiveRequestDto;
import com.topideal.supplychain.ocp.big.dto.BigReceiveResponseDto;
import com.topideal.supplychain.ocp.big.dto.BigValidateStatusEnum;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.distribution.service.DistributionService;
import com.topideal.supplychain.ocp.fx.dto.FxReceiveDto;
import com.topideal.supplychain.ocp.fx.dto.FxResponseDto;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 分销订单rest接单入口 平台->卓志ocp
 *
 * @author xuxiaoyan
 * @date 2020-05-22 09:34
 */
@Controller
@RequestMapping("/rest/distribution")
public class DistributionApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributionApiController.class);

    @Autowired
    private DistributionService distributionService;


    /**
     * 接单接口 校验订单参数（保存订单）
     *
     * @return 校验是否成功
     */
    @RequestMapping("/receiveOrder")
    @ResponseBody
    @HttpLog
    public FxResponseDto receiveOrder(@RequestBody String requestJson) {
        // 接口日志
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.FX001)
                .setSource(SourceEnum.FX.getCode())
                .setTarget(SourceEnum.OCP.getCode()).setRequestData(requestJson);
        // 因为分销订单接口字段跟大订单的一致，所以直接沿用大订单的接口实体
        FxResponseDto responseDto = new FxResponseDto();
        try {
            FxReceiveDto requestDto = JacksonUtils
                    .readValue(requestJson, FxReceiveDto.class);
            BusinessAssert.assertNotNull(requestDto,"报文格式异常");
            responseDto.setOrderId(requestDto.getOrderId());
            expInvokeLog.setOrderCode(requestDto.getOrderId()).setBusinessCode(requestDto.getOrderId());
            distributionService.receiveOrder(requestDto, requestJson);
            responseDto.setStatus(BigValidateStatusEnum.SUCCESS.getValue());
            responseDto.setNotes(BigValidateStatusEnum.SUCCESS.getDesc());
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS)
                    .setBusinessCode(requestDto.getOrderId()).setOrderCode(requestDto.getOrderId());
        } catch (BusinessException be) {
            LOGGER.error("分销订单接单异常：" + be.getMessage(), be);
            responseDto.setStatus(BigValidateStatusEnum.FAILURE.getValue());
            responseDto.setNotes(BigValidateStatusEnum.FAILURE.getDesc() + ": " + be.getMessage());
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
        } catch (Exception e) {
            LOGGER.error("分销订单接单异常：" + e.getMessage(), e);
            responseDto.setStatus(BigValidateStatusEnum.FAILURE.getValue());
            responseDto.setNotes("分销订单接单异常: 系统异常！");
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
        }
        expInvokeLog.setResponseData(JacksonUtils.toJSon(responseDto));
        return responseDto;
    }


}
