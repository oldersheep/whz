package top.deramertn9527.center.dao.mysql.test.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.deramertn9527.center.common.util.DateUtil;
import top.deramertn9527.center.dao.mysql.test.TestDao;
import top.deramertn9527.center.dao.repository.mysql.datasourceOne.TestRepository;
import top.deramertn9527.center.domain.mysql.datasourceOne.TestPO;

import java.util.Date;
import java.util.List;

/**
 * 类描述:
 *
 * @author:tangniannian
 * @date:2018/8/12
 * @修改描述：
 * @modifier ${tags}
 */
@Repository
public class TestDaoImpl implements TestDao {

    @Autowired
    private TestRepository testRepository;

    @Override
    public TestPO findById(Long id) {
        return testRepository.findOne(id);
    }

    @Override
    public Boolean create(TestPO testPO) {
        testPO.setCreateTime(DateUtil.getNow());

        testRepository.save(testPO);

        return Boolean.TRUE;
    }

    @Override
    public List<TestPO> findPreInfo(Date date) {
        return testRepository.findByCreateTimeBefore(date);
    }
}
