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

    /**
     * 根据名称查询账户
     * @param accountName
     * @return 如果有唯一的一个结果就返回，如果没有结果返回null,如果有多个结果抛异常
     */
    Account findAccountByName(String accountName);


}
