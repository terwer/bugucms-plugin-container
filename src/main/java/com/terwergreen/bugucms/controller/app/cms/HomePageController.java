package com.terwergreen.bugucms.controller.app.cms;

import com.alibaba.fastjson.JSON;
import com.terwergreen.bugucms.base.controller.BGBaseController;
import com.terwergreen.bugucms.dto.SysUserDTO;
import com.terwergreen.bugucms.dto.PostDTO;
import com.terwergreen.bugucms.service.PostService;
import com.terwergreen.bugucms.dto.SiteConfigDTO;
import com.terwergreen.bugucms.core.service.CommonService;
import com.terwergreen.bugucms.exception.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomePageController extends BGBaseController {

    @Autowired
    private CommonService commonService;
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
            logger.info("获取页面信息成功:siteConfigDTO=" + JSON.toJSONString(siteConfigDTO) + ",sysUserDTO=" + sysUserDTO + ",postList=" + postList);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return mv;
    }

    @RequestMapping(value = "/backend", method = RequestMethod.GET)
    public ModelAndView backend(HttpServletRequest request) throws Exception {
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
            logger.info("获取页面信息成功:siteConfigDTO=" + JSON.toJSONString(siteConfigDTO) + ",sysUserDTO=" + sysUserDTO + ",postList=" + postList);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return mv;
    }

    @RequestMapping(value = "/web", method = RequestMethod.GET)
    public ModelAndView web(HttpServletRequest request) throws Exception {
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
            logger.info("获取页面信息成功:siteConfigDTO=" + JSON.toJSONString(siteConfigDTO) + ",sysUserDTO=" + sysUserDTO + ",postList=" + postList);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return mv;
    }

    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public ModelAndView resource(HttpServletRequest request) throws Exception {
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
            logger.info("获取页面信息成功:siteConfigDTO=" + JSON.toJSONString(siteConfigDTO) + ",sysUserDTO=" + sysUserDTO + ",postList=" + postList);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return mv;
    }

    @RequestMapping(value = "/skill", method = RequestMethod.GET)
    public ModelAndView skill(HttpServletRequest request) throws Exception {
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
            logger.info("获取页面信息成功:siteConfigDTO=" + JSON.toJSONString(siteConfigDTO) + ",sysUserDTO=" + sysUserDTO + ",postList=" + postList);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return mv;
    }

    @RequestMapping(value = "/guestbook", method = RequestMethod.GET)
    public ModelAndView guestbook(HttpServletRequest request) throws Exception {
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
            mv.setViewName("themes/" + siteConfigDTO.getWebtheme() + "/guestbook");
            mv.addObject("siteConfigDTO", siteConfigDTO);
            mv.addObject("sysUserDTO", sysUserDTO);
            logger.info("获取页面信息成功:siteConfigDTO=" + JSON.toJSONString(siteConfigDTO) + ",sysUserDTO=" + sysUserDTO + ",postList=" + postList);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return mv;
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ModelAndView about(HttpServletRequest request) throws Exception {
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
            mv.setViewName("themes/" + siteConfigDTO.getWebtheme() + "/about");
            mv.addObject("siteConfigDTO", siteConfigDTO);
            mv.addObject("sysUserDTO", sysUserDTO);
            logger.info("获取页面信息成功:siteConfigDTO=" + JSON.toJSONString(siteConfigDTO) + ",sysUserDTO=" + sysUserDTO + ",postList=" + postList);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return mv;
    }

    @RequestMapping(value = "/shuoshuo", method = RequestMethod.GET)
    public ModelAndView shuoshuo(HttpServletRequest request) throws Exception {
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
            mv.setViewName("themes/" + siteConfigDTO.getWebtheme() + "/shuoshuo");
            mv.addObject("siteConfigDTO", siteConfigDTO);
            mv.addObject("sysUserDTO", sysUserDTO);
            logger.info("获取页面信息成功:siteConfigDTO=" + JSON.toJSONString(siteConfigDTO) + ",sysUserDTO=" + sysUserDTO + ",postList=" + postList);
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
