package com.terwergreen.bugucms.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Author Terwer
 * @Date 2018/7/25 14:44
 * @Version 1.0
 * @Description 自定义DispatcherServlet
 **/

/**
 * Spring Boot想要服务来自应用程序root /下的所有内容。如果你想将自己的servlet映射到该目录下也是可以的，但当然你可能失去一些Spring Boot MVC特性。为了添加你自己的servlet，并将它映射到root资源，你只需声明一个Servlet类型的@Bean，并给它特定的bean名称dispatcherServlet（如果只想关闭但不替换它，你可以使用该名称创建不同类型的bean）。
 */
public class BugucmsDispatcherServlet extends DispatcherServlet {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        boolean isGet = HttpMethod.GET.toString().equals(method);
        if (isGet) {
            logger.debug("Bugucms BugucmsDispatcherServlet.service,process get request:" + request.getRequestURL().toString() + ",queryString:" + request.getQueryString());
            if (null != request.getQueryString() && request.getQueryString().contains("json")) {
                String[] json = request.getParameterMap().getOrDefault("json", null);
                if (json.length > 0 && "1".equals(json[0])) {
                    logger.info("Bugucms json api is open...");
                    handleJSONApi(request, response);
                    logger.info("Bugucms json api end.");
                    return;
                }
            }
        }
        super.service(request, response);
    }

    /**
     * 处理JSON APi
     *
     * @param request
     * @param response
     */
    private void handleJSONApi(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> parameterMap = request.getParameterMap();

        //API的逻辑处理
        //

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String jsonStr = "{\n" +
                "  \"status\": \"ok\",\n" +
                "  \"json_api_version\": \"1.0\",\n" +
                "  \"controllers\": [\n" +
                "    \"core\"\n" +
                "  ]\n" +
                "}";
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.doDispatch(request, response);
    }
}
