package com.kingwang.study.producer.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
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
            TextMessage message = session.createTextMessage("Hello topic-" + i);

            // 指定单条Message的目的地
            message.setJMSDestination(topic);
            // 设置单条消息是否持久化，未持久化的消息保存在内存中，一旦重启服务器会丢失
            message.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
            // 设置单条消息的超时时间，超过该时间的消息会被丢弃，但不保证消费者一定不会消费到过期的消息
            message.setJMSExpiration(3000L);
            // 设置消息ID
            message.setJMSMessageID("1234");

            // 10. 发送消息（同步阻塞方式）
            producer.send(message);
        }
        // 11. 关闭连接
        connection.close();

        System.out.println("消息已发送！");
    }
}
