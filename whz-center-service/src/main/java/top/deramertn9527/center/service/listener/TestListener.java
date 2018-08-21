package top.deramertn9527.center.service.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@Component
@Slf4j
public class TestListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        if(message != null){
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("接收到消息---test---"+textMessage.getText());
            } catch (JMSException e) {
                log.error("TestListener JMSException ", e);
            }
        }
    }
}
