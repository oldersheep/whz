package top.deramertn9527.center.web.config.view;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

import java.io.IOException;

/**
 * volocitu 视图解析器、引擎 配置文件
 */
@Configuration
public class SpringVelocityConfig {

    @Value("${spring.velocity.viewresolver.resourceLoaderPath}")
    private String resourceLoaderPath;
    @Value("${spring.velocity.viewresolver.configLocation}")
    private String configLocation;

    @Value("${spring.velocity.viewresolver.cache}")
    private boolean cache;
    @Value("${spring.velocity.viewresolver.prefix}")
    private String prefix;
    @Value("${spring.velocity.viewresolver.suffix}")
    private String suffix;
    @Value("${spring.velocity.viewresolver.layoutUrl}")
    private String layoutUrl;
    @Value("${spring.velocity.viewresolver.dateToolAttribute}")
    private String dateToolAttribute;
    @Value("${spring.velocity.viewresolver.numberToolAttribute}")
    private String numberToolAttribute;
    @Value("${spring.velocity.viewresolver.contentType}")
    private String contentType;

    /**
     * 配置velocity引擎
     *
     * @return VelocityConfigurer
     * @throws IOException
     */
    @Bean
    public VelocityConfigurer velocityConfigurer() throws IOException {
        VelocityConfigurer velocityConfigurer = new VelocityConfigurer();
        velocityConfigurer.setResourceLoaderPath(resourceLoaderPath);
        velocityConfigurer.setConfigLocation(new ClassPathResource(configLocation));
        return velocityConfigurer;
    }

    /**
     * 配置velocity视图解析器
     *
     * @return ViewResolver
     */
    @Bean
    public ViewResolver viewResolver() {
        VelocityLayoutViewResolver viewResolver = new VelocityLayoutViewResolver();
        viewResolver.setCache(cache);
        viewResolver.setPrefix(prefix);
        viewResolver.setSuffix(suffix);
        viewResolver.setLayoutUrl(layoutUrl);
        viewResolver.setDateToolAttribute(dateToolAttribute);
        viewResolver.setNumberToolAttribute(numberToolAttribute);
        viewResolver.setContentType(contentType);
        return viewResolver;
    }
}