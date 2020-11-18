package com.ming.ui;

import com.ming.service.IListInjection;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟表现层，用于调用业务层实际开发中就servlet
 */
public class Client {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IListInjection listInjection = (IListInjection) context.getBean("listInjection");
        listInjection.saveInjection();

    }
}
