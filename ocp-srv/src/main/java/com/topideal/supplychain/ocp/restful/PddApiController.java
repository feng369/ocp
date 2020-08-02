package com.topideal.supplychain.ocp.restful;

import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.mq.BasicMessage;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.mq.QueueConstants;
import com.topideal.supplychain.ocp.order.model.OrderTemp;
import com.topideal.supplychain.ocp.order.service.OrderTempService;
import com.topideal.supplychain.ocp.pdd.dto.PddReceiveRequest;
import com.topideal.supplychain.ocp.pdd.dto.PddReceiveResponse;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author klw
 * @date 2019/12/19 15:54
 * @description: 拼多多rest接口
 */
@Controller
@RequestMapping("/rest/pdd")
public class PddApiController {
    private static final Logger logger = LoggerFactory.getLogger(PddApiController.class);

    @Autowired
    private OrderTempService orderTempService;

    /**
     * 接单
     * @param requestOrder
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/orderReceive")
    @HttpLog
    public PddReceiveResponse pddOrderReceive(@RequestBody PddReceiveRequest requestOrder, HttpServletRequest request){
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.PDD001)
                .setSource(ExpCodeEnum.PDD001.getSource().getCode())
                .setTarget(ExpCodeEnum.PDD001.getTarget().getCode())
                .setRequestData(JacksonUtils.toJSon(requestOrder))
                .setInterfaceUrl(request.getRequestURL().toString())
                .setSourceIp(request.getRemoteHost());
        try {
            //简单地校验一下，以前代码连校验都没
            validatePddRequest(requestOrder);
            expInvokeLog.setBusinessCode(requestOrder.getOrderId()).setOrderCode(requestOrder.getOrderId());
            OrderTemp orderTemp = new OrderTemp(expInvokeLog.getRequestData());
            orderTempService.insertAndSendMq(orderTemp, QueueConstants.QueueEnum.PDD_RECEIVE_ORDER_PROCESS,
                    new BasicMessage(orderTemp.getId(), requestOrder.getOrderId()));
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            return PddReceiveResponse.buildSuccess("订单接收成功");
        }catch (BusinessException e){
            return PddReceiveResponse.buildFailure(e.getMessage());
        }catch (Exception e){
            logger.error("拼多多接单异常", e);
            return PddReceiveResponse.buildFailure("系统异常，请稍后再试");
        }
    }

    private void validatePddRequest(@RequestBody PddReceiveRequest requestOrder) {
        if (requestOrder == null){
            throw new BusinessException("报文为空");
        }
        if (StringUtils.isEmpty(requestOrder.getOrderId())){
            throw new BusinessException("企业订单编号为空");
        }
        if (CollectionUtils.isEmpty(requestOrder.getGoodList())){
            throw new BusinessException("订单明细为空");
        }
    }

}
