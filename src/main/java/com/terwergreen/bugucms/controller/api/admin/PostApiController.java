package com.terwergreen.bugucms.controller.api.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.terwergreen.bugucms.base.controller.BGBaseController;
import com.terwergreen.bugucms.base.exception.RestException;
import com.terwergreen.bugucms.dto.PostDTO;
import com.terwergreen.bugucms.dto.PostMetaDTO;
import com.terwergreen.bugucms.dto.RestResponseDTO;
import com.terwergreen.bugucms.dto.SysUserDTO;
import com.terwergreen.bugucms.service.PostService;
import com.terwergreen.bugucms.utils.Constants;
import com.terwergreen.bugucms.utils.PostStatusEnum;
import com.terwergreen.bugucms.utils.PostTypeEmum;
import com.terwergreen.bugucms.utils.RestResponseStates;
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
                timelineMap.put("praise", post.getPraiseCount());
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
                                   PostDTO post
    ) throws Exception {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        try {
            //获得当前登陆用户对应的对象
            SysUserDTO sysUser = null;
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SysUserDTO) {
                sysUser = (SysUserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                post.setPostAuthor(sysUser.getId());
            } else {
                post.setPostAuthor(1);
            }
            if (StringUtils.isEmpty(post.getPostType())) {
                String postType = PostTypeEmum.POST_TYPE_ESSAY.getName();
                post.setPostType(postType);
            }
            if (StringUtils.isEmpty(post.getPostStatus())) {
                String postStatus = PostStatusEnum.POST_STATUS_PUBLISH.getName();
                post.setPostStatus(postStatus);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dtPostDate = sdf.parse(sdf.format(new Date()));
            if (StringUtils.isEmpty(post.getPostDate())) {
                post.setPostDate(dtPostDate);
            }
            if (StringUtils.isEmpty(post.getPostRawContent())) {
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
    public RestResponseDTO updatePost(Model model, HttpServletRequest request, HttpServletResponse response,
                                      PostDTO post) throws Exception {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        try {
            logger.info("开始修改，PostDTO=:" + JSON.toJSONString(post));
            boolean flag = postService.editPostById(post);
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

    @RequestMapping(value = "/api/post/meta/{postId}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public RestResponseDTO updatePostMeta(Model model, @PathVariable("postId") Integer postId, String metaKey, String metaValue) throws Exception {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        try {
            PostMetaDTO postMeta = new PostMetaDTO();
            postMeta.setPostId(postId);
            postMeta.setMetaKey(metaKey);
            postMeta.setMetaValue(metaValue);
            boolean result = postService.saveOrUpdatePostMeta(postMeta);
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
