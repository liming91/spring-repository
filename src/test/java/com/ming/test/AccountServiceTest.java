package com.ming.test;

import com.ming.model.Account;
import com.ming.service.IAccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 基于xml的IOC配置使用Junit单元测试
 */
public class AccountServiceTest {
    @Test
    public void testFindAll() {
        //1.获取容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        IAccountService accountService = (IAccountService) context.getBean("accountService");
        List<Account> accountList = accountService.findAll();
        System.out.println(accountList);
    }

    @Test
    public void testFindById() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService = (IAccountService) context.getBean("accountService");
        Account account = accountService.findById(1);
        System.out.println(account);
    }


    @Test
    public void testSaveAccount() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService = (IAccountService) context.getBean("accountService");
        Account account = new Account();
        account.setName("aaa");
        account.setMoney(1000f);
        accountService.saveAccount(account);
    }

    @Test
    public void testUpdateAccount() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService = (IAccountService) context.getBean("accountService");
        Account account = new Account();
        account.setName("aaa");
        account.setMoney(1000f);
        accountService.updateAccount(account);
    }

    @Test
    public void testDeleteAccount() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService = (IAccountService) context.getBean("accountService");
        accountService.deleteAccount(1);
    }

    @Test
    public void testTransfer() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService = (IAccountService) context.getBean("accountProxy");
        accountService.transfer("aaa", "bbb", 100f);
    }
}
