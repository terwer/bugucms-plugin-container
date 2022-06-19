package com.terwergreen.bugucms.coresevice.xmlrpc;

import org.apache.xmlrpc.XmlRpcException;

import java.util.List;
import java.util.Map;

/**
 * metaWeblogApi接口定义
 *
 * @name: IMetaWeblog
 * @author: terwer
 * @date: 2022-03-07 13:30
 **/
public interface IMetaWeblog {
    /**
     * 获取博客信息：blogger.getUsersBlogs
     * @param appKey
     * @param username
     * @param password
     * @return
     * @throws XmlRpcException
     */
    List<Map<String, Object>> getUsersBlogs(String appKey, String username, String password) throws XmlRpcException;

    /**
     * 发布博客文章：metaWeblog.newPost
     * @param blogid
     * @param username
     * @param password
     * @param post
     * @param publish
     * @return
     * @throws XmlRpcException
     */
    String newPost(String blogid, String username, String password, Map<String, Object> post, boolean publish) throws XmlRpcException;

    /**
     * 编辑博客文章：metaWeblog.editPost
     * @param postid
     * @param username
     * @param password
     * @param post
     * @param publish
     * @return
     * @throws XmlRpcException
     */
    boolean editPost(String postid, String username, String password, Map<String, Object> post, boolean publish) throws XmlRpcException;

    /**
     * 获取博客文章：metaWeblog.getPost
     * @param postid
     * @param username
     * @param password
     * @return
     * @throws XmlRpcException
     */
    Map<String, Object> getPost(String postid, String username, String password) throws XmlRpcException;

    /**
     * 获取博客分类：metaWeblog.getCategories
     * @param blogid
     * @param username
     * @param password
     * @return
     * @throws XmlRpcException
     */
    List<Map<String, String>> getCategories(String blogid, String username, String password) throws XmlRpcException;

    /**
     * 获取最近的文章列表：metaWeblog.getRecentPosts
     * @param blogid
     * @param username
     * @param password
     * @param numberOfPosts
     * @return
     * @throws XmlRpcException
     */
    List<Map<String, Object>> getRecentPosts(String blogid, String username, String password, int numberOfPosts) throws XmlRpcException;

    /**
     * 上传媒体对象：metaWeblog.newMediaObject
     * @param blogid
     * @param username
     * @param password
     * @param post
     * @return
     * @throws XmlRpcException
     */
    Map<String, String> newMediaObject(String blogid, String username, String password, Map<String, Object> post) throws XmlRpcException;
}
