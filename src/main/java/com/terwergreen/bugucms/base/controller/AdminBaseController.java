package com.terwergreen.bugucms.base.controller;

import com.terwergreen.bugucms.base.exception.WebException;
import com.terwergreen.bugucms.base.service.CommonService;
import com.terwergreen.bugucms.dto.SiteConfigDTO;
import com.terwergreen.bugucms.dto.SysUserDTO;
import com.terwergreen.bugucms.utils.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.terwergreen.bugucms.utils.Constants.AUTH_LOGIN_PAGE;

/**
 * @Author Terwer
 * @Date 2018/7/4 17:56
 * @Version 1.0
 * @Description 后台父控制器
 **/
public class AdminBaseController {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private CommonService commonService;

    public void preCheck(Model model, HttpServletRequest request, HttpServletResponse response, String adminpath) throws Exception {
        try {
            //获取站点配置
            SiteConfigDTO siteConfigDTO = commonService.getSiteConfig();
            if (null == siteConfigDTO) {
                logger.error("站点配置异常:siteConfigDTO=null");
                throw new WebException("站点配置异常:siteConfigDTO=null");
            }

            //检测后台路径配置
            if (!siteConfigDTO.getAdminpath().equals(adminpath)) {
                throw new WebException(Constants.ADMIN_PATH_ERROR_MESSAGE);
            }

            //获得当前登陆用户对应的对象
            SysUserDTO sysUserDTO = null;
            if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SysUserDTO) {
                sysUserDTO = (SysUserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            } else {
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;

                String authUrl = basePath + AUTH_LOGIN_PAGE;
                response.sendRedirect(authUrl);
                throw new WebException(Constants.ADMIN_USER_NOT_LOGIN);
            }

            model.addAttribute("siteConfigDTO", siteConfigDTO);
            model.addAttribute("sysUserDTO", sysUserDTO);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
    }
}
