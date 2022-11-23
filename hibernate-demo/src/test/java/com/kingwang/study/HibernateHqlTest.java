package com.kingwang.study;

import com.kingwang.study.entity.Customer;
import com.kingwang.study.entity.CustomerOrder;
import com.kingwang.study.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * HQL：Hibernate Query Language。是Hibernate框架提供的一种查询机制，它和SQL类似，不同的是HQL是面向对象的查询语言，
 * 让开发者能够以面向对象的思想来编写查询语句，对Java编程是一种较为友好的方式。
 * HQL不能直接参与数据库的交互，它是中间层语言。执行过程为：Java -> HQL -> Hibernate -> SQL -> DB
 * HQL只能完成查询、修改和删除操作，无法执行新增操作。
 */
public class HibernateHqlTest {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();

        queryObjects(sessionFactory);

        paginationQuery(sessionFactory);

        queryWithCriteria(sessionFactory);

        orderByFields(sessionFactory);

        projectionQuery(sessionFactory);

        queryParameters(sessionFactory);

        cascadeQuery(sessionFactory);

        aggregationQuery(sessionFactory);

        projectionQueryWithStrongType(sessionFactory);

        innerJoinQuery(sessionFactory);

        leftJoinQuery(sessionFactory);

        rightJoinQuery(sessionFactory);
    }

    private static void rightJoinQuery(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "select c.name as cname, o.name as oname from CustomerOrder o right join o.customer c";

            Query<Object[]> query = session.createQuery(hql);
            List<Object[]> list = query.list();
            for (Object[] row : list) {
                for (Object field : row) {
                    System.out.print(field + "\t");
                }

                System.out.println();
            }
        }
    }

    private static void leftJoinQuery(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "select c.name as cname, o.name as oname from Customer c left join c.orders o";

            Query<Object[]> query = session.createQuery(hql);
            List<Object[]> list = query.list();
            for (Object[] row : list) {
                for (Object field : row) {
                    System.out.print(field + "\t");
                }

                System.out.println();
            }
        }
    }

    private static void innerJoinQuery(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "select c.name as cname, o.name as oname from Customer c inner join c.orders o";

            Query<Object[]> query = session.createQuery(hql);
            List<Object[]> list = query.list();
            for (Object[] row : list) {
                for (Object field : row) {
                    System.out.print(field + "\t");
                }

                System.out.println();
            }
        }
    }

    private static void projectionQueryWithStrongType(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            // 注意：需要在CustomerOrder对象中添加相应参数的构造方法
            String hql = "select new CustomerOrder(id, name) from CustomerOrder";

            Query<CustomerOrder> query = session.createQuery(hql);

            List<CustomerOrder> list = query.list();
            for (CustomerOrder order : list) {
                System.out.print(order);
            }
        }
    }

    /**
     * 使用聚合函数查询
     *
     * @param sessionFactory
     */
    private static void aggregationQuery(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "select COUNT(*) from Customer";

            Query<Long> query = session.createQuery(hql);

            Long count = query.uniqueResult();
            System.out.println(count);
        }
    }

    /**
     * 级联查询
     *
     * @param sessionFactory
     */
    private static void cascadeQuery(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Customer where name = :name";

            Query<Customer> query1 = session.createQuery(hql);
            query1.setParameter("name", "张三");

            Customer customer = query1.uniqueResult();

            String hql2 = "from CustomerOrder where customer = :customer";
            Query<CustomerOrder> query2 = session.createQuery(hql2);
            query2.setParameter("customer", customer);
            List<CustomerOrder> list = query2.list();
            for (CustomerOrder order : list) {
                System.out.println(order);
            }
        }
    }

    /**
     * HQL语句中的占位符使用:开头
     *
     * @param sessionFactory
     */
    private static void queryParameters(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Customer where name = :name";

            Query<Customer> query = session.createQuery(hql);
            query.setParameter("name", "张三");

            List<Customer> list = query.list();
            for (Customer customer : list) {
                System.out.println(customer);
            }
        }
    }

    /**
     * 查询特定字段
     * 查询单个字段时可以直接使用对应字段的Java类型进行接收
     * 查询多个字段时，返回的结果为由Object[]组成的ArrayList，类似二维数组
     *
     * @param sessionFactory
     */
    private static void projectionQuery(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "select id, name from CustomerOrder";

            Query<Object[]> query = session.createQuery(hql);
            List<Object[]> list = query.list();
            for (Object[] row : list) {
                for (Object field : row) {
                    System.out.println(field + "\t");
                }

                System.out.println();
            }
        }
    }

    /**
     * HQL 排序
     *
     * @param sessionFactory
     */
    private static void orderByFields(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Customer order by name desc";

            Query<Customer> query = session.createQuery(hql);
            List<Customer> list = query.list();
            for (Customer customer : list) {
                System.out.println(customer);
            }
        }
    }

    /**
     * Where条件查询
     * HQL直接追加 where 关键字作为查询条件，与SQL相同
     *
     * @param sessionFactory
     */
    private static void queryWithCriteria(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Customer where name like '张%'";

            Query<Customer> query = session.createQuery(hql);
            List<Customer> list = query.list();
            for (Customer customer : list) {
                System.out.println(customer);
            }
        }
    }

    /**
     * 分页查询
     * HQL分页查询可以通过调用query方法来完成
     * 使用setFirstResult()方法设置起始的下标，使用setMaxResults()方法设置截取长度
     *
     * @param sessionFactory
     */
    private static void paginationQuery(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Customer";

            Query<Customer> query = session.createQuery(hql);
            query.setFirstResult(0).setMaxResults(2);

            List<Customer> list = query.list();
            for (Customer customer : list) {
                System.out.println(customer);
            }
        }
    }

    /**
     * HQL查询
     * 使用HQL进行查询，from 关键字后面不能写表名，必须写表对应的实体名。select 关键字可以省略，省略时即获取所有字段。
     *
     * @param sessionFactory
     */
    private static void queryObjects(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from Customer";

            Query<Customer> query = session.createQuery(hql);
            List<Customer> list = query.list();
            for (Customer customer : list) {
                System.out.println(customer);
            }
        }
    }
}
