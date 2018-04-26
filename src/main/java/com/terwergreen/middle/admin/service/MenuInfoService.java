package com.terwergreen.middle.admin.service;

import com.terwergreen.middle.admin.dto.MenuInfoDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Jordan
 */
public interface MenuInfoService {
    /**
     * 查询第一级菜单
     * @param session HttpSession
     * @return 第一级菜单
     */
    List<MenuInfoDTO> searchMenuFirstByUserId(HttpSession session);
    /**
     * 查询子级菜单
     * @param jurisdictionId 权限id
     * @param parentId 父级菜单id
     * @return 子级菜单
     */
    List<MenuInfoDTO> searchMenuNextByUserId(Integer jurisdictionId, Integer parentId, HttpSession session);
}
