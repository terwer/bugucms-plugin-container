package com.terwergreen.bugucms.controller.app.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.terwergreen.bugucms.base.controller.AdminBaseController;
import com.terwergreen.bugucms.dto.PostDTO;
import com.terwergreen.bugucms.service.PostService;
import com.terwergreen.bugucms.util.Constants;
import com.terwergreen.bugucms.exception.RestException;
import com.terwergreen.bugucms.exception.WebException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Terwer
 * @Date 2018/7/4 10:11
 * @Version 1.0
 * @Description 文章管理
 **/
@Controller
@RequestMapping(value = "/{adminpath}/postManage")
public class PostManageController extends AdminBaseController {

    @Resource
    private PostService postService;

    @RequestMapping("new")
    public String add(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, request, response, adminpath);
            initPage(model, 0);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "postManage/post_edit";
    }

    @RequestMapping("edit/{postId}")
    public String edit(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("postId") Integer postId, @PathVariable("adminpath") String adminpath) throws Exception {

        try {
            super.preCheck(model, request, response, adminpath);
            initPage(model, postId);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "postManage/post_edit";
    }

    @RequestMapping("list")
    public String list(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, request, response, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "postManage/post_list";
    }

    @RequestMapping("cat")
    public String cat(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, request, response, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "postManage/post_cat";
    }

    @RequestMapping("tag")
    public String tag(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, request, response, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "postManage/post_tag";
    }

    /**
     * 初始化页面
     *
     * @param model
     */
    private void initPage(Model model, Integer postId) throws Exception {
        try {
            PostDTO post = null;
            if (postId > 0) {
                post = postService.getPostById(postId);
            }
            model.addAttribute("post", post);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
    }
}
