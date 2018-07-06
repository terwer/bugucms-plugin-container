package com.terwergreen.bugucms.controller.app.admin;

import com.terwergreen.bugucms.base.controller.AdminBaseController;
import com.terwergreen.bugucms.exception.WebException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Terwer
 * @Date 2018/7/6 10:52
 * @Version 1.0
 * @Description TODO
 **/
@Controller
@RequestMapping(value = "/{adminpath}/doc")
public class DocController extends AdminBaseController {

    /***********/
    /** 页面开始 **/
    /***********/

    @RequestMapping("quickstart")
    public String quickstart(Model model, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "doc/quickstart";
    }

    @RequestMapping("menu")
    public String menu(Model model, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "doc/menu";
    }

    @RequestMapping("tabs")
    public String tabs(Model model, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "doc/tabs";
    }

    @RequestMapping("other")
    public String other(Model model, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "doc/other";
    }

    @RequestMapping("about")
    public String about(Model model, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "doc/about";
    }

    @RequestMapping("changelog")
    public String changelog(Model model, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "doc/changelog";
    }

    @RequestMapping("license")
    public String license(Model model, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "doc/license";
    }

    /***********/
    /** 页面结束 **/
    /***********/
}
