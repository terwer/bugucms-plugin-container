package com.terwergreen.bugucms.admin.controller;

import com.terwergreen.base.controller.AdminBaseController;
import com.terwergreen.exception.WebException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/{adminpath}")
public class AdminController extends AdminBaseController {

    /***********/
    /** 页面开始 **/
    /***********/

    @RequestMapping("/")
    public String main(Model model, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "index";
    }

    @RequestMapping("console")
    public String console(Model model, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "console/console";
    }

    @RequestMapping("about")
    public String about(Model model, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "console/about";
    }

    @RequestMapping("changelog")
    public String changelog(Model model, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "console/changelog";
    }

    @RequestMapping("license")
    public String license(Model model, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "console/license";
    }

    @RequestMapping("user")
    public String userIndex(Model model, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "user/user_index";
    }

    /***********/
    /** 页面结束 **/
    /***********/
}
