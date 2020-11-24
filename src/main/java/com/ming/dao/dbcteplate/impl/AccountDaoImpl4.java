package com.ming.dao.dbcteplate.impl;

import com.ming.dao.dbcteplate.IAccountDao;
import com.ming.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 基于jdbcTemplate的账户持久层实现类
 * 纯注解
 */
@Repository("accountDao")
public class AccountDaoImpl4 implements IAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Account findAccountById(Integer accountId) {
        List<Account> accountList = jdbcTemplate.query("select * from account where id = ? ",new BeanPropertyRowMapper<Account>(Account.class),accountId);
        return accountList.isEmpty()?null:accountList.get(0);
    }

    @Override
    public Account findAccountByName(String accountName) {
        List<Account> accountList = jdbcTemplate.query("select * from account where name = ? ",
                new BeanPropertyRowMapper<Account>(Account.class),accountName);
        if(accountList.isEmpty()){
            return null;
        }
        if(accountList.size() > 1){
             throw new RuntimeException("结果集不为1");
        }
        return  accountList.get(0);
    }

    @Override
    public void updateAccount(Account account) {
        jdbcTemplate.update("update account set name = ? , money = ? where id = ? ",account.getName(),account.getMoney(),account.getId());
    }
}
