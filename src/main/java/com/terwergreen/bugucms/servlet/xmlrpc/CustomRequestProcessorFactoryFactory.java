package com.terwergreen.bugucms.servlet.xmlrpc;

import com.terwergreen.bugucms.handler.MetaWeblogHandler;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory;

/**
 * @Author Terwer
 * @Date 2018/7/9 18:35
 * @Version 1.0
 * @Description TODO
 **/
public class CustomRequestProcessorFactoryFactory implements RequestProcessorFactoryFactory {

    private final RequestProcessorFactory factory = new CustomRequestProcessorFactory();
    private final MetaWeblogHandler service;

    public CustomRequestProcessorFactoryFactory(MetaWeblogHandler service) {
        this.service = service;
    }

    @Override
    public RequestProcessorFactory getRequestProcessorFactory(Class arg0) throws XmlRpcException {
        return factory;
    }

    private class CustomRequestProcessorFactory implements RequestProcessorFactory {
        @Override
        public Object getRequestProcessor(XmlRpcRequest arg0) throws XmlRpcException {
            return service;
        }
    }
}
