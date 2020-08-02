package com.topideal.supplychain.ocp.utils;

import com.topideal.supplychain.ocp.constants.SystemConstants;
import java.math.BigDecimal;

/**
 * @ClassName AmountUtils
 * @TODO 金额转换工具
 * @Author zhangzhihao
 * @DATE 2020/7/1 11:22
 * @Version 1.0
 **/
public class AmountUtils {

    /**
     * 分转换为元
     */
    public static BigDecimal convertY(BigDecimal amount) {
        if (amount == null) {
            return BigDecimal.ZERO;
        }
        return amount.divide(SystemConstants.HUNDRED);
    }


    /**
     * 元转换为分
     */
    public static BigDecimal convertF(BigDecimal amount) {
        if (amount == null) {
            return BigDecimal.ZERO;
        }
        return amount.multiply(SystemConstants.HUNDRED);
    }
}
