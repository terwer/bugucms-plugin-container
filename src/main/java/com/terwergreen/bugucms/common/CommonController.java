package com.terwergreen.bugucms.common;

import com.terwergreen.core.CommonService;
import org.pf4j.RuntimeMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Terwer
 * @Date 2018/11/26 18:05
 * @Version 1.0
 * @Description 公共控制器
 **/
@Controller
@RequestMapping("/")
public class CommonController {

    @Value("${bugucms.pluginSwitch}")
    private boolean pluginSwitch;
    @Value("${pf4j.mode}")
    private String pf4jMode;
    @Value("${pf4j.pluginsDir}")
    private String pf4jPluginsDir;

    @Autowired
    private CommonService commonService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        String webname = (String) commonService.getSiteConfig("webname");
        String adminPath = (String) commonService.getSiteConfig("adminPath");
        model.addAttribute("info", "BuguCMS 2.0.0:" + webname);
        model.addAttribute("adminPath",adminPath);
        String pluginInfo = "pluginSwitch:" + (pluginSwitch ? "开启" : "关闭");
        pluginInfo += "<br/>pf4j.mode:";
        pluginInfo += pf4jMode.equals("dev") ? RuntimeMode.DEVELOPMENT.toString() : RuntimeMode.DEPLOYMENT.toString();
        pluginInfo += "<br/>pf4j.pluginsDir:";
        pluginInfo += pf4jPluginsDir;
        model.addAttribute("pluginInfo", pluginInfo);
        return "container";
    }
}