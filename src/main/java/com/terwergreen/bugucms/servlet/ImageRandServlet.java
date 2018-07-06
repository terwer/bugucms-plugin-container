package com.terwergreen.bugucms.servlet;

import com.terwergreen.bugucms.util.security.ValidateCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.terwergreen.bugucms.util.Constants.SESSION_VERIFY_CODE_KEY;

/**
 * @Author Terwer
 * @Date 2018/7/6 14:44
 * @Version 1.0
 * @Description 图片验证码支持
 **/
@WebServlet(name = "imageRandServlet", urlPatterns = "/servlet/imageRandServlet")
public class ImageRandServlet extends HttpServlet {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession();

        int width = 120;
        int height = 40;
        int codeCount = 5;
        int lineCount = 30;

        if (request.getParameter("width") != null && request.getParameter("width") != "") {
            width = Integer.parseInt(request.getParameter("width").trim());
        }
        if (request.getParameter("height") != null && request.getParameter("height") != "") {
            height = Integer.parseInt(request.getParameter("height").trim());
        }
        if (request.getParameter("codeCount") != null && request.getParameter("codeCount") != "") {
            codeCount = Integer.parseInt(request.getParameter("codeCount").trim());
        }
        if (request.getParameter("lineCount") != null && request.getParameter("lineCount") != "") {
            lineCount = Integer.parseInt(request.getParameter("lineCount").trim());
        }

        logger.debug("生成图片验证码的宽/高/字符数/干扰线数：width=" + width + ";height=" + height + ";codeCount=" + codeCount + ";lineCount=" + lineCount);
        ValidateCode vCode = new ValidateCode(width, height, codeCount, lineCount);
        session.setAttribute(SESSION_VERIFY_CODE_KEY, vCode.getCode());

        vCode.write(response.getOutputStream());
    }
}
