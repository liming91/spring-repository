package com.ming.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 工厂模式解耦
 *
 * 创建bean对象的工厂
 */
public class BeanFactory {

    //定义一个properties
    private static Properties properties;
    //定义一个容器用来存放对象
    static Map<String,Object> beans;
    //定义静态代码块
    static {
       try {
           properties = new Properties();
           InputStream in= BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
           //加载流文件对象
           properties.load(in);
           Enumeration enumeration=properties.keys();
           beans=new HashMap<String,Object>();
           while (enumeration.hasMoreElements()){
               //获取配置文件所有的key
              String keys=enumeration.nextElement().toString();
              String benaPath=properties.getProperty(keys);
              //根据反射创建对象
               Object value= Class.forName(benaPath).newInstance();
               beans.put(keys,value);
           }

       }catch (Exception e){
           throw new ExceptionInInitializerError("初始化properties文件错误");
       }
    }

    /**
     * 根据beanName获取Bean
     * @param beanName
     * @return
     */
//    public static Object getBean(String beanName){
//        Object bean=null;
//        try{
//            String beanPath= properties.getProperty(beanName);
//            bean=Class.forName(beanPath).newInstance();//每次调用默认构造函数创建对象
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return bean;
//    }

    public static Object getBean(String beanName){
        return beans.get(beanName);
    }
}
