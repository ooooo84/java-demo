package com.kingwang.study.factory;

import com.kingwang.study.dao.UserDao;
import com.kingwang.study.dao.impl.UserDaoImpl;

public class MyBeanFactory1 {
    public static UserDao userDao(String username) {
        return new UserDaoImpl(username);
    }
}
