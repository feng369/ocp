package com.topideal.supplychain.ocp.restful;

import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.big.dto.BigReceiveRequestDto;
import com.topideal.supplychain.ocp.big.dto.BigReceiveResponseDto;
import com.topideal.supplychain.ocp.big.dto.BigValidateStatusEnum;
import com.topideal.supplychain.ocp.big.service.BigService;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
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
 * 标题：大订单restController 模块：ocp-parent 版权: Copyright © 2019 topideal 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.restful 作者：songping 创建日期：2019/12/26 13:40
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/rest/big")
public class BigApiController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BigApiController.class);

    @Autowired
    private BigService bigService;

    /**
     * 接单接口 校验订单参数（保存订单）
     *
     * @return 校验是否成功
     */
    @RequestMapping("/receiveOrder")
    @ResponseBody
    @HttpLog
    public BigReceiveResponseDto receiveOrder(@RequestBody String requestJson) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.BIG001)
                .setSource(SourceEnum.BIG.getCode())
                .setTarget(SourceEnum.OCP.getCode()).setRequestData(requestJson);
        BigReceiveResponseDto responseDto = new BigReceiveResponseDto();
        try {
            BigReceiveRequestDto requestDto = JacksonUtils
                    .readValue(requestJson, BigReceiveRequestDto.class);
            BusinessAssert.assertNotNull(requestDto,"报文格式异常");
            responseDto.setOrderId(requestDto.getOrderId());
            expInvokeLog.setOrderCode(requestDto.getOrderId()).setBusinessCode(requestDto.getOrderId());
            bigService.receiveOrder(requestDto, requestJson);
            responseDto.setStatus(BigValidateStatusEnum.SUCCESS.getValue());
            responseDto.setNotes(BigValidateStatusEnum.SUCCESS.getDesc());
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS)
                    .setBusinessCode(requestDto.getOrderId()).setOrderCode(requestDto.getOrderId());
        } catch (BusinessException be) {
            LOGGER.error("大订单接单异常：" + be.getMessage(), be);
            responseDto.setStatus(BigValidateStatusEnum.FAILURE.getValue());
            responseDto.setNotes(BigValidateStatusEnum.FAILURE.getDesc() + ": " + be.getMessage());
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
        } catch (Exception e) {
            LOGGER.error("大订单接单异常：" + e.getMessage(), e);
            responseDto.setStatus(BigValidateStatusEnum.FAILURE.getValue());
            responseDto.setNotes("大订单接单异常: 系统异常！");
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
        }
        expInvokeLog.setResponseData(JacksonUtils.toJSon(responseDto));
        return responseDto;
    }


}
