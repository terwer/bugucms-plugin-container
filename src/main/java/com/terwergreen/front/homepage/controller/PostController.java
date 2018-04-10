package com.terwergreen.front.homepage.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.terwergreen.framework.core.bg.controller.BGBaseController;
import com.terwergreen.middle.common.dto.SiteConfigDTO;
import com.terwergreen.middle.common.service.CommonService;

@Controller
public class PostController extends BGBaseController {

    @Autowired
    private CommonService commonService;


    /***********/
    /**页面开始**/
    /***********/

    @RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
    public ModelAndView postById(HttpServletRequest request,
                                 @PathVariable(name = "postId") String postId) {
        //去除后缀
        postId = postId.replace(".html","");
        //文章ID和文章别名分开处理
        if (StringUtils.isNumeric(postId)) {
            logger.debug("文章ID为：" + postId);
        } else {
            logger.debug("文章别名为：" + postId);
        }
        SiteConfigDTO siteConfigDTO = null;
        try {
            siteConfigDTO = commonService.getSiteConfig();
        } catch (Exception e) {
            super.logger.error("接口异常:error=", e);
            new ModelAndView("error", "message", "接口异常");
        }
        return new ModelAndView("themes/" + siteConfigDTO.getWebtheme() + "/post", "siteConfigDTO", siteConfigDTO);
    }

    /***********/
    /**页面结束**/
    /***********/
}
