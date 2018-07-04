package com.terwergreen.bugucms.cms.service;

import com.github.pagehelper.PageInfo;
import com.terwergreen.bugucms.cms.dto.PostDTO;

import java.util.List;

public interface PostService {
    /**
     * 查询最新文章
     *
     * @return
     */
    List<PostDTO> getPosts();

    /**
     * 查询单个文章
     *
     * @param slug
     * @return
     */
    PostDTO getPostBySlug(String slug);

    /**
     * 查询单个文章
     *
     * @param postId
     * @return
     */
    PostDTO getPostById(Integer postId);

    /**
     * 获取分页数据
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<PostDTO> getPostsByPage(Integer pageNum, Integer pageSize);
}
