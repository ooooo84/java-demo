package com.kingwang.study;

import com.kingwang.study.entity.Customer;
import com.kingwang.study.util.HibernateUtil;
import org.hibernate.Session;

import java.util.UUID;

public class HibernateAdvancedTopicTest {
    public static void main(String[] args) {
//        objectStateTransfer();

        level1Cache();
    }

    /**
     * Hibernate的一级缓存机制
     */
    private static void level1Cache() {
        try (Session session = HibernateUtil.getSession()) {
            Customer customer1 = session.get(Customer.class, "7332961d-b501-45fd-8dcf-3c4a4cba5c43");

            customer1.setName("刘六666");

            // 没有必要执行update语句，因为持久态的对象已经能够更新数据库中的数据了
            // session.update(customer1);

            session.beginTransaction().commit();

            // 第二次访问相同的对象时，不再从数据库中查询，直接从 Session 的一级缓存中获取
            Customer customer2 = session.get(Customer.class, "7332961d-b501-45fd-8dcf-3c4a4cba5c43");
            System.out.println(customer2);

            customer2.setName("刘六");

            // 将 customer2 对象移出一级缓存，不会生成update语句更新数据库
            session.evict(customer2);

            session.beginTransaction().commit();
        }
    }

    /**
     * Hibernate 对象状态迁移
     */
    private static void objectStateTransfer() {
        // 新建的 customer 对象的状态为临时状态
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setName("刘六");
        try (Session session = HibernateUtil.getSession()) {
            session.save(customer);
            // 执行 save 方法后，customer对象的状态变成了持久化状态

            session.beginTransaction().commit();
        }

        // 当 session 关闭后，customer 对象的状态变成了游离状态
        System.out.println(customer);
    }
}
