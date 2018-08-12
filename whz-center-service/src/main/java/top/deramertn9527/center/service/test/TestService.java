package top.deramertn9527.center.service.test;

import top.deramertn9527.center.domain.mysql.datasourceOne.TestPO;

import java.util.Date;
import java.util.List;

public interface TestService {

    TestPO findById(Long id);

    Boolean create(TestPO testPO);

    List<TestPO> findPreInfo(Date date);
}
