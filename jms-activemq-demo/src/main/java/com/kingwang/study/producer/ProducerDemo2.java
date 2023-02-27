package com.kingwang.study.producer;

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
 * <p>
 * 使用Queue模式的特点：
 * 1. 1条消息只能被1个消费者消费
 * 2. 在有多个消费者的情况下，使用轮询的方式消费多条消息
 */
public class ProducerDemo2 {
    // 1. activemq 的地址
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    // 2. destination 目的地
    public static final String QUEUE_NAME = "my_queue_1";

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
        Destination queue = session.createQueue(QUEUE_NAME);
        // 8. 创建生产者
        MessageProducer producer = session.createProducer(queue);
        // 9. 创建消息
        for (int i = 0; i < 10; i++) {
            TextMessage textMessage = session.createTextMessage("Hello activemq-" + i);

            // 10. 发送消息（同步阻塞方式）
            producer.send(textMessage);
        }
        // 11. 关闭连接
        connection.close();

        System.out.println("消息已发送！");
    }
}
