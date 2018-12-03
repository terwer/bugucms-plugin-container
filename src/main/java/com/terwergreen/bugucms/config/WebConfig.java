package com.terwergreen.bugucms.config;

import com.terwergreen.bugucms.container.BugucmsPluginManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

import java.util.List;

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

    @Autowired
    private BugucmsPluginManager pluginManager;

    /**
     * 添加静态文件路径
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("添加容器静态资源映射");
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
        List<PluginWrapper> plugins = pluginManager.getStartedPlugins();
        for (PluginWrapper pluginWrapper : plugins) {
            // 静态资源目录
            String pluginStaticPath = "static/";
            // 虚拟路径
            String prefix = pluginWrapper.getPluginId().replace("-plugin", "");
            String virtualPath = "/" + prefix + "/" + pluginStaticPath + "**";
            // 实际路径
            String absPath = pluginWrapper.getPluginClassLoader().getResource(".").getPath();
            String pluginResourceLocation = "file://" + absPath + pluginStaticPath;
            // 注册路径到Web上下文
            registry.addResourceHandler(virtualPath).addResourceLocations(pluginResourceLocation);
            // registry.addResourceHandler("/auth/static/**").addResourceLocations("plugins/auth-plugin-1.0.0/classes/static/");

            logger.info("添加插件静态资源映射，from " + virtualPath + " to " + pluginResourceLocation);
        }
    }
}
