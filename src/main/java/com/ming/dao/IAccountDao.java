package com.ming.dao;

import com.ming.model.Account;

import java.util.List;

/**
 * 账户的持久层接口
 */
public interface IAccountDao {

    /**
     * 查找所有的账户
     * @return
     */
    List<Account> findAll();

    /**
     * 按照id查找账户
     * @param id
     * @return
     */
    Account  findById(Integer id);

    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Integer id);

    Account findAccountByName(String accountName);


}
