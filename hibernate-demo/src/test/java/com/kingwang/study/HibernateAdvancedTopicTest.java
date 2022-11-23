package com.kingwang.study;

import com.kingwang.study.entity.Customer;
import com.kingwang.study.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.UUID;

public class HibernateAdvancedTopicTest {
    public static void main(String[] args) {
        objectStateTransfer();

        level1Cache();

        threadLocalSession();
    }

    /**
     * 使用ThreadLocal管理Session对象，模拟Service业务方法
     */
    private static void threadLocalSession() {
        Session session = HibernateUtil.getSession();

        Transaction tx = session.beginTransaction();

        try {
            Customer c1 = new Customer();
            c1.setId(UUID.randomUUID().toString());
            c1.setName("C1");

            saveCustomer(c1);

//            int i = 100 / 0;

            Customer c2 = new Customer();
            c2.setId(UUID.randomUUID().toString());
            c2.setName("C2");

            saveCustomer(c2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }

        // 使用ThreadLocal管理Session对象时，为保证本线程内其它方法也可以使用session对象，所以不需要关闭
    }

    /**
     * 使用ThreadLocal管理Session对象，模拟Dao层
     *
     * @param customer
     */
    private static void saveCustomer(Customer customer) {
        Session session = HibernateUtil.getSession();

        session.save(customer);

        // 使用ThreadLocal管理Session对象时，Dao层不要关闭session对象！
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
