package com.terwergreen.bugucms.middle.post.service.impl;

import com.terwergreen.bugucms.middle.common.dao.CommonDAO;
import com.terwergreen.bugucms.middle.post.dto.PostDTO;
import com.terwergreen.bugucms.middle.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public List<PostDTO> getPosts() {
        Map paramMap = new HashMap();
        List<PostDTO> posts = (List<PostDTO>) commonDAO.queryList("get_posts_by_user", paramMap);
        if (CollectionUtils.isEmpty(posts)) {
            return null;
        } else {
            return posts;
        }
    }

    /**
     * 查询单个文章
     *
     * @param slug
     * @return
     */
    @Override
    public PostDTO getPost(String slug) {
        Map paramMap = new HashMap();
        paramMap.put("slug", slug);
        PostDTO post = (PostDTO) commonDAO.querySingle("get_post_by_slug", paramMap);
        return post;
    }
}
