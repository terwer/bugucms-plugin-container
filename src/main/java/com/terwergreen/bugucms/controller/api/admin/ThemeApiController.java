package com.terwergreen.bugucms.controller.api.admin;

import com.terwergreen.bugucms.base.controller.BGBaseController;
import com.terwergreen.bugucms.dto.RestResponseDTO;
import com.terwergreen.bugucms.dto.ThemeDTO;
import com.terwergreen.bugucms.base.exception.RestException;
import com.terwergreen.bugucms.service.ThemeService;
import com.terwergreen.bugucms.utils.RestResponseStates;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
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
 * @Date 2018/9/7 10:54
 * @Version 1.0
 * @Description 主题相关API接口
 **/
@RestController
@RequestMapping("/")
public class ThemeApiController extends BGBaseController {

    @Resource
    private ThemeService themeService;

    @RequestMapping(value = "/api/theme/changeTheme", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public RestResponseDTO changeTheme(Model model, HttpServletRequest request, HttpServletResponse response, String theme) throws Exception {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        try {
            super.preCheck(model, request, response);
            boolean reslult = themeService.changeTheme(theme);
            if (reslult) {
                restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
                restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
            } else {
                restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
                restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            }
        } catch (Exception e) {
            super.logger.error("接口异常:error=", e);
            restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
            restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            throw new RestException(e);
        }
        return restResponseDTO;
    }

    @RequestMapping(value = "/api/theme/getAvailableThemes", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public RestResponseDTO getAvailableThemes(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        try {
            super.preCheck(model, request, response);
            List<ThemeDTO> themes = themeService.getAvailableThemes(request);
            if (!CollectionUtils.isEmpty(themes)) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("themes", themes);
                restResponseDTO.setData(resultMap);
                restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
                restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
            } else {
                restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
                restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            }
        } catch (Exception e) {
            super.logger.error("接口异常:error=", e);
            restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
            restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            throw new RestException(e);
        }
        return restResponseDTO;
    }
}
