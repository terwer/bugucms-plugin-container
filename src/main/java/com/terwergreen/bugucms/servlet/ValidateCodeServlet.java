package com.terwergreen.bugucms.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Terwer
 */
@WebServlet(name="imageRandServlet",urlPatterns="/servlet/imageRandServlet")
public class ValidateCodeServlet extends HttpServlet {
	protected static final Logger logger=LogManager.getLogger(ValidateCodeServlet.class);
	private static final long serialVersionUID=8657719316171328200L;
	
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
		
		if(request.getParameter("width")!=null && request.getParameter("width")!=""){
			width=Integer.parseInt(request.getParameter("width").trim());
			
		}
		if(request.getParameter("height")!=null && request.getParameter("height")!=""){
			height=Integer.parseInt(request.getParameter("height").trim());
			
		}
		if(request.getParameter("codeCount")!=null && request.getParameter("codeCount")!=""){
			codeCount=Integer.parseInt(request.getParameter("codeCount").trim());
			
		}
		if(request.getParameter("lineCount")!=null && request.getParameter("lineCount")!=""){
			lineCount=Integer.parseInt(request.getParameter("lineCount").trim());
			
		}
		logger.debug("生成图片验证码的宽/高/字符数/干扰线数：width="+width+";height="+height+";codeCount="+codeCount+";lineCount="+lineCount);
		ValidateCode vCode = new ValidateCode(width, height, codeCount, lineCount);
		session.setAttribute("vcode", vCode.getCode());
		
		vCode.write(response.getOutputStream());
	}
}
