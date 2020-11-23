package com.ming.test;

import com.ming.config.SpringConfiguration;
import com.ming.model.Account;
import com.ming.service.IAccountService;
import com.ming.util.aop.AccountProxy;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class AccountTest {
    Logger logger = Logger.getLogger(AccountTest.class);
    @Autowired
    private IAccountService accountService;

    @Test
    public void addTest() {
        Account account = new Account();
        account.setName("ff");
        account.setMoney(1000f);
        accountService.saveAccount(account);
    }

    @Test
    public void findAllTest() {
        List<Account> li = accountService.findAll();
        System.out.println(li);
    }

    @Test
    public void addTransef() {
        accountService.transfer("aaa", "bbb", 100f);
    }

    @Test
    public void tansefProxyTest() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService accountService = context.getBean("accountService", IAccountService.class);
        IAccountService accountProxy = AccountProxy.createProxy(accountService, IAccountService.class);
        accountProxy.transfer("aaa", "bbb", 100f);

    }

}
