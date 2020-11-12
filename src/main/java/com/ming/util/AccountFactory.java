package com.ming.util;

import com.ming.service.IAccountService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AccountFactory {
    private IAccountService  IAccountService;
    private TransacationManger tx;
    public final void setIAccountService(IAccountService IAccountService) {
        this.IAccountService = IAccountService;
    }

    public void setTx(TransacationManger tx) {
        this.tx = tx;
    }

    public IAccountService getIAccountService(){
        return (IAccountService) Proxy.newProxyInstance(IAccountService.getClass().getClassLoader(), IAccountService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result=null;
                try{
                    //1.开启事物
                    tx.createTransaction();
                    result=method.invoke(IAccountService,args);
                    //3.提交事物
                    tx.commitTransaction();
                   return  result;
                }catch (Exception e){
                    //4.回滚事物
                    tx.rollbackTransaction();
                    throw new RuntimeException(e);
                }finally {
                    //5.释放资源
                    tx.releaseTransaction();
                }
            }
        });
    }
}
