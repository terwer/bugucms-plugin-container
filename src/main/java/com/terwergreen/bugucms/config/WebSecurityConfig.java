/*
 * Copyright 2010-2018 Terwer Green.
 *
 * Licensed under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.terwergreen.bugucms.config;

import com.alibaba.fastjson.JSON;
import com.terwergreen.bugucms.container.BugucmsPluginManager;
import com.terwergreen.plugins.PluginInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 安全配置
 *
 * @author Terwer
 * @version 1.0
 * 2018/6/22 15:55
 **/
//@ConditionalOnProperty(name = "bugucms.web.application-type", havingValue = "servlet")
@ConditionalOnExpression("'${bugucms.web.application-type}'.equals('servlet') && ${bugucms.plugin-switch:true}")
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Log logger = LogFactory.getLog(WebSecurityConfig.class);
    /**
     * 授权插件名称
     */
    private static final String AUTH_PLUGIN = "auth-plugin";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BugucmsPluginManager pluginManager;

    /**
     * 获取密码加密策略
     *
     * @return 密码加密策略
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("WebMVC Security config http");
        PluginWrapper pluginWrapper = pluginManager.getPlugin(AUTH_PLUGIN);
        if (null != pluginWrapper) {
            List extentions = pluginManager.getExtensions(AUTH_PLUGIN);
            if (CollectionUtils.isEmpty(extentions)) {
                logger.warn(AUTH_PLUGIN + " extentions not exists");
                configSecurity(http, null);
            } else {
                logger.info("Get " + AUTH_PLUGIN + " extentions:" + extentions);
                PluginInterface extention = (PluginInterface) extentions.get(0);
                Map data = extention.data();
                logger.info("extentions data:" + JSON.toJSONString(data));
                configSecurity(http, data);
            }
        } else {
            logger.warn(AUTH_PLUGIN + " not exists");
            configSecurity(http, null);
        }
    }

    private void configSecurity(HttpSecurity http, Map data) throws Exception {
        //运行加载iframe
        http.headers().frameOptions().disable();

        //关闭csrf
        http.csrf().disable();

        if (null != data) {
            // 获取权限插件配置的内容
            int securityOn = (int) data.getOrDefault("securityOn", 0);
            String adminPath = (String) data.getOrDefault("adminPath", "admin");
            String loginPath = (String) data.getOrDefault("loginPath", "login");
            //配置权限及登录验证
            if (1 == securityOn) {
                logger.info("授权打开");
                //配置权限及登录验证
                http
                        .authorizeRequests()
                        .antMatchers("/").permitAll()
                        .antMatchers("/" + adminPath + "/**").hasRole("ADMIN")
                        .and()
                        .formLogin()
                        .loginPage("/" + loginPath + "")//.failureUrl("login?error")
                        .loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(new AjaxAuthSuccessHandler())
                        .failureHandler(new AjaxAuthFailHandler())
                        .permitAll()
                        .and()
                        .logout()
                        .permitAll();
            } else {
                logger.info("授权关闭");
                http.authorizeRequests().antMatchers("/**").permitAll();
            }
        } else {
            logger.warn("授权插件不存在");
            http.authorizeRequests().antMatchers("/**").permitAll();
        }
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        logger.info("WebMVC Security config password");
        //内存中缓存权限数据
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("123456")).roles("ADMIN");
        String encodePassword = passwordEncoder.encode("123456");
        logger.info("WebMVC Security passwordSource:123456,encodePassword:" + encodePassword);
    }
}

class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (isAjaxRequest(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        } else {
            response.sendRedirect("/login.html");
        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }
}


class AjaxAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取请求是从哪里来的
        String referer = request.getParameter("referer");
        String targetUrl = super.determineTargetUrl(request, response);
        logger.info("referer from login page:" + referer);
        logger.info("targetUrl from security:" + referer);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        // String responseToClient = "{\"timestamp\":" + (new Date()).getTime() + ",\"status\":200,\"error\":\"\",\"message\":\"Authentication ok\",\"path\":\"/login\"}";
        String responseToClient = "{\"timestamp\":" + (new Date()).getTime() + ",\"status\":200,\"error\":\"\",\"message\":\"Authentication ok\",\"path\":\"" + referer + "\"}";
        response.getWriter().write(responseToClient);
        response.getWriter().flush();
    }
}

class AjaxAuthFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "登陆失败，用户名或者密码错误");
    }
}



