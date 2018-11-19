package com.terwergreen.bugucms.controller.app.cms;

import com.alibaba.fastjson.JSON;
import com.terwergreen.bugucms.base.controller.BGBaseController;
import com.terwergreen.bugucms.dto.PostDTO;
import com.terwergreen.bugucms.dto.PostMetaDTO;
import com.terwergreen.bugucms.service.PostService;
import com.terwergreen.bugucms.dto.SiteConfigDTO;
import com.terwergreen.bugucms.base.service.CommonService;
import com.terwergreen.bugucms.utils.MarkdownUtils;
import com.terwergreen.bugucms.dto.SysUserDTO;
import com.terwergreen.bugucms.base.exception.WebException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/post", method = RequestMethod.GET)
public class PostController extends BGBaseController {

    @Autowired
    private CommonService commonService;
    @Autowired
    private PostService postService;

    /***********/
    /**页面开始**/
    /***********/

    @RequestMapping(value = "/{postId}", method = RequestMethod.GET)
    public ModelAndView postById(HttpServletRequest request,
                                 @PathVariable(name = "postId") String postId) throws Exception {
        //去除后缀
        postId = postId.replace(".html", "");

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

            //文章ID和文章别名分开处理
            if (StringUtils.isNumeric(postId)) {
                logger.debug("文章ID为：" + postId);
                post = postService.getPostById(Integer.parseInt(postId));
            } else {
                logger.debug("文章别名为：" + postId);
                post = postService.getPostBySlug(postId);
            }

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
            } else {
                PostMetaDTO viewCountPostMeta = new PostMetaDTO();
                viewCountPostMeta.setPostId(post.getPostId());
                viewCountPostMeta.setMetaKey("view_count");
                viewCountPostMeta.setMetaValue(String.valueOf(post.getViewCount() + 1));
                postService.saveOrUpdatePostMeta(viewCountPostMeta);
            }
            logger.info("获取文章成功:siteConfigDTO=" + JSON.toJSONString(siteConfigDTO) + ",post=" + post);
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
