package com.ming.test.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * jdbctemplate最基本的用法
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        //准备数据源：spring内置数据源
        DriverManagerDataSource db = new DriverManagerDataSource();
        db.setDriverClassName("com.mysql.jdbc.Driver");
        db.setUrl("jdbc:mysql://localhost:3307/test");
        db.setUsername("liming");
        db.setPassword("liming");
        //1.创建jdbctemplate对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(db);
        //2.执行插入操作
        jdbcTemplate.execute("insert into account(name,money) values ('gg',100)");
    }
}
