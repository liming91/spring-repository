package com.ming.util;


import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 获取数据库连接
 */
public class ConnectionThredUtil {
    //将连接与当前线程绑定
    private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 返回当前线程的连接
     *
     * @return
     */
    public Connection getThredConnection() {
        try {
            Connection connection = connectionThreadLocal.get();
            if (connection == null) {
                connection = dataSource.getConnection();
                connectionThreadLocal.set(connection);
            }
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 释放资源
     */
    public void remove() {
        connectionThreadLocal.remove();
    }

}
