package com.terwergreen.front.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.terwergreen.front.common.dto.RestResponseDTO;
import com.terwergreen.front.common.util.RestResponseStates;
import com.terwergreen.front.common.web.BGBaseController;

@Controller
public class LoginController extends BGBaseController {

	/***********/
	/**页面开始**/
	/***********/
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		return new ModelAndView();
	}

	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public ModelAndView reg(HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("weburl", "http://localhost:8080/bugucms");
		paramMap.put("webname", "特维博客");
		paramMap.put("webtheme", "专注于服务端技术分享");
		paramMap.put("keywords", "软件架构、服务端开发、Java、Spring、Dubbo、Zookeeper、微服务");
		paramMap.put("description", "特维博客致力于Java后端开发及服务端技术、软件架构、微服务技术分享。");
		paramMap.put("beianinfo", "粤ICP备18023717号-1");
		return new ModelAndView("reg", paramMap);
	}

	@RequestMapping(value = "/findpwd", method = RequestMethod.GET)
	public ModelAndView findpwd(HttpServletRequest request) {
		return new ModelAndView();
	}
	
	/***********/
	/**页面结束**/
	/***********/
	
	/**
	 * 注册接口
	 * 
	 * @author terwergreen
	 * @date 2018-03-07 13:36:00
	 * @return
	 */
	@RequestMapping(value = "/user/login")
	@ResponseBody
	public RestResponseDTO login(HttpServletRequest request,
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

	/**
	 * 注册接口
	 * 
	 * @author terwergreen
	 * @date 2018-03-07 13:36:00
	 * @return
	 */
	@RequestMapping(value = "/user/register")
	@ResponseBody
	public RestResponseDTO register(HttpServletRequest request,
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
