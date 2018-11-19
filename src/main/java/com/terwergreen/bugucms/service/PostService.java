package com.terwergreen.bugucms.service;

import com.github.pagehelper.PageInfo;
import com.terwergreen.bugucms.base.exception.BusinessServiceException;
import com.terwergreen.bugucms.dto.PostDTO;
import com.terwergreen.bugucms.dto.PostMetaDTO;

import java.util.List;
import java.util.Map;

public interface PostService {
    /**
     * 查询最新文章
     *
     * @return
     */
    List<PostDTO> getRecentPosts(Map paramMap) throws BusinessServiceException;

    /**
     * 查询单个文章
     *
     * @param slug
     * @return
     */
    PostDTO getPostBySlug(String slug) throws BusinessServiceException;

    /**
     * 查询单个文章
     *
     * @param postId
     * @return
     */
    PostDTO getPostById(Integer postId) throws BusinessServiceException;

    /**
     * 获取分页数据
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<PostDTO> getPostsByPage(Integer pageNum, Integer pageSize, Map paramMap) throws BusinessServiceException;

    /**
     * 获取博客信息
     *
     * @param appkey
     * @param username
     * @param password
     * @return
     * @throws BusinessServiceException
     */
    List getUsersBlogs(String appkey, String username, String password) throws BusinessServiceException;

    /**
     * 新建文章
     *
     * @param post 文章
     * @return 新文章的ID
     */
    Integer newPost(PostDTO post);

    /**
     * 更新文章
     *
     * @param post 文章
     * @return 是否修改成功
     */
    boolean editPostById(PostDTO post);

    /**
     * 根据别名删除文章
     *
     * @param postSlug 文章别名
     * @return 是否删除成功
     */
    boolean deletePostBySlug(String postSlug);

    /**
     * 根据ID删除文章
     *
     * @param postId 文章ID
     * @return 是否删除成功
     */
    boolean deletePostById(Integer postId);

    /**
     * 新增或者修改文章属性
     * @param postMeta
     * @return
     */
    boolean saveOrUpdatePostMeta(PostMetaDTO postMeta);
}
