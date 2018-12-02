package com.terwergreen.bugucms.common;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.terwergreen.bugucms.container.BugucmsPluginManager;
import com.terwergreen.core.CommonService;
import com.terwergreen.plugins.PluginInterface;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pf4j.RuntimeMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Terwer
 * @Date 2018/11/26 18:05
 * @Version 1.0
 * @Description 公共控制器
 **/
@Controller
@RequestMapping("/")
public class CommonController {
    private static final Log logger = LogFactory.getLog(CommonController.class);

    @Value("${bugucms.plugin-switch}")
    private boolean pluginSwitch;
    @Value("${pf4j.mode}")
    private String pf4jMode;
    @Value("${pf4j.plugins-dir}")
    private String pf4jPluginsDir;

    @Autowired
    private CommonService commonService;

    @Autowired
    private BugucmsPluginManager pluginManager;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        String webname = (String) commonService.getSiteConfig("webname");
        model.addAttribute("info", "BuguCMS 2.0.0:" + webname);
        String adminPath = (String) commonService.getSiteConfig("adminPath");
        model.addAttribute("info", "BuguCMS 2.0.0:" + webname);
        model.addAttribute("adminPath", adminPath);
        String pluginInfo = "pluginSwitch:" + (pluginSwitch ? "开启" : "关闭");
        pluginInfo += "<br/>pf4j.mode:";
        pluginInfo += pf4jMode.equals("dev") ? RuntimeMode.DEVELOPMENT.toString() : RuntimeMode.DEPLOYMENT.toString();
        pluginInfo += "<br/>pf4j.pluginsDir:";
        pluginInfo += pf4jPluginsDir;
        model.addAttribute("pluginInfo", pluginInfo);
        logger.info("容器运行正常");
        return "container";
    }

    @ConditionalOnProperty(name = "bugucms.web.application-type", havingValue = "servlet")
    @RequestMapping(value = "plugins", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String plugins() {
        return getPlugins();
    }

    /**
     * 注册webFlux
     *
     * @return
     */
    private String getPlugins() {
        String base = baseRoot(pluginManager);
        return base;
    }

    /**
     * 插件信息接口
     *
     * @param pm
     * @return
     */
    private String baseRoot(BugucmsPluginManager pm) {
        List<String> identityList = pm.getExtensions(PluginInterface.class).stream().map(g -> g.getClass().getName() + ": " + g.identify()).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(identityList)) {
            return JSON.toJSONString(identityList);
        } else {
            return "[]";
        }
    }
}