package com.terwergreen.front.homepage.controller;

import com.terwergreen.bugucms.exception.RestException;
import com.terwergreen.bugucms.exception.WebException;
import com.terwergreen.framework.core.bg.controller.BGBaseController;
import com.terwergreen.middle.common.dto.SiteConfigDTO;
import com.terwergreen.middle.common.service.CommonService;
import com.terwergreen.middle.user.UserDTO;
import com.terwergreen.middle.user.service.LoginService;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomePageController extends BGBaseController {

    @Autowired
    private CommonService commonService;
    @Autowired
    private LoginService loginService;

    /***********/
    /** 页面开始 **/
    /***********/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) throws Exception {
        SiteConfigDTO siteConfigDTO = null;
        UserDTO userDTO = null;
        ModelAndView mv = new ModelAndView();
        try {
            siteConfigDTO = commonService.getSiteConfig();
            if (null == siteConfigDTO) {
                logger.error("站点配置异常:siteConfigDTO=null");
                throw new WebException("站点配置异常:siteConfigDTO=null");
            }
            userDTO = loginService.getLoginUserInfo(request.getSession());
            mv.setViewName("themes/" + siteConfigDTO.getWebtheme() + "/index");
            mv.addObject("siteConfigDTO", siteConfigDTO);
            mv.addObject("userDTO", userDTO);
            logger.info("获取页面信息成功:siteConfigDTO=" + JSONValue.toJSONString(siteConfigDTO) + ",userDTO=" + userDTO);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return mv;
    }

    /***********/
    /** 页面结束 **/
    /***********/

}
