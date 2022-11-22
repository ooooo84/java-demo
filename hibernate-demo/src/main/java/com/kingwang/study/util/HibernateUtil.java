package com.kingwang.study.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {
    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration().configure();

        return configuration.buildSessionFactory();
    }
}
