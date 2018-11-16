package com.terwergreen.bugucms.servlet;

import com.terwergreen.bugucms.servlet.xmlrpc.CustomRequestProcessorFactoryFactory;
import com.terwergreen.bugucms.handler.MetaWeblogHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.terwergreen.bugucms.utils.Constants.XMLRPC_URL;

/**
 * @Author Terwer
 * @Date 2018/7/6 14:44
 * @Version 1.0
 * @Description Xmlrpc支持
 **/
@WebServlet(name = "xmlrpc", urlPatterns = XMLRPC_URL)
public class XmlrpcServlet extends HttpServlet {
    private Logger logger = LogManager.getLogger(this.getClass());
    protected static ApplicationContext ctx = null;
    private XmlRpcServletServer server;

    public void init(ServletConfig pConfig) throws ServletException {
        super.init(pConfig);
        try {
            // create a new XmlRpcServletServer object
            server = new XmlRpcServletServer();
            if (ctx == null) {
                ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
            }

            // set up handler mapping of XmlRpcServletServer object
            PropertyHandlerMapping phm = new PropertyHandlerMapping();
            MetaWeblogHandler service = (MetaWeblogHandler) ctx.getBean("metaWeblog");
            phm.setRequestProcessorFactoryFactory(new CustomRequestProcessorFactoryFactory(service));
            // 类似于Wordpress的API
            phm.addHandler("metaWeblog", MetaWeblogHandler.class);
            // 支持Windows Live Writer格式的metaWeblog API
            phm.addHandler("blogger", MetaWeblogHandler.class);
            server.setHandlerMapping(phm);
            // PropertyHandlerMapping phm = new PropertyHandlerMapping();
            // 类似于Wordpress的API
            // phm.addHandler("metaWeblog", MetaWeblogHandelerImpl.class);
            // 支持Windows Live Writer格式的metaWeblog API
            // phm.addHandler("blogger", MetaWeblogHandelerImpl.class);
            // server.setHandlerMapping(phm);

            // more config of XmlRpcServletServer object
            XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) server.getConfig();
            serverConfig.setEnabledForExtensions(true);
            serverConfig.setContentLengthOptional(false);
        } catch (XmlRpcException e) {
            try {
                logger.info("Failed to create XmlRpcServer: " + e.getMessage(), e);
            } catch (Throwable ignore) {
                logger.error(e.getMessage(), e);
            }
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        PrintWriter out = response.getWriter();
        logger.info("XML-RPC server is running...");
        out.print("XML-RPC server accepts POST requests only.");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        server.execute(request, response);
    }
}
