package com.terwergreen.base.controller;

import com.terwergreen.bugucms.admin.dto.SysUserDTO;
import com.terwergreen.bugucms.common.dto.SiteConfigDTO;
import com.terwergreen.bugucms.common.service.CommonService;
import com.terwergreen.exception.WebException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

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

    public void preCheck(Model model) throws Exception {
        try {
            //获取站点配置
            SiteConfigDTO siteConfigDTO = commonService.getSiteConfig();
            if (null == siteConfigDTO) {
                logger.error("站点配置异常:siteConfigDTO=null");
                throw new WebException("站点配置异常:siteConfigDTO=null");
            }

            //获得当前登陆用户对应的对象
            SysUserDTO sysUserDTO = null;
            if (null != SecurityContextHolder.getContext().getAuthentication().getPrincipal()) {
                sysUserDTO = (SysUserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            } else {
                sysUserDTO = null;
            }

            model.addAttribute("siteConfigDTO", siteConfigDTO);
            model.addAttribute("sysUserDTO", sysUserDTO);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
    }
}
