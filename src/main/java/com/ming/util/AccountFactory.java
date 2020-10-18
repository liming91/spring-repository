package com.ming.util;

import com.ming.model.Account;
import com.ming.service.AccountService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AccountFactory {
    private AccountService  accountService;
    private TransacationManger tx;
    public final void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setTx(TransacationManger tx) {
        this.tx = tx;
    }

    public AccountService getAccountService(){
        return (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {
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
