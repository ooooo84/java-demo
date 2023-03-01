package com.kingwang.study;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.annotation.Resource;

@SpringJUnitConfig
@ContextConfiguration("classpath:spring-activemq.xml")
class TestProducer {
    @Resource
    private Producer producer;

    @Test
    void testSend() {
        producer.send("123");
    }
}
