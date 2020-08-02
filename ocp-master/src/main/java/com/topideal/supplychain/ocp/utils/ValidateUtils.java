package com.topideal.supplychain.ocp.utils;

import com.topideal.supplychain.exception.BusinessAssert;
import com.topideal.supplychain.ocp.annotation.Length;
import com.topideal.supplychain.ocp.annotation.Required;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * <p>标题: 字段参数校验工具</p>
 * <p>模块: ocp-parent</p>
 * <p>版权: Copyright © 2019 topideal</p>
 * <p>公司: 广东卓志供应链科技有限公司武汉分公司</p>
 * <p>类路径: com.topideal.supplychain.ocp.utils</p>
 * <p>作者: LeoZhang</p>
 * <p>创建日期: 2019/12/18 11:21</p>
 *
 * @version 1.0
 */
public class ValidateUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(ValidateUtils.class);

    private ValidateUtils(){ }

    /**
     * 校验字段必填
     * @param model
     * @param <T>
     */
    public static <T> void validateNull(T model) {
        BusinessAssert.assertNotNull(model, "校验对象为空");
        // 获取class对象
        Class<?> clazz = model.getClass();
        // 获取当前类的所有属性（目前不向上找父类，以后需要再加）
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Required required = field.getAnnotation(Required.class);
            if (required == null) {
                continue;
            }
            try {
                Object value = field.get(model);
                BusinessAssert.assertIsFalse(value == null || (value instanceof String && StringUtils.isBlank((String) value))
                        , StringUtils.isNotEmpty(required.message()) ? required.message() : field.getName() + "信息为空");
            } catch (IllegalAccessException e) {
                LOGGER.error("cannot access field", e);
            }
        }
    }

    /**
     * 校验字段必填
     * @param model
     * @param <T>
     */
    public static <T> void validateLength(T model) {
        BusinessAssert.assertNotNull(model, "校验对象为空");
        // 获取class对象
        Class<?> clazz = model.getClass();
        // 获取当前类的所有属性（目前不向上找父类，以后需要再加）
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Length length = field.getAnnotation(Length.class);
            if (length == null) {
                continue;
            }
            try {
                Object value = field.get(model);
                //如果不是字符串或者为空不校验
                if (!(value instanceof String) || StringUtils.isEmpty((String) value)) {
                    continue;
                }
                String val = (String) value;
                //如果有值需要校验长度是否符合
                BusinessAssert.assertIsFalse((length.maxLength() != 2147483647 && val.length() > length.maxLength())
                                || (length.minLength() != 0 && val.length() > length.minLength())
                        , StringUtils.isNotEmpty(length.message()) ? length.message() : field.getName() + "信息为空");

            } catch (IllegalAccessException e) {
                LOGGER.error("cannot access field", e);
            }
        }
    }
}
