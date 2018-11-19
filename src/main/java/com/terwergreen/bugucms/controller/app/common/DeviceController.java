package com.terwergreen.bugucms.controller.app.common;

import com.terwergreen.bugucms.base.controller.BGBaseController;
import com.terwergreen.bugucms.dto.RestResponseDTO;
import com.terwergreen.bugucms.utils.RestResponseStates;
import com.terwergreen.bugucms.base.exception.RestException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/common/device")
public class DeviceController extends BGBaseController {
    /**
     * 发送短信校验码接口
     *
     * @return
     * @author terwergreen
     * @date 2018-04-03 13:36:00
     */
    @RequestMapping(value = "/sendSMS")
    @ResponseBody
    public RestResponseDTO sendSMS(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        try {
            response.setContentType("application/json;charset=utf-8");
            logger.debug("发送开始。");

            Map<String, String> paramMap = new HashMap<String, String>();
//			if (true) {
//				paramMap.put("info", "错误的验证码");
//				paramMap.put("status", "0");
//			} else {
            paramMap.put("info", "发送成功");
            paramMap.put("status", "1");
//			}
            restResponseDTO.setData(paramMap);
            restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
            restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
            logger.debug("发送完成。");
        } catch (Exception e) {
            super.logger.error("接口异常:error=", e);
            restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
            restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
            return restResponseDTO;
        }
        return restResponseDTO;
    }

    /**
     * 短信校验码结果接口
     *
     * @return
     * @author terwergreen
     * @date 2018-04-03 13:36:00
     */
    @RequestMapping("/verifySMS")
    @ResponseBody
    public RestResponseDTO verifySMS(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value = "smsCode", required = true) String smsCode) throws Exception {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        try {
            response.setContentType("application/json;charset=utf-8");
            logger.debug("验证开始。");
            logger.debug("验证手机验证码，入参：smsCode=" + smsCode);
            Map<String, String> paramMap = new HashMap<String, String>();
//			if (true) {
//				paramMap.put("info", "错误的验证码");
//				paramMap.put("status", "0");
//			} else {
//				paramMap.put("info", "验证成功");
//				paramMap.put("status", "1");
//			}
            restResponseDTO.setData(paramMap);
            restResponseDTO.setFlag(RestResponseStates.SUCCESS.getValue());
            restResponseDTO.setMsg(RestResponseStates.SUCCESS.getMsg());
            logger.debug("验证完成。");
        } catch (Exception e) {
            logger.error("接口异常:error=", e);
            throw new RestException(e);
        }
        return restResponseDTO;
    }
}
