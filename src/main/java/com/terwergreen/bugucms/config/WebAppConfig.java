package com.terwergreen.bugucms.config;

import com.terwergreen.bugucms.interceptor.AuthHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author Terwer
 * @Date 2018/11/16 19:36
 * @Version 1.0
 * @Description Web配置
 **/

@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {
    /**
     * 接口访问，带token
     *
     * @return
     */
    @Bean
    public AuthHandlerInterceptor authHandlerInterceptor() {
        return new AuthHandlerInterceptor();
    }

    /**
     * 配置拦截器
     *
     * @param registry InterceptorRegistry
     * @author Jordan
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authHandlerInterceptor()).addPathPatterns("/api/**");
        super.addInterceptors(registry);
    }
}
