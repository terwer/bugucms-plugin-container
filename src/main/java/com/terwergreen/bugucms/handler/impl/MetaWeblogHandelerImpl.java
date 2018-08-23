package com.terwergreen.bugucms.handler.impl;

import com.terwergreen.bugucms.handler.MetaWeblogHandler;
import com.terwergreen.bugucms.service.PostService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Terwer
 * @Date 2018/7/6 19:29
 * @Version 1.0
 * @Description MetaWeblogHandler xmlrpc的文章处理器的实现
 **/
public class MetaWeblogHandelerImpl implements MetaWeblogHandler {

    private Log logger = LogFactory.getLog(this.getClass());

    @Resource
    private PostService postService;

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    //{"permaLink":"http://localhost/wordpress/index.php/2018/07/04/hello-world/","wp_post_format":"standard","wp_slug":"hello-world","link":"http://localhost/wordpress/index.php/2018/07/04/hello-world/","wp_more_text":"","description":"欢迎使用WordPress。这是您的第一篇文章。编辑或删除它，然后开始写作吧！","postid":1,"title":"世界，您好！","userid":"1","wp_author_display_name":"admin","dateCreated":1530673525000,"categories":["未分类"],"post_status":"publish","wp_password":"","date_modified_gmt":1530644725000,"mt_text_more":"","mt_allow_comments":1,"custom_fields":[],"date_created_gmt":1530644725000,"mt_allow_pings":1,"date_modified":1530673525000,"sticky":false,"mt_keywords":"","wp_post_thumbnail":"","mt_excerpt":"","wp_author_id":"1"}
    @Override
    public String getPost(int postid, String username, String password) {
        return "MetaWeblogHandler";
    }

    //[{"blogName":"测试博客","xmlrpc":"http://localhost/wordpress/xmlrpc.php","isAdmin":true,"blogid":"1","url":"http://localhost/wordpress/"}]
    @Override
    public Object getUsersBlogs(String appkey, String username, String password) {
        List usersBlogs = null;
        try {
            usersBlogs = postService.getUsersBlogs(appkey, username, password);
        } catch (Exception e) {
            logger.error("系统异常：" + e.getLocalizedMessage(), e);
            return e.getClass().toString() + "：" + e.getLocalizedMessage();
        }
        return usersBlogs;
    }
}
