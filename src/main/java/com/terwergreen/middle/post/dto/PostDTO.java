package com.terwergreen.middle.post.dto;

public class PostDTO {
    /**
     * 文章ID
     */
    private int postId;
    /**
     * 文章别名
     */
    private String postSlug;
    /**
     * 文章标题
     */
    private String postTitle;
    /**
     * 文章内容
     */
    private String postContent;
    /**
     * 原始内容，数据库不保存
     */
    private String postRawContent;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostSlug() {
        return postSlug;
    }

    public void setPostSlug(String postSlug) {
        this.postSlug = postSlug;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
        this.postRawContent = postContent;
    }

    public String getPostRawContent() {
        return postRawContent;
    }
}
