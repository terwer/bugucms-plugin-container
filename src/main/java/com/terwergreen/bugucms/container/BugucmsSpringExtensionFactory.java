package com.terwergreen.bugucms.container;

import com.terwergreen.plugins.BugucmsPlugin;
import com.terwergreen.util.ReflectUtil;
import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringExtensionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * 扩展点工厂
 *
 * @author Terwer
 * @version 1.0
 * 2018/11/26 17:05
 **/
public class BugucmsSpringExtensionFactory extends SpringExtensionFactory {
    private static final Logger logger = LoggerFactory.getLogger(BugucmsSpringExtensionFactory.class);
    private BugucmsPluginManager pluginManager;
    private boolean autowire;

    public BugucmsSpringExtensionFactory(BugucmsPluginManager pluginManager) {
        this(pluginManager, true);
    }

    public BugucmsSpringExtensionFactory(BugucmsPluginManager pluginManager, boolean autowire) {
        super(pluginManager, autowire);
        this.pluginManager = pluginManager;
        this.autowire = autowire;
    }

    @Override
    public Object create(Class<?> extensionClass) {
        logger.debug("BugucmsSpringExtensionFactory create extension，开始创建插件上下文...");
        // 获取插件管理器上下文
        GenericApplicationContext applicationContext = (GenericApplicationContext) ((BugucmsPluginManager) pluginManager).getApplicationContext();
        // 实例化扩展点
        Object extension = null;
        try {
            extension = applicationContext.getBean(extensionClass);
        } catch (Exception e) {
            logger.debug("无法获取扩展点" + extensionClass);
        }
        if (null == extension) {
            // 创建扩展点
            logger.info("扩展点不存在，创建扩展点:" + extensionClass.getName());
            extension = ReflectUtil.newInstance(extensionClass, new Class[]{GenericApplicationContext.class}, new Object[]{applicationContext});
            // 注入扩展到插件上下文
            if (autowire && extension != null) {
                PluginWrapper pluginWrapper = pluginManager.whichPlugin(extensionClass);
                if (pluginWrapper != null) {
                    Plugin plugin = pluginWrapper.getPlugin();
                    if (plugin instanceof BugucmsPlugin) {
                        logger.info("注入扩展到插件上下文：" + extension);
                        ApplicationContext pluginContext = ((BugucmsPlugin) plugin).getApplicationContext();
                        pluginContext.getAutowireCapableBeanFactory().autowireBean(extension);
                    }
                }
            }
        }
        return extension;
    }
}