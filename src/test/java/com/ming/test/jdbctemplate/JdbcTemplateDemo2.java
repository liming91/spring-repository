package com.ming.test.jdbctemplate;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateDemo2 {
    @Test
    public void jdbcTemplateTest() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("jdbcTemplate.xml");
        JdbcTemplate jt = ac.getBean("jdbcTemplate", JdbcTemplate.class);
        jt.execute(" insert into account(name,money) values('hh',500)");
    }
}
