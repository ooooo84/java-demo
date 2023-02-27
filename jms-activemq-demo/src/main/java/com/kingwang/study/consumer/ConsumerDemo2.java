package com.kingwang.study.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Objects;

public class ConsumerDemo2 {
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
        // 8. 创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        // 9. 消费消息（同步阻塞方式）
        Message message = consumer.receive();
        if (Objects.nonNull(message) && message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;

            System.out.println("接收到的消息：" + textMessage.getText());
        }

        connection.close();

        System.out.println("==========只有接收到消息时才会执行到这里==========");
    }
}
