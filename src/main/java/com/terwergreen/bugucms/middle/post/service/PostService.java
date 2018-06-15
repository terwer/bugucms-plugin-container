package com.terwergreen.bugucms.middle.post.service;

import com.terwergreen.bugucms.middle.post.dto.PostDTO;

import java.util.List;

public interface PostService {
    /**
     * 查询最新文章
     * @return
     */
    List<PostDTO> getPosts();

    /**
     * 查询单个文章
     * @param slug
     * @return
     */
    PostDTO getPost(String slug);
}
