package com.kingwang.study;

import com.kingwang.study.dao.UserDao;
import com.kingwang.study.service.UserService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BeanFactoryTest {
    public static void main(String[] args) {
        // 1. 创建工厂对象
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 创建配置读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        // 3. 读取配置文件给工厂
        beanDefinitionReader.loadBeanDefinitions("beans.xml");

        // 4. 根据id获取bean实例对象
        UserService userService = ((UserService) beanFactory.getBean("userService"));
        System.out.println(userService);

        UserDao userDao = (UserDao) beanFactory.getBean("userDao");
        System.out.println(userDao);
    }
}
