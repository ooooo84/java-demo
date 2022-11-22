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

//        lazyLoadingOneToMany(sessionFactory);

//        lazyLoadingManyToOne(sessionFactory);

        lazyLoadingManyToMany(sessionFactory);
    }

    private static void lazyLoadingManyToMany(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Course course = session.get(Course.class, "27bed758-2d0f-45ac-a49a-7efc38890346");

            System.out.println(course);

            Set<Student> students = course.getStudents();
            System.out.println(students);
        }
    }

    private static void lazyLoadingManyToOne(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            CustomerOrder order = session.get(CustomerOrder.class, "d22fa4c9-91a4-41eb-86c9-a975d53d6b0c");
            System.out.println(order);

            Customer customer = order.getCustomer();
            System.out.println(customer);
        }
    }

    private static void lazyLoadingOneToMany(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Customer customer = session.get(Customer.class, "c262cd49-4d27-472d-9577-06420d24dcf9");
            System.out.println(customer);

            Set<CustomerOrder> orders = customer.getOrders();
            System.out.println(orders);
        }
    }
}
