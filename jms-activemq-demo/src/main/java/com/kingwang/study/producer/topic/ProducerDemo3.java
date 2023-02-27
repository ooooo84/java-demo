package com.kingwang.study.producer.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 基于JMS实现的消息生产者
 * ActiveMQ Topic
 */
public class ProducerDemo3 {
    // 1. activemq 的地址
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    // 2. destination 目的地
    public static final String TOPIC_NAME = "my_topic_1";

    public static void main(String[] args) throws JMSException {
        // 3. 创建 ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 4. 获得 Connection
        Connection connection = connectionFactory.createConnection();
        // 5. 开启连接
        connection.start();
        // 6. 获取 Session，两个参数分别为是否开启事务和提交方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 7. 设置 Destination 目的地
        Destination topic = session.createTopic(TOPIC_NAME);
        // 8. 创建生产者
        MessageProducer producer = session.createProducer(topic);
        // 9. 创建消息
        for (int i = 0; i < 10; i++) {
            TextMessage textMessage = session.createTextMessage("Hello topic-" + i);

            // 10. 发送消息（同步阻塞方式）
            producer.send(textMessage);
        }
        // 11. 关闭连接
        connection.close();

        System.out.println("消息已发送！");
    }
}
