package com.terwergreen.bugucms.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.terwergreen.bugucms.util.HtmlUtils;
import com.terwergreen.bugucms.util.ImageUtils;
import com.terwergreen.bugucms.util.MarkdownUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

public class PostDTO {
    /**
     * 文章ID
     */
    @Getter
    @Setter
    private int postId;
    /**
     * 文章别名
     */
    @Getter
    private String postSlug;
    /**
     * 文章标题
     */
    @Getter
    @Setter
    private String postTitle;
    /**
     * 文章内容
     */
    @Getter
    private String postContent;
    /**
     * 原始内容，数据库不保存
     */
    @Getter
    @Setter
    private String postRawContent;
    /**
     * 文章包含的图片，数据库不保存
     */
    @Getter
    @Setter
    private List<String> thumbnails;
    /**
     * 文章摘要，数据库不保存
     */
    @Getter
    @Setter
    private String postDesc;
    /**
     * 发布时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")  //FastJson包使用注解
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:MM")  //格式化前台日期参数注解
    @Getter
    @Setter
    private Date postDate;

    public void setPostSlug(String postSlug) {
        if (StringUtils.isEmpty(postSlug)) {
            this.postSlug = String.valueOf(this.getPostId());
        } else {
            this.postSlug = postSlug;
        }
    }

    public void setPostContent(String postContent) {
        this.postRawContent = postContent;
        this.postContent = MarkdownUtils.md2html(postContent);
        this.thumbnails = ImageUtils.getImgSrc(this.getPostContent());
        this.postDesc = HtmlUtils.parseHtml(this.getPostContent(), 240);
    }
}
