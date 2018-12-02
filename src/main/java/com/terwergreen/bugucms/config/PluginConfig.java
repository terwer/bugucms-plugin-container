package com.terwergreen.bugucms.config;

import com.alibaba.fastjson.JSON;
import com.terwergreen.bugucms.container.BugucmsPluginManager;
import com.terwergreen.plugins.PluginInterface;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pf4j.RuntimeMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @Author Terwer
 * @Date 2018/11/21 11:19
 * @Version 1.0
 * @Description 项目配置
 **/
@ConditionalOnProperty(name = "bugucms.plugin-switch", havingValue = "true")
@Configuration
public class PluginConfig {
    private static final Log logger = LogFactory.getLog(PluginConfig.class);

    @Value("${bugucms.plugin-switch}")
    private boolean pluginSwitch;
    @Value("${pf4j.mode}")
    private String pf4jMode;
    @Value("${pf4j.plugins-dir}")
    private String pf4jPluginsDir;

    /**
     * 插件注入入口，注释此方法则插件功能关闭
     *
     * @return
     */
    @Bean
    public BugucmsPluginManager pluginManager() {
        logger.info("插件功能状态:" + (pluginSwitch ? "开启" : "关闭"));
        if (pluginSwitch) {
            // 插件开启，先设置环境变量
            System.setProperty("pf4j.mode", pf4jMode.equals("dev") ? RuntimeMode.DEVELOPMENT.toString() : RuntimeMode.DEPLOYMENT.toString());
            System.setProperty("pf4j.pluginsDir", pf4jPluginsDir);
            // 创建插件管理器
            return new BugucmsPluginManager();
        }
        return null;
    }

    @Bean
    public RouterFunction<?> pluginEndpoints() {
        if (pluginSwitch) {
            BugucmsPluginManager pm = pluginManager();
            logger.debug("Load pluginEndpoints,pluginManager is:" + pm);
            List<PluginInterface> pluginExtentions = pm.getExtensions(PluginInterface.class);
            logger.info("Load pluginExtentions from plugins:" + pluginExtentions);
            //注册RouterFunction模式的webFlux
            RouterFunction<?> webFlux = getReactiveRoutes(pm);
            return webFlux;
        }
        return null;
    }

    /**
     * 注册webFlux
     *
     * @param pm
     * @return
     */
    private RouterFunction<?> getReactiveRoutes(BugucmsPluginManager pm) {
        RouterFunction<?> base = baseRoot(pm);
        RouterFunction<?> routes = pm.getExtensions(PluginInterface.class).stream().flatMap(g -> g.reactiveRoutes().stream()).map(r -> (RouterFunction<ServerResponse>) r).reduce((o, r) -> (RouterFunction<ServerResponse>) o.andOther(r)).orElse(null);
        return routes == null ? base : base.andOther(routes);
    }

    /**
     * 插件信息接口
     *
     * @param pm
     * @return
     */
    private RouterFunction<?> baseRoot(BugucmsPluginManager pm) {
        return route(GET("/plugins"), req -> ServerResponse.ok().body(Mono.just(pluginNamesMono(pm)), String.class));
    }

    /**
     * 插件信息
     *
     * @param pm
     * @return
     */
    private String pluginNamesMono(BugucmsPluginManager pm) {
        List<String> identityList = pm.getExtensions(PluginInterface.class).stream().map(g -> g.getClass().getName() + ": " + g.identify()).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(identityList)) {
            return JSON.toJSONString(identityList);
        } else {
            return "[]";
        }
    }
}
