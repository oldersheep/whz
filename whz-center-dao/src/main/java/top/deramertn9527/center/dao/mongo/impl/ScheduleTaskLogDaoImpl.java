package top.deramertn9527.center.dao.mongo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import top.deramertn9527.center.common.annotation.ServerDaoException;
import top.deramertn9527.center.dao.mongo.ScheduleTaskLogDao;
import top.deramertn9527.center.dao.mongo.SequenceDao;
import top.deramertn9527.center.dao.repository.mongo.ScheduleTaskLogRepository;
import top.deramertn9527.center.domain.mongo.ScheduleTaskLogPO;

import java.util.Date;


@Repository
public class ScheduleTaskLogDaoImpl implements ScheduleTaskLogDao {

    private static final String PO_ID = "ScheduleTaskLogPOID";

    @Autowired
    private ScheduleTaskLogRepository scheduleTaskLogRepository;

    @Autowired
    private SequenceDao sequenceDao;

    @Override
    public Page<ScheduleTaskLogPO> findByExecBeanId(String execBeanId, Pageable pageable) {
        return scheduleTaskLogRepository.findByExecBeanId(execBeanId, pageable);
    }

    @Override
    @ServerDaoException
    public Boolean create(ScheduleTaskLogPO scheduleTaskLogPO) {
        scheduleTaskLogPO.setId(sequenceDao.get(PO_ID));
        scheduleTaskLogPO.setCreated(new Date());
        scheduleTaskLogRepository.save(scheduleTaskLogPO);
        return Boolean.TRUE;
    }
}
