package com.topideal.supplychain.ocp.big.converter;

import java.math.BigDecimal;

/**
 * 标题：大订单转单相关常量
 * 模块：ocp-parent
 * 版权: Copyright © 2019 topideal
 * 公司: 广东卓志供应链科技有限公司武汉分公司
 * 类路径：com.topideal.supplychain.ocp.big.converter
 * 作者：songping
 * 创建日期：2020/1/7 14:35
 *
 * @version 1.0
 */
public interface BigConvertConstant {
    public final static String		RESULT_SUCCESS			= "SUCCESS";
    public final static String		IEFLAG					= "I";
    public final static String		EXPFLAG					= "E";
    public final static String		CURRENCY				= "CNY";
    public final static Long		PACKNO					= 1L;
    public final static BigDecimal  WEIGHT					= new BigDecimal("1000");
    public final static BigDecimal	FCY						= new BigDecimal("100");
    public final static String		EXCSTATUS				= "10";
    public final static String		BUSIMODE			    = "10";
    public final static String		EXP_BUSIMODE			= "20";
    public final static Integer		CUSTOMS_Type				= 1;
    public final static String		CUSTOMSCODE				= "5165";
    public final static String		CIQBCODE				= "000069";
    public final static String		VERSION					= "1";
    public final static Long		DISCOUNT				= 0L;
    public final static String		PAYTYPE					= "M";
    public final static String		PAYSTATUS				= "D";
    public final static String		FROMEPLAT				= "0";
    public final static Integer		ORDERTYPE				= 1;
    public final static String		ORDERSTATUS				= "S";
    public final static String		AMWAY_RESULT_SUCCESS	= "0";
    public final static String		AMWAY_RESULT_FAILURE	= "1";
}
