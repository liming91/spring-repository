package com.ming.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 事物管理
 */
@Component
@Aspect
public class TransacationManger{
   private static Logger logger = Logger.getLogger(TransacationManger.class);
    //注入当前线程的连接
    @Autowired
    private  ConnectionThredUtil connectionUtil;

    @Pointcut("execution(* com.ming.service.impl.*.*(..))")
    public void pointCut(){

    }
    /**
     * 开启事物
     */
    //@Before("pointCut()")
    public  void createTransaction() {
        try {
            connectionUtil.getThredConnection().setAutoCommit(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交事物
     */
   // @AfterReturning("pointCut()")
    public  void commitTransaction() {
        try {
            connectionUtil.getThredConnection().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 回滚事物
     */
    //@AfterThrowing("pointCut()")
    public  void rollbackTransaction() {
        try {
            connectionUtil.getThredConnection().rollback();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 释放资源
     */
    //@After("pointCut()")
    public  void releaseTransaction() {
        try {
            connectionUtil.getThredConnection().close();//关闭连接，释放资源到连接池
            connectionUtil.remove();//解绑线程与连接
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 环绕通知
     * @param point
     *
     */
    @Around("pointCut()")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        try {
            logger.info("环绕通知的前置通知执行了...");
            connectionUtil.getThredConnection().setAutoCommit(false);
            Object object = point.proceed(point.getArgs());
            logger.info("环绕通知的后置通知执行了...");
            connectionUtil.getThredConnection().commit();
            return object;
        } catch (Throwable throwable) {
            logger.info("环绕通知的异常通知执行了...");
            connectionUtil.getThredConnection().rollback();
            throw new RuntimeException(throwable);
        }finally {
            logger.info("环绕通知的最终通知执行了...");
            connectionUtil.getThredConnection().close();
        }
    }
}
