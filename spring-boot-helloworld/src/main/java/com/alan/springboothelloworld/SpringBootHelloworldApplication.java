package com.alan.springboothelloworld;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling //  定时任务开启
@SpringBootApplication
@EnableTransactionManagement // 添加事务注解
@ServletComponentScan
public class SpringBootHelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHelloworldApplication.class, args);
    }

   /* @Bean("duridDatasource")
    @ConfigurationProperties(prefix="application-dev.properties")
    public DruidDataSource druidDataSource() { return new DruidDataSource();};
*/

}

