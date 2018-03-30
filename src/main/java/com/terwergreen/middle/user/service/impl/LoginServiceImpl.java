package com.terwergreen.middle.user.service.impl;

import com.terwergreen.middle.user.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public String login(String username, String password) {
        return "0";
    }
}
