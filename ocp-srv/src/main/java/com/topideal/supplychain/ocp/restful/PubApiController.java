package com.topideal.supplychain.ocp.restful;

import com.topideal.supplychain.enumeration.SuccessFailureEnum;
import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.exception.BusinessException;
import com.topideal.supplychain.log.HttpLog;
import com.topideal.supplychain.log.HttpLogRequestBody;
import com.topideal.supplychain.log.dto.ExpInvokeLogDto;
import com.topideal.supplychain.ocp.common.ExpCodeEnum;
import com.topideal.supplychain.ocp.common.ResponseCodeEnum;
import com.topideal.supplychain.ocp.common.SourceEnum;
import com.topideal.supplychain.ocp.enums.IeFlagEnum;
import com.topideal.supplychain.ocp.enums.MerchantEnum;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.master.dto.EsdStoreConfig;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.order.model.OrderPub;
import com.topideal.supplychain.ocp.order.service.OrderPubService;
import com.topideal.supplychain.ocp.pub.dto.OrderContent;
import com.topideal.supplychain.ocp.pub.dto.PubReceiptDto;
import com.topideal.supplychain.ocp.pub.dto.PubResponseDto;
import com.topideal.supplychain.ocp.pub.service.PubService;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.TreeMap;

/**
 * <p>标题: 标准平台rest接口类</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.restful</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/18 09:50</p>
 *
 * @version 1.0
 */
@Controller
@RequestMapping("/rest/pub")
public class PubApiController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(PubApiController.class);

    protected static final String BIZ_CONTENT = "biz_content";
    protected static final String SIGN = "sign";
    protected static final String SIGN_TYPE = "sign_type";


    @Autowired
    private StoreService storeService;
    @Autowired
    private PubService pubService;


    /**
     * 获取店铺配值
     * 按照店铺配置解签
     * 校验订单是否存在
     * 校验订单参数信息
     * 保存数据
     *
     * @param request
     * @return
     */
    @PostMapping(value = "/receipt")
    @ResponseBody
    @HttpLog
    public PubResponseDto receipt(@HttpLogRequestBody PubReceiptDto request) {
        PubResponseDto responseDto = new PubResponseDto();
        //获取请求报文
        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.PUB001).
                setSource(SourceEnum.EXERP.getCode()).setTarget(SourceEnum.OCP.getCode())
                .setFlag(SuccessFailureEnum.FAILURE);
        try {
            OrderContent orderReq = JacksonUtils.readValue(request.getBiz_content(), OrderContent.class);
            BusinessAssert.assertNotNull(orderReq, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "订单信息为空");
            expInvokeLog.setBusinessCode(orderReq.getOrderNo()).setOrderCode(orderReq.getOrderNo());
            //获取店铺信息
            Store store = storeService.selectByCode(orderReq.getStoreCode());
            BusinessAssert.assertNotNull(store, ResponseCodeEnum.NO_PERMISSION.getCode(), ResponseCodeEnum.NO_PERMISSION.getSubMsg());
            EsdStoreConfig esdStoreConfig = JacksonUtils.readValue(store.getEsdArguments(), EsdStoreConfig.class);
            BusinessAssert.assertNotNull(esdStoreConfig, ResponseCodeEnum.NO_PERMISSION.getCode(), ResponseCodeEnum.NO_PERMISSION.getSubMsg());

            //按照店铺的参数校验加密信息
            BusinessAssert.assertIsTrue(validateSign(esdStoreConfig.getAppKey(), request), ResponseCodeEnum.AUTH_FAIL.getCode(), ResponseCodeEnum.AUTH_FAIL.getSubMsg());

            //校验订单是否重复，如果存在返回成功
            /*OrderPub orderPub = orderPubService.selectByStoreAndCode(orderReq.getStoreCode(), orderReq.getOrderNo());
            if (orderPub != null) {
                generateResponse(responseDto, ResponseCodeEnum.SUCCESS.getCode(), "订单已存在", 1);
                expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
                return responseDto;
            }*/
            //校验参数信息
            validate(orderReq);
            //保存到数据库
            pubService.saveOrder(orderReq);
            //生成响应报文
            generateResponse(responseDto, ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getSubMsg(), 1);
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
        } catch (DuplicateKeyException e) {
            //唯一索引冲突报错,返回成功
            generateResponse(responseDto, ResponseCodeEnum.SUCCESS.getCode(), "订单已存在", 1);
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            LOGGER.error("标准平台接单业务异常", e);
        }catch (BusinessException e) {
            generateResponse(responseDto, e.getCode(), e.getMessage(), 1);
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            LOGGER.error("标准平台接单业务异常", e);
        } catch (Exception e) {
            generateResponse(responseDto, ResponseCodeEnum.SERVICE_NOT_AVAILABLE.getCode(), ResponseCodeEnum.SERVICE_NOT_AVAILABLE.getSubMsg(), -1);
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            LOGGER.error("系统异常", e);
        }
        return responseDto;
    }


    /**
     * 信息校验
     *
     * @param reqDto
     */
    public void validate(OrderContent reqDto) {
        //检验订单信息
        if (StringUtils.isNotEmpty(reqDto.getLogisticsCode())) {
            BusinessAssert.assertIsFalse(reqDto.getLogisticsCode().length() > 64,
                    ResponseCodeEnum.PARAMETER_ERROR.getCode(), "物流公司编码cp_code长度不能超过64");
        }
        if (StringUtils.isNotEmpty(reqDto.getProductCode())) {
            BusinessAssert.assertIsFalse(reqDto.getProductCode().length() > 64,
                     ResponseCodeEnum.PARAMETER_ERROR.getCode(), "产品代码product_code为空或长度超过64");
        }

        BusinessAssert.assertIsFalse(StringUtils.isEmpty(reqDto.getStoreCode())
                || reqDto.getStoreCode().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "店铺dno不能为空或长度超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getLogisticsNo())
                && reqDto.getLogisticsNo().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "物流运单号mail_no长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getTransportNo())
                && reqDto.getTransportNo().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "国际快递单号tran_no长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getLogisticsId())
                && reqDto.getLogisticsId().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "菜鸟物流单号logistics_id长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getSellerCode())
                && reqDto.getSellerCode().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "淘宝店铺编码seller_id长度不能超过64");
        //BusinessAssert.assertIsFalse(StringUtils.isEmpty(reqDto.getWarehouseCode()) || reqDto.getWarehouseCode().length() > 32, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "海外仓编码storehouse_id为空或长度超过32");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getEsdNo())
                && reqDto.getEsdNo().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "卓志速运订单号esdno长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isEmpty(reqDto.getOrderNo())
                || reqDto.getOrderNo().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "交易订单号trade_no为空或长度超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getPaymentEnterprise())
                && reqDto.getPaymentEnterprise().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "支付企业编号payment_enterprise长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getPaymentEnterpriseName())
                && reqDto.getPaymentEnterpriseName().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "支付企业名称payment_enterprise_name长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getPaymentTransaction())
                && reqDto.getPaymentTransaction().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "支付流水号payment_transaction长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getPaymentRemark())
                && reqDto.getPaymentRemark().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "支付信息备注payment_remark长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getWayBillUrl())
                && reqDto.getWayBillUrl().length() > 255, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "out_way_bill_url长度不能超过255");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getDeclareSchemeSid())
                && reqDto.getDeclareSchemeSid().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "申报方案编码declare_scheme_sid长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getTotalCode())
                && reqDto.getTotalCode().length() > 32, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "运费币制total_code长度不能超过32");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getPremiumCurrency())
                && reqDto.getPremiumCurrency().length() > 32, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "保费币制premium_code长度不能超过32");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getTaxCurrency())
                && reqDto.getTaxCurrency().length() > 32, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "税费币制totai_code长度不能超过32");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getDiscountCurrency())
                && reqDto.getDiscountCurrency().length() > 32, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "优惠减免金额币制discount_code长度不能超过32");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getBillNo())
                && reqDto.getBillNo().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "提单号bill长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getPlatformCode())
                && reqDto.getPlatformCode().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "电商平台编码platform长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getZcode())
                && reqDto.getZcode().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "溯源码值zcode长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getTotalPayCode())
                && reqDto.getTotalPayCode().length() > 32, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "支付币制totai_pay_code长度不能超过32");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getRemark())
                && reqDto.getRemark().length() > 2000, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "备注remark长度不能超过2000");

        //校验发货人信息
        BusinessAssert.assertNotNull(reqDto.getSender(), ResponseCodeEnum.PARAMETER_ERROR.getCode(), "缺少发件人信息");
        BusinessAssert.assertIsFalse(StringUtils.isEmpty(reqDto.getSender().getSenderName())
                || reqDto.getSender().getSenderName().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "发件人姓名name为空或者长度超过64");
        BusinessAssert.assertIsFalse(StringUtils.isEmpty(reqDto.getSender().getSenderPhone())
                && StringUtils.isEmpty(reqDto.getSender().getSenderMobile()), ResponseCodeEnum.PARAMETER_ERROR.getCode(), "发件人手机或电话号码必须填一个");
        BusinessAssert.assertIsFalse(StringUtils.isEmpty(reqDto.getSender().getSenderCountry())
                || reqDto.getSender().getSenderCountry().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "发件人国家country为空或者长度超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getSender().getSenderProvince())
                && reqDto.getSender().getSenderProvince().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "发件人省province长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getSender().getSenderCity())
                && reqDto.getSender().getSenderCity().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "发件人市city长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getSender().getSenderCounty())
                && reqDto.getSender().getSenderCounty().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "发件人区county长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isEmpty(reqDto.getSender().getSenderAddress())
                || reqDto.getSender().getSenderAddress().length() > 255, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "发件人地址address为空或者长度超过255");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getSender().getSenderZipCode())
                && reqDto.getSender().getSenderZipCode().length() > 32, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "发件人邮编zip_code长度不能超过32");

        //校验收货人
        BusinessAssert.assertNotNull(reqDto.getReceiver(), ResponseCodeEnum.PARAMETER_ERROR.getCode(), "收件人信息为空");
        BusinessAssert.assertIsFalse(StringUtils.isEmpty(reqDto.getReceiver().getReceiverName())
                || reqDto.getReceiver().getReceiverName().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "收件人姓名name为空或者长度超过64");
        BusinessAssert.assertIsFalse(StringUtils.isEmpty(reqDto.getReceiver().getReceiverPhone())
                && StringUtils.isEmpty(reqDto.getReceiver().getReceiverMobile()), ResponseCodeEnum.PARAMETER_ERROR.getCode(), "收件人手机或电话号码必须填一个");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getReceiver().getReceiverIdentityNo())
                && reqDto.getReceiver().getReceiverIdentityNo().length() > 255, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "收件人身份证号码identity_no长度不能超过255");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getReceiver().getReceiverIdentityNoFront())
                && reqDto.getReceiver().getReceiverIdentityNoFront().length() > 255, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "收件人身份证正面identity_no_front长度不能超过255");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getReceiver().getReceiverIdentityNoBack())
                && reqDto.getReceiver().getReceiverIdentityNoBack().length() > 255, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "收件人身份证反面identity_no_back长度不能超过255");
        BusinessAssert.assertIsFalse(StringUtils.isEmpty(reqDto.getReceiver().getReceiverCountry())
                || reqDto.getReceiver().getReceiverCountry().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "收件人国country为空或长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isEmpty(reqDto.getReceiver().getReceiverProvince())
                || reqDto.getReceiver().getReceiverProvince().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "收件人省province为空或长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isEmpty(reqDto.getReceiver().getReceiverCity())
                || reqDto.getReceiver().getReceiverCity().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "收件人市city为空或长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getReceiver().getReceiverCounty())
                && reqDto.getReceiver().getReceiverCounty().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "收件人区county长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isEmpty(reqDto.getReceiver().getReceiverAddress())
                || reqDto.getReceiver().getReceiverAddress().length() > 255, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "收件人地址address为空或长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getReceiver().getReceiverZipCode())
                && reqDto.getReceiver().getReceiverZipCode().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "收件人邮编zip_code长度不能超过64");
        if (reqDto.getBuyer() != null) {
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getBuyer().getBuyerName())
                    && reqDto.getBuyer().getBuyerName().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "订购人姓名name长度不能超过64");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getBuyer().getBuyerPhone())
                    && reqDto.getBuyer().getBuyerPhone().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "订购人电话phone长度不能超过64");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getBuyer().getBuyerMobile())
                    && reqDto.getBuyer().getBuyerMobile().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "订购人注册号mobile长度不能大于64");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getBuyer().getBuyerIdType())
                    && reqDto.getBuyer().getBuyerIdType().length() > 255, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "订购人证件类型id_type长度不能超过255");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(reqDto.getBuyer().getBuyerIdentityNo())
                    && reqDto.getBuyer().getBuyerIdentityNo().length() > 255, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "订购人证件号码identity_no长度不能超过255");
        }

        //校验服务类型
        //BusinessAssert.assertNotEmpty(reqDto.getServiceList(), "服务类型为空");
        //BusinessAssert.assertIsFalse(reqDto.getServiceList().stream().anyMatch(serviceType -> StringUtils.isEmpty(serviceType.getServiceType())), "服务类型service_type为空");

        //校验商品信息
        BusinessAssert.assertNotEmpty(reqDto.getItemList(), "商品信息为空");
        for (OrderContent.Good good : reqDto.getItemList()) {
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(good.getItemNo())
                    && good.getItemNo().length() > 256, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "商品序号index长度不能大于256");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(good.getGoodsCode())
                    && good.getGoodsCode().length() > 250, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "商品货号item长度不能大于250");
            BusinessAssert.assertIsFalse(StringUtils.isEmpty(good.getGoodsName())
                    || good.getGoodsName().length() > 256, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "商品名称item_name为空或长度超过256");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(good.getItemCategory())
                    && good.getItemCategory().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "商品行邮税号item_category长度不能大于64");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(good.getHsCode())
                    && good.getHsCode().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "HS海关编码HScode长度不能大于64");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(good.getCustNo())
                    && good.getCustNo().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "商品海关备案号cust_no长度不能大于64");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(good.getCiqNo())
                    && good.getCiqNo().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "国检商品备案号ciq_no长度不能大于64");
            BusinessAssert.assertNotNull(good.getQty(), ResponseCodeEnum.PARAMETER_ERROR.getCode(), "商品数量item_quantity为空");
            BusinessAssert.assertNotNull(good.getUnitPrice(), "商品单价price_declare为空");
            BusinessAssert.assertIsFalse(StringUtils.isEmpty(good.getCurrency())
                    || good.getCurrency().length() > 5, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "单价币制price_code为空或长度大于5");
            BusinessAssert.assertIsFalse(StringUtils.isEmpty(good.getUnit())
                    || good.getUnit().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "计量单位unit为空或长度大于5");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(good.getUnit1())
                    && good.getUnit1().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "第一法定计量单位unit1长度不能大于64");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(good.getUnit2())
                    && good.getUnit2().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "第二法定计量单位unit2长度不能大于64");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(good.getBrand())
                    && good.getBrand().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "商品品牌brand长度不能大于64");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(good.getTax())
                    && good.getTax().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "商品税金item_tax长度不能大于64");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(good.getOriCountry())
                    && good.getOriCountry().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "原产国assem_country长度不能大于64");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(good.getSpec())
                    && good.getSpec().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "规格型号spec长度不能大于64");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(good.getOriArea())
                    && good.getOriArea().length() > 64, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "国检原产地assem_area长度不能大于64");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(good.getBarCode())
                    && good.getBarCode().length() > 255, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "商品条形码bar_code长度不能大于255");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(good.getRemark())
                    && good.getRemark().length() > 255, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "商品备注nots长度不能大于255");
        }

        if(IeFlagEnum.E.getValue().equals(reqDto.getIeFlag())) {
            BusinessAssert.assertNotEmpty(reqDto.getPackageType(),"出口订单包裹类型必填");
        }
    }

    /**
     * 生成响应报文
     *
     * @param responseDto
     * @param code
     * @param message
     * @param status
     */
    private void generateResponse(PubResponseDto responseDto, String code, String message, Integer status) {
        responseDto.setCode(code);
        responseDto.setSubMsg(message);
        responseDto.setStatus(status);
        responseDto.setBody(ResponseCodeEnum.SUCCESS.getCode().equals(code) ? JacksonUtils.toJSon(new PubResponseDto.Body()) : JacksonUtils.toJSon(new PubResponseDto.Body(message)));
    }

    /**
     * 验证签名
     *
     * @param privateKey
     * @param request
     * @return
     */
    public boolean validateSign(String privateKey, PubReceiptDto request) {
        String sign = request.getSign();
        String val = linkString(paramFilter(request));
        String newSign = sign(val, privateKey);
        return StringUtils.isNotEmpty(sign) && newSign.equals(sign);
    }

    /**
     * 过滤
     *
     * @param request
     * @return
     */
    private TreeMap<String, String> paramFilter(PubReceiptDto request) {
        TreeMap<String, String> result = new TreeMap<>();
        Class<? extends PubReceiptDto> clazz = request.getClass();
        for (Field declaredField : clazz.getDeclaredFields()) {
            declaredField.setAccessible(Boolean.TRUE);
            try {
                Object value = declaredField.get(request);
                //如果不是string的跳过
                if (!(declaredField.get(request) instanceof String)) {
                    continue;
                }
                String val = (String) value;
                if (StringUtils.isEmpty(val) || SIGN.equalsIgnoreCase(declaredField.getName()) || SIGN_TYPE.equalsIgnoreCase(declaredField.getName())
                        || BIZ_CONTENT.equalsIgnoreCase(declaredField.getName())) {
                    continue;
                }
                result.put(declaredField.getName(), val);
            } catch (IllegalAccessException e) {
                LOGGER.error("解签异常", e);
            }
        }
        return result;
    }

    /**
     * 参数拼接
     *
     * @param params
     * @return
     */
    private String linkString(TreeMap<String, String> params) {
        StringBuilder str = new StringBuilder();
        params.forEach((key, value) -> str.append(key).append("=").append(value).append("&"));
        return str.deleteCharAt(str.length() - 1).toString();
    }

    /**
     * 加签
     *
     * @param val
     * @param privateKey
     * @return
     */
    private String sign(String val, String privateKey) {
        String builder = val + privateKey;
        //md5
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MD5Encoder.encode(md.digest(builder.getBytes())).toUpperCase();
        } catch (Exception e) {
            LOGGER.error("标准平台加密失败");
            return "";
        }
    }

    public static void main(String[] args) {
        String s = "{\"dno\":\"DNKHSC\",\"cp_code\":\"SF\",\"declare_scheme_sid\":\"\",\"storehouse_id\":\"HK01\",\"trade_no\":\"15862289622240135271\",\"product_code\":\"GZ-SF\",\"sender\":{\"country\":\"中国\",\"address\":\"北京市朝阳区东四环中路62号楼远洋国际D座2005\",\"name\":\"甜觅世界\",\"mobile\":\"18511308455\"},\"receiver\":{\"country\":\"中国\",\"address\":\"上饶市信州区上饶中学西门\",\"province\":\"江西省\",\"city\":\"上饶市\",\"name\":\"杨敏雪\",\"mobile\":\"18379380188\",\"county\":\"信州区\",\"identity_no\":\"361121200110288321\"},\"itemList\":[{\"price_code\":\"CNY\",\"item\":\"8633043\",\"unit1\":\"1\",\"item_name\":\"Adidas white & black samba og trainers youth\",\"qty1\":\"1\",\"spec\":\"1件\",\"item_net_weight\":450,\"item_weight\":450,\"unit\":\"件\",\"assem_country\":\"越南\",\"price_declare\":120,\"bar_code\":\"SWEET8633043\",\"brand\":\"Adidas\",\"item_quantity\":1}]}";
        OrderContent orderContent = JacksonUtils.readValue(s,OrderContent.class);
        System.out.println("");
    }


}
