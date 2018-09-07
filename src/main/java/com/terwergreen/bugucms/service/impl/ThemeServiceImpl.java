package com.terwergreen.bugucms.service.impl;

import com.terwergreen.bugucms.service.ThemeService;
import org.apache.commons.compress.compressors.FileNameUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class ThemeServiceImpl implements ThemeService {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Value("${bugucms.theme.path}")
    String themePath;

    @Override
    public boolean changeTheme(String theme) {
        return false;
    }

    @Override
    public List<String> getAvailableThemes(HttpServletRequest request) {
        List<String> resultList = new ArrayList<>();
        // 获取项目根路径
        String realPath = request.getSession().getServletContext().getRealPath("/");
        // 获取主题所在的文件夹
        String themeFullPath = FilenameUtils.concat(realPath, themePath);
        // 获取主题目录
        File dir = new File(themeFullPath);
        File[] dirs = dir.listFiles();
        if (null != dirs && dirs.length > 0) {
            for (File d : dirs) {
                //添加主题文件夹
                if (d.isDirectory()) {
                    resultList.add(d.getName());
                }
            }
        }
        logger.debug("themeFullPath:" + themeFullPath);
        logger.debug("info themeFullPath:" + themeFullPath);
        logger.error("error themeFullPath:" + themeFullPath);
        // resultList.add(themeFullPath);
        return resultList;
    }
}
