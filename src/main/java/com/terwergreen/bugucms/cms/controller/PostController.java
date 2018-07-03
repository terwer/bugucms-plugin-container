package com.terwergreen.bugucms.cms.controller;

import javax.servlet.http.HttpServletRequest;

import com.terwergreen.bugucms.admin.dto.SysUserDTO;
import com.terwergreen.exception.WebException;
import com.terwergreen.bugucms.common.util.MarkdownUtils;
import com.terwergreen.bugucms.cms.dto.PostDTO;
import com.terwergreen.bugucms.cms.service.PostService;
import net.minidev.json.JSONValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.terwergreen.base.controller.BGBaseController;
import com.terwergreen.bugucms.common.dto.SiteConfigDTO;
import com.terwergreen.bugucms.common.service.CommonService;

@Controller
public class PostController extends BGBaseController {

    @Autowired
    private CommonService commonService;
    @Autowired
    private PostService postService;

    /***********/
    /**页面开始**/
    /***********/

    @RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
    public ModelAndView postById(HttpServletRequest request,
                                 @PathVariable(name = "postId") String postId) throws Exception {
        //去除后缀
        postId = postId.replace(".html", "");
        //文章ID和文章别名分开处理
        if (StringUtils.isNumeric(postId)) {
            logger.debug("文章ID为：" + postId);
        } else {
            logger.debug("文章别名为：" + postId);
        }
        //查询分类
        //logger.debug("分类为：" + categoryId);
        ModelAndView mv = new ModelAndView();

        SiteConfigDTO siteConfigDTO = null;
        SysUserDTO sysUserDTO = null;

        PostDTO post = null;
        try {
            siteConfigDTO = commonService.getSiteConfig();

            //获得当前登陆用户对应的对象。
            Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (currentUser != "anonymousUser") {
                sysUserDTO = (SysUserDTO) currentUser;
            }

            post = postService.getPost(postId);
            //将Markdown转换为Html显示
            String htmlContent = MarkdownUtils.md2html(post.getPostContent());
            post.setPostContent(htmlContent);
            mv.setViewName("themes/" + siteConfigDTO.getWebtheme() + "/post");
            mv.addObject("siteConfigDTO", siteConfigDTO);
            mv.addObject("sysUserDTO", sysUserDTO);
            mv.addObject("post", post);

            if (post == null) {
                logger.error("文章不存在");
                throw new WebException("文章不存在");
            }
            logger.info("获取文章成功:siteConfigDTO=" + JSONValue.toJSONString(siteConfigDTO) + ",post=" + post);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return mv;
    }

    /***********/
    /**页面结束**/
    /***********/
}
