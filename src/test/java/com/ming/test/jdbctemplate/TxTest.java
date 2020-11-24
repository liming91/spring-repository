package com.ming.test.jdbctemplate;

import com.ming.service.jdbctemplate.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 事物控制
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:jdbcTemplate.xml")
public class TxTest {
    @Autowired
    private IAccountService accountService;

    @Test
    public void transferTest() {
        accountService.transfer("aaa", "bbb", 100f);
    }
}
