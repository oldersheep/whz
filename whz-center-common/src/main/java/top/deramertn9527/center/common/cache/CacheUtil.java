package top.deramertn9527.center.common.cache;

import lombok.extern.slf4j.Slf4j;
import top.deramertn9527.center.common.annotation.NotNull;
import top.deramertn9527.center.common.exception.WhzServerException;
import top.deramertn9527.center.common.exception.enums.ExceptionEnum;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 缓存工具类
 *
 */
@Slf4j
public class CacheUtil {

    private CacheUtil() {
    }

    /**
     * 获取缓存校验信息
     *
     * @param key 缓存的key
     * @return List<DomainCache>
     */
    public static List<DomainCache> getDomainCacheList(String key) {

        List<DomainCache> list = new ArrayList<>();

        // 缓存不存在
        if (!CheckCache.containsKey(key)) {
            log.info("cache empty: {}", key);
            // 反射类对象
            Class clazz;
            try {
                // 通过反射获得类对象
                clazz = Class.forName(key);
            } catch (ClassNotFoundException e) {
                throw new WhzServerException(e);
            }

            // 缓存中单个对象
            DomainCache domainCache;
            while (clazz != null) {
                // 获得对象中的所有属性
                for (Field field : clazz.getDeclaredFields()) {
                    // 判断属性上是否有标签非空注解
                    if (field.isAnnotationPresent(NotNull.class)) {
                        // 获取注解中的异常枚举类
                        ExceptionEnum e = field.getAnnotation(NotNull.class).e();
                        domainCache = new DomainCache();
                        domainCache.setClazz(field.getType());
                        domainCache.setExceptionEnum(e);
                        try {
                            // 得到对象的取值方法
                            Method m;
                            String name = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                            if (field.getType() != Boolean.class && field.getType() != boolean.class) {
                                m = clazz.getMethod("get" + name);
                            } else {
                                m = clazz.getMethod("is" + name);
                            }
                            domainCache.setMethod(m);
                        } catch (NoSuchMethodException e1) {
                            log.error("cache NoSuchMethodException: ", e);
                        }
                        list.add(domainCache);
                    }
                }
                clazz = clazz.getSuperclass();
            }
            CheckCache.put(key, list);
        } else {
            Object object = CheckCache.get(key);
            if (object != null) {
                if (object instanceof List) {
                    List l = (List) object;
                    if (!l.isEmpty()) {
                        if (l.get(0) instanceof DomainCache) {
                            for (Object o : l) {
                                list.add((DomainCache) o);
                            }
                        }
                    }
                }
            }
        }
        return list;
    }
}