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
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.util.CollectionUtils;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * 安全授权配置
 *
 * @author Terwer
 * @version 1.0
 * 2018/6/22 15:55
 **/
@SuppressWarnings("all")
//@ConditionalOnProperty(name = "bugucms.web.application-type", havingValue = "reactive")
@ConditionalOnExpression("'${bugucms.web.application-type}'.equals('reactive') && ${bugucms.plugin-switch:true}")
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {
    private static final Log logger = LogFactory.getLog(WebFluxSecurityConfig.class);

    /**
     * 授权插件名称
     */
    private static final String AUTH_PLUGIN = "auth-plugin";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BugucmsPluginManager pluginManager;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        logger.info("WebFlux Security is running");
        SecurityWebFilterChain filterChain = null;
        PluginWrapper pluginWrapper = pluginManager.getPlugin(AUTH_PLUGIN);
        if (null != pluginWrapper) {
            // 获取授权插件扩展点
            List extentions = pluginManager.getExtensions(AUTH_PLUGIN);
            if (CollectionUtils.isEmpty(extentions)) {
                logger.warn(AUTH_PLUGIN + " extentions not exists");
                filterChain = configSecurity(http, null);
            } else {
                logger.info("Get " + AUTH_PLUGIN + " extentions:" + extentions);
                PluginInterface extention = (PluginInterface) extentions.get(0);
                Map data = extention.data();
                logger.info("extentions data:" + JSON.toJSONString(data));
                filterChain = configSecurity(http, data);
            }
        } else {
            logger.warn(AUTH_PLUGIN + " not exists");
            filterChain = configSecurity(http, null);
        }
        return filterChain;
    }

    private SecurityWebFilterChain configSecurity(ServerHttpSecurity http, Map data) {
        //运行加载iframe
        http.headers().frameOptions().disable();

        //关闭csrf
        http.csrf().disable();

        //http.csrf(
        //.csrfTokenRepository(customCsrfTokenRepository)
        //.requireCsrfProtectionMatcher(customCsrfMatcher)

        if (null != data) {
            // 获取权限插件配置的内容
            int securityOn = (int) data.getOrDefault("securityOn", 0);
            String adminPath = (String) data.getOrDefault("adminPath", "admin");
            String loginPath = (String) data.getOrDefault("loginPath", "login");

            //配置权限及登录验证
            if (1 == securityOn) {
                logger.info("授权打开");
                http.authorizeExchange()
                        .pathMatchers("/" + adminPath + "/**")
                        .authenticated()
                        .pathMatchers("/**")
                        .permitAll();

                http.formLogin()
                        .loginPage("/" + loginPath + "")
                        //.authenticationFailureHandler(new RedirectServerAuthenticationFailureHandler("/login?error"))
                        //.authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/admin"))
                        .and()
                        .logout()
                        //.logoutUrl("/logout")
                        .logoutSuccessHandler(logoutSuccessHandler("/login?logout"));
            } else {
                logger.info("授权关闭");
                http.authorizeExchange()
                        .pathMatchers("/**")
                        .permitAll();
            }
        } else {
            logger.warn("授权插件不存在");
            http.authorizeExchange()
                    .pathMatchers("/**")
                    .permitAll();
        }
        return http.build();
    }

    public ServerLogoutSuccessHandler logoutSuccessHandler(String uri) {
        RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
        successHandler.setLogoutSuccessUrl(URI.create(uri));
        return successHandler;
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        logger.info("WebFlux Security config password");
        //内存中缓存权限数据
        User.UserBuilder userBuilder = User.builder();
        UserDetails admin = userBuilder.username("admin").password(passwordEncoder.encode("123456")).roles("USER", "ADMIN").build();
        // 输出加密密码
        String encodePassword = passwordEncoder.encode("123456");
        logger.info("encodePassword:" + encodePassword);
        return new MapReactiveUserDetailsService(admin);
    }
}


