package com.terwergreen.bugucms.container;

import org.pf4j.PluginWrapper;
import org.pf4j.spring.ExtensionsInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;

import java.util.List;
import java.util.Set;

/**
 * 扩展点注入
 *
 * @author Terwer
 * @version 1.0
 * 2018/11/26 16:44
 **/
public class BugucmsExtensionsInjector extends ExtensionsInjector {
    private static final Logger logger = LoggerFactory.getLogger(BugucmsExtensionsInjector.class);
    protected final BugucmsPluginManager pluginManager;
    protected final AbstractAutowireCapableBeanFactory beanFactory;

    public BugucmsExtensionsInjector(BugucmsPluginManager pluginManager, AbstractAutowireCapableBeanFactory beanFactory) {
        super(pluginManager, beanFactory);
        this.pluginManager = pluginManager;
        this.beanFactory = beanFactory;
    }

    @Override
    public void injectExtensions() {
        logger.debug("BugucmsExtensionsInjector injectExtensions");
        // add extensions for each started plugin
        List<PluginWrapper> startedPlugins = pluginManager.getStartedPlugins();
        for (PluginWrapper plugin : startedPlugins) {
            logger.debug("Registering extensions of the plugin '{}' as beans" + plugin.getPluginId());
            Set<String> extensionClassNames = pluginManager.getExtensionClassNames(plugin.getPluginId());
            for (String extensionClassName : extensionClassNames) {
                try {
                    logger.debug("Register extension '{}' as bean" + extensionClassName);
                    Class<?> extensionClass = plugin.getPluginClassLoader().loadClass(extensionClassName);
                    registerExtension(extensionClass);
                } catch (ClassNotFoundException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * Register an extension as bean.
     * Current implementation register extension as singleton using {@code beanFactory.registerSingleton()}.
     * The extension instance is created using {@code pluginManager.getExtensionFactory().create(extensionClass)}.
     * The bean name is the extension class name.
     * Override this method if you wish other register strategy.
     */
    @Override
    protected void registerExtension(Class<?> extensionClass) {
        logger.debug("BugucmsExtensionsInjector registerExtension");
        Object extension = pluginManager.getExtensionFactory().create(extensionClass);
        beanFactory.registerSingleton(extension.getClass().getName(), extension);
    }
}
