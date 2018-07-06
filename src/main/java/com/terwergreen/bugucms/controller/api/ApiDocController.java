package com.terwergreen.bugucms.controller.api;

import com.alibaba.fastjson.JSON;
import com.terwergreen.bugucms.core.service.CommonService;
import com.terwergreen.bugucms.dto.SiteConfigDTO;
import com.terwergreen.bugucms.exception.WebException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Terwer
 */
@Controller
public class ApiDocController {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    private CommonService commonService;

    @ApiIgnore
    @RequestMapping("/doc")
    public String index(Model model, HttpServletRequest request) throws Exception {
        SiteConfigDTO siteConfigDTO = null;
        try {
            siteConfigDTO = commonService.getSiteConfig();
            if (null == siteConfigDTO) {
                logger.error("站点配置异常:siteConfigDTO=null");
                throw new WebException("站点配置异常:siteConfigDTO=null");
            }
            model.addAttribute("siteConfigDTO", siteConfigDTO);
            logger.info("获取页面信息成功:siteConfigDTO=" + JSON.toJSONString(siteConfigDTO));
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "swagger/index";
    }
}
