package com.ming.service.impl;

import com.ming.dao.AccountDao;
import com.ming.model.Account;
import com.ming.service.IAccountService;
import com.ming.util.TransacationManger;

import java.util.List;

public class AccountServiceImpl_old implements IAccountService {

    //private AccountDao accountDao= (AccountDao) BeanFactory.getBean("accountDao");
    private AccountDao  accountDao;
    private TransacationManger tx;
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTx(TransacationManger tx) {
        this.tx = tx;
    }

    @Override
    public List<Account> findAll() {
       try{
           //1.开启事物
           tx.createTransaction();
           //2.执行操作
           List<Account> accountList= accountDao.findAll();
           //3.提交事物
           tx.commitTransaction();
           //4.返回结果
           return accountList;
       }catch (Exception e){
           //5.回滚事物
           tx.rollbackTransaction();
           throw new RuntimeException(e);
       }finally {
           //6.释放资源
           tx.releaseTransaction();
       }
    }

    @Override
    public Account findById(Integer id) {
        try{
            //1.开启事物
            tx.createTransaction();
            //2.执行操作
            Account account=  accountDao.findById(id);
            //3.提交事物
            tx.commitTransaction();
            //4.返回结果
            return account;
        }catch (Exception e){
            //5.回滚事物
            tx.rollbackTransaction();
            throw new RuntimeException(e);
        }finally {
            //6.释放资源
            tx.releaseTransaction();
        }
    }

    @Override
    public void saveAccount(Account account) {
        try{
            //1.开启事物
            tx.createTransaction();
            //2.执行操作
             accountDao.saveAccount(account);
            //3.提交事物
            tx.commitTransaction();
        }catch (Exception e){
            //4.回滚事物
            tx.rollbackTransaction();
            throw new RuntimeException(e);
        }finally {
            //5.释放资源
            tx.releaseTransaction();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try{
            //1.开启事物
            tx.createTransaction();
            //2.执行操作
            accountDao.updateAccount(account);
            //3.提交事物
            tx.commitTransaction();
        }catch (Exception e){
            //4.回滚事物
            tx.rollbackTransaction();
            throw new RuntimeException(e);
        }finally {
            //5.释放资源
            tx.releaseTransaction();
        }
    }

    @Override
    public void deleteAccount(Integer id) {
        try{
            //1.开启事物
            tx.createTransaction();
            //2.执行操作
            accountDao.deleteAccount(id);
            //3.提交事物
            tx.commitTransaction();
        }catch (Exception e){
            //4.回滚事物
            tx.rollbackTransaction();
            throw new RuntimeException(e);
        }finally {
            //5.释放资源
            tx.releaseTransaction();
        }
    }

    /**
     *
     * @param sourceName 转出账户  更新减钱
     * @param targetName 转入账户  更新加钱
     * @param money  转账金额
     */
    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        System.out.println(tx);
        try{
            //1.开启事物
            tx.createTransaction();
            //2.执行操作
            Account sourceAccount =accountDao.findAccountByName(sourceName);
            System.out.println(sourceAccount);
            sourceAccount.setMoney(sourceAccount.getMoney()-money);
            accountDao.updateAccount(sourceAccount);
            Account targetAccount =accountDao.findAccountByName(targetName);
            targetAccount.setMoney(targetAccount.getMoney()+money);
            accountDao.updateAccount(targetAccount);
            //3.提交事物
            tx.commitTransaction();
        }catch (Exception e){
            //4.回滚事物
            tx.rollbackTransaction();
            e.printStackTrace();
        }finally {
            //5.释放资源
            tx.releaseTransaction();
        }
    }




}
