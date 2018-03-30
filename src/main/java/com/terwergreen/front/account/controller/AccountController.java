package com.terwergreen.front.account.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.terwergreen.front.common.dto.RestResponseDTO;
import com.terwergreen.front.common.util.RestResponseStates;
import com.terwergreen.front.common.web.BGBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * Created by terwergreen on 2017-11-30.
 */
@Controller
public class AccountController extends BGBaseController {

    @RequestMapping(value = "/accountInfo", method = RequestMethod.GET)
    public ModelAndView Hello(HttpServletRequest request) {
        // Map<String, String> resultMap = new HashMap<String, String>();
        // resultMap.put("flag", RestResponseStates.SUCCESS.getValue());
        // resultMap.put("msg", RestResponseStates.SUCCESS.getMsg());
        // return super.processResultAll(resultMap);

        return new ModelAndView();
    }

    @RequestMapping(value = "/activate", method = RequestMethod.GET)
    @ResponseBody
    public RestResponseDTO Hello2(HttpServletRequest request) {
        RestResponseDTO restResponseDTO = new RestResponseDTO();

        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("accountId", "F10001");
        resultMap.put("mobile", "18888888888");
        restResponseDTO.setData(resultMap);

        restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
        restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
        return restResponseDTO;
    }
}