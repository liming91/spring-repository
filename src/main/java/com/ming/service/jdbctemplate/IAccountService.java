package com.ming.service.jdbctemplate;

import com.ming.model.Account;

public interface IAccountService {
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
     * 转账
     * @param sourceName 转出账户
     * @param targetName 转入账户
     * @param money 金额
     */
    void transfer(String sourceName,String targetName,Float money);
}
