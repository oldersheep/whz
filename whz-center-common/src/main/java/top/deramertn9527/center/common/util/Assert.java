package top.deramertn9527.center.common.util;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.deramertn9527.center.common.exception.CheckException;
import top.deramertn9527.center.common.exception.enums.ExceptionEnum;

import java.util.Collection;
import java.util.Map;

/**
 * 自定义断言
 */
public class Assert {

    private Assert() {
    }

    /**
     * 是否为真，不为真时，抛出枚举类定义的异常信息
     *
     * @param expression    判断条件
     * @param exceptionEnum 异常枚举类
     */
    public static void isTrue(boolean expression, ExceptionEnum exceptionEnum) {
        if (!expression) {
            throw new CheckException(exceptionEnum);
        }
    }

    /**
     * 是否为真，为真时，抛出枚举类定义的异常信息
     *
     * @param expression    判断条件
     * @param exceptionEnum 异常枚举类
     */
    public static void notTrue(boolean expression, ExceptionEnum exceptionEnum) {
        if (expression) {
            throw new CheckException(exceptionEnum);
        }
    }

    /**
     * 是否为真，不为真时，抛出指定的异常码和异常信息
     *
     * @param expression 判断条件
     * @param errCode    异常码
     * @param message    异常信息
     */
    public static void isTrue(boolean expression, Long errCode, String message) {
        if (!expression) {
            throw new CheckException(errCode, message);
        }
    }

    /**
     * 是否为真，不为真时，抛出指定的异常码和异常信息
     *
     * @param expression 判断条件
     * @param errCode    异常码
     * @param message    异常信息
     */
    public static void notTrue(boolean expression, Long errCode, String message) {
        if (expression) {
            throw new CheckException(errCode, message);
        }
    }

    /**
     * 是否为真，不为真时，抛出指定的异常信息
     *
     * @param expression 判断条件
     * @param message    异常信息
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new CheckException(ExceptionEnum.IS_TRUE.getErrCode(), message);
        }
    }

    /**
     * 是否为真，为真时，抛出指定的异常信息
     *
     * @param expression 判断条件
     * @param message    异常信息
     */
    public static void notTrue(boolean expression, String message) {
        if (expression) {
            throw new CheckException(ExceptionEnum.NOT_TRUE.getErrCode(), message);
        }
    }

    /**
     * 是否为空，不为空时，抛出枚举类定义的异常信息
     *
     * @param object        判空对象
     * @param exceptionEnum 异常枚举类
     */
    public static void isNull(Object object, ExceptionEnum exceptionEnum) {
        if (object != null) {
            throw new CheckException(exceptionEnum);
        }
    }

    /**
     * 是否为空，不为空时，抛出指定的异常信息
     *
     * @param object  判空对象
     * @param message 异常信息
     */
    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new CheckException(ExceptionEnum.IS_NULL.getErrCode(), message);
        }
    }

    /**
     * 是否为空，不为空时，抛出枚举类定义的异常信息
     *
     * @param s             判空对象
     * @param exceptionEnum 异常枚举类
     */
    public static void isNull(String s, ExceptionEnum exceptionEnum) {
        if (!StringUtils.isEmpty(s)) {
            throw new CheckException(exceptionEnum);
        }
    }

    /**
     * 是否为空，不为空时，抛出指定的异常信息
     *
     * @param s       判空对象
     * @param message 异常信息
     */
    public static void isNull(String s, String message) {
        if (!StringUtils.isEmpty(s)) {
            throw new CheckException(ExceptionEnum.IS_NULL.getErrCode(), message);
        }
    }

    /**
     * 是否为空，为空时，抛出枚举类定义的异常信息
     *
     * @param object        判空对象
     * @param exceptionEnum 异常枚举类
     */
    public static void notNull(Object object, ExceptionEnum exceptionEnum) {
        if (object == null) {
            throw new CheckException(exceptionEnum);
        }
    }

    /**
     * 是否为空，为空时，抛出指定的异常信息
     *
     * @param object  判空对象
     * @param message 异常信息
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new CheckException(ExceptionEnum.NOT_NULL.getErrCode(), message);
        }
    }

    /**
     * 是否为空，为空时，抛出枚举类定义的异常信息
     *
     * @param str           字符串对象
     * @param exceptionEnum 异常枚举类
     */
    public static void notNull(String str, ExceptionEnum exceptionEnum) {
        if (StringUtils.isEmpty(str)) {
            throw new CheckException(exceptionEnum);
        }
    }

    /**
     * 是否为空，为空时，抛出指定的异常信息
     *
     * @param str     字符串对象
     * @param message 异常信息
     */
    public static void notNull(String str, String message) {
        if (StringUtils.isEmpty(str)) {
            throw new CheckException(ExceptionEnum.NOT_NULL.getErrCode(), message);
        }
    }

    /**
     * 数组是否为空，为空时，抛出枚举类定义的异常信息
     *
     * @param array         判断数组对象
     * @param exceptionEnum 异常枚举类
     */
    public static void notEmpty(Object[] array, ExceptionEnum exceptionEnum) {
        if (ObjectUtils.isEmpty(array)) {
            throw new CheckException(exceptionEnum);
        }
    }

    /**
     * 数组是否为空，不为空时，抛出枚举类定义的异常信息
     *
     * @param array         判断数组对象
     * @param exceptionEnum 异常枚举类
     */
    public static void isEmpty(Object[] array, ExceptionEnum exceptionEnum) {
        if (!ObjectUtils.isEmpty(array)) {
            throw new CheckException(exceptionEnum);
        }
    }

    /**
     * 数组是否为空，为空时，抛出指定的异常信息
     *
     * @param array   判断数组对象
     * @param message 异常信息
     */
    public static void notEmpty(Object[] array, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new CheckException(ExceptionEnum.NOT_EMPTY.getErrCode(), message);
        }
    }

    /**
     * 数组是否为空，不为空时，抛出指定的异常信息
     *
     * @param array   判断数组对象
     * @param message 异常信息
     */
    public static void isEmpty(Object[] array, String message) {
        if (!ObjectUtils.isEmpty(array)) {
            throw new CheckException(ExceptionEnum.IS_EMPTY.getErrCode(), message);
        }
    }

    /**
     * 集合是否为空，为空时，抛出枚举类定义的异常信息
     *
     * @param collection    判断集合对象
     * @param exceptionEnum 异常枚举类
     */
    public static void notEmpty(Collection<?> collection, ExceptionEnum exceptionEnum) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new CheckException(exceptionEnum);
        }
    }

    /**
     * 集合是否为空，不为空时，抛出枚举类定义的异常信息
     *
     * @param collection    判断集合对象
     * @param exceptionEnum 异常枚举类
     */
    public static void isEmpty(Collection<?> collection, ExceptionEnum exceptionEnum) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new CheckException(exceptionEnum);
        }
    }

    /**
     * 集合是否为空，为空时，抛出枚举类定义的异常信息
     *
     * @param collection 判断集合对象
     * @param message    异常信息
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new CheckException(ExceptionEnum.NOT_EMPTY.getErrCode(), message);
        }
    }

    /**
     * 集合是否为空，不为空时，抛出枚举类定义的异常信息
     *
     * @param collection 判断集合对象
     * @param message    异常信息
     */
    public static void isEmpty(Collection<?> collection, String message) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new CheckException(ExceptionEnum.IS_EMPTY.getErrCode(), message);
        }
    }

    /**
     * 集合是否为空，为空时，抛出枚举类定义的异常信息
     *
     * @param map           判断Map对象
     * @param exceptionEnum 异常枚举类
     */
    public static void notEmpty(Map<?, ?> map, ExceptionEnum exceptionEnum) {
        if (CollectionUtils.isEmpty(map)) {
            throw new CheckException(exceptionEnum);
        }
    }

    /**
     * 集合是否为空，不为空时，抛出枚举类定义的异常信息
     *
     * @param map           判断Map对象
     * @param exceptionEnum 异常枚举类
     */
    public static void isEmpty(Map<?, ?> map, ExceptionEnum exceptionEnum) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new CheckException(exceptionEnum);
        }
    }

    /**
     * 集合是否为空，为空时，抛出枚举类定义的异常信息
     *
     * @param map     判断Map对象
     * @param message 异常信息
     */
    public static void notEmpty(Map<?, ?> map, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new CheckException(ExceptionEnum.NOT_EMPTY.getErrCode(), message);
        }
    }

    /**
     * 集合是否为空，不为空时，抛出枚举类定义的异常信息
     *
     * @param map     判断Map对象
     * @param message 异常信息
     */
    public static void isEmpty(Map<?, ?> map, String message) {
        if (!CollectionUtils.isEmpty(map)) {
            throw new CheckException(ExceptionEnum.IS_EMPTY.getErrCode(), message);
        }
    }
}