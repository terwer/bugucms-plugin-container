package com.terwergreen.bugucms.base.controller;

import com.terwergreen.bugucms.base.exception.WebException;
import com.terwergreen.bugucms.base.service.CommonService;
import com.terwergreen.bugucms.dto.SiteConfigDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class BGBaseController {

	protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private CommonService commonService;

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
		MappingJackson2JsonView json = new MappingJackson2JsonView();
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
		MappingJackson2JsonView json = new MappingJackson2JsonView();
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
		MappingJackson2JsonView json = new MappingJackson2JsonView();
		json.setContentType("application/json;charset=utf-8");
		return new ModelAndView(json, model);
	}

	public void preCheck(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			//获取站点配置
			SiteConfigDTO siteConfigDTO = commonService.getSiteConfig();
			if (null == siteConfigDTO) {
				logger.error("站点配置异常:siteConfigDTO=null");
				throw new WebException("站点配置异常:siteConfigDTO=null");
			}
			model.addAttribute("siteConfigDTO", siteConfigDTO);
		} catch (Exception e) {
			logger.error("系统异常" + e.getLocalizedMessage(), e);
			throw new WebException(e);
		}
	}
}
