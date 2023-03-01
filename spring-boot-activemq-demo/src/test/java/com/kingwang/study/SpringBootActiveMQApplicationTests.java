package com.kingwang.study;

import com.kingwang.study.producer.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class SpringBootActiveMQApplicationTests {
    @Resource
    private Producer producer;

    @Test
    void testSendToQueue() {
        producer.sendToQueue("123");
    }

    @Test
    void testSendToTopic() {
        producer.sendToTopic("T123");
    }
}
