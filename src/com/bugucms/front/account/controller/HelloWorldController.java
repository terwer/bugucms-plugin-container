package com.bugucms.front.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by terwergreen on 2017-11-30.
 */
@Controller
public class HelloWorldController {

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public ModelAndView Hello(HttpServletRequest request) {
        MappingJacksonJsonView json = new MappingJacksonJsonView();
        json.setContentType("application/json;charset=utf-8");
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("flag", "1");
        resultMap.put("msg", "Success");
        return new ModelAndView(json, resultMap);
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> Hello2(HttpServletRequest request) {
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("flag", "1");
        resultMap.put("msg", "Success2");
        return resultMap;
    }

    @RequestMapping(value = "/hello3.do", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> Hello3(HttpServletRequest request) {
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("flag", "1");
        resultMap.put("msg", "Success3");
        return resultMap;
    }

    @RequestMapping(value = "/hello4/hello4.do", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> Hello4(HttpServletRequest request) {
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("flag", "1");
        resultMap.put("msg", "Success4，成功了");
        return resultMap;
    }

    @RequestMapping(value = "/hello5", method = RequestMethod.GET)
    public Model Hello5(HttpServletRequest request, Model model) {
        model.addAttribute("msg", "星期五了");
        return model;
    }

    @RequestMapping(value = "/hello6", method = RequestMethod.GET)
    public ModelAndView Hello6() {
        ModelAndView mv = new ModelAndView("hello6");
        mv.addObject("msg", "明天不上班");
        return mv;
    }

    @RequestMapping(value = "/hello7", method = RequestMethod.GET)
    public Map<String, String> Hello7(HttpServletRequest request) {
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("msg", "恩，放假了");
        return resultMap;
    }
}