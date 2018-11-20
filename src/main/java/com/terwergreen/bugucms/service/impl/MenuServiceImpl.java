package com.terwergreen.bugucms.service.impl;

import com.terwergreen.bugucms.base.dao.CommonDAO;
import com.terwergreen.bugucms.dto.MenuDTO;
import com.terwergreen.bugucms.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Terwer
 * @Date 2018/11/20 14:07
 * @Version 1.0
 * @Description 菜单
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private CommonDAO commonDAO;

    @Override
    public List<MenuDTO> getMenuList(Integer parentId) {
        List<MenuDTO> menuList = commonDAO.queryListByObject("getMenuListByParentId", parentId);
        if (!CollectionUtils.isEmpty(menuList)) {
            for (MenuDTO menuDTO : menuList) {
                Integer mid = menuDTO.getMenuId();
                // 递归获取子菜单
                List<MenuDTO> childMenuList = getMenuList(mid);
                if (!CollectionUtils.isEmpty(menuList)) {
                    menuDTO.setList(childMenuList);
                }
            }
        }
        return menuList;
    }
}
