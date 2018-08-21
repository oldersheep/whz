package top.deramertn9527.center.service.test.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.deramertn9527.center.dao.mysql.test.TestDao;
import top.deramertn9527.center.domain.mysql.datasourceOne.TestPO;
import top.deramertn9527.center.service.test.TestService;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;


    @Override
    public TestPO findById(Long id) {
        return testDao.findById(id);
    }

    @Override
    public Boolean create(TestPO testPO) {
        return testDao.create(testPO);
    }

    @Override
    public List<TestPO> findPreInfo(Date date) {
        return testDao.findPreInfo(new Date());
    }

}
