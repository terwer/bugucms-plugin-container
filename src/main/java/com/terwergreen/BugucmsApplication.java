package com.terwergreen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ServletComponentScan(value = {"com.terwergreen.servlet"})
@ComponentScan(value = {"com.terwergreen"})
@ImportResource(locations = {"classpath:mykaptcha.xml"})
public class BugucmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BugucmsApplication.class, args);
    }
}
