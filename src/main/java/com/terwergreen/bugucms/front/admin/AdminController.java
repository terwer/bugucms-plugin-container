package com.terwergreen.bugucms.front.admin;

import com.terwergreen.exception.WebException;
import com.terwergreen.core.controller.BGBaseController;
import com.terwergreen.bugucms.middle.common.dto.SiteConfigDTO;
import com.terwergreen.bugucms.middle.common.service.CommonService;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "admin")
public class AdminController extends BGBaseController {

    @Autowired
    private CommonService commonService;

    /***********/
    /** 页面开始 **/
    /***********/

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) throws Exception {
        SiteConfigDTO siteConfigDTO = null;
        try {
            siteConfigDTO = commonService.getSiteConfig();
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        model.addAttribute("siteConfigDTO", siteConfigDTO);
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) throws Exception {
        SiteConfigDTO siteConfigDTO = null;
        try {
            siteConfigDTO = commonService.getSiteConfig();
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        model.addAttribute("siteConfigDTO", siteConfigDTO);
        return "register";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) throws Exception {
        SiteConfigDTO siteConfigDTO = null;
        ModelAndView mv = new ModelAndView("admin/main");
        try {
            siteConfigDTO = commonService.getSiteConfig();
            if (null == siteConfigDTO) {
                logger.error("站点配置异常:siteConfigDTO=null");
                throw new WebException("站点配置异常:siteConfigDTO=null");
            }
            mv.addObject("siteConfigDTO", siteConfigDTO);
            logger.info("获取页面信息成功:siteConfigDTO=" + JSONValue.toJSONString(siteConfigDTO));
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return mv;
    }

    @RequestMapping("/index")
    public ModelAndView index(Model model, HttpServletRequest request) throws Exception {
//        Cookie[] cookie=request.getCookies();
//        if (cookie!=null) {
//            //获取cookie中保存的用户名和密码
//            for (Cookie aCookie : cookie) {
//                if (aCookie.getName().equalsIgnoreCase(EncryptAndDecrypt.MD5("loginUser"))) {
//                    byte[] cookieValue = java.util.Base64.getDecoder().decode(aCookie.getValue().getBytes("UTF-8"));
//                    String loginUser = new String(Objects.requireNonNull(EncryptAndDecrypt.decrypt(cookieValue, "bugucms.terwer")), "UTF-8");
//                    int num = loginUser.indexOf("@");
//                    model.addAttribute("loginName", loginUser.substring(0, num));
//                    model.addAttribute("loginPwd", loginUser.substring(num + 1));
//                    model.addAttribute("remember", 1);
//                    break;
//                }
//            }
//        }
        SiteConfigDTO siteConfigDTO = null;
        try {
            siteConfigDTO = commonService.getSiteConfig();
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return new ModelAndView("admin/index", "siteConfigDTO", siteConfigDTO);
    }

    /***********/
    /** 页面结束 **/
    /***********/
}
