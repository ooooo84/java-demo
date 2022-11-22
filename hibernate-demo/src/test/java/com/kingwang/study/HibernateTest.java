package com.kingwang.study;

import com.kingwang.study.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.UUID;

public class HibernateTest {
    public static void main(String[] args) {
        // 1. 创建Configuration对象
        Configuration configuration = new Configuration().configure();

        // 2. 获取SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // 3. 获取Session对象
        try (Session session = sessionFactory.openSession()) {
            Customer newCustomer = new Customer();
            newCustomer.setId(UUID.randomUUID().toString());
            newCustomer.setName("张三");

            session.save(newCustomer);
            Transaction transaction = session.beginTransaction();
            transaction.commit();
        }

        System.out.println(configuration);
    }
}
