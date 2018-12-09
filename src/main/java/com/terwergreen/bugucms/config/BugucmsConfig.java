package com.terwergreen.bugucms.config;

import com.terwergreen.bugucms.container.BugucmsPluginManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pf4j.RuntimeMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 项目自定义配置
 *
 * @author Terwer
 * @version 1.0
 * 2018/6/29 15:03
 **/
@Configuration
public class BugucmsConfig {
    private static final Log logger = LogFactory.getLog(BugucmsConfig.class);

    @Value("${bugucms.plugin-switch}")
    private boolean pluginSwitch;
    @Value("${pf4j.mode}")
    private String pf4jMode;
    @Value("${pf4j.plugins-dir}")
    private String pf4jPluginsDir;

    /**
     * 插件注入入口，注释此方法则插件功能关闭
     *
     * @return BugucmsPluginManager
     */
    @Bean
    public BugucmsPluginManager pluginManager() {
        logger.info("插件功能状态:" + (pluginSwitch ? "开启" : "关闭"));
        if (pluginSwitch) {
            // 插件开启，先设置环境变量
            System.setProperty("pf4j.mode", pf4jMode.equals("dev") ? RuntimeMode.DEVELOPMENT.toString() : RuntimeMode.DEPLOYMENT.toString());
            System.setProperty("pf4j.pluginsDir", pf4jPluginsDir);
            // 创建插件管理器
            logger.info("创建插件管理器");
            Path pluginsRoot = Paths.get(pf4jPluginsDir);
            logger.info("插件路径为：" + pluginsRoot.toUri().toString());
            return new BugucmsPluginManager(pluginsRoot);
        }
        return null;
    }
}
