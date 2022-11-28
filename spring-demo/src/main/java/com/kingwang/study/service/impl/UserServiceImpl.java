package com.kingwang.study.service.impl;

import com.kingwang.study.dao.UserDao;
import com.kingwang.study.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    private List<String> stringList;

    private Set<String> stringSet;

    private Map<String, String> stringMap;

    private Properties properties;

    public UserServiceImpl() {
    }

    public UserServiceImpl(String name) {
    }

    /**
     * BeanFactory去调用该方法，从容器中获得UserDao实例设置到此处
     *
     * @param userDao
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
        System.out.println("UserDao注入完成！");
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public void setStringSet(Set<String> stringSet) {
        this.stringSet = stringSet;
    }

    public void setStringMap(Map<String, String> stringMap) {
        this.stringMap = stringMap;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
