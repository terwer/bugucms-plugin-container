package com.terwergreen.bugucms.middle.admin.service.impl;

import com.terwergreen.bugucms.front.user.util.Constants;
import com.terwergreen.bugucms.middle.admin.dto.MenuInfoDTO;
import com.terwergreen.bugucms.middle.admin.service.MenuInfoService;
import com.terwergreen.bugucms.middle.common.dao.CommonDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jordan
 */
@Service
public class MenuInfoServiceImpl implements MenuInfoService {
    protected static final Logger logger = LogManager.getLogger(MenuInfoServiceImpl.class);


    @Resource
    private CommonDAO commonDAO;

    /**
     * 查询第一级菜单
     *
     * @param session HttpSession
     * @return 第一级菜单
     */
    @Override
    public List<MenuInfoDTO> searchMenuFirstByUserId(HttpSession session) {
        int userId = (int) session.getAttribute(Constants.SESSION_USER_ID);
        List<MenuInfoDTO> list = commonDAO.queryListByObject("selectMenuFirstByUserId", userId);
        return this.addSessionId(list, session);
    }

    /**
     * 查询子级菜单
     *
     * @param jurisdictionId 权限id
     * @param parentId       父级菜单id
     * @return 子级菜单
     */
    @Override
    public List<MenuInfoDTO> searchMenuNextByUserId(Integer jurisdictionId, Integer parentId, HttpSession session) {
        Map paramMap = new HashMap();
        paramMap.put("jurisdictionId", jurisdictionId);
        paramMap.put("parentId", parentId);
        List<MenuInfoDTO> list = commonDAO.queryList("selectMenuNextByParentId", paramMap);
        return this.addSessionId(list, session);
    }

    /**
     * 菜单url添加sessionId
     *
     * @param list    菜单列表
     * @param session HttpSession
     */
    private List<MenuInfoDTO> addSessionId(List<MenuInfoDTO> list, HttpSession session) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (!StringUtils.isEmpty(list.get(i).getMenuUrl()) && !StringUtils.isEmpty(list.get(i).getMenuUrl().trim())) {
                    list.get(i).setMenuUrl(list.get(i).getMenuUrl());
                }
            }
        }
        return list;
    }
}
