package com.ming.util.aop;

import com.ming.service.IAccountService;
import com.ming.util.TransacationManger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AccountFactory {
    private IAccountService accountService;

    private TransacationManger tx;

    public final void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    public void setTx(TransacationManger tx) {
        this.tx = tx;
    }

    public IAccountService getAccountService(){
        return (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result=null;
                try{
                    //1.开启事物
                    tx.createTransaction();
                    result=method.invoke(accountService,args);
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
