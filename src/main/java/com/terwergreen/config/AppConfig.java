package com.terwergreen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class AppConfig {
    @Bean
    public ClassLoaderTemplateResolver emailTemplateResolver() {
        ClassLoaderTemplateResolver adminTemplateResolver = new ClassLoaderTemplateResolver();
        adminTemplateResolver.setPrefix("admin/");
        adminTemplateResolver.setSuffix(".html");
        adminTemplateResolver.setTemplateMode(TemplateMode.HTML);
        adminTemplateResolver.setCharacterEncoding("UTF-8");
        adminTemplateResolver.setOrder(0);
        adminTemplateResolver.setCheckExistence(true);
        return adminTemplateResolver;
    }
}
