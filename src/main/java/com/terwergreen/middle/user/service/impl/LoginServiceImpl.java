package com.terwergreen.middle.user.service.impl;

import com.terwergreen.framework.core.bg.biz.service.BaseService;
import com.terwergreen.middle.common.dao.CommonDAO;
import com.terwergreen.middle.user.dto.UserDTO;
import com.terwergreen.middle.user.service.LoginService;
import com.terwergreen.middle.user.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class LoginServiceImpl extends BaseService implements LoginService {

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public Map login(String account, String password) {
        Map resultMap = new HashMap();
        String loginStatus = "0";//0正常，1账号不存在，2密码错误
        String accountFlag = validateAccount(account, Constants.TYPE_NORMAL, Constants.ACCOUNT_TYPE_PHONE);
        if (Constants.RESULT_INVALID.equals(accountFlag)) {
            loginStatus = "1";
        } else {
            String passwordFlag = validatePassword(account, Constants.TYPE_NORMAL, Constants.ACCOUNT_TYPE_PHONE, password);
            if (Constants.RESULT_INVALID.equals(passwordFlag)) {
                loginStatus = "2";
            }
        }
        resultMap.put("loginStatus", loginStatus);
        logger.debug("查询校验结果:loginStatus=" + loginStatus);
        return resultMap;
    }

    /**
     * 校验账户
     *
     * @param account     账户
     * @param type        校验类型(normal,sso)
     * @param accountType 账户类型(accountId,email,phone)
     * @return true, false
     */
    @Override
    public String validateAccount(String account, String type, String accountType) {
        Map paramMap = new HashMap();
        paramMap.put("account", account);
        int result = (int) commonDAO.querySingle("vali_by_account", paramMap);
        if (result > 0) {
            return Constants.RESULT_VALID;
        } else {
            return Constants.RESULT_INVALID;
        }
    }

    /**
     * 校验密码
     *
     * @param account     账户
     * @param type        校验类型(normal,sso)
     * @param accountType 账户类型(accountId,email,phone)
     * @param password    密码
     * @return true, false
     */
    @Override
    public String validatePassword(String account, String type, String accountType, String password) {
        Map paramMap = new HashMap();
        paramMap.put("account", account);
        paramMap.put("password", password);
        int result = (int) commonDAO.querySingle("vali_by_password", paramMap);
        if (result > 0) {
            return Constants.RESULT_VALID;
        } else {
            return Constants.RESULT_INVALID;
        }
    }

    /**
     * 是否已经登录
     *
     * @return true, false
     */
    @Override
    public boolean isLogin(HttpSession session) {
        String mobile = (String) session.getAttribute(com.terwergreen.front.user.util.Constants.SESSION_ACCOUNT_MOBILE);
        if (StringUtils.isEmpty(mobile)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 从Session中获取登录的用户ID，并查出用户信息
     *
     * @param session
     */
    public UserDTO getLoginUserInfo(HttpSession session) {
        String mobile = (String) session.getAttribute(com.terwergreen.front.user.util.Constants.SESSION_ACCOUNT_MOBILE);
        if (StringUtils.isEmpty(mobile)) {
            return null;
        } else {
            Map paramMap = new HashMap();
            paramMap.put("account", mobile);
            UserDTO userDTO = (UserDTO) commonDAO.querySingle("get_user_by_account", paramMap);
            return userDTO;
        }
    }

}
