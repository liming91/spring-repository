package com.ming.service.impl;

import com.ming.dao.IAccountDao;
import com.ming.model.Account;
import com.ming.service.IAccountService;
import com.ming.util.TransacationManger;

import java.util.List;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {
    //最早的写法
    //private IAccountDao accountDao = new AccountDaoImpl();
    //BeanFactory读取配置的bean.properties的dao
    //private IAccountDao accountDao= (IAccountDao) BeanFactory.getBean("accountDao");
    private IAccountDao accountDao;
    private TransacationManger tx;
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTx(TransacationManger tx) {
        this.tx = tx;
    }

    @Override
    public List<Account> findAll() {
      return accountDao.findAll();
    }

    @Override
    public Account findById(Integer id) {
        return accountDao.findById(id);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer id) {
        accountDao.deleteAccount(id);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        Account sourceAccount =accountDao.findAccountByName(sourceName);
        sourceAccount.setMoney(sourceAccount.getMoney()-money);
        accountDao.updateAccount(sourceAccount);
        Account targetAccount =accountDao.findAccountByName(targetName);
        int a=4/0;
        targetAccount.setMoney(targetAccount.getMoney()+money);
        accountDao.updateAccount(targetAccount);
    }




}
