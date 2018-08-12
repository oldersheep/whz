package top.deramertn9527.center.common.annotation;



import top.deramertn9527.center.common.exception.enums.ExceptionEnum;

import java.lang.annotation.*;

/**
 * 异常解析注解
 * <p>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ServerDaoException {

    /**
     * dao 异常，提示的异常枚举
     *
     * @return TagExceptionEnum
     */
    ExceptionEnum value() default ExceptionEnum.SYSTEM_ERROR;
}
