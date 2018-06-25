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

    @RequestMapping("workspace")
    public String workspace() {
        return "workspace/workspace_index";
    }

    @RequestMapping("user")
    public String userIndex() {
        return "user/user_index";
    }

    /***********/
    /** 页面结束 **/
    /***********/
}
