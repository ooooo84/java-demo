package com.kingwang.study;

import com.kingwang.study.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BeanFactory 与 ApplicationContext 的关系
 * 1. BeanFactory 是 Spring 的早期接口，称为 Spring 的 Bean 工厂，ApplicationContext 是后期更高级的接口，称为 Spring 容器
 * 2. ApplicationContext 在 BeanFactory 基础上对功能进行了扩展，例如：监听功能、国际化功能等。BeanFactory 的API更偏向底层，ApplicationContext
 * 的API大多数是对这些底层API的封装
 * 3. Bean 创建主要逻辑和功能都被封装在 BeanFactory 中，ApplicationContext 不仅继承了BeanFactory，而且 ApplicationContext
 * 内部还维护着BeanFactory的引用，所以，ApplicationContext 与 BeanFactory 既有继承关系，又有融合关系
 * 4. Bean 的初始化时机不同，原始 BeanFactory 是在首次调用 getBean 时才进行 Bean 的创建，而 ApplicationContext 则是配置文件加载，
 * 容器一创建就将 Bean 都实例化并初始化好
 */
public class ApplicationContextTest {
    public static void main(String[] args) {
        // 常用的ApplicationContext实现类：
        // 1. ClassPathXmlApplicationContext：加载基于类路径下的XML配置的ApplicationContext
        // 2. FileSystemXmlApplicationContext：加载基于指定路径下的XML配置的ApplicationContext
        // 3. AnnotationConfigApplicationContext：加载基于注解配置的ApplicationContext
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = applicationContext.getBean("userService", UserService.class);

        System.out.println(userService);
    }
}
