package top.deramertn9527.center.web.controller.test;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.deramertn9527.center.dao.config.RedisClient;
import top.deramertn9527.center.domain.mysql.datasourceOne.TestPO;
import top.deramertn9527.center.service.test.TestService;

/**
 * 类描述:
 *
 * @author:tangniannian
 * @date:2018/8/9
 * @修改描述：
 * @modifier ${tags}
 */
@RestController
@RequestMapping("/test")
public class Test {

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private TestService testService;

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
}
