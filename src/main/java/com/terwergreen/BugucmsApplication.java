package com.terwergreen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ServletComponentScan(value = {"com.terwergreen.bugucms.servlet"}) // 开启Servlet支持
@ComponentScan(value = {"com.terwergreen.bugucms"}) //包扫描路径
@EnableTransactionManagement //事务管理，等同于xml配置方式的
@SpringBootApplication
public class BugucmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BugucmsApplication.class, args);
    }
}
