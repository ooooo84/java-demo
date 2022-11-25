package com.kingwang.study.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public final class HibernateUtil {
    /**
     * SessionFactory 针对每个数据库有一个即可
     */
    private static SessionFactory INSTANCE = null;

    public static synchronized SessionFactory createSessionFactory() {
        if (INSTANCE != null) {
            return INSTANCE;
        }

        // 以下为Hibernate 3/4版本的写法
//        Configuration configuration = new Configuration().configure();
//
//        return configuration.buildSessionFactory();

        // Hibernate 5.x版本写法
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();

        INSTANCE = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();

        return INSTANCE;
    }

    public static Session getSession() {
        if (INSTANCE == null) {
            createSessionFactory();
        }

        // 未使用ThreadLocal管理Session对象时，使用下面的命令获取Session
        return INSTANCE.openSession();

        // 启用ThreadLocal管理Session对象时，使用下面的命令获取Session
//        return INSTANCE.getCurrentSession();
    }
}
