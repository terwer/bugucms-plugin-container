package com.terwergreen.bugucms.container;

import com.terwergreen.plugins.BugucmsPlugin;
import com.terwergreen.plugins.BugucmsPluginExtension;
import com.terwergreen.util.ReflectUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pf4j.Plugin;
import org.pf4j.PluginManager;
import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringExtensionFactory;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Author Terwer
 * @Date 2018/11/26 17:05
 * @Version 1.0
 * @Description 扩展点工厂
 **/
public class BugucmsSpringExtensionFactory extends SpringExtensionFactory {
    private static final Log logger = LogFactory.getLog(BugucmsSpringExtensionFactory.class);

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
            logger.info("扩展点不存在，创建扩展点");
            // 手动创建
            if (ReflectUtil.instanceOf(extensionClass, BugucmsPluginExtension.class)) {
                extension = ReflectUtil.newInstance(extensionClass, new Class[]{GenericApplicationContext.class}, new Object[]{applicationContext});
                // 注入扩展到插件上下文
                PluginWrapper pluginWrapper = pluginManager.whichPlugin(extensionClass);
                if (extension != null && pluginWrapper != null && autowire) {
                    Plugin plugin = pluginWrapper.getPlugin();
                    if (ReflectUtil.instanceOf(plugin.getClass(), BugucmsPlugin.class)) {
                        // 将插件上下文设置为和容器一致
                        logger.info("BugucmsSpringExtensionFactory注入扩展到插件上下文，将插件上下文设置为和容器一致");
                        GenericApplicationContext pluginApplicationContext = (GenericApplicationContext) ReflectUtil.getField(plugin, "applicationContext");
                        pluginApplicationContext.getAutowireCapableBeanFactory().autowireBean(extension);
                    }
                }
            }
            logger.info("扩展点创建完毕，extension=" + extension);
        }
        return extension;
    }
}
