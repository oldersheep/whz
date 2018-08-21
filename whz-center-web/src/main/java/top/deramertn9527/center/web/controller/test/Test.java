package top.deramertn9527.center.web.controller.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.deramertn9527.center.dao.config.RedisClient;
import top.deramertn9527.center.domain.mysql.datasourceOne.TestPO;
import top.deramertn9527.center.service.listener.TestProducerService;
import top.deramertn9527.center.service.test.TestService;

import javax.annotation.Resource;
import javax.jms.Destination;


@RestController
@RequestMapping("/test")
public class Test {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private TestService testService;

    @Autowired
    private Environment environment;

    @Resource(name = "testTopic")
    private Destination testTopic;

    @Resource(name = "whz")
    private Destination whz;

    @Autowired
    private TestProducerService testProducerService;

    @Autowired
    private PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer;

    @GetMapping("/set/{key}/{value}")
    public Boolean set(@PathVariable String key, @PathVariable String value){
        redisClient.set(key, value);

        return Boolean.TRUE;
    }

    @GetMapping("/get/{key}")
    public Boolean get(@PathVariable String key){
        Object a = redisClient.get(key);
        System.out.println(a);
        return Boolean.TRUE;
    }

    @GetMapping("/{id}")
    public String findId(@PathVariable Long id){

        TestPO testPO = testService.findById(id);
        return JSONObject.toJSONString(testPO);

    }

    @GetMapping("/one/{txt}")
    public Boolean send(@PathVariable String txt){
        testProducerService.sendMessage(testTopic, txt);
        return Boolean.TRUE;
    }

    @GetMapping("/two/{txt}")
    public Boolean another(@PathVariable String txt){
        testProducerService.sendMessage(whz, txt);
        return Boolean.TRUE;
    }
}
