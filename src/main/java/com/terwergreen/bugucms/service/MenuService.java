package com.terwergreen.bugucms.service;

import com.terwergreen.bugucms.dto.MenuDTO;

import java.util.List;

/**
 * @Author Terwer
 * @Date 2018/11/20 14:04
 * @Version 1.0
 * @Description 菜单
 **/
public interface MenuService {
    /**
     * 获取菜单列表
     *
     * @param parentId 父级菜单ID
     * @return
     */
    List<MenuDTO> getMenuList(Integer parentId);
}
