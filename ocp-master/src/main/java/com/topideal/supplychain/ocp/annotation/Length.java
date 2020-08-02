package com.topideal.supplychain.ocp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>标题: </p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.annotation</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/18 16:49</p>
 *
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Length {

    String message() default "";//错误信息

    int maxLength() default 2147483647;//字符串的最大长度

    int minLength() default 0;//字符串的最小长度
}
