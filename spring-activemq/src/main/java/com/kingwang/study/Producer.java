package com.kingwang.study;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Producer {
    @Resource
    private JmsTemplate jmsTemplate;

    /**
     * 发送消息
     *
     * @param message 消息的内容
     */
    public void send(String message) {
        jmsTemplate.send(session -> session.createTextMessage(message));
    }
}
