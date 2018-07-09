package com.terwergreen.bugucms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.terwergreen.bugucms.base.service.BusinessServiceException;
import com.terwergreen.bugucms.config.BugucmsConfig;
import com.terwergreen.bugucms.core.dao.CommonDAO;
import com.terwergreen.bugucms.core.service.CommonService;
import com.terwergreen.bugucms.dto.PostDTO;
import com.terwergreen.bugucms.dto.SiteConfigDTO;
import com.terwergreen.bugucms.dto.SysRoleDTO;
import com.terwergreen.bugucms.dto.SysUserDTO;
import com.terwergreen.bugucms.service.PostService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.terwergreen.bugucms.util.Constants.XMLRPC_URL;

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
    public List<PostDTO> getPosts() throws BusinessServiceException {
        Map paramMap = new HashMap();
        List<PostDTO> posts = (List<PostDTO>) commonDAO.queryListByMap("get_posts_by_user", paramMap);
        if (CollectionUtils.isEmpty(posts)) {
            return null;
        } else {
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
        PostDTO post = (PostDTO) commonDAO.querySingle("get_post_by_slug", paramMap);
        return post;
    }

    @Override
    public PostDTO getPostById(Integer postId) throws BusinessServiceException {
        Map paramMap = new HashMap();
        paramMap.put("postId", postId);
        PostDTO post = (PostDTO) commonDAO.querySingle("get_post_by_id", paramMap);
        return post;
    }

    @Override
    public PageInfo<PostDTO> getPostsByPage(Integer pageNum, Integer pageSize) throws BusinessServiceException {
        PageHelper.startPage(pageNum, pageSize);
        List<PostDTO> list = (List<PostDTO>) commonDAO.queryList("get_posts_by_page");
        // 分页信息
        PageInfo<PostDTO> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        int pages = pageInfo.getPages();
        pageNum = pageInfo.getPageNum();
        pageSize = pageInfo.getPageSize();
        logger.info("分页信息：total=" + total + "，pages=" + pages + "，pageNum=" + pageNum + "，pageSize=" + pageSize);

        if (CollectionUtils.isEmpty(pageInfo.getList())) {
            return null;
        } else {
            return pageInfo;
        }
    }

    @Override
    public List getUsersBlogs(String appkey, String username, String password) throws BusinessServiceException {
        //[{"blogName":"测试博客","xmlrpc":"http://localhost/wordpress/xmlrpc.php","isAdmin":true,"blogid":"1","url":"http://localhost/wordpress/"}]

        SiteConfigDTO siteConfigDTO = commonService.getSiteConfig();
        List<Map> usersBlogs = new ArrayList<>();

        Map userBlog = new HashMap();
        userBlog.put("blogName", siteConfigDTO.getWebname());
        String url = siteConfigDTO.getWeburl();
        if (!url.endsWith("/")) {
            url += "/";
        }
        userBlog.put("xmlrpc", url + XMLRPC_URL);
        //检测是否是管理员
        boolean isAdmin = false;
        SysUserDTO sysUserDTO = (SysUserDTO) commonDAO.querySingleByString("selectByUserName", username);
        if (sysUserDTO == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        if (!isDbAdminPasswordEncoded) {
            password = BugucmsConfig.passwordEncoder().encode(sysUserDTO.getPassword());
        }
        if(!password.equals(sysUserDTO.getPassword())){
            throw new BusinessServiceException("密码错误");
        }
        for (SysRoleDTO role : sysUserDTO.getSysRoles()) {
            logger.info("role：" + role.getName());
            if("ADMIN".equals(role.getName())){
                isAdmin = true;
                break;
            }
        }
        userBlog.put("isAdmin", isAdmin);
        userBlog.put("blogid", sysUserDTO.getId());
        userBlog.put("url", url);

        usersBlogs.add(userBlog);
        return usersBlogs;
    }
}
