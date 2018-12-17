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
 * 静态资源配置
 *
 * @author Terwer
 * @version 1.0
 * 2018/12/3 3:52
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
     * @param registry 资源注册器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("添加容器静态资源映射");
        // 静态资源映射
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/dist/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
        registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
        // swagger-ui
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        // webjars资源映射
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/")
                .resourceChain(false)
                .addResolver(new WebJarsResourceResolver())
                .addResolver(new PathResourceResolver());

        // 插件静态资源映射
        List<PluginWrapper> plugins = pluginManager.getStartedPlugins();
        for (PluginWrapper pluginWrapper : plugins) {
            // 静态资源目录
            String pluginStaticPath = "static/";
            registerPluginStaticResource(registry, pluginWrapper, pluginStaticPath);

            // 模板资源目录
            String pluginTemplatePath = "templates/";
            registerPluginStaticResource(registry, pluginWrapper, pluginTemplatePath);

            // WarJars资源目录
            String webjarsPath = "webjars/";
            registerPluginMetaInfResource(registry, pluginWrapper, webjarsPath);
        }
    }

    /**
     * 注册静态资源映射
     *
     * @param registry
     * @param pluginWrapper
     * @param pluginResourcePath
     */
    private void registerPluginStaticResource(ResourceHandlerRegistry registry, PluginWrapper pluginWrapper, String pluginResourcePath) {
        // 虚拟路径
        String prefix = pluginWrapper.getPluginId().replace("-plugin", "");
        String virtualPath = "/" + prefix + "/" + pluginResourcePath + "**";
        // 实际路径
        String absPath = pluginWrapper.getPluginClassLoader().getResource(".").getPath();
        String pluginResourceLocation = "file://" + absPath + pluginResourcePath;
        // 注册路径到Web上下文
        registry.addResourceHandler(virtualPath).addResourceLocations(pluginResourceLocation);
        // registry.addResourceHandler("/auth/static/**").addResourceLocations("plugins/auth-plugin-1.0.0/classes/static/");
        logger.info("添加插件静态资源映射，from " + virtualPath + " to " + pluginResourceLocation);
    }

    private void registerPluginMetaInfResource(ResourceHandlerRegistry registry, PluginWrapper pluginWrapper, String pluginResourcePath) {
        // 虚拟路径
        String prefix = pluginWrapper.getPluginId().replace("-plugin", "");
        String virtualPath = "/" + prefix + "/" + pluginResourcePath + "**";
        // 实际路径
        String absPath = pluginWrapper.getPluginClassLoader().getResource(".").getPath();
        String pluginResourceLocation = "file://" + absPath + "META-INF/resources/" + pluginResourcePath;
        // 注册路径到Web上下文
        registry.addResourceHandler(virtualPath).addResourceLocations(pluginResourceLocation)
                .resourceChain(false)
                .addResolver(new WebJarsResourceResolver())
                .addResolver(new PathResourceResolver());
        // registry.addResourceHandler("/auth/static/**").addResourceLocations("plugins/auth-plugin-1.0.0/classes/META-INF/resources/webjars/");
        logger.info("添加META-INF资源映射，from " + virtualPath + " to " + pluginResourceLocation);
    }
}
