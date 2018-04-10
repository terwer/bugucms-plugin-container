package com.terwergreen.front.homepage.controller;

import com.terwergreen.framework.core.bg.controller.BGBaseController;
import com.terwergreen.middle.common.dto.SiteConfigDTO;
import com.terwergreen.middle.common.service.CommonService;
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

    /***********/
    /**页面开始**/
    /***********/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {
        SiteConfigDTO siteConfigDTO = null;
        try {
            siteConfigDTO = commonService.getSiteConfig();
        } catch (Exception e) {
            super.logger.error("接口异常:error=", e);
            new ModelAndView("error", "message", "接口异常");
        }
        return new ModelAndView("themes/" + siteConfigDTO.getWebtheme() + "/index", "siteConfigDTO", siteConfigDTO);
    }

    /***********/
    /**页面结束**/
    /***********/

}
