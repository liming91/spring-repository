package com.ming.service.jdbctemplate.impl;

import com.ming.dao.dbcteplate.IAccountDao;
import com.ming.model.Account;
import com.ming.service.jdbctemplate.IAccountService;

public class AccountServiceImpl implements IAccountService {

    private IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public Account findAccountByName(String accountName) {
        return accountDao.findAccountByName(accountName);
    }


    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        //1.根据名称查询转出账户
        Account sourceAccount = accountDao.findAccountByName(sourceName);
        //2.根据名称查询转入账户
        Account targetAccount = accountDao.findAccountByName(targetName);
        //3.转出账户减钱
        sourceAccount.setMoney(sourceAccount.getMoney() - money);
        //4.转入账户加钱
        targetAccount.setMoney(targetAccount.getMoney() + money);
        //5.更新账户
        accountDao.updateAccount(sourceAccount);
        //int a=2/0;
        accountDao.updateAccount(targetAccount);
    }
}
