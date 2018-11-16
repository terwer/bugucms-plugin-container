package com.terwergreen.bugucms.controller.app.cms;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.terwergreen.bugucms.base.controller.BGBaseController;
import com.terwergreen.bugucms.base.service.CommonService;
import com.terwergreen.bugucms.dto.PostDTO;
import com.terwergreen.bugucms.dto.SiteConfigDTO;
import com.terwergreen.bugucms.dto.SysUserDTO;
import com.terwergreen.bugucms.base.exception.WebException;
import com.terwergreen.bugucms.service.PostService;
import com.terwergreen.bugucms.utils.PostStatusEnum;
import com.terwergreen.bugucms.utils.PostTypeEmum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.terwergreen.bugucms.utils.Constants.DEFAULT_PAGE_SIZE;

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
    public ModelAndView home(HttpServletRequest request, String q, Integer page) throws Exception {
        SiteConfigDTO siteConfigDTO = null;
        SysUserDTO sysUserDTO = null;
        List<PostDTO> dingPostList = null;
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

            Map paramMap = new HashMap();
            paramMap.put("postStatus", PostStatusEnum.POST_STATUS_PUBLISH.getName());
            paramMap.put("postType", PostTypeEmum.POST_TYPE_POST.getName());
            if (!StringUtils.isEmpty(q)) {
                if (PostTypeEmum.POST_TYPE_NOTE.getName().equals(q)) {
                    paramMap.put("postType", PostTypeEmum.POST_TYPE_NOTE.getName());
                } else {
                    paramMap.put("search", q);
                }
            }
            if (null == page) {
                page = 1;
            }
            PageInfo postListInfo = postService.getPostsByPage(page, DEFAULT_PAGE_SIZE, paramMap);
            postList = postListInfo.getList();

            Map dingParamMap = new HashMap();
            dingParamMap.put("postType", PostTypeEmum.POST_TYPE_POST.getName());
            dingParamMap.put("metaKey", "ding");
            dingPostList = postService.getRecentPosts(dingParamMap);

            mv.setViewName("themes/" + siteConfigDTO.getWebtheme() + "/index");
            mv.addObject("siteConfigDTO", siteConfigDTO);
            mv.addObject("sysUserDTO", sysUserDTO);
            mv.addObject("dingPostList", dingPostList);
            mv.addObject("page", postListInfo.getNextPage());
            mv.addObject("postList", postList);
            logger.info("获取页面信息成功:siteConfigDTO=" + JSON.toJSONString(siteConfigDTO) + ",sysUserDTO=" + sysUserDTO + ",postList=" + postList);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return mv;
    }

    @RequestMapping(value = "/post/page/{page}", method = RequestMethod.GET)
    public ModelAndView postPageList(HttpServletRequest request, String q, @PathVariable("page") Integer page) throws Exception {
        return home(request, q, page);
    }

    @RequestMapping(value = "/essay.html", method = RequestMethod.GET)
    public ModelAndView essay(HttpServletRequest request) throws Exception {
        SiteConfigDTO siteConfigDTO = null;
        SysUserDTO sysUserDTO = null;
        // List<PostDTO> essayList = null;
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
            // Map paramMap = new HashMap();
            // paramMap.put("postType", PostTypeEmum.POST_TYPE_ESSAY.getName());
            // essayList = postService.getRecentPosts(paramMap);
            mv.setViewName("themes/" + siteConfigDTO.getWebtheme() + "/essay");
            mv.addObject("siteConfigDTO", siteConfigDTO);
            mv.addObject("sysUserDTO", sysUserDTO);
            // mv.addObject("essayList", essayList);
            logger.info("获取页面信息成功:siteConfigDTO=" + JSON.toJSONString(siteConfigDTO) + ",sysUserDTO=" + sysUserDTO);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return mv;
    }

    @RequestMapping(value = "/guestbook.html", method = RequestMethod.GET)
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

    @RequestMapping(value = "/about.html", method = RequestMethod.GET)
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

    /***********/
    /** 页面结束 **/
    /***********/

}
