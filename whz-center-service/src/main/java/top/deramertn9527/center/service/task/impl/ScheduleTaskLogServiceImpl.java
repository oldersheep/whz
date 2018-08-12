package top.deramertn9527.center.service.task.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.deramertn9527.center.dao.mongo.ScheduleTaskLogDao;
import top.deramertn9527.center.domain.mongo.ScheduleTaskLogPO;
import top.deramertn9527.center.service.task.ScheduleTaskLogService;

@Service
public class ScheduleTaskLogServiceImpl implements ScheduleTaskLogService {

    @Autowired
    private ScheduleTaskLogDao scheduleTaskLogDao;

    @Override
    public Page<ScheduleTaskLogPO> findByExecBeanId(String execBeanId, Pageable pageable) {
        return scheduleTaskLogDao.findByExecBeanId(execBeanId, pageable);
    }

    @Override
    public Boolean create(ScheduleTaskLogPO scheduleTaskLogPO) {
        return scheduleTaskLogDao.create(scheduleTaskLogPO);
    }
}
