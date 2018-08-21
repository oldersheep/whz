package top.deramertn9527.center.service.listener;


import javax.jms.Destination;


public interface TestProducerService {

    void sendMessage(Destination destination, String msg);
}
