package com.terwergreen.bugucms.config;

import com.terwergreen.bugucms.container.BugucmsPluginManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import javax.annotation.PostConstruct;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Author Terwer
 * @Date 2018/12/2 22:09
 * @Version 1.0
 * @Description 模板引擎配置
 **/
@ConditionalOnProperty(name = "bugucms.plugin-switch", havingValue = "true")
@Configuration
public class ThmeleafConfig {
    private static final Log logger = LogFactory.getLog(ThmeleafConfig.class);

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${pf4j.plugins-dir}")
    private String pf4jPluginsDir;

    @Autowired
    private BugucmsPluginManager pluginManager;

    @Bean
    public ClassLoaderTemplateResolver containerTemplateResolver() {
        String containerTemplatePath = "templates/";
        ClassLoaderTemplateResolver containerTemplateResolver = new ClassLoaderTemplateResolver();
        containerTemplateResolver.setPrefix(containerTemplatePath);
        containerTemplateResolver.setSuffix(".html");
        containerTemplateResolver.setTemplateMode(TemplateMode.HTML);
        containerTemplateResolver.setCharacterEncoding("UTF-8");
        containerTemplateResolver.setOrder(0);  // this is important. This way spring boot will listen to both places 0 and 1
        containerTemplateResolver.setCheckExistence(true);
        String absPath = containerTemplateResolver.getClass().getResource("/").getPath();
        logger.info("添加容器插件解析器，路径为:" + absPath + containerTemplatePath);
        return containerTemplateResolver;
    }

    @PostConstruct
    public void pluginTemplateResolver() {
        // 设置插件模板路径
        List<PluginWrapper> plugins = pluginManager.getPlugins();
        for (PluginWrapper pluginWrapper : plugins) {
            String pluginPath = pluginWrapper.getPluginPath().toString();
            String childPath = pluginManager.isDevelopment() ? "\\target\\classes\\templates\\" : "\\classes\\templates\\";
            String pluginTemplatePath = Paths.get(pluginPath, childPath).toAbsolutePath().toString() + "\\";
            logger.info("添加插件解析器，路径为:" + pluginTemplatePath);

            // 生成插件模板解析器
            FileTemplateResolver pluginTemplateResolver = new FileTemplateResolver();
            pluginTemplateResolver.setPrefix(pluginTemplatePath);
            pluginTemplateResolver.setSuffix(".html");
            pluginTemplateResolver.setTemplateMode("HTML");
            pluginTemplateResolver.setOrder(templateEngine.getTemplateResolvers().size());
            pluginTemplateResolver.setCacheable(false);
            templateEngine.addTemplateResolver(pluginTemplateResolver);
        }
    }
}
