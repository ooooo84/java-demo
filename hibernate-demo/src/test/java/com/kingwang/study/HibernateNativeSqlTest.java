package com.kingwang.study;

import com.kingwang.study.entity.CustomerOrder;
import com.kingwang.study.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

/**
 * 原生SQL查询（比较少用）
 */
public class HibernateNativeSqlTest {
    public static void main(String[] args) {
        objectArrayQuery();

        strongTypeQuery();
    }

    private static void strongTypeQuery() {
        try (Session session = HibernateUtil.getSession()) {
            NativeQuery sqlQuery = session.createSQLQuery("SELECT * FROM cust_order WHERE customer_id = :cid");

            // 指定强类型
            sqlQuery.addEntity(CustomerOrder.class);

            sqlQuery.setParameter("cid", "f72e7be2-8713-466b-8c77-98027789f1c8");

            List<CustomerOrder> list = sqlQuery.list();
            for (CustomerOrder order : list) {
                System.out.println(order);
            }
        }
    }

    private static void objectArrayQuery() {
        try (Session session = HibernateUtil.getSession()) {
            NativeQuery sqlQuery = session.createSQLQuery("SELECT * FROM cust_order WHERE customer_id = :cid");

            sqlQuery.setParameter("cid", "f72e7be2-8713-466b-8c77-98027789f1c8");

            List<Object[]> list = sqlQuery.list();
            for (Object[] row : list) {
                for (Object column : row) {
                    System.out.print(column + "\t");
                }

                System.out.println();
            }
        }
    }
}
