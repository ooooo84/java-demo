package com.kingwang.study.producer;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

@Component
public class Producer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue queue;

    @Resource
    private Topic topic;

    /**
     * 使用 JmsMessagingTemplate 向队列发送消息
     *
     * @param message
     */
    public void sendToQueue(String message) {
        jmsMessagingTemplate.convertAndSend(queue, message);
    }

    /**
     * 使用 JmsMessagingTemplate 向Topic发送消息
     *
     * @param message
     */
    public void sendToTopic(String message) {
        jmsMessagingTemplate.convertAndSend(topic, message);
    }
}
