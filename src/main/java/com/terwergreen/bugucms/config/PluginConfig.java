package com.terwergreen.bugucms.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.terwergreen.bugucms.container.BugucmsPluginManager;
import com.terwergreen.util.ReflectUtil;
import org.pf4j.RuntimeMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Terwer
 * @Date 2018/11/21 11:19
 * @Version 1.0
 * @Description 项目配置
 **/
@Configuration
public class PluginConfig {
    private static final Logger logger = LoggerFactory.getLogger(PluginConfig.class);
    @Value("${bugucms.pluginSwitch}")
    private boolean pluginSwitch;
    @Value("${pf4j.mode}")
    private String pf4jMode;
    @Value("${pf4j.pluginsDir}")
    private String pf4jPluginsDir;
    private final ObjectMapper objectMapper;

    public PluginConfig() {
        this.objectMapper = new ObjectMapper();
    }

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

//    @Bean
//    public RouterFunction<?> pluginEndpoints() {
//        if (pluginSwitch) {
//            BugucmsPluginManager pm = pluginManager();
//            logger.debug("Load pluginEndpoints,pluginManager is:" + pm);
//            List pluginExtentions = pm.getExtensions(Object.class);
//            logger.info("Load pluginExtentions from plugins:" + pluginExtentions);
//            //注册RouterFunction模式的webFlux
//            RouterFunction<?> webFlux = getReactiveRoutes(pm);
//            return webFlux;
//        }
//        return null;
//    }

    /**
     * 注册webFlux
     *
     * @param pm
     * @return
     */
//    private RouterFunction<?> getReactiveRoutes(BugucmsPluginManager pm) {
//        RouterFunction<?> base = baseRoot(pm);
//        RouterFunction<?> routes = pm.getExtensions(Object.class).stream().flatMap((Object g) -> {
//            List<?> reactiveRoutes = (List<?>) ReflectUtil.invoke(g, "reactiveRoutes");
//            if (null == reactiveRoutes) {
//                reactiveRoutes = new ArrayList<>();
//            }
//            return reactiveRoutes.stream();
//        }).map(r -> (RouterFunction<ServerResponse>) r).reduce((o, r) -> (RouterFunction<ServerResponse>) o.andOther(r)).orElse(null);
//        return routes == null ? base : base.andOther(routes);
//    }

    /**
     * 插件信息接口
     *
     * @param pm
     * @return
     */
//    private RouterFunction<?> baseRoot(BugucmsPluginManager pm) {
//        return route(GET("/plugins"), req -> ServerResponse.ok().body(Mono.just(pluginNamesMono(pm)), String.class));
//    }


    /**
     * 插件信息
     *
     * @param pm
     * @return
     */
    private String pluginNamesMono(BugucmsPluginManager pm) {
        List<String> identityList = pm.getExtensions(Object.class).stream().map((Object g) -> {
            String identify = (String) ReflectUtil.invoke(g, "identify");
            return g.getClass().getName() + ": " + identify;
        }).collect(Collectors.toList());
        try {
            return objectMapper.writeValueAsString(identityList);
        } catch (JsonProcessingException e) {
            return "[]";
        }
    }
}
