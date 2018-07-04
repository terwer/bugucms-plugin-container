package com.terwergreen.bugucms.cms.dto;

import com.terwergreen.bugucms.common.util.HtmlUtils;
import com.terwergreen.bugucms.common.util.ImageUtils;
import com.terwergreen.bugucms.common.util.MarkdownUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Setter
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
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    @Getter
    @Setter
    private Date postDate;

    public void setPostContent(String postContent) {
        this.postRawContent = postContent;
        this.postContent = MarkdownUtils.md2html(postContent);
        this.thumbnails = ImageUtils.getImgSrc(this.getPostContent());
        this.postDesc = HtmlUtils.parseHtml(this.getPostContent(), 320);
    }
}
