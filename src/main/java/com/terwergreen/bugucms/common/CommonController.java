package com.terwergreen.bugucms.common;

import com.terwergreen.bugucms.container.BugucmsPluginManager;
import com.terwergreen.core.CommonService;
import com.terwergreen.plugins.PluginInterface;
import com.terwergreen.pojo.SiteConfig;
import com.terwergreen.util.RestResponse;
import com.terwergreen.util.RestResponseStates;
import org.pf4j.RuntimeMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 公共控制器
 *
 * @author Terwer
 * @version 1.0 2018/11/26 18:05
 **/
@SuppressWarnings("all")
@CrossOrigin
@Controller
@RequestMapping("/")
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Value("${bugucms.plugin-switch}")
    private boolean pluginSwitch;
    @Value("${pf4j.mode}")
    private String pf4jMode;
    @Value("${pf4j.plugins-dir}")
    private String pf4jPluginsDir;

    @Autowired
    private CommonService commonService;

    @Autowired(required = false)
    private BugucmsPluginManager pluginManager;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "redirect:/index.html";
    }

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("current username: " + auth.getName());
        model.addAttribute("username", auth.getName());
        model.addAttribute("isLogin", !auth.getName().equals("anonymousUser"));
        boolean isAdmin = auth.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("roles", auth.getAuthorities());

        SiteConfig siteConfig = commonService.getSiteConfig();
        String webname = siteConfig.getWebname();
        model.addAttribute("info", "BuguCMS - " + webname);
        String adminPath = siteConfig.getAdminpath();
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
    @RequestMapping(value = "api/plugins", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public List plugins() {
        return getPlugins();
    }

    @RequestMapping(value = "api/site/config/list", method = {RequestMethod.GET, RequestMethod.POST}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public RestResponse siteConfigList() {
        return this.siteConfig();
    }

    @RequestMapping(value = "api/site/config", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public RestResponse siteConfig() {
        RestResponse restResponse = new RestResponse();
        try {
            restResponse.setData(commonService.getSiteConfig());
            restResponse.setStatus(RestResponseStates.SUCCESS.getValue());
            restResponse.setMsg(RestResponseStates.SUCCESS.getMsg());
        } catch (Exception e) {
            restResponse.setStatus(RestResponseStates.SERVER_ERROR.getValue());
            restResponse.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
        }
        return restResponse;
    }

    /**
     * 注册webFlux
     *
     * @return
     */
    private List getPlugins() {
        if (null == pluginManager) {
            ArrayList noPluginList = new ArrayList();
            noPluginList.add("plugin is disabled");
            return noPluginList;
        }
        return baseRoot(pluginManager);
    }

    /**
     * 插件信息接口
     *
     * @param pm
     * @return
     */
    private List baseRoot(BugucmsPluginManager pm) {
        return pm.getExtensions(PluginInterface.class).stream().map(g -> g.getClass().getName() + ": " + g.identify()).collect(Collectors.toList());
    }
}