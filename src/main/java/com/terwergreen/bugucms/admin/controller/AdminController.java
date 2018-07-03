package com.terwergreen.bugucms.admin.controller;

import com.terwergreen.bugucms.admin.dto.SysUserDTO;
import com.terwergreen.bugucms.common.service.CommonService;
import com.terwergreen.base.controller.BGBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping(value = "admin")
public class AdminController extends BGBaseController {

    @Autowired
    private CommonService commonService;

    /***********/
    /** 页面开始 **/
    /***********/

    @RequestMapping("console")
    public String workspace(Model model) {
        //获得当前登陆用户对应的对象。
        SysUserDTO sysUserDTO = (SysUserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获得当前登陆用户所拥有的所有权限。
        Collection<? extends GrantedAuthority> authorities = sysUserDTO.getAuthorities();
        model.addAttribute("sysUserDTO",sysUserDTO);
        model.addAttribute("authorities",authorities);
        return "console/console";
    }

    @RequestMapping("user")
    public String userIndex() {
        return "user/user_index";
    }

    /***********/
    /** 页面结束 **/
    /***********/
}
