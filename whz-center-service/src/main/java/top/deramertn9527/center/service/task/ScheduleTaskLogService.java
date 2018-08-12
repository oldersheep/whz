package top.deramertn9527.center.service.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import top.deramertn9527.center.domain.mongo.ScheduleTaskLogPO;

public interface ScheduleTaskLogService {

    Page<ScheduleTaskLogPO> findByExecBeanId(String execBeanId, Pageable pageable);

    Boolean create(ScheduleTaskLogPO scheduleTaskLogPO);
}
