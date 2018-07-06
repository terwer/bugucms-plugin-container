package com.terwergreen.bugucms.handler;

import com.terwergreen.bugucms.base.service.BusinessServiceException;
import com.terwergreen.bugucms.core.service.CommonService;
import com.terwergreen.bugucms.dto.RestResponseDTO;
import com.terwergreen.bugucms.dto.SiteConfigDTO;
import com.terwergreen.bugucms.util.RestResponseStates;
import com.terwergreen.bugucms.exception.AppErrorController;
import com.terwergreen.bugucms.exception.RestException;
import com.terwergreen.bugucms.exception.WebException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class BugucmsExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AppErrorController.class);

    //error有bug，不显示那个view
//    public static final String DEFAULT_ERROR_VIEW = "error";
    public static final String DEFAULT_ERROR_VIEW = "errorPage";

    @Autowired
    private CommonService commonService;

    @ExceptionHandler(value = RestException.class)
    @ResponseBody
    public RestResponseDTO restErrorHandler(HttpServletRequest request, Exception e) {
        RestResponseDTO restResponseDTO = new RestResponseDTO();
        restResponseDTO.setData(e.getLocalizedMessage() + ",request url is:" + request.getRequestURL());
        restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
        restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
        return restResponseDTO;
    }

    @ExceptionHandler(value = WebException.class)
    public ModelAndView webErrorHandler(HttpServletRequest request, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", e.getLocalizedMessage());
        mav.addObject("exception", getErrorInfoFromException(e));
        mav.addObject("url", request.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);

        SiteConfigDTO siteConfigDTO = new SiteConfigDTO();
        try {
            siteConfigDTO = commonService.getSiteConfig();
        } catch (BusinessServiceException ex) {
            logger.error("获取站点信息失败", ex);
        }

        mav.addObject("siteConfigDTO", siteConfigDTO);

        return mav;
    }

    /**
     * 将Exception.printStackTrace()转换为String
     *
     * @param e
     * @return
     */
    public static String getErrorInfoFromException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return "\r\n" + sw.toString() + "\r\n";
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        }
    }

}
