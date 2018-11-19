package com.terwergreen.bugucms.config;

import com.terwergreen.bugucms.interceptor.AuthHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

/**
 * @Author Terwer
 * @Date 2018/11/16 19:36
 * @Version 1.0
 * @Description Web配置
 **/
@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {
    /**
     * 添加静态文件路径
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 静态资源映射
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/admin/");
        // swagger-ui
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // webjars资源映射
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/")
                .resourceChain(false)
                .addResolver(new WebJarsResourceResolver())
                .addResolver(new PathResourceResolver());
    }

    /**
     * 跨域访问
     *
     * @param registry
     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**").allowedOrigins("*.terwergreen.com", "localhost").allowedMethods(HttpMethod.GET.toString(), HttpMethod.POST.toString()).allowedHeaders("*").allowCredentials(true).maxAge(3600);
//        super.addCorsMappings(registry);
//    }

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
     * 允许跨域访问
     *
     * @return
     */
//    @Bean
//    public CorsHandlerInterceptor corsHandlerInterceptor() {
//        return new CorsHandlerInterceptor();
//    }

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
