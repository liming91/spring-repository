package com.ming.service;

import com.ming.model.Account;

import java.util.List;

/**
 * 账户业务层接口
 */
public interface IAccountService {
    /**
     * 查找所有的账户
     *
     * @return
     */
    List<Account> findAll();

    /**
     * 按照id查找账户
     *
     * @param id
     * @return
     */
    Account findById(Integer id);

    /**
     * 保存
     *
     * @param account
     */
    void saveAccount(Account account);

    /**
     * 更新
     *
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     *
     * @param id
     */
    void deleteAccount(Integer id);

    /**
     * @param sourceName 转出账户
     * @param targetName 转入账户
     * @param money      金额
     */
    public void transfer(String sourceName, String targetName, Float money);

}
