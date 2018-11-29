package com.terwergreen.bugucms.container;

import org.pf4j.Extension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE;

/**
 * @Author Terwer
 * @Date 2018/11/29 13:51
 * @Version 1.0
 * @Description 插件扩展点公共基类
 **/
@Extension
public abstract class BugucmsPluginExtension {
    private static final Logger logger = LoggerFactory.getLogger(BugucmsPluginExtension.class);
    private ApplicationContext applicationContext;

    /**
     * 公共获取上下文方法
     *
     * @return
     */
    public ApplicationContext getBugucmsApplicationContext() {
        return this.applicationContext;
    }

    /**
     * 公共的注册bean方法
     *
     * @param obj
     */
    public void registerBean(Object obj) {
        ApplicationContext applicationContext = this.applicationContext;
        if (null != applicationContext) {
            AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
            if (null != beanFactory) {
                beanFactory.autowire(Object.class, AUTOWIRE_BY_TYPE, true);
                logger.info("BugucmsPluginExtension autowire Bean " + Object.class + " in container " + applicationContext);
            }
        }
    }

    /**
     * 设置扩展点上下文
     *
     * @param applicationContext
     */
    public abstract void createApplicationContext(ApplicationContext applicationContext);
}
