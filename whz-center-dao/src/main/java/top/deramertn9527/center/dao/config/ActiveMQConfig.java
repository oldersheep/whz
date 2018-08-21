package top.deramertn9527.center.dao.config;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.SimpleMessageConverter;


@Configuration
public class ActiveMQConfig {

    @Value("${activeMQ.brokerURL}")
    private String brokerURL;

    @Value("${activeMQ.maxThreadPoolSize}")
    private Integer maxThreadPoolSize;

    @Value("${activeMQ.sessionCacheSize}")
    private Integer sessionCacheSize;


    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerURL);
//        activeMQConnectionFactory.setPassword("admin");
//        activeMQConnectionFactory.setUserName("admin");
        activeMQConnectionFactory.setMaxThreadPoolSize(maxThreadPoolSize);
        return activeMQConnectionFactory;
    }


    @Bean
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory activeMQConnectionFactory,
                                   SimpleMessageConverter simpleMessageConverter){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory);
        jmsTemplate.setMessageConverter(simpleMessageConverter);
        // true是topic，false是queue，默认是false，此处显示写出true
        jmsTemplate.setPubSubDomain(Boolean.TRUE);

        return jmsTemplate;
    }

    @Bean
    public SimpleMessageConverter simpleMessageConverter(){
        return new SimpleMessageConverter();
    }
}
