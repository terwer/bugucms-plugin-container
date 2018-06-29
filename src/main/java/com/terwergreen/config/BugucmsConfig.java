package com.terwergreen.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author Terwer
 * @Date 2018/6/29 15:03
 * @Version 1.0
 * @Description TODO
 **/
public class BugucmsConfig {
    /**
     * 密码加密策略
     * @return
     */
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
