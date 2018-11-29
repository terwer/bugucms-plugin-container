package com.terwergreen.bugucms.container;

import org.pf4j.PluginException;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;
import org.pf4j.spring.SpringPluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE;

/**
 * @Author Terwer
 * @Date 2018/11/26 18:47
 * @Version 1.0
 * @Description 公共插件基类
 **/
public  class BugucmsPlugin extends SpringPlugin {
    private static final Logger logger = LoggerFactory.getLogger(BugucmsPlugin.class);

    public BugucmsPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    /**
     * 公共获取上下文方法
     * @return
     */
    public ApplicationContext getBugucmsApplicationContext() {
        return super.getApplicationContext();
    }

    /**
     * 公共的注册bean方法
     *
     * @param obj
     */
    public void registerBean(Object obj) {
        ApplicationContext applicationContext = this.getBugucmsApplicationContext();
        if (null != applicationContext) {
            AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
            if (null != beanFactory) {
                beanFactory.autowire(Object.class, AUTOWIRE_BY_TYPE, true);
                logger.info("BugucmsPlugin autowire Bean " + Object.class + " in container " + applicationContext);
            }
        }
    }

    @Override
    protected ApplicationContext createApplicationContext() {
        ApplicationContext applicationContext = ((SpringPluginManager) (this.getWrapper().getPluginManager())).getApplicationContext();
        logger.info("Creating applicationContext in BugucmsPlugin:" + applicationContext);
        return applicationContext;
    }

    @Override
    public void start() throws PluginException {
        super.start();
        logger.debug("BugucmsPlugin started");
    }

    @Override
    public void stop() {
        super.stop();
        logger.debug("BugucmsPlugin stoped");
    }
}
