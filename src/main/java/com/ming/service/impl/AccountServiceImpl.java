package com.ming.service.impl;

import com.ming.dao.IAccountDao;
import com.ming.model.Account;
import com.ming.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户的业务层实现类
 */
@Service
public class AccountServiceImpl implements IAccountService {
    //最早的写法
    //private IAccountDao accountDao = new AccountDaoImpl();
    //BeanFactory读取配置的bean.properties的dao
    //private IAccountDao accountDao= (IAccountDao) BeanFactory.getBean("accountDao");

    @Autowired
    private IAccountDao accountDao;

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

    /**
     * 此方法如抛出异常事物不会回滚，添加aop控制事物
     * @param sourceName 转出账户
     * @param targetName 转入账户
     * @param money      金额
     */
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
            int a=2/0;
            accountDao.updateAccount(targetAccount);
    }


}
