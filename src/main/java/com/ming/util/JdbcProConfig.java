package com.ming.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

/**
 * jdbc配置类，用于@import注解导入
 */
public class JdbcProConfig {
    @Value("${driver}")
    private String driver;

    @Value("${url}")
    private String url;

    @Value("${user}")
    private String user;

    @Value("${pwd}")
    private String pwd;

    /**
     * 该注解可以将一个方法的返回值注册到spring容器中
     * @param dataSource
     * @return
     */
    @Bean(name="queryRunner")
    @Scope("prototype")
    public QueryRunner getQueryRunner(DataSource dataSource){
        return  new QueryRunner(dataSource);
    }

    @Bean(name="dataSource")
    public  DataSource getDataSource() {
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(driver);
            dataSource.setJdbcUrl(url);
            dataSource.setUser(user);
            dataSource.setPassword(pwd);
            return  dataSource;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }
}
