package com.terwergreen.bugucms.handler;

/**
 * @Author Terwer
 * @Date 2018/7/6 19:28
 * @Version 1.0
 * @Description MetaWeblogHandler xmlrpc的文章处理器
 **/
public interface MetaWeblogHandler {
    /**
     * Retrieve a post.
     *
     * @param postid
     * @param username
     * @param password
     * @return struct
     * struct
     * int postid†
     * string title
     * string description: Post content.
     * string link: Post URL.
     * string userid†: ID of post author.
     * datetime dateCreated†
     * datetime date_created_gmt†
     * datetime date_modified† (Added in WordPress 3.4)
     * datetime date_modified_gmt† (Added in WordPress 3.4)
     * string wp_post_thumbnail†
     * string permaLink†: Post URL, equivalent to link.
     * array categories: Names of categories assigned to the post.
     * string mt_keywords: Names of tags assigned to the post.
     * string mt_excerpt
     * string mt_text_more: Post "Read more" text.
     * string wp_more_text
     * int mt_allow_comments
     * int mt_allow_pings
     * string wp_slug†
     * string wp_password†
     * string wp_author_id†
     * string wp_author_display_name†
     * string post_status†
     * string wp_post_format† (Added in WordPress 3.1)
     * bool sticky† (Added in WordPress 2.7.1)
     * array custom_fields† (Added in WordPress 2.3.2)
     * struct
     * string id
     * string key
     * string value
     * struct enclosure: Only set if post has an enclosure. (Added in WordPress 2.6.3)
     * string url
     * int length
     * string type
     * Errors
     * 401
     * If the user does not have permission to edit this post.
     * 404
     * If no post with that postid exists.
     * @see <a href="https://codex.wordpress.org/XML-RPC_MetaWeblog_API#metaWeblog.getPost">https://codex.wordpress.org/XML-RPC_MetaWeblog_API#metaWeblog.getPost</a>
     */
    String getPost(int postid, String username, String password);

    /**
     *
     * @param appkey Not applicable for Bugucms, can be any value and will be ignored
     * @param username
     * @param password
     * @return array
     * struct
     * string blogid
     * string url: Homepage URL for this blog.
     * string blogName
     * bool isAdmin†
     * string xmlrpc†: URL endpoint to use for XML-RPC requests on this blog.
     * @see <a href="https://codex.wordpress.org/XML-RPC_MetaWeblog_API#metaWeblog.getUsersBlogs">https://codex.wordpress.org/XML-RPC_MetaWeblog_API#metaWeblog.getUsersBlogs</a>
     */
    Object getUsersBlogs(String appkey, String username, String password);

    //metaWeblog.getCategories
    //metaWeblog.getRecentPosts
    //metaWeblog.newPost
}
