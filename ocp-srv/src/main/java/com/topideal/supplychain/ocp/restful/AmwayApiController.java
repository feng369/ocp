package com.topideal.supplychain.ocp.restful;

import com.google.common.base.Throwables;
import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.amway.dto.*;
import com.topideal.supplychain.ocp.amway.service.AmwayService;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.enums.AmwayOrderEnum;
import com.topideal.supplychain.ocp.service.SystemConfigService;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.List;

@Controller
@RequestMapping("/rest/amway")
public class AmwayApiController {
    protected static final Logger logger = LoggerFactory.getLogger(AmwayApiController.class);

    @Autowired
    private SystemConfigService configService;
    @Autowired
    private AmwayService amwayService;

    /**
     * 安利接单入口
     *
     * @param requestBody
     * @return
     */
    @PostMapping(value = "/ocpjson/postamwayorder")
    @ResponseBody
    @HttpLog
    public OCPAmwayResponsePojo postAmwayOrder(@RequestBody String requestBody, HttpServletRequest request) {
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.AMWAY001)
                .setSource(ExpCodeEnum.AMWAY001.getSource().getCode())
                .setTarget(ExpCodeEnum.AMWAY001.getTarget().getCode())
                .setRequestData(JacksonUtils.toJSon(requestBody))
                .setInterfaceUrl(request.getRequestURL().toString())
                .setSourceIp(request.getRemoteHost());
        OCPAmwayOrderRequestPojo requestPojo = JacksonUtils.readValue(requestBody, OCPAmwayOrderRequestPojo.class);
        OCPAmwayResponsePojo responseDto = new OCPAmwayResponsePojo();
        try { //如果下发的报文签名与SHA256计算得出的报文签名不一致，则抛出异常
            checkSign(requestPojo);
            //json验证定义
            checkJson(requestPojo);
            //数据校验,检验报文的发送方和接收方是否和前台配置的参数一致
            dataCheck(requestPojo);
            //保存订单数据
            amwayService.saveOrder(requestPojo);
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS).setBusinessCode(requestPojo.getMessageDetail().getOrder().get(0).getOrderHeader().getOrderNumber()).setOrderCode(requestPojo.getMessageDetail().getOrder().get(0).getOrderHeader().getOrderNumber());
            generateResponse(requestPojo, responseDto,
                    AmwayOrderEnum.SUCCESS.getValue(), null);

        } catch (BusinessException e) {
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            generateResponse(requestPojo, responseDto, AmwayOrderEnum.FAILTURE.getValue(), e.getMessage());
            logger.error("安利订单接单异常：" + e.getMessage(), e);
        } catch (Exception e) {
            generateResponse(requestPojo, responseDto, AmwayOrderEnum.FAILTURE.getValue(), "系统异常，请稍后再试！");
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            logger.error("系统异常", e);

        }
        return responseDto;


    }

    /**
     检验报文的发送方和接收方是否一致
     * @param requestPojo
     */
    private void dataCheck(OCPAmwayOrderRequestPojo requestPojo) {
        String nullParams = null;
        String message = null;
        String LocationSender = configService.getAmWayMessageName();
        String sender = requestPojo.getMessageHeader().getSender();
        if (!StringUtils.equals(sender, LocationSender)) {
            nullParams = StringUtils.join(nullParams, "sender:报文发送方");
        }
        String LocationReceiver = configService.getAmWayZZMessageName();
        String receiver = requestPojo.getMessageHeader().getReceiver();
        if (!StringUtils.equals(receiver, LocationReceiver)) {
            nullParams = StringUtils.join(nullParams, "receiver:报文接收方");
        }
        if (StringUtils.isNotBlank(nullParams)) {
            message = StringUtils.join(message, "参数[" + nullParams + "]不正确");
        }
        if (StringUtils.isNotBlank(message)) {
            throw new BusinessException(message);
        }
    }

    private void generateResponse(OCPAmwayOrderRequestPojo requestPojo, OCPAmwayResponsePojo responsePojo, String resultcode, String errorMsg) {
        responsePojo.setMessageID(requestPojo.getMessageHeader().getMessageID());
        responsePojo.setResultCode(resultcode);
        //				根据SHA256(messageID + resultCode + 系统选项的KEY)生成报文签名
        try {
            responsePojo.setSign(SHA256(requestPojo.getMessageHeader().getMessageID() + resultcode + configService.getAmwayAppSecret()));
            if (StringUtils.equals(AmwayOrderEnum.FAILTURE.getValue(), resultcode)) {
                responsePojo.setErrorMessage(errorMsg);
            }
        } catch (Exception e) {
            logger.error("系统异常", e);
        }

    }


    private void checkSign(OCPAmwayOrderRequestPojo requestPojo) throws Exception {
        String key = configService.getAmwayAppSecret();
        String SHA256Sign = SHA256(requestPojo.getMessageHeader().getMessageID() + requestPojo.getMessageHeader().getSender() + key);
        if (!SHA256Sign.equals(requestPojo.getMessageHeader().getSign())) {
            throw new BusinessException("报文签名不正确");
        }
    }

    private String SHA256(String decript) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(decript.getBytes());
        byte messageDigest[] = digest.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < messageDigest.length; i++) {
            String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        return hexString.toString();
    }

    private void checkJson(OCPAmwayOrderRequestPojo requestPojo) {
        MessageHeader messageHeader = requestPojo.getMessageHeader();
        MessageDetail messageDetail = requestPojo.getMessageDetail();
        List<Order> orderList = messageDetail.getOrder();
        for (Order order : orderList) {
            OrderHeader orderHeader = order.getOrderHeader();
            List<OrderLine> orderLineList = orderHeader.getOrderLine();
            PaymentHeader paymentHeader = order.getPaymentHeader();
            List<PaymentLine> paymentLineList = paymentHeader.getPaymentLine();
            CrossBorder crossBorder = order.getCrossBorder();
            String messageID = messageHeader.getMessageID();
            String sender = messageHeader.getSender();
            String sign = messageHeader.getSign();

            if (StringUtils.isBlank(messageID)) {
                throw new BusinessException("messageID不能为空");
            }
            if (StringUtils.isBlank(sender)) {
                throw new BusinessException("sender不能为空");
            }
            if (StringUtils.isBlank(messageHeader.getReceiver())) {
                throw new BusinessException("receiver不能为空");
            }
            if (StringUtils.isBlank(messageHeader.getMessageType())) {
                throw new BusinessException("messageType不能为空");
            }
            if (StringUtils.isBlank(messageHeader.getSendTime())) {
                throw new BusinessException("sendTime不能为空");
            }
            if (StringUtils.isBlank(messageHeader.getVersion())) {
                throw new BusinessException("version不能为空");
            }
            if (StringUtils.isBlank(sign)) {
                throw new BusinessException("sign不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getWarehouseId())) {
                throw new BusinessException("warehouseId不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getBusinessType())) {
                throw new BusinessException("businessType不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getVersionId())) {
                throw new BusinessException("versionId不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getIsGroupOrderFlag())) {
                throw new BusinessException("isGroupOrderFlag不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getOrderNumber())) {
                throw new BusinessException("orderNumber不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getOrderType())) {
                throw new BusinessException("orderType不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getDistributorNumber())) {
                throw new BusinessException("distributorNumber不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getDistributorName())) {
                throw new BusinessException("distributorName不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getShipToDst())) {
                throw new BusinessException("shipToDst不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getConsigneeName())) {
                throw new BusinessException("consigneeName不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getShipToAddress1())) {
                throw new BusinessException("shipToAddress1不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getShipToAddress2())) {
                throw new BusinessException("shipToAddress2不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getShipToAddress3())) {
                throw new BusinessException("shipToAddress3不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getShipToAddress4())) {
                throw new BusinessException("shipToAddress4不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getSaleDate())) {
                throw new BusinessException("saleDate不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getDeliveryFee())) {
                throw new BusinessException("deliveryFee不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getDeliveryInsuranceFee())) {
                throw new BusinessException("deliveryInsuranceFee不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getOrderStatus())) {
                throw new BusinessException("orderStatus不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getOrderBv())) {
                throw new BusinessException("orderBv不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getOrderPv())) {
                throw new BusinessException("orderPv不能为空");
            }
            if (StringUtils.isBlank(orderHeader.getOrderOriginalPrice())) {
                throw new BusinessException("orderOriginalPrice不能为空");
            }
            for (OrderLine orderLine : orderLineList) {
                if (StringUtils.isBlank(orderLine.getItemSku())) {
                    throw new BusinessException("itemSku不能为空");
                }
                if (StringUtils.isBlank(orderLine.getUnitDp())) {
                    throw new BusinessException("unitDp不能为空");
                }
                if (StringUtils.isBlank(orderLine.getOrderingItemName())) {
                    throw new BusinessException("orderingItemName不能为空");
                }
                if (StringUtils.isBlank(orderLine.getOrderingItemNumber())) {
                    throw new BusinessException("orderingItemNumber不能为空");
                }
                if (StringUtils.isBlank(orderLine.getIsReturn())) {
                    throw new BusinessException("isReturn不能为空");
                }
                if (StringUtils.isBlank(orderLine.getOrderQty())) {
                    throw new BusinessException("orderQty不能为空");
                }
            }

            if (StringUtils.isBlank(paymentHeader.getPaymentHeaderAmount())) {
                throw new BusinessException("paymentHeaderAmount不能为空");
            }
            if (StringUtils.isBlank(paymentHeader.getPaymentDateTime())) {
                throw new BusinessException("paymentDateTime不能为空");
            }
            for (PaymentLine paymentLine : paymentLineList) {
                if (StringUtils.isBlank(paymentLine.getPaymentMethod())) {
                    throw new BusinessException("paymentMethod不能为空");
                }
                if (StringUtils.isBlank(paymentLine.getPaymentLineAmount())) {
                    throw new BusinessException("paymentLineAmount不能为空");
                }
                if (StringUtils.isBlank(paymentLine.getPaymentMethodName())) {
                    throw new BusinessException("paymentMethodName不能为空");
                }
            }
            if (StringUtils.isBlank(crossBorder.getImportMode())) {
                throw new BusinessException("importMode不能为空");
            }
            if (StringUtils.isBlank(crossBorder.getCustoms())) {
                throw new BusinessException("customs不能为空");
            }
            if (StringUtils.isBlank(crossBorder.getTaxFee())) {
                throw new BusinessException("taxFee不能为空");
            }
            if (StringUtils.isBlank(crossBorder.getCustomerID())) {
                throw new BusinessException("customerID不能为空");
            }
        }
    }


}
