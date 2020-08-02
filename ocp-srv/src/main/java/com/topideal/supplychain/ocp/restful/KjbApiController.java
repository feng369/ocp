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
import com.topideal.supplychain.ocp.enums.MerchantEnum;
import com.topideal.supplychain.ocp.enums.PlatformEnum;
import com.topideal.supplychain.ocp.kjb.dto.KjbReceiveDto;
import com.topideal.supplychain.ocp.kjb.dto.KjbReceiveReqDto;
import com.topideal.supplychain.ocp.kjb.dto.KjbReceiveRespDto;
import com.topideal.supplychain.ocp.kjb.service.KjbService;
import com.topideal.supplychain.ocp.master.dto.EsdStoreConfig;
import com.topideal.supplychain.ocp.master.model.Store;
import com.topideal.supplychain.ocp.master.service.StoreService;
import com.topideal.supplychain.ocp.order.model.OrderKjb;
import com.topideal.supplychain.ocp.order.service.OrderKjbService;
import com.topideal.supplychain.util.JacksonUtils;
import com.topideal.supplychain.web.log.model.ExpInvokeLog;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.*;

/**
 * @description: 跨境宝rest接口
 * @author: syq
 * @create: 2019-12-18 16:58
 **/
@Controller
@RequestMapping("/rest/kjb")
public class KjbApiController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(KjbApiController.class);


    @Autowired
    private StoreService storeService;
    @Autowired
    private OrderKjbService orderKjbService;
    @Autowired
    private KjbService kjbService;

    /**
     * 跨境宝接单接口
     * 1.根据平台编码、商家编码、店铺编码、海外仓编码查询店铺信息，若为空则接口失败
     * 2.校验签名
     * 3.校验是否重复接单
     * 4.校验订单信息有效性
     * 5.保存数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/createOrder")
    @HttpLog
    public KjbReceiveRespDto createOrder(@HttpLogRequestBody KjbReceiveDto dto) {

        ExpInvokeLog expInvokeLog = ExpInvokeLogDto.get().setInterfaceEnum(ExpCodeEnum.KJB001)
                                    .setSource(SourceEnum.KJB.getCode())
                                    .setTarget(SourceEnum.OCP.getCode());

        try {
            BusinessAssert.assertNotNull(dto, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "报文为空");
            KjbReceiveReqDto kjbReceiveReqDto = JacksonUtils.readValue(dto.getBiz_content(), KjbReceiveReqDto.class);
            BusinessAssert.assertNotNull(kjbReceiveReqDto, ResponseCodeEnum.PARAMETER_ERROR.getCode(), "订单信息为空");
            expInvokeLog.setBusinessCode(kjbReceiveReqDto.getTradeNo()).setOrderCode(kjbReceiveReqDto.getTradeNo());

            //根据平台编码、商家编码、店铺编码、海外仓编码查询店铺信息，若为空则接口失败
            Store store = storeService.selectEnableByCode(kjbReceiveReqDto.getStoreCode());
            BusinessAssert.assertNotNull(store, ResponseCodeEnum.NO_PERMISSION.getCode(), ResponseCodeEnum.NO_PERMISSION.getSubMsg());
            EsdStoreConfig esdStoreConfig = JacksonUtils.readValue(store.getEsdArguments(), EsdStoreConfig.class);
            BusinessAssert.assertNotNull(esdStoreConfig, ResponseCodeEnum.NO_PERMISSION.getCode(), ResponseCodeEnum.NO_PERMISSION.getSubMsg());

            //校验签名
            BusinessAssert.assertIsTrue(validateSign(esdStoreConfig.getAppKey(), dto), ResponseCodeEnum.AUTH_FAIL.getCode(), ResponseCodeEnum.AUTH_FAIL.getSubMsg());

            //校验是否重复接单
            OrderKjb exist = orderKjbService.selectExist(kjbReceiveReqDto.getTradeNo(), kjbReceiveReqDto.getStoreCode(), dto.getTimestamp());
            if (exist != null) {
                expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
                return buildResponse(ResponseCodeEnum.SUCCESS.getCode(), "订单已存在", 1, "");
            }

            //校验订单信息有效性
            validateDate(kjbReceiveReqDto);

            //保存数据
            kjbService.receiveOrder(kjbReceiveReqDto, dto.getTimestamp());
            //接口返回成功
            expInvokeLog.setFlag(SuccessFailureEnum.SUCCESS);
            return buildResponse(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getSubMsg(), 1, "");
        } catch (BusinessException businessException) {
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            LOGGER.error("跨境宝接单业务异常", businessException);
            return buildResponse(StringUtils.isNotEmpty(businessException.getCode()) ? businessException.getCode() : ResponseCodeEnum.PARAMETER_ERROR.getCode(),
                    businessException.getMessage(), -1, businessException.getMessage());
        } catch (Exception e) {
            expInvokeLog.setFlag(SuccessFailureEnum.FAILURE);
            LOGGER.error("系统异常", e);
            return buildResponse(ResponseCodeEnum.SERVICE_NOT_AVAILABLE.getCode(), ResponseCodeEnum.SERVICE_NOT_AVAILABLE.getSubMsg(), -1, e.getMessage());
        }
    }

    /**
     * 校验接单报文数据有效性
     * @param dto
     */
    private void validateDate(KjbReceiveReqDto dto) {
        BusinessAssert.assertNotEmpty(dto.getStoreCode(), "店铺编码dno必填");
        BusinessAssert.assertIsFalse(dto.getStoreCode().length() > 64, "店铺编码dno长度不能超过64");
        if (StringUtils.isNotEmpty(dto.getLogisticsCode())) {
            BusinessAssert.assertIsFalse(dto.getLogisticsCode().length() > 64, "物流公司编码cp_code长度不能超过64");
        }
        if (StringUtils.isNotEmpty(dto.getLogisticsNo())) {
            BusinessAssert.assertIsFalse(dto.getLogisticsNo().length() > 64, "物流运单号mail_no长度不能超过64");
        }
        if (StringUtils.isNotEmpty(dto.getInterLogisticsNo())) {
            BusinessAssert.assertIsFalse(dto.getInterLogisticsNo().length() > 64, "国际快递单号tran_no长度不能超过64");
        }
        if (StringUtils.isNotEmpty(dto.getCainiaoLogisticsNo())) {
            BusinessAssert.assertIsFalse(dto.getCainiaoLogisticsNo().length() > 64, "菜鸟物流单号logistics_id长度不能超过64");
        }
        if (StringUtils.isNotEmpty(dto.getTbStoreCode())) {
            BusinessAssert.assertIsFalse(dto.getTbStoreCode().length() > 64, "淘宝店铺编码seller_id长度不能超过64");
        }
        BusinessAssert.assertNotNull(dto.getOrderCreateTime(), "订单创建时间order_create_time必填");
        BusinessAssert.assertNotEmpty(dto.getOverseaHouseCode(), "海外仓编码storehouse_id必填");
        BusinessAssert.assertIsFalse(dto.getOverseaHouseCode().length() > 32, "海外仓编码storehouse_id长度不能超过32");
        if (StringUtils.isNotEmpty(dto.getEsdNo())) {
            BusinessAssert.assertIsFalse(dto.getEsdNo().length() > 64, "卓志速运订单号esdno长度不能超过64");
        }
        BusinessAssert.assertNotEmpty(dto.getTradeNo(), "交易订单号trade_no必填");
        BusinessAssert.assertIsFalse(dto.getTradeNo().length() > 64, "交易订单号trade_no长度不能超过64");
        if (StringUtils.isNotEmpty(dto.getPayMerchantCode())) {
            BusinessAssert.assertIsFalse(dto.getPayMerchantCode().length() > 64, "支付企业编号payment_enterprise长度不能超过64");
        }
        if (StringUtils.isNotEmpty(dto.getPayMerchantName())) {
            BusinessAssert.assertIsFalse(dto.getPayMerchantName().length() > 64, "支付企业名称payment_enterprise_name长度不能超过64");
        }
        if (StringUtils.isNotEmpty(dto.getPayNo())) {
            BusinessAssert.assertIsFalse(dto.getPayNo().length() > 64, "支付流水号payment_transaction长度不能超过64");
        }
        if (StringUtils.isNotEmpty(dto.getPayRemark())) {
            BusinessAssert.assertIsFalse(dto.getPayRemark().length() > 64, "支付信息备注payment_remark长度不能超过64");
        }
        if (StringUtils.isNotEmpty(dto.getWaybillUrl())) {
            BusinessAssert.assertIsFalse(dto.getWaybillUrl().length() > 255, "面单url out_way_bill_url长度不能超过255");
        }
        if (StringUtils.isNotEmpty(dto.getDeclareSchemeSid())) {
            BusinessAssert.assertIsFalse(dto.getDeclareSchemeSid().length() > 32, "申报方案编码declare_scheme_sid长度不能超过32");
        }
        if (StringUtils.isNotEmpty(dto.getProductCode())) {
            BusinessAssert.assertIsFalse(dto.getProductCode().length() > 32, "产品代码Product_code长度不能超过32");
        }
        if (dto.getFreight() != null) {
            BusinessAssert.assertNotEmpty(dto.getFreightCurrency(), "运费币制total_code必填");
        }
        if (dto.getPremium() != null) {
            BusinessAssert.assertNotEmpty(dto.getPremiumCurrency(), "保费币制premium_code必填");
            BusinessAssert.assertIsFalse(dto.getPremiumCurrency().length() > 5, "保费币制premium_code长度不能超过5");
        }
        if (dto.getTax() != null) {
            BusinessAssert.assertNotEmpty(dto.getTaxCurrency(), "税费币制totai_code必填");
            BusinessAssert.assertIsFalse(dto.getTaxCurrency().length() > 5, "税费币制totai_code长度不能超过5");
        }
        if (dto.getDiscount() != null) {
            BusinessAssert.assertNotEmpty(dto.getDiscountCurrency(), "优惠减免金额币制discount_code必填");
            BusinessAssert.assertIsFalse(dto.getDiscountCurrency().length() > 5, "优惠减免金额币制discount_code长度不能超过5");
        }
        if (StringUtils.isNotEmpty(dto.getBillNo())) {
            BusinessAssert.assertIsFalse(dto.getBillNo().length() > 64, "提单号bill长度不能超过64");
        }
        BusinessAssert.assertNotEmpty(dto.getPlatformCode(), "电商平台编码platform必填");
        BusinessAssert.assertIsFalse(dto.getPlatformCode().length() > 64, "电商平台编码platform长度不能超过64");
        if (StringUtils.isNotEmpty(dto.getZcode())) {
            BusinessAssert.assertIsFalse(dto.getZcode().length() > 64, "溯源码值zcode长度不能超过64");
        }
        BusinessAssert.assertNotNull(dto.getIsTraceSource(), "是否推溯源is_trace_source必填");
        if (StringUtils.isNotEmpty(dto.getActualPaidCurrency())) {
            BusinessAssert.assertIsFalse(dto.getActualPaidCurrency().length() > 5, "支付币制totai_pay_code长度不能超过5");
        }
        BusinessAssert.assertNotNull(dto.getIsTran(), "换单标志is_tran必填");
        if (StringUtils.isNotEmpty(dto.getRemark())) {
            BusinessAssert.assertIsFalse(dto.getRemark().length() > 2000, "备注remark长度不能超过2000");
        }
        BusinessAssert.assertNotNull(dto.getSender(), "缺少发件人信息");

        BusinessAssert.assertNotEmpty(dto.getSender().getName(), "发件人姓名name必填");
        BusinessAssert.assertIsFalse(dto.getSender().getName().length() > 64, "发件人姓名name长度不能超过64");

        BusinessAssert.assertIsFalse(!StringUtils.isNotEmpty(dto.getSender().getPhone()) && !StringUtils.isNotEmpty(dto.getSender().getMobile()), "发件人手机或电话号码必须填一个");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getSender().getPhone()) && dto.getSender().getPhone().length() > 64, "发件人电话phone长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getSender().getMobile()) && dto.getSender().getMobile().length() > 64, "发件人手机mobile长度不能超过64");
        BusinessAssert.assertNotEmpty(dto.getSender().getCountry(), "发件人国country必填");
        BusinessAssert.assertIsFalse(dto.getSender().getCountry().length() > 64, "发件人国country长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getSender().getProvince()) && dto.getSender().getProvince().length() > 64, "发件人手机mobile长度不能超过64");
        BusinessAssert.assertNotEmpty(dto.getSender().getCity(), "发件人市city必填");
        BusinessAssert.assertIsFalse(dto.getSender().getCity().length() > 64, "发件人市city长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getSender().getCounty()) && dto.getSender().getCounty().length() > 64, "发件人手机mobile长度不能超过64");
        BusinessAssert.assertNotEmpty(dto.getSender().getAddress(), "发件人地址address必填");
        BusinessAssert.assertIsFalse(dto.getSender().getAddress().length() > 255, "发件人地址address长度不能超过255");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getSender().getZipCode()) && dto.getSender().getZipCode().length() > 64, "发件人邮编zip_code长度不能超过64");

        BusinessAssert.assertNotNull(dto.getReceiver(), "缺少收件人信息");

        BusinessAssert.assertNotEmpty(dto.getReceiver().getName(), "收件人姓名name必填");
        BusinessAssert.assertIsFalse(dto.getReceiver().getName().length() > 64, "收件人姓名name长度不能超过64");
        BusinessAssert.assertIsFalse(!StringUtils.isNotEmpty(dto.getReceiver().getPhone()) && !StringUtils.isNotEmpty(dto.getReceiver().getMobile()), "收件人手机或电话号码必须填一个");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getReceiver().getPhone()) && dto.getReceiver().getPhone().length() > 64, "收件人电话phone长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getReceiver().getMobile()) && dto.getReceiver().getMobile().length() > 64, "收件人手机mobile长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getReceiver().getIdentityNo()) && dto.getReceiver().getIdentityNo().length() > 255, "收件人身份证号码identity_no长度不能超过255");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getReceiver().getIdentityNoFront()) && dto.getReceiver().getIdentityNoFront().length() > 255, "收件人身份证正面url identity_no_front长度不能超过255");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getReceiver().getIdentityNoBack()) && dto.getReceiver().getIdentityNoBack().length() > 255, "收件人身份证反面url identity_no_back长度不能超过255");
        BusinessAssert.assertNotEmpty(dto.getReceiver().getCountry(), "收件人国country必填");
        BusinessAssert.assertIsFalse(dto.getReceiver().getCountry().length() > 64, "收件人国country长度不能超过64");
        BusinessAssert.assertNotEmpty(dto.getReceiver().getProvince(), "收件人省province必填");
        BusinessAssert.assertIsFalse(dto.getReceiver().getProvince().length() > 64, "收件人省province长度不能超过64");
        BusinessAssert.assertNotEmpty(dto.getReceiver().getCity(), "收件人市city必填");
        BusinessAssert.assertIsFalse(dto.getReceiver().getCity().length() > 64, "收件人市city长度不能超过64");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getReceiver().getCounty()) && dto.getReceiver().getCounty().length() > 64, "收件人区county长度不能超过64 identity_no_front长度不能超过255");
        BusinessAssert.assertNotEmpty(dto.getReceiver().getAddress(), "收件人地址address必填");
        BusinessAssert.assertIsFalse(dto.getReceiver().getAddress().length() > 255, "收件人地址address长度不能超过255");
        BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getReceiver().getZipCode()) && dto.getReceiver().getZipCode().length() > 64, "收件人邮编zip_code长度不能超过64");

        if (dto.getBuyer() != null) {
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getBuyer().getName()) && dto.getBuyer().getName().length() > 64, "订购人姓名name长度不能超过64");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getBuyer().getPhone()) && dto.getBuyer().getPhone().length() > 64, "订购人电话phone长度不能超过64");
            if (StringUtils.isNotEmpty(dto.getBuyer().getMobile())) {
                BusinessAssert.assertIsFalse(dto.getBuyer().getMobile().length() > 64, "订购人注册号mobile长度不能大于64");
            }
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getBuyer().getIdType()) && dto.getBuyer().getIdType().length() > 255, "订购人证件类型id_type长度不能超过255");
            BusinessAssert.assertIsFalse(StringUtils.isNotEmpty(dto.getBuyer().getIdentityNo()) && dto.getBuyer().getIdentityNo().length() > 255, "订购人证件号码identity_no长度不能超过255");
        }

        BusinessAssert.assertNotEmpty(dto.getSerciveList(), "服务类型service_type必填");

        for (KjbReceiveReqDto.Good item : dto.getItemList()) {
            BusinessAssert.assertNotNull(item.getIsPresente(), "是否商品备案is_presente必填");
            if (StringUtils.isNotEmpty(item.getIndex())) {
                BusinessAssert.assertIsFalse(item.getIndex().length() > 256, "商品序号index长度不能大于256");
            }
            if (StringUtils.isNotEmpty(item.getItem())) {
                BusinessAssert.assertIsFalse(item.getItem().length() > 256, "商品货号item长度不能大于256");
            }
            BusinessAssert.assertNotEmpty(item.getGoodsName(), "商品名称item_name必填");
            BusinessAssert.assertIsFalse(item.getGoodsName().length() > 256, "商品名称item_name长度不能超过256");
            if (StringUtils.isNotEmpty(item.getCategoryNo())) {
                BusinessAssert.assertIsFalse(item.getCategoryNo().length() > 256, "商品行邮税号item_category长度不能大于64");
            }
            if (StringUtils.isNotEmpty(item.getHscode())) {
                BusinessAssert.assertIsFalse(item.getHscode().length() > 64, "HS海关编码HScode长度不能大于64");
            }
            if (StringUtils.isNotEmpty(item.getCustNo())) {
                BusinessAssert.assertIsFalse(item.getCustNo().length() > 64, "商品海关备案号cust_no长度不能大于64");
            }
            if (StringUtils.isNotEmpty(item.getCiqNo())) {
                BusinessAssert.assertIsFalse(item.getCiqNo().length() > 64, "国检商品备案号ciq_no长度不能大于64");

            }
            BusinessAssert.assertNotNull(item.getQty(), "商品数量item_quantity必填");
            BusinessAssert.assertNotNull(item.getPrice(), "商品单价price_declare必填");
            BusinessAssert.assertNotEmpty(item.getPriceCurrency(), "单价币制price_code必填");
            BusinessAssert.assertIsFalse(item.getPriceCurrency().length() > 5, "单价币制的长度不能超过5");
            BusinessAssert.assertNotEmpty(item.getUnit(), "计量单位unit必填");
            BusinessAssert.assertIsFalse(item.getUnit().length() > 64, "计量单位unit长度不能超过64");
            if (StringUtils.isNotEmpty(item.getUnit1())) {
                BusinessAssert.assertIsFalse(item.getUnit1().length() > 64, "第一法定计量单位qty1长度不能大于64");
            }
            if (StringUtils.isNotEmpty(item.getUnit2())) {
                BusinessAssert.assertIsFalse(item.getUnit2().length() > 64, "第二法定计量单位qty1长度不能大于64");
            }
            if (StringUtils.isNotEmpty(item.getBrand())) {
                BusinessAssert.assertIsFalse(item.getBrand().length() > 64, "商品品牌brand长度不能大于64");
            }
            if (StringUtils.isNotEmpty(item.getTax())) {
                BusinessAssert.assertIsFalse(item.getTax().length() > 64, "商品税金item_tax长度不能大于64");
            }
            if (StringUtils.isNotEmpty(item.getOriginCountry())) {
                BusinessAssert.assertIsFalse(item.getOriginCountry().length() > 64, "原产国assem_country长度不能大于64");
            }
            if (StringUtils.isNotEmpty(item.getSpec())) {
                BusinessAssert.assertIsFalse(item.getSpec().length() > 64, "规格型号spec长度不能大于64");
            }
            if (StringUtils.isNotEmpty(item.getCiqOriginArea())) {
                BusinessAssert.assertIsFalse(item.getCiqOriginArea().length() > 64, "国检原产地assem_area长度不能大于64");
            }
            if (StringUtils.isNotEmpty(item.getBarCode())) {
                BusinessAssert.assertIsFalse(item.getBarCode().length() > 255, "商品条形码bar_code长度不能大于255");
            }
        }
    }

    /**
     * 组建接口回执信息
     * @return
     */
    private KjbReceiveRespDto buildResponse(String code, String subMsg, Integer status, String errorMsg) {
        KjbReceiveRespDto response = new KjbReceiveRespDto();
        response.setStatus(status);
        response.setCode(code);
        response.setSubMsg(subMsg);
//        KjbReceiveRespDto.Body body = new KjbReceiveRespDto.Body();
//        body.setErrMsg(errorMsg);
        response.setBody(ResponseCodeEnum.SUCCESS.getCode().equals(code) ?
                            JacksonUtils.toJSon(new KjbReceiveRespDto.Body()) :
                            JacksonUtils.toJSon(new KjbReceiveRespDto.Body(errorMsg)));
        return response;
    }

    /**
     * 校验签名
     * @return
     */
    private Boolean validateSign(String privateKey, KjbReceiveDto dto){
        String sign = dto.getSign();
        String text = createLinkString(paraFilter(dto));
        String newSign = sign(text, privateKey);
        if (sign != null && sign.equals(newSign)) {
            return true;
        }
        return false;
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @return 去掉空值与签名参数后的新签名参数组
     */
    private Map<String, String> paraFilter(KjbReceiveDto dto) {
        Map<String, String> result = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(dto.getApp_id())) {
            result.put("app_id", dto.getApp_id());
        }
        if (StringUtils.isNotEmpty(dto.getApp_key())) {
            result.put("app_key", dto.getApp_key());
        }
        if (StringUtils.isNotEmpty(dto.getCharset())) {
            result.put("charset", dto.getCharset());
        }
        if (StringUtils.isNotEmpty(dto.getFormat())) {
            result.put("format", dto.getFormat());
        }
        if (StringUtils.isNotEmpty(dto.getMethod())) {
            result.put("method", dto.getMethod());
        }
        if (StringUtils.isNotEmpty(dto.getTimestamp())) {
            result.put("timestamp", dto.getTimestamp());
        }
        if (StringUtils.isNotEmpty(dto.getVersion())) {
            result.put("version", dto.getVersion());
        }
        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    private String createLinkString(Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
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

    /**
     * 签名字符串
     *
     * @param text          需要签名的字符串
     * @param key           密钥
     * @return 签名结果
     */
    private String sign(String text, String key) {
        text = text + key;
        //md5
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MD5Encoder.encode(md.digest(text.getBytes())).toUpperCase();
        } catch (Exception e) {
            LOGGER.error("跨境宝订单加密失败");
            return "";
        }
    }

}
