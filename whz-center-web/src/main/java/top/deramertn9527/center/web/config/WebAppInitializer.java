package top.deramertn9527.center.web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * java config 取代 传统 spring mvc web.xml
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    /**
     * web.xml 中的 url-pattern
     *
     * @return String[]
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/", "*.action"};
    }
}
