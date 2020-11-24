package com.ming.util.tx;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 事物相关的配置类
 * 配置事物管理器
 */
public class TransactionConfig {
    /**
     * 用于创建事物管理器
     * @param dataSource
     * @return
     */
    @Bean(name = "transactionManager")
    public PlatformTransactionManager getTransaction(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}
