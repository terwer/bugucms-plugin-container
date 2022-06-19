package com.terwergreen.bugucms.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * ApplicaitonContext工具类
 *
 * @name: ApplicationContextUtils
 * @author: terwer
 * @date: 2022-03-07 14:26
 **/
public class SpringBeanUtils {
    private static final Logger logger = LoggerFactory.getLogger(SpringBeanUtils.class);

    private static ApplicationContext context;

    /**
     * 设置上下文：注意，用户不能直接调用，只能由框架启动设置
     *
     * @param applicationContext
     */
    public static void setContext(ApplicationContext applicationContext) {
        final String callee = Thread.currentThread().getStackTrace()[2].getClassName() +
                Thread.currentThread().getStackTrace()[1].getMethodName(); // This gets me the caller
        logger.debug("SpringBeanUtils setContext callee = " + callee);
        if (!"com.terwergreen.bugucms.BugucmsApplicationsetContext".equals(callee)) {
            logger.error("用户不能直接调用，只能由框架启动设置");
            return;
        }
        context = applicationContext;
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static <T> T getBean(Class<T> t) {
        return context.getBean(t);
    }
}
