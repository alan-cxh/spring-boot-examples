package com.alan.springboothelloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling //  定时任务开启
@SpringBootApplication
@EnableTransactionManagement // 添加事务注解

public class SpringBootHelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHelloworldApplication.class, args);
    }

}

