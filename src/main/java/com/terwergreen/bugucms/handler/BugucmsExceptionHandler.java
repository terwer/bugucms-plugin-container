package com.terwergreen.bugucms.handler;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Terwer
 * @Date 2018/11/19 14:19
 * @Version 1.0
 * @Description 统一异常处理类
 **/
@Controller
public class BugucmsExceptionHandler implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        //获取statusCode
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 400) {
            return "error/400";
        } else if (statusCode == 401) {
            return "error/401";
        } else if (statusCode == 403) {
            return "error/403";
        } else if (statusCode == 404) {
            return "error/404";
        } else {
            return "error/500";
        }
    }

    @Override
    public String getErrorPath() {
        return "/templates/error";
    }
}
