package top.deramertn9527.center.service.schedule.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import top.deramertn9527.center.dao.config.RedisClient;
import top.deramertn9527.center.domain.mongo.ScheduleTaskLogPO;
import top.deramertn9527.center.service.task.ScheduleTaskLogService;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class AbstractLockWorker implements InitializingBean {

    @Autowired
    protected RedisClient cluster;

    @Autowired
    protected RedisLockManager redisLockManager;

    protected RedisLock lock;

    protected static final String PRESENT = "1";

    @Autowired
    private ScheduleTaskLogService scheduleTaskLogService;

    /**
     * work 任务方法
     */
    protected void work(Long time) {
        System.out.println("进入执行任务方法");
        try {
            if (this.lock.tryLock(time, TimeUnit.MINUTES)) {
                String doneKey = getKey() + "_DONE";
                if (PRESENT.equals(this.cluster.get(doneKey))) {
                    return;
                }

                Date date = new Date();
                Long num = this.doWork();
                if (this.cluster.set(doneKey, PRESENT, time, TimeUnit.MINUTES, false)) {
                    ScheduleTaskLogPO scheduleTaskLogPO = new ScheduleTaskLogPO();
                    scheduleTaskLogPO.setExecBeanId(getKey());
                    scheduleTaskLogPO.setStartTime(date);
                    scheduleTaskLogPO.setExecNum(num);
                    scheduleTaskLogPO.setUseTime(System.currentTimeMillis() - date.getTime());
                    scheduleTaskLogService.create(scheduleTaskLogPO);
                }

            }

        } catch (Exception e) {
            log.error("Error", e);
        } finally {
            this.lock.unlock();
        }
    }

    /**
     * 执行方法
     *
     * @return boolean
     */
    protected abstract Long doWork();

    /**
     * lock key 在redis中，超时时间
     * 默认：1分钟
     *
     * @return long
     */
    protected long expireTime() {
        return 60_000L;
    }

    /**
     * 尝试获取redis中，lock key的间隔时间
     * 默认：1秒
     *
     * @return long
     */
    protected long intervalTime() {
        return 1_000L;
    }

    protected abstract String getKey();

    @Override
    public void afterPropertiesSet() {
        this.lock = this.redisLockManager.getLock(this.getKey(), this.expireTime(), this.intervalTime());
    }
}