package com.ming.dao.dbcteplate;

import com.ming.model.Account;

/**
 * 基于jdbcTemplate的持久层
 */
public interface IAccountDao {

    /**
     * 根据id查询账户
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 根据账户名称查询账户
     * @param accountName
     * @return
     */
    Account findAccountByName(String accountName);
    /**
     * 更新账户
     * @param account
     */
    void updateAccount(Account account);


}
