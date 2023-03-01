package com.kingwang.study.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;
import javax.jms.Topic;

@EnableJms
@Configuration
public class JmsConfig {
    @Value("${queue-name}")
    private String queueName;

    @Value("${topic-name}")
    private String topicName;

    /**
     * 注入队列的Bean
     *
     * @return
     */
    @Bean
    public Queue queue() {
        return new ActiveMQQueue(queueName);
    }

    /**
     * 注入Topic的Bean
     *
     * @return
     */
    @Bean
    public Topic topic() {
        return new ActiveMQTopic(topicName);
    }
}
