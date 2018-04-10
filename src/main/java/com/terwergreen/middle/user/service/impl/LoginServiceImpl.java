package com.terwergreen.middle.user.service.impl;

import com.terwergreen.framework.core.bg.biz.service.BaseService;
import com.terwergreen.middle.common.dao.CommonDAO;
import com.terwergreen.middle.user.service.LoginService;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LoginServiceImpl extends BaseService implements LoginService {

    @Autowired
    private CommonDAO commonDAO;

    @Override
    public Map login(String account, String password) {
        Map paramMap = new HashMap();
        paramMap.put("account", account);
        paramMap.put("password", password);
        List list = commonDAO.queryList("login_by_account", paramMap);

        Map resultMap = new HashMap();
        String loginStatus = "0";
        if(list.size() > 0){
            loginStatus = "1";
        }
        resultMap.put("loginStatus", loginStatus);
        resultMap.put("loginInfo", list);

        super.logger.debug("查询结果：" + JSONValue.toJSONString(list));
        return resultMap;
    }
}
