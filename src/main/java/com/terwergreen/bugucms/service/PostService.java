package com.terwergreen.bugucms.service;

import com.github.pagehelper.PageInfo;
import com.terwergreen.bugucms.base.service.BusinessServiceException;
import com.terwergreen.bugucms.dto.PostDTO;

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

    List getUsersBlogs(String appkey, String username, String password) throws BusinessServiceException;

}
