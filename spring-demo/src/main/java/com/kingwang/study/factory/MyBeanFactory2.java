package com.kingwang.study.factory;

import com.kingwang.study.dao.UserDao;
import com.kingwang.study.dao.impl.UserDaoImpl;

public class MyBeanFactory2 {
    public UserDao userDao() {
        return new UserDaoImpl();
    }
}
