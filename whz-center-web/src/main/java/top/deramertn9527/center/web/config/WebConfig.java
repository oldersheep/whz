package top.deramertn9527.center.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import top.deramertn9527.center.web.config.interceptor.AuthInterceptor;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"top.deramertn9527.**.controller"})
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 拦截器拦截地址
     */
    private String[] includePatterns = {"/**"};
    /**
     * 拦截器过滤地址
     * <p>
     * 系统访问路径: ["/system/**"]
     */
    private String[] excludePatterns = {"/system/**", "/test/**"};


    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns(includePatterns).excludePathPatterns(excludePatterns);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}