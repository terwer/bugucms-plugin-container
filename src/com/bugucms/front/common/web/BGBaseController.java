package com.bugucms.front.common.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BGBaseController {

	protected Log logger = LogFactory.getLog(getClass());

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}

	/**
	 * 封装放回结果信息 {"flag":"","msg":"","data":""} flag-msg:0-未登录,1-操作成功,2.....
	 */
	public static ModelAndView processResult(Map model, String... flagAndMsg) {
		Map resultMap = new HashMap();
		if (StringUtils.isEmpty((String) model.get("flag"))) {
			// 传参数如processResult(model)，且model不包含flag进入
			resultMap.put("flag", "1");
			resultMap.put("msg", "操作成功");
			resultMap.put("data", model);
		} else if (flagAndMsg.length > 1) {
			// 重新自定义flag和msg。传参数如processResult(model, "1",
			// "abcxyz")，只有同时传了flag和msg才进入，顺序是flag在前！！！
			resultMap.put("flag", flagAndMsg[0]);
			resultMap.put("msg", flagAndMsg[1]);
			resultMap.put("data", model);
		} else {
			// 传参数如processResult(model)，且model包含flag进入
			resultMap.put("flag", model.get("flag"));
			resultMap.put("msg", model.get("msg"));
		}
		MappingJacksonJsonView json = new MappingJacksonJsonView();
		json.setContentType("application/json;charset=utf-8");
		return new ModelAndView(json, resultMap);
	}

	/**
	 * 错误信息里有多个值
	 */
	public ModelAndView processResultMulti(Map model) {
		// LogUtil.debug("响应信息，model:" + model);
		Map resultMap = new HashMap();
		if (StringUtils.isEmpty((String) model.get("flag"))) {
			resultMap.put("flag", "1");
			resultMap.put("msg", "操作成功");
			resultMap.put("data", model);
		} else {
			resultMap.put("flag", model.get("flag"));
			model.remove("flag");
			resultMap.put("msg", model.get("msg"));
			model.remove("msg");
			resultMap.put("data", model);
		}
		MappingJacksonJsonView json = new MappingJacksonJsonView();
		json.setContentType("application/json;charset=utf-8");
		return new ModelAndView(json, resultMap);
	}

	/**
	 * 标准
	 * 
	 * @param model
	 * @return
	 */
	public ModelAndView processResultAll(Map model) {
		model.put("data", model.get("data"));
		MappingJacksonJsonView json = new MappingJacksonJsonView();
		json.setContentType("application/json;charset=utf-8");
		return new ModelAndView(json, model);
	}

}
