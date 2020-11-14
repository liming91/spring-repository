package com.ming.util;

import org.springframework.beans.factory.BeanFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * 读取properties配置文件
 */
public class RedProperties {

    private static Properties properties;

    //使用静态对象为properties赋值
    static {
        try {
            properties = new Properties();
            //获取properties的文件流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //读取流
            properties.load(in);
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties文件错误");
        }
    }
    public static String getProperties(String name) {
        return properties.getProperty(name);
    }


}
