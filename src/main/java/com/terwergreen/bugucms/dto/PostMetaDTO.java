package com.terwergreen.bugucms.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Terwer
 * @Date 2018/11/15 20:28
 * @Version 1.0
 * @Description 文章属性
 **/
@Getter
@Setter
public class PostMetaDTO {
    private Integer postId;
    private String metaKey;
    private String metaValue;
    private Integer count;
}
