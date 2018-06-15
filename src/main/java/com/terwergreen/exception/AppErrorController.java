package com.terwergreen.exception;

import com.terwergreen.core.controller.BGBaseController;
import com.terwergreen.bugucms.front.common.util.Constants;
import com.terwergreen.bugucms.front.common.util.RestResponseStates;
import net.minidev.json.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@Controller
public class AppErrorController extends BGBaseController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(AppErrorController.class);

    private final static String ERROR_PATH = "/error";

    /**
     * Supports the HTML Error View
     *
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));
        MultiValueMap<String, String> headers = getHeaders(request);
        HttpStatus status = getStatus(request);
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(body, headers, status);

        Map resultMap = new HashMap();
        resultMap.put("data", responseEntity);
        resultMap.put("flag", RestResponseStates.SERVER_ERROR.getValue());
        resultMap.put("msg", RestResponseStates.SERVER_ERROR.getMsg());

        String isAPI = request.getParameter("isAPI");
        //API访问输出JSON，否则输出HTML页面
        if (Constants.YES_FLAG.equals(isAPI)) {
            //输出JSON
            return super.processResultAll(resultMap);
        } else {
            //输出XML
            return new ModelAndView("errorInfo", "errorMap", resultMap);
        }
    }

//    /**
//     * Supports other formats like JSON, XML
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = ERROR_PATH)
//    @ResponseBody
//    public RestResponseDTO error(HttpServletRequest request) {
//        RestResponseDTO restResponseDTO = new RestResponseDTO();
//
//        Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));
//        MultiValueMap<String, String> headers = getHeaders(request);
//        HttpStatus status = getStatus(request);
//        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(body, headers, status);
//
//        restResponseDTO.setData(responseEntity);
//        restResponseDTO.setFlag(RestResponseStates.SERVER_ERROR.getValue());
//        restResponseDTO.setMsg(RestResponseStates.SERVER_ERROR.getMsg());
//        return restResponseDTO;
//    }

    /**
     * Returns the path of the error page.
     *
     * @return the error path
     */
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    /**
     * get request headers
     *
     * @return
     */
    private MultiValueMap<String, String> getHeaders(HttpServletRequest request) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.set(key, value);
        }
        return map;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request,
                                                   boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        logger.debug("Request Attributes:" + JSONValue.toJSONString(requestAttributes.getAttributeNames(SCOPE_REQUEST)));
        Map<String, Object> map = new HashMap<String, Object>();
        String URL = request.getRequestURL().toString();
        map.put("URL", URL);
        map.put("error", getErrorMessage(request));
        map.put("status", getStatus(request));
        map.put("timestamp", getTimestamp());
        logger.debug("AppErrorController.method [error info]: status-" + map.get("status") + ", request url-" + URL);
        return map;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception ignored) {
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private String getTimestamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    private String getErrorMessage(HttpServletRequest request) {
        String errorServletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        String errorMessage = (String) request.getAttribute("javax.servlet.error.message");
        if (!StringUtils.isEmpty(errorMessage)) {
            return "Servlet " + errorServletName + " Occured An Rrror:" + errorMessage;
        }
        return "No Message";
    }

}