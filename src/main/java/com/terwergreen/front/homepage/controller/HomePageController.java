package com.terwergreen.front.homepage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.terwergreen.framework.core.bg.controller.BGBaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomePageController extends BGBaseController {

    /***********/
    /**页面开始**/
    /***********/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView reg(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<String, String>();
        // paramMap.put("domain", "terwergreen.com");
        // paramMap.put("weburl", "http://www.terwergreen.com");
        paramMap.put("domain", "localhost");
        paramMap.put("weburl", "http://localhost:8080");
        paramMap.put("webtheme", "default");
        paramMap.put("webname", "远方的灯塔");
        paramMap.put("webslogen", "专注于服务端技术分享");
        paramMap.put("keywords", "软件架构、服务端开发、Java、Spring、Dubbo、Zookeeper、微服务");
        paramMap.put("description", "远方的灯塔(特维博客)是关注与分享互联网及服务端开发技术的个人博客，致力于Java后端开发及服务端技术、软件架构、微服务技术分享。同时也记录个人的一路点滴，所蕴含的包括前端、后端、数据库等知识，欢迎您关注我们。");
        paramMap.put("beianinfo", "粤ICP备18023717号-1");
        return new ModelAndView("themes/"+paramMap.get("webtheme")+"/index", paramMap);
    }

    /***********/
    /**页面结束**/
    /***********/

}
