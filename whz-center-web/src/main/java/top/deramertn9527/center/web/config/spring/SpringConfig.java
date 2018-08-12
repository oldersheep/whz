package top.deramertn9527.center.web.config.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * spring 管理xml文件的 javabean
 * 统一加载配置文件
 */
@Configuration
@Slf4j
//@ImportResource(
//        value = {
//                "classpath:spring/*.xml"
//        }
//)
@ComponentScan({"top.deramertn9527"})
@EnableScheduling
public class SpringConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() throws UnsupportedEncodingException {

        PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        String classpath = URLDecoder.decode(Thread.currentThread().getContextClassLoader().getResource("").getPath(), "utf-8");

        File directory = new File(classpath + "properties");

        if (directory.isDirectory()) {
            File[] propFiles = directory.listFiles();
            if (ArrayUtils.isNotEmpty(propFiles)) {
                List<ClassPathResource> resourceList = new ArrayList<>();
                List<ClassPathResource> yamlList = new ArrayList<>();
                for (File file : propFiles) {
                    String fileName = file.getName();
                    log.info("Scan Property File：" + fileName);
                    if (fileName.endsWith("properties") || fileName.endsWith("yml")) {
                        ClassPathResource classPathResource = new ClassPathResource("properties/" + fileName);
                        resourceList.add(classPathResource);
                        if (fileName.endsWith("yml")) {
                            yamlList.add(classPathResource);
                        }
                    }

                }
                YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
                yaml.setResources(yamlList.toArray(new ClassPathResource[yamlList.size()]));
                propertyPlaceholderConfigurer.setProperties(yaml.getObject());
                propertyPlaceholderConfigurer.setLocations(resourceList.toArray(new ClassPathResource[resourceList.size()]));
            }
        }
        propertyPlaceholderConfigurer.setIgnoreResourceNotFound(true);
        return propertyPlaceholderConfigurer;
    }
}