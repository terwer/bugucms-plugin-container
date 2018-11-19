package com.terwergreen.bugucms.service.impl;

import com.terwergreen.bugucms.base.dao.CommonDAO;
import com.terwergreen.bugucms.dto.SysRoleDTO;
import com.terwergreen.bugucms.dto.SysUserDTO;
import com.terwergreen.bugucms.service.SysUserService;
import com.terwergreen.bugucms.config.BugucmsConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Terwer
 * @Date 2018/6/25 13:49
 * @Version 1.0
 * @Description 自定义UserDetailsService 接口
 **/
@Service
public class SysUserServiceImpl implements SysUserService {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private CommonDAO commonDAO;

    @Value("${bugucms.admin.password.encode.enable}")
    private boolean isDbAdminPasswordEncoded;

    /**
     * 重写loadUserByUsername 方法获得 userdetails 类型用户
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserDTO sysUserDTO = (SysUserDTO) commonDAO.querySingleByString("selectByUserName", username);
        if (sysUserDTO == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        //设置密码
        String password = sysUserDTO.getPassword();
        if (!isDbAdminPasswordEncoded) {
            password = BugucmsConfig.passwordEncoder().encode(sysUserDTO.getPassword());
        }

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        //用于添加用户的权限。只要把用户权限添加到authorities就万事大吉。
        for (SysRoleDTO role : sysUserDTO.getSysRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            logger.info("role：" + role.getName());
        }

        SysUserDTO validUser = (new SysUserDTO(username, password, authorities)).initDetail(sysUserDTO);
        logger.debug("当前登陆用户：user=" + validUser.toString());
        return validUser;
    }
}
