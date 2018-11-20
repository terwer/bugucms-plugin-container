package com.terwergreen.bugucms.controller.api.admin;

import com.terwergreen.bugucms.base.controller.BGBaseController;
import com.terwergreen.bugucms.base.exception.RestException;
import com.terwergreen.bugucms.dto.MenuDTO;
import com.terwergreen.bugucms.dto.RestResponseDTO;
import com.terwergreen.bugucms.service.MenuService;
import com.terwergreen.bugucms.utils.RestResponseStates;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Terwer
 * @Date 2018/11/20 14:14
 * @Version 1.0
 * @Description 菜单
 **/
@RestController
@RequestMapping("/")
public class MenuApiController extends BGBaseController {

    @Resource
    private MenuService menuService;

    @RequestMapping(value = "/api/menu/list/{parentId}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public RestResponseDTO getMenus(Model model, HttpServletRequest request, HttpServletResponse response, @PathVariable Integer parentId) throws Exception {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        try {
            super.preCheck(model, request, response);
            Map resultMap = new HashMap();
            List<MenuDTO> menus = menuService.getMenuList(parentId);
            resultMap.put("menus",menus);
            restResponseDTO.setData(resultMap);
            restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
            restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
        } catch (Exception e) {
            logger.error("接口异常:error=", e);
            restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
            restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            throw new RestException(e);
        }
        return restResponseDTO;
    }
}
