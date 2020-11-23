package com.ming.util.aop;

import com.ming.util.TransacationManger;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

public class AccountProxy implements InvocationHandler {
    private Object target;
    private TransacationManger tx;

    public void setTarget(Object target) {
        this.target = target;
    }

    public AccountProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       try{
           tx.createTransaction();
           Object result  = method.invoke(this.target, args);
           System.out.println("result==:"+result);
           tx.commitTransaction();
           return result;
       }catch (Exception e){
           tx.rollbackTransaction();
           throw  new RuntimeException(e);
       }finally {
           tx.releaseTransaction();
       }
    }

    public static <T> T createProxy(Object target, Class<T> targetInterface){
        if (!targetInterface.isInterface()) {
            throw new IllegalStateException("targetInterface必须是接口类型!");
        } else if (!targetInterface.isAssignableFrom(target.getClass())) {
            throw new IllegalStateException("target必须是targetInterface接口的实现类!");
        }
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new AccountProxy(target));
    }
}
