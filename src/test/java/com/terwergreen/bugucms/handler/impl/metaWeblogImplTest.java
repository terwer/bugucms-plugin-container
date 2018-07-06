package com.terwergreen.bugucms.handler.impl;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import static org.junit.Assert.*;

public class metaWeblogImplTest {

    @Test
    public void getPost() {
        try {
            // config client
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://localhost:8081/bg/xmlrpc"));      // should be modified according to your configuration of jsp container

            // create a new XmlRpcClient object and bind above config object with it
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            // create parameter list
            Vector params = new Vector<String>();
            params.addElement(1);
            params.addElement("admin");
            params.addElement("123456");

            // execute XML-RPC call
            String result = (String) client.execute("metaWeblog.getPost", params);
            System.out.println(result);

        } catch (MalformedURLException e) {
            System.out.println(e.toString());
        } catch (XmlRpcException e) {
            System.out.println(e.toString());
        }
    }
}