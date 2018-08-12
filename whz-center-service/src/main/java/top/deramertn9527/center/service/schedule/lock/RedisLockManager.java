package top.deramertn9527.center.service.schedule.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.deramertn9527.center.dao.config.RedisClient;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class RedisLockManager {

    @Autowired
    private RedisClient cluster;

    private ConcurrentHashMap<String, RedisLock> lockMap = new ConcurrentHashMap<>();

    public RedisLock getLock(String key, long expireTime, long intervalTime) {
        return lockMap.computeIfAbsent(key, (k) -> new RedisLock(k, cluster, expireTime, intervalTime));
    }
}