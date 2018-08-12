package top.deramertn9527.center.common.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 校验数据缓存
 *
 */
public class CheckCache {

    private CheckCache() {
    }

    /**
     * 缓存容器初始化大小
     */
    private static final int CACHE_HASHMAP_INITIAL_CAPACITY = 1 << 8;

    /**
     * 缓存数据容器
     */
    private static final ConcurrentHashMap<String, Object> CACHE_MAP = new ConcurrentHashMap<>(CACHE_HASHMAP_INITIAL_CAPACITY);

    /**
     * 查询容器是否存在该缓存
     *
     * @param key 缓存的key
     * @return boolean
     */
    public static boolean containsKey(String key) {
        return CACHE_MAP.containsKey(key);
    }

    /**
     * 通过key 查询缓存数据
     *
     * @param key 缓存的key
     * @return Object
     */
    public static Object get(String key) {
        return CACHE_MAP.get(key);
    }

    /**
     * 容器中添加缓存数据
     *
     * @param key    缓存的key
     * @param object 缓存的对象
     * @return Object
     */
    public static Object put(String key, Object object) {
        return CACHE_MAP.put(key, object);
    }

    /**
     * 得到缓存容器
     *
     * @return ConcurrentMap
     */
    public static ConcurrentMap<String, Object> getCache() {
        return CACHE_MAP;
    }

    /**
     * 清空缓存
     */
    public static void clear() {
        CACHE_MAP.clear();
    }

    /**
     * 移除指定的key的缓存内容
     *
     * @param key 缓存的key
     * @return Object
     */
    public static Object remove(String key) {
        return CACHE_MAP.remove(key);
    }
}