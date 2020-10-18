package com.ming.util;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 获取连接，并且把连接与当前线程绑定
 */
public class ConnectionUtil {


    private ThreadLocal<Connection> t = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程的连接
     * @param
     * @return
     */
    public Connection getThredConnection(){
       try{
           Connection conn=t.get();
           if(conn==null){
               conn=dataSource.getConnection();
               t.set(conn);
           }
           return  conn;
       }catch (Exception e){
           throw new RuntimeException(e);
       }
    }

    /**
     * 解绑连接与线程
     */
    public void remove(){
        t.remove();
    }
}
