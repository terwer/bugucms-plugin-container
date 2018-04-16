package com.terwergreen.middle.user.service;

import com.terwergreen.middle.user.dto.UserDTO;

import javax.servlet.http.HttpSession;
import java.util.Map;

@SuppressWarnings({"rawtypes"})
public interface LoginService {
    /**
     * 登录
     *
     * @param account  账号
     * @param password 密码
     * @return
     */
    Map login(String account, String password);

    /**
     * 校验账户
     *
     * @param account     账户
     * @param type        校验类型(normal,sso)
     * @param accountType 账户类型(accountId,email,phone)
     * @return true, false
     */
    String validateAccount(String account, String type, String accountType);

    /**
     * 校验密码
     *
     * @param account     账户
     * @param type        校验类型(normal,sso)
     * @param accountType 账户类型(accountId,email,phone)
     * @param password    密码
     * @return true, false
     */
    String validatePassword(String account, String type, String accountType, String password);

    /**
     * 是否已经登录
     *
     * @return true, false
     */
    boolean isLogin(HttpSession session);

    /**
     * 从Session中获取登录的用户ID，并查出用户信息
     *
     * @param session
     */
    UserDTO getLoginUserInfo(HttpSession session);
}
