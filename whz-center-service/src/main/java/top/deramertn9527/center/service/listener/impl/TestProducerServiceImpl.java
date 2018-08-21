package top.deramertn9527.center.service.listener.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import top.deramertn9527.center.service.listener.TestProducerService;

import javax.jms.Destination;
@Component
public class TestProducerServiceImpl implements TestProducerService {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(Destination destination, String msg){
        System.out.println(Thread.currentThread().getName() + " 向" + destination.toString() + "发送主题：" + msg);
        try {
            jmsTemplate.send(destination, session -> session.createTextMessage(msg));
        } catch (JmsException e) {
            try {
                throw new Exception(e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}
