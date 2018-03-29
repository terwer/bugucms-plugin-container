package com.terwergreen.bugucms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(value = {"com.terwergreen.front"})
public class BugucmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BugucmsApplication.class, args);
    }
}
