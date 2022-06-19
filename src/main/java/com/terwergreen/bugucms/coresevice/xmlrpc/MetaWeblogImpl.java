package com.terwergreen.bugucms.coresevice.xmlrpc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.terwergreen.bugucms.container.BugucmsPluginManager;
import com.terwergreen.bugucms.coresevice.aliyunoss.OssManager;
import com.terwergreen.bugucms.utils.BuguCMSConstants;
import com.terwergreen.bugucms.utils.SpringBeanUtils;
import com.terwergreen.core.CommonService;
import com.terwergreen.pojo.SiteConfig;
import com.terwergreen.util.PropertyUtil;
import com.terwergreen.util.ReflectUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.common.XmlRpcNotAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * metaWeblogApi的具体实现
 *
 * @name: MetaWeblogImpl
 * @author: terwer
 * @date: 2022-03-07 14:09
 **/
public class MetaWeblogImpl implements IMetaWeblog {
    private static final Logger logger = LoggerFactory.getLogger(MetaWeblogImpl.class);

    private CommonService commonService;
    private Object userService;

    public CommonService getCommonService() {
        if (commonService == null) {
            commonService = SpringBeanUtils.getBean(CommonService.class);
        }
        return commonService;
    }

    public Object getUserService() {
        if (userService == null) {
            userService = (UserDetailsService) SpringBeanUtils.getBean("userService");
            // userService = SpringBeanUtils.getBean("com.terwergreen.plugins.auth.service.impl.UserService");
        }
        return userService;
    }

    public MetaWeblogImpl() {
        logger.info("容器中注册MetaWeblogImpl");
    }

    private Map<String, Object> isValid(String username, String password) throws XmlRpcNotAuthorizedException {
        logger.info("username: {}, password: {}", username, password);
        Object userService = getUserService();
        Map<String, Object> rtnResult = (Map<String, Object>) ReflectUtil.invoke(userService, "isValid", new Class[]{String.class, String.class},
                new Object[]{username, password});
        boolean isValid = (boolean) rtnResult.get("matches");
        logger.info("isValid = {}", isValid);
        if (!isValid) {
            throw new XmlRpcNotAuthorizedException("账号或密码有误");
        }
        return rtnResult;
    }

    public List<Map<String, Object>> getUsersBlogs(String appKey, String username, String password) throws XmlRpcException {
        logger.info("[blogger.getUsersBlogs] -> appKey: {}, username: {}, password: {}", appKey, username, password);
        isValid(username, password);

        SiteConfig siteConfig = getCommonService().getSiteConfig();

        List<Map<String, Object>> usersBlogs = new ArrayList<>();
        Map<String, Object> blogInfo = new HashMap<>();
        blogInfo.put("blogid", "BuguCMS");
        blogInfo.put("url", siteConfig.getWeburl());
        blogInfo.put("blogName", siteConfig.getWebname());
        usersBlogs.add(blogInfo);

        return usersBlogs;
    }

    @Override
    public String newPost(String blogid, String username, String password, Map<String, Object> post, boolean publish) throws XmlRpcException {
        // logger.info("metaWeblog.newPost -> blogid: {}, post: {}, publish: {}", blogid, JSON.toJSONString(post), publish);
        logger.info("metaWeblog.newPost -> blogid: {}, publish: {}", blogid, publish);
        Map<String, Object> rtnResult = isValid(username, password);

        JSONObject postJson = JSONObject.parseObject(JSON.toJSONString(post));
        // logger.debug("postJson = {}", postJson);

        Integer postId = 0;
        Object bean = SpringBeanUtils.getBean("com.terwergreen.plugins.blog.service.impl.PostServiceImpl");
        Map pramMap = new HashMap();
        try {
            BugucmsPluginManager bugucmsPluginManager = SpringBeanUtils.getBean(BugucmsPluginManager.class);
            ClassLoader pluginClassLoader = bugucmsPluginManager.getPlugin("blog-plugin").getPluginClassLoader();
            Class PostClazz = Class.forName("com.terwergreen.plugins.blog.pojo.Post", true, pluginClassLoader);

            Object postObj = PostClazz.newInstance();
            // ==========
            // 数据转换开始
            // ArticleCategory ac = getCategory(postJson.getJSONArray("categories"));
            // article.setCategory(ac);
            PropertyUtils.setProperty(postObj, "postTitle", postJson.getString("title"));
            PropertyUtils.setProperty(postObj, "postRawContent", postJson.getString("description"));
            // article.setKeywords(postJson.getString("mt_keywords"));
            PropertyUtils.setProperty(postObj, "postSlug", postJson.getString("wp_slug"));
            PropertyUtils.setProperty(postObj, "postStatus", postJson.getString("post_status"));
            PropertyUtils.setProperty(postObj, "postDate", postJson.getDate("dateCreated"));
            // post page essay note
            PropertyUtils.setProperty(postObj, "postType", "post");
            PropertyUtils.setProperty(postObj, "postAuthor", rtnResult.get("userId"));
            // 数据转换结束
            // ==========

            postId = (Integer) ReflectUtil.invoke(bean, "newPost", new Class[]{PostClazz}, new Object[]{postObj});
            logger.info("postId = {}", postId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmlRpcException(500, e.getMessage());
        }

        return postId + "";
    }

    @Override
    public boolean editPost(String postid, String username, String password, Map<String, Object> post, boolean publish) throws XmlRpcException {
        // logger.info("metaWeblog.editPost -> postid: {}, post: {}", postid, JSON.toJSONString(post));
        logger.info("metaWeblog.editPost -> postid: {}", postid);

        Map<String, Object> rtnResult = isValid(username, password);

        JSONObject postJson = JSONObject.parseObject(JSON.toJSONString(post));
        // logger.debug("postJson = {}", postJson);

        boolean flag = false;
        Object bean = SpringBeanUtils.getBean("com.terwergreen.plugins.blog.service.impl.PostServiceImpl");
        Map pramMap = new HashMap();
        try {
            BugucmsPluginManager bugucmsPluginManager = SpringBeanUtils.getBean(BugucmsPluginManager.class);
            ClassLoader pluginClassLoader = bugucmsPluginManager.getPlugin("blog-plugin").getPluginClassLoader();
            Class PostClazz = Class.forName("com.terwergreen.plugins.blog.pojo.Post", true, pluginClassLoader);

            Object postObj = PostClazz.newInstance();
            // ==========
            // 数据转换开始
            // ArticleCategory ac = getCategory(postJson.getJSONArray("categories"));
            // article.setCategory(ac);
            PropertyUtils.setProperty(postObj, "postId", Integer.valueOf(postid));
            PropertyUtils.setProperty(postObj, "postTitle", postJson.getString("title"));
            PropertyUtils.setProperty(postObj, "postRawContent", postJson.getString("description"));
            // article.setKeywords(postJson.getString("mt_keywords"));
            PropertyUtils.setProperty(postObj, "postSlug", postJson.getString("wp_slug"));
            PropertyUtils.setProperty(postObj, "postStatus", postJson.getString("post_status"));
            PropertyUtils.setProperty(postObj, "postDate", postJson.getDate("dateCreated"));
            // post page essay note
            PropertyUtils.setProperty(postObj, "postType", "post");
            PropertyUtils.setProperty(postObj, "postAuthor", rtnResult.get("userId"));
            // 数据转换结束
            // ==========

            flag = (boolean) ReflectUtil.invoke(bean, "editPostById", new Class[]{PostClazz}, new Object[]{postObj});
            logger.info("flag = {}", flag);
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmlRpcException(500, e.getMessage());
        }

        return flag;
    }

    @Override
    public Map<String, Object> getPost(String postid, String username, String password) throws XmlRpcException {
        logger.info("metaWeblog.getPost -> postid: {}", postid);
        isValid(username, password);

        Map<String, Object> rtnResult = isValid(username, password);

        Map<String, Object> post = new HashMap<>();
        Object bean = SpringBeanUtils.getBean("com.terwergreen.plugins.blog.service.impl.PostServiceImpl");
        Map pramMap = new HashMap();
        try {
            BugucmsPluginManager bugucmsPluginManager = SpringBeanUtils.getBean(BugucmsPluginManager.class);
            ClassLoader pluginClassLoader = bugucmsPluginManager.getPlugin("blog-plugin").getPluginClassLoader();
            Class PostClazz = Class.forName("com.terwergreen.plugins.blog.pojo.Post", true, pluginClassLoader);

            Object postObj = ReflectUtil.invoke(bean, "getPostById", new Class[]{Integer.class}, new Object[]{Integer.valueOf(postid)});

            // ==========
            // 数据转换开始
            // ArticleCategory ac = getCategory(postJson.getJSONArray("categories"));
            // article.setCategory(ac);
            post.put("title", PropertyUtils.getProperty(postObj, "postTitle"));
            post.put("description", PropertyUtils.getProperty(postObj, "postRawContent"));
            // article.setKeywords(postJson.getString("mt_keywords"));
            post.put("wp_slug", PropertyUtils.getProperty(postObj, "postSlug"));
            post.put("post_status", PropertyUtils.getProperty(postObj, "postStatus"));
            post.put("dateCreated", PropertyUtils.getProperty(postObj, "postDate"));
            // post page essay note
            post.put("post", PropertyUtils.getProperty(postObj, "postType"));
            // 数据转换结束
            // ==========
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmlRpcException(500, e.getMessage());
        }

        return post;
    }

    @Override
    public List<Map<String, String>> getCategories(String blogid, String username, String password) throws XmlRpcException {
        logger.info("metaWeblog.getCategories -> blogid: {}", blogid);

        return new ArrayList<>();
    }

    @Override
    public List<Map<String, Object>> getRecentPosts(String blogid, String username, String password, int numberOfPosts) throws XmlRpcException {
        logger.info("metaWeblog.getRecentPosts -> blogid: {}, numberOfPosts: {}", blogid, numberOfPosts);

//        Object bean = SpringBeanUtils.getBean("com.terwergreen.plugins.blog.service.impl.PostServiceImpl");
//        Map pramMap = new HashMap();
//        Object posts = ReflectUtil.invoke(bean, "getRecentPosts", new Class[]{Map.class}, new Object[]{pramMap});
//        System.out.println("posts = " + posts);

        return null;
    }

    @Override
    public Map<String, String> newMediaObject(String blogid, String username, String password, Map<String, Object> post) throws XmlRpcException {
        logger.info("metaWeblog.newMediaObject -> blogid: {}", blogid);

        isValid(username, password);

        Map<String, String> urlData = new HashMap<>();

        try {
            String retUrl = "http://oss.terwergreen.com/%s";
            String name = post.get("name").toString();
            //  {year}/{mon}/{day}/{filename}{.suffix}
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String forder = sdf.format(now);
            System.out.println("forder = " + forder);
            String fileName = "bugucms/" + forder + "/" + name;
            String url = String.format(retUrl, fileName);

            byte[] bits = (byte[]) post.get("bits");
            logger.info("准备上传图片，url = " + url);
            // 开始上传图片
            OssManager manager = OssManager.getInstance();
            manager.upload(fileName, bits);

            // 水印
            // String watermark = String.format("?x-oss-process=%s", "image/auto-orient,1/quality,q_90/format,jpg/watermark,image_YnVndWNtcy9sb2dvLWRhcmsucG5nP3gtb3NzLXByb2Nlc3M9aW1hZ2UvcmVzaXplLFBfNjI,g_se,x_10,y_10");
            // String markedUrl = url + watermark;

            urlData.put("url", url);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("图片上传错误", e);
        }

        logger.info("urlData = {}", urlData);
        return urlData;
    }
}
