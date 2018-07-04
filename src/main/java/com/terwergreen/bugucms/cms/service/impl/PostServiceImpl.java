package com.terwergreen.bugucms.cms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.terwergreen.bugucms.core.dao.CommonDAO;
import com.terwergreen.bugucms.cms.dto.PostDTO;
import com.terwergreen.bugucms.cms.service.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public List<PostDTO> getPosts() {
        Map paramMap = new HashMap();
        List<PostDTO> posts = (List<PostDTO>) commonDAO.queryListByMap("get_posts_by_user", paramMap);
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
    public PostDTO getPostBySlug(String slug) {
        Map paramMap = new HashMap();
        paramMap.put("slug", slug);
        PostDTO post = (PostDTO) commonDAO.querySingle("get_post_by_slug", paramMap);
        return post;
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Map paramMap = new HashMap();
        paramMap.put("postId", postId);
        PostDTO post = (PostDTO) commonDAO.querySingle("get_post_by_id", paramMap);
        return post;
    }

    @Override
    public PageInfo<PostDTO> getPostsByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PostDTO> list = (List<PostDTO>) commonDAO.queryList("get_posts_by_page");
        // 分页信息
        PageInfo<PostDTO> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();
        pageNum = pageInfo.getPageNum();
        pageSize = pageInfo.getPageSize();
        logger.info("分页信息：total=" + total + "，pages=" + pages + "，pageNum=" + pageNum + "，pageSize=" + pageSize);

        if (CollectionUtils.isEmpty(pageInfo.getList())) {
            return null;
        } else {
            return pageInfo;
        }
    }
}
