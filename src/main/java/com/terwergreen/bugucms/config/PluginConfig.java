package com.terwergreen.bugucms.config;

import com.alibaba.fastjson.JSON;
import com.terwergreen.bugucms.container.BugucmsPluginManager;
import com.terwergreen.plugins.PluginInterface;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pf4j.RuntimeMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * 项目配置
 *
 * @author Terwer
 * @version 1.0
 * 2018/11/21 11:19
 **/
@SuppressWarnings("all")
@ConditionalOnProperty(name = "bugucms.plugin-switch", havingValue = "true")
@Configuration
public class PluginConfig {
    private static final Log logger = LogFactory.getLog(PluginConfig.class);

    @Value("${bugucms.plugin-switch}")
    private boolean pluginSwitch;

    @Autowired
    private BugucmsPluginManager pluginManager;

    public PluginConfig() {
        logger.info("插件配置开始");
    }

    @Bean
    public RouterFunction<?> pluginEndpoints() {
        if (pluginSwitch) {
            logger.debug("Load pluginEndpoints,pluginManager is:" + pluginManager);
            List<PluginInterface> pluginExtentions = pluginManager.getExtensions(PluginInterface.class);
            logger.info("Load pluginExtentions from plugins:" + pluginExtentions);
            //注册RouterFunction模式的webFlux
            RouterFunction<?> webFlux = getReactiveRoutes(pluginManager);
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
