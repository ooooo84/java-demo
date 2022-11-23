package com.kingwang.study;

import com.kingwang.study.entity.Customer;
import com.kingwang.study.entity.CustomerOrder;
import com.kingwang.study.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Qbc：Query by Criteria，即使用Criteria对象进行查询
 * 从Hibernate 5.2开始，Criteria对象被废弃，建议使用JPA的Criteria，以下为使用JPA的方式
 */
public class HibernateQbcTest {
    public static void main(String[] args) {
        fullTableQuery();

        criteriaQuery();
    }

    private static void criteriaQuery() {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<CustomerOrder> cr = cb.createQuery(CustomerOrder.class);

            Root<CustomerOrder> root = cr.from(CustomerOrder.class);

            cr.select(root).where(cb.equal(root.get("name"), "订单1"));
            Query<CustomerOrder> query = session.createQuery(cr);
            List<CustomerOrder> list = query.list();
            for (CustomerOrder order : list) {
                System.out.println(order);
            }
        }
    }

    private static void fullTableQuery() {
        try (Session session = HibernateUtil.getSession()) {
            CriteriaQuery<Customer> cr = session.getCriteriaBuilder().createQuery(Customer.class);

            Root<Customer> root = cr.from(Customer.class);
            cr.select(root);

            Query<Customer> query = session.createQuery(cr);
            List<Customer> list = query.list();
            for (Customer customer : list) {
                System.out.println(customer);
            }
        }
    }
}
