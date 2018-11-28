package top.deramertn9527.center.service.schedule.lock;

import org.apache.commons.lang3.StringUtils;
import top.deramertn9527.center.dao.config.RedisClient;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 分布式锁
 */
public class RedisLock implements Lock {

    /**
     * 锁redis key
     */
    private final String key;
    /**
     * redis 客户端
     */
    private final RedisClient cluster;
    /**
     * 锁持续时间
     */
    private final long expireTime;
    /**
     * 重试间隔时间
     */
    private final long intervalTime;
    /**
     * 本地并发连接占用锁
     */
    private final Lock lock;
    /**
     * 锁持有线程
     */
    private volatile Thread owner;

    private static final long DEFAULT_TIMEOUT = 5_000L;
    private static final long DEFAULT_EXPIRE_TIME = 10_000L;
    private static final long DEFAULT_INTERVAL_TIME = 2_000L;
    private static final String PRESENT = "1";

    public RedisLock(String key, RedisClient cluster) {
        this(key, cluster, DEFAULT_EXPIRE_TIME, DEFAULT_INTERVAL_TIME);
    }

    public RedisLock(String key, RedisClient cluster, long expireTime, long intervalTime) {
        if (StringUtils.isEmpty(key)) throw new NullPointerException("key in RedisLock");
        if (cluster == null) throw new NullPointerException("cluster in RedisLock");
        this.key = key;
        this.cluster = cluster;
        this.expireTime = expireTime > 0 ? expireTime : DEFAULT_EXPIRE_TIME;
        this.intervalTime = intervalTime > 0 ? intervalTime : DEFAULT_INTERVAL_TIME;
        this.lock = new ReentrantLock();
    }

    @Override
    public boolean tryLock() {
        if (this.lock.tryLock()) {
            if (this.cluster.set(this.key, PRESENT, this.expireTime, TimeUnit.MILLISECONDS, false)) {
                this.owner = Thread.currentThread();
                return true;
            }
            this.lock.unlock();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        long start = System.currentTimeMillis();
        long timeout = time > 0 && unit != null ? unit.toMillis(time) : DEFAULT_TIMEOUT;
        boolean got = false;
        if (this.lock.tryLock(timeout, TimeUnit.MILLISECONDS)) {
            try {
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
                long limit = start + timeout;
                while (System.currentTimeMillis() < limit) {
                    if (this.cluster.set(this.key, PRESENT, this.expireTime, TimeUnit.MILLISECONDS, false)) {
                        this.owner = Thread.currentThread();
                        got = true;
                        break;
                    }
                    Thread.sleep(intervalTime);
                }
            } finally {
                if (!got) {
                    this.lock.unlock();
                }
            }
        }
        return got;
    }

    @Override
    public void unlock() {
        if (this.owner == Thread.currentThread()) {
            try {
                this.cluster.delete(this.key);
                this.owner = null;
            } finally {
                this.lock.unlock();
            }
        }
    }

    @Deprecated
    @Override
    public void lock() {
        throw new UnsupportedOperationException("method lock() in lock is unsupported");
    }

    @Deprecated
    @Override
    public void lockInterruptibly() {
        throw new UnsupportedOperationException("method lockInterruptibly() in lock is unsupported");
    }

    @Deprecated
    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException("method newCondition() in lock is unsupported");
    }
}