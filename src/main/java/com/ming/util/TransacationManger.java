package com.ming.util;

import javax.sql.DataSource;

public class TransacationManger {

    private static ConnectionUtil connectionUtil;

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil=connectionUtil;
    }

    /**
     * 开启事物
     *
     */
    public static void createTransaction(){
        try{
            connectionUtil.getThredConnection().setAutoCommit(false);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交事物
     *
     */
    public static void commitTransaction(){
        try{
            connectionUtil.getThredConnection().commit();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 回滚事物
     *
     */
    public  static void rollbackTransaction(){
        try{
            connectionUtil.getThredConnection().rollback();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 释放资源
     *
     */
    public  static void releaseTransaction(){
        try{
            connectionUtil.getThredConnection().close();//释放资源到线程池
            connectionUtil.remove();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
