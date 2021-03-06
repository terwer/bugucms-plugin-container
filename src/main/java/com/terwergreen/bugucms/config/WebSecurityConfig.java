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
import com.terwergreen.util.RestResponse;
import com.terwergreen.util.RestResponseStates;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 安全配置
 *
 * @author Terwer
 * @version 1.0 2018/6/22 15:55
 **/
@SuppressWarnings("all")
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
            String loginProcessingUrl = (String) data.getOrDefault("loginProcessingUrl", "login");
            String logoutUrl = (String) data.getOrDefault("logoutUrl", "logout");
            String usernameParameter = (String) data.getOrDefault("usernameParameter", "username");
            String passwordParameter = (String) data.getOrDefault("passwordParameter", "password");

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
                        .loginPage("/" + loginPath)
                        .loginProcessingUrl("/" + loginProcessingUrl)
                        .usernameParameter(usernameParameter)
                        .passwordParameter(passwordParameter)
                        .successHandler((request, response, authentication) -> {
                            // 获取请求是从哪里来的
                            String targetUrl = "/";
                            DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
                            if (defaultSavedRequest != null) {
                                targetUrl = defaultSavedRequest.getRedirectUrl();
                            }
                            logger.info("targetUrl:" + targetUrl);

                            Map resultMap = new HashMap();
                            resultMap.put("targetUrl", targetUrl);

                            response.setStatus(HttpServletResponse.SC_OK);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            RestResponse restResponse = new RestResponse(RestResponseStates.SUCCESS.getValue(),"登陆成功",resultMap);
                            String responseToClient = JSON.toJSONString(restResponse);
                            response.getWriter().write(responseToClient);
                            response.getWriter().flush();
                        })
                        .failureHandler((request, response, e) -> {
                            logger.info("登陆失败，用户名或者密码错误");

                            response.setStatus(HttpServletResponse.SC_OK);
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            RestResponse restResponse = new RestResponse(RestResponseStates.INVALID_PASSWORD.getValue(),"登陆失败，用户名或者密码错误",null);
                            String responseToClient = JSON.toJSONString(restResponse);
                            response.getWriter().write(responseToClient);
                            response.getWriter().flush();
                        })
                        .permitAll()
                        .and()
                        .logout()
                        .logoutUrl("/" + logoutUrl)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            String returnUrl = request.getParameter("returnUrl");
                            logger.info("logout ok,redirect to previous page:" + returnUrl);
                            response.sendRedirect(returnUrl);
                        })
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
