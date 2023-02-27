package com.kingwang.study.consumer.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;
import java.io.IOException;
import java.util.Objects;

/**
 * Topic模式消费者-发布订阅
 * <p>
 * Topic模式的特点：
 * 1. 无法消费已经存在于Topic中的历史消息
 * 2. 每条消息会被所有的的消费者消费
 * 3. 可以实现发布订阅模式
 */
public class ConsumerDemo7 {
    // 1. activemq 的地址
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    // 2. destination 目的地
    public static final String TOPIC_NAME = "my_topic_2";

    public static void main(String[] args) throws JMSException, IOException {
        // 3. 创建 ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 4. 获得 Connection
        Connection connection = connectionFactory.createConnection();
        // 5. 设置客户端ID
        connection.setClientID("1234");
        // 6. 获取 Session，两个参数分别为是否开启事务和提交方式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 7. 设置 Destination 目的地
        Topic topic = session.createTopic(TOPIC_NAME);
        // 8. 消费消息（发布订阅方式，通过设置消息监听器实现）
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, "s1");
        // 9. 开启连接
        connection.start();
        while (true) {
            Message message = topicSubscriber.receive();

            if (Objects.nonNull(message) && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;

                System.out.println(textMessage.getText());
            }
        }
    }
}
