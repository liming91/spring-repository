package com.ming.util;

import org.apache.log4j.Logger;

/**
 * 事物管理
 */
public class TransacationManger {
   private static Logger logger = Logger.getLogger(TransacationManger.class);
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
            logger.info("开启事物");
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
            logger.info("提交事物");
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
            logger.info("回滚事物");
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
            logger.debug("释放资源");
            connectionUtil.getThredConnection().close();//关闭连接，释放资源到连接池
            connectionUtil.remove();//解绑线程与连接
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
