package com.ming.service.impl;
import com.ming.dao.AccountDao;
import com.ming.model.Account;
import com.ming.service.IAccountService;
import com.ming.util.TransacationManger;

import java.util.List;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

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
