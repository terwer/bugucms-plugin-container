package com.terwergreen.bugucms.service;

import com.terwergreen.bugucms.base.exception.BusinessServiceException;
import com.terwergreen.bugucms.dto.ThemeDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ThemeService {
    /**
     * 修改主题
     *
     * @param theme 主题名
     * @return
     */
    boolean changeTheme(String theme) throws BusinessServiceException;

    /**
     * 获取系统可用的主题列表
     *
     * @param request
     * @return
     */
    List<ThemeDTO> getAvailableThemes(HttpServletRequest request) throws BusinessServiceException;
}
