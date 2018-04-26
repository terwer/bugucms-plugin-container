package com.terwergreen.front.homepage.controller;

import com.terwergreen.bugucms.exception.WebException;
import com.terwergreen.framework.core.bg.controller.BGBaseController;
import com.terwergreen.front.common.util.ImageUtils;
import com.terwergreen.middle.common.dto.SiteConfigDTO;
import com.terwergreen.middle.common.service.CommonService;
import com.terwergreen.middle.post.dto.PostDTO;
import com.terwergreen.middle.post.service.PostService;
import com.terwergreen.middle.user.dto.UserDTO;
import com.terwergreen.middle.user.service.LoginService;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomePageController extends BGBaseController {

    @Autowired
    private CommonService commonService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private PostService postService;

    /***********/
    /** 页面开始 **/
    /***********/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) throws Exception {
        SiteConfigDTO siteConfigDTO = null;
        UserDTO userDTO = null;
        List<PostDTO> postList = null;
        ModelAndView mv = new ModelAndView();
        try {
            siteConfigDTO = commonService.getSiteConfig();
            if (null == siteConfigDTO) {
                logger.error("站点配置异常:siteConfigDTO=null");
                throw new WebException("站点配置异常:siteConfigDTO=null");
            }
            userDTO = loginService.getLoginUserInfo(request.getSession());
            postList = postService.getPosts();
            mv.setViewName("themes/" + siteConfigDTO.getWebtheme() + "/index");
            mv.addObject("siteConfigDTO", siteConfigDTO);
            mv.addObject("userDTO", userDTO);
            mv.addObject("postList", postList);
            logger.info("获取页面信息成功:siteConfigDTO=" + JSONValue.toJSONString(siteConfigDTO) + ",userDTO=" + userDTO + ",postList=" + postList);
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
