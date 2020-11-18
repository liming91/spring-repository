package com.ming.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ming.util.JdbcProConfig;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * 该类是一个配置类，它的作用和bean.xml是一样的
 */
@Configuration
@ComponentScan(basePackages = {"com.ming"})
@PropertySource("jdbc.properties")
@Import(value = JdbcProConfig.class)
public class SpringConfiguration {

   @Autowired
    JdbcProConfig jdbcProConfig;
    /**
     * 该注解可以将一个方法的返回值注册到spring容器中
     * @param dataSource
     * @return
     */
    @Bean(name="queryRunner")
    public QueryRunner getQueryRunner(DataSource dataSource){
        return  new QueryRunner(dataSource);
    }

    @Bean(name="dataSource")
    public  DataSource getDataSource() {
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(jdbcProConfig.getDriver());
            dataSource.setJdbcUrl(jdbcProConfig.getUrl());
            dataSource.setUser(jdbcProConfig.getUser());
            dataSource.setPassword(jdbcProConfig.getPwd());
            return  dataSource;
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }
}
