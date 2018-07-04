package com.terwergreen.bugucms.admin.controller;

import com.github.pagehelper.PageInfo;
import com.terwergreen.base.controller.AdminBaseController;
import com.terwergreen.bugucms.cms.dto.PostDTO;
import com.terwergreen.bugucms.cms.service.PostService;
import com.terwergreen.bugucms.common.util.Constants;
import com.terwergreen.exception.RestException;
import com.terwergreen.exception.WebException;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Terwer
 * @Date 2018/7/4 10:11
 * @Version 1.0
 * @Description 文章管理
 **/
@Controller
@RequestMapping(value = "/admin/postManage")
public class PostManageController extends AdminBaseController {

    @Autowired
    private PostService postService;

    @RequestMapping("new")
    public String add(Model model) throws Exception {
        try {
            super.preCheck(model);
            initPage(model, 0);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "postManage/post_edit";
    }

    @RequestMapping("edit/{postId}")
    public String edit(Model model, @PathVariable("postId") Integer postId) throws Exception {

        try {
            super.preCheck(model);
            initPage(model, postId);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "postManage/post_edit";
    }

    @RequestMapping("list")
    public String list(Model model) throws Exception {
        try {
            super.preCheck(model);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "postManage/post_list";
    }

    @RequestMapping("cat")
    public String cat(Model model) throws Exception {
        try {
            super.preCheck(model);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "postManage/post_cat";
    }

    @RequestMapping("tag")
    public String tag(Model model) throws Exception {
        try {
            super.preCheck(model);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "postManage/post_tag";
    }

    @RequestMapping("/api/post/list")
    @ResponseBody
    public String getPosts(Model model, Integer page, Integer limit) throws Exception {
        Map resultMap = new HashMap();

        if (page == null) {
            page = Constants.DEFAULT_PAGE_NUM;
        }
        if (limit == null) {
            limit = Constants.DEFAULT_PAGE_SIZE;
        }

        try {
            super.preCheck(model);
            PageInfo<PostDTO> posts = postService.getPostsByPage(page, limit);
            resultMap.put("code", 0);
            resultMap.put("msg", "success");
            resultMap.put("count", posts.getTotal());
            resultMap.put("data", posts.getList());
        } catch (Exception e) {
            resultMap.put("code", 0);
            resultMap.put("msg", "");
            resultMap.put("count", 0);
            resultMap.put("data", null);
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new RestException(e);
        }
        return JSONValue.toJSONString(resultMap);
    }

    /**
     * 初始化页面
     *
     * @param model
     */
    private void initPage(Model model, Integer postId) {
        PostDTO post = null;
        if (postId > 0) {
            post = postService.getPostById(postId);
        }
        model.addAttribute("post", post);
    }
}
