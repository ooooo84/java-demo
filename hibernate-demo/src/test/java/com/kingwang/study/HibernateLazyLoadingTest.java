package com.kingwang.study;

import com.kingwang.study.entity.Course;
import com.kingwang.study.entity.Customer;
import com.kingwang.study.entity.CustomerOrder;
import com.kingwang.study.entity.Student;
import com.kingwang.study.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Set;

public class HibernateLazyLoadingTest {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();

        lazyLoadingOneToMany(sessionFactory);

        lazyLoadingManyToOne(sessionFactory);

        lazyLoadingManyToMany(sessionFactory);

        lazyLoadingProperties(sessionFactory);
    }

    /**
     * 类级别延迟加载（无法实现）
     *
     * @param sessionFactory
     */
    private static void lazyLoadingProperties(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            // 不支持延迟加载
//            Customer customer = session.get(Customer.class, "7332961d-b501-45fd-8dcf-3c4a4cba5c43");

            // 支持延迟加载
            Customer customer = session.load(Customer.class, "7332961d-b501-45fd-8dcf-3c4a4cba5c43");

            System.out.println();
            System.out.println(customer.getName());
        }
    }

    private static void lazyLoadingManyToMany(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Course course = session.get(Course.class, "ec33a73d-7493-4e9a-94ee-07b8efc3e421");

            System.out.println(course);

            Set<Student> students = course.getStudents();
            System.out.println(students);
        }
    }

    private static void lazyLoadingManyToOne(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            CustomerOrder order = session.get(CustomerOrder.class, "7acd8bfd-9c4b-46ae-90ad-2e9cafd7f687");
            System.out.println(order);

            Customer customer = order.getCustomer();
            System.out.println(customer);
        }
    }

    private static void lazyLoadingOneToMany(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Customer customer = session.get(Customer.class, "4f9bd906-bf2b-4898-ab78-d27df8d71290");
            System.out.println(customer);

            Set<CustomerOrder> orders = customer.getOrders();
            System.out.println(orders);
        }
    }
}
