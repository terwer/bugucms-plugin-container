package com.terwergreen.bugucms.container;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pf4j.ExtensionFactory;
import org.pf4j.PluginClasspath;
import org.pf4j.spring.SpringPluginManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.nio.file.Path;

/**
 * @Author Terwer
 * @Date 2018/11/22 10:52
 * @Version 1.0
 * @Description 自定义插件管理器
 **/
public class BugucmsPluginManager extends SpringPluginManager {
    private static final Log logger = LogFactory.getLog(BugucmsPluginManager.class);

    /**
     * 插件加载路径获取
     *
     * @return
     */
    public PluginClasspath getBugucmsPluginClasspath() {
        return super.pluginClasspath;
    }

    /**
     * 判断是否处于插件开发模式
     *
     * @return
     */

    public boolean isDevelopment() {
        return super.isDevelopment();
    }

    /**
     * 重定义工厂，用于设置上下文
     *
     * @return
     */
    @Override
    protected ExtensionFactory createExtensionFactory() {
        logger.debug("BugucmsPluginManager createExtensionFactory");
        return new BugucmsSpringExtensionFactory(this);
    }

    /**
     * 创建插件目录
     *
     * @return
     */
    @Override
    protected Path createPluginsRoot() {
        Path pluginsRoot = super.createPluginsRoot();
        logger.debug("BugucmsPluginManager创建插件目录,pluginsRoot is:" + pluginsRoot.toUri().toString());
        return pluginsRoot;
    }

    /**
     * 插件初始化
     */
    @Override
    protected void initialize() {
        super.initialize();
        Path pluginsRoot = super.getPluginsRoot();
        logger.info("BugucmsPluginManager initialize,pluginsRoot is:" + pluginsRoot.toUri().toString());
    }

    /**
     * 设置Spring上下文
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        super.setApplicationContext(applicationContext);
        logger.debug("BugucmsPluginManager setApplicationContext,ApplicationContext is:" + applicationContext);
    }

    /**
     * 启动
     */
    @PostConstruct
    @Override
    public void init() {
        logger.info("BugucmsPluginManager加载插件");
        // 加载插件
        loadPlugins();
        logger.info("BugucmsPluginManager启动插件");
        // 启动插件
        startPlugins();
        logger.info("BugucmsPluginManager注册依赖");
        // 注册依赖
        AbstractAutowireCapableBeanFactory beanFactory = (AbstractAutowireCapableBeanFactory) getApplicationContext().getAutowireCapableBeanFactory();
        BugucmsExtensionsInjector extensionsInjector = new BugucmsExtensionsInjector(this, beanFactory);
        extensionsInjector.injectExtensions();
    }
}
