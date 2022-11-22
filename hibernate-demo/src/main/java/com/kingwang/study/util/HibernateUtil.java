package com.kingwang.study.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public final class HibernateUtil {
    public static SessionFactory createSessionFactory() {
        // 以下为Hibernate 3/4版本的写法
//        Configuration configuration = new Configuration().configure();
//
//        return configuration.buildSessionFactory();

        // Hibernate 5.x版本写法
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();

        return new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
    }
}
