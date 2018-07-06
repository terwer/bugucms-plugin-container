package com.terwergreen.bugucms.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.terwergreen.bugucms.util.Constants.SERVLET_BASE_PATH;

/**
 * @Author Terwer
 * @Date 2018/7/6 14:44
 * @Version 1.0
 * @Description TODO
 **/
@WebServlet(name = "testServlet", urlPatterns = SERVLET_BASE_PATH + "/testServlet")
public class TestServlet extends HttpServlet {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应的类型格式为网页格式
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        logger.info("Test servlet is running...");
        out.print("Hello World!");
    }
}
