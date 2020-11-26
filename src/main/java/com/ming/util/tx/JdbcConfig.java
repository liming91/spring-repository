package com.ming.util.tx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * 配置db
 */
public class JdbcConfig {

    @Value("${driver}")
    private String driver;

    @Value("${url}")
    private String url;

    @Value("${user}")
    private String user;

    @Value("${pwd}")
    private String pwd;

    @Bean(name="jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
        return  new JdbcTemplate(dataSource);
    }

    @Bean(name="dataSource")
    public DataSource getDataSource() {
        try {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(pwd);
            return  dataSource;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }
}
