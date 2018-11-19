package com.terwergreen.bugucms.config;

import com.terwergreen.bugucms.handler.MetaWeblogHandler;
import com.terwergreen.bugucms.handler.impl.MetaWeblogHandelerImpl;
import com.terwergreen.bugucms.servlet.BugucmsDispatcherServlet;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.DispatcherServlet;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * @Author Terwer
 * @Date 2018/6/29 15:03
 * @Version 1.0
 * @Description 项目自定义配置
 **/
@Configuration
public class BugucmsConfig {
    /**
     * 密码加密策略
     * @return
     */
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义DispatcherServlet
     *
     * @return
     */
    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new BugucmsDispatcherServlet();
    }

    /**
     * 注入自定义DispatcherServlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet());
        registration.setLoadOnStartup(0);
        registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
        return registration;
    }

    /**
     * 设置Admin模板引擎查找目录
     *
     * @return
     */
    @Bean
    public ClassLoaderTemplateResolver adminTemplateResolver() {
        ClassLoaderTemplateResolver adminTemplateResolver = new ClassLoaderTemplateResolver();
        adminTemplateResolver.setPrefix("admin/");
        adminTemplateResolver.setSuffix(".html");
        adminTemplateResolver.setTemplateMode(TemplateMode.HTML);
        adminTemplateResolver.setCharacterEncoding("UTF-8");
        adminTemplateResolver.setOrder(0);
        adminTemplateResolver.setCheckExistence(true);
        return adminTemplateResolver;
    }

    /**
     * 将metaWeblog的bean交给Spring
     *
     * @return
     */
    @Bean
    public MetaWeblogHandler metaWeblog() {
        return new MetaWeblogHandelerImpl();
    }
}
