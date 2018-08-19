package top.deramertn9527.center.service.simpleschedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import top.deramertn9527.center.dao.config.RedisClient;

import java.util.concurrent.TimeUnit;


@Slf4j
public abstract class AbstractLockWorker extends AbstractWorker {

    @Autowired
    private RedisClient cluster;


    /**
     * jimdb key前缀
     *
     * @return String
     */
    protected abstract String prefix();

    /**
     * 执行主方法
     *
     * @throws Exception 抛出系统异常
     */
    protected abstract void workDetail() throws Exception;

    /**
     * 默认自旋间隔时间 实现类可重写
     *
     * @return Long
     */
    protected Long sleepMS() {
        return MS_10S;
    }

    /**
     * 默认缓存失效时间 实现类可重写
     *
     * @return Long
     */
    protected Long expireMIN() {
        return MIN_30MIN;
    }

    @Override
    public void work() {

        // 尝试获取分布式锁(*_LOCK)
        if (this.tryAdd(K_LOCK)) {

            // 如果此时已存在完成标记则退出
            if (this.cluster.hasKey(K_DONE)) {
                return;
            }

            try {
                this.workDetail();
                // 添加完成标记
                this.tryAdd(K_DONE);
            } catch (Exception e) {
                log.error("异常: {}", e);
            } finally {
                this.cluster.delete(K_LOCK);
            }
        } else {
            // 判断锁是否仍存在
            while (this.cluster.hasKey(K_LOCK)) {
                try {
                    // 自旋间隔
                    Thread.sleep(this.sleepMS());
                } catch (InterruptedException ie) {
                    log.error("线程异常: {}", ie);
                    Thread.interrupted();
                }
            }

            // 验证完成标记(*_DONE)
            if (this.cluster.hasKey(K_DONE)) {
                log.info("发现完成标记,线程退出");
                return;
            }

            // 验证失败后重跑 尝试获取分布式锁(*_RETRY)
            if (this.tryAdd(K_RETRY)) {
                log.info("重跑");
                try {
                    this.workDetail();
                } catch (Exception e) {
                    log.error("Error: ", e);
                }
                log.info("重跑完成");
            }
        }
    }

    /**
     * 尝试在缓存添加短暂key，如果已存在返回false
     *
     * @param key key
     * @return boolean
     */
    private boolean tryAdd(String key) {
        return this.cluster.set(key, PRESENT, this.expireMIN(), TimeUnit.MINUTES, false);
    }

    /**
     * 分布式锁
     */
    private final String K_LOCK = this.prefix() + "_LOCK";

    /**
     * 分布式完成锁
     */
    private final String K_DONE = this.prefix() + "_DONE";

    /**
     * 分布式重试锁
     */
    private final String K_RETRY = this.prefix() + "_RETRY";

    /**
     * 分布式锁，默认value
     */
    private static final String PRESENT = "1";

    /**
     * 重试间隔时间
     */
    private static final Long MS_10S = 10 * 1000L;

    /**
     * 分布式锁超时时间
     */
    private static final Long MIN_30MIN = 30L;

    /**
     * 每次查询数据库条数
     */
    protected static final int COUNT_PER_TIME = 20;
}