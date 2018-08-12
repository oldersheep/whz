package top.deramertn9527.center.dao.repository.mongo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import top.deramertn9527.center.domain.mongo.ScheduleTaskLogPO;


@Repository
public interface ScheduleTaskLogRepository extends MongoRepository<ScheduleTaskLogPO, Long> {

    Page<ScheduleTaskLogPO> findByExecBeanId(String execBeanId, Pageable pageable);
}
