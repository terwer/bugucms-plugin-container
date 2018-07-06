package com.terwergreen.bugucms.controller.app.admin;

import com.terwergreen.bugucms.base.controller.AdminBaseController;
import com.terwergreen.bugucms.exception.WebException;
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

    /***********/
    /** 页面结束 **/
    /***********/
}