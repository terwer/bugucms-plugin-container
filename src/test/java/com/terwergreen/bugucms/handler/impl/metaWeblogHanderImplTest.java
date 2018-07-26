package com.terwergreen.bugucms.handler.impl;

import com.alibaba.fastjson.JSON;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public class metaWeblogHanderImplTest {

    @Test
    public void getPost() {
        try {
            // config client
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://localhost:8081/bg/xmlrpc"));      // should be modified according to your configuration of jsp container
            // config.setServerURL(new URL("http://localhost/wordpress/xmlrpc.php"));

            // create a new XmlRpcClient object and bind above config object with it
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            // create parameter list
            Vector params = new Vector<String>();
            params.addElement(1);
            params.addElement("admin");
            params.addElement("123456");

            // execute XML-RPC call
            Object result = client.execute("metaWeblog.getPost", params);
            System.out.println(JSON.toJSONString(result));

        } catch (MalformedURLException e) {
            System.out.println(e.toString());
        } catch (XmlRpcException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void getUsersBlogs() {
        try {
            // config client
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://localhost:8081/bg/xmlrpc"));      // should be modified according to your configuration of jsp container
            // config.setServerURL(new URL("http://localhost/wordpress/xmlrpc.php"));

            // create a new XmlRpcClient object and bind above config object with it
            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            // create parameter list
            Vector params = new Vector<String>();
            params.addElement("appkey");
            params.addElement("admin");
            params.addElement("123456");

            // execute XML-RPC call
            Object result = client.execute("metaWeblog.getUsersBlogs", params);
            System.out.println(JSON.toJSONString(result));

        } catch (MalformedURLException e) {
            System.out.println(e.toString());
        } catch (XmlRpcException e) {
            System.out.println(e.toString());
        }
    }
}