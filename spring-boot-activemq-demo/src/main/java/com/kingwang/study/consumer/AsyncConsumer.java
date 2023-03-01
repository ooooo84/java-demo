package com.kingwang.study.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Component
public class AsyncConsumer {
    /**
     * 使用消息监听器监听消息
     *
     * @param message
     * @throws JMSException
     */
    @JmsListener(destination = "${queue-name}")
    public void receiveFromQueue(Message message) throws JMSException {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;

            System.out.println("接收到的消息为：" + textMessage.getText());
        }
    }

    @JmsListener(destination = "${topic-name}")
    public void receiveFromTopic(Message message) throws JMSException {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;

            System.out.println("接收到的消息为：" + textMessage.getText());
        }
    }
}
