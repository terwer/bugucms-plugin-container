package com.terwergreen.bugucms.front.admin;

import com.terwergreen.bugucms.middle.admin.dto.MenuInfoDTO;
import com.terwergreen.bugucms.middle.admin.service.MenuInfoService;
import net.minidev.json.JSONValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Terwer
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController {

    @Resource
    private MenuInfoService menuInfoService;


    /**
     * 返回主菜单和框架
     * @param model 返回页面的数据
     * @param session HttpSession
     * @return 页面
     */
    @RequestMapping(value = "/main/")
    public String firstMenu(Model model, HttpSession session){
        List<MenuInfoDTO> list=menuInfoService.searchMenuFirstByUserId(session);

        model.addAttribute("mainMenu",list);
        return "main";
    }

    /**
     * 返回主菜单和框架
     * @param jurisdictionId 权限id
     * @param menuId 菜单id
     * @return 页面
     */
    @ResponseBody
    @RequestMapping(value = "/child/{jurisdictionId}/{menuId}",produces ="text/json;charset=UTF-8")
    public String childMenu(@PathVariable("jurisdictionId")Integer jurisdictionId, @PathVariable("menuId")Integer menuId, HttpSession session){
        Map<String,Object> map=new HashMap<>();
        map.put("data",menuInfoService.searchMenuNextByUserId(jurisdictionId,menuId,session));
        map.put("status",1);
        return JSONValue.toJSONString(map);
    }
}
