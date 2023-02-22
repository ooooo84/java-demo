package com.kingwang.study.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 基于JMS实现的消息生产者
 */
public class ProducerDemo1 {
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
        TextMessage textMessage = session.createTextMessage("Hello activemq!");
//        textMessage.setText("Hello activemq!"));
        // 10. 发送消息
        producer.send(textMessage);
        // 11. 关闭连接
        connection.close();

        System.out.println("消息已发送！");
    }
}
