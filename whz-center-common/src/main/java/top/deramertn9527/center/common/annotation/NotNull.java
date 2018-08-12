package top.deramertn9527.center.common.annotation;



import top.deramertn9527.center.common.exception.enums.ExceptionEnum;

import java.lang.annotation.*;

/**
 *验证非空自定义注解
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface NotNull {

    /**
     * 验证失败，提示的异常枚举
     *
     * @return TagExceptionEnum
     */
    ExceptionEnum e() default ExceptionEnum.CHECK_PARAM_ERROR;
}
