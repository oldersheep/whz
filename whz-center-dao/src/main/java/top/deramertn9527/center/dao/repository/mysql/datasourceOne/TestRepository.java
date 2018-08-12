package top.deramertn9527.center.dao.repository.mysql.datasourceOne;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.deramertn9527.center.domain.mysql.datasourceOne.TestPO;

import java.util.Date;
import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<TestPO, Long>, JpaSpecificationExecutor {

    List<TestPO> findByCreateTimeBefore(Date date);
}
