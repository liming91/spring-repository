package com.ming.util;

import java.sql.*;

/**
 * 程序的耦合
 */
public class JdbcUtil {
    public static void main(String[] args) throws Exception{
        //1.注册驱动
        //DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());
        //读取配置文件中的key
        String key =  RedProperties.getProperties("jdbc").toString();
        System.out.println(key);
        //利用工厂模式获取配置文件的key
        //String key=BeanFactory.getBean("jdbc").toString();
        Class.forName(key);
        //2.获取连接
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/test","liming","liming");
        //3.获取操作数据库的预处理对象
        PreparedStatement psm = con.prepareStatement("select * from account ");
        //4.执行sql语句，得到结果集
        ResultSet result = psm.executeQuery();
        //5.封装结果集或遍历结果集
        while (result.next()){
            System.out.println(result.getString("name"));
        }
        //6.释放资源
        result.close();
        psm.close();
        con.close();
    }
}
