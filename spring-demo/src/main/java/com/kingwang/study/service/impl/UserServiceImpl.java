package com.kingwang.study.service.impl;

import com.kingwang.study.dao.UserDao;
import com.kingwang.study.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    /**
     * BeanFactory去调用该方法，从容器中获得UserDao实例设置到此处
     *
     * @param userDao
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
        System.out.println("UserDao注入完成！");
    }
}
