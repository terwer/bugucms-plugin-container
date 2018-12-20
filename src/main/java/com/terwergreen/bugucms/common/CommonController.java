package com.terwergreen.bugucms.common;

import com.terwergreen.bugucms.container.BugucmsPluginManager;
import com.terwergreen.core.CommonService;
import com.terwergreen.plugins.PluginInterface;
import com.terwergreen.pojo.SiteConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pf4j.RuntimeMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 公共控制器
 *
 * @author Terwer
 * @version 1.0
 * 2018/11/26 18:05
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

    @Autowired(required = false)
    private BugucmsPluginManager pluginManager;

    @RequestMapping(method = RequestMethod.GET)
    public String index(){
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
        model.addAttribute("info", "BuguCMS 2.1.1:" + webname);
        String adminPath = siteConfig.getAdminpath();
        model.addAttribute("info", "BuguCMS 2.1.1:" + webname);
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

    @RequestMapping(value = "api/site/config", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public SiteConfig siteConfig() {
        return commonService.getSiteConfig();
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