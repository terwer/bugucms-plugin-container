package com.terwergreen.bugucms.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;

/**
 * @Author Terwer
 * @Date 2018/6/25 12:02
 * @Version 1.0
 * @Description TODO
 **/
public class SysUserDTO implements UserDetails, CredentialsContainer {
    private static final Log logger = LogFactory.getLog(SysUserDTO.class);

    /**
     * 账户ID
     */
    @Getter
    @Setter
    private Integer id;
    /**
     * 昵称
     */
    @Getter
    @Setter
    private String nickName;
    /**
     * 简介
     */
    @Getter
    @Setter
    private String userProfile;
    /**
     * 个人连接
     */
    @Getter
    @Setter
    private String userUrl;
    /**
     * 手机
     */
    @Getter
    @Setter
    private String mobile;
    /**
     * 邮箱
     */
    @Getter
    @Setter
    private String email;
    /**
     * 状态
     */
    @Getter
    @Setter
    private Integer status;
    /**
     * 备注
     */
    @Getter
    @Setter
    private String userDesc;
    /**
     * 注册时间
     */
    @Getter
    @Setter
    private String userRegistered;

    /**
     * 角色集合
     */
    @Getter
    @Setter
    private List<SysRoleDTO> sysRoles;

    /**
     * 账户相关
     */
    private String password;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public SysUserDTO() {
        this.username = null;
        this.password = null;
        this.enabled = false;
        this.accountNonExpired = false;
        this.credentialsNonExpired = false;
        this.accountNonLocked = false;
        this.authorities = new HashSet<>();
    }

    public SysUserDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(username, password, true, true, true, true, authorities);
    }

    public SysUserDTO(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        if (username != null && !"".equals(username) && password != null) {
            this.username = username;
            this.password = password;
            this.enabled = enabled;
            this.accountNonExpired = accountNonExpired;
            this.credentialsNonExpired = credentialsNonExpired;
            this.accountNonLocked = accountNonLocked;
            this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
        } else {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
    }

    /**
     * 初始化其他属性
     *
     * @param oldSysUserDTO
     */
    public SysUserDTO initDetail(SysUserDTO oldSysUserDTO) {
        this.id = oldSysUserDTO.getId();
        this.nickName = oldSysUserDTO.getNickName();
        this.userProfile = oldSysUserDTO.getUserProfile();
        this.userUrl = oldSysUserDTO.getUserUrl();
        this.mobile = oldSysUserDTO.getMobile();
        this.email = oldSysUserDTO.getEmail();
        this.status = oldSysUserDTO.getStatus();
        this.userDesc = oldSysUserDTO.getUserDesc();
        this.userRegistered = oldSysUserDTO.getUserRegistered();
        this.sysRoles = oldSysUserDTO.getSysRoles();
        return this;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void eraseCredentials() {
        this.password = null;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet(new SysUserDTO.AuthorityComparator());
        Iterator iterator = authorities.iterator();

        while (iterator.hasNext()) {
            GrantedAuthority grantedAuthority = (GrantedAuthority) iterator.next();
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    public boolean equals(Object rhs) {
        return rhs instanceof SysUserDTO ? this.username.equals(((SysUserDTO) rhs).username) : false;
    }

    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");

        sb.append("id=" + id);
        sb.append("nickName='").append(nickName).append("; ");
        sb.append("userProfile='").append(this.userProfile).append("; ");
        sb.append("userUrl='").append(this.userUrl).append("; ");
        sb.append("mobile='").append(this.mobile).append("; ");
        sb.append("email='").append(this.email).append("; ");
        sb.append("status=").append(this.status).append("; ");
        sb.append("userDesc='").append(this.userDesc).append("; ");
        sb.append("userRegistered='").append(this.userRegistered).append("; ");
        sb.append("sysRoles=").append(this.sysRoles).append("; ");

        sb.append("Username: ").append(this.username).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.enabled).append("; ");
        sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
        sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
        sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");
        if (!this.authorities.isEmpty()) {
            sb.append("Granted Authorities: ");
            boolean first = true;
            Iterator iterator = this.authorities.iterator();

            while (iterator.hasNext()) {
                GrantedAuthority auth = (GrantedAuthority) iterator.next();
                if (!first) {
                    sb.append(",");
                }

                first = false;
                sb.append(auth);
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }

    public static SysUserDTO.UserBuilder withUsername(String username) {
        return builder().username(username);
    }

    public static SysUserDTO.UserBuilder builder() {
        return new SysUserDTO.UserBuilder();
    }

    /**
     * @deprecated
     */
    public static SysUserDTO.UserBuilder withDefaultPasswordEncoder() {
        logger.warn("SysUserDTO.withDefaultPasswordEncoder() is considered unsafe for production and is only intended for sample applications.");
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        SysUserDTO.UserBuilder builder = builder();
        encoder.getClass();
        return builder.passwordEncoder(encoder::encode);
    }

    public static SysUserDTO.UserBuilder withUserDetails(UserDetails userDetails) {
        return withUsername(userDetails.getUsername()).password(userDetails.getPassword()).accountExpired(!userDetails.isAccountNonExpired()).accountLocked(!userDetails.isAccountNonLocked()).authorities(userDetails.getAuthorities()).credentialsExpired(!userDetails.isCredentialsNonExpired()).disabled(!userDetails.isEnabled());
    }

    public static class UserBuilder {
        private String username;
        private String password;
        private List<GrantedAuthority> authorities;
        private boolean accountExpired;
        private boolean accountLocked;
        private boolean credentialsExpired;
        private boolean disabled;
        private Function<String, String> passwordEncoder;

        private UserBuilder() {
            this.passwordEncoder = (password) -> {
                return password;
            };
        }

        public SysUserDTO.UserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }

        public SysUserDTO.UserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        public SysUserDTO.UserBuilder passwordEncoder(Function<String, String> encoder) {
            Assert.notNull(encoder, "encoder cannot be null");
            this.passwordEncoder = encoder;
            return this;
        }

        public SysUserDTO.UserBuilder roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList(roles.length);
            String[] userRoles = roles;
            int length = roles.length;

            for (int i = 0; i < length; ++i) {
                String role = userRoles[i];
                Assert.isTrue(!role.startsWith("ROLE_"), role + " cannot start with ROLE_ (it is automatically added)");
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }

            return this.authorities((Collection) authorities);
        }

        public SysUserDTO.UserBuilder authorities(GrantedAuthority... authorities) {
            return this.authorities((Collection) Arrays.asList(authorities));
        }

        public SysUserDTO.UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList(authorities);
            return this;
        }

        public SysUserDTO.UserBuilder authorities(String... authorities) {
            return this.authorities((Collection) AuthorityUtils.createAuthorityList(authorities));
        }

        public SysUserDTO.UserBuilder accountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        public SysUserDTO.UserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public SysUserDTO.UserBuilder credentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public SysUserDTO.UserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public UserDetails build() {
            String encodedPassword = (String) this.passwordEncoder.apply(this.password);
            return new SysUserDTO(this.username, encodedPassword, !this.disabled, !this.accountExpired, !this.credentialsExpired, !this.accountLocked, this.authorities);
        }
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = 500L;

        private AuthorityComparator() {
        }

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            } else {
                return g1.getAuthority() == null ? 1 : g1.getAuthority().compareTo(g2.getAuthority());
            }
        }
    }
}
