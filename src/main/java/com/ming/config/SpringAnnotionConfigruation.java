package com.ming.config;

import com.ming.util.tx.JdbcConfig;
import com.ming.util.tx.TransactionConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.ming"})
@Import({JdbcConfig.class,TransactionConfig.class})
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
public class SpringAnnotionConfigruation {


}
