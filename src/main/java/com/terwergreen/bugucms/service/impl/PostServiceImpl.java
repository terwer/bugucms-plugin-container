package com.terwergreen.bugucms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.terwergreen.bugucms.base.exception.BusinessServiceException;
import com.terwergreen.bugucms.base.dao.CommonDAO;
import com.terwergreen.bugucms.base.service.CommonService;
import com.terwergreen.bugucms.dto.PostDTO;
import com.terwergreen.bugucms.dto.PostMetaDTO;
import com.terwergreen.bugucms.dto.SiteConfigDTO;
import com.terwergreen.bugucms.dto.SysRoleDTO;
import com.terwergreen.bugucms.dto.SysUserDTO;
import com.terwergreen.bugucms.service.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.terwergreen.bugucms.utils.Constants.DEFAULT_PAGE_NUM;
import static com.terwergreen.bugucms.utils.Constants.DEFAULT_PAGE_SIZE;
import static com.terwergreen.bugucms.utils.Constants.XMLRPC_URL;

@Service
public class PostServiceImpl implements PostService {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private CommonDAO commonDAO;

    @Resource
    private CommonService commonService;

    @Value("${bugucms.admin.password.encode.enable}")
    private boolean isDbAdminPasswordEncoded;

    @Override
    public List<PostDTO> getRecentPosts(Map paramMap) throws BusinessServiceException {
        List<PostDTO> posts = getPostsByPage(DEFAULT_PAGE_NUM, DEFAULT_PAGE_SIZE, paramMap).getList();
        if (CollectionUtils.isEmpty(posts)) {
            logger.error("查询结果为空");
            return null;
        } else {
            logger.info("查询分页文章");
            return posts;
        }
    }

    /**
     * 查询单个文章
     *
     * @param slug
     * @return
     */
    @Override
    public PostDTO getPostBySlug(String slug) throws BusinessServiceException {
        Map paramMap = new HashMap();
        paramMap.put("slug", slug);
        PostDTO post = (PostDTO) commonDAO.querySingleByMap("getPostBySlug", paramMap);
        return post;
    }

    @Override
    public PostDTO getPostById(Integer postId) throws BusinessServiceException {
        Map paramMap = new HashMap();
        paramMap.put("postId", postId);
        PostDTO post = (PostDTO) commonDAO.querySingleByMap("getPostById", paramMap);
        return post;
    }

    @Override
    public PageInfo<PostDTO> getPostsByPage(Integer pageNum, Integer pageSize, Map paramMap) throws BusinessServiceException {
        PageHelper.startPage(pageNum, pageSize);
        List<PostDTO> list = (List<PostDTO>) commonDAO.queryListByMap("getPostsByType", paramMap);
        // 分页信息
        PageInfo<PostDTO> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();
        if (pageNum > pages) {
            pageInfo.setList(new ArrayList<>());
        }
        pageNum = pageInfo.getPageNum();
        pageSize = pageInfo.getPageSize();
        logger.info("分页信息：total=" + total + "，pages=" + pages + "，pageNum=" + pageNum + "，pageSize=" + pageSize);
        return pageInfo;
    }

    @Override
    public List getUsersBlogs(String appkey, String username, String password) throws BusinessServiceException {
        //[{"blogName":"测试博客","xmlrpc":"http://localhost/wordpress/xmlrpc.php","isAdmin":true,"blogid":"1","url":"http://localhost/wordpress/"}]

        SiteConfigDTO siteConfigDTO = commonService.getSiteConfig();
        List<Map> usersBlogs = new ArrayList<>();

        Map userBlog = new HashMap();
        userBlog.put("blogName", siteConfigDTO.getWebname());
        String url = siteConfigDTO.getWeburl();
        userBlog.put("xmlrpc", url + XMLRPC_URL);
        //检测是否是管理员
        boolean isAdmin = false;
        SysUserDTO sysUserDTO = (SysUserDTO) commonDAO.querySingleByString("selectByUserName", username);
        if (sysUserDTO == null) {
            throw new UsernameNotFoundException("用户名不存在。");
        }
        //if (isDbAdminPasswordEncoded) {
        //    password = BugucmsConfig.passwordEncoder().encode(sysUserDTO.getPassword());
        //}
        //hashed就是明文密码password加密后的结果，存储到数据库
        //String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        //candidate是明文密码，checkpw方法返回的是boolean
        if (!BCrypt.checkpw(password, sysUserDTO.getPassword())) {
            throw new BusinessServiceException("密码错误。");
        }
        //if (sysUserDTO == null || !BCrypt.checkpw(password, sysUserDTO.getPassword())) {
        //    // 用户名或密码不正确。
        //    throw new BusinessServiceException("用户名或密码不正确。");
        //}
        for (SysRoleDTO role : sysUserDTO.getSysRoles()) {
            logger.info("role：" + role.getName());
            if ("ADMIN".equals(role.getName())) {
                isAdmin = true;
                break;
            }
        }
        userBlog.put("isAdmin", isAdmin);
        userBlog.put("blogid", sysUserDTO.getId());
        if (!url.endsWith("/")) {
            url += "/";
        }
        userBlog.put("url", url);

        usersBlogs.add(userBlog);
        return usersBlogs;
    }

    @Override
    public Integer newPost(PostDTO post) {
        Integer postId = 0;
        int count = (int) commonDAO.insertByObject("insertPost", post);
        if (count > 0) {
            logger.info("新增文章成功");
            postId = post.getPostId();
        }
        return postId;
    }

    @Override
    public boolean editPostById(PostDTO post) {
        int count = commonDAO.updateByObject("updatePost", post);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePostBySlug(String postSlug) {
        Map paramMap = new HashMap();
        paramMap.put("postSlug", postSlug);
        int count = commonDAO.delete("deletePostBySlug", paramMap);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePostById(Integer postId) {
        Map paramMap = new HashMap();
        paramMap.put("postId", postId);
        int count = commonDAO.delete("deletePostById", paramMap);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean saveOrUpdatePostMeta(PostMetaDTO postMeta) {
        int count = commonDAO.updateByObject("saveOrUpdatePostMeta", postMeta);
        if (count > 0) {
            return true;
        }
        return false;
    }
}
