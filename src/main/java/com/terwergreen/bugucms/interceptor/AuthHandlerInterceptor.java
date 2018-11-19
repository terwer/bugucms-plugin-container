package com.terwergreen.bugucms.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.terwergreen.bugucms.utils.EncryptAndDecrypt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Terwer
 * @Date 2018/11/6 14:53
 * @Version 1.0
 * @Description 接口token校验
 **/
public class AuthHandlerInterceptor implements HandlerInterceptor {
    private static final Logger logger = LogManager.getLogger(AuthHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) {
        logger.info(request.getRequestURI());
        response.setHeader("Access-Control-Allow-Origin", "*");
        //告诉浏览器编码方式
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        // 说明处在编辑的页面
        Object tokenString = request.getParameter("tokenString");
        //request.getParameter(arg0)
        if (!StringUtils.isEmpty(tokenString) && !tokenString.toString().trim().isEmpty()) {
            logger.info("token:" + EncryptAndDecrypt.decrypt(tokenString.toString()));
            JSONObject jsonObject = JSON.parseObject(EncryptAndDecrypt.decrypt(tokenString.toString()));
            if (!StringUtils.isEmpty(jsonObject)) {
                request.getSession().setAttribute("admin.loginId", jsonObject.getIntValue("loginId"));
                request.getSession().setAttribute("admin.loginName", jsonObject.getString("loginName"));
                request.getSession().setAttribute("admin.role", jsonObject.getString("role"));
                return true;
            }
        }
        //tokenString错误，返回错误信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            Map<String, Object> map = new HashMap<>(3);
            map.put("status", 0);
            Map dataMap = new HashMap();
            dataMap.put("errorCode", 10001);
            map.put("data", dataMap);
            map.put("msg", "tokenString为空或错误");
            out.append(JSON.toJSONString(map));
            logger.info("tokenString错误");
        } catch (IOException e) {
            logger.error(e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
