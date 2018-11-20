package com.terwergreen.bugucms.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Terwer
 * @Date 2018/11/20 14:01
 * @Version 1.0
 * @Description 菜单
 **/
@Getter
@Setter
public class MenuDTO {
    /**
     * 菜单ID
     */
    private Integer menuId;
    /**
     * 父级ID
     */
    private Integer parentId;
    /**
     * 菜单名称
     */
    private String title;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 链接
     */
    private String href;
}
