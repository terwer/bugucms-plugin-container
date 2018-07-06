package com.terwergreen.bugucms.servlet;

import com.terwergreen.bugucms.handler.impl.metaWeblogImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.XmlRpcServletServer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.terwergreen.bugucms.util.Constants.XMLRPC_URL;

/**
 * @Author Terwer
 * @Date 2018/7/6 14:44
 * @Version 1.0
 * @Description Xmlrpc支持
 **/
@WebServlet(name = "xmlrpc", urlPatterns = XMLRPC_URL)
public class XmlrpcServlet extends HttpServlet {
    private Logger logger = LogManager.getLogger(this.getClass());

    private XmlRpcServletServer server;

    public void init(ServletConfig pConfig) throws ServletException {
        super.init(pConfig);
        try {
            // create a new XmlRpcServletServer object
            server = new XmlRpcServletServer();

            // set up handler mapping of XmlRpcServletServer object
            PropertyHandlerMapping phm = new PropertyHandlerMapping();
            phm.addHandler("metaWeblog", metaWeblogImpl.class);
            server.setHandlerMapping(phm);

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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        logger.info("XML-RPC Server is running...");
        out.print("XML-RPC Server accepts POST requests only.");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        server.execute(request, response);
    }
}
