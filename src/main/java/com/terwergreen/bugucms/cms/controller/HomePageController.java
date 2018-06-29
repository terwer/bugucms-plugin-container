package com.terwergreen.bugucms.cms.controller;

import com.terwergreen.bugucms.admin.dto.SysUserDTO;
import com.terwergreen.bugucms.cms.dto.PostDTO;
import com.terwergreen.bugucms.cms.service.PostService;
import com.terwergreen.bugucms.common.dto.SiteConfigDTO;
import com.terwergreen.bugucms.common.service.CommonService;
import com.terwergreen.core.controller.BGBaseController;
import com.terwergreen.exception.WebException;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    //    @Autowired
//    private SysUserService sysUserService;
    @Autowired
    private PostService postService;

    /***********/
    /** 页面开始 **/
    /***********/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) throws Exception {
        SiteConfigDTO siteConfigDTO = null;
        SysUserDTO sysUserDTO = null;
        List<PostDTO> postList = null;
        ModelAndView mv = new ModelAndView();
        try {
            siteConfigDTO = commonService.getSiteConfig();
            if (null == siteConfigDTO) {
                logger.error("站点配置异常:siteConfigDTO=null");
                throw new WebException("站点配置异常:siteConfigDTO=null");
            }
            //获得当前登陆用户对应的对象。
            Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (currentUser != "anonymousUser") {
                sysUserDTO = (SysUserDTO) currentUser;
            }
            postList = postService.getPosts();
            mv.setViewName("themes/" + siteConfigDTO.getWebtheme() + "/index");
            mv.addObject("siteConfigDTO", siteConfigDTO);
            mv.addObject("sysUserDTO", sysUserDTO);
            mv.addObject("postList", postList);
            logger.info("获取页面信息成功:siteConfigDTO=" + JSONValue.toJSONString(siteConfigDTO) + ",sysUserDTO=" + sysUserDTO + ",postList=" + postList);
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
