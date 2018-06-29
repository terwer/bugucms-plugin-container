package com.terwergreen.handler;

import com.terwergreen.exception.RestException;
import com.terwergreen.exception.WebException;
import com.terwergreen.bugucms.common.dto.RestResponseDTO;
import com.terwergreen.bugucms.common.util.RestResponseStates;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalExceptionHandler {

    //error有bug，不显示那个view
//    public static final String DEFAULT_ERROR_VIEW = "error";
    public static final String DEFAULT_ERROR_VIEW = "errorPage";

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
