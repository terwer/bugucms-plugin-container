package com.terwergreen.front.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.terwergreen.bugucms.exception.WebException;
import com.terwergreen.middle.common.dto.SiteConfigDTO;
import com.terwergreen.middle.common.service.CommonService;
import com.terwergreen.middle.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.terwergreen.framework.core.bg.controller.BGBaseController;
import com.terwergreen.front.common.dto.RestResponseDTO;
import com.terwergreen.front.common.util.RestResponseStates;

@Controller
@SuppressWarnings({"rawtypes"})
public class LoginController extends BGBaseController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private CommonService commonService;

    /***********/
    /**页面开始**/
    /***********/

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() throws Exception{
        try {
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return new ModelAndView();
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public ModelAndView reg() throws Exception {
        new HashMap<String, String>();
        SiteConfigDTO siteConfigDTO = null;
        try {
            siteConfigDTO = commonService.getSiteConfig();
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return new ModelAndView("reg", "siteConfigDTO", siteConfigDTO);
    }

    @RequestMapping(value = "/findpwd", method = RequestMethod.GET)
    public ModelAndView findpwd(HttpServletRequest request) throws Exception{
        try {
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return new ModelAndView();
    }

    /***********/
    /**页面结束**/
    /***********/

    /**
     * 登录接口
     *
     * @return
     * @author terwergreen
     * @date 2018-03-07 13:36:00
     */
    @RequestMapping(value = "/user/login")
    @ResponseBody
    public RestResponseDTO login(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "account", required = true) String account,
                                 @RequestParam(value = "password", required = true) String password
    ) {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        response.setContentType("application/json;charset=utf-8");
        try {
            super.logger.info("请求开始");
            Map resultMap = loginService.login(account, password);
            restResponseDTO.setData(resultMap);

            restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
            restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
            logger.debug("登陆成功");
            super.logger.info("请求结束");
        } catch (Exception e) {
            logger.error("接口异常:error=", e);
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
    public RestResponseDTO register(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "account", required = true) String account,
                                    @RequestParam(value = "password", required = true) String password
    ) {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        response.setContentType("application/json;charset=utf-8");
        try {
            super.logger.info("请求开始");

            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("accountId", "F00001");
            paramMap.put("account", account);
            paramMap.put("password", password);

//            Map resultMap = loginService.register(account, password);
//            restResponseDTO.setData(resultMap);

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
