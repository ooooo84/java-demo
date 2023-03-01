package com.kingwang.study;

import org.junit.jupiter.api.Test;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;
import java.io.IOException;

@SpringJUnitConfig
@ContextConfiguration("classpath:spring-activemq.xml")
public class TestConsumer {
    @Resource
    private Consumer consumer;

    @Test
    void testConsumer() {
        consumer.receive();
    }

    @Resource
    private DefaultMessageListenerContainer jmsContainer;

    @Test
    void testAsyncConsumer() throws IOException {
        // 由Spring 容器自动启动
        // jmsContainer.start();
        System.out.println("启动消费者...");
        System.in.read();
    }
}
