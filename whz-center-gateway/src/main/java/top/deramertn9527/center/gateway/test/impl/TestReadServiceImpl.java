package top.deramertn9527.center.gateway.test.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.deramertn9527.center.api.doman.Test;
import top.deramertn9527.center.api.exception.WhzCenterApiException;
import top.deramertn9527.center.api.read.TestReadService;
import top.deramertn9527.center.common.exception.enums.ExceptionEnum;
import top.deramertn9527.center.common.util.Assert;
import top.deramertn9527.center.common.util.BeanUtil;
import top.deramertn9527.center.service.test.TestService;

@Service("testReadServiceImpl")
public class TestReadServiceImpl implements TestReadService {

    @Autowired
    private TestService testService;

    @Override
    public Test findById(Long id) throws WhzCenterApiException {
        Assert.notNull(id, ExceptionEnum.CHECK_PARAM_ERROR);

        return BeanUtil.toBean(testService.findById(id), Test.class);
    }
}
