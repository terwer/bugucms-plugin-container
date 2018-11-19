package com.terwergreen.bugucms.controller.app.admin;

import com.terwergreen.bugucms.base.controller.AdminBaseController;
import com.terwergreen.bugucms.base.exception.WebException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Terwer
 * @Date 2018/9/7 10:52
 * @Version 1.0
 * @Description 主题
 **/
@Controller
@RequestMapping(value = "/{adminpath}/themeManage")
public class ThemeManageController extends AdminBaseController {
    @RequestMapping("list")
    public String list(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model, request, response, adminpath);
        } catch (Exception e) {
            logger.error("系统异常" + e.getLocalizedMessage(), e);
            throw new WebException(e);
        }
        return "themeManage/theme_list";
    }
}
