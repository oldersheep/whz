package top.deramertn9527.center.common.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring 上下文工具类
 */
@Component
@Data
@Slf4j
public class SpringContextUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * 通过 bean名称 得到 bean对象
     *
     * @param name bean名称
     * @return Object
     */
    public Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 通过 class类 得到 bean对象
     *
     * @param clazz class类
     * @param <T>   泛型
     * @return T
     */
    public <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过 bean名称、class类 得到 bean对象
     *
     * @param name  bean名称
     * @param clazz class类
     * @param <T>   泛型
     * @return T
     */
    public <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    public String[] getBeanNameByType(Class requiredType) {
        return applicationContext.getBeanNamesForType(requiredType);
    }
}
