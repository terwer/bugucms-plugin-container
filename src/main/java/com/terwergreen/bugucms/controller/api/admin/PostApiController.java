package com.terwergreen.bugucms.controller.api.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.terwergreen.bugucms.base.controller.AdminBaseController;
import com.terwergreen.bugucms.base.controller.BGBaseController;
import com.terwergreen.bugucms.dto.PostDTO;
import com.terwergreen.bugucms.exception.RestException;
import com.terwergreen.bugucms.service.PostService;
import com.terwergreen.bugucms.util.Constants;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Terwer
 * @Date 2018/7/6 10:05
 * @Version 1.0
 * @Description 文章相关API接口
 **/
@RestController
@RequestMapping("/")
public class PostApiController extends BGBaseController {

    @Resource
    private PostService postService;

    @RequestMapping(value = "/api/post/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getPosts(Model model, HttpServletRequest request, HttpServletResponse response, Integer page, Integer limit) throws Exception {
        Map resultMap = new HashMap();

        if (page == null) {
            page = Constants.DEFAULT_PAGE_NUM;
        }
        if (limit == null) {
            limit = Constants.DEFAULT_PAGE_SIZE;
        }

        try {
            super.preCheck(model, request, response);
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
        return JSON.toJSONString(resultMap);
    }

}
