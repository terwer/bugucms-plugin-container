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
 * @Date 2018/7/6 10:53
 * @Version 1.0
 * @Description TODO
 **/
@Controller
@RequestMapping(value = "/{adminpath}/userManage")
public class UserManageController extends AdminBaseController{
    /***********/
    /** 页面开始 **/
    /***********/

    @RequestMapping("list")
    public String userIndex(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable("adminpath") String adminpath) throws Exception {
        try {
            super.preCheck(model,request,response, adminpath);
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
