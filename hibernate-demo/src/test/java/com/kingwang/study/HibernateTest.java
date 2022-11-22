package com.kingwang.study;

import com.kingwang.study.entity.Customer;
import com.kingwang.study.entity.CustomerOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.UUID;

public class HibernateTest {
    public static void main(String[] args) {
//        basicUsage();

        SessionFactory sessionFactory = createSessionFactory();

        oneToManyCascadeSave(sessionFactory);
    }

    /**
     * 最简易使用示例
     */
    private static void basicUsage() {
        // 1. 创建Configuration对象
        Configuration configuration = new Configuration().configure();

        // 2. 获取SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // 3. 获取Session对象
        try (Session session = sessionFactory.openSession()) {
            Customer newCustomer = new Customer();
            newCustomer.setId(UUID.randomUUID().toString());
            newCustomer.setName("李四");

            session.save(newCustomer);
            Transaction transaction = session.beginTransaction();
            transaction.commit();
        }

        System.out.println("添加Customer完成");
    }

    /**
     * 级联保存的使用
     *
     * @param sessionFactory
     */
    private static void oneToManyCascadeSave(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Customer newCustomer = new Customer();
            newCustomer.setId(UUID.randomUUID().toString());
            newCustomer.setName("朱七");

            CustomerOrder order = new CustomerOrder();
            order.setId(UUID.randomUUID().toString());
            order.setName("订单1");
            order.setCustomer(newCustomer);

            // 保存，多个对象都要保存
            session.save(newCustomer);
            session.save(order);

            session.beginTransaction().commit();
        }
    }

    private static SessionFactory createSessionFactory() {
        // 1. 创建Configuration对象
        Configuration configuration = new Configuration().configure();

        // 2. 获取SessionFactory
        return configuration.buildSessionFactory();
    }
}
