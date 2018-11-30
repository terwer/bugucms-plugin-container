package com.terwergreen.bugucms.container;

import com.terwergreen.plugins.BugucmsPlugin;
import com.terwergreen.plugins.BugucmsPluginExtension;
import com.terwergreen.util.ReflectUtil;
import org.pf4j.Plugin;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringExtensionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Author Terwer
 * @Date 2018/11/26 17:05
 * @Version 1.0
 * @Description 扩展点工厂
 **/
public class BugucmsSpringExtensionFactory extends SpringExtensionFactory {
    private static final Logger logger = LoggerFactory.getLogger(BugucmsSpringExtensionFactory.class);

    private PluginManager pluginManager;
    private boolean autowire;

    public BugucmsSpringExtensionFactory(PluginManager pluginManager) {
        this(pluginManager, true);
    }

    public BugucmsSpringExtensionFactory(PluginManager pluginManager, boolean autowire) {
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
            logger.info("扩展点不存在，创建扩展点：" + extension);
            // 手动创建
            if (ReflectUtil.instanceOf(extensionClass, BugucmsPluginExtension.class)) {
                extension = ReflectUtil.newInstance(extensionClass, new Class[]{GenericApplicationContext.class}, new Object[]{applicationContext});
            }
            logger.info("extension = " + extension);
        } else {
            // 注入扩展到插件上下文
            if (ReflectUtil.instanceOf(extensionClass, BugucmsPluginExtension.class) && autowire) {
                PluginWrapper pluginWrapper = pluginManager.whichPlugin(extensionClass);
                if (pluginWrapper != null) {
                    Plugin plugin = pluginWrapper.getPlugin();
                    if (plugin instanceof BugucmsPlugin) {
                        // 将插件上下文设置为和容器一致
                        logger.debug("BugucmsSpringExtensionFactory.create()，将插件上下文设置为和容器一致");
                        ApplicationContext pluginContext = ((BugucmsPlugin) plugin).getApplicationContext();
                        pluginContext.getAutowireCapableBeanFactory().autowireBean(extension);
                    }
                }
            }
        }
        return extension;
    }
}
