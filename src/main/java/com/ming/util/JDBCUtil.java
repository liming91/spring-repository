package com.ming.util;

import java.sql.*;

/**
 * 程序的耦合
 */
public class JDBCUtil {
    public static void main(String[] args) throws Exception{
        //1.注册驱动
        //DriverManager.deregisterDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/test","liming","liming");
        //3.获取操作数据库的预处理对象
        PreparedStatement preparedStatement = con.prepareStatement("select * from account ");
        //4.执行sql语句，得到结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        //5.封装结果集或遍历结果集
        while (resultSet.next()){
            System.out.println(resultSet.getString("name"));
        }
        //6.释放资源
        resultSet.close();
        preparedStatement.close();
        con.close();
    }
}
