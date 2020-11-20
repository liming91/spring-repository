package com.ming.util;

/**
 * 事物管理
 */
public class TransacationManger {

    //注入当前线程的连接
    private static ConnectionThredUtil connectionUtil;

    public void setConnectionUtil(ConnectionThredUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    /**
     * 开启事物
     */
    public static void createTransaction() {
        try {
            connectionUtil.getThredConnection().setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交事物
     */
    public static void commitTransaction() {
        try {
            connectionUtil.getThredConnection().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 回滚事物
     */
    public static void rollbackTransaction() {
        try {
            connectionUtil.getThredConnection().rollback();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 释放资源
     */
    public static void releaseTransaction() {
        try {
            connectionUtil.getThredConnection().close();//关闭连接，释放资源到连接池
            connectionUtil.remove();//解绑线程与连接
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
