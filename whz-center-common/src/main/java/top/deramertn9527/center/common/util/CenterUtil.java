package top.deramertn9527.center.common.util;


import top.deramertn9527.center.common.cache.CacheUtil;
import top.deramertn9527.center.common.cache.DomainCache;
import top.deramertn9527.center.common.exception.WhzServerException;
import top.deramertn9527.center.common.exception.enums.ExceptionEnum;

import java.util.List;
import java.util.Set;

/**
 * 工具类
 */
public class CenterUtil {

    private CenterUtil() {
    }

    /**
     * 校验对象必填工具类
     *
     * @param object 待校验的对象
     */
    public static void checkObjectNotNull(final Object object) {
        // 从缓存中得到校验信息
        List<DomainCache> domainCacheList = CacheUtil.getDomainCacheList(object.getClass().getName());
        // 校验信息列表
        domainCacheList.forEach(domainCache -> {
            Object o;
            try {
                // 得到校验内容
                o = domainCache.getMethod().invoke(object);
            } catch (Exception e) {
                throw new WhzServerException(e);
            }
            checkAnnotation(domainCache.getClazz(), o, domainCache.getExceptionEnum());
        });
    }

    /**
     * 执行注解校验方法
     *
     * @param clazz 校验内容的类型
     * @param o     校验内容
     * @param e     失败时提示的错误信息
     */
    public static void checkAnnotation(Class clazz, Object o, ExceptionEnum e) {
        if (clazz == String.class) {
            // 判断该属性是字符串
            Assert.notNull((String) o, e);
        } else if (List.class.isAssignableFrom(clazz)) {
            // 判断该属性是List集合
            Assert.notEmpty((List) o, e);
        } else if (Set.class.isAssignableFrom(clazz)) {
            // 判断该属性是Set集合
            Assert.notEmpty((Set) o, e);
        } else {
            // 普通的object对象
            Assert.notNull(o, e);
        }
    }
}
