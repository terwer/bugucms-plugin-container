package com.bugucms.front.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bugucms.front.common.dto.RestResponseDTO;
import com.bugucms.front.common.util.RestResponseStates;
import com.bugucms.front.common.web.BGBaseController;

@Controller
public class LoginController extends BGBaseController {
	/**
	 * 注册接口
	 * 
	 * @author terwergreen
	 * @date 2018-03-07 13:36:00
	 * @return
	 */
	@RequestMapping(value = "/register")
	@ResponseBody
	public RestResponseDTO accreditCredit(HttpServletRequest request,
			HttpServletResponse response) {
		RestResponseDTO restResponseDTO = new RestResponseDTO();
		response.setContentType("application/json;charset=utf-8");
		try {
			super.logger.info("请求开始");
			restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
			restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
			super.logger.info("请求结束");
		} catch (Exception e) {
			super.logger.error("接口异常:error=", e);
			restResponseDTO = new RestResponseDTO();
			restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
			restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
			return restResponseDTO;
		}
		return restResponseDTO;
	}
}
