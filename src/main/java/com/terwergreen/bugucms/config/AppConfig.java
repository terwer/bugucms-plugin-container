/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.terwergreen.bugucms.config;

import com.terwergreen.bugucms.handler.MetaWeblogHandler;
import com.terwergreen.bugucms.handler.impl.MetaWeblogHandelerImpl;
import com.terwergreen.bugucms.servlet.BugucmsDispatcherServlet;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class AppConfig {

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
