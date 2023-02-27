package com.kingwang.study.consumer.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.IOException;
import java.util.Objects;

/**
 * Topic模式消费者
 * <p>
 * Topic模式的特点：
 * 1. 无法消费已经存在于Topic中的历史消息
 * 2. 每条消息会被所有的的消费者消费
 * 3. 可以实现发布订阅模式
 */
public class ConsumerDemo5 {
    // 1. activemq 的地址
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    // 2. destination 目的地
    public static final String TOPIC_NAME = "my_topic_1";

    public static void main(String[] args) throws JMSException, IOException {
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
        // 8. 创建消费者
        MessageConsumer consumer = session.createConsumer(topic);
        // 9. 消费消息（异步非阻塞方式，通过设置消息监听器实现）
        consumer.setMessageListener(message -> {
            if (Objects.nonNull(message) && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;

                try {
                    System.out.println(Thread.currentThread().getId() + "接收到的消息：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("==========没有接收到消息时也会执行到这里==========");
        System.in.read();
    }
}
