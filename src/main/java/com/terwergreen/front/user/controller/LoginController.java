package com.terwergreen.front.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terwergreen.middle.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.terwergreen.front.common.dto.RestResponseDTO;
import com.terwergreen.front.common.util.RestResponseStates;
import com.terwergreen.front.common.web.BGBaseController;

@Controller
public class LoginController extends BGBaseController {

    @Autowired
    private LoginService loginService;

    /***********/
    /**页面开始**/
    /***********/

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) {
        return new ModelAndView();
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
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
        return new ModelAndView("reg", paramMap);
    }

    @RequestMapping(value = "/findpwd", method = RequestMethod.GET)
    public ModelAndView findpwd(HttpServletRequest request) {
        return new ModelAndView();
    }

    /***********/
    /**页面结束**/
    /***********/

    /**
     * 注册接口
     *
     * @return
     * @author terwergreen
     * @date 2018-03-07 13:36:00
     */
    @RequestMapping(value = "/user/login")
    @ResponseBody
    public RestResponseDTO login(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "username", required = true) String username,
                                 @RequestParam(value = "password", required = true) String password
    ) {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        response.setContentType("application/json;charset=utf-8");
        try {
            super.logger.info("请求开始");
            Map resultMap = loginService.login(username,password);
            restResponseDTO.setData(resultMap);

            restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
            restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
            logger.debug("登陆成功");
            super.logger.info("请求结束");
        } catch (Exception e) {
            super.logger.error("接口异常:error=", e);
            restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
            restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            return restResponseDTO;
        }
        return restResponseDTO;
    }

    /**
     * 注册接口
     *
     * @return
     * @author terwergreen
     * @date 2018-03-07 13:36:00
     */
    @RequestMapping(value = "/user/register")
    @ResponseBody
    public RestResponseDTO register(HttpServletRequest request,
                                    HttpServletResponse response) {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        response.setContentType("application/json;charset=utf-8");
        try {
            super.logger.info("请求开始");

            Map<String, String> resultMap = new HashMap<String, String>();
            resultMap.put("accountId", "F00001");
            resultMap.put("mobile", "15986685029");
            restResponseDTO.setData(resultMap);

            restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
            restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
            logger.debug("注册成功，您的注册信息：用户名F10001。");
            super.logger.info("请求结束");
        } catch (Exception e) {
            super.logger.error("接口异常:error=", e);
            restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
            restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            return restResponseDTO;
        }
        return restResponseDTO;
    }
}
