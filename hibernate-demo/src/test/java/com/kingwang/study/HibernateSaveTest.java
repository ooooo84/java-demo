package com.kingwang.study;

import com.kingwang.study.entity.Course;
import com.kingwang.study.entity.Customer;
import com.kingwang.study.entity.CustomerOrder;
import com.kingwang.study.entity.Student;
import com.kingwang.study.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class HibernateSaveTest {
    public static void main(String[] args) {
        basicUsage();

        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();

        oneToManyCascadeSave(sessionFactory);

        manyToManyCascadeSave(sessionFactory);
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
     * 一对多级联保存的使用
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

    /**
     * 多对多级联保存的使用
     *
     * @param sessionFactory
     */
    private static void manyToManyCascadeSave(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Course newCourse = new Course();
            newCourse.setId(UUID.randomUUID().toString());
            newCourse.setName("Java");

            Student newStudent = new Student();
            newStudent.setId(UUID.randomUUID().toString());
            newStudent.setName("Charlie");

            Set<Course> courses = new HashSet<>();
            courses.add(newCourse);
            newStudent.setCourses(courses);

            session.save(newCourse);
            session.save(newStudent);

            session.beginTransaction().commit();
        }
    }
}
