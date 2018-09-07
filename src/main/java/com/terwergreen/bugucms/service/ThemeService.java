package com.terwergreen.bugucms.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ThemeService {
    /**
     * 修改主题
     *
     * @param theme 主题名
     * @return
     */
    boolean changeTheme(String theme);

    /**
     * 获取系统可用的主题列表
     *
     * @param request
     * @return
     */
    List<String> getAvailableThemes(HttpServletRequest request);
}
