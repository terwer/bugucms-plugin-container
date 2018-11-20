package com.terwergreen.bugucms.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    /**
     * 事件
     */
    private String event;
    /**
     * 是否开启
     */
    private boolean open;
    /**
     * 子级菜单
     */
    private List<MenuDTO> list = new ArrayList<>();
}
