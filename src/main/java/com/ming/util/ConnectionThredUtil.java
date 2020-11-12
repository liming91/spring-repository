package com.ming.util;


import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 获取数据库连接
 */
public class ConnectionThredUtil {

    private  ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public  Connection getThredConnection(){
        try {
            Connection connection  = connectionThreadLocal.get();
            if(connection==null){
                connection= dataSource.getConnection();
                connectionThreadLocal.set(connection);
            }
            return  connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 释放资源
     */
    public void remove(){
        connectionThreadLocal.remove();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


}
