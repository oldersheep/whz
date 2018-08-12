package top.deramertn9527.center.common.cache;

import lombok.Data;
import top.deramertn9527.center.common.exception.enums.ExceptionEnum;

import java.lang.reflect.Method;

/**
 * domain check cache object
 *
 */
@Data
public class DomainCache {

    /**
     * 取值的方法
     */
    private Method method;
    /**
     * 属性信息
     */
    private Class clazz;
    /**
     * 异常枚举对象
     */
    private ExceptionEnum exceptionEnum;
}