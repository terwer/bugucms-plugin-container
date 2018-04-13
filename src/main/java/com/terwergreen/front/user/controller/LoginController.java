package com.terwergreen.front.user.controller;

import com.terwergreen.bugucms.exception.RestException;
import com.terwergreen.bugucms.exception.WebException;
import com.terwergreen.framework.core.bg.controller.BGBaseController;
import com.terwergreen.front.common.dto.RestResponseDTO;
import com.terwergreen.front.common.util.RestResponseStates;
import com.terwergreen.front.user.util.Constants;
import com.terwergreen.middle.common.dto.SiteConfigDTO;
import com.terwergreen.middle.common.service.CommonService;
import com.terwergreen.middle.user.UserDTO;
import com.terwergreen.middle.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
    public ModelAndView login() throws Exception {
        SiteConfigDTO siteConfigDTO = null;
        try {
            siteConfigDTO = commonService.getSiteConfig();
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return new ModelAndView("login", "siteConfigDTO", siteConfigDTO);
    }

    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public ModelAndView reg() throws Exception {
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
    public ModelAndView findpwd(HttpServletRequest request) throws Exception {
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
    ) throws Exception {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        response.setContentType("application/json;charset=utf-8");
        try {
            logger.info("请求开始");
            //校验登录结果
            Map resultMap = loginService.login(account, password);
            logger.debug("登陆结果:resultMap=" + resultMap);

            //loginStatus取值:0正常，1账号不存在，2密码错误
            String loginStatus = (String) resultMap.get("loginStatus");
            //登录校验成功，返回登录信息
            if (com.terwergreen.front.common.util.Constants.BG_ZERO.equals(loginStatus)) {
                //简单处理，登陆信息写入Session，Tomcat管理
                request.getSession().setAttribute(Constants.SESSION_ACCOUNT_MOBILE, account);

                //返回登录信息
                UserDTO userDTO = loginService.getLoginUserInfo(request.getSession());
                resultMap.put("userInfo", userDTO);
                restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
                restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
                //账号不存在
            } else if (com.terwergreen.front.common.util.Constants.BG_ONE.equals(loginStatus)) {
                restResponseDTO.setFlag(RestResponseStates.INVALID_ACCOUNT.getValue());
                restResponseDTO.setMsg(RestResponseStates.INVALID_ACCOUNT.getMsg());
            } else {
                restResponseDTO.setFlag(RestResponseStates.INVALID_PASSWORD.getValue());
                restResponseDTO.setMsg(RestResponseStates.INVALID_PASSWORD.getMsg());
            }

            restResponseDTO.setData(resultMap);

            logger.info("请求结束");
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new RestException(e);
        }
        return restResponseDTO;
    }

    /**
     * 推出接口
     *
     * @return
     * @author terwergreen
     * @date 2018-04-14 13:36:00
     */
    @RequestMapping(value = "/user/logout")
    @ResponseBody
    public RestResponseDTO logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        response.setContentType("application/json;charset=utf-8");
        try {
            logger.info("请求开始");

            //简单处理，移除Session，Tomcat管理
            request.getSession().removeAttribute(Constants.SESSION_ACCOUNT_MOBILE);

            restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
            restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());

            logger.info("请求结束");
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new RestException(e);
        }
        return restResponseDTO;
    }

    /**
     * 校验账号是否存在
     *
     * @return
     * @author terwergreen
     * @date 2018-04-14 13:36:00
     */
    @RequestMapping(value = "/user/validateAccount")
    @ResponseBody
    public String validateAccount(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "account", required = true) String account,
                                  @RequestParam(value = "type", required = true) String type,
                                  @RequestParam(value = "accountType", required = true) String accountType
    ) throws Exception {
        String result = null;
        try {
            logger.info("请求开始");
            result = loginService.validateAccount(account, type, accountType);
            logger.debug("账号校验结果:result=" + result);
            logger.info("请求结束");
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new RestException(e);
        }
        return result;
    }

    /**
     * 校验密码
     *
     * @return
     * @author terwergreen
     * @date 2018-03-07 13:36:00
     */
    @RequestMapping(value = "/user/validatePassword")
    @ResponseBody
    public String validatePassword(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam(value = "account", required = true) String account,
                                   @RequestParam(value = "type", required = true) String type,
                                   @RequestParam(value = "accountType", required = true) String accountType,
                                   @RequestParam(value = "password", required = true) String password
    ) throws Exception {
        String result = null;
        try {
            logger.info("请求开始");
            result = loginService.validatePassword(account, type, accountType, password);
            logger.debug("密码校验结果:result=" + result);
            logger.info("请求结束");
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new RestException(e);
        }
        return result;
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
    ) throws Exception {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        response.setContentType("application/json;charset=utf-8");
        try {
            logger.info("请求开始");

            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("accountId", "F00001");
            paramMap.put("account", account);
            paramMap.put("password", password);

//            Map resultMap = loginService.register(account, password);
//            restResponseDTO.setData(resultMap);

            restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
            restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
            logger.debug("注册成功，您的注册信息：用户名F10001。");
            logger.info("请求结束");
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new RestException(e);
        }
        return restResponseDTO;
    }
}
