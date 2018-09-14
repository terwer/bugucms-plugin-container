package com.terwergreen.bugucms.controller.api.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.terwergreen.bugucms.base.controller.BGBaseController;
import com.terwergreen.bugucms.dto.PostDTO;
import com.terwergreen.bugucms.dto.RestResponseDTO;
import com.terwergreen.bugucms.dto.SysUserDTO;
import com.terwergreen.bugucms.exception.RestException;
import com.terwergreen.bugucms.exception.WebException;
import com.terwergreen.bugucms.service.PostService;
import com.terwergreen.bugucms.util.Constants;
import com.terwergreen.bugucms.util.PostStatusEnum;
import com.terwergreen.bugucms.util.PostTypeEmum;
import com.terwergreen.bugucms.util.RestResponseStates;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    public String getPosts(Model model, HttpServletRequest request, HttpServletResponse response, String postType, String search, Integer page, Integer limit) throws Exception {
        Map resultMap = new HashMap();

        if (page == null) {
            page = Constants.DEFAULT_PAGE_NUM;
        }
        if (limit == null) {
            limit = Constants.DEFAULT_PAGE_SIZE;
        }

        try {
            super.preCheck(model, request, response);
            Map paramMap = new HashMap();
            if (!StringUtils.isEmpty(postType)) {
                paramMap.put("postType", postType);
            }
            if (!StringUtils.isEmpty(search)) {
                paramMap.put("search", search);
            }
            PageInfo<PostDTO> posts = postService.getPostsByPage(page, limit, paramMap);
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

    @RequestMapping(value = "/api/post/essay", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getEssays(Model model, HttpServletRequest request, HttpServletResponse response, Integer page, Integer limit) throws Exception {
        Map resultMap = new HashMap();

        if (page == null) {
            page = Constants.DEFAULT_PAGE_NUM;
        }
        if (limit == null) {
            limit = Constants.DEFAULT_PAGE_SIZE;
        }

        try {
            Map paramMap = new HashMap();
            paramMap.put("postType", PostTypeEmum.POST_TYPE_ESSAY.getName());
            PageInfo<PostDTO> posts = postService.getPostsByPage(page, limit, paramMap);

            //转换成说说需要的格式
            List<Map> timelines = new ArrayList<>();
            for (PostDTO post : posts.getList()) {
                Integer postId = post.getPostId();
                Date postDate = post.getPostDate();
                String postTitle = post.getPostTitle();
                String postDesc = post.getPostDesc();
                String postContent = post.getPostContent();

                Map timelineMap = new HashMap();
                // SimpleDateFormat keySdf = new SimpleDateFormat("yyyyMMddHHmmss");
                // timelineMap.put("key", keySdf.format(postDate));
                timelineMap.put("key", postId);
                SimpleDateFormat yearSdf = new SimpleDateFormat("yyyy");
                timelineMap.put("year", yearSdf.format(postDate));
                SimpleDateFormat titleSdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                timelineMap.put("title", titleSdf.format(postDate));
                if (postDesc.endsWith("...")) {
                    postContent = postDesc;
                }
                timelineMap.put("content", postContent);
                timelines.add(timelineMap);
            }

            Map jsonMap = new HashMap();
            jsonMap.put("timelines", timelines);

            resultMap.put("code", 0);
            resultMap.put("msg", "success");
            resultMap.put("count", posts.getTotal());
            resultMap.put("data", jsonMap);
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

    @RequestMapping(value = "/api/post/new", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public RestResponseDTO newPost(Model model, HttpServletRequest request, HttpServletResponse response,
                                   String postType,
                                   String postStatus,
                                   String postTitle,
                                   String postContent,
                                   String postDate
    ) throws Exception {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        try {
            //登录检测延后开发 TODO
            //super.preCheck(model, request, response);

            PostDTO post = new PostDTO();
            //获得当前登陆用户对应的对象
            SysUserDTO sysUser = null;
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SysUserDTO) {
                sysUser = (SysUserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                post.setPostAuthor(sysUser.getId());
            } else {
                post.setPostAuthor(1);
                //throw new WebException(Constants.ADMIN_USER_NOT_LOGIN);
            }
            if (StringUtils.isEmpty(postType)) {
                postType = PostTypeEmum.POST_TYPE_ESSAY.getName();
            }
            if (StringUtils.isEmpty(postStatus)) {
                postStatus = PostStatusEnum.POST_STATUS_PUBLISH.getName();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dtPostDate = sdf.parse(sdf.format(new Date()));
            if (!StringUtils.isEmpty(postDate)) {
                dtPostDate = sdf.parse(postDate);
            }
            post.setPostType(postType);
            post.setPostStatus(postStatus);
            if (!StringUtils.isEmpty(postTitle)) {
                post.setPostTitle(postTitle);
            }
            post.setPostContent(postContent);
            post.setPostDate(dtPostDate);
            if (StringUtils.isEmpty(postContent)) {
                logger.error("文章信息内容不能为空");
                restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
                restResponseDTO.setMsg("文章信息内容不能为空");
            }
            logger.info("准备新增文章，文章信息：" + JSON.toJSONString(post));
            Integer postId = postService.newPost(post);
            if (postId > 0) {
                logger.info("文章信息添加成功，postId：" + postId);
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("postId", postId);
                restResponseDTO.setData(resultMap);
                restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
                restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
            } else {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("postId", 0);
                restResponseDTO.setData(resultMap);
                restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
                restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            }
        } catch (Exception e) {
            super.logger.error("接口异常:error=", e);
            restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
            restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            throw new RestException(e);
        }
        return restResponseDTO;
    }

    @RequestMapping(value = "/api/post/update", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public RestResponseDTO newPost(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        try {
            boolean flag = false;
            if (flag) {
                logger.info("文章信息修改");
                restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
                restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
            } else {
                restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
                restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            }
        } catch (Exception e) {
            super.logger.error("接口异常:error=", e);
            restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
            restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            throw new RestException(e);
        }
        return restResponseDTO;
    }

    @RequestMapping(value = "/api/post/delete/{postId}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public RestResponseDTO deletePost(Model model, @PathVariable("postId") Integer postId) throws Exception {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        try {
            boolean result = postService.deletePostById(postId);
            if (result) {
                restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
                restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
            } else {
                restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
                restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            }
        } catch (Exception e) {
            super.logger.error("接口异常:error=", e);
            restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
            restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            throw new RestException(e);
        }
        return restResponseDTO;
    }
}
