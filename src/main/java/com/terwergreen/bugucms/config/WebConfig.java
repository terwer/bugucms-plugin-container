package com.terwergreen.bugucms.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

/**
 * @Author Terwer
 * @Date 2018/12/3 3:52
 * @Version 1.0
 * @Description 静态资源配置
 **/
@ConditionalOnProperty(name = "bugucms.plugin-switch", havingValue = "true")
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    private static final Log logger = LogFactory.getLog(WebConfig.class);

    /**
     * 添加静态文件路径
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("添加静态资源映射");
        // 静态资源映射
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        // swagger-ui
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // webjars资源映射
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/")
                .resourceChain(false)
                .addResolver(new WebJarsResourceResolver())
                .addResolver(new PathResourceResolver());

        // 插件静态资源映射
        registry.addResourceHandler("/auth/static/**").addResourceLocations("plugins/auth-plugin-1.0.0/classes/static/");
    }
}
