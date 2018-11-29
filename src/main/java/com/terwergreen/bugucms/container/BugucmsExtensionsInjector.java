package com.terwergreen.bugucms.container;

import org.pf4j.PluginManager;
import org.pf4j.spring.ExtensionsInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;

/**
 * @Author Terwer
 * @Date 2018/11/26 16:44
 * @Version 1.0
 * @Description 扩展点注入
 **/
public class BugucmsExtensionsInjector extends ExtensionsInjector {
    private static final Logger logger = LoggerFactory.getLogger(BugucmsExtensionsInjector.class);

    public BugucmsExtensionsInjector(PluginManager pluginManager, AbstractAutowireCapableBeanFactory beanFactory) {
        super(pluginManager, beanFactory);
    }

    @Override
    public void injectExtensions() {
        logger.debug("BugucmsExtensionsInjector.injectExtensions()");
        super.injectExtensions();
    }

    @Override
    protected void registerExtension(Class<?> extensionClass) {
        logger.debug("BugucmsExtensionsInjector.registerExtension()");
        super.registerExtension(extensionClass);
    }
}
