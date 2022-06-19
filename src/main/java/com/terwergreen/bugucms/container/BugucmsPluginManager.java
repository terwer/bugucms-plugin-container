package com.terwergreen.bugucms.container;

import com.terwergreen.core.CommonService;
import org.pf4j.ExtensionFactory;
import org.pf4j.spring.SpringPluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;

import javax.annotation.PostConstruct;
import java.nio.file.Path;

/**
 * 自定义插件管理器
 *
 * @author Terwer
 * @version 1.0
 * 2018/11/22 10:52
 **/
public class BugucmsPluginManager extends SpringPluginManager {
    private static final Logger logger = LoggerFactory.getLogger(BugucmsPluginManager.class);

    @Autowired
    private CommonService commonService;

    public BugucmsPluginManager(Path pluginsRoot) {
        super(pluginsRoot);
        logger.info("加载插件");
        loadPlugins();
        logger.info("插件管理器创建完毕");
    }

    /**
     * 重定义工厂，用于设置上下文
     *
     * @return BugucmsSpringExtensionFactory
     */
    @Override
    protected ExtensionFactory createExtensionFactory() {
        logger.info("创建扩展点工厂");
        return new BugucmsSpringExtensionFactory(this);
    }

    @Override
    @PostConstruct
    public void init() {
        logger.info("初始化插件");

        logger.info("启动插件");
        startPlugins();

        logger.info("创建注册注入器");
        AbstractAutowireCapableBeanFactory beanFactory = (AbstractAutowireCapableBeanFactory) getApplicationContext().getAutowireCapableBeanFactory();
        BugucmsExtensionsInjector extensionsInjector = new BugucmsExtensionsInjector(this, beanFactory);
        logger.info("注册插件到上下文");
        extensionsInjector.injectExtensions();


        logger.info("插件初始化完毕");
    }
}
