package top.deramertn9527.center.service.simpleschedule.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.deramertn9527.center.domain.mysql.datasourceOne.TestPO;
import top.deramertn9527.center.service.simpleschedule.AbstractLockWorker;
import top.deramertn9527.center.service.test.TestService;

import java.util.UUID;


@Component("testSchedule")
@Slf4j
public class TestSchedule extends AbstractLockWorker {

    @Autowired
    private TestService testService;

    @Override
    protected String prefix() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected void workDetail() throws Exception {
        for (int i = 0; i < 50; i++) {
            try {
                System.out.println("正在执行----num " + num);
                TestPO testPO = new TestPO();
                testPO.setRfId(num);
                testPO.setName(UUID.randomUUID().toString());
                testService.create(testPO);
                System.out.println(JSON.toJSONString(testPO));
                i++;
            } catch (Exception e) {
                log.error("TestLock doWork Exception ", e);
                System.out.println(e);
            }
        }
    }

    @Scheduled(cron = "0 */59 * * * ?")
    @Override
    public void start() {
        this.work();
    }
}
