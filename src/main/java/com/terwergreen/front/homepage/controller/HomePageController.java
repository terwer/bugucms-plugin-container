package com.terwergreen.front.homepage.controller;

import com.terwergreen.front.common.web.BGBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        paramMap.put("weburl", "http://localhost:8080/bugucms");
        paramMap.put("webname", "特维博客");
        paramMap.put("webtheme", "专注于服务端技术分享");
        paramMap.put("keywords", "软件架构、服务端开发、Java、Spring、Dubbo、Zookeeper、微服务");
        paramMap.put("description", "特维博客致力于Java后端开发及服务端技术、软件架构、微服务技术分享。");
        paramMap.put("beianinfo", "粤ICP备18023717号-1");
        return new ModelAndView("index", paramMap);
    }

    /***********/
    /**页面结束**/
    /***********/

}
