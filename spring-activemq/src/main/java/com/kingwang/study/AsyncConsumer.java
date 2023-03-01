package com.kingwang.study;

import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class AsyncConsumer implements MessageListener {
    /**
     * 监听到消息后对消息进行处理
     *
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = ((TextMessage) message);

            System.out.println(textMessage.getText());
        }
    }
}
