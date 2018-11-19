package com.terwergreen.bugucms.controller.app.admin;

import com.terwergreen.bugucms.base.controller.AdminBaseController;
import com.terwergreen.bugucms.base.exception.WebException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/{adminpath}")
public class AdminController extends AdminBaseController {

    /***********/
    /** 页面开始 **/
    /***********/

    @RequestMapping(value = "/")
    public String main(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("adminpath") String adminpath, String action, String title) throws Exception {
        try {
            super.preCheck(model, request, response, adminpath);
            if (StringUtils.isEmpty(action)) {
                action = "console";
            }
            if (StringUtils.isEmpty(title)) {
                title = "控制台";
            }
            model.addAttribute("action", action);
            model.addAttribute("title", title);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "index";
    }

    @RequestMapping("console")
    public String console(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, request, response, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "console/console";
    }

    /***********/
    /** 页面结束 **/
    /***********/
}
