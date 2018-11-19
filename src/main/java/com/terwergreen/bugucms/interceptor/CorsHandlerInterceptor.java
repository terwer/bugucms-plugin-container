package com.terwergreen.bugucms.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Terwer
 * @Date 2018/11/6 14:53
 * @Version 1.0
 * @Description 接口跨域
 **/
public class CorsHandlerInterceptor implements HandlerInterceptor {
    private static final Logger logger = LogManager.getLogger(CorsHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) {
        String allowOrigin=".terwergreen.com";
        String myOrigin = request.getHeader("origin");
        if( myOrigin != null && myOrigin.endsWith(allowOrigin)){
            response.setHeader("Access-Control-Allow-Origin", myOrigin);
            response.setHeader("Access-Control-Request-Method", HttpMethod.POST.toString()+","+HttpMethod.GET.toString());
            response.setHeader("Access-Control-Request-Headers", "*");
        }

        //告诉浏览器编码方式
        response.setHeader("Content-Type", "text/html;charset=UTF-8");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
