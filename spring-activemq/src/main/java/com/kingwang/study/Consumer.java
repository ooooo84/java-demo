package com.kingwang.study;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Consumer {
    @Resource
    private JmsTemplate jmsTemplate;

    public void receive() {
        while (true) {
            String content = (String) jmsTemplate.receiveAndConvert();

            System.out.println("接收到的消息：" + content);
        }
    }
}
