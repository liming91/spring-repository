package com.ming.test.jdbctemplate;

import com.ming.dao.dbcteplate.IAccountDao;
import com.ming.model.Account;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * jdbcTemplate的CRUD
 */
public class JdbcTemplateDemo3 {

    @Test
    public void jdbcTemplateDaoTest(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("jdbcTemplate.xml");
        IAccountDao accountDao = ac.getBean("accountDao", IAccountDao.class);
        Account account = accountDao.findAccountById(7);
        System.out.println(account);
        account.setMoney(999f);
        accountDao.updateAccount(account);
    }

    @Test
    public void jdbcTemplateTest() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("jdbcTemplate.xml");
        JdbcTemplate jt = ac.getBean("jdbcTemplate", JdbcTemplate.class);
        //保存
        //jt.update(" insert into account(name,money) values(?,?)","lm",200f);
        //更新
        //jt.update("update account set name =?,money =? where id=? ","xl",1f,8);
        //删除
        //jt.update("delete from account  where id=? ",8);
        //查询所有
        //List<Account> accountList = jt.query("select * from account where money > ? ",new AccountRowMapeer(),10f);
//        List<Account> accountList = jt.query("select * from account where money > ? ",new BeanPropertyRowMapper<>(Account.class),10f);
//        for (Account list : accountList){
//            System.out.println(list);
//        }
        //查询一个
//        List<Account> accountList = jt.query("select * from account where id = ? ",new BeanPropertyRowMapper<>(Account.class),1);
//        System.out.println(accountList.isEmpty()?"null":accountList.get(0));
        //查询返回一行一列（使用聚合函数，但不加group by子句）
        int count = jt.queryForObject("select count(*) from account where money > ? ", Integer.class, 100f);
        System.out.println(count);
    }

    /**
     * 定义封装account
     */
    class AccountRowMapeer implements RowMapper<Account> {

        @Override
        public Account mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Account account = new Account();
            account.setId(resultSet.getInt("id"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getFloat("money"));
            return account;
        }
    }
}
