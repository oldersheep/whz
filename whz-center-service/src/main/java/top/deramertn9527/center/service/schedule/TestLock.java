package top.deramertn9527.center.service.schedule;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.deramertn9527.center.domain.mysql.datasourceOne.TestPO;
import top.deramertn9527.center.service.schedule.lock.AbstractLockWorker;
import top.deramertn9527.center.service.test.TestService;

import java.util.UUID;


@Component
@Slf4j
public class TestLock extends AbstractLockWorker {

    @Autowired
    private TestService testService;

    @Scheduled(cron = "0 */1 * * * ?")
    public void start() {
        super.work(2L);
    }

    @Override
    protected long expireTime() {
        return 60_000L;
    }

    @Override
    protected long intervalTime() {
        return 5_000L;
    }

    @Override
    protected Long doWork() {

        Long num = 0L;
        for(int i = 0; i < 50; i++){
            try {
                System.out.println("正在执行----num "+num);
                TestPO testPO = new TestPO();
                testPO.setRfId(num);
                testPO.setName(UUID.randomUUID().toString());
                testService.create(testPO);
                System.out.println(JSON.toJSONString(testPO));
                i++;
                num++;
            } catch (Exception e) {
               log.error("TestLock doWork Exception ", e);
                System.out.println(e);
            }
        }
       return num;
    }

    @Override
    protected String getKey() {
        return this.getClass().getSimpleName();
    }

}
