package com.ming.config;

import com.ming.util.JdbcProConfig;
import org.springframework.context.annotation.*;

/**
 * 该类是一个配置类，它的作用和bean.xml是一样的
 */
@Configuration
@ComponentScan(basePackages = {"com.ming"})
@PropertySource("classpath:jdbc.properties")
@Import(value = JdbcProConfig.class)
@EnableAspectJAutoProxy
public class SpringConfiguration {

}
