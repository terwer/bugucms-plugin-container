package com.terwergreen.bugucms.container;

import org.pf4j.DefaultPluginManager;
import org.pf4j.ExtensionFactory;
import org.pf4j.spring.SpringPluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(DefaultPluginManager.class);

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
        logger.debug("BugucmsPluginManager init");
        initPlugins();
    }

    /**
     * 启动插件
     */
    private void initPlugins() {
        logger.debug("BugucmsPluginManager initPlugins");
        // 插件启动
        loadPlugins();
        startPlugins();
        // 注册依赖
        AbstractAutowireCapableBeanFactory beanFactory = (AbstractAutowireCapableBeanFactory) getApplicationContext().getAutowireCapableBeanFactory();
        BugucmsExtensionsInjector extensionsInjector = new BugucmsExtensionsInjector(this, beanFactory);
        extensionsInjector.injectExtensions();
    }
}
